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
    <div>{{currPage}}</div>
  </div>
</template>
<script>
import { defineComponent, onMounted, ref } from "vue";
import { useStore } from "vuex";
export default defineComponent({
  name: "Pagination",
  computed: {
      currPage () {
          //这里有bug   number undefined
          //return this.$store.pageNum.number;
      }
  },
  setup() {
    const store = useStore();
    onMounted(() => {
        // setPage(val);
      console.log(store.pageNum);
    });
  },
  methods: {
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      this.$store.commit({
        type: "setPages",
        page: val,
      });
      console.log("设置页面" + val);
    //   console.log(`当前页: ${val}`);
    },
  },
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
