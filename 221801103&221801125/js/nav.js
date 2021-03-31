$(function () {
  function Page(){}
  $.extend(Page.prototype,{
    init:function(){
      this.bindEvents()

      if(USER_INFO.name==""&&USER_INFO.userID=="")
      {
        $(".right-part:eq(3) div:eq(0)").html("未登录");
        $("a").css("cursor","not-allowed")
        $("a").attr("href","#")
        $(".right-part:eq(3) ul li:eq(1)").html("去登陆");
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
    },
    bindEvents:function(){
      // var logOut = $(".slide-down li:eq(1)")
      // logOut.click(this.logOutFunc)
    },
    logOutFunc:function(){
      // if(confirm("确定要退出吗")){
      //   window.location.replace("../login&regist/login.html")
      // }
    }
  })
  var p = new Page()
  p.init()
});
