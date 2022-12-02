<template>
  <el-container>
    <el-header>
      <el-menu mode="horizontal"
               :style="{display: authController.menu()}"
      >
        <router-link :to="{name: 'personal'}" class="menu-link"
                     :style="{display: authController.personnel}"
        >
          <el-menu-item index="1">基本信息</el-menu-item>
        </router-link>
        <router-link :to="{name: 'personnel'}" class="menu-link"
                     :style="{display: authController.personnel}"
        >
          <el-menu-item index="2">人事管理</el-menu-item>
        </router-link>
        <router-link :to="{name: 'salary'}"
                     class="menu-link"
                     :style="{display: authController.salary}"
                     :right="authController.salary"
        >
          <el-menu-item index="3">工资管理</el-menu-item>
        </router-link>
        <router-link :to="{name: 'info'}"
                     class="menu-link"
                     :style="{display: authController.info}"
                     :right="authController.info"
        >
          <el-menu-item index="4">基础信息管理</el-menu-item>
        </router-link>
        <router-link :to="{name: 'advance'}"
                     class="menu-link"
                     :style="{display: authController.advance}"
                     :right="authController.advance"
        >
          <el-menu-item index="5">高级管理</el-menu-item>
        </router-link>
      </el-menu>
    </el-header>
    <el-main>
      <!-- 获取当前是否有使用权限 -->
      <router-view
          :right="authController[router.currentRoute.value.path.replace(/.+index\//, '')] === 'flex'"
      />
    </el-main>
  </el-container>
</template>

<script setup>
import axios from "../utils/axios.js";
import {onMounted, reactive} from "vue";
import {useRouter} from "vue-router";
import {ElMessage} from "element-plus";
import PersonalInfo from "../components/PersonalInfo.vue";

const authController = reactive({
  personnel: 'none',
  salary: 'none',
  info: 'none',
  advance: 'none',
  menu() {
    if (
        this.personnel === 'none'
        && this.info === 'none'
        && this.salary === 'none'
        && this.advance === 'none'
    ) {
      return 'none'
    }
    return 'flex'
  }
});

const router = useRouter();

// 检测用户是否有权限访问相应的模块
const checkAuth = () => {
  const token = localStorage.getItem("token");
  if (token == null) {
    router.push("/account")
    ElMessage.warning("您还未登录，请先登录");
    return
  }
  axios({
    method: 'GET',
    url: `/user/role?token=${token}`
  }).then(res => {
    if (res.status === 200) {
      // 正常获取到职位信息，对可控制的内容进行赋值
      authController.personnel = res.data['content']['personnelRight'] ? 'flex' : 'none';
      authController.salary = res.data['content']['salaryRight'] ? 'flex' : 'none';
      authController.info = res.data['content']['infoRight'] ? 'flex' : 'none';
      authController.advance = res.data['content']['advanceRight'] ? 'flex' : 'none';
    } else {
      authController.personnel = 'none';
      authController.salary = 'none';
      authController.info = 'none';
      authController.advance = 'none';
      localStorage.removeItem("token")
      ElMessage.error("登录信息有误，请重新登录后重试")
    }
  }).catch(() => {
    authController.personnel = 'none';
    authController.salary = 'none';
    authController.info = 'none';
    authController.advance = 'none';
    localStorage.removeItem("token")
    ElMessage.error("登录信息有误，请重新登录后重试")
  })
}

onMounted(() => {
  checkAuth();
})

</script>

<style scoped>
.menu-link {
  text-decoration: none;
}
</style>