<template>
  <el-space direction="vertical" alignment="start">
    <StyledTitle title="添加主题"></StyledTitle>
    <el-space class="content">
      <span>主题名称</span>
      <el-input v-model="topic"></el-input>
    </el-space>
    <el-space>
      <el-button @click="addTopic">添加</el-button>
      <el-button @click="resetAll">重置</el-button>
    </el-space>
  </el-space>
</template>

<script>
import StyledTitle from "@/components/StyledTitle";
import {ElMessage} from 'element-plus'

export default {
  name: "TopicAddingPage",
  components: {
    StyledTitle,
  },
  data() {
    return {
      topic: '',
    }
  },
  methods: {
    async addTopic() {
      await this.$http({
        url: '/api/topic/append',
        method: 'post',
        params: {
          name: this.topic
        }
      }).then((res)=>{
        this.resetAll()
        ElMessage({
          message: '主题已添加',
          type: 'success'
        })
        console.log(res)
      }).catch((err)=>{
        ElMessage({
          message: '主题添加失败',
          type: 'error'
        })
        console.log(err)
      })
    },
    resetAll() {
      this.topic = ''
    }
  },
}
</script>

<style scoped>
.content {
  margin-top: 20px;
}
</style>