$(document).ready(function () {
    var httpRoot = "http://localhost:8080";
    // var httpRoot = "http://120.24.27.29:8080";

    var NAME_EMPTY = "<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\">\n" +
        "    用户名为空！\n" +
        "    <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
        "        <span aria-hidden=\"true\">&times;</span>\n" +
        "    </button>\n" +
        "</div>";

    var PASSWORD_EMPTY = "<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\">\n" +
        "    密码为空！\n" +
        "    <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
        "        <span aria-hidden=\"true\">&times;</span>\n" +
        "    </button>\n" +
        "</div>";

    // 登录
    $("#").click(function (){
        if (isEmpty()){
            $("body").prepend(NAME_EMPTY);
            return ;
        }
        if (isEmpty()){
            $("body").prepend(PASSWORD_EMPTY);
            return ;
        }
        var account = {
            name:"123456",
            password:"123456"
        }
        $.ajax({
            url:httpRoot + "/account/login",
            type:"POST",
            cache:false,
            data:JSON.stringify(account),
            dataType:"json",
            async:false,
            contentType:"application/json",
            success:function(result){
                if(result == "ok"){
                    window.location.href = "home.html";
                } else {
                    $("body").prepend("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\">\n" +
                        "    " + result + "\n" +
                        "    <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
                        "        <span aria-hidden=\"true\">&times;</span>\n" +
                        "    </button>\n" +
                        "</div>");
                }
            }
        });
    });

    // 注册
    $("#").click(function (){
        if (isEmpty()){
            $("body").prepend(NAME_EMPTY);
            return ;
        }
        if (isEmpty()){
            $("body").prepend(PASSWORD_EMPTY);
            return ;
        }
        var account = {
            name:"123456",
            password:"123456"
        }
        $.ajax({
            url:httpRoot + "/account/register",
            type:"POST",
            cache:false,
            data:JSON.stringify(account),
            dataType:"json",
            async:false,
            contentType:"application/json",
            success:function(result){
                if(result == "ok"){
                    location.reload();
                } else {
                    $("body").prepend("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\">\n" +
                        "    " + result + "\n" +
                        "    <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
                        "        <span aria-hidden=\"true\">&times;</span>\n" +
                        "    </button>\n" +
                        "</div>");
                }
            }
        });
    });


    function isEmpty(obj){
        return typeof obj == "undefined" || obj == null || obj == "";
    }

});
