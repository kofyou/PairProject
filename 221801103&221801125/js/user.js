/**
 * 
 * 
 * 后续需要使得login,register
 * 数据调用cookie
 * 进入到主页再用cookie进行赋值
 * 
 */

var USER_INFO = 
    {
        "name" : "",
        "userID" : "1",
        "sign" : "",
        "company" : "",
        "address" : "",
        "password" : ""
    }

var DATA_NEEDED_TO_BE_CHANGED = false


// var HOTTEST_TOP10 = [{"name":"","value":""}]
var HOTTEST_TOP10 = []

// var HOTTEST_THREE = {
//     "cvpr" : [], //cvpr的前5月排行int数组
// 	"iccv" : [],
// 	"eccv" : [],
// }
var HOTTEST_THREE = {}

// var ALL_PAGE_LIST = [{
//     "title" : "",
//     "keyword" : "",
//     "info" : "",
//     "author" : "",
//     "like" : "",
//     "link" : ""
// }]
var ALL_PAGE_LIST = [{
        "title" : "",
        "keyword" : "",
        "info" : "",
        "author" : "",
        "link" : "",
        "like":true
    }
    ]

// var LIKE_LIST = [{
//     "title" : "",
//     "keyword" : "",
//     "info" : "",
//     "author" : "",
//     "link" : ""
// }]
var LIKE_LIST = [{
    "title" : "标题1",
    "keyword" : "13123123 123123123",
    "info" : "1232435644123·3243564312432465414243246542413436542413246543241254356231432465431452356435342315245",
    "author" : "不认识",
    "link" : "www.baidu.com"
},{
    "title" : "标题2",
    "keyword" : "13123123 123123123",
    "info" : "1232435644123·3243564312432465414243246542413436542413246543241254356231432465431452356435342315245",
    "author" : "不认识",
    "link" : "www.baidu.com"
},{
    "title" : "标题3",
    "keyword" : "13123123 123123123",
    "info" : "1232435644123·3243564312432465414243246542413436542413246543241254356231432465431452356435342315245",
    "author" : "不认识",
    "link" : "www.baidu.com"
},{
    "title" : "标题4",
    "keyword" : "13123123 123123123",
    "info" : "1232435644123·3243564312432465414243246542413436542413246543241254356231432465431452356435342315245",
    "author" : "不认识",
    "link" : "www.baidu.com"
},{
    "title" : "标题5",
    "keyword" : "13123123 123123123",
    "info" : "1232435644123·3243564312432465414243246542413436542413246543241254356231432465431452356435342315245",
    "author" : "不认识",
    "link" : "www.baidu.com"
},{
    "title" : "标题6",
    "keyword" : "13123123 123123123",
    "info" : "1232435644123·3243564312432465414243246542413436542413246543241254356231432465431452356435342315245",
    "author" : "不认识",
    "link" : "www.baidu.com"
},{
    "title" : "标题7",
    "keyword" : "13123123 123123123",
    "info" : "1232435644123·3243564312432465414243246542413436542413246543241254356231432465431452356435342315245",
    "author" : "不认识",
    "link" : "www.baidu.com"
},{
    "title" : "标题",
    "keyword" : "13123123 123123123",
    "info" : "1232435644123·3243564312432465414243246542413436542413246543241254356231432465431452356435342315245",
    "author" : "不认识",
    "link" : "www.baidu.com"
}]


/**
 * 
 * 每隔五分钟问后端要一次数据
 * 
 */
var GET_ALL_DATA_INTERVAL = setInterval(function(){
    /**
     * 
     * 发送ajax数据，获取后端总数据
     * 
     */
    $.ajax({
        url : "",
        data : {
            "userName" : USER_INFO.userID
        },
        type : "POST",
        dataType : "json",
        success : data=>{

        }
    })
},300000)
