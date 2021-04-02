<template>
  <div class = "index-view root-div">
    <!--    回到主页按钮-->
    <!--    <i class="el-icon-back"></i>-->
    <div id = my-title-div>
      <span id = "my-title"> 顶会论文 </span>
    </div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>注册</span>
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
            <el-input type="password" v-model="user.password1" placeholder="请输入密码"></el-input>
            <!-- @keydown.enter.native="doLogin"当按下enter键的时候也会执行doLogin方法-->
          </td>
        </tr>
        <tr>
          <td><i class="el-icon-key"></i> </td>
          <td>
            <el-input type="password" v-model="user.password2" placeholder="再次输入密码"></el-input>
            <!-- @keydown.enter.native="doLogin"当按下enter键的时候也会执行doLogin方法-->
          </td>
        </tr>
        <tr><td>&nbsp;&nbsp;</td></tr>
        <tr>
          <td colspan="2">
            <!-- 点击事件的两种不同的写法v-on:click和 @click-->
            <!--<el-button style="width: 300px" type="primary" v-on:click="doLogin">登录</el-button>-->
            <el-button id = "login-button" style="width: 300px" type="primary" @click="doRegister">注册</el-button>
          </td>
        <tr>
          <td colspan="2">
            <router-link to="/login"><span id = "register-guide">已有账号，点击登录</span></router-link>
          </td>
        </tr>
      </table>
    </el-card>
  </div>
</template>

<script>
import Router from '../router/index'

export default {
  name: "login",
  data(){
    return {
      user:{
        //<-- 调试先定死 -->
        username:'',
        password1:'',
        password2:''
      }
    }
  },
  methods: {
    doRegister(){
      const that = this;
      let myResponse = null;
      let username = this.user.username
      let psw1 = this.user.password1;
      let psw2 = this.user.password2;
      if(username == "")
        that.alertMes("用户名不能为空！");
      else if(psw1 == "" || psw2 == "")
        that.alertMes("密码不能为空！");
      else if( psw1 !== psw2)
        that.alertMes("两次输入的密码不一致！");
      else if(username.length < 8)
        that.alertMes("账号长度需大于等于8位！");
      else if(psw1 < 8)
        that.alertMes("密码长度需大于等于8位！");
      else {
        this.axios.post('user/register', {
          username: that.user.username,
          password: that.user.password1
        }).then(function (response) {
            myResponse = response;
            if(myResponse.data.code == '0') {
              that.alertMes("注册成功，请返回登陆！");
              Router.push({path: 'login'});
            }
            else{
              that.alertMes('用户名已存在！');
            }
            // console.log(myResponse);
          }).catch(function (error) {
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
  width: 360px;
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
