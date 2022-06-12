<template>
  <div class="container">
    <div class="left-background"></div>
    <div class="right-background"></div>
    <span class="title">{{title}}</span>
  </div>
</template>

<script>
export default {
  name: "StyledTitle",
  props: {
    title: String,
  },
}
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: row;
}

.left-background {
  width: 8px;
  height: 35px;
  background-color: #024679;
}

.right-background {
  width: 750px;
  height: 35px;
  background-color: #E5EAF3;
}

.title {
  position: absolute;
  margin-left: 15px;
  margin-top: 5px;
  font-weight: bold;
  font-size: 18px;
}
</style>