<template>
  <Header />
  <div>
      <router-link to="/hotspot"><span style="font-size: 20px">HotSpot</span></router-link><span style="font-size: 20px"> | </span>
      <!-- <router-link to="/wordCloud"><span style="font-size: 20px">HotSpot</span></router-link><span style="font-size: 20px"> | </span> -->
      <router-link to="/top10"><span style="font-size: 20px">Top10</span></router-link>

      <router-view style="margin: 0 auto; margin-top:60px"></router-view>
  </div>
  <Footer />
</template>
<script>
import { inject, onMounted } from "vue";
import { defineComponent } from "vue";
import Header from "@/components/common/Header.vue";
import Footer from "@/components/common/Footer.vue";
export default defineComponent({
  name: "Graph",
  components: {
    Header,
    Footer,
  },
  mounted() {},
});
</script>
<style scoped>
</style>
