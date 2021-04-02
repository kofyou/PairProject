$(function (){
    $.ajax({
        url:"http://127.0.0.1:8080/user/like",
        method:"post",
        success:function (data){
            $("#name").text=localStorage.getItem("username");
            $.each(data,function(index,paper){
                var div = "<div class='like' style='100%;height: 20px;'><span class='id'>" +
                    data.id+"</span><a class='collectLink'>"+
                    paper.title+"</a><span class='delete'>"+"删除</span></div>";
                $("#empty").append(div);
                $("a.collectLink").eq(index).attr('href',data.link);
            })
        }
    })
    $(".delete").click(function(){
        var index = $(this).index();
        var id = $(".id").val();
        var user = localStorage.getItem("username") == null ? "" : localStorage.getItem("username");
        $.ajax({
            url: "http://127.0.0.1:8080/list/like/add",
            method: post,
            data: JSON.stringify({
                "id": id,
                "user": user
            }),
            contentType:"application/json",
            success: function (data) {
                $(".like").eq(index).css("display","none");
            },
            error: function () {
                alert("页面丢失");
            }
        });
    })


})