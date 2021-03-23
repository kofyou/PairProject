<template>
  <el-container>
    <el-header>
      <div class="title">
        <i class="fa fa-university" aria-hidden="true" style="margin-right: 60px;font-size: 36px;float: left; position: relative; top: 22px;left: 40px;"></i>
        <span class="title-text">计算机论文查询平台</span>
      </div>
      <el-button class="loginbutton">登录</el-button>
      <el-button class="registerbutton">注册</el-button>
    </el-header>
    <el-main>
      <el-image class="pageimage"></el-image>
      <div class="loginblock">
        <span class="logintitle">登录</span>
        <el-form ref="form" :model="form" class="loginform">
           <el-form-item label="登录账号">
             <el-input type="text" placeholder="请输入账号" v-model="form.name" class="logininput"></el-input>
           </el-form-item>
          <el-form-item label="登录密码">
            <el-input type="password" placeholder="请输入密码" v-model="form.password"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @onclick="onSubmit" style="width: 500px;background-color:#333333;">登录</el-button>
          </el-form-item>
        </el-form>
      </div>

    </el-main>
    <el-footer>Footer</el-footer>
  </el-container>
</template>
<script>
export default {
  name:'Login',
  data(){
    return{
      form:{
        name:'',
        password:''
      }
    }

  },
  methods:{
    onSubmit:function () {
      alert("hello");
    }
  }

}
</script>
<style>
.el-header, .el-footer {
  background-color: #133382;
  color: #D3DCE6;
  text-align: center;
  line-height: 80px;
  height: 80px !important;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  line-height: 160px;
  height: 900px !important;
  margin: 0 !important;
  padding: 0 !important;
}

body > .el-container {
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
.pageimage{
  margin-top: 0px;
  height: 900px;
  width: 50%;
  float: left;
  position: relative;
  left: 0px;
}
.title-text
{
  width: 300px;
  float: left;
  left: 100px;
  top:15px;
  position: absolute;
}

.el-button{
  width: 120px;
}

.loginbutton
{
    margin: auto;
    margin-left: 200px;
  margin-right:50px;
}
.registerbutton{
  background-color:#333333!important;
  border-color: #333333;
  color: #D3DCE6;
  margin-right: 50px;
}
.title
{
  display: inline-block;
  width: 500px;
  height: 50px;
  line-height: 50px;
  margin: auto  300px auto 100px;
  font-size: 32px;
  position: relative;

}
.loginblock
{
  height: 900px;
  width: 50%;
  float: left;
  position: relative;
  right: 0px;
  background-color: #eeeeee;
}
.logintitle
{
  width:200px;
  height: 100px;
  float: left;
  top:200px;
  left:100px;
  font-size: 40px;
  line-height: 100px;
  position: relative;
  font-weight: bold;
}
.loginform{
  width:500px;
  height: 500px;
  float: left;
  position: relative;
  top: 300px;
  left:50%;
  margin-left: -50%;
}
.logininput{
  background: none !important;
  outline: none !important;
  border: none !important;
}
</style>
