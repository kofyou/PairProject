<template>
  <div id = "starlist" class = "root-div">
    <Base type="starlist"></Base>
    <div class = "main">

      <div id = "search-div">
        <!--            搜索框和搜索按钮-->
        <el-row :gutter="20">
          <el-col :span="4" :offset="15">
            <el-input v-model="searchWord" placeholder="输入检索条件"  clearable></el-input>
          </el-col>
          <el-col :span="2">
            <el-button type="primary" icon="el-icon-search" @click = "doSearch(searchWord)">检索论文</el-button>
          </el-col>
        </el-row>
      </div>


      <!-- 展示搜索结果 -->
      <div id = "show-paper-div">
        <!--卡片视图        -->
        <el-card class="box-card">
          <el-table
            :data="displayedTableData"
            border
            style="width: 100%"
            :default-sort = "{prop: 'time', order: 'descending'}"
            @row-click="getDetails"
          >
            <el-table-column
              fixed
              prop="aid"
              label="论文编号"
              sortable
              width="150">
            </el-table-column>
            <el-table-column
              prop="title"
              label="标题"
              sortable
              width="120">
            </el-table-column>
            <el-table-column
              prop="author"
              label="作者"
              sortable
              width="120">
            </el-table-column>
            <el-table-column
              prop="keywords"
              label="关键字"
              width="120">
            </el-table-column>
            <el-table-column
              prop="no"
              label="发表时间"
              sortable
              width="120">
            </el-table-column>
            <el-table-column
              prop="abstract"
              label="摘要"
              width="300">
            </el-table-column>
            <el-table-column
              fixed="right"
              label="操作"
              width="100">
              <template slot-scope="scope">
                <el-button icon="el-icon-search" @click="goToOriWeb" circle></el-button>
                <el-button type="warning" icon="el-icon-star-off"  @click="doStar" circle style="background-color: palegreen;"></el-button>
              </template>
            </el-table-column>
          </el-table>


          <!--        分页实现-->
          <div class="block">
            <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="tableMes.current_page"
              :page-sizes="[2, 3, 5, 10]"
              :page-size="tableMes.total_page"
              layout="total, sizes, prev, pager, next, jumper"
              :total="tableMes.totalItem">
            </el-pagination>
          </div>


        </el-card>

      </div>
    </div>
  </div>
</template>

<script>

import Base from '../components/base'
import axios from "axios";
import Router from "../router";

export default {
  name: "starlist",
  data() {
    return {
      searchWord : '',
      paperListHTML : '',
      page : 0,
      paperItemArr : [],
      tableData: [],
      displayedTableData: [],   /*正在展示的数据*/
      tableMes: {
        totalItem: 6,
        eachPageItem: 10,
        currentPage: 1,
        totalPage: 2
      },
    }
  },
  components: {
    Base
  },
  methods :{
    create(){
      const that = this;
      this.axios.get("/star/list")
        .then(
          function (response){
            console.log("内容");
            console.log(response);
            that.tableData = response.data.article;
            that.tableMes.totalItem = that.tableData.length;
            for(let i = 0; i < that.tableMes.totalItem; i++) {
              if (that.tableData[i]["author"] == "[]")
                that.tableData[i]["author"] = "无";
              if (that.tableData[i]["no"] == "[]" || that.tableData[i]["no"] == "[]" == null)
                that.tableData[i]["no"] = "无";
            }
          }
        ).catch(
        function (error){
          console.log(error);
        }
      );
    },
    /*搜索响应函数*/
    doSearch(searchWord){
      const that = this;
      this.axios.get("/star/list", {withCredentials: true})
        .then(
          function (response){
            console.log("内容");
            console.log(response);
            that.tableData = response.data.article;
            that.tableMes.totalItem = that.tableData.length;
            for(let i = 0; i < that.tableMes.totalItem; i++) {
              if (that.tableData[i]["author"] == "[]")
                that.tableData[i]["author"] = "无";
              if (that.tableData[i]["no"] == "[]" || that.tableData[i]["no"] == "[]" == null)
                that.tableData[i]["no"] = "无";
            }
            this.handleSizeChange(that.tableMes.eachPageItem);
          }
        ).catch(
        function (error){
          console.log(error);
        }
      );
      console.log(searchWord);
    },
    doStar(){
      let t;
      const that = this;
      clearTimeout(t)
      t = setTimeout(function (){
        console.log(that.selectedAId);
        that.axios.post('/apis/star/add', {
          aid: that.selectedAId
        }, {withCredentials: true})
          .then(
            function (response) {
              // if(response.data.code == '0') {
              //   // alert("登录成功！");
              //   Router.push({ path: 'index' });
              // }
              // else{
              //   alert('用户名与密码不匹配！');
              // }
              console.log(response);
            })
          .catch(
            function (error) {
              console.log(error);
            });
        console.log('执行了');
      }, 500);
    },
    goToOriWeb(){   /*跳转*/
      let t;
      let url;
      const that = this;
      clearTimeout(t)
      t = setTimeout(function (){
        console.log(that.selectedAId);
        for(let i = 0; i < that.displayedTableData.length; i++) {
          if(that.displayedTableData[i]['aid'] == that.selectedAId) {
            url = that.displayedTableData[i]['url'];
            break;
          }
        }
        if(url != "" && url != null){
          console.log(url)
          window.open(url, '_blank');
        }
        console.log('执行了');
      }, 500);

    },
    getDetails(row){
      this.selectedAId = row['aid'];
      // console.log(row['aid']);//此时就能拿到整行的信息
    },
    handleSizeChange(val) {
      /*console.log(`每页 ${val} 条`);*/
      this.tableMes.eachPageItem = val;
      this.tableMes.total_page = Math.ceil(this.tableMes.totalItem / this.tableMes.eachPageItem);
      this.tableMes.current_page = 1
      this.handleCurrentChange(this.tableMes.current_page);
    },
    handleCurrentChange(val) {
      /*console.log(`当前页: ${val}`);*/
      this.tableMes.currentPage = val;
      let itemCnt = this.tableData.length;
      /* 计算展示的数组左右值 */
      let leftIndex = (val - 1) * this.tableMes.eachPageItem;
      let rightIndex = val * this.tableMes.eachPageItem;

      /* 最后一页 */
      if( rightIndex > itemCnt)
        this.displayedTableData = this.tableData.slice(leftIndex);
      else
        this.displayedTableData = this.tableData.slice(leftIndex, rightIndex);

    }
  }
}
</script>

<style scoped>

.text {
  font-size: 14px;
}

.item {
  padding: 18px 0;
}

.box-card {
  width: 85%;
  margin-top: 3%;
  margin-left: auto;
  margin-right: auto;
  background: RGBA(102, 102, 102,0.9);
  min-height: 500px;
  box-shadow: 0 1px 1px !important;
  border-radius: 15px;
}

.block{
  margin-left: 20%;
  margin-top: 10px;
}


</style>
