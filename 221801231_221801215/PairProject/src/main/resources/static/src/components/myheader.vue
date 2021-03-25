<template>

  <div>
    <el-header>
      <div class="title">
        <i
          class="fa fa-university"
          aria-hidden="true"
          style="
            margin-right: 60px;
            font-size: 36px;
            float: left;
            position: relative;
            top: 22px;
            left: 40px;
          "
        ></i>
        <span class="title-text">计算机论文查询平台</span>
      </div>
      <el-button class="loginbutton" @click="toLogin">登录</el-button>
      <el-button class="registerbutton" @click="toRegister">注册</el-button>
      <el-avatar :size="50" :src="circleUrl" style="position: absolute;top:15px;right: 200px"></el-avatar>
    </el-header>
  </div>
</template>
<script>
export default {
  name:'myheader',
  data(){
    return{
      circleUrl:
        "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
      squareUrl:
        "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png"
    }
  },
  methods:{
    toLogin:function (){
      this.$router.push('/login');
    },
    toRegister:function () {
      this.$router.push('/Register');

    }
  }
}
</script>
<style scoped>
.el-header,
.el-footer {
  background-color: #133382;
  color: #d3dce6;
  text-align: center;
  line-height: 80px;
  height: 80px !important;
}
.el-button {
  width: 120px;
}

.loginbutton {
  margin: auto;
  margin-left: 200px;
  margin-right: 50px;
}
.registerbutton {
  background-color: #333333 !important;
  border-color: #333333;
  color: #d3dce6;
  margin-right: 100px;
}
</style>
