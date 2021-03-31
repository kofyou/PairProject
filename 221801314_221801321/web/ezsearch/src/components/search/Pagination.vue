<template>
  <div class="block">
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      v-model:currentPage="currentPage"
      :page-size="5"
      layout="total, prev, pager, next"
      :total="sum"
    >
    </el-pagination>
  </div>
</template>
<script>
import { number } from "echarts";
import { defineComponent, onMounted, ref } from "vue";
import { useStore } from "vuex";
export default defineComponent({
  name: "Pagination",
  props:['total'],
  data() {
    return {
      sum: this.total,
      currentPage: 1,
    };
  },
  methods: {
    getQueryString(name) {
      var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
      var r = window.location.search.substr(1).match(reg);
      if (r != null) {
        return unescape(r[2]);
      }
      return null;
    },
  },
  created() {
    // console.log(this.getQueryString);
    var page = this.getQueryString("address");
    this.currentPage = Number(page) + Number(1);
    console.log(page);
    console.log(this.currentPage);
  },
  setup(props, { emit }) {
    // const store = useStore();
    let page = 1;
    function handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    }
    function handleCurrentChange(val) {
      page = val;
      getPage();
      // console.log(`当前页: ${page}`);
    }
    const getPage = () => {
      emit("page", page);
    };
    onMounted(() => {
      // setPage(val);
      // handleCurrentChange();
    });
    return {
      page,
      handleCurrentChange,
      handleSizeChange,
    };
  },
});
</script>
<style scoped>
</style>
