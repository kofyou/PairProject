$(function(){
    var thisPage
    var maxPage
    thisPage = window.location.toString().split("=").pop()
    $(".changePage:eq(0)").val(thisPage)
    if(thisPage=="1"){
        $("#leftPage").css("cursor","not-allowed")
    }


    function Page(){}
    $.extend(Page.prototype,{
        init:function(){
            /**
             * 
             * 
             * 传递数据
             * 
             */
             $.ajax({
                url : AJAX_URL.myPaperCount,
                type : "post",
                data : JSON.stringify({
                    "type" : 0,
                    "account" : USER_INFO.userID,
                    "methods" : "getPages"
                }),
                contentType : "application/json",
                success:data=>{
                    maxPage = data
                    if(maxPage==thisPage)
                        $("#rightPage").css("cursor","not-allowed")
                }
            })



            
            //发个ajax数据获取页面数据
            $.ajax({
                url : AJAX_URL.myPaper,
                type : "post",
                data : JSON.stringify({
                    "type" : 0,
                    "account" : USER_INFO.userID
                }),
                dataType : "json",
                contentType:"application/json",
                success:data=>{
                    $("#reg_wait").css("display","none")
                    $.each(data,function(index,like){
                        var obj = {
                            "title" : like.title,
                            "keyword" : like.keyword,
                            "info" : like.abstract,
                            "author" : like.author,
                            "link" : like.link
                        }
                        LIKE_LIST.push(obj)
                    })
                    LIKE_LIST.forEach(like=>{
                        $(".container").append("<div class='paper-item'>"+
                        "<div class='paper-head'>"+
                            "<span class='paper-title'><a href='"+like.link+"'>"+like.title+"</a></span>"+
                            "<!-- <span>作者：</span>"+
                            "<span class='paper-author'>"+like.author.toString()+"</span> -->"+
                            "<span class='iconfont icon-shanchu xiaoshi '></span>"+
                        "</div>"+
                        "<div class='paper-mid'>"+
                            "<span class='mid-word'>关键字：</span>"+
                            "<span class='paper-keyword'>"+like.keyword.toString()+"</span>"+
                        "</div>"+
                        "<div class='paper-foot'>"+
                            "<span>摘要：</span>"+
                            "<p class='paper-info'>"+like.info+"</p>"+
                        "</div>"+
                        "<div class='line'></div>"+
                    "</div>")
                    })
                    if(LIKE_LIST.length==0)
                    {
                        $("#region ").removeClass("xiaoshi")
                        $("#empty").removeClass("xiaoshi")
                    }
                    else{
                        $(".deleteLike").removeClass("xiaoshi")
                        $(".footer").removeClass("xiaoshi")
                    }
                        
                },
                error:()=>{
                    
                    alert("网络不好，什么都看不到")
                    $("#reg_wait").css("display","none")
                    $("#empty").removeClass("xiaoshi")
                }
            })
            LIKE_LIST.forEach(like=>{
                $(".container").append("<div class='paper-item'>"+
                "<div class='paper-head'>"+
                    "<span class='paper-title'><a href='"+like.link+"'>"+like.title+"</a></span>"+
                    "<!-- <span>作者：</span>"+
                    "<span class='paper-author'>"+like.author.toString()+"</span> -->"+
                    "<span class='iconfont icon-shanchu xiaoshi '></span>"+
                "</div>"+
                "<div class='paper-mid'>"+
                    "<span class='mid-word'>关键字：</span>"+
                    "<span class='paper-keyword'>"+like.keyword.toString()+"</span>"+
                "</div>"+
                "<div class='paper-foot'>"+
                    "<span>摘要：</span>"+
                    "<p class='paper-info'>"+like.info+"</p>"+
                "</div>"+
                "<div class='line'></div>"+
            "</div>")})
            $("#reg_wait").css("display","inline-block")
            $(".icon-shanchu").click(function(){
                if(confirm("确定取消收藏吗"))
                {
                    let removeIndex = $(".shixin").index(this)
                    let removeInLike = $(".paper-title").eq(removeIndex).text()
                    $.ajax({
                        url:AJAX_URL.myPaperDelete,
                        data:JSON.stringify({
                            "account" : USER_INFO.userID,
                            "title" : removeInLike
                        }),
                        contentType:"application/json",
                        type:"POST",
                        success:data=>{
                            if(data==true){
                                alert("取消收藏成功")
                                $(".shixin").eq(removeIndex).addClass("xiaoshi")
                                $(".kongxin").eq(removeIndex).removeClass("xiaoshi")
                                location.reload()
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
            this.bindEvents()
        },
        bindEvents:function(){
            var logOut = $(".slide-down li:eq(1)")
            var btnDel = $(".deleteLike")
            var btn = $("#search_btn")
            logOut.click(this.logOutFunc)
            btn.click(this.searchFunc)

            var to_page = $(".changePage:eq(0)")
            to_page.change(this.toPage)
            var left = $("#leftPage")
            var right = $("#rightPage")
            left.click(this.lastPage)
            right.click(this.nextPage)
            var go_to_collect =  $("#empty")
            go_to_collect.click(this.to_collect)
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
                window.open("myPaperList.html?page="+p,"_self")
        },
        lastPage:function(){
            if(thisPage!="1"){
                window.open("myPaperList.html?page="+(parseInt(thisPage)-1),"_self")
            }
        },
        nextPage:function(){
            if(thisPage<maxPage){
                window.open("myPaperList.html?page="+(parseInt(thisPage)+1),"_self")
            }
        },
        to_collect : function(){
            window.open("allPaperList.html?page=1","_self")
        }
    })

    var p = new Page()
    p.init()

})