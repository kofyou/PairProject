$(function (){
    $.ajax({
        url:"",
        method:"post",
        success:function (data){
            $.each(data,function(index,paper){
                var div = "<div style='width: 100%;height: 20px;'><a href='"+paper.link+">"+
                paper.title+"</a></div>";
                $("#empty").append(div);
            })

        }
    })

})