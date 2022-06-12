<template>
  <el-space v-if="isDataReady" direction="vertical" alignment="start">
    <StyledTitle title="修改主题"></StyledTitle>
    <el-space direction="vertical" alignment="start">
      <el-row v-for="i in topicData" :key="i" class="topic-row" align="middle" justify="space-between" :gutter="10">
        <el-col :span="12" :push="1">{{i.tname}}</el-col>
        <el-col :span="6">
          <el-button type="primary">修改</el-button>
        </el-col>
        <el-col :span="6">
          <el-button type="danger">删除</el-button>
        </el-col>
      </el-row>
    </el-space>
  </el-space>
</template>

<script>
import StyledTitle from "@/components/StyledTitle";

export default {
  name: "TopicModifyPage",
  components: {
    StyledTitle,
  },
  data() {
    return {
      topicData: null,
    }
  },
  methods: {

  },
  computed: {
    isDataReady() {
      return this.topicData != null
    }
  },
  async created() {
    await this.$http.get('/api/topic').then((res)=>{
      this.topicData = res.data.data
    }).catch((err)=>{
      console.log(err)
    })
  }
}
</script>

<style scoped>
.topic-row {
  width: 300px;
  text-align: left;
}
</style>