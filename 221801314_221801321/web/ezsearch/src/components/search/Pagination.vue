<template>
  <div class="block">
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      v-model:currentPage="currentPage1"
      :page-size="100"
      layout="total, prev, pager, next"
      :total="1000"
    >
    </el-pagination>
  </div>
</template>
<script>
import { defineComponent, onMounted, ref } from "vue";
import { useStore } from "vuex";
export default defineComponent({
  name: "Pagination",
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
    }
    onMounted(() => {
      // setPage(val);
      handleCurrentChange();
    });
    return { 
      page,
      handleCurrentChange,
      handleSizeChange,
    }
  },
  methods: {},
  data() {
    return {
      currentPage1: 1,
      currentPage2: 5,
      currentPage3: 5,
      currentPage4: 4,
    };
  },
});
</script>
<style scoped>
</style>
