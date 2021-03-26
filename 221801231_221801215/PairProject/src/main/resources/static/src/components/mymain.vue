<template>
<div>
 <el-main>

   <slot></slot>
 </el-main>
</div>
</template>
<script>
export default {
 name:'mymain'
}
</script>
<style scoped>
.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  line-height: 160px;
  margin: 0;
  padding: 0;
  border: 0;
}
.pageimage {
  margin-top: 0px;
  height: 900px;
  width: 50%;
  float: left;
  position: relative;
  left: 0px;
}
</style>
