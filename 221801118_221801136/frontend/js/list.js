$(function (){
    //关键词查询后显示论文
    $("#searchBtn").click(function (){
       var keyword=$("#searchInput").val();
       var year=$("#searchInput2").val();
       var source=$("#searchInput3").val();
       var page=$("#page").val();
       //如果为空
       if(keyword==''&&year==''&&source==''){
           alert("请输入查找内容");
       }
       //不为空则从后端获得论文数据
        else {
            $.ajax({
                url:"/list/search",
                method:"post",
                data:JSON.stringify({
                    "keyword":keyword,
                    "year":year,
                    "source":source,
                    "page":page
                },),
                success:function (data){
                    //显示论文数据
                    for (var i=0;i<4;i++){
                        $(".num").eq(i).text=data[i].id;
                        $(".link").eq(i).text=data[i].link;
                        $(".title").eq(i).text=data[i].title;
                        $(".keyword").eq(i).text=data[i].keyword;
                        $(".summary").eq(i).text=data[i].abstract;
                    }
                },
                error:function (){
                    alert("页面丢失");
                },
            })
       }
    });
    //收藏点击添加
    $(".collect").eq(0).click(function (){
        var id = $("#lb1").val();
        var user = localStorage.getItem("username")==null ? "":localStorage.getItem("username");
        // var div = $("<div style='width: 100%;height: 20px;float: left;'></div>")
        //     .text("<a href='"+link+"'>"+title+"</a>");
        // $("#empty").append(div);
        $.ajax({
            url:"/list/like/add",
            method: post,
            data:JSON.stringify({
                "id":id,
                "user":user
            }),
            success:function (data){
                if (data==true)
                alert("收藏成功");
            },
            error:function (){
                alert("页面丢失");
            }
        });

    });
    $(".collect").eq(1).click(function (){
        var id = $("#lb2").val();
        var user = localStorage.getItem("username")==null ? "":localStorage.getItem("username");
        // var div = $("<div style='width: 100%;height: 20px;float: left;'></div>")
        //     .text("<a href='"+link+"'>"+title+"</a>");
        // $("#empty").append(div);
        $.ajax({
            url:"/list/like/add",
            method: post,
            data:JSON.stringify({
                "id":id,
                "user":user
            }),
            success:function (data){
                if (data==true)
                    alert("收藏成功");
            },
            error:function (){
                alert("页面丢失");
            }
        });

    });
    $(".collect").eq(2).click(function (){
        var id = $("#lb3").val();
        var user = localStorage.getItem("username")==null ? "":localStorage.getItem("username");
        // var div = $("<div style='width: 100%;height: 20px;float: left;'></div>")
        //     .text("<a href='"+link+"'>"+title+"</a>");
        // $("#empty").append(div);
        $.ajax({
            url:"/list/like/add",
            method: post,
            data:JSON.stringify({
                "id":id,
                "user":user
            }),
            success:function (data){
                if (data==true)
                    alert("收藏成功");
            },
            error:function (){
                alert("页面丢失");
            }
        });

    });
    $(".collect").eq(3).click(function (){
        var id = $("#lb4").val();
        var user = localStorage.getItem("username")==null ? "":localStorage.getItem("username");
        // var div = $("<div style='width: 100%;height: 20px;float: left;'></div>")
        //     .text("<a href='"+link+"'>"+title+"</a>");
        // $("#empty").append(div);
        $.ajax({
            url:"/list/like/add",
            method: post,
            data:JSON.stringify({
                "id":id,
                "user":user
            }),
            success:function (data){
                if (data==true)
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
        var page=$("#page").val();
        $.ajax({
            url:'/list/search',
            method:'post',
            data:JSON.stringify({
                "keyword":keyword,
                "year":year,
                "source":source,
                "page":page
            }),
            success:function (data){
                //显示论文数据
                for (var i=0;i<4;i++){
                    $(".num").eq(i).text=data[i].id;
                    $(".link").eq(i).text=data[i].link;
                    $(".title").eq(i).text=data[i].title;
                    $(".keyword").eq(i).text=data[i].keyword;
                    $(".summary").eq(i).text=data[i].abstract;
                }
                $("#page").text+=1;
            },
            error:function (){
                alert("页面丢失");
            },
        })

    });
    //右翻页
    $("#left").click(function (){
        var keyword=$("#searchInput").val();
        var year=$("#searchInput2").val();
        var source=$("#searchInput3").val();
        var page=$("#page").val();
        $.ajax({
            url:'/list/search',
            method:'post',
            data:JSON.stringify({
                "keyword":keyword,
                "year":year,
                "source":source,
                "page":page
            }),
            success:function (data){
                //显示论文数据
                for (var i=0;i<4;i++){
                    $(".num").eq(i).text=data[i].id;
                    $(".link").eq(i).text=data[i].link;
                    $(".title").eq(i).text=data[i].title;
                    $(".keyword").eq(i).text=data[i].keyword;
                    $(".summary").eq(i).text=data[i].abstract;
                }
                $("#page").text-=1;
            },
            error:function (){
                alert("页面丢失");
            },
        })

    });
})