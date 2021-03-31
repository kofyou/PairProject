<template>
  <div>
    <ve-line :data="chartData" style="margin-top:150px;"></ve-line>
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
    //   chartData: {
    //     columns: ['日期', 'CV浏览量','NLP浏览量','DL浏览量'],
    //     rows: [
    //       { '日期': '1月1日', 'CV浏览量': 123 ,'NLP浏览量':111,'DL浏览量':128},
    //       { '日期': '1月2日', 'CV浏览量': 1223 ,'NLP浏览量': 3653,'DL浏览量': 5783},
    //       { '日期': '1月3日', 'CV浏览量': 2123 ,'NLP浏览量': 2223,'DL浏览量': 2123},
    //       { '日期': '1月4日', 'CV浏览量': 4123,'NLP浏览量': 5523,'DL浏览量': 5623 },
    //       { '日期': '1月5日', 'CV浏览量': 3123 ,'NLP浏览量': 4333,'DL浏览量': 8923},
    //       { '日期': '1月6日', 'CV浏览量': 7123 ,'NLP浏览量': 8023,'DL浏览量': 5623}
    //     ]
    //   }
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
    }
  }
}
</script>