<template>
  <el-space v-if="isDataReady" direction="vertical" alignment="start">
    <h2>{{newsData.ntitle}}</h2>
    <hr width="855" color="#909399" size="1">
    <div class="flex-box">
      <span>作者：{{newsData.nauthor}}</span>
      <span>类型：{{newsTopic[0].tname}}</span>
      <span>发布时间：{{newsData.ncreatedate}}</span>
    </div>
    <span style="font-weight: bold">摘要：{{newsData.nsummary}}</span>
    <p>{{newsData.ncontent}}</p>
    <hr width="855" color="#909399" size="1">
    <el-space v-for="i in newsComments" :key="i" direction="vertical" alignment="start">
      <div class="flex-box">
        <span>留言人：{{i.cauthor}}</span>
        <span>IP：{{i.cip}}</span>
        <span>留言时间：{{i.cdate}}</span>
      </div>
      <span>{{i.ccontent}}</span>
    </el-space>
    <hr width="855" color="#909399" size="1">
    <h3>评论</h3>
    <el-space>
      <span>用户名：</span>
      <el-input disabled v-model="getUsername"></el-input>
      <span>IP：</span>
      <el-input disabled v-model="ip"></el-input>
    </el-space>
    <el-input type="textarea" :rows="10" v-model="comment" style="width: 800px" :disabled="!isLogin"></el-input>
    <el-button :disabled="!isLogin" @click="handleCommentSubmit">评论</el-button>
  </el-space>
</template>

<script>
import {ElMessage} from 'element-plus'

export default {
  name: "NewsPage",
  data() {
    return {
      newsData: null,
      newsTopic: null,
      newsComments: null,
      ip: '127.0.0.1',
      comment: '',
      newsId: this.$route.params.id
    }
  },
  computed: {
    isDataReady() {
      return this.newsData != null && this.newsTopic != null && this.newsComments != null
    },
    isLogin() {
      return this.$store.state.id != null
    },
    getUsername() {
      return this.$store.state.name
    }
  },
  methods: {
    async handleCommentSubmit() {
      let comment = {
        cid: null,
        cnid: this.newsId,
        ccontent: this.comment,
        cdate: new Date(),
        cip: this.ip,
        cauthor: this.$store.state.name,
      }
      await this.$http({
        url: '/api/comment/append',
        method: 'post',
        headers: {
          'Content-type': 'application/json',
        },
        data: JSON.stringify(comment)
      }).then((res)=>{
        console.log(res)
        this.comment = ''
        this.newsComments.push(comment)
        ElMessage({
          message: '评论成功',
          type: 'success',
        })
      }).catch((err)=>{
        console.log(err)
        ElMessage({
          message: '评论失败',
          type: 'error',
        })
      })
    }
  },
  async created() {
    await this.$http.get('/api/news/' + this.newsId).then((res)=>{
      this.newsData = res.data.data
      this.$http.get('/api/topic/?id=' + this.newsData.ntid).then((res)=>{
        this.newsTopic = res.data.data
      })
      this.$http.get('/api/comment/' + this.newsId).then((res)=>{
        this.newsComments = res.data.data
      })
    }).catch((err)=>{
      console.log(err)
    })
  },
  watch: {
    async '$route.params.id'(val) {
      console.log(val)
      this.newsData = null
      await this.$http.get('/api/news/' + this.newsId).then((res)=>{
        location.reload()
        this.newsData = res.data.data
      }).catch((err)=>{
        console.log(err)
      })
    },
  },
}
</script>

<style scoped>
.flex-box {
  width: 855px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
</style>