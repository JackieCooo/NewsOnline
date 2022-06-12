<template>
  <el-space v-if="isDataReady" direction="vertical">
    <StyledTitle title="编辑新闻"></StyledTitle>
    <el-space direction="vertical" alignment="start">
      <el-row v-for="i in newsData.news" :key="i" class="news-row" align="middle" justify="space-between" :gutter="10">
        <el-col :span="16">{{i.ntitle}}</el-col>
        <el-col :span="4">作者：{{i.nauthor}}</el-col>
        <el-col :span="2">
          <el-button type="primary">修改</el-button>
        </el-col>
        <el-col :span="2">
          <el-button type="danger">删除</el-button>
        </el-col>
      </el-row>
    </el-space>
    <el-pagination background layout="prev, pager, next" :total="newsData.cnt" :default-page-size="15" @current-change="pageChange"></el-pagination>
  </el-space>
</template>

<script>
import StyledTitle from "@/components/StyledTitle";

export default {
  name: "NewsModifyPage",
  components: {
    StyledTitle,
  },
  data() {
    return {
      newsData: null,
    }
  },
  methods: {
    async pageChange(val) {
      await this.$http.get('/api/title?page=' + val).then((res)=>{
        this.newsData = res.data.data
      }).catch((err)=>{
        console.log(err)
      })
    },
  },
  computed: {
    isDataReady() {
      return this.newsData != null
    }
  },
  async created() {
    await this.$http.get('/api/title?page=1').then((res)=>{
      console.log(res)
      this.newsData = res.data.data
    }).catch((err)=>{
      console.log(err)
    })
  },
}
</script>

<style scoped>
.news-row {
  width: 750px;
  text-align: left;
}
</style>