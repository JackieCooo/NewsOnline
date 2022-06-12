<template>
  <el-space direction="vertical" alignment="start">
    <StyledTitle title="添加主题"></StyledTitle>
    <el-space class="content">
      <span>主题名称</span>
      <el-input :model-value="topic"></el-input>
    </el-space>
    <el-space>
      <el-button>添加</el-button>
      <el-button>重置</el-button>
    </el-space>
  </el-space>
</template>

<script>
import StyledTitle from "@/components/StyledTitle";

export default {
  name: "TopicAddingPage",
  components: {
    StyledTitle,
  },
  data() {
    return {
      topic: '',
    }
  },
  methods: {
    async addTopic() {

    }
  },
}
</script>

<style scoped>
.content {
  margin-top: 20px;
}
</style>