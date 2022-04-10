<template>
  <van-nav-bar
      title="修改密码"
  />
  <van-field
      v-model="info.oldPassword"
      type="password"
      name="原密码"
      label="原密码"
      placeholder="原密码"
      :rules="[{ required: true, message: '请填写密码' }]"
  />
  <van-field
      v-model="info.newPassword"
      type="password"
      name="新密码"
      label="新密码"
      placeholder="新密码"
      :rules="[{ required: true, message: '请填写密码' }]"
  />
  <div style="margin: 16px;">
    <van-button round block type="primary" @click="submit">
      提交
    </van-button>
  </div>
  <div style="margin: 16px;">
    <van-button round plain block type="primary" @click="cancel">
      取消
    </van-button>
  </div>


</template>


<script>
import {reactive, ref} from "vue";
import {setPassword} from "../api/index.js";
import {useRouter} from "vue-router";
import {Toast} from "vant";
import {onClickLeft} from '../common/index.js'
export default {
  name: "SetPasswordView",
  setup(){
    const router = useRouter();
    let info = reactive({
      oldPassword:"",
      newPassword:""
    })

    function submit(){
      setPassword(info).then(res=>{
        Toast("提交成功")
        router.push("/Settings/index")
      })
    };

    function cancel(){
       router.push("/settings/index")
    };
    function onClickLeft(){
      history.back()
    }

    return {
      info,
      submit,
      cancel,
      onClickLeft
    }
  }
}
</script>

<style scoped>
.custom-title {
  vertical-align: middle;
}
.custom-button {
  width: 26px;
  color: #fff;
  font-size: 13px;
  line-height: 18px;
  text-align: center;
  background-color: #ee0a24;
  border-radius: 100px;
}

</style>
