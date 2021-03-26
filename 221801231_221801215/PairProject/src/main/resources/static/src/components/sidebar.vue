<template>
  <div class="mySideBar">
    <div>
      <input type="text" placeholder="请输入论文题目" class="mySideBar-input"></input>
      <button class="mySideBar-search"><i class="fa fa-search" aria-hidden="true"></i></button>
      <el-menu :default-active="defaultActive" class="el-menu-demo mySideBarMenu" mode="horizontal" >
        <el-menu-item index="1">全部论文</el-menu-item>
        <el-menu-item index="2">搜索结果</el-menu-item>
      </el-menu>
      <div  class="paperlisttitle">论文列表</div>
      <ul style="list-style: none; position:absolute;top: 130px;left: 50%;margin-left: -90px">
        <li v-for="(item,index) in paperList" class="paperlistitem">
          <el-card shadow="hover">
            <span style="position: absolute;left: 25%">{{index}}{{item}}</span>
            <i class="fa fa-trash" aria-hidden="true" style="position: absolute;left: 75%;" @click="deleteItem(index)"></i>
          </el-card>
        </li>
        <li class="paperlistitem"><el-card shadow="hover">
          
        </el-card></li>
      </ul>

    </div>

  </div>
</template>
<script>
export default {
name: "sidebar",
  data(){
  return{
    paperList:['title1','title2',],
    defaultActive:'1',
  }
  }
}
</script>

<style scoped>
.mySideBar
{
  width: 200px;
  position: absolute;
  height: 100%;
  border: #d3dce6 1px solid;
  box-shadow: 1px 1px 5px #d3dce6;
}
.mySideBar-input{
  width: 80%;
  height: 30px;
  position: absolute;
  top: -2px;
  left: -2px;
  border: #133382 2px solid;
  margin:0;
}
.mySideBar-search
{
  height: 34px;
  width:20%;
  position: absolute;
  color: #ffffff;
  border: #133382 2px solid;
  background: #133382;
  right: 0px;
  top:-2px;
  border-top-right-radius: 5px;
  border-bottom-right-radius: 5px;
 font-size: 20px  ;
}
.mySideBarMenu
{
  position: absolute;
  width: 100%;
  top:33px;
  background-color: #5677FC;
  box-shadow: 0px 2px 2px #d3dce6;
}
.el-menu-item:hover{
  background-color: rgba(0,0,0,0) !important;
  color: #d3dce6 !important;
}
.el-menu-item:focus
{
  background-color: #5677FC !important;
}
.el-menu-item{
  color: #ffffff !important;
}
.el-menu-item.is-active{
  border-bottom-color: yellow !important;
}
.papercard
{

  width: 180px;
  height:40px;
}
.papercard div
{
  position: absolute;
  left: -5px;
  top: 50%;
  margin-top: -15px;
  width: 140px;
  height:30px;
  line-height: 30px;
}
.paperlisttitle
{
  position: absolute;
  top: 90px;
  font-size: 20px;
  left: 5px;
  height: 50px;
  color: #133382;
  line-height: 50px;
}
.paperlistitem{
  float: left;
  width: 180px;
  height: 50px !important;
 margin-top: 5px;
}
.el-card{
  height: 50px;
  line-height: 10px;
}
</style>
