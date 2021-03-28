<template>
  <div>
    <el-row :gutter="0">
      <el-col :span="14" :push="4">
        <el-input
          style="width: 80%"
          placeholder="搜索论文"
          prefix-icon="el-icon-search"
          v-model="input"
        >
        </el-input>
      </el-col>
      <el-col :span="6" :push="1">
        <el-button type="primary">搜索</el-button>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import { defineComponent } from "vue";
export default defineComponent({
  name: "Search",
});
</script>
<style scoped>
</style>
