<template>
  <div v-if="topics != null && newsTitles != null">
    <el-space direction="vertical" alignment="start">
      <div class="top-bar">
        <el-image :src="require('@/assets/blue_bar.png')"></el-image>
        <span class="left-title">新闻中心</span>
        <span class="right-title">分类新闻</span>
      </div>
      <el-space alignment="start" :size="10">
        <el-space direction="vertical" class="content-area">
          <el-space class="navi-bar" :wrap="true" style="margin-bottom: 10px;">
            <el-button text type="danger" v-for="i in topics" :key="i" @click="gotoCate(i)" class="navi-item">{{i.tname}}</el-button>
          </el-space>
          <el-space direction="vertical" alignment="start" class="content-area">
            <div v-for="i in newsTitles.news" :key="i" class="flex-box">
              <span @click="gotoNews(i.nid)" class="title">{{i.ntitle}}</span>
              <span>{{i.ncreatedate}}</span>
            </div>
          </el-space>
          <el-pagination background layout="prev, pager, next" :total="newsTitles.cnt" :default-page-size="15" @current-change="pageChange"></el-pagination>
        </el-space>
        <el-space direction="vertical">
          <ImageHolder :src="require('@/assets/Picture1.jpg')" title="幻想中穿越时空"></ImageHolder>
          <ImageHolder :src="require('@/assets/Picture2.jpg')" title="幻想中穿越时空"></ImageHolder>
          <ImageHolder :src="require('@/assets/Picture3.jpg')" title="幻想中穿越时空"></ImageHolder>
          <ImageHolder :src="require('@/assets/Picture4.jpg')" title="幻想中穿越时空"></ImageHolder>
        </el-space>
      </el-space>
    </el-space>
  </div>
</template>

<script>
import ImageHolder from "@/components/ImageHolder";

export default {
  name: "NewsCenter",
  components: {
    ImageHolder,
  },
  data() {
    return {
      topics: null,
      newsTitles: null,
    }
  },
  async created() {
    await this.$http.get('/api/topic').then((res)=>{
      this.topics = res.data.data
    }).catch((err)=>{
      console.log(err)
    })
    await this.$http.get('/api/title?page=1').then((res)=>{
      this.newsTitles = res.data.data
    }).catch((err)=>{
      console.log(err)
    })
  },
  methods: {
    gotoCate(name) {
      console.log(name)
    },
    async pageChange(val) {
      await this.$http.get('/api/title?page=' + val).then((res)=>{
        this.newsTitles = res.data.data
      }).catch((err)=>{
        console.log(err)
      })
    },
    gotoNews(id) {
      this.$router.push('/home/news/' + id)
    }
  },
}
</script>

<style scoped>
.content-area {
  width: 600px;
}

.top-bar {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
}

.flex-box {
  width: 600px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.navi-item {
  font-weight: bold;
}

.left-title {
  position: absolute;
  font-size: 20px;
  font-weight: bold;
  margin-left: 30px;
  color: red;
}

.right-title {
  position: absolute;
  margin-left: 700px;
  margin-top: 3px;
  color: white;
}

.title:hover {
  color: #409EFF;
  font-weight: bold;
  cursor: pointer;
}
</style>