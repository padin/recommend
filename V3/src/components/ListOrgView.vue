<template>
  <van-nav-bar
      title="组织列表"
  />
  <van-cell-group>
<!--    <van-cell title="orgs" :label="orgs" ></van-cell>-->
    <van-cell :title="item.organazationName" v-for="(item,index) in orgs"></van-cell>
  </van-cell-group>
<!--  {{orgs}}-->
</template>

<script>
import {listOrg} from "../api/index.js";
import {reactive} from "vue";

export default {
  name: "ListOrgView",
  setup(){
    let orgs = reactive([]);
    function onClickLeft(){
      history.back();
    }
    onMounted:{
      listOrg().then(res=>{
        console.log("res",res.data)
        res.data.forEach(i=>{
          orgs.push(i)
        })
        // orgs = res.data;
      })
    }
    return {
      orgs,
      onClickLeft
    }
  }
}
</script>

<style scoped>

</style>