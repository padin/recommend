import axios from "../utils/http";
import qs from 'qs'
import store from "../store/index.js";

export function getValiCode(phoneNumber){
    let a = qs.stringify(phoneNumber)
    return axios.post("/auth/getValiCode?"+a);
}

export function listCouponByCustomer(userId){
    return axios.post("/recommend/coupon/listCouponByCustomer?userId="+userId)
}

export function getCustomerByCouponId(couponId){
    return axios.post("/recommend/user/getCustomerByCouponId?couponId="+couponId)
}

export function confirmDeal(deal){
    return axios.post("/recommend/coupon/confirmDeal",deal)
}



export function loginByPhone(user){
    return axios.post("/login?"+qs.stringify(user))
}
export function logout(){
    return axios.post("/logout")
}

export function getUserInfo(){
    return axios.post("/auth/userInfo",{
        headers:[{Authorization:localStorage.getItem("token")}]
    })
}

export function addOrg(org){
    return axios.post("/recommend/organazation/create",org)
}

export function getOrg(org){
    return axios.post("/recommend/organazation/info",org)
}

export function listOrg(){
    return axios.post("/recommend/organazation/list")
}

export function addReferees(referees){
    return axios.post("/recommend/user/createReferees",referees)
}

export function listReferees(){
    return axios.post("/recommend/user/listReferees")
}

export function addCustomer(customer){
    return axios.post("/recommend/user/createCustomer",customer)
}

export function setCoupon(couponTemplate){
    return axios.post("/recommend/organazation/setTemplate",couponTemplate)
}

export function setPassword(user){
    return axios.post("/recommend/user/setPassword",user)
}

export function listCustomer(condition){
    return axios.post("/recommend/user/listCustomer",condition)
}

export function getCustomerInfo(user){
    return axios.post("/recommend/user/getCustomerInfo",user)
}




