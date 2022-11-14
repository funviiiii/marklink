<template>
  <el-form
      label-width="100"
      class="el-col-offset-6"
      :rules="rules"
      :model="account"
      status-icon
  >
    <el-form-item label="用户名："
                  prop="username"
                  required
    >
      <el-input
          v-model="account.username"
          maxlength="16"
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
    <el-form-item label="确认密码：" prop="checkPassword" required>
      <el-input v-model="account.checkPassword"
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
  username: "",
  password: "",
  checkPassword: "",
  email: "",
  verificationCode: ""
})

const regs = {
  username_pwd: /[\w.]{6,16}/
}

const usernameAndPwdChecker = (rule, value, callback) => {
  if (!regs.username_pwd.test(value)) {
    callback(new Error("您的输入不合法，请输入6~16位字母或数字"))
  } else {
    callback()
  }
}

const rules = reactive({
  username: [{validator: usernameAndPwdChecker, trigger: "blur"}],
  password: [{validator: usernameAndPwdChecker, trigger: "blur"}],
  checkPassword: [
    {
      validator: (rule, value, callback) => {
        if (value !== account.password || value === "" || value === null) {
          callback(new Error("你的输入和原来的密码不一致"))
        } else {
          callback()
        }
      },
      trigger: "blur"
    }
  ]
})
</script>

<style scoped>

</style>