<template>
  <div class="mySideBar">
    <div>
      <input
        type="text"
        placeholder="请输入论文题目,关键词,摘要进行模糊搜索"
        class="mySideBar-input"
        v-model="content"
      />
      <button class="mySideBar-search" @click="search">
        <i class="fa fa-search" aria-hidden="true"></i>
      </button>
      <div class="mysidebarlist">
        <el-tabs
          type="border-card mysearchtab"
          :stretch="true"
          v-model="sidebarPage"
          @tab-click="ChangePagenation"
        >
          <el-tab-pane label="全部论文" name="page1">
            <div class="paperlisttitle">论文列表</div>
            <div v-if="loadingFinished"><i class="el-icon-loading" style="font-size:30px"></i></div>
            <ul
              style="
                height: 750px;
                list-style: none;
                position: absolute;
                top: 40px;
                left: 10px;
                overflow-y: auto;
                overflow-x: hidden;
              "
              id="#list"
            >
              <li
                v-for="(item, index) in paperList"
                class="paperlistitem"
                :key="index"
              >
                <el-card shadow="hover" style="line-height: 100%">
                  <el-tooltip
                    :content="item.title"
                    effect="light"
                    :open-delay="500"
                  >
                    <span
                      style="
                        width: 250px;
                        height: 30px;
                        position: absolute;
                        left: 10px;
                        text-align: left;
                        text-overflow: ellipsis;
                        overflow: hidden;
                        white-space: nowrap;
                        font-size: 15px;
                      "
                      >{{ item.title }}</span
                    ></el-tooltip
                  >
                  <i
                    class="fa fa-trash"
                    aria-hidden="true"
                    style="position: absolute; left: 80%; cursor: pointer"
                    @click="deleteItem(item.id, index)"
                  ></i>
                </el-card>
              </li>
              <li class="paperlistitem" v-if="!loadingFinished">
                <el-card shadow="hover" @click.native="addItem()">
                  <i
                    class="fa fa-plus"
                    aria-hidden="true"
                    style="
                      color: #d3dce6;
                      margin-left: 80px;
                      margin-top: -5px;
                      font-size: 30px;
                    "
                  ></i>
                </el-card>
              </li>
            </ul>
          </el-tab-pane>
          <el-tab-pane label="搜索结果" name="page2"
            ><div class="paperlisttitle">结果列表</div>
            <ul
              style="
                height: 700px;
                list-style: none;
                position: absolute;
                top: 40px;
                left: 10px;
                overflow-y: auto;
                overflow-x: hidden;
              "
            >
              <li
                v-for="(item, index) in resultList"
                class="paperlistitem"
                :key="index"
              >
                <el-card shadow="hover">
                  <el-tooltip
                    :content="item.title"
                    effect="light"
                    :open-delay="500"
                  >
                    <span
                      style="
                        width: 250px;
                        height: 30px;
                        position: absolute;
                        left: 10px;
                        text-align: left;
                        text-overflow: ellipsis;
                        overflow: hidden;
                        white-space: nowrap;
                        font-size: 15px;
                      "
                      >{{ item.title }}</span
                    ></el-tooltip
                  >
                </el-card>
              </li>
            </ul>
          </el-tab-pane>
        </el-tabs>
      </div>
    </div>
    <el-dialog :visible.sync="addDialogVisible">
      <span>请输入你要添加的题目：</span>
      <el-input v-model="addPaperTitle" style="width: 400px"></el-input>
      <el-button type="primary" @click="addPaper">确认</el-button>
      <el-button @click="cancelpaper">取消</el-button>
    </el-dialog>
  </div>
</template>
<script>
export default {
  name: "sidebar",
  data() {
    return {
      loadingFinished:true,
      paperList: [],
      resultList: [],
      sidebarPage: "page1",
      addDialogVisible: false,
      addPaperTitle: "",
      content: "",
    };
  },
  mounted() {
    this.getsidebarpaperlist();
  },
  methods: {
    ChangePagenation: function (tab, event) {
      if (tab.index == 0) {
        sessionStorage.setItem("searchContent", "");
        this.$emit("ChangeToFullResult");
      } else {
        if(this.content!="")
        {
          this.search();
        }
      }
    },
    getsidebarpaperlist() {
      let _this = this;
      this.$message({
        message: "左侧用户论文列表加载中",
      });
      this.$axios
        .get(_this.$api.globalUrl + "/userPaper/all", {
          params: {},
        })
        .then(function (response) {
          _this.$message({
            message: "加载成功",
            type: "success",
          });
          _this.loadingFinished=false;
          console.log(response);
          sessionStorage.setItem("papernum", response.data.data.length);
          response.data.data.forEach((element) => {
            let newitem = {};
            newitem.id = element.id;
            newitem.title = element.title;
            _this.paperList.push(newitem);
          });
        })
        .catch(function (error) {
          console.log(error);
          _this.$message.error("用户论文列表加载失败");
        });
    },
    cancelpaper: function () {
      this.addDialogVisible = false;
      this.addPaperTitle = "";
    },
    addPaper: function () {
      this.addDialogVisible = false;
      let newTitle = {};
      let _this = this;

      this.$axios
        .get(_this.$api.globalUrl + "/userPaper/add", {
          params: {
            titleOrigin: _this.addPaperTitle,
          },
        })
        .then(function (response) {
          console.log(response);
          _this.getsidebarpaperlist();
        })
        .catch(function (error) {
          console.log(error);
          _this.$message.error("用户论文添加失败");
        });
      this.addPaperTitle = "";
    },
    search: function () {
      let _this = this;
      this.sidebarPage = "page2";
      let sContent = _this.content;
      this.$axios
        .get(_this.$api.globalUrl + "/userPaper/search", {
          params: {
            originContent: sContent,
          },
        })
        .then(function (response) {
          console.log(response);
          response.data.data.forEach((element) => {
            let newitem = {};
            newitem.id = element.id;
            newitem.title = element.title;
            _this.resultList.push(newitem);
          });
          sessionStorage.setItem("searchContent", sContent);
          _this.$emit("GetPagePaperList");
        })
        .catch(function (error) {
          console.log(error);
          _this.$message.error("搜索失败");
        });
      _this.content = "";
    },
    deleteItem(value, index) {
      let _this = this;
      this.$axios
        .get(_this.$api.globalUrl + "/userPaper/delete", {
          params: {
            paperId: value,
          },
        })
        .then(function (response) {
          console.log(response);
          _this.paperList.splice(index, 1);
          let num = sessionStorage.getItem("papernum") - 1;
          sessionStorage.setItem("papernum", num);
          _this.$emit("ResetPage");
        })
        .catch(function (error) {
          console.log(error);
          _this.$message.error("删除失败");
        });
    },
    addItem: function () {
      this.addDialogVisible = true;
    },
    paperlistChange: function (paperlistpage) {
      this.paperlistPage = paperlistpage;
    },
    resultlistChange: function (resultlistpage) {
      this.resultlistPage = resultlistpage;
    },
  },
};
</script>

<style scoped>
.mySideBar {
  width: 400px;
  position: absolute;
  height: 100%;
  border: #d3dce6 1px solid;
  box-shadow: 1px 1px 5px #d3dce6;
}
.mySideBar-input {
  width: 80%;
  height: 30px;
  position: absolute;
  top: -2px;
  left: 2px;
  border: #133382 2px solid;
  margin: 0;
}
.mySideBar-search {
  height: 34px;
  width: 20%;
  position: absolute;
  color: #ffffff;
  border: #133382 2px solid;
  background: #133382;
  right: 0px;
  top: -2px;
  border-top-right-radius: 5px;
  border-bottom-right-radius: 5px;
  font-size: 20px;
}
.mySideBarMenu {
  position: absolute;
  width: 100%;
  top: 33px;
  background-color: #5677fc;
  box-shadow: 0px 2px 2px #d3dce6;
}
.el-menu-item:hover {
  background-color: rgba(0, 0, 0, 0) !important;
  color: #d3dce6 !important;
}
.el-menu-item:focus {
  background-color: #5677fc !important;
}
.el-menu-item {
  color: #ffffff !important;
}
.el-menu-item.is-active {
  border-bottom-color: yellow !important;
}
.papercard {
  width: 180px;
  height: 40px;
}
.papercard div {
  position: absolute;
  left: -5px;
  top: 50%;
  margin-top: -15px;
  width: 140px;
  height: 30px;
  line-height: 30px;
}
.paperlisttitle {
  position: absolute;
  top: -10px;
  font-size: 20px;
  left: 5px;
  height: 50px;
  color: #133382;
  line-height: 50px;
}
.paperlistitem {
  float: left;
  width: 450px;
  height: 50px !important;
  margin-top: 5px;
  margin-left: -100px;
}
.el-card {
  height: 50px;
  line-height: 10px;
}
.el-tabs {
  position: absolute;
  width: 100%;
  height: 800px;
  top: 50px;
}
.el-tab-pane {
  height: 800px;
}
/deep/ .el-tabs_item {
  font-size: 30px !important;
}
</style>
