<template>

  <router-view />
  <van-tabbar v-model="active" @change="onChange">
    <van-tabbar-item icon="comment">报名</van-tabbar-item>
    <van-tabbar-item icon="friends">我的</van-tabbar-item>
    <van-tabbar-item icon="shop" @click="toRefereesIndex">推荐</van-tabbar-item>
    <van-tabbar-item icon="setting" @click="toSettingsIndex">设置</van-tabbar-item>
  </van-tabbar>
</template>
<script>
import {computed, ref, watch} from 'vue';
import {useStore } from 'vuex'
import {useRouter} from "vue-router";
import {Toast} from "vant";
export default {
  name:"App",
  setup(){
    let refId = ref("")
    let userId = ref("")
    let role =  ref("");
    const router = useRouter();
    const store = useStore();
    const onClickLeft = () => history.back();
    function toSettingsIndex(val){
      router.push("/settings/index")
    }
    function toRefereesIndex(val){
      router.push("/referees/refereesIndex")
    }
    let active = computed(()=> {
      return store.state.active;
    })
    watch(active,(value,old)=>{

    })
    const onChange = (index) => {
      active = index
      store.commit("SET_ACTIVE",index);
      switch (index){
        case 0:
            refId = store.state.referees
            router.push("/enroll/"+refId)
          break
        case 1:
            userId = store.state.uid
            router.push("/coupon/"+userId)
          break
        case 2:
          router.push("/referees/refereesIndex")
          break
        case 3:
          router.push("/settings/index")
          break
      }
    };
    return {
      role,
      active,
      onClickLeft,
      refId,
      onChange,
      toSettingsIndex,
      toRefereesIndex,
    }

  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
  /*background-color:pink*/
}
</style>
