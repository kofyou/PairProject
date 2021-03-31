$(function (){
    $.ajax({
        url:"/user/like",
        method:"post",
        success:function (data){
            $.each(data,function(index,paper){
                var div = "<div class='like' style='100%;height: 20px;'><span class='id'>" +
                    data.id+"</span><a href='"+paper.link+">"+
                    paper.title+"</a><span class='delete'>"+"删除</span></div>";
                $("#empty").append(div);
            })
        }
    })
    $(".delete").click(function(){
        var index = $(this).index();
        var id = $(".id").val();
        var user = localStorage.getItem("username") == null ? "" : localStorage.getItem("username");
        $.ajax({
            url: "/list/like/add",
            method: post,
            data: JSON.stringify({
                "id": id,
                "user": user
            }),
            success: function (data) {
                $(".like").eq(index).css("display","none");
            },
            error: function () {
                alert("页面丢失");
            }
        });
    })


})