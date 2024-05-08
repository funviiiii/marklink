<script setup>
import {reactive, ref} from "vue";
import axios from "axios";

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

let announcements = reactive([{
  id: 1,
  title: '【公告】关于部门组织架构调整的通知',
  text: '尊敬的各位同事，根据公司业务发展需求，自即日起，我们将对部门组织架构进行调整。原市场一部与二部合并为市场部，负责人为王XX，请大家积极配合此次调整，共同为公司发展贡献力量。',
  creator: 'manager1',
  createTime: '2001.01.01',
}, {
  id: 2,
  title: '【重要通知】启用新办公自动化系统的通知',
  text: '各位同仁，为提高工作效率，公司决定自本月起启用新的办公自动化系统。请大家务必在下周内完成系统培训，并在日常工作中熟练运用，以便更好地服务于工作。',
  creator: 'manager1',
  createTime: '2001.01.01',
}, {
  id: 3,
  title: '【员工关怀】关于年度员工体检报名的通知',
  text: '关于年度员工体检通知：为关心员工身体健康，公司定于下个月开展年度员工体检活动。请各位同事在规定时间内携带身份证到人事部报名，并按照安排参加体检。请大家务必重视本次体检，确保自己的身体健康。',
  creator: 'manager1',
  createTime: '2001.01.01',
},]);

function createAnnouncement() {
  announcementForm.creatorToken = localStorage.getItem('token');
  // todo submit
}

function showAnnouncement(announcement) {
  announcementViewerForm.title = announcement.title
  announcementViewerForm.text = announcement.text
  announcementViewerForm.creator = announcement.creator
  announcementViewerForm.createTime = announcement.createTime
  announcementViewer.value = true
}
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
</style>