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
        "name" : localStorage.getItem("name")==null? "":localStorage.getItem("name"),
        // "userID" : localStorage.getItem("userName")==null? "":localStorage.getItem("userName"),
        "userID" : "123",
        "sign" : localStorage.getItem("sign")==null? "":localStorage.getItem("sign"),
        "company" : localStorage.getItem("company")==null? "":localStorage.getItem("company"),
        "address" : localStorage.getItem("address")==null? "":localStorage.getItem("address"),
        "password" : localStorage.getItem("password")==null? "":localStorage.getItem("password")
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
var ALL_PAGE_LIST = []

// var LIKE_LIST = [{
//     "title" : "",
//     "keyword" : "",
//     "info" : "",
//     "author" : "",
//     "link" : ""
// }]
var LIKE_LIST = []


// /**
//  * 
//  * 每隔五分钟问后端要一次数据
//  * 
//  */
// var GET_ALL_DATA_INTERVAL = setInterval(function(){
//     /**
//      * 
//      * 发送ajax数据，获取后端总数据
//      * 
//      */
//     $.ajax({
//         url : "",
//         data : {
//             "userName" : USER_INFO.userID
//         },
//         type : "POST",
//         dataType : "json",
//         success : data=>{

//         }
//     })
// },300000)
