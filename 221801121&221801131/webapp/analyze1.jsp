<%--
  Created by IntelliJ IDEA.
  User: fjf
  Date: 2021/3/29
  Time: 22:40
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
    <a href="JavaScript:history.back(-1)">
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
            <option value="analyze1.jsp">ECCV</option>
            <option value="analyze.jsp">CVPR</option>
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
            for (let i = 0; i < 9; ++i) {
                data.push(111);
            }

            option1 = {
                xAxis: {
                    max: 'dataMax',
                },
                yAxis: {
                    type: 'category',
                    data: ['Deep learning', 'Object detection', 'Semantic segmentation', 'Domain adaptation', '3D reconstruction', 'Unsupervised learning', 'Attention', '3d displays', 'Convolutional neural networks'],
                    inverse: true,
                    animationDuration: 300,
                    animationDurationUpdate: 300,
                    max: 4 // only the largest 3 bars will be displayed
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
                data[1] = 65;
                data[2] = 45;
                data[3] = 76;
                data[4] = 43;
                data[5] = 54;
                data[6] = 79;
                data[7] = 96;
                data[8] = 73;
                data[0] = 12;
                myChart.setOption(option1);
            }

            function run2() {
                var data = option1.series[0].data;
                data[1] = 54;
                data[2] = 56;
                data[3] = 61;
                data[4] = 37;
                data[5] = 46;
                data[6] = 99;
                data[7] = 64;
                data[8] = 34;
                data[0] = 64;

                myChart.setOption(option1);
            }
            function run3() {
                var data = option1.series[0].data;
                data[0] = 33;
                data[1] = 194;
                data[2] = 90;
                data[3] = 80;
                data[4] = 55;
                data[5] = 42;
                data[6] = 42;
                data[7] = 40;
                data[8] = 40;

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
        //window.onresize = chart.resize;
    </script>
</div>
</body>
</html>
