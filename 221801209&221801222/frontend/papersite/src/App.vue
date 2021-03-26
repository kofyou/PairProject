<template>
  <div id="app">
    <!-- 路由占位符 -->
    <router-view></router-view>
  </div>
</template>

<script>


export default {

  components:{
  },
  name: 'App',
}
</script>

<style>

#app{
  border: 0px;
  padding: 0px;
}

.root-div {
  background-image: url("assets/background.jpg");
  background-attachment: fixed;
  background-size: 100% 100%;
  background-repeat: no-repeat;
  min-height: 37em;
  width: auto;
  border: 0px;
  padding: 0px;
}

</style>

