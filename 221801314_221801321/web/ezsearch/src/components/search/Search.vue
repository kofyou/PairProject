<template>
  <el-autocomplete
    class="inline-input"
    v-model="inputValue"
    prefix-icon="el-icon-search"
    :fetch-suggestions="querySearch"
    placeholder="请输入内容"
    @select="handleSelect()"
  ></el-autocomplete>
</template>
<script>
import { defineComponent, ref, onMounted, getCurrentInstance } from "vue";
export default defineComponent({
  name: "Search",
  setup() {
    const { ctx } = getCurrentInstance();
    var inputValue = ref("");
    //get方法获取文章title
    const getTitles = () => {
      ctx.$http
        .get("/paper/title", {
          title: inputValue.value,
        })
        .then((res) => {
          console.log(res)
        });
    };

    const paperTitles = ref([]);
    const querySearch = (queryString, cb) => {
      ctx.$http
        .get("/paper/title", {
          title: inputValue.value,
        })
        .then(({data}) => {
          console.log(data);
          for (let i = 0; i < data.data.length; i++) {
            data.data[i].value = data.data[i].title;
          }
          cb(data.data);
        });
      // var results = queryString
      //   ? paperTitles.value.filter(createFilter(queryString))
      //   : paperTitles.value;
      // 调用 callback 返回建议列表的数据
      // cb(results);
    };
    const createFilter = (queryString) => {
      return (paperTitles) => {
        return (
          paperTitles.value.toLowerCase().indexOf(queryString.toLowerCase()) ===
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
      paperTitles.value = loadAll();
    });
    return {
      inputValue,
      getTitles,
      paperTitles,
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
