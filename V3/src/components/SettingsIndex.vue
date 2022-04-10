<template>
  <van-nav-bar
      title="设置"
  />
  <van-cell v-show="isSuperAdmin" title="添加组织" is-link @click="toAddOrgView"/>
  <van-cell v-show="isAdmin" title="添加推荐人" is-link @click="toAddRefereesView"/>
  <van-cell v-show="isAdmin" title="管理优惠券" is-link @click="toSetCouponView"/>
  <van-cell title="修改密码" is-link @click="toSetPasswordView"/>
  <van-cell v-show="isReferess" title="我的信息" is-link @click="toMyInfo"/>
  <van-button type="danger" round is-link @click="toLogout">注销登陆</van-button>

</template>

<script>
import router from "../router/index.js";
import {logout} from "../api/index.js"
import {useStore} from "vuex";
import {onClickLeft} from "../common/index.js";
import {ref,computed} from "vue";
import {Toast} from "vant";
export default {
  name: "SettingsIndex",
  setup(){
    const store = useStore()
    let role = store.state.role

    let isSuperAdmin = ref(false);
    isSuperAdmin = (role == 'SUPER_ADMIN')
    let isAdmin = ref(false)
    isAdmin = (role == 'SUPER_ADMIN' | role == 'ADMIN')
    let isReferess = ref(false)
    isReferess = (role == 'REFEREES')

    function onClickLeft(){
      history.back();
    }
    function toAddOrgView(){
      router.push("/settings/addOrgView")
    }
    function toAddRefereesView(){
      router.push("/settings/addRefereesView")
    }
    function toSetCouponView(){
      router.push("/settings/setCouponView")
    }
    function toSetPasswordView(){
      router.push("/settings/setPasswordView")
    }
    function toMyInfo(){
      if(isAdmin||isSuperAdmin){
        Toast("只有推荐人可以访问")
        return
      }
      router.push("/settings/myInfoView")
    }
    function toLogout(){
      logout()
      store.commit("SET_TOKEN",0)
      store.commit("SET_ROLE",0)
      localStorage.removeItem("token")
      router.push("/settings/login")
    }



    return {
      onClickLeft,
      toAddOrgView,
      toAddRefereesView,
      toLogout,
      toSetCouponView,
      toSetPasswordView,
      toMyInfo,
      isSuperAdmin,
      isAdmin,
      isReferess
    }
  }
}
</script>

<style scoped>

</style>