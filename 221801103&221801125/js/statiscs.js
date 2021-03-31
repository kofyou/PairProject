$(function () {
  var names = [];
  var values = [];
  var logOut = $(".slide-down li:eq(1)");
  logOut.click(logOutFunc);
  var btn = $("#search_btn");
  btn.click(searchFunc);
  function searchFunc() {
    let search = $(".search:eq(0)").val();
    if (search != "") {
      window.open(
        "./paper/searchList.html?search=" + search + "&page=1",
        "_self"
      );
    } else {
      window.open("../pages/paper/allPaperList.html?page=1", "_self");
    }
  }

  function logOutFunc() {
    if (confirm("确定要退出吗")) {
      window.location.replace("../login&regist/login.html");
    }
  }
  $.ajax({
    url: AJAX_URL.static,
    dataType: "json",
    type: "post",
    success: (data) => {
      $("#reg_wait").css("display", "none");
      var obj = {
        cvpr: data.cvpr, //cvpr的前5月排行int数组
        iccv: data.iccv,
        eccv: data.eccv,
      };
      HOTTEST_THREE = obj;
      for (let i = 0; i < data.top10.length; i++) {
        names.push(data.top10[i].keyword);
        values.push(data.top10[i].count);
      }

      var height = window.innerHeight - 61;
      var dom = document.getElementsByClassName("container")[0];
      var dom2 = document.getElementsByClassName("wrap")[0];
      document.getElementsByClassName("container")[0].style.height =
        height + "px";
      document.getElementsByClassName("wrap")[0].style.height = height + "px";
      document.getElementsByClassName("wrap")[0].style.top =
        height + 100 + "px";
      var myChart = echarts.init(dom);
      var myChart2 = echarts.init(dom2);
      //var months = ["1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"]
      var myDate = new Date();
      //var mon = myDate.getMonth() + 1;
      var year = myDate.getFullYear();
      var dataMonth = [];
      // if(mon<5)
      // {
      //     for(let k=0;k<5;k++)
      //     {
      //         if(k<(5-mon))
      //             dataMonth.push(months[(12-5+mon+k)])
      //         else
      //             dataMonth.push(months[mon-5+k])
      //     }
      // }else{
      //     for(let l=0;l<5;l++)
      //     {
      //         dataMonth.push(months[mon-5+l])
      //     }
      // }

      var app = {};
      var app2 = {};
      var option, option2;
      option = {
        title: {
          text: "论文数量对比",
        },
        tooltip: {
          trigger: "axis",
        },
        legend: {
          data: ["cvpr", "iccv", "eccv"],
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
          containLabel: true,
        },
        // toolbox: {
        //     feature: {
        //         saveAsImage: {}
        //     }
        // },
        xAxis: {
          type: "category",
          boundaryGap: false,
          data: [year - 5, year - 4, year - 3, year - 2, year - 1],
        },
        yAxis: {
          type: "value",
        },
        series: [
          {
            name: "cvpr",
            type: "line",
            stack: "总量",
            data: HOTTEST_THREE.cvpr,
            //data:[10,10,10,10,10]
          },
          {
            name: "iccv",
            type: "line",
            stack: "总量",
            //data:[10,10,10,10,10]
            data: HOTTEST_THREE.iccv,
          },
          {
            name: "eccv",
            type: "line",
            stack: "总量",
            //data:[10,10,10,10,10]
            data: HOTTEST_THREE.eccv,
          },
        ],
      };

      option2 = {
        title: {
          text: "排名前10数据",
        },
        xAxis: {
          type: "category",
          //data:["1",'2','3','4','5','6','7','8','9','10']
          data: names,
        },
        yAxis: {
          type: "value",
        },
        series: [
          {
            data: values,
            //data: [10,10,10,10,10,10,10,10,10,10],
            type: "bar",
            //url:["www.baidu.com","www.baidu.com","www.baidu.com","www.baidu.com","www.baidu.com","www.baidu.com","www.baidu.com","www.baidu.com","www.baidu.com","www.baidu.com",],
            showBackground: true,
            backgroundStyle: {
              color: "rgba(180, 180, 180, 0.2)",
            },
          },
        ],
        legend: {},
      };
      if (option && typeof option === "object") {
        myChart.setOption(option);
      }
      if (option2 && typeof option === "object") {
        myChart2.setOption(option2);
      }
    },
    error: () => {
      $("#reg_wait").css("display", "none");
      alert("网络出错了，可能有问题");
      $("#empty").removeClass("xiaoshi");
    },
  });
  $("#reg_wait").css("display", "inline-block");
});
