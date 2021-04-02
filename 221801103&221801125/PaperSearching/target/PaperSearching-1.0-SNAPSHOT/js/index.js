$(function () {
  function Page() {}
  var top = [90, 20, 130, 190, 180, 260, 60, 105, 230, 150];
  var left = [-100, 60, 90, -20, 180, 60, 180, 300, 280, 400];
  var sizes = [10,15,20,25,30,35,40,45,50,55]
  var color = [];
  var strs = [];

  var arr1 = [0,1,2,3,4,5,6,7,8,9];
  var out = [];
  while(arr1.length){
    var index = parseInt(Math.random() * arr1.length);
    out = out.concat( arr1.splice(index, 1) );
  }

  for (var i = 0; i < 10; i++) {
    color.push(randomColor());
  }

  function randomColor() {
    return (
      "#" +
      (function (color) {
        return (color += "5678956789defdef"[Math.floor(Math.random() * 16)]) &&
          color.length == 6
          ? color
          : arguments.callee(color);
      })("")
    );
  }

  $.extend(Page.prototype, {
    init: function () {
      this.bindEvents();
      $.ajax({
        url: AJAX_URL.index,
        type: "post",
        dataType: "json",
        success: (data) => {
          $("#reg_wait").css("display", "none");
          let i = 0;
          $.each(data, function (index, str) {
            let cloud = "#cloud" + (i + 1);
            $(cloud).html(str.keyword);
            $(cloud).css("top", top[out[i]] + 100 + "px");
            $(cloud).css("left", left[out[i]] + 10 + "px");
            $(cloud).css("color", color[i]);
            $(cloud).css("font-size", sizes[i]+"px");
            $(cloud).css("opacity", "1");
            $(cloud).click(function () {
              window.open(
                "../pages/paper/searchList.html?search=" +
                  str.keyword +
                  "&page=1",
                "_self"
              );
            });
            $(cloud).hover(
              function () {
                $(cloud).css("text-shadow", "2px 2px 5px " + color[i]);
              },
              function () {
                $(cloud).css("text-shadow", "none");
              }
            );
            i++;
          });
        },
        error: () => {
          alert("网烂掉了哦");
          $("#reg_wait").css("display", "none");
          $("#empty").removeClass("xiaoshi");
        },
      });
      $("#reg_wait").css("display", "inline-block");
    },
    bindEvents: function () {
      var logOut = $(".slide-down li:eq(1)");
      logOut.click(this.logOutFunc);
      var btn = $("#search_btn");
      btn.click(this.searchFunc);
    },
    logOutFunc: function () {
      if (confirm("确定要退出吗")) {
        window.location.replace("../login&regist/login.html");
      }
    },
    searchFunc: function () {
      let search = $(".search:eq(0)").val();
      if (search != "") {
        window.open(
          "../pages/paper/searchList.html?search=" + search + "&page=1",
          "_self"
        );
      } else {
        window.open("../pages/paper/allPaperList.html?page=1", "_self");
      }
    },
  });

  var page = new Page();
  page.init();
});
