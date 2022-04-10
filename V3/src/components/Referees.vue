<template>

  <router-view></router-view>

</template>

<script>
import {listReferees} from "../api/index.js";
import {computed, reactive, ref} from "vue";
import {useStore} from "vuex";
import {Toast} from "vant";

export default {
  name: "Referees",
  setup(){

  }



}
</script>

<style scoped>

</style>