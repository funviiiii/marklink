<template>
  <el-container>
    <el-main>
      <el-divider content-position="left"><h2>账户信息</h2></el-divider>
      <br>
      <account-form>
        <el-form-item label="邮箱地址：" :error="errMsg.email">
          <el-input
              v-model="account.email"
              clearable
              class="el-col-10"
          >
          </el-input>
        </el-form-item>
        <el-form-item label="验证码：" :error="errMsg.code">
          <el-input
              class="el-col-10"
              v-model="account.verificationCode"
              :formatter="value=>value.toUpperCase()"
              maxlength="6"
          >
            <template #suffix>
              <el-link type="primary" :underline="false" @click="getVerificationCode">获取验证码</el-link>
            </template>
          </el-input>
        </el-form-item>
      </account-form>
      <br>
      <el-divider content-position="left"><h2>个人信息</h2></el-divider>
      <br>
      <info-form></info-form>
      <br>
      <el-row>
        <el-col
            :span="7" :offset="8"
        >
          <el-container>
            <el-button @click="signUp">
              注册
            </el-button>
            &nbsp;&nbsp;&nbsp;
            <div class="sign-in-link">
              已有账号？点此
              <el-link :underline="false" href="/signin"><p>登录</p></el-link>
            </div>
          </el-container>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>

<script setup>
import AccountForm from './AccountForm.vue'
import InfoForm from "./InfoForm.vue";
import {reactive, ref} from "vue";
import axios from "../utils/axios";

const account = reactive({
  username: "",
  password: "",
  email: "",
  verificationCode: ""
})

const user = reactive({
  name: "",
  gender: "",
  birthday: "",
  isMarried: false
})

const errMsg = reactive({
  email: "",
  code: ""
})

const getVerificationCode = () => {
  if (!/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(account.email)) {
    errMsg.email = "邮箱格式有误，请重新输入"
    return;
  } else {
    errMsg.email = "";
  }
  axios({
    method: "GET",
    url: `/account/email/${account.email}`,
  }).then(res => {
    if (res.status === 200) {
      errMsg.email = "当前邮箱已存在，请更换邮箱地址!";
    } else {
      errMsg.email = "";
    }
  }).catch(() => {
    errMsg.email = "";
  })
}

function signUp() {

}
</script>

<style scoped>
.sign-in-link {
  text-align: center;
  font-size: small;

}
</style>