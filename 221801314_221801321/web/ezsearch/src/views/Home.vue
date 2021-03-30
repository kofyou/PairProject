<template>
  <div class="block">
    <Header />
    <el-row :gutter="20" style="height: 40%">
      <el-col :span="12" :offset="3"><Carousel /></el-col>
      <el-col :span="6" :offset="0"><CarouselV /></el-col>
    </el-row>
    <div class="divider"></div>
    <div>
      <el-row :gutter="0">
        <!-- 搜索框 -->
        <el-col :span="5" :offset="0"></el-col>
        <el-col :span="12">
          <SearchBar @title="getTitle" />
        </el-col>
        <!-- 搜索按钮 -->
        <el-col :span="1" :push="0">
          <el-button
            type="primary"
            class="search-btn blue-background"
            style="height: 60px; font-size: 20px"
            @click="search"
            >搜索</el-button
          >
        </el-col>
        <!-- 高级搜索 -->
        <el-col :span="1" :push="0">
          <AdvanceSearch @year="getYear" @meeting="getMeeting" />
        </el-col>
        <el-col :span="5" :offset="0"></el-col>
      </el-row>
    </div>
    <div class="divider"></div>
    <el-space direction="vertical" :size="40">
      <Paper />
      <Paper />
      <Paper />
      <Paper />
      <Paper />
    </el-space>
    <Pagination @page="getPage" />
    <Footer />
  </div>
</template>
<script>
import {
  provide,
  defineComponent,
  ref,
  onMounted,
  getCurrentInstance,
} from "vue";
import Header from "@/components/common/Header.vue";
import Paper from "@/components/search/Paper.vue";
import Carousel from "@/components/search/Carousel.vue";
import CarouselV from "@/components/search/CarouselVertical.vue";
import SearchBar from "@/components/search/Search.vue";
import AdvanceSearch from "@/components/search/AdvanceSearch.vue";
import Pagination from "@/components/search/Pagination.vue";
import Footer from "@/components/common/Footer.vue";
export default defineComponent({
  name: "Home",
  components: {
    Header,
    Paper,
    Carousel,
    CarouselV,
    SearchBar,
    AdvanceSearch,
    Pagination,
    Footer,
  },
  setup() {
    const { ctx } = getCurrentInstance();
    var title = ref("");
    var year;
    var meeting;
    var page;
    //获取输入框组件title
    const getTitle = (val) => {
      title = val;
      console.log(title);
    };
    //获取高级搜索组件条件
    const getYear = (val) => {
      console.log(val);
      year = val;
    };
    const getMeeting = (val) => {
      console.log(val);
      meeting = val;
    };
    //获取分页组件当前页面
    const getPage = (val) => {
      page = val;
    };
    //搜索按钮事件
    const search = () => {
      if (page === undefined) page = 1;
      // console.log(title);
      // console.log(year);
      // console.log(meeting);
      meeting === undefined ? null : meeting;
      title.value === "" ? null : title.value;
      year === undefined ? null : year;
      console.log(
        JSON.stringify({
          address: page - 1,
          source: meeting,
          title: title,
          years: year,
        })
      );

      queryPaper();
    };
    //get请求查询title
    const queryPaper = () => {
      ctx.$http
        .get("/paper/title", {
          address: page - 1, //页数
          source: meeting,
          title: title,
          years: year,
        })
        .then((data) => {
          console.log(data);
          for (let i = 0; i < data.length; i++) {
            let name = "paper" + i;
          }
        });
    };
    return {
      title,
      year,
      meeting,
      page,
      getTitle,
      getYear,
      getMeeting,
      getPage,
      search,
      queryPaper,
    };
  },
});
</script>
<style>
.blue-background {
  background-color: #155263;
  border: 0px;
}

.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}

.divider {
  height: 80px;
}
.el-autocomplete {
  display: block;
}
input {
  height: 60px !important;
  border: 1px solid rgb(47, 47, 47) !important;
}
/* #search-input >>> .el-input__inner {
  height: 60px ;
  border: 1px solid rgb(47, 47, 47) !important;
} */
.search-btn {
  width: 100px;
  height: 60px;
}
</style>
