<template>
  <div class = "base">
    <div id = "head">

      <el-row>
        <el-col :span="3" >
          <router-link to="/index" style="text-decoration: none !important;">
            <div class="grid-content bg-purple" :class = "{active : isActive['index']}" >
              <span>主页</span>
            </div>
          </router-link>
        </el-col>

        <el-col :span="3">
          <router-link to="starlist"  style="text-decoration: none !important;" >
            <div class="grid-content bg-purple"  :class = "{active : isActive['starlist']}" >
              <span>我的收藏</span>
            </div>
          </router-link>
        </el-col>

        <el-col :span="3">
          <router-link to= "/analysis" style="text-decoration: none !important;">
            <div class="grid-content bg-purple" :class = "{active : isActive['analysis']}" >
              <span>顶会数据</span>
            </div>
          </router-link>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  name: "login",
  props:['type'],
  data(){
    return {
      username: "DemoJi",
      isActive:{
        "index": false,
        "starlist": false,
        "analysis": false,
        "user": false
     },
    }
  },
  created() {
    this.isActive[this.type] = true;
  },
  methods: {
  }
}
</script>

//scoped只影响本组件
<style scope>

el-row {
  margin-bottom: 20px;
}

el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
  text-align: center;
  margin-top: 10px;
  margin-left: 20px;
  padding-top: 10px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}

.active{
  background-color: #99a9bf;
}

.base{
  background-color: black;
  height: 60px;
  margin-bottom: 50px;
}

</style>
