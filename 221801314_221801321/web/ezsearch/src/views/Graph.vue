<template>
  <Header />
  <h1>111</h1>
  <Footer />
</template>
<script>
import { defineComponent, ref } from "vue";
import Header from "@/components/common/Header.vue";
import Footer from "@/components/common/Footer.vue";
export default defineComponent({
  name: "Graph",
  components: {
      Header,
      Footer,
  }
});
</script>
<style scoped>
</style>
