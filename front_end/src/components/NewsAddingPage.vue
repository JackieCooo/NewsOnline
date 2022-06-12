<template>
  <el-space v-if="isDataReady" direction="vertical" alignment="start" :size="20">
    <StyledTitle title="添加新闻"></StyledTitle>
    <el-space class="content">
      <span>主题</span>
      <el-select :model-value="selected">
        <el-option v-for="i in topics" :key="i" :value="i.tid" :label="i.tname"></el-option>
      </el-select>
    </el-space>
    <el-space>
      <span>标题</span>
      <el-input :model-value="title" class="input-width"></el-input>
    </el-space>
    <el-space>
      <span>作者</span>
      <el-input :model-value="author" class="input-width"></el-input>
    </el-space>
    <el-space>
      <span>摘要</span>
      <el-input :model-value="summary" type="textarea" :rows="5" class="input-width"></el-input>
    </el-space>
    <el-space>
      <span>内容</span>
      <el-input :model-value="content" type="textarea" :rows="10" class="input-width"></el-input>
    </el-space>
    <el-space>
      <span>上传图片</span>
      <el-button>选择文件</el-button>
    </el-space>
    <el-space>
      <el-button>提交</el-button>
      <el-button>重置</el-button>
    </el-space>
  </el-space>
</template>

<script>
import StyledTitle from "@/components/StyledTitle";

export default {
  name: "NewsAddingPage",
  components: {
    StyledTitle,
  },
  data() {
    return {
      topics: null,
      selected: '',
      title: '',
      author: '',
      summary: '',
      content: '',
    }
  },
  methods: {

  },
  computed: {
    isDataReady() {
      return this.topics != null
    }
  },
  async created() {
    await this.$http.get('/api/topic').then((res)=>{
      this.topics = res.data.data
      this.selected = this.topics[0].tname
    }).catch((err)=>{
      console.log(err)
    })
  }
}
</script>

<style scoped>
.content {
  margin-top: 20px;
}

.input-width {
  width: 500px;
}

</style>