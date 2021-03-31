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
  data() {
    return {
      inputValue: '22',
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
    this.inputValue = this.getQueryString("title");
    console.log(this.inputValue);
  },
  setup(props, { emit }) {
    const { ctx } = getCurrentInstance();
    var inputValue = ref("");
    //搜索框内容 子传父
    const emitTitle = () => {
      emit("title", inputValue.value);
    };
    //get方法获取文章title
    const getTitles = () => {
      ctx.$http
        .get("/paper/title", {
          title: inputValue.value,
        })
        .then((res) => {
          console.log(res);
        });
    };

    const paperTitles = ref([]);
    const querySearch = (queryString, cb) => {
      // console.log(inputValue)
      emitTitle();
      ctx.$http
        .get("/paper/title", {
          address: 5,
          title: inputValue.value,
        })
        .then((data) => {
          for (let i = 0; i < data.length; i++) {
            // console.log(data[i].title)
            data[i].value = data[i].title;
          }
          // console.log(data)
          cb(data);
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
      emitTitle,
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
