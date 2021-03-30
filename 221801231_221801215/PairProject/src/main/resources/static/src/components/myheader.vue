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
            position: absolute;
            top: 13px;
            left: -100px;
          "
        ></i>
        <router-link to="/index" style="text-decoration:none;color:white"><span>计算机论文查询平台</span></router-link>
      </div>
      <el-button class="loginbutton" @click="toLogin" v-show="!loginStatus">登录</el-button>
      <el-button class="registerbutton" @click="toRegister" v-show="!loginStatus">注册</el-button>
      <el-avatar :size="50" :src="circleUrl" style="position: absolute;top:15px;right: 250px"></el-avatar>
      <div class="showusername" style="position:absolute;right:100px;font-weight:bold">欢迎你：{{userName}}</div>
    </el-header>
  </div>
</template>
<script>
export default {
  props:{
  'userName':String,
  'loginStatus':String
  },
  name:'myheader',
  data(){
    return{
      circleUrl:
        "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
      squareUrl:
        "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",
        username:this.userName,
        loginstatus:""
    }
  },
  mounted(){
     this.username=localStorage.getItem['username'];
     this.loginStatus=localStorage.getItem['loginstatus'];
  },
  // watch:{
  //    'userName':function(val)
  //    {
  //    this.userName=val;
  //    alert(this.userName);
  //    },
  //    'loginStatus':function(val){
  //     this.loginStatus=val;
  //    }
  // },
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
  position: absolute;
  top: 20px;
  right: 500px;
}
.registerbutton {
  background-color: #333333 !important;
  border-color: #333333;
  color: #d3dce6;
  position: absolute;
  top: 20px;
  right: 330px;
}
.title {
  display: inline-block;
  width: 500px;
  height: 50px;
  line-height: 50px;
  font-size: 32px;
  position: absolute;
  left: 400px;
  top:10px;
}
.title span{
  position: absolute;
  top: 5px;
  width: 300px;
  left:-50px;
}
</style>
