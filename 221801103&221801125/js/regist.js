/**
 * 
 * 用户注册
 */
var inputs = document.getElementsByTagName("input");
var hints = document.getElementsByClassName("hint");

console.log(inputs[0].value);


for(let i = 0 ;i<3;i++){
    inputs[i].onchange = ()=>{
        if(inputs[i].value == "")
        {
            inputs[i].style.borderBottom = "3px solid #B22222"
        }
        else
        {
            inputs[i].style.borderBottom = "3px solid rgb(228,228,228)"
        }
    }
}



document.getElementById("regist").onclick=()=>{
    var username_reg;
    var password_reg;
    var re_password_reg;
    username_reg = inputs[0].value;
    password_reg = inputs[1].value;
    re_password_reg = inputs[2].value;
    
    if(username_reg!=""&&password_reg!=""&&re_password_reg!=""){
        for(let j=0;j<3;j++)
            inputs[j].style.borderBottom = "3px solid rgb(228,228,228)"
        for(let k=0;k<3;k++)
            hints[k].getElementsByTagName("span")[0].style.display = "none"
        if(password_reg!=re_password_reg){
            hints[2].getElementsByTagName("span")[0].style.display = "inline";
        }
        else{
            //向后端发送请求
            //检测账号是否已注册
            var ele = document.getElementById("inform")
            ele.style.height = "100px";
        }
    }else{
        if(username_reg=="")
            inputs[0].style.borderBottom = "3px solid #B22222"
        if(password_reg=="")
            inputs[1].style.borderBottom = "3px solid #B22222"
        if(re_password_reg=="")
            inputs[2].style.borderBottom = "3px solid #B22222"
    }
}