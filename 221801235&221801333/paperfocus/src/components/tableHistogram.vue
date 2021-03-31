<template>
  <div>
    <ve-histogram :data="chartData" :events="chartEvents" style="margin-top:100px;"></ve-histogram>
    <!-- <v-tabletrend></v-tabletrend> -->
  </div>
</template>
 
<script>
import VeHistogram from 'v-charts/lib/histogram.common' // 柱状
import tableTrend from '@/components/tableTrend'
import axios from 'axios'
export default {
  components: { VeHistogram ,
  'v-tabletrend':tableTrend},
  data () {
    return {
        select:'',
        cnt:0,
        res:{},
        chartData: {
            columns: ['关键字', '总浏览量'],
            rows:[]
        },
        trendPerYear:[]
    }
  },
  mounted:function(){
    this.init();
  },
  methods:{
    autoPlay:function(){
      console.log('okkkkkk');
      console.log('this.cnt',this.cnt);
      this.cnt+=1;
      this.cnt%=5;
      for(var i=0;i<10;i++){i
          // console.log("this.chartData.rows=",this.chartData.rows[0].总浏览量);
          this.chartData.rows[i].总浏览量=this.trendPerYear[i+this.cnt*10].总浏览量;
      }
    },
    init:function(){
        //   测试post传入字段
        //   let search=this.input;
        //   console.log("this.input="+this.input+'\n');

        //axios
        axios.get('/api/trend.php').then(res=>{
            console.log('res=>',res.data);
            this.res=res;
            console.log('this.res=>',this.res.data);

            //转换成数组
            let snsArr;
            //判断是否为空，非空则split()
            if(this.res.data){
              snsArr=this.res.data.split("*");
              console.log("snsArr=",snsArr);
            }

            //需要删除空项
            for(var j=0;j<5;j++){
              for(var i=0,k=0;i<20;i+=2,k++){
                this.trendPerYear.push({关键字:snsArr[i],
                                  总浏览量:snsArr[19+j*10+k]});
              }
            }
            console.log("trendPerYear=",this.trendPerYear);
          
            //需要删除空项
            for(var i=0;i<20;i+=2){
                this.chartData.rows.push({关键字:snsArr[i],
                                总浏览量:snsArr[i+1]});
            }
            console.log("this.chartData.rows=",this.chartData.rows[0].总浏览量);

            setInterval(this.autoPlay, 2500)
        })
    },

    chartEvents:function(){
      legendselectchanged: (item) => {
        this.select=item.关键字;
      }
    }
  }
}
</script>