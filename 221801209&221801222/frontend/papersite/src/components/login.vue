<template>
  <div class = "index-view">
<!--    回到主页按钮-->
<!--    <i class="el-icon-back"></i>-->
    <div id = my-title-div>
      <span id = "my-title"> 顶会论文 </span>
    </div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>登录</span>
      </div>
      <table>
        <tr>
          <td><i class="el-icon-user-solid">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</i></td>
          <td>
            <el-input v-model="user.username" placeholder="请输入用户名"></el-input>
          </td>
        </tr>
        <tr>
          <td><i class="el-icon-key"></i> </td>
          <td>
            <el-input type="password" v-model="user.password" placeholder="请输入密码" @keydown.enter.native="doLogin"></el-input>
            <!-- @keydown.enter.native="doLogin"当按下enter键的时候也会执行doLogin方法-->
          </td>
        </tr>
        <tr><td>&nbsp;&nbsp;</td></tr>
        <tr>
            <td colspan="2">
                <!-- 点击事件的两种不同的写法v-on:click和 @click-->
                <!--<el-button style="width: 300px" type="primary" v-on:click="doLogin">登录</el-button>-->
                <el-button id = "login-button" style="width: 300px" type="primary" @click="doLogin">登录</el-button>
            </td>
        </tr>
        <tr>
          <td colspan="2">
            <router-link to = "/register"><span id = "register-guide" @click="goToRegister">没有账号，点击注册</span></router-link>
          </td>
        </tr>
      </table>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "login",
  data(){
    return {
      user:{
        //<-- 调试先定死 -->
        username:'zhangsan',
        password:'123',
      }
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
.index-view {
  background-image: url("../assets/background.jpg");
  background-attachment: fixed;
  background-size: 100% 100%;
  background-repeat: no-repeat;
  height: 37em;
  width: auto;
  border: 0px;
  padding: 0px;
}

.text {
  font-size: 14px;
}

.item {
  padding: 18px 0;
}

.box-card {
  width: 27%;
  position: relative;
  margin: auto;
  top: 30%;
}

#register-guide{
  font-family: "Helvetica Neue";
  font-size: smaller;
  text-decoration: underline;
}

#my-title{
  font-family: "微软雅黑";
  font-size: 50px;
  letter-spacing: 120px;
}

#my-title-div{
  top: 20%;
  left: 30%;
  position: relative;
  margin: auto;
}

</style>
