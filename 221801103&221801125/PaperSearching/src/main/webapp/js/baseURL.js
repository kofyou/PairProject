//var LOGIN = "http://192.168.0.114:8080/PaperSearching_war_exploded/LoginServlet"


const BASE_URL = "../"

const LOGIN =  BASE_URL+"LoginServlet"
const REGIST = BASE_URL+"RegisterServlet"

const HOTTEST = BASE_URL+"GetHottestKeywordsServlet"
const STATIC = BASE_URL+"GetTendencyServlet"

const GLOBAL_BLUR_SEARCH_ALL_PAPER_AND_COUNT = BASE_URL+"../PaperListServlet"
const GLOBAL_BUL_DELETE_MY_COLLECT = BASE_URL+"../DeleteMyCollectServlet"
const GLOBAL_BLUR_UPDATE = BASE_URL+"../UpdateMyCollectServlet"

const MY_COLLECT_ALL_PAPER = BASE_URL+"../MyCollectServlet"
const MY_COLLECT_COUNT = BASE_URL+"../MyCollectServlet"
const MY_COLLECT_UPDATE = BASE_URL+"../DeleteMyCollectServlet"

const SETTING = "../UpdateUserInfoServlet"


const AJAX_URL = {    
    "login" : LOGIN,    //登录
    "regist" : REGIST,    //注册

    "index" : HOTTEST,   //首页获得最热

    "static" : STATIC,  //图表

    "allPaper" : GLOBAL_BLUR_SEARCH_ALL_PAPER_AND_COUNT,  //获取所有论文
    "allPaperCount" : GLOBAL_BLUR_SEARCH_ALL_PAPER_AND_COUNT, //获取论文页数
    "allPaperDelete" : GLOBAL_BUL_DELETE_MY_COLLECT,  //论文取消收藏
    "allPaperAdd" : GLOBAL_BLUR_UPDATE, //论文添加收藏

    "searchPaper" : GLOBAL_BLUR_SEARCH_ALL_PAPER_AND_COUNT, //获取搜索论文数目
    "searchPaperCount" : GLOBAL_BLUR_SEARCH_ALL_PAPER_AND_COUNT,//获取论文数目
    "searchPaperDelete" : GLOBAL_BUL_DELETE_MY_COLLECT, //搜索页面取消收藏
    "searchPaperAdd" : GLOBAL_BLUR_UPDATE, //搜索页面添加收藏

    "myPaper" : MY_COLLECT_ALL_PAPER, //获得收藏论文
    "myPaperCount" :    MY_COLLECT_COUNT,    //获得收藏数目
    "myPaperDelete" : MY_COLLECT_UPDATE,   //删除收藏

    "mySetting" : SETTING,   //个人设置
}