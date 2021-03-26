<template>
  <div>
    <myheader></myheader>
    <el-main>
      <sidebar></sidebar>
      <mainheader></mainheader>
    </el-main>
  </div>
</template>

<script>
import Myheader from "../components/myheader";
import Sidebar from "../components/sidebar";
import Mainheader from "../components/mainheader";
export default {
  name: "keywordMap",
  components: {Mainheader, Sidebar, Myheader}
}
</script>

<style scoped>
.el-main {
  background-color: #e9eef3;
  color: #333;
  text-align: center;
  line-height: 160px;
  height: 900px !important;
  margin: 0 !important;
  padding: 0 !important;
}
</style>
