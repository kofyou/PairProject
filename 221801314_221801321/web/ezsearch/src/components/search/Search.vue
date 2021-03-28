<template>
  <el-autocomplete
    id="search-input"
    class="inline-input"
    v-model="state1"
    prefix-icon="el-icon-search"
    :fetch-suggestions="querySearch"
    placeholder="请输入内容"
    @select="handleSelect"
  ></el-autocomplete>
</template>
<script>
import { defineComponent, ref, onMounted } from "vue";
export default defineComponent({
  name: "Search",
  setup() {
    const restaurants = ref([]);
    const querySearch = (queryString, cb) => {
      var results = queryString
        ? restaurants.value.filter(createFilter(queryString))
        : restaurants.value;
      // 调用 callback 返回建议列表的数据
      cb(results);
    };
    const createFilter = (queryString) => {
      return (restaurant) => {
        return (
          restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) ===
          0
        );
      };
    };
    const loadAll = () => {
      return [{ value: "三全鲜食（北新泾店）", address: "长宁区新渔路144号" }];
    };
    const handleSelect = (item) => {
      console.log(item);
    };
    onMounted(() => {
      restaurants.value = loadAll();
    });
    return {
      restaurants,
      state1: ref(""),
      state2: ref(""),
      querySearch,
      createFilter,
      loadAll,
      handleSelect,
    };
  },
});
</script>

<style scoped>
.el-autocomplete {
  display: block;
}
</style>
