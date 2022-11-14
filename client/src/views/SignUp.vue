<template>
  <el-container>
    <el-main
        class="el-col-lg-14 el-col-lg-offset-5 el-col-xl-10 el-col-xl-offset-7"
    >
      <el-tabs type="border-card" class="demo-tabs">
        <el-tab-pane label="注册">
          <sign-up-form></sign-up-form>
        </el-tab-pane>
        <el-tab-pane label="登录">
        </el-tab-pane>
      </el-tabs>
    </el-main>
  </el-container>
</template>

<script setup>
import SignUpForm from "../components/SignUpForm.vue";
</script>

<style scoped>

</style>