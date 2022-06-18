<template>
  <el-space v-if="isDataReady" direction="vertical">
    <StyledTitle title="编辑新闻"></StyledTitle>
    <el-space direction="vertical" alignment="start">
      <el-row v-for="i in newsData.news" :key="i" class="news-row" align="middle" justify="space-between" :gutter="10">
        <el-col :span="16">{{i.ntitle}}</el-col>
        <el-col :span="4">作者：{{i.nauthor}}</el-col>
        <el-col :span="2">
          <el-button type="primary" @click="handleModifyBtnPush(i.nid)">修改</el-button>
        </el-col>
        <el-col :span="2">
          <el-button type="danger" @click="handleDelete(i.nid)">删除</el-button>
        </el-col>
      </el-row>
    </el-space>
    <el-pagination background layout="prev, pager, next" :total="newsData.cnt" :default-page-size="15" @current-change="pageChange"></el-pagination>
  </el-space>

  <el-dialog v-model="dialogVisible" class="dialog" :destroy-on-close="true">
    <ModifyDialog></ModifyDialog>
  </el-dialog>
</template>

<script>
import StyledTitle from "@/components/StyledTitle";
import ModifyDialog from "@/components/NewsModifyDialog";
import {ElMessage} from 'element-plus'

export default {
  name: "NewsModifyPage",
  components: {
    StyledTitle,
    ModifyDialog,
  },
  data() {
    return {
      newsData: null,
      dialogVisible: false,
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
    handleModifyBtnPush(id) {
      // console.log(id)
      this.$store.commit("modifyNews", id)
      this.dialogVisible = true
    },
    async handleDelete(id) {
      await this.$http({
        url: '/api/news/delete',
        method: 'post',
        params: {
          nid: id,
        }
      }).then((res)=>{
        console.log(res)
        this.newsData.news = this.newsData.news.filter(item => item.nid !== id)
        ElMessage({
          message: '删除成功',
          type: 'success',
        })
      }).catch((err)=>{
        console.log(err)
        ElMessage({
          message: '删除失败',
          type: 'error',
        })
      })
    }
  },
  computed: {
    isDataReady() {
      return this.newsData != null
    }
  },
  async created() {
    await this.$http.get('/api/title?page=1').then((res)=>{
      // console.log(res)
      this.newsData = res.data.data
      console.log(this.newsData)
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