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
import WordCloud from "@/components/graph/WordCloud.vue"
export default defineComponent({
  name: "Top10",
  components: { 
      WordCloud,
  },
  setup() {
    const { ctx } = getCurrentInstance();
    let echarts = inject("ec"); //引入
    onMounted(() => {
        querySearch();
    });
    let myArray = new Array();
    const querySearch = () => {
      ctx.$http.get("/word/top", {}).then((data) => {
        console.log(data);
        for (let i = 0; i < data.length; i++) {
          let array = new Array();
          array.push(data[i].keyword);
          array.push(data[i].num);
          myArray.push(array);
        }
        //需要获取到element,所以是onMounted的Hook
        let myChart = echarts.init(document.getElementById("top10"));
        // 绘制图表
        myChart.setOption({
          dataset: [
            {
              // dimensions: ["name", "age", "profession", "score", "date"],
              dimensions: ["keyword", "num"],
              source: myArray,
            },
            {
              transform: {
                type: "sort",
                config: { dimension: "num", order: "desc" },
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
            encode: { x: "keyword", y: "num" },
            datasetIndex: 1,
          },
        });
        window.onresize = function () {
          //自适应大小
          myChart.resize();
        };
      });
    };
    return {
      querySearch,
      myArray,
    };
  },
});
</script>
<style scoped>
</style>
