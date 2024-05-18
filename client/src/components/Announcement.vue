<script setup>
import {onMounted, reactive, ref} from "vue";
import {ElMessage} from "element-plus";
import axios from "../utils/axios.js";
import dayjs from "dayjs";

const right = JSON.parse(sessionStorage.getItem('role'))['rid'] === 'rid_manager' || JSON.parse(sessionStorage.getItem('role'))['rid'] === 'rid_super';

let announcementCreator = ref(false)
let announcementViewer = ref(false)

let announcementForm = reactive({
  title: '',
  text: '',
  creatorToken: '',
})
let announcementViewerForm = reactive({
  title: '',
  text: '',
  creator: '',
  createTime: ''
})

let announcements = reactive([]);

function createAnnouncement() {
  // 创建公告
  announcementForm.creatorToken = localStorage.getItem('token');
  axios({
    url: '/announcement',
    method: 'POST',
    data: announcementForm
  }).then(res => {
    ElMessage.success(res.data["msg"]);
    fetchInfo()
    announcementCreator.value = false
  }).catch(res => {
    ElMessage.warning(res.response.data["msg"]);
  })
}

function showAnnouncement(announcement) {
  announcementViewerForm.title = announcement.title
  announcementViewerForm.text = announcement.text
  announcementViewerForm.creator = announcement.creator
  announcementViewerForm.createTime = announcement.createTime
  announcementViewer.value = true
}

function fetchInfo() {
  // 获取公告
  axios({
    url: '/announcement?token=' + localStorage.getItem('token'),
    method: 'GET'
  }).then(res => {
    announcements.length = 0
    // 载入数据
    res.data['content'].forEach((item) => {
      item.createTime = dayjs(item.createTime).format('YYYY-MM-DD HH:mm:ss')
      announcements.push(item)
    })
    announcements.reverse()
  }).catch(res => {
    ElMessage.warning(res.response.data["msg"]);
  })
}

onMounted(() => {
  fetchInfo()
})
</script>

<template>
  <el-container>
    <el-header class="header">
      <div :hidden="!right">
        <el-button size="large" class="submit-btn" @click="announcementCreator = true">发布新公告</el-button>
      </div>
      <div><h1 class="title">公告栏</h1></div>
    </el-header>
    <el-container>
      <el-main>
        <div v-for="announcement in announcements">
          <div class="announcement-box" @click="showAnnouncement(announcement)">
            <el-container>
              <el-header>
                <h2>{{ announcement.title }}</h2>
              </el-header>
              <el-container>
                <el-main>{{ announcement.text.substring(0, 20) }} ...</el-main>
                <el-aside class="announcement-info">
                  <div>
                    发布者： {{ announcement.creator }}
                  </div>
                  <div>
                    发布时间: {{ announcement.createTime }}
                  </div>
                </el-aside>
              </el-container>
            </el-container>
          </div>
        </div>
      </el-main>
      <el-aside>
      </el-aside>
    </el-container>
  </el-container>
  <!-- announcementCreator -->
  <el-dialog v-model="announcementCreator">
    <el-container>
      <el-main>
        <h2 class="announcement-title">发布公告</h2>
        <el-form label-width="100">
          <el-form-item label="标题">
            <el-input v-model="announcementForm.title"></el-input>
          </el-form-item>
          <el-form-item label="内容">
            <el-input
                type="textarea"
                rows="5"
                v-model="announcementForm.text"
                resize="none"
            ></el-input>
          </el-form-item>
          <el-form-item
          >
            <el-container>
              <el-aside></el-aside>
              <el-main>
                <el-button @click="createAnnouncement">提交</el-button>
              </el-main>
              <el-aside></el-aside>
            </el-container>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </el-dialog>
  <!-- announcementViewer -->
  <el-dialog v-model="announcementViewer">
    <el-container>
      <el-header>
        <el-container>
          <el-main>
            <h2>{{ announcementViewerForm.title }}</h2>
          </el-main>
          <el-aside>
            <el-container>
              <el-main></el-main>
              <el-footer>
                <p>作者：{{ announcementViewerForm.creator }}<br>发布时间：{{ announcementViewerForm.createTime }}</p>
              </el-footer>
            </el-container>
          </el-aside>
        </el-container>
      </el-header>
      <div class="announcement-separator"></div>
      <el-divider/>
      <el-main>
        <p>{{ announcementViewerForm.text }}</p>
      </el-main>
      <el-divider/>
    </el-container>
  </el-dialog>
</template>

<style scoped>
.header {
  margin: 20px;
}

.submit-btn {
  border: solid 1px lightgrey;
}

.submit-btn:hover {
  background-color: #f5f7fa;
  color: #353535;
  border: solid 1px lightgrey;
}

.tool-bar > h2 {
  text-align: center;
}

.tool-bar div {
  text-align: center;
  font-size: larger;
}

.announcement-box {
  transition: background-color 0.3s;
}

.announcement-box:hover {
  background: #ecf5ff;
  cursor: pointer;
}

.announcement-info > div {
  margin: 10px;
}

.announcement-title {
  text-align: center;
}

.announcement-separator {
  margin: 10px;
}

.alter-text p {
  color: black;
  transition: color 0.2s;
}

.alter-text p:hover {
  color: #47a4c4;
  cursor: pointer;
}
</style>