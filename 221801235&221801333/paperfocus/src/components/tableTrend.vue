<template>
  <div>
    <ve-line :data="chartData"></ve-line>
  </div>
</template>
 
<script>
import VeLine from 'v-charts/lib/line.common'
import axios from 'axios'
export default {
  components: { VeLine },
  data () {
    return {
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
        })

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
    }
  }
}
</script>