<template>
  <div v-if="isDataReady">
    <el-space v-if="!isLogin">
      <span>账户名</span>
      <el-input v-model="username"></el-input>
      <span>密码</span>
      <el-input v-model="password"></el-input>
      <el-button @click="login">登录</el-button>
    </el-space>
    <el-space v-else :size="100">
      <span>欢迎您，{{getUserName}}</span>
      <el-button @click="gotoCtlPanel">控制面板</el-button>
      <el-button @click="logout">登出</el-button>
    </el-space>
    <el-space :size="50" class="banner-area">
      <el-image :src="require('@/assets/logo.jpg')" @click="backHome" class="logo"></el-image>
      <el-image :src="require('@/assets/a_b01.gif')"></el-image>
    </el-space>
    <el-space alignment="start" :size="10">
      <el-space direction="vertical">
        <el-card class="side-bar">
          <template #header>
            <span>国内新闻</span>
          </template>
          <el-space direction="vertical" alignment="start" style="width: 250px">
            <span v-for="i in nationalNews" :key="i" class="title" @click="gotoNews(i.nid)">{{textFormatting(i.ntitle)}}</span>
          </el-space>
        </el-card>
        <el-card class="side-bar">
          <template #header>
            <span>国际新闻</span>
          </template>
          <el-space direction="vertical" alignment="start" style="width: 250px">
            <span v-for="i in internationalNews" :key="i" class="title" @click="gotoNews(i.nid)">{{textFormatting(i.ntitle)}}</span>
          </el-space>
        </el-card>
      </el-space>
      <div style="width: 855px">
        <router-view></router-view>
      </div>
    </el-space>
  </div>
</template>

<script>
export default {
  name: "FrontPage",
  data() {
    return {
      username: '',
      password: '',
      nationalNews: null,
      internationalNews: null,
    }
  },
  methods: {
    async login() {
      await this.$http({
        url: '/user/login',
        method: 'post',
        params: {
          username: this.username,
          password: this.password,
        }
      }).then((res)=>{
        const user = res.data.data
        this.$store.commit('login', user)
      }).catch((err)=>{
        console.log(err)
      }).finally(()=>{
        this.username = ''
        this.password = ''
      })
    },
    logout() {
      this.$store.commit('logout')
    },
    gotoCtlPanel() {
      this.$router.push('/space')
    },
    gotoNews(id) {
      this.$router.push('/home/news/' + id)
    },
    textFormatting(str) {
      return str.length > 9 ? str.substr(0, 12) + '...' : str
    },
    backHome() {
      this.$router.push('/home')
    }
  },
  computed: {
    isLogin() {
      return this.$store.state.id != null
    },
    getUserName() {
      return this.$store.state.name
    },
    isDataReady() {
      return this.nationalNews != null && this.internationalNews != null
    },
  },
  async created() {
    await this.$http.get('/api/title/1').then((res)=>{
      this.nationalNews = res.data.data
    }).catch((err)=>{
      console.log(err)
    })
    await this.$http.get('/api/title/2').then((res)=>{
      this.internationalNews = res.data.data
    }).catch((err)=>{
      console.log(err)
    })
  }
}
</script>

<style scoped>
.side-bar {
  width: 250px;
}

.title:hover {
  color: #409EFF;
  font-weight: bold;
  cursor: pointer;
}

.logo {
  cursor: pointer;
}

.banner-area {
  margin-bottom: 10px;
  margin-top: 10px;
}
</style>