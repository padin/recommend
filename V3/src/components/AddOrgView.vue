<template>
  <van-nav-bar
      title="添加组织"
  />
  <van-form @failed="">
    <van-cell-group inset>
      <!-- 通过 pattern 进行正则校验 -->
      <van-field
          label="组织名称"
          v-model="org.organazationName"
          name="pattern"
          placeholder="组织名称"
          :rules="[{ require:true, message: '请输入内容' }]"
      />
      <van-field v-model ="org.mobilephone" label="管理员电话" :rules="[
                { required: true, message: '请填写该组织管理员的手机号码！' },
                { pattern: /^1[3456789]\d{9}$/, message: '手机号码格式错误！'}
              ]" type="tel" placeholder="请填写该组织管理员的手机号码" >

      </van-field>
      <van-field v-model ="org.realName" label="管理员姓名" :rules="[
                { required: true, message: '请填写该组织管理员的姓名！' },
                { pattern: /^[\u4E00-\u9FA5]{2,8}$/, message: '2-8位中文！'}
              ]" type="tel" placeholder="请填写该组织管理员的姓名" >

      </van-field>
      <van-field
          label="有效期(月)"
          name="validator">
        <template #input>
          <van-stepper  v-model="org.validityMonth" theme="round" button-size="22"/>
        </template>
      </van-field>


    </van-cell-group>
    <div style="margin: 16px;">
      <van-button @click="submitOrgAdd" round block type="primary" native-type="submit">
        提交
      </van-button>
    </div>
  </van-form>


</template>

<script>
import {addOrg} from "../api";
import {reactive, ref} from "vue";
import {Toast} from "vant";
import router from "../router/index.js";
export default {
  name: "",
  setup(){
    let org =reactive({
      organazationName:"",
      validityMonth:"",
      mobilephone:""
    })
    function onClickLeft(){
      history.back();
    }
    function toAddView(){
      this.$router.push("/addOrgView")
    }
    function submitOrgAdd(){
      addOrg(org).then(res=>{
        Toast("添加完成")
        if (res.code===200){
          router.push("/orgListView");
        }
      })
    }
    return {
      org,
      onClickLeft,
      toAddView,
      submitOrgAdd
    }
  }
}
</script>

<style scoped>

</style>