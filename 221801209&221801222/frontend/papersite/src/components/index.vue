<template>
  <div id = "index" class = "root-div">
    <Base></Base>
    <div class = "main">

      <div id = "search-div">
        <!--            搜索框和搜索按钮-->
        <el-row :gutter="20">
          <el-col :span="10" :offset="6">
            <el-input v-model="searchWord" placeholder="输入检索条件"  clearable></el-input>
          </el-col>
          <el-col :span="6">
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
          >
            <el-table-column
              fixed
              prop="id"
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
              prop="keyWord"
              label="关键字"
              width="120">
            </el-table-column>
            <el-table-column
              prop="time"
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
                <el-button icon="el-icon-search" circle style="color: black !important; "></el-button>
                <el-button type="warning" icon="el-icon-star-off" circle style="background-color: palegreen;"></el-button>
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

export default {
  name: "index",
  data() {
    return {
      searchWord : '',
      paperListHTML : '',
      page : 0,
      paperItemArr : [],
      tableData: [{
        id: '1',
        title: 'A Convex Solution to Spatially-Regularized Correspondence Problems',
        author: 'James ben',
        keyWord: 'UAV tracking、UAV simulator、Aerial object tracking',
        type: 'ECCV',
        time: '17 September 2016"',
        abstract: "We propose a convex formulation of the correspondence problem between two images with respect to an energy function measuring data consistency and spatial regularity. To this end, we formulate the general correspondence problem as the search for a minimal two-dimensional surface in \\(\\mathbb {R}^4\\). We then use tools from geometric measure theory and introduce 2-vector fields as a representation of two-dimensional surfaces in \\(\\mathbb {R}^4\\). We propose a discretization of this surface formulation that gives rise to a convex minimization problem and compute a globally optimal solution using an efficient primal-dual algorithm."
      }, {
        id: '2',
        title: 'A Convex Solution to Spatially-Regularized Correspondence Problems',
        author: 'James ben',
        keyWord: 'UAV tracking、UAV simulator、Aerial object tracking',
        type: 'ECCV',
        time: '17 September 2016"',
        abstract: "We propose a convex formulation of the correspondence problem between two images with respect to an energy function measuring data consistency and spatial regularity. To this end, we formulate the general correspondence problem as the search for a minimal two-dimensional surface in \\(\\mathbb {R}^4\\). We then use tools from geometric measure theory and introduce 2-vector fields as a representation of two-dimensional surfaces in \\(\\mathbb {R}^4\\). We propose a discretization of this surface formulation that gives rise to a convex minimization problem and compute a globally optimal solution using an efficient primal-dual algorithm."
      }, {
        id: '3',
        title: 'A Convex Solution to Spatially-Regularized Correspondence Problems',
        author: 'James ben',
        keyWord: 'UAV tracking、UAV simulator、Aerial object tracking',
        type: 'ECCV',
        time: '17 September 2016"',
        abstract: "We propose a convex formulation of the correspondence problem between two images with respect to an energy function measuring data consistency and spatial regularity. To this end, we formulate the general correspondence problem as the search for a minimal two-dimensional surface in \\(\\mathbb {R}^4\\). We then use tools from geometric measure theory and introduce 2-vector fields as a representation of two-dimensional surfaces in \\(\\mathbb {R}^4\\). We propose a discretization of this surface formulation that gives rise to a convex minimization problem and compute a globally optimal solution using an efficient primal-dual algorithm."
      }, {
        id: '4',
        title: 'A Convex Solution to Spatially-Regularized Correspondence Problems',
        author: 'James ben',
        keyWord: 'UAV tracking、UAV simulator、Aerial object tracking',
        type: 'ECCV',
        time: '17 September 2016"',
        abstract: "We propose a convex formulation of the correspondence problem between two images with respect to an energy function measuring data consistency and spatial regularity. To this end, we formulate the general correspondence problem as the search for a minimal two-dimensional surface in \\(\\mathbb {R}^4\\). We then use tools from geometric measure theory and introduce 2-vector fields as a representation of two-dimensional surfaces in \\(\\mathbb {R}^4\\). We propose a discretization of this surface formulation that gives rise to a convex minimization problem and compute a globally optimal solution using an efficient primal-dual algorithm."
      }, {
        id: '5',
        title: 'A Convex Solution to Spatially-Regularized Correspondence Problems',
        author: 'James ben',
        keyWord: 'UAV tracking、UAV simulator、Aerial object tracking',
        type: 'ECCV',
        time: '17 September 2016"',
        abstract: "We propose a convex formulation of the correspondence problem between two images with respect to an energy function measuring data consistency and spatial regularity. To this end, we formulate the general correspondence problem as the search for a minimal two-dimensional surface in \\(\\mathbb {R}^4\\). We then use tools from geometric measure theory and introduce 2-vector fields as a representation of two-dimensional surfaces in \\(\\mathbb {R}^4\\). We propose a discretization of this surface formulation that gives rise to a convex minimization problem and compute a globally optimal solution using an efficient primal-dual algorithm."
      }, {
        id: '6',
        title: 'A Convex Solution to Spatially-Regularized Correspondence Problems',
        author: 'James ben',
        keyWord: 'UAV tracking、UAV simulator、Aerial object tracking',
        type: 'ICCV',
        time: '17 September 2016"',
        abstract: "We propose a convex formulation of the correspondence problem between two images with respect to an energy function measuring data consistency and spatial regularity. To this end, we formulate the general correspondence problem as the search for a minimal two-dimensional surface in \\(\\mathbb {R}^4\\). We then use tools from geometric measure theory and introduce 2-vector fields as a representation of two-dimensional surfaces in \\(\\mathbb {R}^4\\). We propose a discretization of this surface formulation that gives rise to a convex minimization problem and compute a globally optimal solution using an efficient primal-dual algorithm."
      }],
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
    doSearch(searchWord){
      this.handleSizeChange(this.tableMes.eachPageItem);
      console.log(searchWord);
    },
    handleClick(row) {
      console.log(row);
    },
    handleSizeChange(val) {
      /*console.log(`每页 ${val} 条`);*/
      this.tableMes.eachPageItem = val;
      this.tableMes.total_page = Math.ceil(this.tableMes.totalItem / this.tableMes.each_page_item);
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
  background: RGBA(229,217,215,0.6);
  min-height: 500px;
  box-shadow: 0 1px 1px !important;
  border-radius: 15px;
}

.block{
  margin-left: 20%;
  margin-top: 10px;
}


</style>
