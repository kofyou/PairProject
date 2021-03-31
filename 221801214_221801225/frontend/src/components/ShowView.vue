<template>
  <div class="showView">
    <div class="search">
      <el-select v-model="value" placeholder="请选择" class="selfbutton">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        >
        </el-option>
      </el-select>

      <input class="searchBox" type="text" v-model="searchtext" />
      <button class="click" @click="search1">搜索</button>
    </div>
    <PaperItem v-for="item in listArr1" :key="item.paper_id" :itemObj="item" />
    <div class="changePage">
      <button class="lastpage1" @click="lastpage">上一页</button>
      <button class="nextpage1" @click="nextpage">下一页</button>
    </div>
  </div>
</template>

<script>
import PaperItem from "./PaperItem";
import axios from "axios";
export default {
  data() {
    return {
      gotop: false,
      searchtext: "",
      current: 1,
      selectwhat: 1,
      options: [
        {
          value: "题目",
        },
        {
          value: "摘要",
        },
        {
          value: "关键词",
        },
      ],
      value: "题目",
      listArr2: [
        {
          paper_id: "",
          title: "",
          link: "",
          magazine: "",
          publication_year: "",
          abstracted: "",
          keyword: [],
        },
      ],
      listArr1: [
        {
          paper_id: "",
          title: "",
          link: "",
          magazine: "",
          publication_year: "",
          abstracted: "",
          keyword: [],
        },
      ],
    };
  },
  mounted() {
    //此处true 需要加上 不加滚动事件可能绑定不成功
    window.addEventListener("scroll", this.handleScroll, true);
  },
  components: {
    PaperItem,
  },
  created() {
    axios({
      url: "http://localhost:8080/changepage",
    }).then((res) => {
      this.listArr2 = res.data;
      this.listArr1 = this.listArr2.slice(0, 5);
    });
  },
  methods: {
    handleScroll() {
      let scrolltop =
        document.documentElement.scrolltop || document.body.scrollTop;
      scrolltop > 30 ? (this.gotop = true) : (this.gotop = false);
    },
    search1() {
    },
    nextpage() {
      let top = document.documentElement.scrollTop || document.body.scrollTop;
      //实现滚动效果
      const timeTop = setInterval(() => {
        document.body.scrollTop = document.documentElement.scrollTop = top -= 50;
        if (top <= 0) {
          clearInterval(timeTop);
        }
      }, 10);
      this.current++;
      if (this.selectwhat == 1) {
        this.listArr1 = this.listArr2.slice(
          this.current * 5 - 5,
          this.current * 5
        );
      } else if (this.selectwhat == 2) {
        this.listArr1 = this.listArr2
          .filter((item, index) => item.title.includes(this.searchtext))
          .slice(this.current * 5 - 5, this.current * 5);
      } else if (this.selectwhat == 3) {
        this.listArr1 = this.listArr2
          .filter((item, index) => item.abstracted.includes(this.searchtext))
          .slice(this.current * 5 - 5, this.current * 5);
      } else {
        this.listArr1 = this.listArr2
          .filter((item, index) => item.keyword.indexOf(this.searchtext) > -1)
          .slice(this.current * 5 - 5, this.current * 5);
      }
    },
    lastpage() {

    },
  },
};
</script>

<style lang="less" scoped>
.showView {
  padding: 30px;
  // background-color: black;
  background-image: url(https://images.cnblogs.com/cnblogs_com/lzklzk/1952300/o_210325063128u=3078691788,1881157151&fm=26&gp=0.jpg);
  .search {
    display: flex;
    // border: 1px solid #41b883;
    justify-content: space-between;
    align-items: center;

    border-radius: 5px;
    // background-color: white;
    // background-image: url(https://images.cnblogs.com/cnblogs_com/lzklzk/1952300/o_2103250632091.jpg);
    // height: 150px;
    /deep/.selfbutton {
      display: inline-block;
      font-size: 20px;
      height: 40px;
      width: 140px;
      margin-left: 150px;
      // border: 5px solid #41B883;
    }
    /deep/.el-input__inner {
      // background-color: #fff;
      background-image: none;
      border-radius: 4px;
      border: 1px solid #dcdfe6;
      box-sizing: border-box;
      color: #131314;
      display: inline-block;
      font-size: 20px;
      height: 40px !important;
      line-height: 55px !important;
      width: 100%;
    }
    .el-select-dropdown__item {
      color: #dcdfe6;
    }
    .searchBox {
      height: 40px;
      width: 600px;
      font-size: 20px;
      // border: 5px solid #41B883;
    }
    .click {
      height: 40px;
      width: 60px;
      margin-right: 150px;
      background-color: rgb(22, 173, 153);
      font-size: 18px;
      //   margin : 0 auto;
    }
  }
  .changePage {
    // border: 1px solid #41b883;
    padding-left: 500px;
    width: 100%;
    height: 40px;
    display: flex;
    // justify-content: space-between;
    .nextpage1 {
      height: 40px;
      width: 60px;
      font-size: 15px;
      color: white;
      background-color: rgba(5, 0, 2, 0.616);
    }
    .lastpage1 {
      background-color: rgba(5, 0, 2, 0.616);
      color: white;
      height: 40px;
      width: 60px;
      font-size: 15px;
    }
  }
}
</style>