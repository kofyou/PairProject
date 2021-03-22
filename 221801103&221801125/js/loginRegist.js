console.log(USER_INFO.name);


document.getElementById("regist").onclick=()=>{
    var username_reg;
    var password_reg;
    var re_password_reg;
    username_reg = document.getElementsByTagName("input")[0].value;
    password_reg = document.getElementsByTagName("input")[1].value;
    re_password_reg = document.getElementsByTagName("input")[2].value;
    
    if(username_reg!=""&&password_reg!=""&&re_password_reg){
        document.getElementById("inform").setAttribute("display","block");
    }else{
        console.log("请输入信息");
    }
}