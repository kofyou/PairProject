<template>
  <div class = "base">
    <div id = "head">

      <el-row :gutter="20">
        <el-col :span="3">
          <router-link to = "/index">
            <div class="grid-content bg-purple"><span>主页</span></div>
          </router-link>
        </el-col>

        <el-col :span="3">
          <router-link to = "/starlist">
            <div class="grid-content bg-purple"><span>我的收藏</span></div>
          </router-link>
        </el-col>

        <el-col :span="3">
          <router-link to = "/analysis">
            <div class="grid-content bg-purple"><span>顶会数据</span></div>
          </router-link>
        </el-col>


        <el-col :span="3" :offset="11">
          <router-link to = "/index">
            <div class="grid-content bg-purple">
              <i class="el-icon-user-solid">&nbsp;&nbsp;</i><span>{{ username }}</span>
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
  data(){
    return {
      username: "DemoJi"
    }
  },
  methods: {
    doLogin() {

    },
    goToRegister() {

    }

  }
}
</script>

//scoped只影响本组件
<style scoped>
.base {
  background-image: url("../assets/background.jpg");
  background-attachment: fixed;
  background-size: 100% 100%;
  background-repeat: no-repeat;
  height: 37.5em;
  width: auto;
  border: 0px;
  padding: 0px;
}

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





</style>
