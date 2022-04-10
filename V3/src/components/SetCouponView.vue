<template>
  <van-nav-bar
      title="设置优惠券"
  />
  <van-divider content-position="left">是否启用</van-divider>
  <!--      <van-switch v-model="coupon.couponEnable" />-->
  <van-switch v-model="coupon.couponEnable" />

  <van-divider content-position="left">价格(元,2位精度)</van-divider>
  <van-cell-group inset>
    <van-field v-model="coupon.couponPrice" label="优惠价格" placeholder="请输入价格" />
  </van-cell-group>

  <van-divider content-position="left">有效期(天)</van-divider>
  <van-slider v-model="coupon.couponValidity" bar-height="4px"  min="0" max="100" active-color="#ee0a24">
    <template #button>
      <div class="custom-button">{{ coupon.couponValidity }}</div>
    </template>
  </van-slider>



  <van-divider content-position="left">共发行{{coupon.couponTotal}}张</van-divider>
  <van-divider content-position="left">剩余</van-divider>
  <van-progress :percentage="(coupon.couponSurplus/coupon.couponTotal*100).toFixed(2)" />
  <van-divider content-position="left">追加</van-divider>
  <van-stepper v-model="coupon.add" step="10" min="0" theme="round" button-size="22" disable-input /> 张

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
import {reactive, onMounted, computed} from "vue";
import {setCoupon,getOrg,getUserInfo} from "../api";
import store from "../store/index.js";
import {useRouter} from "vue-router";
import {Toast} from "vant";
import {onClickLeft} from '../common/index.js'
export default {
  name: "SetCouponView",
  setup(){
    const router = useRouter();
    let coupon = reactive({
      couponEnable: false,
      couponPrice: 0,
      couponSurplus: 0,
      couponTotal: 0,
      couponValidity: 0,
      id: store.state.oid,
      add:0
    });
    function onClickLeft(){
      history.back()
    }
    onMounted(()=>{
      getOrg({id:store.state.oid}).then(res=>{
        coupon.id = res.data.id;
        coupon.couponEnable = res.data.couponEnable;
        coupon.couponPrice = res.data.couponPrice;
        coupon.couponSurplus = res.data.couponSurplus;
        coupon.couponTotal = res.data.couponTotal;
        coupon.couponValidity = res.data.couponValidity;
      })
    });
    function submit(){
      setCoupon(coupon).then(res=>{
        Toast("提交成功")
        router.push("/settings/index")
      })
    };

    function cancel(){
       router.push("/settings/index")
    };

    return {
      coupon,
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
