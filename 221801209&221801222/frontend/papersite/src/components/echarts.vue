<template>
  <div id = "echarts">
    <div>

      <br><br>
      <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;开始年份：</span>
      <el-input
      type="text"
      placeholder="请输入开始年份"
      v-model="minYear"
      maxlength="4"
      show-word-limit
      style="width: 100px !important;"
      size="small"
      ></el-input>

      <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结束年份：</span>

      <el-input
        type="text"
        placeholder="请输入结束年份"
        v-model="maxYear"
        maxlength="4"
        show-word-limit
        style="width: 100px !important;"
        size="small"
      ></el-input>
      <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
      <el-button type="primary" round @click="getData">生成图表</el-button>

    </div>
    <div id="myChart2" :style="{width: '1100px', height: '600px'}"></div>
    <div id="myChart1" :style="{width: '1100px', height: '600px'}"></div>
    <div id="myChart3" :style="{width: '1100px', height: '600px'}"></div>
  </div>
</template>

<script>
import Router from "../router";
import {stringify} from "querystring";

export default {
  name: 'hello',
  data() {
    return {
      data11: [],
      data12: [],
      data2: [],
      minYear: '2000',
      maxYear: '2021'
    }
  },
  mounted() {
    this.getData();
    this.drawLine();
  },
  watch: {
    data11: {
      handler(val, oldVal) {
        this.drawLine();
      }
    }
  },
  methods: {

    getData() {
      const that = this;
      this.axios.post('top10', stringify({
          'min': this.minYear,
          'max': this.maxYear
        }
      ))
        .then(
          function (response) {
            if (response.data.code == '0') {
              let mes = response.data.top;
              let len = mes.length;
              let m = new Map();
              //清空原来数据
              that.data11 = [];
              that.data12 = [];
              that.data2 = [];
              for (let i = 0; i < len; i++) {
                that.data11.push(mes[i]['keyword']);
                that.data12.push(mes[i]['count']);
                m.set("value", parseInt(mes[i]['count']));
                m.set("name", mes[i]['keyword']);
                that.data2[i] = {value: mes[i]['count'], name: mes[i]['keyword']};
              }
              // console.log(that.data2);
            }
          })
        .catch(
          function (error) {
            console.log(error);
          });
    },
    drawLine() {
      // 基于准备好的dom，初始化echarts实例
      let myChart1 = this.$echarts.init(document.getElementById('myChart1'));
      let myChart2 = this.$echarts.init(document.getElementById('myChart2'));
      let myChart3 = this.$echarts.init(document.getElementById('myChart3'));
      // 绘制图表
      myChart1.setOption({
        title: {text: '年度Top10'},
        tooltip: {},
        xAxis: {
          data: this.data11
        },
        yAxis: {},
        series: [{
          name: 'Top10',
          type: 'bar',
          data: this.data12
        }]
      });

      myChart2.setOption({
          legend: {
            top: 'bottom'
          },
          title: {text: 'Top10玫瑰图'},
          toolbox: {
            show: true,
            feature: {
              mark: {show: true},
              dataView: {show: true, readOnly: false},
              saveAsImage: {show: true}
            }
          },
          series: [{
              name: 'top10',
              type: 'pie',
              radius: [50, 250],
              center: ['50%', '50%'],
              roseType: 'area',
              itemStyle: {
                borderRadius: 8
              },
              data: this.data2
            }
          ]
        }
      );


      let data = [];
      data.push(this.data2);
      data.push(this.data2);
      data.push(this.data2);
      data.push(this.data2);
      myChart3.setOption({
        series: [{
          title: {text: "热词词云图"},
          type: 'wordCloud',
          shape: 'circle',
          left: 'center',
          top: 'center',
          right: null,
          bottom: null,
          width: '70%',
          height: '80%',
          sizeRange: [12, 60],
          rotationRange: [-90, 90],
          rotationStep: 45,
          gridSize: 8,
          drawOutOfBound: false,
          textStyle: {
            normal: {
              fontFamily: 'sans-serif',
              fontWeight: 'normal'
            },
            emphasis: {
              shadowBlur: 10,
              shadowColor: '#333'
            }
          },
          data: this.data2
        }]
      });
    }
  }
}
</script>

<style scoped>
#echarts{
  background-color: #f9fafc;
  length: 50%;
  margin-left: 100px;
  margin-right: 100px;
  border-radius: 20px;
}

#myChart1,#myChart2,#myChart3{
  padding-top: 50px;
  margin-bottom: 100px;
  padding-left: 40px;
  padding-right: 40px;
}

el-input{

  display: block;
}

</style>
