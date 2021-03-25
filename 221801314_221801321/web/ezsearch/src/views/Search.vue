<template>
  <div class="block">
    <h1 title="">EzSearch</h1>
    <el-row :gutter="20">
      <el-col :span="12" :offset="2"><Carousel/></el-col>
      <el-col :span="6" :offset="0"><Carousel/></el-col>
      
    </el-row>
    
    <el-divider></el-divider>

    <el-row :gutter="0">
      <el-col :span="14" :push="4">
        <el-input
          style="width: 80%"
          placeholder="搜索论文"
          prefix-icon="el-icon-search"
          v-model="input">
        </el-input>
      </el-col>
      <el-col :span="6" :push="1">
        <el-button type="primary">搜索</el-button>
      </el-col>
    </el-row>
  </div>
  <el-divider></el-divider>

  <el-space direction="vertical">
    <Paper/>
    <Paper/>
  </el-space>

  

</template>
<script>
import {defineComponent, ref} from 'vue';
import Paper from '@/components/Paper.vue';
import Carousel from '@/components/Carousel.vue';
// import Sidebar from '@/components/Sidebar.vue';
export default defineComponent ({
  name: "Search",
  components:{
    Paper,
    Carousel,
    // Sidebar,
  },
  setup() { 
    const path = require("@/assets/CVPR.jpg");
    const images = [
      { url: require("@/assets/CVPR.jpg")},
      { url: require('@/assets/ECCV.png')},
      { url: require('@/assets/ICCV.png')},
    ]
      return {
        input: ref(''),
        images,
        path,
      }
  },
});
</script>
<style>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>
