<template>
  <div id="wordCloud" :style="{ width: '300px', height: '300px' }"></div>
</template>


<script>
import {
  inject,
  onMounted,
  mounted,
  defineComponent,
  getCurrentInstance,
} from "vue";
export default defineComponent({
  name: "WordCloud",
  setup() {
    let echarts = inject("ec"); //引入
    onMounted(() => {
      let myChart = echarts.init(document.getElementById("wordCloud"));
      // 绘制图表
    });
    return {
    };
  },
});
</script>
<style scoped>
</style>
