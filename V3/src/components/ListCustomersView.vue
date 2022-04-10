<template>
  <van-nav-bar
      title="客户列表"
  />
<!--  <van-cell title="选择日期区间" :value="date" @click="show = true" />-->
<!--  <van-calendar v-model:show="show" :min-date="minDate" :max-date="maxDate" type="range" @confirm="onConfirm" />-->
  <van-search
      v-model="selectKeyWord"
      shape="round"
      show-action
      background="#4fc08d"
      placeholder="请输入搜索关键词"
  />
  <van-cell-group>
    <van-cell v-for="(v,i) in customerListSearchFor" :title="v.realName" is-link icon="contact" @click="onClick(v.id)"></van-cell>
  </van-cell-group>

</template>

<script>
import {listCustomer} from "../api/index.js";
import {useRoute,useRouter} from "vue-router";
import {onMounted, reactive, ref, watch} from "vue";
export default {
  name: "ListCustomersView",
  setup(){
    let selectKeyWord = ref("")
    let date =ref("")
    let show = ref(false)
    const route = useRoute()
    const router = useRouter()
    let customerList = reactive([])
    let customerListSearchFor = reactive([])
    onMounted(()=>{
      let id = route.params.id
      listCustomer({"uid":id}).then(res=>{
        res.data.forEach(i=>{
          customerList.push(i)
          customerListSearchFor.push(i)
        })
      })
    })
    watch(selectKeyWord,(value, oldValue) => {
      customerListSearchFor.splice(0,customerListSearchFor.length);
      customerList.forEach(i=>{
        if(i["realName"].startsWith(value)){
          customerListSearchFor.push(i);
        }
      })
    })
    const formatDate = (date) => `${date.getFullYear()}/${date.getMonth() + 1}/${date.getDate()}`;
    function onConfirm(values){
      const [start, end] = values;
      show.value = false;
      date.value = `${formatDate(start)} - ${formatDate(end)}`;
    }
    function onClick(id){
      router.push("/referees/customerInfo/"+id)
    }
    return {
      customerList,
      date,
      show,
      onConfirm,
      // minDate: new Date(2022, 4, 1),
      // maxDate: new Date(2022, 12, 31),
      selectKeyWord,
      customerListSearchFor,
      onClick
    }
  }
}
</script>

<style scoped>

</style>