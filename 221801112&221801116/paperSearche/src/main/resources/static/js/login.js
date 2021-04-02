
/**
 * 提示框参数配置
 * @type {{hideEasing: string, positionClass: string, hideDuration: string, debug: boolean, showMethod: string, closeButton: boolean, extendedTimeOut: string, showEasing: string, progressBar: boolean, onclick: null, showDuration: string, hideMethod: string}}
 */
toastr.options = {
    closeButton: false,
    debug: false,
    progressBar: false,
    positionClass: "toast-top-center",
    onclick: null,
    showDuration: "300",
    hideDuration: "1000",
    // timeOut: "1500",
    extendedTimeOut: "1000",
    showEasing: "swing",
    hideEasing: "linear",
    showMethod: "fadeIn",
    hideMethod: "fadeOut"
};

let active = false;

/**
 * 登录验证
 */
$("#login").click(() => {
    let username = $("#login_name").val().trim(), password = $("#login_password").val().trim();
    console.log(username)
    if (username == '' || password == '') {
        toastr.warning('用户名或密码不能为空');
    } else {
        $('#form_login').submit();
    }
})

/**
 * 注册验证
 */
$("#register").click(() => {
    let username = $("#registername").val().trim(), password = $("#registerPassword").val().trim(),surePassword = $("#sureRegisterPassword").val().trim();
    let regLogin = /^[a-zA-Z0-9]{5,15}$/,
        regRegister = /^(?=.*[a-zA-Z])(?=.*[0-9])[A-Za-z0-9]{5,15}$/;
    if (username == '' || password == '') {
        toastr.warning('用户名或密码不能为空');
    } else if (!regLogin.test(username)) {
        toastr.warning('用户名必须由5-15位字母或数字组成！(如test001,test123)');
    } else if (!regRegister.test(password)) {
        toastr.warning('密码必须由6-16位字母和数字组成！(如test123)');
    } else if (password!=surePassword)
    {
        toastr.warning('两次密码不一致');
    }
    else {
        $('#form_register').submit();
    }
})