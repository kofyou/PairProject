<template>
  <router-view></router-view>
</template>

<script>
import * as echarts from "echarts";
import { provide } from "vue";
import { defineComponent } from "vue";
export default defineComponent({
  name: "App",
  setup() {
    provide("ec", echarts); //provide
  },
  components: {},
});
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
