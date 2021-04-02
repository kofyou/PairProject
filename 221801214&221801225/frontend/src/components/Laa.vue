<template>
<div class="cesh">
  <el-select v-model="value" placeholder="请选择">
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
  </el-select>
</div>
</template>

<script>

  export default {
    data() {
      return {
        options: [{
          value: '选项1',
        }, {
          value: '选项2',
        }, {
          value: '选项3',
        }, {
          value: '选项4',
        }, {
          value: '选项5',
        }],
        value: ''
      }
    }
  }
</script>
<style lang="less" scoped>
</style>