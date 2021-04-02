<template>
    <div style="display:flex; justify-content:center; width:500px; margin:50px 0 0 35%;">
        <el-form ref="form" :model="form" label-width="80px">
            <el-form-item label="用户名">
                <el-input v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="密码">
                <el-input v-model="form.pwd" show-password></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit">登录</el-button>
                <el-button>注册</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
import axios from 'axios'
  export default {
    data() {
      return {
        form: {
          name: '',
          pwd: ''
        }
      }
    },
    methods: {
      onSubmit() {
        console.log('submit!');
        this.init();
      },
      init:function(){
        //   测试post传入字段
        //   let search=this.input;
        //   console.log("this.input="+this.input+'\n');

        let account=[
            {id:this.form.name},
            {password:this.form.pwd}
        ];
        //axios
        axios.post('/api/login.php',account).then(res=>{
            console.log('res=>',res.data);
            this.res=res;
            console.log('this.res=>',this.res.data);

            //转换成数组
            // let snsArr;
            // //判断是否为空，非空则split()
            // if(this.res.data){
            //   snsArr=this.res.data.split("*");
            //   console.log("snsArr=",snsArr);
            // }
          
            // //需要删除空项
            // for(var i=0;i<20;i+=2){
            //     // let keywordFormate=snsArr[i].replace('[','').replace(']','');
            //     this.chartData.rows.push({关键字:snsArr[i],
            //                     总浏览量:snsArr[i+1]});
            // }
            // console.log("this.chartData.rows:"+this.chartData.rows[0]+'\n');
        })
        }
    }
  }
</script>