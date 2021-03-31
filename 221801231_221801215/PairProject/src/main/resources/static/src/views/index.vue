<template>
  <div>
    <myheader
      :userName="this.Username"
      :loginStatus="this.loginStatus"
    ></myheader>
    <el-main>
      <el-image
        src="https://i.loli.net/2021/03/25/8LunI4sv1UGlEdD.png"
        style="width: 500px"
      />
      <div class="searchBox">
        <ul class="dropDownMenu">
          <li style="padding-left: 16px">
            单个<i class="el-icon-arrow-down el-icon--right"></i>
          </li>
          <li class="batch">批量</li>
        </ul>
        <input
          class="searchInput"
          type="text"
          placeholder="输入相关论文题目词汇"
          v-model="searchForm.singleSearchText"
        />
        <input
          class="searchButton"
          type="button"
          value="添加"
          style="color: #eeeeee"
          @click="AddTitle"
        />
      </div>
      <div
        class="crawlButton"
        @mouseenter="showButtonText"
        @mouseleave="showButtonText"
        @click="ToCrawlResult"
      >
        <span v-show="buttonhover">爬取内容</span>
        <i
          class="fa fa-arrow-right"
          aria-hidden="true"
          style="
            font-size: 30px;
            position: absolute;
            top: 50%;
            right: 13px;
            margin-top: -15px;
          "
        ></i>
      </div>
      <div v-if="loadingFinished&&loginStatus" class="paperList"><i class="el-icon-loading" style="font-size:30px"></i></div>
      <div class="paperList" v-show="tableData.length != 0">
        <ul v-for="(items, index) in tableData" :key="index">
          <li class="paperItem">
            <el-tooltip :content="items.title" effect="light"
              ><span
                style="
                  width: 300px;
                  position: absolute;
                  left: 20px;
                  text-align: left;
                  text-overflow: ellipsis;
                  overflow: hidden;
                  white-space: nowrap;
                "
              >
                {{ items.title }}
                {{ index }}
              </span></el-tooltip
            >
            <i
              class="fa fa-trash"
              aria-hidden="true"
              style="margin-left: 400px"
              @click="deleteItem(items.id, index)"
            ></i>
          </li>
        </ul>
      </div>
    </el-main>
    <el-footer>copyright &copy; 2021 puffer&&tars 结对作业二</el-footer>
  </div>
</template>

<script>
import Myheader from "../components/myheader";
import Mymain from "../components/mymain";
export default {
  name: "index",
  components: { Mymain, Myheader },
  data() {
    return {
      loadingFinished:true,
      searchForm: {
        searchWay: true,
        singleSearchText: "",
      },
      tableData: [],
      buttonhover: false,
      Username: "",
      loginStatus: false,
      paperNum: 0,
    };
  },
  mounted() {
    // this.loginStatus = this.$route.query.isLogin;
    this.Username = sessionStorage.getItem("username");
    this.loginStatus = sessionStorage.getItem("loginstatus");
    this.GetUserPaperList();
  },
  methods: {
    GetUserPaperList: function () {//获取用户相关论文列表
      if(this.loginStatus=="true")
      {
        this.$message({
        message: "用户论文列表加载中",
      });
      let newTitle = {};
      let _this = this;
      this.$axios
        .get(_this.$api.globalUrl + "/userPaper/all", {
          params: {},
        })
        .then(function (response) {
          _this.$message({
            message: "用户论文列表加载成功",
            type: "success",
          });
          response.data.data.forEach((element) => {
            let newTitle = {};
            newTitle.id = element.id;
            newTitle.title = element.title;
            _this.tableData.push(newTitle);
            _this.paperNum = _this.tableData.length;
          });
          _this.loadingFinished=false;
        })
        .catch(function (error) {
          console.log(error);
          _this.$message.error("用户论文列表加载失败");
        });
      }
      else{
         this.$message({
            message: "登录后即可获取属于你的用户列表！",
          });
      }
    },
    AddTitle: function () {//添加与输入词相关的论文题目
      let newTitle = {};
      let _this = this;
      if (this.searchForm.singleSearchText == "") {
        this.$message({
          message: "未输入你要查询的题目",
          type: "warning",
        });
      } else {
        this.$axios
          .get(_this.$api.globalUrl + "/userPaper/add", {
            params: {
              titleOrigin: _this.searchForm.singleSearchText,
            },
          })
          .then(function (response) {
            console.log(response);
            _this.$message({
              message: "用户论文列表添加成功",
              type: "success",
            });
            response.data.data.forEach((element) => {
              let newTitle = {};
              newTitle.id = element.id;
              newTitle.title = element.title;
              _this.tableData.push(newTitle);
            });
          })
          .catch(function (error) {
            console.log(error);
            _this.$message.error("用户论文列表添加失败");
          });
        this.searchForm.singleSearchText = "";
      }
    },
    deleteItem: function (value, index) {//删除项
      let _this = this;
      this.$axios
        .get(_this.$api.globalUrl + "/userPaper/delete", {
          params: {
            paperId: value,
          },
        })
        .then(function (response) {
          _this.$message({
            message: "删除成功",
            type: "success",
          });
          console.log(response);
          _this.tableData.splice(index, 1);
        })
        .catch(function (error) {
          console.log(error);
          _this.$message.error("删除失败");
        });
    },
    showButtonText: function () {
      this.buttonhover = !this.buttonhover;
    },
    ToCrawlResult: function () {
      this.$router.push({
        path: "/crawlresult",
      });
      sessionStorage.setItem("papernum", this.tableData.length);
    },
  },
};
</script>

<style>
@import "../css/Index.css";
</style>
