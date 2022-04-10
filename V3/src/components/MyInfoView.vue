<template>
  <van-nav-bar
      title="我的信息"
  />
  <van-cell-group>
    <van-cell title="商户名称" icon="friends" :value="info.userName"></van-cell>
    <van-cell title="联系人" icon="friends" :value="info.realName"></van-cell>
    <van-cell title="联系电话" icon="phone" :value="info.phoneNumber"></van-cell>
    <van-cell title="我的推荐二维码" ></van-cell>
    <van-image
          width="20rem"
          height="20rem"
          fit="contain"
          :src="info.erCodeAddress"
    />
  </van-cell-group>

</template>

<script>
// require('@/assets/CFDE3BBF.png')
import {onMounted, reactive} from "vue";
import {getUserInfo} from "../api/index.js";
export default {
  name: "myInfoView",
  setup(){
    let info =reactive({});
    onMounted(()=>{
      getUserInfo().then(res=>{
        info.userName = res.data.userName;
        info.realName = res.data.realName;
        info.phoneNumber = res.data.phoneNumber;
        info.erCodeAddress = res.data.erCodeAddress;
      })

    })

    return {
      info
    }
  }

}
</script>

<style scoped>

</style>