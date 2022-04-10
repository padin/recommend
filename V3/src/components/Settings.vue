<template>
  <router-view></router-view>
</template>

<script>
import router from "../router/index.js";
import {logout} from "../api/index.js"
import store from "../store/index.js";
export default {
  name: "Settings",
  setup(){
    // function onClickLeft(){
    //   history.back();
    // }
    // function toAddOrgView(){
    //   router.push("/settings/addOrgView")
    // }
    // function toAddRefereesView(){
    //   router.push("/settings/addRefereesView")
    // }
    // function toSetCouponView(){
    //   router.push("/settings/setCouponView")
    // }
    // function toLogout(){
    //   logout()
    //   store.commit("SET_TOKEN",0)
    //   store.commit("SET_ROLE",0)
    //   router.push("/settings/login")
    // }

    // function toSetPasswordView(){
    //   router.push("/settings/setPasswordView")
    // }



    return {
      // toAddOrgView,
      // toAddRefereesView,
      // toLogout,
      // toSetCouponView,
      // toSetPasswordView
    }
  }
}
</script>

<style scoped>

</style>