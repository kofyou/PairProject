<template>
<div>
  <el-card shadow="hover" class="papercard">
     <div class="papertitle">
      {{this.paperDetail.paperTitle}}
     </div>
    <div class="precis">
      论文摘要:{{this.paperDetail.paperAbstract}}
    </div>
    <div class="keyword">
      关键词:<span v-for="(item,index) in this.paperDetail.paperKeyword" :key="index">{{item}}</span>
    </div>
    <a :href="this.paperDetail.paperUrl" class="originallink">
      原文链接
    </a>
    <slot></slot>
  </el-card>
</div>
</template>

<script>
export default {
 props:{
   paperDetail:{
     type:Object,
     default:()=>{  return{}}
   }
 },
  mounted(){

  }
}
</script>

<style>
.papercard{
  height:200px;
  box-shadow: 5px 5px 5px #d3dce6;
  margin-top:10px;
  line-height: 150px;
  position: relative;
}
.papertitle{
  width: 700px;
  height: 50px;
  line-height: 50px;
  position: absolute;
  top:10px;
  left: 30px;
  font-size: 15px;
  font-weight: bold;
  text-align: left;
}
.precis{
  width: 700px;
  height: 60px;
  line-height: 60px;
  position: absolute;
  top:40px;
  left: 30px;
  text-align: left;
  text-overflow: ellipsis;
  overflow:hidden;
  white-space:nowrap;
}
.keyword{
  width: 700px;
  height: 40px;
  line-height: 40px;
  position: absolute;
  top:130px;
  left: 30px;
  text-align: left;
  text-overflow: ellipsis;
  overflow:hidden;
  white-space:nowrap;
}
.originallink{
  display: block;
  width: 60px;
  height: 40px;
  line-height: 40px;
  position: absolute;
  top:160px;
  left: 30px;
  font-size: 15px;
  text-decoration: none;
  color: #d3dce6;
}
</style>
