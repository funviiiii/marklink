<template>
  <mavon-editor
      v-model="content"
      :editable="false"
      placeholder=" "
  ></mavon-editor>
</template>

<script setup>
import {mavonEditor} from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'

import {onMounted, ref} from "vue";
import axios from "../utils/axios.js";
import {ElMessage} from "element-plus";

let content = ref("")

const props = defineProps(["id"]);

onMounted(() => {
  // 获取相应的简历
  axios({
    url: `/resume/${props.id}`,
    method: "GET",
  }).then(res => {
    if (res.status === 200) {
      content.value = res.data["content"];
    }
  }).catch(() => {
    ElMessage.warning("加载简历失败，请查看简历号是否出错")
  })
})
</script>

<style scoped>

</style>