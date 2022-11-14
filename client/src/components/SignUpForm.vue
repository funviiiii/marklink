<template>
  <el-container>
    <el-main>
      <el-divider content-position="left"><h2>账户信息</h2></el-divider>
      <br>
      <account-form @report-data="updateAccountValue">
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
      <info-form @report-data="updateUserValue"></info-form>
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
import {ElMessage, ElNotification} from "element-plus";

const reg = {
  email: /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
  username_pwd: /[\w.]{6,16}/,
}

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

function updateUserValue(userData) {
  user.name = userData.name;
  user.gender = userData.gender;
  user.birthday = userData.birthday;
  user.isMarried = userData.isMarried;
}

function updateAccountValue(accountData) {
  account.username = accountData.username;
  account.password = accountData.password;
}

const getVerificationCode = () => {
  if (!reg.email.test(account.email)) {
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

function checkInfo() {
  if (!reg.username_pwd.test(account.username)) {
    ElMessage.warning("用户名输入格式有误，请检查输入内容");
    return null;
  }
  if (!reg.username_pwd.test(account.password)) {
    ElMessage.warning("密码输入格式有误，请检查输入内容");
    return null;
  }
  if (!reg.email.test(account.email) && account.email !== "") {
    ElMessage.warning("邮箱输入格式有误，请检查输入内容");
    return null;
  }
  let tmp = JSON.parse(JSON.stringify(account));
  if (!Boolean(account.email)) {
    delete tmp.email;
    delete tmp.verificationCode;
  }
}

function signUp() {
  let info = checkInfo();
  if (info === null) {
    return
  }

  // 新增属性
  info["name"] = user.name;
  info["gender"] = user.gender;
  info["birthday"] = user.birthday;
  info["isMarried"] = user.isMarried;

  // 注册请求
  axios({
    url: "/account",
    method: "POST",
    data: info
  }).then(res => {
    if (res.status === 200) {
      ElNotification({
        title: "成功！",
        message: "恭喜你，注册成功",
        type: "success",
      })
    } else {
      ElNotification({
        title: "错误！",
        message: res.data["msg"],
        type: "warning",
      })
    }
  }).catch(res => {
    ElNotification({
      title: "错误！",
      message: res.response.data["msg"],
      type: "warning",
    })
  })
}
</script>

<style scoped>
.sign-in-link {
  text-align: center;
  font-size: small;
}
</style>