var tableData = [];
window.onload = function(){
    $.ajax({
    url: 'http://localhost/PairProject/228101410&041801406/backend/public/index.php/index/Serchfunction/serchdata',
    type: 'get',
    data: {},
    dataType: 'json'
}).then(function (res) {
    for (var i in res) {
        tableData.push(res[i])
     }
　　　　　　　　　　//把从json获取的数据赋值给数组
}).fail(function () {
    console.log('失败');
})
}


/*
var tableData = [
    { title: "一叶扁舟", keyword: " 22", date: "2018-06-05" },
    { title: "一叶扁舟1", keyword: " 2321", date: "2018-06-04" },
    { title: "一叶扁舟2", keyword: " 2322", date: "2018-06-17" },
    { title: "一叶扁舟3", keyword: " 2323", date: "2018-06-16" },
    { title: "一叶扁舟4", keyword: " 2324", date: "2018-06-15" },
    { title: "一叶扁舟5", keyword: " 2325", date: "2018-06-24" },
    { title: "一叶扁舟6", keyword: " 2326", date: "2018-06-14" },
    { title: "一叶扁舟7", keyword: " 2327", date: "2018-06-03" },
    { title: "一叶扁舟8", keyword: " 2328", date: "2018-06-01" },
    { title: "一叶扁舟9", keyword: " 2329", date: "2018-06-02" },
];
*/
var Main = {
    data() {
        return {
            tableData
        }
    },
    methods: {
        handleClick(row) {
            tableData.splice(tableData.findIndex(e => e.title == row.title), 1);
        },
        changeTableSort(column) {
            //获取字段名称和排序类型
            var fieldName = column.prop;
            var sortingType = column.order;
            //按照降序排序
            if (sortingType == "descending") {
                this.tableData = this.tableData.sort((a, b) => Date.parse(new Date(b[fieldName])) - Date.parse(new Date(a[fieldName])));
            }
            //按照升序排序
            else {
                this.tableData = this.tableData.sort((a, b) => Date.parse(new Date(a[fieldName])) - Date.parse(new Date(b[fieldName])));
            }
        }
    },
}
var Ctor = Vue.extend(Main)
new Ctor().$mount('#app')