<template>
  <el-space v-if="true" direction="vertical" alignment="start" :size="20">
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
      <el-upload action="http://localhost:8001/api/upload" :auto-upload="false" list-type="picture-card" :limit="1" :disabled="hasFile" :on-change="handleFileChange" ref="upload" accept="image/jpg, image/jpeg, image/png, image/gif">
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
      <el-button>重置</el-button>
    </el-space>
  </el-space>

  <el-dialog v-model="dialogVisible">
    <el-image :src="dialogImageUrl"></el-image>
  </el-dialog>
</template>

<script>
import StyledTitle from "@/components/StyledTitle";
import {Plus, ZoomIn, Delete} from "@element-plus/icons-vue"

export default {
  name: "NewsAddingPage",
  components: {
    StyledTitle,
    Plus,
    ZoomIn,
    Delete,
  },
  data() {
    return {
      topics: null,
      selected: '',
      title: 'test',
      author: 'admin',
      summary: 'test',
      content: 'test',
      dialogImageUrl: '',
      dialogVisible: false,
      picFile: null,
    }
  },
  methods: {
    handlePicPreview(file) {
      this.dialogImageUrl = file.url
      this.dialogVisible = true
    },
    handleRemove(file) {
      console.log(file)
    },
    handleFileChange(uploadFile, uploadFiles) {
      console.log(uploadFile)
      console.log(uploadFiles)
      this.picFile = uploadFile.raw
    },
    async submitNews() {
      console.log(this.picFile)
/*
      let formData = new FormData()
      formData.append('file', this.picFile)
      await this.$http({
        url: '/api/upload',
        method: 'post',
        headers: {
          'Content-type': 'multipart/form-data',
        },
        data: formData,
      }).then((res)=>{
        console.log(res)
        this.$http({
          url: '/api/news/append',
          data: {
            nid: null,
            ntid: this.selected,
            ntitle: this.title,
            nauthor: this.author,
            ncreatedate: new Date(),
            npic: null,
            ncontent: this.content,
            nmodifydate: null,
            nsummary: this.summary
          },
          method: 'post'
        }).then((res)=>{
          console.log(res)
        }).catch((err)=>{
          console.log(err)
        })
      }).catch((err)=>{
        console.log(err)
      })
*/
      let formData = new FormData()
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
      formData.append('news', JSON.stringify(news))
      formData.append('file', this.picFile)
      await this.$http({
        url: '/api/news/append',
        data: formData,
        method: 'post',
        // headers: {
        //   'Content-type': 'multipart/form-data',
        // },
      }).then((res)=>{
        console.log(res)
      }).catch((err)=>{
        console.log(err)
      })
    }
  },
  computed: {
    isDataReady() {
      return this.topics != null
    },
    hasFile() {
      return this.picFile != null
    }
  },
  async created() {
    await this.$http.get('/api/topic').then((res)=>{
      this.topics = res.data.data
      this.selected = this.topics[0].tid
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