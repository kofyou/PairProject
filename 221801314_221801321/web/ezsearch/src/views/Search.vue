<template>
  <div class="block">
    <h1 title="">EzSearch</h1>
    <el-carousel type="card" interval="0" height="300px">
      <el-carousel-item v-for="item in images" :key="item">
        <img object-fit="cover" :src="item.url" alt="1" />
        <!-- <h3 class="small">{{ item }}</h3> -->
      </el-carousel-item>
    </el-carousel>
  </div>
</template>
<script>
export default {
  name: "Search",
  data() {
    return {
      // images,
    }
  },
  setup() { 
    console.log("123");
    const path = require("@/assets/CVPR.jpg");
    const images = [
      { url: require("@/assets/CVPR.jpg")},
      { url: require('@/assets/ECCV.png')},
      { url: require('@/assets/ICCV.png')},
    ]
      return {
        images,
        path,
      }
  },
};
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
