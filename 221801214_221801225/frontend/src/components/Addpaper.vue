<template>
  <div class="add">
    <div class="title">
      <h1 class="titlename">标题:</h1>
      <input class="searchBox" type="text" v-model="itemObj.title" />
    </div>
    <div class="content">
      <h1 class="contentname">摘要:</h1>
      <textarea
        class="textarea"
        cols="73"
        rows="10"
        v-model="itemObj.abstracted"
      ></textarea>
    </div>
    <div class="list">
      <h1 class="listname">关键词:</h1>
      <input class="listBox" type="text" v-model="itemObj.keyword" />
    </div>
    <div class="address">
      <h1 class="addname">地址:</h1>
      <input class="addBox" type="text" v-model="itemObj.link" />
    </div>
    <div class="changediv">
      <button class="changebu" @click="changecl">返回</button>
    </div>
  </div>
</template>

<script>
import eventVue from "@/assets/eventVue.js";
export default {
  components: {},
  data() {
    return {
      itemObj: {
        type: Object,
        default: function () {
          return {};
        },
      },
    };
  },
  methods: {
    changecl() {
      this.$router.replace("/show");
      this.$nextTick(() => {
        eventVue.$emit("ak", this.itemObj);
      });
    },
  },
  mounted() {
    eventVue.$on("aa", (message) => {
      this.itemObj = message;
      console.log(this.itemObj);
    });
  },
};
</script>

<style lang="less" scoped>
.add {
  width: 1200px;
  height: 800px;
  padding-top: 60px;
  background-image: url(https://images.cnblogs.com/cnblogs_com/lzklzk/1952300/o_210325063128u=3078691788,1881157151&fm=26&gp=0.jpg);
  .changediv {
    padding-top: 50px;
    padding-left: 500px;
      
    .changebu {
      color: black;
      background-color: white;
      height: 50px;
      width: 60px;
      font-size: 20px;
    }
  }
  .title {
    display: flex;
    padding-top: 30px;
    padding-left: 100px;
    .searchBox {
      width: 800px;
      //   margin-right: 200px;
      font-size: 20px;
    }
    .titlename {
      //   margin-left: 200px;
      color: white;
    }
  }
  .content {
    display: flex;
    padding-top: 30px;
    padding-left: 100px;
    .textarea {
      resize: none;
      font-size: 20px;
    }
    .contentname {
      color: white;
    }
  }
  .list {
    display: flex;
    padding-left: 66px;
    padding-top: 30px;
    .listBox {
      width: 800px;
      font-size: 20px;
    }
    .listname {
      color: white;
    }
  }
  .address {
    display: flex;
    padding-left: 100px;
    padding-top: 30px;
    .addname {
      color: white;
    }
    .addBox {
      width: 800px;
      font-size: 20px;
    }
  }
}
</style>
