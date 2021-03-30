<template>
  <div id="analysis" class="root-div">
    <Base type= "analysis"></Base>
    <Echarts></Echarts>
    <div className="main">
    </div>
  </div>
</template>
<script>

import Base from '../components/base'
import Echarts from '../components/echarts'

export default {
  name: "analysis",
  components: {
    Base,
    Echarts
  }
}
</script>

<style scoped>

</style>
