<template>
  <div id="top10" :style="{ width: '1200px', height: '900px' }"></div>
</template>
<script>
import {
  inject,
  onMounted,
  mounted,
  defineComponent,
  getCurrentInstance,
} from "vue";
export default defineComponent({
  name: "HotSpot",
  setup() {
    const { ctx } = getCurrentInstance();
    let echarts = inject("ec"); //引入
    onMounted(() => {
      qS();

      //需要获取到element,所以是onMounted的Hook
      let myChart = echarts.init(document.getElementById("top10"));
      // 绘制图表
      myChart.setOption({
        dataset: [
          {
            dimensions: ["name", "age", "profession", "score", "date"],
            source: [
              [" Hannah Krause ", 41, "Engineer", 314, "2011-02-12"],
              ["Zhao Qian ", 20, "Teacher", 351, "2011-03-01"],
              [" Jasmin Krause ", 52, "Musician", 287, "2011-02-14"],
              ["Li Lei", 37, "Teacher", 219, "2011-02-18"],
              [" Karle Neumann ", 25, "Engineer", 253, "2011-04-02"],
              [" Adrian Groß", 19, "Teacher", null, "2011-01-16"],
              ["Mia Neumann", 71, "Engineer", 165, "2011-03-19"],
              [" Böhm Fuchs", 36, "Musician", 318, "2011-02-24"],
              ["Han Meimei ", 67, "Engineer", 366, "2011-03-12"],
            ],
          },
          {
            transform: {
              type: "sort",
              config: { dimension: "score", order: "desc" },
            },
          },
        ],
        xAxis: {
          type: "category",
          axisLabel: { interval: 0, rotate: 30 },
        },
        yAxis: {},
        series: {
          type: "bar",
          encode: { x: "name", y: "score" },
          datasetIndex: 1,
        },
      });
      window.onresize = function () {
        //自适应大小
        myChart.resize();
      };
    });
    function qS() {
      ctx.$http.get("/word/top", {}).then((data) => {
        console.log(data);
      });
    }
    const querySearch = () => {
      ctx.$http.get("/word/top", {}).then((data) => {
        console.log(data);
      });
    };
    return {
      //   querySearch,
      qS,
    };
  },
});
</script>
<style scoped>
</style>
