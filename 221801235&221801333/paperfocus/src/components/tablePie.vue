<template>
  <div>
    <ve-pie :data="chartData" :events="chartEvents" style="margin-top:150px;"></ve-pie>
  </div>
</template>
 
<script>

import VePie from 'v-charts/lib/pie.common' // 饼形
import axios from 'axios'
export default {
  components: { VePie },
  data () {
    return {
        select:'',
        res:{},
        chartData: {
            columns: ['关键字', '总浏览量'],
            rows:[]
        }
    }
  },
  mounted:function(){
    this.init();
  },
  methods:{
    init:function(){
        //   测试post传入字段
        //   let search=this.input;
        //   console.log("this.input="+this.input+'\n');

        //axios
        axios.get('/api/keyword.php').then(res=>{
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
            for(var i=0;i<20;i+=2){
                // let keywordFormate=snsArr[i].replace('[','').replace(']','');
                this.chartData.rows.push({关键字:snsArr[i],
                                总浏览量:snsArr[i+1]});
            }
            // console.log("this.chartData.rows:"+this.chartData.rows[0]+'\n');
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