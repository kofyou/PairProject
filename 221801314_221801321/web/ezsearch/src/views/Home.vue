<template>
  <div class="block">
    <Header />
    <el-row :gutter="20">
      <el-col :span="12" :offset="3"><Carousel /></el-col>
      <el-col :span="6" :offset="0"><CarouselV /></el-col>
    </el-row>
    <div class="divider"></div>
    <div>
      <el-row :gutter="0">
        <!-- 搜索框 -->
        <el-col :span="5" :offset="0"></el-col>
        <el-col :span="12">
          <SearchBar/>
        </el-col>
        <!-- 搜索按钮 -->
        <el-col :span="1" :push="0">
          <el-button
            type="primary"
            class="search-btn blue-background"
            style="height: 60px; font-size:20px;"
            >搜索</el-button
          >
        </el-col>
        <!-- 高级搜索 -->
        <el-col :span="1" :push="0">
          <AdvanceSearch/>
        </el-col>
        <el-col :span="5" :offset="0"></el-col>
      </el-row>
    </div>
    <div class="divider"></div>
    <el-space direction="vertical">
      <Paper />
      <Paper />
      <Paper />
      <Paper />
      <Paper />
    </el-space>
    <Pagination />
    <Footer />
  </div>
</template>
<script>
import { defineComponent, ref } from "vue";
import Header from "@/components/common/Header.vue";
import Paper from "@/components/search/Paper.vue";
import Carousel from "@/components/search/Carousel.vue";
import CarouselV from "@/components/search/CarouselVertical.vue";
import SearchBar from "@/components/search/Search.vue";
import AdvanceSearch from "@/components/search/AdvanceSearch.vue"
import Pagination from "@/components/search/Pagination.vue"
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
    const path = require("@/assets/CVPR.jpg");
    const images = [
      { url: require("@/assets/CVPR.jpg") },
      { url: require("@/assets/ECCV.png") },
      { url: require("@/assets/ICCV.png") },
    ];
    return {
      input: ref(""),
      images,
      path,
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
