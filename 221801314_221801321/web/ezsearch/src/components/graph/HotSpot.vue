<template>
  <div id="hotspot" :style="{ width: '1200px', height: '900px' }"></div>
</template>
<script>
import { inject, onMounted } from "vue";
import { defineComponent } from "vue";
export default defineComponent({
  name: "HotSpot",
  setup() {
    let echarts = inject("ec"); //引入
    onMounted(() => {
      //需要获取到element,所以是onMounted的Hook
      let myChart = echarts.init(document.getElementById("hotspot"));
      // 绘制图表
      myChart.setOption({
        legend: {},
        tooltip: {
          trigger: "axis",
          showContent: false,
        },
        dataset: {
          source: [
            ["paper", "2014", "2015", "2016", "2017", "2018", "2019", "2020"],
            ["深度学习", 56, 82, 88, 70, 53, 85, 90],
            ["姿态估计", 51, 51, 55, 53, 73, 68, 60],
            ["对象检测", 40, 62, 69, 36, 45, 32, 20],
            ["目标跟踪", 25, 37, 41, 18, 34, 49, 40],
            ["图像编辑", 20, 45, 65, 53, 63, 58, 45],
            ["3D形状生成", 10, 35, 81, 32, 68, 28, 35],
            ["高效CNN", 35, 32, 48, 29, 41, 61, 38],
            ["人脸检测", 15, 52, 25, 66, 30, 25, 65],
            ["数据分析", 45, 15, 62, 10, 82, 21, 49],
          ],
        },
        xAxis: { type: "category" },
        yAxis: { gridIndex: 0 },
        grid: { top: "55%" },
        series: [
          {
            type: "line",
            smooth: true,
            seriesLayoutBy: "row",
            emphasis: { focus: "series" },
          },
          {
            type: "line",
            smooth: true,
            seriesLayoutBy: "row",
            emphasis: { focus: "series" },
          },
          {
            type: "line",
            smooth: true,
            seriesLayoutBy: "row",
            emphasis: { focus: "series" },
          },
          {
            type: "line",
            smooth: true,
            seriesLayoutBy: "row",
            emphasis: { focus: "series" },
          },
          {
            type: "line",
            smooth: true,
            seriesLayoutBy: "row",
            emphasis: { focus: "series" },
          },
          {
            type: "line",
            smooth: true,
            seriesLayoutBy: "row",
            emphasis: { focus: "series" },
          },
          {
            type: "line",
            smooth: true,
            seriesLayoutBy: "row",
            emphasis: { focus: "series" },
          },
          {
            type: "line",
            smooth: true,
            seriesLayoutBy: "row",
            emphasis: { focus: "series" },
          },
          {
            type: "line",
            smooth: true,
            seriesLayoutBy: "row",
            emphasis: { focus: "series" },
          },
          {
            type: "pie",
            id: "pie",
            radius: "30%",
            center: ["50%", "25%"],
            emphasis: { focus: "data" },
            label: {
              formatter: "{b}: {@2014} ({d}%)",
            },
            encode: {
              itemName: "paper",
              value: "2014",
              tooltip: "2014",
            },
          },
        ],
      });
      window.onresize = function () {
        //自适应大小
        myChart.resize();
      };
    });
  },
  mounted() {},
});
</script>
<style scoped>
</style>
