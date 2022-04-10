<template>

  <van-nav-bar title="我的优惠券"/>

  <van-card
      v-for="(v,i) in couponList"
      num="1"
      :price="v.price"
      desc="下单立减,通过商家扫描二维码验证后立即生效"
      :title="v.title"
      thumb="https://oss.wyizd.com/uPic/IUbhZO.png">
    <template v-if="!v.isUsed" #tags>
      <van-tag plain type="danger">未使用</van-tag>
    </template>
    <template v-if="v.isUsed" #tags>
      <van-tag plain type="default">已使用</van-tag>
    </template>
    <template #footer>
      <van-button size="mini" @click="lookCouponErCode(v.erCodeAddress)">显示二维码</van-button>
    </template>
  </van-card>


  <van-popup v-model:show="options.showPopup">
    确认交易时
    <br/>
    将此二维码展示给商家检验
    <van-image width="20rem"
               height="20rem"
               fit="contain"
               :src = "options.couponErCodePath">
    </van-image>
  </van-popup>




</template>


<script>
import {onMounted, reactive,ref} from "vue";
import {listCouponByCustomer} from '../api/index.js'
import {useRoute} from "vue-router";
import {useStore} from "vuex";

export default {
  name: "Coupon",
  setup(){
    const route = useRoute();
    const store = useStore();

    let options = reactive({
      showPopup : false,
      couponErCodePath : ""
    })
    let couponList = reactive([
      {
        createTime: "",
        description: "",
        erCodeAddress: "",
        id: "",
        isUsed: false,
        price: 0,
        templateId: "",
        title: "",
        userId: "",
        validateTime: "",
        validityTime: null,
        verifier: null,
      }
    ])
    function lookCouponErCode(addr){
      options.showPopup = true;
      options.couponErCodePath = addr;
    }
    onMounted(()=>{
      let id = route.params.id
      store.commit("SET_UID",id)
      listCouponByCustomer(id).then(res=>{
        couponList.splice(0,couponList.length);
        res.data.forEach(i=>{
          couponList.push(i);
        })
      })

    })
    return{
      options,
      couponList,
      lookCouponErCode,
    }
  }
}
</script>

<style scoped>

</style>