<template>
  <div id = "index" class = "root-div">
    <Base></Base>
    <div class = "main">

      <el-input v-model="searchWord" placeholder="输入检索条件"  clearable></el-input>
    </div>
  </div>
</template>

<script>

import Base from '../components/base'

export default {
  name: "index",
  data() {
    return {
      searchWord : ''
    }
  },
  components: {
    Base
  }
}
</script>

<style scoped>

</style>
