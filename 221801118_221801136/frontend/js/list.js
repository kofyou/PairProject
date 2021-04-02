$(function (){
    //关键词查询后显示论文
    $("#searchBtn").click(function (){
       var keyword=$("#searchInput").val();
       // var year=$("#searchInput2").val();
       // var source=$("#searchInput3").val();
       var page=$("#page").val();
       //如果为空
       if(keyword==''){
           alert("请输入查找内容");
       }
       //不为空则从后端获得论文数据
        else {
            $.ajax({
                url:"http://127.0.0.1:8080/list/search",
                method:"post",
                data:JSON.stringify({
                    "keyword":keyword,
                    // "year":year,
                    // "source":source,
                    "page":page
                },),
                contentType:"application/json",
                success:function (data){
                    //显示论文数据
                    $.each(data,function(index,data){
                        $(".num").eq(index).text(data.id);
                        $(".link").eq(index).text(data.link);
                        $(".title").eq(index).text("标题："+data.title);
                        $(".keyword").eq(index).text("关键词："+data.keyword);
                        $(".summary").eq(index).text("摘要："+data.abstract);
                        $("a").eq(index).attr('href',data.link);
                    })
                },
                error:function (){
                    alert("页面丢失");
                },
            })
       }
    });
    //收藏点击添加
    $(".collect").eq(0).click(function (){
        var id = $("#lb1").text();
        var user = localStorage.getItem("username")==null ? "":localStorage.getItem("username");
        // var div = $("<div style='width: 100%;height: 20px;float: left;'></div>")
        //     .text("<a href='"+link+"'>"+title+"</a>");
        // $("#empty").append(div);
        $.ajax({
            url:"http://127.0.0.1:8080/list/like/add",
            method: "post",
            data:JSON.stringify({
                "id":id,
                "user":user
            }),
            contentType:"application/json",
            success:function (data){
                if (data.ifSucceed==true)
                alert("收藏成功");
            },
            error:function (){
                alert("页面丢失");
            }
        });

    });
    $(".collect").eq(1).click(function (){
        var id = $("#lb2").text();
        var user = localStorage.getItem("username")==null ? "":localStorage.getItem("username");
        // var div = $("<div style='width: 100%;height: 20px;float: left;'></div>")
        //     .text("<a href='"+link+"'>"+title+"</a>");
        // $("#empty").append(div);
        $.ajax({
            url:"http://127.0.0.1:8080/list/like/add",
            method: "psot",
            data:JSON.stringify({
                "id":id,
                "user":user
            }),
            contentType:"application/json",
            success:function (data){
                if (data.ifSucceed==true)
                    alert("收藏成功");
            },
            error:function (){
                alert("页面丢失");
            }
        });

    });
    $(".collect").eq(2).click(function (){
        var id = $("#lb3").text();
        var user = localStorage.getItem("username")==null ? "":localStorage.getItem("username");
        // var div = $("<div style='width: 100%;height: 20px;float: left;'></div>")
        //     .text("<a href='"+link+"'>"+title+"</a>");
        // $("#empty").append(div);
        $.ajax({
            url:"http://127.0.0.1:8080/list/like/add",
            method: "post",
            data:JSON.stringify({
                "id":id,
                "user":user
            }),
            contentType:"application/json",
            success:function (data){
                if (data.ifSucceed==true)
                    alert("收藏成功");
            },
            error:function (){
                alert("页面丢失");
            }
        });

    });
    $(".collect").eq(3).click(function (){
        var id = $("#lb4").text();
        var user = localStorage.getItem("username")==null ? "":localStorage.getItem("username");
        // var div = $("<div style='width: 100%;height: 20px;float: left;'></div>")
        //     .text("<a href='"+link+"'>"+title+"</a>");
        // $("#empty").append(div);
        $.ajax({
            url:"http://127.0.0.1:8080/list/like/add",
            method: "post",
            data:JSON.stringify({
                "id":id,
                "user":user
            }),
            contentType:"application/json",
            success:function (data){
                if (data.ifSucceed==true)
                    alert("收藏成功");
            },
            error:function (){
                alert("页面丢失");
            }
        });

    });
    //左翻页
    $("#left").click(function (){
        var keyword=$("#searchInput").val();
        var year=$("#searchInput2").val();
        var source=$("#searchInput3").val();
        var page=$("#page").text();
        $.ajax({
            url:'http://127.0.0.1:8080/list/search',
            method:'post',
            data:JSON.stringify({
                "keyword":keyword,
                "page":page
            }),
            contentType:"application/json",
            success:function (data){
                //显示论文数据
                $.each(data,function(index,data){
                    $(".num").eq(index).text(data.id);
                    $(".link").eq(index).text(data.link);
                    $(".title").eq(index).text("标题："+data.title);
                    $(".keyword").eq(index).text("关键词："+data.keyword);
                    $(".summary").eq(index).text("摘要："+data.abstract);
                    $("a").eq(index).attr('href',data.link);
                })
                var pageNum=$("#page").text();
                pageNum++;
                $("#page").text(pageNum);
            },
            error:function (){
                alert("页面丢失");
            },
        })

    });
    //右翻页
    $("#right").click(function (){
        var keyword=$("#searchInput").val();
        var year=$("#searchInput2").val();
        var source=$("#searchInput3").val();
        var page=$("#page").text();
        $.ajax({
            url:'http://127.0.0.1:8080/list/search',
            method:'post',
            data:JSON.stringify({
                "keyword":keyword,
                "year":year,
                "source":source,
                "page":page
            }),
            contentType:"application/json",
            success:function (data){
                //显示论文数据
                $.each(data,function(index,data){
                    $(".num").eq(index).text(data.id);
                    $(".link").eq(index).text(data.link);
                    $(".title").eq(index).text("标题："+data.title);
                    $(".keyword").eq(index).text("关键词："+data.keyword);
                    $(".summary").eq(index).text("摘要："+data.abstract);
                    $("a").eq(index).attr('href',data.link);
                })
                var pageNum=$("#page").text();
                pageNum++;
                $("#page").text(pageNum);
            },
            error:function (){
                alert("页面丢失");
            },
        })

    });
})