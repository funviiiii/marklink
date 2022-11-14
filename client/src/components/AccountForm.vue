<template>
  <el-form
      label-width="100"
      class="el-col-offset-6"
  >
    <el-form-item label="用户名："
                  required
    >
      <el-input
          v-model="account.username"
          maxlength="16"
          clearable
          class="el-col-10"
      ></el-input>
    </el-form-item>
    <el-form-item label="密码：" required>
      <el-input v-model="account.password"
                clearable
                type="password"
                maxlength="16"
                show-password
                class="el-col-10"
      ></el-input>
    </el-form-item>
  </el-form>
</template>

<script setup>

import {reactive} from "vue";

const account = reactive({
  username: "",
  password: "",
})
</script>

<style scoped>

</style>