import axios from "axios";
import { Toast } from 'vant';
const http = {}
import router from "../router/index.js";
import store from "../store/index.js";

// const store = useStore()


axios.defaults.baseURL = "http://10.168.1.200:8080"
const request = axios.create({
    timeout: 5000,
    headers: {
        'Content-Type': "application/json; charset=utf-8"
    }
})


// 添加请求拦截器
request.interceptors.request.use(config => {
    config.headers['Authorization'] = localStorage.getItem("token")
    return config
})

// 响应拦截器及异常处理
request.interceptors.response.use(
    response =>{
        return response
    },err =>{
        if(err && err.response){
            switch (err.response.status){
                case 400:
                    err.message = '请求出错'
                    break
                case 401:
                    Toast("授权失败，请重新登陆")
                    setTimeout(() =>{
                        window.location.reload()
                    },1000)
                    return ;
                case 403:
                    err.message = "拒绝访问"
                    break
                case 404:
                    err.message = "请求错误，未找到该资源"
                    break
                case 500:
                    err.message = "内部错误，请联系管理人员"
                    break
            }
        } else {
            err.message = "连接服务器失败"
        }
        Toast.fail(err.message)
        return Promise.reject(err.response)
    }
)

http.get = function (url , options) {
    return new Promise((resolve,reject) =>{
        request.get(url,options).then(response =>{
            if(response.code === 200 ){
                resolve(response)
            } else{
                Toast.fail(response.data)
                reject(response)
            }
        }).catch(exception=>{
            console.log(exception)
        })
    })
}

http.post = function (url , options) {
    return new Promise((resolve,reject) =>{
        request.post(url,options).then(response =>{
            // debugger;
            // 如果请求成功
            if(response.data.code === 200 ){
                const pattern = /^\/login.*/;
                // 如果登陆成功
                if(pattern.test(url)){
                    const jwt = response.headers['authorization']
                    store.commit('SET_TOKEN', jwt)
                    localStorage.setItem("token",jwt)
                    store.commit("SET_ROLE",response.data.data.role)
                    store.commit("SET_OID",response.data.data.oid)
                    store.commit("SET_UID",response.data.data.id)
                }
                resolve(response.data)
            } else{
                Toast.fail(response.data.msg)
                reject(response.data)
            }
        }).catch(exception=>{
            console.log(exception)
        })
    })
}

export default http

