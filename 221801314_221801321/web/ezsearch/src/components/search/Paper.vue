<template>
  <div class="box-card" align="left">
    <div class="paper title" style="padding-bottom: 25px">
      <a
        :href="item.link"
        target="_blank"
        style="
          height: 65px;
          color: #000;
          font-weight: bold;
          font-size: 40px;
          overflow: hidden;
        "
      >
        {{ item.title }}
      </a>
      <div style="padding-top: 10px; padding-left: 5px">
        <span
          style="
            height: 20px;
            overflow: hidden;
            float: left;
            color: #222;
            padding-right: 5px;
          "
          >作者:
        </span>
        <span
          class="blue"
          style="
            width: 60%;
            height: 20px;
            float: left;
            color: #222;
            overflow: hidden;
          "
          >{{ item.authors }}</span
        >
        <span
          style="
            height: 20px;
            overflow: hidden;
            float: left;
            color: #222;
            padding-right: 5px;
          "
          >来源:</span
        >
        <span
          class="blue"
          style="
            width: 15%;
            height: 20px;
            float: left;
            color: #222;
            overflow: hidden;
          "
          >{{ item.source }}</span
        >
        <span
          style="
            height: 20px;
            overflow: hidden;
            float: left;
            color: #222;
            padding-right: 5px;
          "
          >发表时间:</span
        >
        <span
          class="blue"
          style="
            width: 10%;
            height: 20px;
            float: left;
            color: #222;
            overflow: hidden;
          "
          >{{ item.years }}</span
        >
      </div>
    </div>
    <el-divider></el-divider>
    <div class="paper body">
      <h2>摘要：</h2>
      <div style="height: 80px; padding-top: 5px; overflow: hidden">
        <span>{{ item.abstracts }}</span>
      </div>
      <div style="height: 10px"></div>
      <h3>关键词:</h3>
      <div style="height: 32px; padding-top: 5px; overflow: hidden">
        <span>{{ item.keyword }}</span>
      </div>
    </div>
    <!-- <div class="right">
      <el-button class="btns"></el-button>
      <el-button class="btns"></el-button>
      <el-button class="btns"></el-button>
    </div> -->
  </div>
</template>
<script>
import { defineComponent, reactive } from "vue";
export default defineComponent({
  name: "Paper",
  props: ["paper"],
  data() {
    return {
      item: this.paper,
    };
  },
  created() {
    // console.log(this.item);
  },
  setup() {},
});
</script>
<style>
.box-card {
  max-width: 1200px;
  width: 100%;
  height: 100%;
  /* margin: 20px; */
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  float: left;
  border-radius: 10px;
  padding: 5px;
  padding-top: 15px;
}
.paper {
  /* width: 80%; */
  height: 100%;
}
.title {
  background-color: #ccadbb;
  /* filter: blur(2px); */
  border-radius: 7px;
  padding-top: 10px;
  padding-bottom: 10px;
  padding-left: 20px;
  padding-right: 20px;
  margin-top: 5px;
  margin-left: 20px;
  margin-right: 20px;
  justify-content: flex-start;
}
.body {
  width: 90%;
  float: left;
  overflow: hidden;
  padding-left: 40px;
  padding-bottom: 20px;
}
.right {
  float: right;
  padding-bottom: 10px;
}
.btns {
  clear: both;
  display: block;
  margin-bottom: 5px;
  margin-left: 0px !important;
  margin-right: 20px;
}
.block {
  display: block;
}
.blue {
  color: rgb(98, 123, 155);
}
.paper-details {
  widows: 80%;
}
</style>
