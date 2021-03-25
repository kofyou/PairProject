var tableData = [
    { num: '1', title: "一叶扁舟", keyword: " 22", date: "2018-06-27" },
    { num: '2', title: "一叶扁舟", keyword: " 232", date: "2018-06-27" },
    { num: '3', title: "一叶扁舟", keyword: " 232", date: "2018-06-27" },
    { num: '4', title: "一叶扁舟", keyword: " 232", date: "2018-06-27" },
    { num: '5', title: "一叶扁舟", keyword: " 232", date: "2018-06-27" },
    { num: '6', title: "一叶扁舟", keyword: " 232", date: "2018-06-27" },
    { num: '7', title: "一叶扁舟", keyword: " 232", date: "2018-06-27" },
    { num: '40', title: "一叶扁舟", keyword: " 232", date: "2018-06-27" },
    { num: '11', title: "一叶扁舟", keyword: " 232", date: "2018-06-27" },
    { num: '9', title: "一叶扁舟", keyword: " 232", date: "2018-06-27" },
];
var Main = {
    data() {
        return {
            tableData
        }
    },
    methods: {
        formatter(row, column) {
            return row.address;
        },
        handleClick(row) {
            tableData.splice(tableData.findIndex(e => e.num == row.num), 1);
        },
    },
}
var Ctor = Vue.extend(Main)
new Ctor().$mount('#app')