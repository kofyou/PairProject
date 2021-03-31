<template>
    <div class="w-wrap">
      <div style="width:500px;margin-left:210px;">
        <el-input
          placeholder="请输入内容"
          v-model="input"
          clearable
          @keyup.enter.native="convert()"
          style="width:200px;">
        </el-input>
      </div>

      <el-button type="primary" icon="el-icon-search" style="margin-left:20px;" @click="convert()">搜索</el-button>
    </div>
</template>

<style scoped>
.w-wrap{
  display: flex;
  justify-content: space-around;
}
</style>

<script>
import Vue from 'vue'
import $ from 'jquery'
import axios from 'axios'


export default {
  data() {
    return {
      input: ''
    }
  },
  mounted:function(){
      this.convert();
  },
  methods:{
      convert:function(){
        // let search='a';
        let search=this.input;
        console.log("this.input="+this.input+'\n');
        //将axios挂载到原型上
        // Vue.prototype.$axios = axios;
        // const service = axios.create({
        //   baseURL: process.env.BASE_API, // api 的 base_url
        //   timeout: 15000 // 请求超时时间
        // })
        axios.post('/api/search.php',search).then(res=>{
          console.log('res=>',res.data);
        })
      }
    }
}
</script>