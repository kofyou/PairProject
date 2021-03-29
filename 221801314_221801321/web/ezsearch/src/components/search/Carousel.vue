<template>
  <div class="block">
    <el-carousel interval="0" height="500px">
      <el-carousel-item v-for="item in images" :key="item">
        <!-- <img :src="item.url" alt="1" /> -->
        <el-image :src="item.url" :fit="cover"></el-image>
      </el-carousel-item>
    </el-carousel>
  </div>
</template>
<script>
import { defineComponent } from "vue";
export default defineComponent({
  name: "Carousel",

  setup() {
    const path = require("@/assets/CVPR.jpg");
    const images = [
      { url: require("@/assets/CVPR.jpg") },
      { url: require("@/assets/ECCV.png") },
      { url: require("@/assets/ICCV.png") },
    ];
    return {
      images,
      path,
    };
  },
});
</script>
<style scoped>
</style>
