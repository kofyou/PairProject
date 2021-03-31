$(function(){
    var thisPage
    var maxPage
    thisPage = window.location.toString().split("&page=").pop()
    $(".changePage:eq(0)").val(thisPage)
    if(thisPage=="1"){
        $("#leftPage").css("cursor","not-allowed")
    }
    
    function Page(){}
    $.extend(Page.prototype,{
        init:function(){
            this.bindEvents()
            var search = window.location.toString().split("&page=")[0].split("=").pop()

            $.ajax({
                url : AJAX_URL.searchPaperCount,
                type : "post",
                data : JSON.stringify({
                    "type" : 0,
                    "account" : USER_INFO.userID,
                    "methods" : "getPages",
                    "str" : search
                }),
                contentType : "application/json",
                success:data=>{
                    maxPage = data
                    if(maxPage==thisPage)
                        $("#rightPage").css("cursor","not-allowed")
                }
            })

            $.ajax({
                url:AJAX_URL.searchPaper,
                type:"post",
                data:JSON.stringify({
                    "account":USER_INFO.userID,
                    "type": 0,
                    "str" : search,
                    "page" : thisPage,
                    "methods" : "getSearchList"
                }),
                contentType:"application/json",
                dataType:"json",
                success:data=>{
                    $("#reg_wait").css("display","none")
                    $.each(data,function(index,paper){
                        var obj = {
                            "title" : paper.title,
                            "author" : paper.author,
                            "keyword" : paper.keyword,
                            "info"  : paper.info,
                            "link" : paper.link,
                            "like" : paper.iscollect
                        }
                        ALL_PAGE_LIST.push(obj)
                        $(".container").append("<div class='paper-item'>"+
                        "<div class='paper-head'>"+
                            "<span class='paper-title'><a href='"+paper.link+"'>"+paper.title+"</a></span>"+
                            "<!-- <span>作者：</span>"+
                            "<span class='paper-author'>"+paper.author.toString()+"</span> -->"+
                            "<span class='iconfont icon-shoucang1 kongxin'></span>"+
                            "<span class='iconfont icon-shoucang xiaoshi shixin'></span>"+
                        "</div>"+
                        "<div class='paper-mid'>"+
                            "<span class='mid-word'>关键字：</span>"+
                            "<span class='paper-keyword'>"+paper.keyword.toString()+"</span>"+
                        "</div>"+
                        "<div class='paper-foot'>"+
                            "<span>摘要：</span>"+
                            "<p class='paper-info'>"+paper.info+"</p>"+
                        "</div>"+
                        "<div class='line'></div>"+
                    "</div>")
                    })
                    for(let index = 0;index<ALL_PAGE_LIST.length;index++)
                    {
                        if(ALL_PAGE_LIST[index].like)
                        {
                            $(".kongxin").eq(index).addClass("xiaoshi")
                            $(".shixin").eq(index).removeClass("xiaoshi")

                        }
                    }
                    if(ALL_PAGE_LIST.length==0)
                    {
                        $("#empty").removeClass("xiaoshi")
                    }else{
                        $(".footer").removeClass("xiaoshi")
                    }
                },
                error:()=>{
                    
                    alert("网络烂掉了，你什么也看不到了")
                    $("#reg_wait").css("display","none")
                    $("#empty").removeClass("xiaoshi")
                }
            })
            $("#reg_wait").css("display","inline-block")
            $(".kongxin").click(function(){
                if(confirm("确定要收藏吗"))
                {
                    let addIndex = $(".kongxin").index(this);
                    let addInLike = $(".paper-title").eq(addIndex).text()
                    $.ajax({
                        url:AJAX_URL.searchPaperAdd,
                        data:JSON.stringify({
                            "account" : USER_INFO.userID,
                            "title" : addInLike
                        }),
                        contentType:"application/json",
                        type:"POST",
                        success:data=>{
                            if(data==true){
                                alert("收藏成功")
                                $(".kongxin").eq(addIndex).addClass("xiaoshi")
                                $(".shixin").eq(addIndex).removeClass("xiaoshi")
                            }
                            else
                                alert("不知道为啥没成功哦，可能去了火星")
                        },
                        error:()=>{
                            alert("啊哦，网络可能出问题了，请重新收藏")
                        }
                    })
                }
            })
            $(".shixin").click(function(){
                if(confirm("确定取消收藏吗"))
                {
                    let removeIndex = $(".shixin").index(this)
                    let removeInLike = $(".paper-title").eq(removeIndex).text()
                    $.ajax({
                        url: AJAX_URL.searchPaperDelete,
                        data:JSON.stringify({
                            "account" : USER_INFO.userID,
                            "title" : removeInLike
                        }),
                        contentType:"application/json",
                        type:"POST",
                        dataType:"json",
                        success:data=>{
                            if(data==true){
                                alert("取消收藏成功")
                                $(".shixin").eq(removeIndex).addClass("xiaoshi")
                                $(".kongxin").eq(removeIndex).removeClass("xiaoshi")
                            }
                            else
                                alert("不知道为啥没成功哦，可能去了火星")
                        },
                        error:()=>{
                            alert("啊哦，网络可能出问题了，请重新收藏")
                        }
                    })
                }
            })
        },
        bindEvents:function(){
            var logOut = $(".slide-down li:eq(1)")
            logOut.click(this.logOutFunc)
            var btn = $("#search_btn")
            btn.click(this.searchFunc)
        },
        logOutFunc:function(){
            if(confirm("确定要退出吗")){
              window.location.replace("../../login&regist/login.html")
            }
        },
        searchFunc:function(){
            let search = $(".search:eq(0)").val()
            if(search!="")
            {
              window.open("searchList.html?search="+search+"&page=1","_self")
            }
            else
            {
              window.open("allPaperList.html?page=1","_self")
            }
          },
        toPage : function(){
            var p = $(".changePage:eq(0)").val()
            if(p>maxPage){
                $(".changePage:eq(0)").val(thisPage)
                alert("最多只有"+maxPage+"页哦")
            }
            else
                window.open("searchList.html?page="+p,"_self")
        },
        lastPage:function(){
            if(thisPage!="1"){
                window.open("searchList.html?page="+(parseInt(thisPage)-1),"_self")
            }
        },
        nextPage:function(){
            if(thisPage<maxPage){
                window.open("searchList.html?page="+(parseInt(thisPage)+1),"_self")
            }
        }
    })

    var p = new Page()
    p.init()
})