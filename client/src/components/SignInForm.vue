<template>
  <el-container>
    <el-main>
      <el-divider content-position="center"><h2>登录</h2></el-divider>
      <br>
      <router-view/>
      <el-row>
        <el-col
            :span="6" :offset="9"
        >
          <el-button>登录</el-button>
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

let router = useRouter();

function linkController() {
  if (router.currentRoute.value.path === "/account/email") {
    router.push("/account/username");
  } else {
    router.push("/account/email");
  }
}

let method = computed(() => {
  return router.currentRoute.value.path === "/account/email" ?
      "用户名" : "邮箱";
})

</script>

<style scoped>

</style>