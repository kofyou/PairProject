$(function(){
    function Page(){}
    $.extend(Page.prototype,{
        init:function(){
            this.bindEvents()
            $.ajax({
                url:"../../PaperListServlet",
                type:"post",
                data:{
                    "userName":USER_INFO.userID,
                    "type": 1,
                    "str" : ""
                },
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
                },
                error:()=>{
                    alert("网络烂掉了，你什么也看不到了")
                    $("#reg_wait").css("display","none")
                }
            }),
            $("#reg_wait").css("display","inline-block")
            $(".kongxin").click(function(){
                if(confirm("确定要收藏吗"))
                {
                    let addIndex = $(".kongxin").index(this);
                    let addInLike = $(".paper-title").eq(addIndex).text()
                    $.ajax({
                        url:"../../UpdateMyCollectServlet",
                        data:{
                            "account" : USER_INFO.userID,
                            "title" : addInLike
                        },
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
                        url:"../../DeleteMyCollectSevlet",
                        data:{
                            "account" : USER_INFO.userID,
                            "title" : removeInLike
                        },
                        type:"POST",
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
        bindEvents : function(){
            // var btnAdd = $(".addItem")
            // var btnRemove = $(".removeItem")
            // btnAdd.on('click',$.proxy(this.changeToKongxin,this))
            // btnRemove.on('click',$.proxy(this.changeToKongxin,this))
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