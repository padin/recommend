<template>
  <van-nav-bar
      :title="title"
  />
  <div>
    <van-form>
      <van-cell-group inset>
        <van-field v-model ="userinfo.username" label="电话" :rules="[
                { required: true, message: '请填写管理员的手机号码！' },
                { pattern: /^1[3456789]\d{9}$/, message: '手机号码格式错误！'}
              ]"
                   type="tel" placeholder="请输入11位手机号" >
        </van-field>
        <van-field v-model ="userinfo.password" label="密码" :rules="[
                { required: true, message: '请填写您的6-18位密码！' },
                { pattern: /^\d{6,18}$/, message: '请填写您的6-18位密码！'}
              ]"
                   type="password" placeholder="请输入密码" >
        </van-field>
        <van-button type="primary" @click="confirm" >确认交易</van-button>
      </van-cell-group>
    </van-form>
  </div>
</template>

<script>
import {
  Form, Field, Button, Divider, Picker, Popup, Cell, CellGroup, Col, Row, Stepper, RadioGroup, Radio, Dialog, Toast
} from 'vant';

import {confirmDeal, getCustomerByCouponId} from "../api/index.js"
import {useRouter,useRoute} from "vue-router";
import {onMounted, reactive, ref} from "vue";
import {useStore} from "vuex";

export default {
  name: "Check",
  setup(){
    let title = ref("检票");
    let userinfo=reactive({
      username:"",
      password:""
    })
    let customer = {
      id:""
    }
    const router = useRouter()
    const route = useRoute()
    const state = useStore()
    onMounted(()=>{
      const id = route.params.id;
      getCustomerByCouponId(id).then(res=>{
        customer = res.data;
        title.value = "检票对象----"+res.data.realName
      })
    })
    function confirm(){
      // 网络请求
      let deal = {
        couponID:route.params.id,
        adminPhoneNumber:userinfo.username,
        password:userinfo.password,
        customerID:customer.id
      }
      confirmDeal(deal).then(resp=>{
        if (resp.code == 200){
          Toast("检票成功！")
        }else{
          Toast(resp.data)
        }
      },error=>{
        Toast("检票失败:"+error.msg)
        console.log("error",error)
      })
    }
    return {
      userinfo,
      confirm,
      title
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