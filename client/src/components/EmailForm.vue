<template>
  <el-form
      label-width="100"
      class="el-col-offset-6"
      :rules="rules"
      :model="account"
      status-icon
  >
    <el-form-item label="邮箱："
                  prop="email"
                  required
    >
      <el-input
          v-model="account.email"
          clearable
          class="el-col-10"
      ></el-input>
    </el-form-item>
    <el-form-item label="密码：" prop="password" required>
      <el-input v-model="account.password"
                clearable
                type="password"
                maxlength="16"
                show-password
                class="el-col-10"
      ></el-input>
    </el-form-item>
    <slot></slot>
  </el-form>
</template>

<script setup>

import {reactive} from "vue";

const account = reactive({
  email: "",
  password: ""
})

const rules = {
  email: [{
    validator: (rule, value, callback) => {
      if (!/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(value)) {
        callback(new Error("邮箱格式有误，请重新输入!"))
      } else {
        callback();
      }
    }, trigger: "blur"
  }],
  password: [{
    validator: (rule, value, callback) => {
      if (!/[\w.]{6,16}/.test(value)) {
        callback(new Error("密码格式化有误，请输入6~16位字母或数字"));
      } else {
        callback()
      }
    }, trigger: "blur"
  }]
}

</script>

<style scoped>

</style>