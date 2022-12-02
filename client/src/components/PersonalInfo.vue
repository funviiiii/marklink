<template>
  <el-container>
    <el-main>
      <el-row>
        <el-divider content-position="left"><h1>账户信息</h1></el-divider>
        <el-form
            label-width="100"
            class="el-col-16"
        >
          <el-form-item
              label="用户id："
          >
            <el-input disabled v-model="info.uid"></el-input>
          </el-form-item>
          <el-form-item
              label="用户名："
          >
            <el-input :disabled="!isWriting" v-model="info.username"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button @click="dialogVisible = true">修改密码</el-button>
          </el-form-item>
        </el-form>
      </el-row>
      <el-row>
        <el-divider content-position="left"><h1>用户信息</h1></el-divider>
        <el-col>
          <el-form
              label-width="100"
              class="el-col-16"
          >
            <el-form-item
                label="姓名："
            >
              <el-input :disabled="!isWriting" v-model="info.name"></el-input>
            </el-form-item>
            <el-form-item
                label="性别："
            >
              <el-select :disabled="!isWriting" v-model="info.gender">
                <el-option value="男">男</el-option>
                <el-option value="女">女</el-option>
                <el-option value="保密">保密</el-option>
              </el-select>
            </el-form-item>
            <el-form-item
                label="生日："
            >
              <el-date-picker
                  :disabled="!isWriting"
                  v-model="info.birthday"
                  value-format="YYYY-MM-DD"
              >
              </el-date-picker>
            </el-form-item>
            <el-form-item
                label="入职日期："
            >
              <el-date-picker
                  :disabled="!isWriting"
                  v-model="info.induction"
                  value-format="YYYY-MM-DD"
              >
              </el-date-picker>
            </el-form-item>
            <el-form-item
                label="部门："
            >
              <el-select
                  :disabled="!isWriting"
                  v-model="info.department"
              >
                <el-option v-for="(item, index) in department" :index="index" :value="item">
                  {{ item }}
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item
                label="职位："
            >
              <el-select
                  :disabled="!isWriting"
                  v-model="info.role"
              >
                <el-option v-for="(item, index) in role" :index="index" :value="item">
                  {{ item }}
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item
                label="是否已婚："
            >
              <el-checkbox :disabled="!isWriting" v-model="info.isMarried"></el-checkbox>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8" :offset="8">
          <el-button @click="writingInfo">{{ updateBtnInfo }}</el-button>
          <el-button @click="updateInfo" :style="{display: isWriting ? 'inline-block' : 'none'}">完成修改</el-button>
        </el-col>
      </el-row>
      <el-dialog v-model="dialogVisible" title="修改密码">
        <el-form :model="passwordForm">
          <el-form-item label="旧密码" label-width="100px">
            <el-input v-model="passwordForm.oldPassword"/>
          </el-form-item>
          <el-form-item label="新密码" label-width="100px">
            <el-input></el-input>
          </el-form-item>
        </el-form>
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="cancelChangePassword">取消修改</el-button>
        <el-button type="primary" @click="changePassword">
          确认修改
        </el-button>
      </span>
        </template>
      </el-dialog>
    </el-main>
  </el-container>
</template>

<script setup>

import {onMounted, reactive, ref} from "vue";
import axios from "../utils/axios.js";
import dayjs from "dayjs";
import {ElMessage} from "element-plus";

let isWriting = ref(false);

const department = ref([]);

const role = ref([]);

let updateBtnInfo = ref("修改信息");

let dialogVisible = ref(false);

const cancelChangePassword = () => {
  dialogVisible.value = false;
  passwordForm.oldPassword = "";
  passwordForm.newPassword = "";
}

const passwordForm = reactive({
  oldPassword: "",
  newPassword: ""
});

const info = reactive({
  uid: "",
  username: "",
  name: "",
  gender: "男",
  birthday: "1970-01-01",
  induction: "1970-01-01",
  department: "",
  role: "",
  isMarried: false,
  resume: ""
})
onMounted(() => {
  getInfo();
})
const getInfo = () => {
  let token = localStorage.getItem("token");
  // 获取账户对象
  axios({
    url: `account?token=${token}`,
    method: "GET"
  }).then(res => {
    if (res.status === 200) {
      // 更新数据
      const account = res.data["content"];
      info.uid = account["uid"];
      info.username = account["username"]
    } else {
      ElMessage.error(res.data["msg"]);
    }
  }).catch(res => {
    ElMessage.error(res.data["msg"]);
  })
  // 获取用户对象
  axios({
    url: `/user/get?token=${token}`,
    method: "GET",
  }).then(res => {
    if (res.status === 200) {
      // 更新数据
      const user = res.data["content"];
      info.name = user["name"];
      info.gender = user["gender"];
      info.birthday = dayjs(user["birthday"]).format("YYYY-MM-DD");
      info.induction = dayjs(user["induction"]).format("YYYY-MM-DD");
      info.department = user["department"];
      info.role = user["role"];
      info.isMarried = user["isMarried"];
      info.resume = user["resume"];
    } else {
      ElMessage.error(res.data["msg"]);
    }
  }).catch(res => {
    ElMessage.error(res.data["msg"]);
  })
}

const writingInfo = () => {
  if (isWriting.value) {
    // 撤回修改
    getInfo();
    isWriting.value = false;
    updateBtnInfo.value = "修改信息"
  } else {
    // 进入修改状态
    isWriting.value = true;
    updateBtnInfo.value = "撤回修改"
  }
}

const updateInfo = () => {
  // TODO 提交信息更改
}

const changePassword = () => {
  dialogVisible.value = false;
  // TODO 修改密码
}
</script>

<style scoped>

</style>