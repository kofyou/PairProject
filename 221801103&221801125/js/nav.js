$(function () {
  function Page(){}
  $.extend(Page.prototype,{
    init:function(){
      if(USER_INFO.name==""&&USER_INFO.userID=="")
      {
        $(".right-part:eq(3) div:eq(0)").html("未登录");
        $("a").css("cursor","not-allowed")
        $("a").attr("href","#")
      }
      else if(USER_INFO.name!="")
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
    }
  })
  var p = new Page()
  p.init()
});
