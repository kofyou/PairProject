<template>
  <div class = "index-view root-div">
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
            <router-link to = "/register"><span id = "register-guide">没有账号，点击注册</span></router-link>
          </td>
        </tr>
      </table>
    </el-card>
  </div>
</template>

<script>
import Router from '../router/index'
import {stringify} from "querystring";

export default {
  name: "login",
  data(){
    return {
      user:{
        //<-- 调试先定死 -->
        username:'',
        password:'',
      }
    }
  },
  methods: {
    doLogin() {
      const that = this;
      let myResponse = null;
      let username = this.user.username
      let psw = this.user.password;
      if(username == "")
        this.alertMes("用户名不能为空！");
      else if(psw == "")
        this.alertMes("密码不能为空！");
      else {
        // let data=new FormData()
        // data.append('username',this.user.username.toString());
        // data.append('password',this.user.password.toString());
        this.axios.post('user/login', stringify({
            'username': that.user.username,
            'password': that.user.password
          }
        ))
          .then(
            function (response) {
              console.log(that.user.username.toString() + " " + that.user.password.toString());
              myResponse = response;
              if(myResponse.data.code == '0') {
                // alert("登录成功！");
                Router.push({ path: 'index' });
              }
              else{
                that.alertMes("账号与密码不匹配！！！");
                }
              console.log(myResponse);
            })
          .catch(
            function (error) {
              console.log(error);
            });
      }
    },
    alertMes(mes){
        this.$alert(mes, '提示', {
          confirmButtonText: '确定',
          callback: action => {
            this.$message({
              type: 'info',
              message: `action: ${ action }`
            });
          }
        });
    },
  }
}
</script>

//scoped只影响本组件
<style scoped>
.index-view {
  height: 37em;
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
  width:360px;
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
