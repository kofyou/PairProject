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
      <Paper :paper="item" v-for="item in papers" />
    </el-space>
    <Pagination @page="getPage" :total="total" />
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
  data() {
    return {
      title: "",
      year: "",
      meeting: "",
      page: 1,

      total: 50,
      papers: [],
    };
  },
  methods: {
    //获取输入框组件title
    getTitle(val) {
      console.log(val);
      this.title = val;
      // window.location.href = newUrl;
      // console.log(newUrl);
    },
    //获取高级搜索组件条件
    getYear(val) {
      console.log(val);
      this.year = val;
    },
    getMeeting(val) {
      console.log(val);
      this.meeting = val;
    },
    //获取分页组件当前页面
    getPage(val) {
      if (val === undefined) {
        val = 1;
      }
      // console.log("getPage");
      console.log(val);
      var title = this.getQueryString("title");
      var meeting = this.getQueryString("meeting");
      var year = this.getQueryString("years");

      var myUrl = this.setUrl(val, title, year, meeting);
      this.page = val;
      window.location.href = myUrl;
    },
    //搜索按钮事件
    search() {
      if (this.page === undefined || this.page === NaN) this.page = 1;
      // console.log(title);
      // console.log(year);
      // console.log(meeting);
      this.meeting === undefined ? null : this.meeting;
      this.year === undefined ? null : this.year;

      this.queryPaper();
    },

    //get请求查询title
    queryPaper() {
      var page = this.page - 1;
      // console.log(page);
      // var newUrl = "/paper/title"
      // var myUrl = this.setUrl(page,this.title,this.year,this.meeting);
      var newUrl = "";
      newUrl += "?address=";
      newUrl += page;
      newUrl += "&source=";
      newUrl += this.meeting;
      newUrl += "&title=";
      newUrl += this.title;
      newUrl += "&years=";
      newUrl += this.year;
      window.location.href = newUrl;
      var myUrl = "/paper/title";
      myUrl += newUrl;
      console.log(myUrl);
      this.ctx.$http.get(myUrl).then((data) => {
        this.papers = data;
        console.log(this.papers);
        // window.location.reload();
      });
    },

    setUrl(page, title, year, meeting) {
      var title = this.getQueryString("title");
      var meeting = this.getQueryString("meeting");
      var year = this.getQueryString("years");
      console.log(this.getQueryString("years"));
      if (meeting === null) {
        meeting = "";
      }
      if (year === null) {
        year = "";
      }
      console.log(page);
      var page = page - 1;
      var newUrl = "";
      newUrl += "?address=";
      newUrl += page;
      newUrl += "&source=";
      newUrl += meeting;
      newUrl += "&title=";
      newUrl += title;
      newUrl += "&years=";
      newUrl += year;
      return newUrl;
    },
    getUrl() {
      this.getQueryString("address");
    },
    getQueryString(name) {
      var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
      var r = window.location.search.substr(1).match(reg);
      if (r != null) {
        return unescape(r[2]);
      }
      return null;
    },
  },
  created() {
    // console.log("create")
    var title = this.getQueryString("title");
    var page = this.getQueryString("address");
    var meeting = this.getQueryString("meeting");
    var year = this.getQueryString("years");
    if (meeting === null) {
      meeting = "";
    }
    if (year === null) {
      year = "";
    }
    
    // console.log(page);
    // var newUrl = "/paper/title"
    var newUrl = "";
    newUrl += "?address=";
    newUrl += page;
    newUrl += "&source=";
    newUrl += meeting;
    newUrl += "&title=";
    newUrl += title;
    newUrl += "&years=";
    newUrl += year;
    var myUrl = "/paper/title";
    myUrl += newUrl;
    // newUrl += window.location.search;
    console.log(myUrl);
    this.ctx.$http.get(myUrl).then((data) => {
      this.papers = data;
      console.log(this.papers);
      // window.location.reload();
    });
  },
  setup(props, context) {
    const { ctx } = getCurrentInstance();
    return {
      ctx,
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
