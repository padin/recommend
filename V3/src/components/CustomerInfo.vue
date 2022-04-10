<template>
  <van-nav-bar
      title="客户信息"
  />
  <van-cell-group>
    <van-cell title="姓名" icon="friends" :value="info.realName"></van-cell>
    <van-cell title="电话" icon="phone" :value="info.phoneNumber"></van-cell>
    <van-cell title="区域" icon="guide-o" :value="info.areaName"></van-cell>
    <van-cell title="小区名称" icon="comment" :value="info.community"></van-cell>
    <van-cell title="建筑期号" icon="weapp-nav" :value="info.buildPhase"></van-cell>
    <van-cell title="楼房号" icon="wap-home" :value="info.buildPhase"></van-cell>
    <van-cell title="是否下单" icon="gold-coin" :value="info.isOrder==0?'否':'是'"></van-cell>
  </van-cell-group>

</template>

<script>
import {useRoute} from "vue-router";
import {onMounted, reactive} from "vue";
import {getCustomerInfo} from "../api/index.js";
export default {
  name: "CustomerInfo",
  setup(){
    const route = useRoute()
    let info = reactive({
      realName:"",
      phoneNumber:"",
      areaName:"",
      community:"",
      buildPhase:"",
      houseNumber:"",
      isOrder:""
    })
    onMounted(()=>{
      let id = route.params.id
      getCustomerInfo({"id":id}).then(res=>{
        info.realName = res.data.realName
        info.phoneNumber = res.data.phoneNumber
        info.areaName = res.data.areaName
        info.community = res.data.community
        info.buildPhase = res.data.buildPhase
        info.houseNumber = res.data.houseNumber
        info.isOrder = res.data.isOrder
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