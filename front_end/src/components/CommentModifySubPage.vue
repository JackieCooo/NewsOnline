<template>
  <el-space v-if="isDataReady" direction="vertical" class="container">
    <el-table :data="newsComments" style="width: 100%">
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
    <el-button>删除所选评论</el-button>
  </el-space>
</template>

<script setup>
import {Delete} from "@element-plus/icons-vue"
</script>

<script>
export default {
  name: "CommentModifySubPage",
  data() {
    return {
      newsComments: null,
      deleteList: [],
    }
  },
  methods: {
    handleSingleDelete(id) {
      this.deleteList.push(id)
    },
    handleMutipleDelete() {

    },
  },
  computed: {
    isDataReady() {
      return this.newsComments != null
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