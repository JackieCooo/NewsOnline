<template>
  <el-space direction="vertical" class="container" alignment="start">
    <el-menu mode="horizontal" class="menu" :ellipsis="false">
      <el-menu-item index="NewsModifySubPage" @click="changeSubPage">编辑新闻</el-menu-item>
      <el-menu-item index="CommentModifySubPage" @click="changeSubPage">编辑评论</el-menu-item>
    </el-menu>
    <keep-alive>
      <component v-bind:is="curSubPage"></component>
    </keep-alive>
  </el-space>
</template>

<script>
import NewsModifySubPage from "@/components/NewsModifySubPage";
import CommentModifySubPage from "@/components/CommentModifySubPage";

export default {
  name: "ModifyDialog",
  components: {
    NewsModifySubPage,
    CommentModifySubPage,
  },
  data() {
    return {
      curSubPage: 'NewsModifySubPage',
    }
  },
  methods: {
    changeSubPage(e) {
      this.curSubPage = e.index
    }
  }
}
</script>

<style scoped>
.menu {
  height: 40px;
  margin-bottom: 20px;
}

.container {
  width: 90%;
}
</style>