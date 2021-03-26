<template>
  <div>
<myheader></myheader>
    <el-main>
  <el-image src="https://i.loli.net/2021/03/25/8LunI4sv1UGlEdD.png" style="width: 500px"/>
      <div class="searchBox">
       <ul class="dropDownMenu">
         <li style="padding-left:16px" >单个<i class="el-icon-arrow-down el-icon--right"></i></li>
         <li class="batch">批量</li>
       </ul>
        <input class="searchInput" type="text" placeholder="添加单个论文题目"></input>
        <input class="searchButton" type="button" value="添加" style="color: #eeeeee">
      </div>
       <div class="paperList">
         <el-table
           :data="tableData"
           style="width: 100%">
           <el-table-column
             prop="date"
             label="日期"
             style="width: 100%">
           </el-table-column>
         </el-table>
       </div>
    </el-main>
  </div>
</template>

<script>
import Myheader from "../components/myheader";
import Mymain from "../components/mymain";
export default {
  name:'index',
  components: {Mymain, Myheader},
  data()
  {
    return {
      searchForm:{
        searchWay:true,
        singleSearchText:""
      },
      tableData: [{
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1517 弄'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1519 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      }]
    }
  },
  methods:{

    }

}
</script>

<style>
.el-main {
  background-color: #ffffff;
  color: #333;
  text-align: center;
  line-height: 160px;
  height: 900px !important;
  margin: 0 !important;
  padding: 0 !important;
}
.searchInput{
  display: inline-block;
  position: absolute;
  width: 600px;
  height: 50px;
  left:50%;
  margin-left: -250px;
  border: #133382 2px solid;
}
.searchButton{
  position: absolute;

  right: 60px;
  width:136px;
  height: 54px;
  border: #133382 2px solid;
  background-color: #133382;
  border-top-right-radius: 5%;
  border-bottom-right-radius: 5%;
}
.dropDownMenu{
  display: inline-block;
  height: 50px;
  width: 100px;
  background-color: #133382;
  line-height: 50px;
  color:#ffffff;
  margin-right: 0px;
  position: absolute;
  left:100px;
  border: #133382 2px solid;
  border-top-left-radius: 5%;
  border-bottom-left-radius:5%;
  list-style: none;
}
.dropDownMenu>li:nth-child(2)
{
  display: none;
  height: 50px;
  width: 100px;
  background-color: #133382;
  position: absolute;
  border: #133382 1px solid;
  left:-2px;
}
.searchBox
{
  display: block;
  position: relative;
  height: 80px;
  width: 900px;
  top: -50px;
  left:50%;
  margin-left: -450px;
  line-height: 80px;

}
.paperList
{
  position: absolute;
  width: 600px;
  height: 400px;
  left:50%;
  margin-left: -300px;
  border: #133382 1px solid;
  box-shadow: 5px 5px ;
}
</style>
