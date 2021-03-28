<template>
  <div>
  <myheader></myheader>
  <el-main>
    <sidebar></sidebar>
    <el-tabs type="border-card" stretch=true  style="position: relative">
      <el-tab-pane>
        <span slot="label"><i class="el-icon-date"></i> 爬取结果显示</span>
        <ul style="list-style: none" >
          <li v-for="(item,index) in paperDetailList.slice((currentPage-1)*pagesize,currentPage*pagesize)" style="position: relative">
            <mycard>
              {{item}}
            </mycard>
            <i class="fa fa-trash" style="position: absolute; bottom: 20px;right:20px;cursor:pointer" @click="deleteCard(index)"></i>
          </li>
        </ul>
        <el-pagination
          small
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-size="pagesize"
          layout="prev, pager, next"
          :total="100"
        style="margin-top: 20px">
        </el-pagination>
      </el-tab-pane>
      <el-tab-pane label="关键词图谱">
        <el-card shadow="hover" class="keymap">

        </el-card>
      </el-tab-pane>
      <el-tab-pane label="热度走势">热度走势</el-tab-pane>
    </el-tabs>

  </el-main>
  </div>
</template>

<script>
import Myheader from "../components/myheader";
import Sidebar from "../components/sidebar";
import Mainheader from "../components/mainheader";
import Mycard from '../components/mycard.vue';

export default {
name: "CrawlResult",
  components: {Mainheader, Sidebar, Myheader,Mycard},
  data(){
  return{
    currentPage:1,
    pagesize:3,
    paperDetailList:['paper1','paper2','paper3','paper4','paper5','paper6','paper7','paper8','paper9'],
  }
  },
  methods:{
    deleteCard:function (value) {


        this.paperDetailList.splice(value,1);

     },
    handleCurrentChange:function (currentpage) {
      this.currentPage=currentpage;
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
  left: 700px;
 list-style: none;
}
.delete{
   position: absolute;
   bottom: 20px;
   right: 20px;
   font-size: 20px;
   cursor: pointer;
}
.el-tabs{
  width: 800px;
  height: 750px;
  position: absolute;
  left:700px;
  top:100px;
}
.keymap{
  width: 700px;
  height: 200px;
  margin: 0 auto;
  background-color: 
}
</style>
