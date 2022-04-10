<template>
  <van-nav-bar
      title="推荐人"
  />
  <van-cell-group>
    <van-cell v-for="item in list" :title="item.user_name" is-link @click="cellClick(item.id)">
      {{item.real_name}}
      <template #right-icon>
        <van-badge :content="item.recommended">
          <van-icon name="shop-o" class="search-icon" />
        </van-badge>
      </template>
    </van-cell>
  </van-cell-group>

</template>

<script>
import {listReferees} from "../api/index.js";
import {computed, reactive, ref} from "vue";
import {useStore} from "vuex";
import {useRouter} from "vue-router";
import {Toast} from "vant";

export default {
  name: "RefereesIndex",
  setup(){
    const store = useStore();
    const router = useRouter();
    let list = reactive([{}]);
    let uid = computed(()=>{
      return store.state.uid
    })
    let role = computed(()=>{
      return store.state.role
    })
    onMounted:{
      listReferees().then(res=>{
        list.splice(0,list.length);
        res.data.forEach(i=>{
          list.push(i)
        })
      })
    }
    function cellClick(id){
      if (role.value=='REFEREES'&&id!=uid.value){
        Toast("只能查看自己的推荐信息")
        return;
      }
      if(role.value=="ADMIN"||uid.value==id){
        router.push("/referees/listCustomersView/"+id)
      }

    }

    return {list,
      cellClick}
  }
}
</script>

<style scoped>

</style>