<template>
  <el-space v-if="true" direction="vertical" alignment="start" :size="20">
    <el-space class="content">
      <span>主题</span>
      <el-select v-model="selected">
        <el-option v-for="i in topics" :key="i" :value="i.tid" :label="i.tname"></el-option>
      </el-select>
    </el-space>
    <el-space>
      <span>标题</span>
      <el-input v-model="title" class="input-width"></el-input>
    </el-space>
    <el-space>
      <span>作者</span>
      <el-input v-model="author" class="input-width"></el-input>
    </el-space>
    <el-space>
      <span>摘要</span>
      <el-input v-model="summary" type="textarea" :rows="5" class="input-width"></el-input>
    </el-space>
    <el-space>
      <span>内容</span>
      <el-input v-model="content" type="textarea" :rows="10" class="input-width"></el-input>
    </el-space>
    <el-space>
      <span>上传图片</span>
      <el-upload action="#" :auto-upload="false" list-type="picture-card" :limit="1" :disabled="hasFile" :on-change="handleFileChange" ref="upload" accept="image/jpg, image/jpeg, image/png, image/gif" :on-remove="onRemove">
        <el-icon><Plus /></el-icon>

        <template #file="{file}">
          <div>
            <el-image class="el-upload-list__item-thumbnail" :src="file.url"></el-image>
            <span class="el-upload-list__item-actions">
              <span class="el-upload-list__item-preview" @click="handlePicPreview(file)">
                <el-icon><ZoomIn /></el-icon>
              </span>
              <span v-if="true" class="el-upload-list__item-delete" @click="handleRemove(file)">
                <el-icon><Delete /></el-icon>
              </span>
            </span>
          </div>
        </template>
      </el-upload>
    </el-space>
    <el-space>
      <el-button @click="submitNews">提交</el-button>
      <el-button @click="resetAll">重置</el-button>
    </el-space>
  </el-space>

  <el-dialog v-model="dialogVisible">
    <el-image :src="dialogImageUrl"></el-image>
  </el-dialog>
</template>

<script>
import {Plus, ZoomIn, Delete} from "@element-plus/icons-vue"
import {ElMessage, ElMessageBox} from "element-plus"

export default {
  name: "NewsModifySubPage",
  components: {
    Plus,
    ZoomIn,
    Delete,
  },
  data() {
    return {
      topics: null,
      selected: '',
      title: '',
      author: '',
      summary: '',
      content: '',
      dialogImageUrl: '',
      dialogVisible: false,
      picFile: null,
    }
  },
  methods: {
    // 打开图片预览
    handlePicPreview(file) {
      this.dialogImageUrl = file.url
      this.dialogVisible = true
    },
    // 处理图片删除
    handleRemove(file) {
      // console.log(file)
      this.$refs.upload.handleRemove(file)
    },
    // 更新图片文件变量状态
    onRemove(file, fileList) {
      console.log(file, fileList)
      this.picFile = null
    },
    // 处理文件列表更新
    handleFileChange(uploadFile, uploadFiles) {
      console.log(uploadFile, uploadFiles)
      this.picFile = uploadFile.raw
    },
    // 清空所有选项
    resetAll() {
      this.$refs.upload.clearFiles()
      this.selected = this.topics[0].tid
      this.title = ''
      this.author = ''
      this.summary = ''
      this.content = ''
    },
    // 提交新闻
    async submitNews() {
      // console.log(this.picFile)
      if (this.title === '' || this.author === '' || this.summary === '' || this.content === '') {
        await ElMessageBox("标题、作者、摘要、内容为必填")
        return;
      }
      // let formData = new FormData()
      const news = {
        nid: null,
        ntid: this.selected,
        ntitle: this.title,
        nauthor: this.author,
        ncreatedate: new Date(),
        npic: null,
        ncontent: this.content,
        nmodifydate: null,
        nsummary: this.summary
      }
      // formData.append('news', JSON.stringify(news))
      // if(this.picFile != null) formData.append('file', this.picFile)
      await this.$http({
        url: '/api/news/modify',
        data: JSON.stringify(news),
        method: 'post',
        headers: {
          'Content-type': 'application/json',
        },
      }).then((res)=>{
        console.log(res)
        ElMessage({
          message: '修改成功',
          type: 'success',
        })
      }).catch((err)=>{
        console.log(err)
        ElMessage({
          message: '修改失败',
          type: 'error',
        })
      })
    }
  },
  computed: {
    // 确认数据请求完全
    isDataReady() {
      return this.topics != null
    },
    // 检查是否有图片上传
    hasFile() {
      return this.picFile != null
    }
  },
  async created() {
    await this.$http.get('/api/topic').then((res)=>{
      this.topics = res.data.data
    }).catch((err)=>{
      console.log(err)
    })
    await this.$http.get('/api/news/' + this.$store.state.curModifyNewsId).then((res)=>{
      const newsData = res.data.data
      this.selected = newsData.ntid
      this.title = newsData.ntitle
      this.author = newsData.nauthor
      this.summary = newsData.nsummary
      this.content = newsData.ncontent
      // this.picFile = newsData.npic
    }).catch((err)=>{
      console.log(err)
    })
  }
}

</script>

<style scoped>
.input-width {
  width: 500px;
}
</style>