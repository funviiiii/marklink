<template>
  <el-container>
    <el-main>
      <h1></h1>
      <mavon-editor
          v-model="content"
          :editable="props.editable"
          placeholder=" "
      ></mavon-editor>
      <br>
      <el-button
          class="el-col-2 el-col-offset-11"
          :class="{'hidden-btn': !editable && auth}"
          @click="updateResume"
      >提交
      </el-button>
      <el-button
          class="el-col-2 el-col-offset-11"
          @click="router.push('/index')"
      >回到主页
      </el-button>
    </el-main>
  </el-container>
</template>

<script setup>
import editor, {mavonEditor} from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'

import {onMounted, ref} from "vue";
import axios from "../utils/axios.js";
import {ElMessage} from "element-plus";
import {useRouter} from "vue-router";

let content = ref("")

const props = defineProps(["id", "editable"]);

let auth = false;

const router = useRouter();

function updateResume() {
  if (localStorage.getItem("token") == null) {
    // 不存在token，不允许提交请求
    ElMessage.error("身份校验不通过，无法提交请求");
    return
  }
  let token = localStorage.getItem("token");
  if (props.id == null || props.id === '') {
    // 创建简历
    axios({
      url: "/resume",
      method: "POST",
      data: {
        token: token,
        content: content.value
      }
    }).then(res => {
      if (res.status === 200) {
        router.push("/index");
        ElMessage.success("新增简历成功");
      } else {
        ElMessage.warning(res.data["msg"]);
      }
    }).catch(res => ElMessage.warning(res.response.data["msg"]))
  } else {
    // 更新简历
    axios({
      url: "/resume",
      method: "PUT",
      data: {
        token: token,
        rid: props.id,
        content: content.value
      }
    }).then(res => {
      if (res.status === 200) {
        router.push("/index");
        ElMessage.success("更新简历成功");
      } else {
        ElMessage.warning(res.data["msg"]);
      }
    }).catch(res => ElMessage.warning(res.response.data["msg"]))
  }
}

onMounted(() => {
  // 校验token
  if (localStorage.getItem("token") == null) {
    router.push("/account/username");
    ElMessage.warning("您还未登录，请先登录")
    return
  }

  // 获取相应的简历
  getResume();
})

function getResume() {
  let token = localStorage.getItem("token");
  if (token == null) {
    ElMessage.warning("身份验证不通过，请重新登录");
    return
  }
  if (props.id != null) {
    axios({
      url: `/resume/${props.id}?token=${token}`,
      method: "GET",
    }).then(res => {
      if (res.status === 200) {
        content.value = res.data["content"];
      }
    }).catch(() => {
      ElMessage.warning("加载简历失败，请查看简历号是否出错")
    })
  }
}
</script>

<style scoped>
.hidden-btn {
  display: none;
}
</style>