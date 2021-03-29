var tableData = [];
window.onload = function(){
    var href=window.location.search;
    var word = href.substring(href.lastIndexOf('=')+1, href.length);//获取要搜索的数据
    var wordlist =word.split('+');
    if(wordlist[1]=="1")
    {
        if(wordlist[0]!='')
        {
            $.ajax(
            {
                url: '../index.php/index/Serchfunction/serchdata?words='+wordlist[0],
                type: 'get',
                data: {},
                dataType: 'json'
            }).then(function (res) 
            {
                for (var i in res) 
                {
                    tableData.push(res[i])  //把从json获取的数据赋值给数组
                }　　　　　　　　　　
            }).fail(function () 
            {
                    console.log('失败');
            })
        }
    }
    else if(wordlist[1]=="2")
    {
        if(wordlist[0]!='')
        {
            $.ajax(
            {
                url: '../index.php/index/Serchfunction/serchbykeyword?words='+wordlist[0],
                type: 'get',
                data: {},
                dataType: 'json'
            }).then(function (res) 
            {
                for (var i in res) 
                {
                    tableData.push(res[i])  //把从json获取的数据赋值给数组
                }　　　　　　　　　　
            }).fail(function () 
            {
                    console.log('失败');
            })
        }
    }
    
}
var Main = {
    data() {
        return {
            tableData,
            currentPage1:1,
            pagesize:10,
            currpage:1,
        }
    },
    methods: {
        handleSizeChange(val){
            this.pagesize=val;
        },
        handleCurrentChange(val){
            this.currpage=val;
        },
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