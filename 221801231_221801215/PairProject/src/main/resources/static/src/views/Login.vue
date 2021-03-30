<template>
  <el-container>
   <myheader></myheader>
    <mymain>
      <el-image class="pageimage" :src="this.loginImageUrl"></el-image>
      <div class="loginblock">
        <span class="logintitle">登录</span>
        <el-form ref="form" :model="form" class="loginform" :rules="rules">
          <el-form-item label="登录账号" prop="loginName">
            <br />
            <el-input
              type="text"
              placeholder="请输入账号"
              v-model="form.loginName"
              class="logininput"
            ></el-input>
          </el-form-item>
          <el-form-item label="登录密码" prop="loginPassword">
            <br />
            <el-input
              type="password"
              placeholder="请输入密码"
              v-model="form.loginPassword"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button
              type="primary"
              @click="loginForm('form')"
              style="
                width: 500px;
                background-color: #333333;
                margin-top: 20px;
                border: #333333;
              "
            >登录</el-button
            >
          </el-form-item>
        </el-form>
        <router-link
          to="/Register"
          style="
            text-decoration: none;
            color: #333333;
            position: absolute;
            top: 600px;
            right: 650px;
          "
        >注册新账号-></router-link>

      </div>
    </mymain>
    <el-footer>Footer</el-footer>
  </el-container>
</template>
<script>

import Myheader from "../components/myheader";
import mymain from "../components/mymain";
export default {
  name: "Login",
  components: {Myheader,mymain},
  data() {
    return {
      form: {
        loginName: "",
        loginPassword: "",
      },
      rules: {
        loginName: [
          {
            required: true,
            message: "请输入账号",
            trigger: "blur",
          },
        ],
        loginPassword: [
          {
            required: true,
            message: "请输入密码",
            trigger: "blur",
          },
        ],
      },
      circleUrl:
        "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
      squareUrl:
        "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",
      loginImageUrl:"https://i.loli.net/2021/03/31/q6sy5CGrhvX91wE.jpg",
    };
  },
  methods: {
    loginForm(formName)
    {
      console.log(this.$md5(this.form.loginPassword));
      let _this=this;
      this.$axios.post(_this.$api.globalUrl+"/user/login",{username:_this.form.loginName,account:_this.form.loginName,password:_this.$md5(_this.form.loginPassword)}).then(function (response) {
        console.log(response.data.data);
        _this.$message({
          message:'登录成功',
          type:'success'
        });
        _this.$router.push({
          path:'/index',
          query: {
            username:response.data.data,
            isLogin:true
          }});
          sessionStorage.setItem('username',response.data.data);
          sessionStorage.setItem('loginstatus',true);
      },function (error) {
        console.log("error");
        _this.$message({
          message:'登录失败',
          type:'warning'
        });
      })
    }
  },
};
</script>
<style>
.el-header,
.el-footer {
  background-color: #133382;
  color: #d3dce6;
  text-align: center;
  line-height: 80px;
  height: 80px !important;
}

.el-aside {
  background-color: #d3dce6;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  width:100%;
  background-color: #e9eef3;
  color: #333;
  text-align: center;
  line-height: 160px;
  height: 900px !important;
  margin: 0 !important;
  padding: 0 !important;
  overflow-x:hidden;
}
.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
.pageimage {
  margin-top: 0px;
  height: 900px;
  width: 50%;
  float: left;
  position: relative;
  left: 0px;
}
.title-text {
  width: 300px;
  float: left;
  left: 100px;
  top: 15px;
  position: absolute;
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
  margin-right: 50px;
}
.title {
  display: inline-block;
  width: 500px;
  height: 50px;
  line-height: 50px;
  margin: auto 300px auto 100px;
  font-size: 32px;
  position: relative;
}
.loginblock {
  height: 900px;
  width: 50%;
  float: left;
  position: relative;
  right: 0px;
  background-color: #eeeeee;
}
.logintitle {
  width: 200px;
  height: 100px;
  float: left;
  top: 200px;
  left: 100px;
  font-size: 40px;
  line-height: 100px;
  position: relative;
  font-weight: bold;
}
.loginform {
  width: 500px;
  height: 500px;
  float: left;
  position: relative;
  top: 300px;
  left: 50%;
  margin-left: -50%;
}

.logininput {
  background:rgba(0,0,0,0.2) !important;
  border: 1px solid rgba(0,0,0, 0.2) ! important;
}
</style>
