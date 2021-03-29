<template>
  <div class="mySideBar">
    <div>
      <input type="text" placeholder="请输入论文题目" class="mySideBar-input"></input>
      <button class="mySideBar-search" @click="search"><i class="fa fa-search" aria-hidden="true"></i></button>
      <div class="mysidebarlist">
        <el-tabs type="border-card mysearchtab" stretch="true" v-model="sidebarPage">
          <el-tab-pane label="全部论文" name="page1">
            <div  class="paperlisttitle">论文列表</div>
            <ul style="height:700px;list-style: none; position:absolute;top: 40px;left: 50%;margin-left: -90px;overflow-y:auto;overflow-x: hidden">
              <li v-for="(item,index) in paperList" class="paperlistitem" :key="index">
                <el-card shadow="hover">
                  <span style="position: absolute;left: 25%">{{index}}{{item}}</span>
                  <i class="fa fa-trash" aria-hidden="true" style="position: absolute;left: 75%;" @click="deleteItem(index)"></i>
                </el-card>
              </li>
              <li class="paperlistitem">
                <el-card shadow="hover" @click.native="addItem()">
                <i class="fa fa-plus" aria-hidden="true" style="color: #d3dce6" ></i>
              </el-card></li>
            </ul>
            </el-tab-pane>
          <el-tab-pane label="搜索结果" name="page2"><div class="paperlisttitle">结果列表</div><ul style="height:700px;list-style: none; position:absolute;top: 40px;left: 50%;margin-left: -90px;overflow-y: auto;overflow-x: hidden">
            <li v-for="(item,index) in resultList" class="paperlistitem" :key="index">
              <el-card shadow="hover">
                <span style="position: absolute;left: 25%">{{index}}{{item}}</span>
                <i class="fa fa-trash" aria-hidden="true" style="position: absolute;left: 75%;" @click="deleteItem(index)"></i>
              </el-card>
            </li></ul>
         </el-tab-pane>
        </el-tabs>
      </div>
    </div>
    <el-dialog :visible.sync="addDialogVisible">
       <span>请输入你要添加的题目：</span>
      <el-input v-model="addPaperTitle" style="width:500px"></el-input>
      <el-button type="primary" @click="addPaper">确认</el-button>
      <el-button  @click="cancelpaper">取消</el-button>
    </el-dialog>
  </div>
</template>
<script>
export default {
  name: "sidebar",
  data() {
    return {
      paperList: [
        "title1",
        "title2",
        "title2",
        "title2",
        "title2",
        "title2",
        "title2",
        "title2",
        "title2",
        "title2",
        "title2",
        "title2",
        "title2",
      ],
      resultList: [
        "result1",
        "result2",
        "result2",
        "result2",
        "result2",
        "result2",
        "result2",
        "result2",
        "result2",
        "result2",
        "result2",
        "result2",
        "result2",
      ],
      sidebarPage:"page1",
      addDialogVisible:false,
      addPaperTitle:""
    };
  },
  methods: {
    cancelpaper:function(){
      this.addDialogVisible=false;
      this.addPaperTitle='';
      },
    addPaper:function () {
      this.addDialogVisible=false;
    },
    search: function () {
      this.sidebarPage="page2";
    },
    deleteItem(value) {
      this.paperList.splice(value, 1);
    },
    addItem: function () {
      this.addDialogVisible=true;
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
  width: 200px;
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
  left: -2px;
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
  width: 180px;
  height: 50px !important;
  margin-top: 5px;
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
