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
              class="logininput"
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
      </div>
    </mymain>
    <el-footer>copyright &copy; 2021 puffer&&tars 结对作业二</el-footer>
  </el-container>
</template>
<script>
import Myheader from "../components/myheader";
import mymain from "../components/mymain";
export default {
  name: "Login",
  components: { Myheader, mymain },
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
      loginImageUrl: "https://i.loli.net/2021/03/31/q6sy5CGrhvX91wE.jpg",
    };
  },
  methods: {
    loginForm(formName) {
      if(this.form.loginName==""||this.form.loginPassword=="")
      {
        this.$message({
              message: "用户名账号密码未输入",
              type: "warning",
            });
      }
      else
      {let _this = this;
      this.$axios
        .post(_this.$api.globalUrl + "/user/login", {
          username: _this.form.loginName,
          account: _this.form.loginName,
          password: _this.$md5(_this.form.loginPassword),
        })
        .then(
          function (response) {
            console.log(response.data.data);
            _this.$message({
              message: "登录成功",
              type: "success",
            });
            _this.$router.push({
              path: "/index",
              query: {
                username: response.data.data,
                isLogin: true,
              },
            });
            sessionStorage.setItem("username", response.data.data);
            sessionStorage.setItem("loginstatus", true);
          },
          function (error) {
            console.log("error");
            _this.$message({
              message: "登录失败",
              type: "warning",
            });
          }
        );
      }
    },
  },
};
</script>
<style>
@import "../css/Login.css";
</style>
