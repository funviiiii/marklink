<template>
  <el-container>
    <el-main>
      <el-divider content-position="left"><h2>账户信息</h2></el-divider>
      <br>
      <account-form></account-form>
      <br>
      <el-divider content-position="left"><h2>个人信息</h2></el-divider>
      <br>
      <info-form></info-form>
    </el-main>
  </el-container>
</template>

<script setup>
import AccountForm from './AccountForm.vue'
import InfoForm from "./InfoForm.vue";
</script>

<style scoped>

</style>