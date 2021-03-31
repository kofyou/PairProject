<template>
  <div class="itembox">
    <div class="paperid">
      {{ itemObj.paper_id - 2 }}
    </div>
    <div class="top">
      <h2>{{ itemObj.title }}</h2>
      <div class="magazine">会议:{{ itemObj.magazine }}</div>
      <div class="time">发布时间:{{ itemObj.publication_year }}</div>
    </div>
    <div class="content">
      {{ itemObj.abstracted }}
    </div>
    <div
      class="list"
      style="display: inline"
      v-for="(item, i) in this.itemObj.keyword"
      :key="i"
    >
      {{ item }}
    </div>
    <!-- <div class="info">
    </div> -->
    <div class="address">
      <div class="addre">
        地址:
        <span @click="See(itemObj.link)">{{ itemObj.link }}</span>
      </div>
    </div>
    <div class="change">
      <button class="delete" @click="deleted()">删除</button>
      <button class="update" @click="change">查看</button>
    </div>
  </div>
</template>

<script>
import eventVue from "@/assets/eventVue.js";
export default {
  props: {
    itemObj: {
      type: Object,
      default: function () {
        return {};
      },
    },
  },
  methods: {
    deleted() {
      location.reload();
    },
    See(e) {
      window.location.href = e;
    },
    change: function () {
      this.$router.replace("/add");
      this.$nextTick(() => {
        eventVue.$emit("aa", this.itemObj);
      });
    },
    mounted() {
      eventVue.$on("ak", (message) => {
        this.itemObj = message;
        console.log(this.itemObj);
      });
    },
  },
};
</script>

<style lang="less" scoped>
.itembox {
  padding: 30px 0;
  .paperid {
    width: 50px;

    height: 50px;

    // margin: 30px auto;

    border: 2px solid #00c2e4;

    text-align: center;

    line-height: 40px;

    font-weight: bold;

    font-size: 20px;

    background-color: white;

    border-radius: 50%; //圆角百分比
  }
  .top {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding-bottom: 10px;
    h2 {
      font-size: 25px;
      color: white;
      width: 850px;
    }
    .magazine {
      color: white;
    }
    .time {
      font-size: 14px;
      color: white;
    }
  }
  .content {
    background: #f6f6f6;
    border-radius: 5px;
    padding: 15px 10px;
    font-size: 16px;
    line-height: 1.8em;
    color: #444;
    margin-bottom: 10px;
  }
  .list {
    background: #f6f6f6;
    border-radius: 5px;

    margin: 0 10px;
    // font-size: 16px;
    line-height: 1.8em;

    color: #444;
    // margin-bottom: 10px;
  }
  // .info {
  //   font-size: 14px;
  //   color: white;
  //   padding-top: 5px;
  //   span {
  //     font-size: 16px;
  //     color: white;
  //   }
  // }
  .address {
    padding-top: 5px;
    font-size: 14px;
    color: white;
    .addre {
      font-size: 16px;
      color: rgb(39, 105, 180);
    }
  }
  .change {
    padding-top: 5px;
    display: flex;
    .delete {
      height: 40px;
      width: 60px;
      color: white;
      background-color: rgba(55, 158, 206, 0.616);
      font-size: 18px;
    }
    .update {
      height: 40px;
      width: 60px;
      color: white;
      background-color: rgba(55, 158, 206, 0.616);
      font-size: 18px;
    }
  }
}
</style>