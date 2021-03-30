<template>
  <div id="app">
    <HdView />
    <div class="main">
      <router-view/>
    </div>
  </div>
</template>

<script>
import HdView from "./components/HdView";
//  import ShowView from "./components/ShowView";
export default {
    components: {
      HdView
      // ShowView
    }
};
</script>
<style lang="less">
* {
  padding: 0;
  margin: 0;
  font-family: "微软雅黑";
  box-sizing: border-box;
}
a {
  text-decoration: none;
}
ul,
ol {
  list-style: none;
}
#app{
  width: 1200px; 
  border: 1px solid #41B883;
  margin: 0 auto;
}
.main{
   min-height: 200px;
}
</style>
