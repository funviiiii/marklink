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
            <el-input disabled v-model="info.username"></el-input>
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
            <el-form-item label="姓名：">
              <el-input :disabled="!isWriting" v-model="info.name" maxlength="16"></el-input>
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
              <el-date-picker disabled v-model="info.induction" value-format="YYYY-MM-DD">
              </el-date-picker>
            </el-form-item>
            <el-form-item
                label="部门："
            >
              <el-select disabled v-model="info.department">
                <el-option v-for="(item, index) in department" :index="index" :value="item">
                  {{ item }}
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item
                label="职位："
            >
              <el-select disabled v-model="info.role">
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
            <el-form-item label="工资：">
              ￥{{ info.salary }}
            </el-form-item>
            <el-form-item>
              <el-button @click="editResume">{{ info.resume == null ? '创建简历' : '修改简历' }}</el-button>
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
            <el-input v-model="passwordForm.oldPassword" type="password" show-password clearable maxlength="16"/>
          </el-form-item>
          <el-form-item label="新密码" label-width="100px">
            <el-input v-model="passwordForm.newPassword" type="password" show-password clearable maxlength="16"/>
          </el-form-item>
        </el-form>
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消修改</el-button>
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

import {onMounted, reactive, ref, watch} from "vue";
import axios from "../utils/axios.js";
import dayjs from "dayjs";
import {ElMessage} from "element-plus";
import {useRouter} from "vue-router";

let isWriting = ref(false);

const department = ref([]);

const role = ref([]);

let router = useRouter();

let updateBtnInfo = ref("修改信息");

let dialogVisible = ref(false);

function editResume() {
  if (info.resume != null) {
    router.push(`/resume/${info.resume}?editable=true`)
  } else {
    router.push("/resume?editable=true")
  }
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
  resume: "",
  salary: ""
})

onMounted(() => {
  if (localStorage.getItem('token') !== null) {
    getInfo();
  } else {
    router.push('/account')
  }
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
    ElMessage.error(res.response.data["msg"]);
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
    ElMessage.error(res.response.data["msg"]);
  })
  // 获取部门信息
  axios({
    url: "/department",
    method: "GET"
  }).then(res => {
    if (res.status === 200) {
      const data = res.data["content"];
      // 清空数据
      department.value = [];
      for (const item of data) {
        department.value.push(item["departmentName"])
      }
    } else {
      ElMessage.error(res.data["msg"])
    }
  }).catch(res => {
    ElMessage.error(res.response.data["msg"])
  })
  // 获取职位信息
  axios({
    url: "/role",
    method: "GET"
  }).then(res => {
    if (res.status === 200) {
      const data = res.data["content"];
      // 清空数据
      role.value = [];
      for (const item of data) {
        role.value.push(item["roleName"])
      }
    } else {
      ElMessage.error(res.data["msg"])
    }
  }).catch(res => {
    ElMessage.error(res.response.data["msg"])
  })
  // 获取工资信息
  axios({
    url: `/salary/one?token=${token}`,
    method: "GET"
  }).then(res => {
    if (res.status === 200) {
      info.salary = res.data["content"]["realSalary"]
    }
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

watch(dialogVisible, () => {
  passwordForm.newPassword = "";
  passwordForm.oldPassword = "";
});

function checkName() {
  if (!/[\u4e00-\u9fa5\w.·丶]+/.test(info.name)) {
    ElMessage.warning("你的真实姓名输入不合法")
    return false;
  }
  return true;
}

const updateInfo = () => {
  if (!checkName()) {
    return
  }
  let tmp = JSON.parse(JSON.stringify(info));
  delete tmp["username"]
  delete tmp["induction"]
  delete tmp["department"]
  delete tmp["role"]
  axios({
    url: "/user",
    method: "PUT",
    data: tmp
  }).then(res => {
    if (res.status === 201) {
      ElMessage.success("修改信息成功");
    } else {
      ElMessage.warning(res.data["msg"]);
    }
  }).catch(res => {
    ElMessage.warning(res.response.data["msg"]);
  })
  getInfo();
  updateBtnInfo.value = "修改信息";
  isWriting.value = false;
}

function checkPassword() {
  const reg = /[\w.]{6,16}/;
  if (!reg.test(passwordForm.oldPassword) && !reg.test(passwordForm.newPassword)) {
    ElMessage.warning("密码格式不合法，请输入6到16位字母或数字（可以存在英文字符的\".\"）");
    return false;
  }
  if (passwordForm.oldPassword === passwordForm.newPassword) {
    ElMessage.warning("两次密码输入相同，请确认您的输入是否有误");
    return false;
  }
  return true;
}

const changePassword = () => {
  if (!checkPassword()) {
    return
  }
  let tmp = {};
  tmp["uid"] = info.uid;
  tmp["oldPassword"] = passwordForm.oldPassword;
  tmp["newPassword"] = passwordForm.newPassword;
  axios({
    url: "/account",
    method: "PUT",
    data: tmp
  }).then(res => {
    if (res.status === 201) {
      // 修改成功
      ElMessage.success(res.data["msg"]);
      dialogVisible.value = false;
    } else {
      ElMessage.warning(res.data["msg"]);
    }
  }).catch(res => {
    ElMessage.warning(res.response.data["msg"])
  })
}
</script>

<style scoped>

</style>