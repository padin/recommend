<template>
  <div class="hello">

    <van-nav-bar
        title="尚层空间活动报名"
    />
    <van-form @failed="onFailed">
      <van-cell-group inset>
        <van-divider content-position="left">联系方式</van-divider>
        <van-field v-model ="info.realName" label="姓名" :rules="[{pattern:/^[\u4E00-\u9FA5]{2,8}$/,message:'请输入正确的中文姓名'}]" placeholder="请输入姓名"/>
        <van-field v-model ="info.phoneNumber" label="电话" :rules="[
                { required: true, message: '请填写您的手机号码！' },
                { pattern: /^1[3456789]\d{9}$/, message: '手机号码格式错误！'}
              ]" type="tel" placeholder="请输入11位手机号" >
          <template #button>
            <!--            默认，等待输入手机号 -->
            <van-button disabled v-if="btn.showWait" size="small" type="primary" >
              发送验证码
            </van-button>
<!--            正常的-->
            <van-button v-if="btn.showNormal" size="small" type="primary" @click="sendValidateCode">
              发送验证码
            </van-button>
<!--            有倒计时的-->
            <van-button v-if="btn.showCountDown" disabled size="small" type="primary" >
              <van-count-down  :time="time" format=" ss 秒后重新发送" @finish="countDownFinish"/>
            </van-button>

          </template>
        </van-field>
        <van-field v-model ="info.vcode" label="验证码" type="num" placeholder="6位验证码"/>
        <van-divider content-position="left">楼盘信息</van-divider>

        <van-field
          readonly
          clickable
          name="picker"
          :value="info.areaName"
          v-model="info.areaName"
          label="区县"
          placeholder="点击选择区县"
          @click="options.showPicker = true"
          :rules="[{require:true,mesage:'请选择区县'}]"
        />
        <van-popup v-model:show="options.showPicker" position="bottom">
          <van-picker
            show-toolbar
            :columns="options.areanames"
            @confirm="onConfirm"
            @cancel="options.showPicker = false"
          />
        </van-popup>


        <van-field v-model ="info.community" label="小区名称" placeholder="如：碧桂园"/>

        <van-field name="count1" label=" ">
          <template #input>
            第
            <van-stepper  v-model="info.buildPhase" />
            期
          </template>
        </van-field>
        
        
        <van-field name="count2" label="  ">
          <template #input>
            第
            <van-stepper  v-model="info.houseNumber" />
            幢
          </template>
        </van-field>

        <div style="height:20px"></div>

        <div>
          <van-field name="radio">
            <template #input>
              <van-checkbox v-model="options.checked">我已阅读并同意
                <a @click="showService">服务协议</a>和
                <a @click="showPrivacy">隐私协议</a>
              </van-checkbox>
            </template>
          </van-field>
        </div>
        <van-button type="primary"  @click="toAddCustomer">开始报名</van-button>
        <div style="height:50px"></div>
        <van-divider></van-divider>
        <div style="height:50px"></div>
      </van-cell-group>
    </van-form>
  </div>


  <van-popup closeable v-model:show="options.showEnrollSuccess" @close="closeEnrollSuccessPopup">
    报名成功！
    <br/>
    请截图保存此二维码以获取优惠券。
    <van-image width="20rem"
               height="20rem"
               fit="contain"
               :src = "customerErCodePath"
    >
    </van-image>
  </van-popup>
</template>

<script>
import {computed, reactive, ref, watch} from "vue";
import {Dialog, Toast} from "vant";
import testJson from '../json/agreement.json'
import {useRoute,useRouter} from 'vue-router'
import {addCustomer,getValiCode} from "../api/index.js";
import store from "../store/index.js";
export default {
  name: 'Enroll',
  setup(){

    const route = useRoute()
    const router = useRouter()
    let id = route.params.id
    let info=reactive({
      realName:"",
      areaName:"",
      community:"",
      buildPhase:1,
      houseNumber:1,
      phoneNumber:"",
      referees:id
    })
    let customerErCodePath = ref("")
    let customerId = "";
    const time = ref(60 * 1000)
    let btn = reactive({
      showWait:true,
      showNormal:false,
      showCountDown:false,
    })
    let checkCode = ref("")
    store.commit("SET_REFEREES",id)

    let options = reactive({
      submitDisabled: false,
      showEnrollSuccess: false,
      showPicker: false,
      checked: true,
      vcode: "",
      serviceAgreement: "",
      privacyAgreement: "",
      areanames: [
        "天山区",
        "沙依巴克区",
        "新市区",
        "水磨沟区",
        "头屯河区",
        "达坂城区",
        "米东区",
        "乌鲁木齐县",
      ],
    })
    onMounted:{
      const {service,privacy} = testJson;
      options.serviceAgreement = service.join("\n");
      options.privacyAgreement = privacy.join("\n");
    }

    function countDownFinish(){
      btn.showCountDown =false;
      btn.showWait =false;
      btn.showNormal =true;
    }

    function toAddCustomer (){
      console.log(options.checked)
      let patternName=/^[\u4E00-\u9FA5]{2,8}$/
      if (!patternName.test(info.realName)){
        Toast("请填写姓名")
        return;
      }else if (checkCode.value != info.vcode){
        Toast("验证码错误")
        return;
      }else if (info.areaName.length == 0){
        Toast("请选择区县")
        return;
      }else if (info.areaName.length == 0){
        Toast("请填写小区")
        return;
      } else if (info.community.length == 0){
        Toast("请填写小区")
        return;
      }else if (!options.checked ){
        Toast("请阅读并同意服务协")
        return;
      }else{
        addCustomer(info).then(res=>{
          if (res.code ==200){
            options.showEnrollSuccess = true;
            customerErCodePath.value = res.data.erCodeAddress
            customerId = res.data.id;
            store.commit("SET_UID",res.data.id)
          }
        })
      }
    }
    function sendValidateCode(){

      let phoneNumber = info.phoneNumber;
      btn.showCountDown = true;
      btn.showWait = false;
      btn.showNormal = false;
      getValiCode({"phoneNumber":phoneNumber}).then(res=>{
        checkCode.value= res.data
      })
    }
    function closeEnrollSuccessPopup(){
      // emit("changeActive",1)
      router.push("/coupon/"+customerId)
    }
    function onConfirm(value){
      info.areaName = value;
      options.showPicker = false;
      console.log(options.showPicker)
    }
    function showService(){
      Dialog.alert({
        message:options.serviceAgreement
      })
    }
    function showPrivacy(){
      Dialog.alert({
        message:options.privacyAgreement
      })
    }
    function onFailed(){
      console.log()
    }
    watch(info,(value,old)=>{
      let patten = /^1[3456789]\d{9}$/
      if (!btn.showCountDown){
        if (patten.test(value.phoneNumber)){
          btn.showWait =false
          btn.showNormal =true
        }else{
          btn.showWait = true;
          btn.showNormal = false;
        }
      }
    })
    return {
      closeEnrollSuccessPopup,
      customerErCodePath,
      checkCode,
      btn,
      countDownFinish,
      time,
      info,options,sendValidateCode,onConfirm,showService,showPrivacy,onFailed,toAddCustomer
    }
  },
  components: {
    [Dialog.Component.name]: Dialog.Component,
  },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
