<template>
  <van-nav-bar
      title="登陆"
  />
  <div>
    <van-form>
      <van-cell-group inset>
        <van-field v-model ="userinfo.username" label="电话" :rules="[
                { required: true, message: '请填写您的手机号码！' },
                { pattern: /^1[3456789]\d{9}$/, message: '手机号码格式错误！'}
              ]"
                   type="tel" placeholder="请输入11位手机号" >
        </van-field>
        <van-field v-model ="userinfo.password" label="密码" :rules="[
                { required: true, message: '请填写6-18位密码！' },
                { pattern: /^\d{6,18}$/, message: '请填写6-18位密码！'}
              ]"
                   type="password" placeholder="请输入密码" >
        </van-field>
        <van-button type="primary" @click="login" >登陆</van-button>
      </van-cell-group>
    </van-form>
  </div>
</template>

<script>
import {
  Form,
  Field,
  Button,
  Divider,
  Picker,
  Popup,
  Cell,
  CellGroup,
  Col,
  Row,
  Stepper,
  RadioGroup,
  Radio,
  Dialog,
  Toast
} from 'vant';

import {loginByPhone} from "../api/index.js"
import {useRouter} from "vue-router";
import {useStore} from "vuex";
import {reactive} from "vue";

export default {
  name: "Login",
  setup(){
    let userinfo=reactive({
      username:"",
      password:""
    })
    const router = useRouter()
    const store = useStore()
    function login(){
      // 网络请求
      loginByPhone(userinfo).then(resp=>{
        if (resp.code == 200){
          store.commit("SET_ACTIVE",2)
          router.push("/referees/refereesIndex")
        }else{
          Toast(resp.data)
        }
      },error=>{
        Toast("登陆失败")
        console.log("error",error)
      })
    }
    return {
      userinfo,
      login
    }
  },
  components:{
    [Form.name]:Form,
    [Cell.name]:Cell,
    [CellGroup.name]:CellGroup,
    [Field.name]:Field,
    [Button.name]:Button,
    [Divider.name]:Divider ,
    [Picker.name]:Picker,
    [Popup.name]:Popup,
    [Col.name]:Col,
    [Row.name]:Row,
    [Stepper.name]:Stepper,
    [RadioGroup.name]:RadioGroup,
    [Radio.name]:Radio,
    [Dialog.Component.name]:Dialog.Component
  },



}
</script>

<style scoped>

</style>