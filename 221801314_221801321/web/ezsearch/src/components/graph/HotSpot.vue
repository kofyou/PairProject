<template>
  <div id="hotspot" :style="{ width: '1200px', height: '800px' }"></div>
</template>
<script>
import { inject, onMounted } from "vue";
import { defineComponent, getCurrentInstance } from "vue";
export default defineComponent({
  name: "HotSpot",
  setup() {
    const { ctx } = getCurrentInstance();
    const keywordList = [
      "Computer Vision",
      "Shape",
      "AI",
      "visual",
      "camera",
      "reconstruction",
      "robustness",
      "feature"
    ];
    const yearList = ["2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017"];
    var twoDimArray = new Array();
    const batchQuery = () => {
      // function batchQuery() {
      ctx.$http
        .post("/word/year", {
          keyword: keywordList,
          years: yearList,
        })
        .then((data) => {
          console.log(data);
          twoDimArray[0] = new Array();
          twoDimArray[0][0] = "keyword";
          for (let i = 0; i < keywordList.length; i++) {
            twoDimArray[0][i + 1] = yearList[i];
          }
          for (let i = 0; i < data.length; i++) {
            twoDimArray[i + 1] = new Array();
            twoDimArray[i + 1][0] = data[i].keyword;
            for (let j = 0; j < data[i].num.length; j++) {
              twoDimArray[i + 1][j + 1] = data[i].num[j];
            }
          }

          let myChart = echarts.init(document.getElementById("hotspot"));
          // 绘制图表
          myChart.setOption({
            legend: {},
            tooltip: {
              trigger: "axis",
              showContent: false,
            },
            dataset: {
              source: twoDimArray,
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
                  itemName: "keyword",
                  value: "2014",
                  tooltip: "2014",
                },
              },
            ],
          });
          myChart.on("updateAxisPointer", function (event) {
            var xAxisInfo = event.axesInfo[0];
            if (xAxisInfo) {
              var dimension = xAxisInfo.value + 1;
              myChart.setOption({
                series: {
                  id: "pie",
                  label: {
                    formatter: "{b}: {@[" + dimension + "]} ({d}%)",
                  },
                  encode: {
                    value: dimension,
                    tooltip: dimension,
                  },
                },
              });
            }
          });

          window.onresize = function () {
            //自适应大小
            myChart.resize();
          };
          console.log(twoDimArray);
        });
    };

    let echarts = inject("ec"); //引入
    onMounted(() => {
      batchQuery();
    });
  },
});
</script>
<style scoped>
</style>
