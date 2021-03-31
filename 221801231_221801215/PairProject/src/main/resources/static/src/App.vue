<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
import myheader from './components/myheader.vue'
export default {
  name: 'App',
  components:{
    myheader
  }
}
</script>

<style>
*{
  margin:0;
  padding:0;
  border:0;
}
</style>
