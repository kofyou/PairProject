$(function(){

    var inputs = document.getElementsByTagName("input");

    document.getElementById("login").onclick = ()=>{
        $(".hint span").css("display","none")
        if(inputs[0].value!="" && inputs[1].value!="")
        {
            //校验,登录
            //window.open("../pages/index.html","_self")
            var userInfo = [inputs[0].value,inputs[1].value]
            $.ajax({
                url : "",
                data : userInfo,
                type : "POST",
                dataType : "json",
                success : data =>{
                    $("#login_word").removeClass("to_none")
                    $("#reg_wait").css("display","none")
                    if(data == false){
                        //展示错误信息
                        $(".hint span").css("display","inline")
                    }
                    else{
                        USER_INFO.name = data.name
                        USER_INFO.userID = inputs[0].value
                        USER_INFO.sign = data.info
                        USER_INFO.company = data.company
                    }
                },
                error : ()=>{
                    $("#login_word").removeClass("to_none")
                    $("#reg_wait").css("display","none")
                    alert("啊哦，网络可能出了些错误，请稍后重试")
                }
            })
            $("#login_word").addClass("to_none")
            $("#reg_wait").css("display","inline-block")
        }
        else
        {
            if(inputs[0].value=="")
                inputs[0].style.borderBottom = "3px solid #B22222"
            if(inputs[1].value=="")
                inputs[1].style.borderBottom = "3px solid #B22222"
        }
    }
})