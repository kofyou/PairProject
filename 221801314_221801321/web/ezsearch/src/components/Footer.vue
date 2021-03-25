<template>
    <el-row>
        <el-col :span="4"><div class="grid-content bg-purple"></div></el-col>
        <el-col :span="16"><div class="grid-content bg-purple"><a>©2021 EzSearch</a></div></el-col>
        <el-col :span="4">
            <div class="grid-content bg-purple avatar">
            </div>
        </el-col>
    </el-row>
</template>
<script>
import {defineComponent} from 'vue';
    export default defineComponent ({
       name:'Footer',
    });
</script>
<style scoped>
    a {
        color: #ffffff;
        font-size: 22px;
        line-height: 52px;
    }
  .el-row {
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
  }
  .el-col {
    border-radius: 0px;
  }
  .bg-purple-dark {
    background: #155263;
  }
  .bg-purple {
    background: #155263;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    min-height: 52px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
</style>
