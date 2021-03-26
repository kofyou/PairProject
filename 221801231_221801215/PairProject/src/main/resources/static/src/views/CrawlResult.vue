<template>
  <div>
  <myheader></myheader>
  <el-main>
    <sidebar></sidebar>
    <mainheader></mainheader>
    <ul class="mainpaperlist">
      <li v-for="item in paperDetailList" >
        <el-card shadow="hover" class="papercard">
          {{item}}
        </el-card>
      </li>
    </ul>
  </el-main>
  </div>
</template>

<script>
import Myheader from "../components/myheader";
import Sidebar from "../components/sidebar";
import Mainheader from "../components/mainheader";

export default {
name: "CrawlResult",
  components: {Mainheader, Sidebar, Myheader},
  data(){
  return{
    paperDetailList:['paper1','paper2'],
  }
  }
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
.mainpaperlist{
  position: absolute;
  background-color: transparent;
  height: 500px;
  width:660px;
  top:230px;
  left: 620px;
 list-style: none;
}
.papercard{
  height:150px;
  box-shadow: 5px 5px 5px #d3dce6;
  margin-top:10px;
  line-height: 150px;
}
</style>
