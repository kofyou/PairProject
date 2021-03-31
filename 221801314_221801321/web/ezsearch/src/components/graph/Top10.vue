<template>
  <div id="top10" :style="{ width: '1200px', height: '800px' }"></div>
</template>
<script>
import {
  inject,
  onMounted,
  mounted,
  defineComponent,
  getCurrentInstance,
} from "vue";
import WordCloud from "@/components/graph/WordCloud.vue";
export default defineComponent({
  name: "Top10",
  components: {
    WordCloud,
  },
  data() {
    return {
       myArray: new Array(),
    }
  },
  methods:{ 
    querySearch () {
      let echarts = inject("ec"); //引入
      const { ctx } = getCurrentInstance();
      ctx.$http.get("/word/top", {}).then((data) => {
        console.log(data);
        for (let i = 0; i < data.length; i++) {
          let array = new Array();
          array.push(data[i].keyword);
          array.push(data[i].num);
          this.myArray.push(array);
        }
        //需要获取到element,所以是onMounted的Hook
        let myChart = echarts.init(document.getElementById("top10"));
        // 绘制图表
        myChart.setOption({
          dataset: [
            {
              // dimensions: ["name", "age", "profession", "score", "date"],
              dimensions: ["keyword", "num"],
              source: this.myArray,
            },
            {
              transform: {
                type: "sort",
                config: { dimension: "num", order: "desc" },
              },
            },
          ],
          tooltip: {
            show: true,
          },
          xAxis: {
            type: "category",
            axisLabel: { interval: 0, rotate: 30 },
          },
          yAxis: {},
          series: {
            type: "bar",
            encode: { x: "keyword", y: "num" },
            datasetIndex: 1,
          },
        });
        myChart.on("click", function (params) {
          var keyword = params.data[0];
          var href = window.location.origin;
          href += "/home"
          href += "?address=0"
          href += "&title="
          href += keyword;
          window.location.href = href;
          console.log(href);
        });
        window.onresize = function () {
          //自适应大小
          myChart.resize();
        };
      });
    },
  },
  created() {
    this.querySearch();
  },
  setup() {
  },
});
</script>
<style scoped>
</style>
