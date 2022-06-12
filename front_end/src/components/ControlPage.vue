<template>
  <el-space direction="vertical" alignment="start" class="container">
    <span class="top-title">欢迎使用新闻管理系统</span>
    <el-space class="border-box">
      <el-image :src="require('@/assets/logo.jpg')" @click="backHome" class="logo"></el-image>
      <el-image :src="require('@/assets/a_b01.gif')"></el-image>
    </el-space>
    <el-space class="user-area">
      <span>欢迎您，{{getUserName}}</span>
      <el-button @click="logout">登出</el-button>
    </el-space>
    <el-space alignment="start" :size="30">
      <el-menu class="side-menu">
        <el-menu-item index="add_news" @click="gotoPage">添加新闻</el-menu-item>
        <el-menu-item index="modify_news" @click="gotoPage">编辑新闻</el-menu-item>
        <el-menu-item index="add_topic" @click="gotoPage">添加主题</el-menu-item>
        <el-menu-item index="modify_topic" @click="gotoPage">编辑主题</el-menu-item>
      </el-menu>
      <router-view></router-view>
    </el-space>
  </el-space>
</template>

<script>
export default {
  name: "ControlPage",
  data() {
    return {}
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
      }).then((res) => {
        const user = res.data.data
        this.$store.commit('login', user)
      }).catch((err) => {
        console.log(err)
      }).finally(() => {
        this.username = ''
        this.password = ''
      })
    },
    logout() {
      this.$store.commit('logout')
    },
    backHome() {
      this.$router.push('/home')
    },
    gotoPage(e) {
      this.$router.push('/space/' + e.index)
    }
  },
  computed: {
    getUserName() {
      return this.$store.state.name
    },
    isDataReady() {
      return this.nationalNews != null && this.internationalNews != null
    },
  }
}
</script>

<style scoped>
.border-box {
  border: 1px solid #8D9095;
}

.logo {
  margin: 10px 10px 10px 10px;
}

.logo:hover {
  cursor: pointer;
}

.top-title {
  background-color: #E5EAF3;
  padding: 5px 5px 5px 5px;
  width: 950px;
  text-align: left;
}

.user-area {
  margin-top: 10px;
  margin-bottom: 20px;
}

.side-menu {
  height: 500px;
  width: 150px;

}

</style>