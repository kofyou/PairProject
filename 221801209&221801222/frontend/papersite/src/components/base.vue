<template>
  <div class = "base">
    <div id = "head">

      <el-row :gutter="20">
        <el-col :span="3" >
          <router-link to = "/index">
            <div class="grid-content bg-purple" :class = "{active : isActive['index']}"  @click= 'changeState("index")' >
              <span>主页</span>
            </div>
          </router-link>
        </el-col>

        <el-col :span="3">
          <router-link to = "/starlist">
            <div class="grid-content bg-purple"  :class = "{active : isActive['starlist']}"  @click= 'changeState("starlist")'>
              <span>我的收藏</span>
            </div>
          </router-link>
        </el-col>

        <el-col :span="3">
          <router-link to = "/analysis">
            <div class="grid-content bg-purple" :class = "{active : isActive['analysis']}"  @click= 'changeState("analysis")' >
              <span>顶会数据</span>
            </div>
          </router-link>
        </el-col>


        <el-col :span="3" :offset="11">
          <router-link to = "/index">
            <div class="grid-content bg-purple"  :class = "{active : isActive['user']}"  @click= 'changeState("user")'>
              <i class="el-icon-user-solid">&nbsp;&nbsp;</i><span>{{ username }}</span>
            </div>
          </router-link>
        </el-col>

      </el-row>
    </div>
    <el-divider content-position="left"></el-divider>
  </div>
</template>

<script>
export default {
  name: "login",
  data(){
    return {
      username: "DemoJi",
      isActive:{
        "index": false,
        "starlist": false,
        "analysis": false,
        "user": false
     },
    }
  },
  methods: {
    doLogin() {

    },
    goToRegister() {

    },
    changeState(click_type){   //传入参数为点击的类型，例如主页，数据分析等等
      let key;
      for(key in this.isActive){
        this.$set(this.isActive,key,false);
        // this.isActive[key] = false;
      }
      let type = click_type.toString();
      this.$set(this.isActive,type,true);
      // this.isActive[type] = true;
      // this.$forceUpdate();
    }
  }
}
</script>

//scoped只影响本组件
<style scoped>

el-row {
  margin-bottom: 20px;
}

el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
  text-align: center;
  margin-top: 10px;
  margin-left: 20px;
  padding-top: 10px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}

.active{
  background-color: #99a9bf;
}

.base{
  background-color: black;
}

/*.inactive-item{*/
/*  background: #d3dce6;*/
/*}*/


</style>
