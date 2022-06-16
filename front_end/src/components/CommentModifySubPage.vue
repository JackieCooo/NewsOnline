<template>
  <el-space v-if="isDataReady" direction="vertical" class="container">
    <el-table :data="newsComments" style="width: 100%" ref="table">
      <el-table-column type="selection"></el-table-column>
      <el-table-column label="内容" :width="300">
        <template #default="scope">{{scope.row.ccontent}}</template>
      </el-table-column>
      <el-table-column label="作者" :width="150">
        <template #default="scope">{{scope.row.cauthor}}</template>
      </el-table-column>
      <el-table-column #default="scope">
        <el-button circle type="danger" :icon="Delete" @click="handleSingleDelete(scope.row.cid)"></el-button>
      </el-table-column>
    </el-table>
    <el-button v-if="hasComment" @click="handleMultipleDelete">删除所选评论</el-button>
  </el-space>
</template>

<script setup>
import {Delete} from "@element-plus/icons-vue"
</script>

<script>
import {ElMessage} from 'element-plus'

export default {
  name: "CommentModifySubPage",
  data() {
    return {
      newsComments: null,
    }
  },
  methods: {
    handleSingleDelete(id) {
      // console.log(id)
      let deleteList = []
      deleteList.push(id)
      this.handleDelete(deleteList)
    },
    handleMultipleDelete() {
      const selectedRows = this.$refs.table.getSelectionRows()
      // console.log(selectedRows)
      let deleteList = []
      for (const i of selectedRows) {
        // console.log(i.cid)
        deleteList.push(i.cid)
      }
      console.log(deleteList)
      this.handleDelete(deleteList)
    },
    async handleDelete(list) {
      let formData = new FormData()
      formData.append('list', JSON.stringify(list))
      await this.$http({
        url: '/api/comment/delete',
        method: 'post',
        data: formData,
      }).then((res)=>{
        console.log(res)
        this.newsComments = this.newsComments.filter(item => !list.includes(item.cid))
        ElMessage({
          message: '评论已删除',
          type: 'success'
        })
      }).catch((err)=>{
        ElMessage({
          message: '评论删除失败',
          type: 'error'
        })
        console.log(err)
      })
    },
  },
  computed: {
    isDataReady() {
      return this.newsComments != null
    },
    hasComment() {
      return this.newsComments.length !== 0
    }
  },
  async created() {
    await this.$http.get('/api/comment/' + this.$store.state.curModifyNewsId).then((res)=>{
      this.newsComments = res.data.data
      console.log(res.data.data)
    }).catch((err)=>{
      console.log(err)
    })
  }
}
</script>

<style scoped>
.container {
  width: 100%;
}
</style>