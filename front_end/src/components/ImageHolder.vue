<template>
  <el-space direction="vertical">
    <el-image :src="src"></el-image>
    <span>{{title}}</span>
  </el-space>
</template>

<script>
export default {
  name: "ImageHolder",
  props: {
    src: String,
    title: String,
  },
}
</script>

<style scoped>

</style>