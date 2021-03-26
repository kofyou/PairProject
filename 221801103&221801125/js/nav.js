$(function () {
  if(USER_INFO.name==""&&USER_INFO.userID=="")
    $(".right-part:eq(3) div:eq(0)").html("未登录");
  else if(USER_INFO.username!="")
    $(".right-part:eq(3) div:eq(0)").html(USER_INFO.name);
  else
    $(".right-part:eq(3) div:eq(0)").html(USER_INFO.userID);
  var height = window.innerHeight - 61;
  $(".main").css("height", height);
  if ($(".container").height() > height) {
    $(".right-part:eq(3)").css("margin-right", "5px");
  } else {
    $(".right-part:eq(3)").css("margin-right", "20px");
  }
});
