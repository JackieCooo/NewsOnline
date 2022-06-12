<template>
  <el-space v-if="isDataReady" direction="vertical" alignment="start">
    <h2>{{newsData.news.ntitle}}</h2>
    <hr width="855" color="#909399" size="1">
    <div class="flex-box">
      <span>作者：{{newsData.news.nauthor}}</span>
      <span>类型：{{newsData.topic.tname}}</span>
      <span>发布时间：{{newsData.news.ncreatedate}}</span>
    </div>
    <span style="font-weight: bold">摘要：{{newsData.news.nsummary}}</span>
    <p>{{newsData.news.ncontent}}</p>
    <hr width="855" color="#909399" size="1">
    <el-space v-for="i in newsData.comments" :key="i" direction="vertical" alignment="start">
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
      <el-input disabled v-model="username"></el-input>
      <span>IP：</span>
      <el-input disabled v-model="ip"></el-input>
    </el-space>
    <el-input type="textarea" :rows="10" v-model="comment" style="width: 800px" :disabled="!isLogin"></el-input>
    <el-button :disabled="!isLogin">评论</el-button>
  </el-space>
</template>

<script>
export default {
  name: "NewsPage",
  data() {
    return {
      newsData: null,
      username: '',
      ip: '',
      comment: '',
      newsId: this.$route.params.id
    }
  },
  computed: {
    isDataReady() {
      return this.newsData != null
    },
    isLogin() {
      return this.$store.state.id != null
    }
  },
  async created() {
    await this.$http.get('/api/news/' + this.newsId).then((res)=>{
      this.newsData = res.data.data
    }).catch((err)=>{
      console.log(err)
    })
  },
  watch: {
    async newsId(val) {
      console.log(val)
      this.newsData = null
      await this.$http.get('/api/news/' + this.newsId).then((res)=>{
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