<template>
  <el-container>
    <el-main>
      <el-divider content-position="center"><h2>登录</h2></el-divider>
      <br>
      <router-view @report-data="updateData"/>
      <el-row>
        <el-col
            :span="6" :offset="9"
        >
          <el-button @click="signIn">登录</el-button>
          &nbsp;
          <el-link @click="linkController">使用{{ method }}登录</el-link>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>

<script setup>

import {useRouter} from "vue-router";
import {computed} from "vue";
import {ElNotification} from "element-plus";
import axios from "../utils/axios.js";

let router = useRouter();

function linkController() {
  if (router.currentRoute.value.path === "/account/email") {
    router.push("/account/username");
  } else {
    router.push("/account/email");
  }
}

let formData;

function updateData(data) {
  formData = data;
}

let method = computed(() => {
  return router.currentRoute.value.path === "/account/email" ?
      "用户名" : "邮箱";
})

function signIn() {
  if (formData === undefined ||
      formData['password'] === undefined ||
      formData['password'] === null ||
      formData['password'] === '') {
    ElNotification.warning("你的输入有误，请检查输入后重试");
  } else {
    axios({
      url: "/account/login",
      method: "post",
      data: formData
    }).then(res => {
      if (res.status === 200) {
        ElNotification.success("登录成功！");
        // 保存token
        localStorage.setItem("token", res.data["content"]);
        router.push("/index");
      } else {
        ElNotification.success("登录失败，请检查信息是否有误！");
      }
    }).catch(() => {
      ElNotification.success("登录失败，请检查信息是否有误！");
    })
  }
}

</script>

<style scoped>

</style>