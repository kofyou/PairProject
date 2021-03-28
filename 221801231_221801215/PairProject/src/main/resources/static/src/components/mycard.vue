<template>
<div>
  <el-card shadow="hover" class="papercard">
     <div class="papertitle">
       论文题目1
     </div>
    <div class="precis">
      摘要内容
    </div>
    <div class="keyword">
      关键词
    </div>
    <a href="" class="originallink">
      原文链接
    </a>
    <slot></slot>
  </el-card>
</div>
</template>

<script>
export default {

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
  width: 100px;
  height: 50px;
  line-height: 50px;
  position: absolute;
  top:10px;
  left: 20px;
  font-size: 20px;
}
.precis{
  width: 100%;
  height: 60px;
  line-height: 60px;
  position: absolute;
  top:60px;
  left: 20px;
}
.keyword{
  width: 50px;
  height: 40px;
  line-height: 40px;
  position: absolute;
  top:130px;
  left: 20px;
}
.originallink{
  display: block;
  width: 60px;
  height: 40px;
  line-height: 40px;
  position: absolute;
  top:160px;
  left: 20px;
  font-size: 15px;
  text-decoration: none;
  color: #d3dce6;
}
</style>
