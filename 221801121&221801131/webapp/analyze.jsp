<%--
  Created by IntelliJ IDEA.
  User: fjf
  Date: 2021/3/29
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<html>
<head>
    <title>热词分析</title>
    <link rel="icon" href="img/logo.png" sizes="32x32">
    <meta charset="utf-8">
    <script src='https://cdn.jsdelivr.net/npm/echarts@5/dist/echarts.min.js'></script>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src='./js/echarts-wordcloud.js'></script>
    <link rel="stylesheet" href="./css/defalt.css">
</head>


<body id="analyze">
<div class="div-btn">
    <a href="index.jsp">
        <button id="fat-btn" class="btn btn-default" data-loading-text="Loading..." type="button"> 返回
            <span class="glyphicon glyphicon-arrow-left"></span>
        </button>
        <script>
            $(function () {
                $(".btn").click(function () {
                    $(this).button('loading').delay(1000).queue(function () {
                        // $(this).button('reset');
                        // $(this).dequeue();
                    });
                });
            });
        </script>
    </a>
</div>
<div id="div-left">

<div id="hot">
    <h1>热词分析</h1>
    <script type="text/JavaScript">
        function MM_jumpMenu(targ,selObj,restore){ //v3.0
            eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
            if (restore) selObj.selectedIndex=0;
        }
    </script>
    <select name="dgLink2" id="dgLink2" style="WIDTH: 150px" onchange="MM_jumpMenu('parent',this,0)">
        <option value="analyze.jsp">CVPR</option>
        <option value="analyze1.jsp">ECCV</option>
        <option value="analyze2.jsp">ICCV</option>
    </select>
</div>
    <div>
        <div id="main1" style="height: 70% "></div>
        <script type="text/javascript">
            var chartDom = document.getElementById('main1');
            var myChart = echarts.init(chartDom);
            var option1;

            var data = [];
            for (let i = 0; i < 10; ++i) {
                data.push(111);
            }

            option1 = {
                xAxis: {
                    max: 'dataMax',
                },
                yAxis: {
                    type: 'category',
                    data: ['Cameras', 'Training', 'Computer vision', 'Feature extraction', 'Shape', 'Image segmentation', 'Robustness', 'Visualization', '3D displays', 'Layout'],
                    inverse: true,
                    animationDuration: 300,
                    animationDurationUpdate: 300,
                    max: 4,

                },
                series: [{
                    realtimeSort: true,
                    name: 'X',
                    type: 'bar',
                    data: data,
                    label: {
                        show: true,
                        position: 'right',
                        valueAnimation: true
                    }
                }],
                legend: {
                    show: true
                },
                animationDuration: 0,
                animationDurationUpdate: 1000,
                animationEasing: 'linear',
                animationEasingUpdate: 'linear'
            };

            function run() {
                var data = option1.series[0].data;
                data[1] = 654;
                data[2] = 456;
                data[3] = 761;
                data[4] = 437;
                data[5] = 546;
                data[6] = 799;
                data[7] = 964;
                data[8] = 734;
                data[9] = 430;
                data[0] = 1264;
                myChart.setOption(option1);
            }

            function run2() {
                var data = option1.series[0].data;
                data[1] = 1654;
                data[2] = 1456;
                data[3] = 1761;
                data[4] = 1437;
                data[5] = 1546;
                data[6] = 1799;
                data[7] = 964;
                data[8] = 1734;
                data[9] = 1430;
                data[0] = 1164;

                myChart.setOption(option1);
            }
            function run3() {
                var data = option1.series[0].data;
                data[1] = 1254;
                data[2] = 1756;
                data[3] = 1161;
                data[4] = 937;
                data[5] = 1746;
                data[6] = 1699;
                data[7] = 1437;
                data[8] = 964;
                data[9] = 1967;
                data[0] = 1743;

                myChart.setOption(option1);
            }
            function run4() {
                var data = option1.series[0].data;
                data[1] = 1454;
                data[2] = 2156;
                data[3] = 1111;
                data[4] = 837;
                data[5] = 1476;
                data[6] = 1969;
                data[7] = 1977;
                data[8] = 1964;
                data[9] = 1267;
                data[0] = 743;

                myChart.setOption(option1);
            }
            function run5() {
                var data = option1.series[0].data;
                data[8] = 1437;
                data[6] = 964;
                data[9] = 1967;
                data[0] = 1743;
                data[7] = 1254;
                data[5] = 1756;
                data[3] = 1161;
                data[4] = 937;
                data[1] = 1746;
                data[2] = 1699;

                myChart.setOption(option1);
            }
            function run6() {
                var data = option1.series[0].data;
                data[1] = 1254;
                data[2] = 1756;
                data[3] = 1161;
                data[4] = 937;
                data[5] = 1746;
                data[6] = 1699;
                data[7] = 1437;
                data[8] = 964;
                data[9] = 1967;
                data[0] = 1743;

                myChart.setOption(option1);
            }
            function run7() {
                var data = option1.series[0].data;
                data[0] = 1658;
                data[1] = 1189;
                data[2] = 1350;
                data[3] = 804;
                data[4] = 724;
                data[5] = 673;
                data[6] = 932;
                data[7] = 585;
                data[8] = 1044;
                data[9] = 918;

                myChart.setOption(option1);
            }
            function run8() {
                var data = option1.series[0].data;
                data[1] = 1254;
                data[2] = 756;
                data[3] = 861;
                data[4] = 937;
                data[5] = 746;
                data[6] = 699;
                data[7] = 337;
                data[8] = 464;
                data[9] = 967;
                data[0] = 743;

                myChart.setOption(option1);
            }
            function run9() {
                var data = option1.series[0].data;
                data[0] = 1458;
                data[1] = 1289;
                data[2] = 1150;
                data[3] = 1104;
                data[4] = 924;
                data[5] = 873;
                data[6] = 832;
                data[7] = 685;
                data[8] = 644;
                data[9] = 618;

                myChart.setOption(option1);
            }
            setTimeout(function () {
                run();
            }, 1);
            setTimeout(function () {
                run2();
            }, 1200);
            setTimeout(function () {
                run3();
            }, 2400);
            setTimeout(function () {
                run4();
            }, 3600);
            setTimeout(function () {
                run5();
            }, 4800);
            setTimeout(function () {
                run6();
            }, 6000);
            setTimeout(function () {
                run7();
            }, 7200);
            setTimeout(function () {
                run8();
            }, 8400);
            setTimeout(function () {
                run9();
            }, 9600);
            if (option1 && typeof option1 === 'object') {
                myChart.setOption(option1);
            }

        </script>
    </div>
</div>

<div id="div-right">

<div id="cloudtitle">
    <h1>词云图</h1>
</div>
<div id='cloud'></div>
<script>
    var chart = echarts.init(document.getElementById('cloud'));

    var option = {
        tooltip: {},
        series: [ {
            type: 'wordCloud',
            gridSize: 2,
            sizeRange: [30, 50],
            rotationRange: [-90, 90],
            shape: 'pentagon',
            width: 600,
            height: 400,
            drawOutOfBound: true,
            textStyle: {
                color: function () {
                    return 'rgb(' + [
                        Math.round(Math.random() * 255),
                        Math.round(Math.random() * 255),
                        Math.round(Math.random() * 255),
                        0.8
                    ].join(',') + ')';
                }
            },
            emphasis: {
                textStyle: {
                    shadowBlur: 10,
                    shadowColor: '#333'
                }
            },
            data: [
                {
                    name: 'Cameras',
                    value: 2161,
                },
                {
                    name: 'Training',
                    value: 2022
                },
                {
                    name: 'Visualization',
                    value: 1105
                },
                {
                    name: 'Computer vision',
                    value: 1763
                },
                {
                    name: 'Feature extraction',
                    value: 1680
                },
                {
                    name: 'Shape',
                    value: 1415
                },
                {
                    name: 'Image segmentation',
                    value: 1290
                },
                {
                    name: 'Robustness',
                    value: 1265
                },
                {
                    name: 'Three-dimensional displays',
                    value: 1056
                },
                {
                    name: 'Image reconstruction',
                    value: 953
                }
            ]
        } ]
    };

    chart.setOption(option);

    chart.on('click',function(params){
        var name = params.data.name;
        window.location.href="postList?search="+name;
        //alert(name);
        console.log(name);
    });
    window.onresize = chart.resize;
</script>
</div>
</body>
</html>
