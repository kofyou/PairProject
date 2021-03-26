/**
 *
 * 用户注册
 */
var inputs = document.getElementsByTagName("input");
var hints = document.getElementsByClassName("hint");

console.log(inputs[0].value);

for (let i = 0; i < 3; i++) {
  inputs[i].onchange = () => {
    if (inputs[i].value == "") {
      inputs[i].style.borderBottom = "3px solid #B22222";
    } else {
      inputs[i].style.borderBottom = "3px solid rgb(228,228,228)";
    }
  };
}

document.getElementById("regist").onclick = () => {
  var username_reg;
  var password_reg;
  var re_password_reg;
  username_reg = inputs[0].value;
  password_reg = inputs[1].value;
  re_password_reg = inputs[2].value;

  if (username_reg != "" && password_reg != "" && re_password_reg != "") {
    for (let j = 0; j < 3; j++)
      inputs[j].style.borderBottom = "3px solid rgb(228,228,228)";
    for (let k = 0; k < 3; k++)
      hints[k].getElementsByTagName("span")[0].style.display = "none";
    if (password_reg != re_password_reg) {
      hints[2].getElementsByTagName("span")[0].style.display = "inline";
    } else {
      //向后端发送请求
      //检测账号是否已注册
      $.ajax({
        url: "../RegisterServlet",
        data: {
          userName: inputs[0].value,
          password: inputs[1].value,
        },
        type: "POST",
        success: (data) => {
          $("#reg_word").removeClass("to_none");
          $("#reg_wait").css("display", "none");
          if (data == 1) {
            alert("注册成功,即将跳转到登陆界面");
            USER_INFO.userID = inputs[0].value;
            USER_INFO.password = inputs[1].value;
            setTimeout(() => {
              window.open("./login.html", "_self");
            }, 2000);
          } else if (data == 0) {
            hints[0].getElementsByTagName("span")[0].style.display = "inline";
          } else if (data == 2) {
            alert("注册失败，也不知道为什么");
          }
        },
        error: () => {
          $("#reg_word").removeClass("to_none");
          $("#reg_wait").css("display", "none");
          alert("啊哦，网络可能出了些错误，请稍后重试");
        },
      });
      $("#reg_word").addClass("to_none");
      $("#reg_wait").css("display", "inline-block");
      $("#reg_wait").css("display", "disable");
    }
  } else {
    if (username_reg == "") inputs[0].style.borderBottom = "3px solid #B22222";
    if (password_reg == "") inputs[1].style.borderBottom = "3px solid #B22222";
    if (re_password_reg == "")
      inputs[2].style.borderBottom = "3px solid #B22222";
  }
};
