$(function(){
    function Page(){}
    $.extend(Page.prototype,{
        init:function(){
            this.bindEvents()
            var search = window.location.toString().split("=").pop()
            $.ajax({
                url:"",
                method:"post",
                data:{
                    "type":0,
                    "str":search
                },
                dataType:"json",
                success:data=>{
                    $.each(data,function(index,paper){
                        $(".container").append("<div class='paper-item'>"+
                        "<div class='paper-head'>"+
                            "<span class='paper-title'><a href='"+paper.link+"'>"+paper.title+"</a></span>"+
                            "<!-- <span>作者：</span>"+
                            "<span class='paper-author'>"+paper.author+"</span> -->"+
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
                },
                error:()=>{
                    alert("网络错误了，请重试")
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