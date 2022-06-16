<template>
  <el-space v-if="isDataReady" direction="vertical" alignment="start">
    <StyledTitle title="修改主题"></StyledTitle>
    <el-space direction="vertical" alignment="start">
      <el-row v-for="i in topicData" :key="i" class="topic-row" align="middle" justify="space-between" :gutter="10">
        <el-col :span="12" :push="1">{{i.tname}}</el-col>
        <el-col :span="6">
          <el-button type="primary" @click="showModifyDialog(i.tid, i.tname)">修改</el-button>
        </el-col>
        <el-col :span="6">
          <el-button type="danger" @click="handleDelete(i.tid)">删除</el-button>
        </el-col>
      </el-row>
    </el-space>
  </el-space>
</template>

<script>
import StyledTitle from "@/components/StyledTitle";
import {ElMessage} from 'element-plus'

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
    async showModifyDialog(id, name) {
      await this.$prompt('输入主题名', '主题修改', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        inputValue: name,
      }).then(({value})=>{
        const topic = {
          tid: id,
          tname: value,
        }
        this.$http({
          url: '/api/topic/modify',
          method: 'post',
          headers: {
            'Content-type': 'application/json',
          },
          data: JSON.stringify(topic),
        }).then((res)=>{
          console.log(res)
          for (let i of this.topicData) {
            if (i.tid === id) {
              i.tname = value
              break
            }
          }
          ElMessage({
            message: '修改成功',
            type: 'success'
          })
        }).catch((err)=>{
          console.log(err)
          ElMessage({
            message: '修改失败',
            type: 'error'
          })
        })
      }).catch((err)=>{
        console.log(err)
      })
    },
    async handleDelete(id) {
      await this.$http({
        url: '/api/topic/delete',
        method: 'post',
        params: {
          tid: id,
        }
      }).then((res)=>{
        console.log(res)
        this.topicData = this.topicData.filter(item => item.tid !== id)
        ElMessage({
          message: '删除成功',
          type: 'success'
        })
      }).catch((err)=>{
        console.log(err)
        ElMessage({
          message: '删除失败',
          type: 'error'
        })
      })
    },
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