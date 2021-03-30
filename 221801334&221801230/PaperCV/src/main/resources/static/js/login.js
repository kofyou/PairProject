$(document).ready(function () {
    var login_name = $("#loginName");
    var login_password = $("#loginPassword");
    var register_name = $("#registerName");
    var register_password = $("#registerPassword");

    // 登录
    $("#btn-login").click(function (){
        if (isEmpty(login_name.val())){
            alert("用户名为空");
            return ;
        }
        if (isEmpty(login_password.val())){
            alert("密码为空");
            return ;
        }
        var account = {
            name:login_name.val(),
            password:login_password.val()
        }
        $.ajax({
            url:httpRoot + "/account/login",
            type:"POST",
            cache:false,
            data:JSON.stringify(account),
            async:false,
            contentType:"application/json",
            success:function(result){
                if(result == "登录成功"){
                    window.location.href = "home.html";
                } else {
                    alert(result);
                }
            }
        });
    });

    // 注册
    $("#btn-register").click(function (){
        if (isEmpty(register_name.val())){
            alert("用户名为空")
            return ;
        }
        if (isEmpty(register_password.val())){
            alert("密码为空")
            return ;
        }
        if ($("#againPassword").val() != register_password.val()) {
            alert("两次输入的密码不一致");
            return ;
        }
        if (isEmpty($("#email").val())) {
            alert("邮箱为空");
            return ;
        }
        var account = {
            name:register_name.val(),
            password:register_password.val()
        }
        $.ajax({
            url:httpRoot + "/account/register",
            type:"POST",
            cache:false,
            data:JSON.stringify(account),
            async:false,
            contentType:"application/json",
            success:function(result){
                alert(result);
                if(result == "注册成功"){
                    window.location.href = "login.html";
                }
            }
        });
    });


    function isEmpty(obj){
        return typeof obj == "undefined" || obj == null || obj == "";
    }
});

/*各组件根据打开窗口大小  初始化响应一次*/
baseOnWindowSizeToChange();

/*窗口宽度高度发生变化时，触发各组件响应函数*/
$(window).resize(
    function() {
        baseOnWindowSizeToChange();
    }
);

/*窗口宽度高度发生变化时，各组件应当的响应*/
function baseOnWindowSizeToChange() {
    //获取浏览器高度
    var h = $(window).height();
    //获取浏览器宽度
    var w = $(window).width();
    /*console.log("当前浏览器高度为：" + h + "px");
      console.log("当前浏览器宽度为：" + w + "px");*/

    //获取底部单个选项宽度大小并赋给上拉表单宽度
    var dropdownWidth = $(".bottomSelect").width();
    $(".dropdownSelect").css("width", dropdownWidth);

    /*主标题在窗口大于500时显示，小于500时隐藏*/
    if(h < 500) {
        $("#title").fadeOut(700); //id为title的元素0.7秒内消失
    } else {
        $("#title").fadeIn(1200); //id为title的元素1秒内显示
    }

    /*输入框组在浏览器高度小于600时，高度变紧凑，大于600还原*/
    if(h < 600) {
        $(".form-group").css("margin-bottom","0");
    } else {
        $(".form-group").css("margin-bottom","15");
    }

    /* 当浏览器宽度小于1100时：
     * 标题大小 根据浏览器宽度大小变换而变换
     * 登录 注册只显示图标
     *
     * 大于1100时还原
     * */
    if(w < 1100) {
        $("#title").css("font-size", w / 10);
        $("#loginText").text(" ");
        $("#registText").text(" ");
    } else {
        $("#title").css("font-size", "140px");
        $("#loginText").text("登录");
        $("#registText").text("注册");
    }
    if(w < 400) {
        $(".modal-dialog").css("width","90%");
    }else{
        $(".modal-dialog").css("width","350px");
    }
}

/* 打开网易云播放器 */
var isopen = 0;
function openMyMusic() {
    console.log(isopen);
    if(isopen == 0) {
        $(".netease_Player").css("display", "block");
        isopen = 1;
    } else {
        $(".netease_Player").css("display", "none");
        isopen = 0;
    }
}
/* 检查注册名是否合法 */
// function checkUsername(){
//     var name= $('#name').val();
//     console.log(name);
//     if(name!=""){
//         $.ajax({
//             type : "post",
//             async : true,
//             url : 'Manager/login',
//             data : {
//                 name : name,
//             },
//             success:function(data){
//                 $('#check').text(data);
//             }
//         })
//     }else{
//         $('#check').text("");
//     }
// }
