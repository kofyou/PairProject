$(function(){

    function Page(){}
    $.extend(Page.prototype,{
        init:function(){
            //发个ajax数据
            $.ajax({
                url : "../../MyCollectServlet",
                type : "post",
                data : JSON.stringify({
                    "type" : 0,
                    "userName" : USER_INFO.userID
                }),
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
                },
                error:()=>{
                    alert("网络不好，什么都看不到")
                    $(".deleteLike").addClass("xiaoshi")
                    $("#reg_wait").css("display","none")
                }
            })
            $("#reg_wait").css("display","inline-block")
            $(".icon-shanchu").click(function(){
                if(confirm("确定取消收藏吗"))
                {
                    let removeIndex = $(".shixin").index(this)
                    let removeInLike = $(".paper-title").eq(removeIndex).text()
                    $.ajax({
                        url:"../../DeleteMyCollectServlet",
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
              window.open("searchList.html?search="+search,"_self")
            }
            else
            {
              window.open("allPaperList.html","_self")
            }
          }
    })

    var p = new Page()
    p.init()

})