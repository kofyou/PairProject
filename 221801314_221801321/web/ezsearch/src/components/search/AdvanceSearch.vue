<template>
  <el-popover id="pop" placement="bottom-end" :width="400" trigger="click">
    <template #reference>
      <el-button
        class="el-icon-s-operation blue-background"
        style="color: #fff; margin-left: 40px; height: 60px; font-size: 32px"
      ></el-button>
    </template>
    <!-- 弹出栏内容 -->
    <div class="divider">
      发表年份：<el-select hide-on-click="false" v-model="year" placeholder="请选择" size="small">
        <el-option
          v-for="item in years"
          :key="item.value"
          :label="item.label"
          :value="item.value"
          hide-on-click="true"
        >
        </el-option>
      </el-select>
    </div>
    <div class="divider">
      筛选顶会：<el-select hide-on-click="false" v-model="meeting" placeholder="请选择" size="small">
        <el-option
          v-for="item in meetings"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        >
        </el-option>
      </el-select>
    </div>
    <el-button
      @click="getCond()"
      class="confirm"
      style="margin-left: 160px; color: #fff; background-color: #155263"
      >确定</el-button
    >
  </el-popover>
</template>
<script>
import { defineComponent, ref } from "vue";
// import {$} from "jquery";
export default defineComponent({
  name: "AdvanceSearch",
  setup(props, {emit}) {
    const year = ref("");
    const meeting = ref("");

    function getCond() {
        console.log(year.value);
        console.log(meeting.value);
        // document.getElementById("#pop").style.display = "none";
        emit("year",year.value);
        emit("meeting", meeting.value);
    }

    return {
      year,
      meeting,

      getCond,

      years: [
        {
          value: "2020",
          label: "2020年",
        },
        {
          value: "2019",
          label: "2019年",
        },
        {
          value: "2018",
          label: "2018年",
        },
      ],
      meetings: [
        {
          value: "CVPR",
          label: "CVPR",
        },
        {
          value: "ICCV",
          label: "ICCV",
        },
        {
          value: "ECCV",
          label: "ECCV",
        },
      ],
    };
  },
});
</script>
<style scoped>
.blue-background {
  background-color: #155263;
  border: 0px;
}
.input {
  height: 40px !important;
}
.divider {
  margin: 1px;
}
.confirm {
  text-align: center;
}
</style>
