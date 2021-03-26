<template>
    <el-row>
        <el-col :span="4"><div class="grid-content bg-purple"><img :src="menuUrl" class="header-icon" /></div></el-col>
        <el-col :span="16"><div class="grid-content bg-purple"><a>EzSearch</a></div></el-col>
        <el-col :span="4">
            <div class="grid-content bg-purple avatar">
                <img :src="avatarUrl" class="avatar-img"/>
            </div>
        </el-col>
    </el-row>
</template>
<script>
import {defineComponent} from 'vue';
    export default defineComponent ({
       name:'Header',
       setup(){
            const avatarUrl = require('@/assets/avatars/leiye.jpg');
            const menuUrl = require('@/assets/icons/menu.png');
            return {
                avatarUrl,
                menuUrl,
           }
       }
    });
</script>
<style scoped>
    a {
      color: #ffffff;
      font-size: 30px;
      line-height: 52px;
      font-weight: bold;
    }
    .header-icon {
        width:42px;
        height:42px;
        margin-top: 5px;
    }
    .avatar {
        height: 52px;
    }
    .avatar helper{
        display: inline-block;
        height: 100%;
        vertical-align: middle;
    }
    .avatar img {
        width:42px;
        height:42px;
        margin-top: 5px;
        border-radius: 50%;
    }
  .el-row {
    margin-bottom: 20px;
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
