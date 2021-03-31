$(function (){
    //登录
    $(".btn").eq(0).click(function (){
        var username=$("#user").val();
        var password=$("#password").val();
        //不为空向后端发送ajax
        if(username!=""&&password!=""){
            $.ajax({
                url:"/user/login/check",
                method:"post",
                data:JSON.stringify({
                    "username":username,
                    "password":password
                }),
                contentType:"application/json",
                success:function (data){
                    if(data==true){
                        //登录成功
                        localStorage.setItem("username",username)
                        alert("登录成功");
                    }

                    else{
                            //登陆失败
                        alert("登录失败，请检查登录信息");
                    }
                },
                error:function (){
                    //后端未收到ajax或者没传过去
                    alert("网页丢失，请刷新");
                }
            });

        }

        else{
            alert("请输入内容");
        }
    });
    //注册
    $(".btn").eq(1).click(function (){
        var username=$("#user").val();
        var password=$("#password").val();
        //不为空向后端发送ajax
        if(username!=""&&password!=""){
            $.ajax({
                url:"/user/register/check",
                method:"post",
                data:JSON.stringify({
                    "username":username,
                    "password":password
                }),
                contentType:"application/json",
                success:function (data){
                    if(data==true){
                        //注册成功
                        alert("注册成功");
                    }

                    else{
                        //注册失败
                        alert("注册失败，请重新注册");
                    }
                },
                error:function (){
                    //后端未收到ajax或者没传过去
                    alert("网页丢失，请刷新");
                }
            });

        }

        else{
            alert("请输入内容");
        }
    });

    //切换注册界面
    $("#regist").click(function (){
        $("#loginBtn").css("display","none");
        $("#registBtn").css("display","block");
    });
    //切换登陆界面
    $("#login").click(function (){
        $("#loginBtn").css("display","block");
        $("#registBtn").css("display","none");
    });

})