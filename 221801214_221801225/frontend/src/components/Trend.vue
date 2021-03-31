<template>
  <div class="main1">
    <div id="main" style="width: 1200px; height: 400px"></div>
    <div class="changebu">
      <button class="CV" @click="clickEC">ECCV</button>
      <button class="CV" @click="clickIC">ICCV</button>
      <button class="CV" @click="clickPR">CVPR</button>
    </div>
  </div>
  <!--为echarts准备一个具备大小的容器dom-->
</template>

<script>
import axios from "axios";
import echarts from "echarts";
export default {
  name: "",
  data() {
    return {
      charts: "",
      msg:"ICCV论文年份趋势图",
      datax: [],
      datay: [],
      data2: [],
      data1: [],
      data3: [],
      data4: [],
      data5: [],
      data6: [],
    };
  },
  methods: {
    drawLine(id) {
      this.charts = echarts.init(document.getElementById(id));
      this.charts.setOption({
                title: {
          text: this.msg,
          textStyle: {
            fontSize: 25,
            color: "black",
          },
        },
        tooltip: {
          trigger: "axis",
        },
        legend: {
          data: ["按年份"],
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
          containLabel: true,
        },

        toolbox: {
          feature: {
            saveAsImage: {},
          },
        },
        xAxis: {
          type: "category",
          boundaryGap: false,
          data: this.datax,
        },
        yAxis: {
          type: "value",
        },

        series: [
          {
            name: "ICCV",
            type: "line",
            stack: "总量",
            data: this.datay,
          },
        ],
      });
    },
    clickEC() {
      this.datax = this.data4;
      this.datay = this.data3;
      this.msg="ECCV论文年份趋势图";
      this.$nextTick(function () {
        this.drawLine("main");
      });
    },
    clickIC() {
      this.datax = this.data2;
      this.datay = this.data1;
      this.msg="ICCV论文年份趋势图";
      this.$nextTick(function () {
        this.drawLine("main");
      });
    },
    clickPR() {
      this.datax = this.data6;
      this.datay = this.data5;
      this.msg="CVPR论文年份趋势图";
      this.$nextTick(function () {
        this.drawLine("main");
      });
    },
  },
  //调用
  mounted() {
    const _this = this;
    axios({
      url: "http://localhost:8080/magazine",
    }).then((res) => {
      let j = 0;
      let m = 0;
      let l = 0;
      for (let i = 1; i < 36; i++) {
        if (res.data[i].magezine == "ICCV") {
          _this.data2[j] = res.data[i].publication_year - 0;
          _this.data1[j] = res.data[i].number;
          j++;
        } else if (res.data[i].magezine == "ECCV") {
          _this.data3[m] = res.data[i].number;
          _this.data4[m] = res.data[i].publication_year - 0;
          m++;
        } else if (res.data[i].magezine == "CVPR") {
          _this.data5[l] = res.data[i].number;
          _this.data6[l] = res.data[i].publication_year - 0;
          l++;
        }
        _this.datax = _this.data6;
        _this.datay = _this.data5;
      }
      console.log(_this.data5);
      console.log(_this.data6);
      this.$nextTick(function () {
        this.drawLine("main");
      });
    });
  },
};
</script>

<style lang='less' scope>
 .main1{
  margin: 0;
  height: 1000px;
  padding: 0;
  padding-top: 200px;
  list-style: none;
  background-image: url(https://images.cnblogs.com/cnblogs_com/lzklzk/1952300/o_210325063128u=3078691788,1881157151&fm=26&gp=0.jpg);
}
.changebu {
  padding-left: 300px;
  display: flex;
  // border: 1px solid #41b883;
  justify-content: space-between;
  align-items: center;
  .CV {
    padding: auto 20px;
    height: 40px;
    width: 50px;
    font-size: 15px;
  }
}
</style>