<template>
  <div>
    <myheader></myheader>
    <mymain>
      <el-image class="pageimage" :src="this.registerImageUrl"></el-image>
      <div class="registerblock">
        <span class="registertitle">注册</span>
        <el-form
          ref="registerForm"
          :model="registerform"
          class="registerform"
          :rules="rules"
        >
          <el-form-item label="注册昵称" prop="registerName">
            <br />
            <el-input
              type="text"
              placeholder="请输入昵称"
              v-model="registerform.registerName"
              class="registerinput"
            ></el-input>
          </el-form-item>
          <el-form-item label="注册账号" prop="registerAccount">
            <br />
            <el-input
              type="text"
              placeholder="请输入账号"
              v-model="registerform.registerAccount"
              class="registerinput"
            ></el-input>
          </el-form-item>
          <el-form-item label="注册密码" prop="registerPassword">
            <br />
            <el-input
              type="password"
              placeholder="请输入密码"
              v-model="registerform.registerPassword"
              class="registerinput"
            ></el-input>
          </el-form-item>
          <el-form-item label="确认注册密码" prop="registerPasswordAgain">
            <br />
            <el-input
              type="password"
              placeholder="请再次输入密码"
              v-model="registerform.registerPasswordAgain"
              class="registerinput"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button
              type="primary"
              @click="registerForm('registerForm')"
              style="
                width: 500px;
                background-color: #333333;
                margin-top: 20px;
                border: #333333;
              "
              >注册</el-button
            >
          </el-form-item>
        </el-form>
      </div>
    </mymain>
   <el-footer>copyright &copy; 2021 puffer&&tars 结对作业二</el-footer>
  </div>
</template>

<script>
import myheader from "../components/myheader";
import mymain from "../components/mymain";
import { validateAccountNumber } from "../utils/validate";
import { validatePasswordNumber } from "../utils/validate";

const validatorAccountNumber = (rule, value, callback) => {
  if (!value) {
    return callback(new Error("请输入账号"));
  } else {
    if (validateAccountNumber(value)) {
      callback();
    } else {
      return callback(
        new Error(
          "账号可包含中文、英文、数字但不包括下划线等特殊符号，长度为2-20"
        )
      );
    }
  }
};
const validatorPasswordNumber = (rule, value, callback) => {
  if (!value) {
    return callback(new Error("请输入密码"));
  } else {
    if (validatePasswordNumber(value)) {
      callback();
    } else {
      return callback(
        new Error("密码以字母开头，长度在6~18之间，只能包含字母、数字和下划线")
      );
    }
  }
};
export default {
  name: "Register",
  components: {
    mymain,
    myheader,
  },
  data() {
    return {
      registerImageUrl: "https://i.loli.net/2021/03/31/3KZkfXNLc4psEMl.jpg",
      registerform: {
        registerName: "",
        registerAccount: "",
        registerPassword: "",
        registerPasswordAgain: "",
      },
      rules: {
        registerName: [
          {
            required: true,
            message: "请输入用户名",
            trigger: "blur",
          },
          {
            min: 3,
            max: 20,
            message: "长度在 3 到 20 个字符",
            trigger: "blur",
          },
        ],
        registerAccount: [
          {
            required: true,
            validator: validatorAccountNumber,
            trigger: "blur",
          },
        ],
        registerPassword: [
          {
            required: true,
            validator: validatorPasswordNumber,
            trigger: "blur",
          },
        ],
        registerPasswordAgain: [
          {
            required: true,
            validator: validatorPasswordNumber,
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    registerForm(formName) {
      console.log(this.$md5(this.registerform.registerPassword));
      let _this = this;
      this.$axios
        .post(_this.$api.globalUrl + "/user/register", {
          username: _this.registerform.registerName,
          account: _this.registerform.registerAccount,
          password: _this.$md5(_this.registerform.registerPassword),
        })
        .then(
          function (response) {
            console.log(response);
            _this.$message({
              message: "注册成功",
              type: "success",
            });
            _this.$router.push("/login");
          },
          function (error) {
            console.log("error");
            _this.$message.error("注册失败");
          }
        );
    },
  },
};
</script>

<style scoped>
.el-footer {
  background-color: #133382;
  color: #d3dce6;
  text-align: center;
  line-height: 80px;
  height: 80px !important;
}
.registertitle {
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
.registerblock {
  height: 900px;
  width: 50%;
  float: left;
  position: relative;
  right: 0px;
  background-color: #ffffff;
}
.registerform {
  width: 500px;
  height: 500px;
  float: left;
  position: relative;
  top: 300px;
  left: 50%;
  margin-left: -50%;
}
.registerinput {
  background: rgba(0, 0, 0, 0.2) !important;
  border: 1px solid rgba(0, 0, 0, 0.2) !important;
}
.pageimage {
  margin-top: 0px;
  height: 900px;
  width: 50%;
  float: left;
  position: relative;
  left: 0px;
}
</style>
