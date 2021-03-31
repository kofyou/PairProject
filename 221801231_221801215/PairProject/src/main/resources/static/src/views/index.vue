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
    GetUserPaperList: function () {
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
        })
        .catch(function (error) {
          console.log(error);
          _this.$message.error("用户论文列表加载失败");
        });
    },
    AddTitle: function () {
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
    deleteItem: function (value, index) {
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
.el-main {
  background-color: #ffffff !important;
  color: #333;
  text-align: center;
  line-height: 160px;
  height: 900px !important;
  margin: 0 !important;
  padding: 0 !important;
}
.searchInput {
  display: inline-block;
  position: absolute;
  width: 500px;
  height: 50px;
  left: 50%;
  margin-left: -250px;
  border: #133382 2px solid;
}
.searchButton {
  position: absolute;

  right: 60px;
  width: 136px;
  height: 54px;
  border: #133382 2px solid;
  background-color: #133382;
  border-top-right-radius: 5%;
  border-bottom-right-radius: 5%;
}
.dropDownMenu {
  display: inline-block;
  height: 50px;
  width: 100px;
  background-color: #133382;
  line-height: 50px;
  color: #ffffff;
  margin-right: 0px;
  position: absolute;
  left: 100px;
  border: #133382 2px solid;
  border-top-left-radius: 5%;
  border-bottom-left-radius: 5%;
  list-style: none;
}
.dropDownMenu > li:nth-child(2) {
  display: none;
  height: 50px;
  width: 100px;
  background-color: #133382;
  position: absolute;
  border: #133382 1px solid;
  left: -2px;
}
.searchBox {
  display: block;
  position: relative;
  height: 80px;
  width: 900px;
  top: -100px;
  left: 50%;
  margin-left: -450px;
  line-height: 80px;
}
.paperList {
  position: absolute;
  width: 600px;
  height: 400px;
  left: 50%;
  top: 500px;
  margin-left: -300px;
  border: #d3dce6 1px solid;
  box-shadow: 2px 2px 5px #d3dce6;
  overflow-x: hidden;
  overflow-y: auto;
  border-radius: 10px;
}
.paperItem {
  display: block;
  width: 100%;
  height: 60px;
  border: #d3dce6 1px solid;
  line-height: 60px;
}
.crawlButton {
  position: absolute;
  width: 50px;
  height: 50px;
  top: 430px;
  right: 500px;
  background-color: #133382;
  line-height: 50px;
  color: #ffffff;
  border-radius: 10em;
}
.crawlButton:hover {
  width: 200px;
}
</style>
