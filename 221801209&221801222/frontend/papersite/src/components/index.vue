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
      paperItemArr : []
    }
  },
  components: {
    Base
  },
  methods :{
    doSearch(searchWord){
      console.log(searchWord);
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
  width: 80%;
  margin-top: 3%;
  margin-left: auto;
  margin-right: auto;
  background: RGBA(229,217,215,0.6);
  min-height: 500px;
  box-shadow: 0 1px 1px !important;
  border-radius: 15px;
}

</style>
