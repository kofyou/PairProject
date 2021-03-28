<template>
  <el-menu :default-active="defaultActive" class="el-menu-demo mainheader" mode="horizontal">
    <router-link to="/CrawlResult" class="myheaderitem"><el-menu-item index="1">论文爬取结果</el-menu-item></router-link>
    <router-link to="/keywordMap" class="myheaderitem"><el-menu-item index="2">关键词谱图</el-menu-item></router-link>
    <router-link to="/heattrend" class="myheaderitem"><el-menu-item index="3">热度走势</el-menu-item></router-link>
  </el-menu>
</template>

<script>
export default {
name: "mainheader"
}
</script>

<style scoped>
.mainheader
{
  position: absolute;
  left: 50%;
  top:150px;
  margin-left: -250px;
  width: 660px;
  vertical-align: center;
  background: #5677FC;
  border-radius: 3em;
}
.el-menu-item
{
  width: 220px;
  color: #d3dce6;
}
.el-menu-item:hover,.el-menu-item:focus
{
  background-color: rgba(0,0,0,0)!important;
  color: #ffffff !important;

}
.myheaderitem{
  float: left;
  text-decoration: none;
}
</style>
