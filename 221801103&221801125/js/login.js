$(function(){
    document.getElementById("login").onclick=()=>{
        console.log("12")
    }

    var inputs = document.getElementsByTagName("input");

    document.getElementById("login").onclick = ()=>{
        if(inputs[0].value!="" && inputs[1].value!="")
        {
            //校验,登录
            //window.open("../pages/index.html","_self") 
            $("#login_word").addClass("to_none")
            $("#reg_wait").css("display","inline-block")
        }
        else
        {
            $("#login_word").removeClass("to_none")
            $("#reg_wait").css("display","none")
            if(inputs[0].value=="")
                inputs[0].style.borderBottom = "3px solid #B22222"
            if(inputs[1].value=="")
                inputs[1].style.borderBottom = "3px solid #B22222"
        }
    }
})