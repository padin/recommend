<template>
  <van-nav-bar
      title="添加推荐人"
  />
  <van-form >
    <van-cell-group inset>
      <van-field
          v-model="referess.userName"
          name="推荐人名称"
          label="推荐人名称"
          placeholder="推荐人名称"
          :rules="[{ required: true, message: '请填写商户名称' }]"
      />
      <van-field
          v-model="referess.realName"
          name="联系人"
          label="联系人"
          placeholder="联系人"
      />
      <van-field
          v-model="referess.address"
          name="联系地址"
          label="联系地址"
          placeholder="联系地址"
      />
      <van-field
          v-model="referess.phoneNumber"
          name="联系电话"
          label="联系电话"
          placeholder="联系电话"
      />
    </van-cell-group>
    <div style="margin: 16px;">
      <van-button round block type="primary" @click="submitReferees">
        提交
      </van-button>
    </div>
  </van-form>
</template>

<script>
import {addReferees} from "../api/index.js";
import {Toast} from "vant";
import router from "../router/index.js";
import {reactive} from "vue";
export default {
  name: "AddRefereesView",
  setup(){
    let referess = reactive({})
    function submitReferees(){
        addReferees(referess).then(res=>{
          Toast(res.message)
          router.push("/referees/refereesIndex")
        })
    }
    function onClickLeft(){
      history.back()
    }
    return {
      referess,
      submitReferees,
      onClickLeft
    }
  }
}
</script>

<style scoped>

</style>