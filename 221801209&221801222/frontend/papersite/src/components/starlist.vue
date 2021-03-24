<template>
  <div id = "starlist" class = "root-div">
    <Base> </Base>
    <div class = "main">

    </div>
  </div>
</template>

<script>

import Base from '../components/base'

export default {
  name: "starlist",
  components: {
    Base
  }
}
</script>

<style scoped>

.base {
  background-image: url("../assets/background.jpg");
  background-attachment: fixed;
  background-size: 100% 100%;
  background-repeat: no-repeat;
  height: 37.5em;
  width: auto;
  border: 0px;
  padding: 0px;
}

</style>
