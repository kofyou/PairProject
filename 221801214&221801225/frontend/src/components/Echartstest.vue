<template>
  <div id="panel">
    <div id="myChart" :style="{ width: '800px', height: '700px' }">
      <!-- <button class="aaaa" @click="aaa">afasdsa</button> -->
    </div>
    <!-- <div class="show">
      <h1>{{ msg }}</h1>
    </div> -->
  </div>
</template>

<script>
import eventVue from "@/assets/eventVue.js";
import axios from "axios";
import { color } from "echarts";
const echarts = require("echarts");
export default {
  name: "Chart",
  data() {
    return {
      msg: "热词分析",
      data1: [],
      data2: [1, 2],
    };
  },
  methods: {
    drawLine() {
      // 基于准备好的dom，初始化echarts实例
      const myChart = echarts.init(document.getElementById("myChart"));
      // 绘制图表
      myChart.setOption({
        title: {
          text: this.msg,
          textStyle: {
            fontSize: 25,
            color: "black",
          },
        },
        tooltip: {},
        xAxis: {
          data: this.data1,
          type: "category",
          axisLine: {
            lineStyle: {
              color: "black",
            },
          },
        },
        yAxis: {
          type: "value",
          axisLine: {
            lineStyle: {
              color: "black",
            },
          },
        },
        series: [
          {
            name: "关键词频数",
            type: "bar",
            data: this.data2,
            itemStyle: { normal: { color: "#9a60b4" } },
          },
        ],
        this: myChart.on("click", (param) => {
          console.log(param.name);
          // this.$router.push({
          //   path: "/show",
          // });
          this.$router.replace({name: 'showview', params: {id: param.name}})
        }),
      });
    },
  },
  mounted() {
    const _this = this;
    axios({
      url: "http://localhost:8080/keyword",
    }).then((res) => {
      for (let i = 0; i < 10; i++) {
        // console.log(res.data[i].count);
        // console.log(res)
        _this.data2[i] = res.data[i].count;
        _this.data1[i] = res.data[i].content;
      }
      console.log(_this.data1);
      console.log(_this.data2);
      this.drawLine();
    });
    // this.$nextTick(this.drawLine());
  },
};
</script>

<style lang="less" scoped>
#panel {
  background-image: url(https://images.cnblogs.com/cnblogs_com/lzklzk/1952300/o_210325063128u=3078691788,1881157151&fm=26&gp=0.jpg);
  // background-color: white;
  height: 1000px;
  width: 100%;
  #myChart {
    margin-left: 150px;
    padding-top: 80px;
  }
}
</style>
