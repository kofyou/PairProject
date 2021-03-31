<template>
  <div id = "starlist" class = "root-div">
    <Base type="starlist"></Base>
    <div class = "main">

      <div id = "search-div">
        <!--            搜索框和搜索按钮-->
        <el-row :gutter="20">
          <el-col :span="4" :offset="14">
            <el-input v-model="searchWord" placeholder="输入检索条件"  clearable></el-input>
          </el-col>
          <el-col :span="2">
            <el-button type="primary" icon="el-icon-search" @click = "doSearch(searchWord)">检索论文</el-button>
          </el-col>
          <el-col :span="1" :offset="1">
            <el-button type="primary" icon="el-icon-refresh-right" @click="doRefresh" circle></el-button>
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
              width="60">
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
              width="80">
            </el-table-column>
            <el-table-column
              prop="keywords"
              label="关键字"
              width="120">
            </el-table-column>
            <el-table-column
              prop="type"
              label="类型"
              width="80">
            </el-table-column>
            <el-table-column
              prop="no"
              label="发表时间"
              sortable
              width="90">
            </el-table-column>
            <el-table-column
              prop="abstract"
              label="摘要"
              width="440">
            </el-table-column>
            <el-table-column

              label="操作"
              width="100">
              <!--              fixed="right"-->
              <template slot-scope="scope">
                <el-button icon="el-icon-search" @click="goToOriWeb" circle></el-button>
                <el-button type="danger" icon="el-icon-delete"  @click="doUnStar" circle></el-button>
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
              :total="tableMes.totalItem"
              style="background-color: #f9fafc !important;">
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
  watch:{
    tableData:{
      handler (val, oldVal){
        this.handleSizeChange(this.tableMes.eachPageItem);
      }
    }
  },
  components: {
    Base
  },
  mounted(){    //初始化界面
    this.doRefresh();
  },
  methods :{
    /*搜索响应函数*/
    doSearch(searchWord){
      searchWord = searchWord.toString().toLowerCase();
      let midTableData = this.tableData.slice(0);
      let cnt = midTableData.length;
      this.tableData.splice(0, cnt);  //清空列表
      for(let i = 0; i < cnt; i++)
      {
        if(midTableData[i]['aid'].toString() === searchWord || midTableData[i]['author'].toLowerCase().indexOf(searchWord) !== -1 || midTableData[i]['title'].toLowerCase().indexOf(searchWord) !== -1 || midTableData[i]['keywords'].toLowerCase().indexOf(searchWord) !== -1)
        {
            this.tableData.push(midTableData[i]);
        }
        this.tableMes.totalItem = this.tableData.length;
      }
      if(this.tableData.length === 0)   //收藏列表没有符合的
      {
        const that = this;
        this.axios.get('/search?title=' + searchWord + '&keyword=' + searchWord)
          .then(
            function (response){
              that.tableData = response.data.article;
              that.tableMes.totalItem = that.tableData.length;
              for(let i = 0; i < that.tableMes.totalItem; i++) {
                if (that.tableData[i]["author"] == "[]")
                  that.tableData[i]["author"] = "无";
                if (that.tableData[i]["no"] == "[]" || that.tableData[i]["no"] == null)
                  that.tableData[i]["no"] = "无";
              }
              that.handleSizeChange(that.tableMes.eachPageItem);
              that.alertMes("收藏列表中没有符合搜索条件的论文，已显示从数据库中检索的论文！");
            }
          ).catch(
          function (error){
            console.log(error);
          }
        );
      }
    },
    doRefresh(){   //更新（初始化）页面
      const that = this;
      this.axios.get("/star/list", {withCredentials: true,
        headers: {'Access-Control-Allow-Origin': 'http://localhost:8080',
        },
        credentials: 'include'
      })
        .then(
          function (response){
            const thatThat = that;
            thatThat.tableData.slice(0, thatThat.tableData.length);
            thatThat.displayedTableData.slice(0, thatThat.displayedTableData.length);
            let articleIds = response.data.starlist;
            that.tableMes.totalItem = articleIds.length;
            let cnt = that.tableMes.totalItem;
            for(let i = 0; i < cnt; i++) {
              that.axios.get("/article/" + articleIds[i]).then(
                function (response2){
                  if(response2.data.code !== "0"){
                    let myArticle = response2.data.article;
                    if (myArticle["author"] == "[]");
                    myArticle["author"] = "无";
                    if (myArticle["no"] == "[]" || myArticle["no"] == null)
                      myArticle["no"] = "无";
                    thatThat.tableData.push(myArticle);
                    thatThat.tableMes.totalItem++;
                  }
                }
              ).catch(function (error){
                console.log(error);
              })
            }
          }
        ).catch(
        function (error){
          console.log(error);
        }
      );
    },
    /*取消关注监听事件*/
    doUnStar(){
      let t;
      const that = this;
      clearTimeout(t)
      t = setTimeout(function (){
        that.axios.post('/star/delete', {
          aid: that.selectedAId
        }, {withCredentials: true})
          .then(
            function (response) {
              // if(response.data.code == '0') {
                let cnt = that.tableData.length;  //删除数据
                for(let i = 0; i < cnt; i++) {
                    if (that.tableData[i]['aid'] == that.selectedAId) {
                      let mid = that.tableData[i];
                      that.tableMes.totalItem -= 1;
                      that.tableData.splice(i, 1);
                      break;
                  }
                }
              // }
            })
          .catch(
            function (error) {
              console.log(error);
            });
      }, 1000);
    },
    goToOriWeb(){   /*跳转*/
      let t;
      let url;
      const that = this;
      clearTimeout(t)
      t = setTimeout(function (){
        for(let i = 0; i < that.displayedTableData.length; i++) {
          if(that.displayedTableData[i]['aid'] == that.selectedAId) {
            url = that.displayedTableData[i]['url'];
            break;
          }
        }
        if(url != "" && url != null){
          // console.log(url)
          window.open(url, '_blank');
        }
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
      this.tableMes.current_page = 1;
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
