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
            sizeRange: [50, 100],
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
                    name: 'feature',
                    value: 1795,
                    /*textStyle: {
                        color: 'black'
                    },
                    emphasis: {
                        textStyle: {
                            color: 'red'
                        }
                    }*/
                },
                {
                    name: 'modeling',
                    value: 1812
                },
                {
                    name: 'recognition',
                    value: 1962
                },
                {
                    name: 'detection',
                    value: 2138
                },
                {
                    name: 'cameras',
                    value: 2236
                },
                {
                    name: 'vision',
                    value: 2249
                },
                {
                    name: 'training',
                    value: 2437
                },
                {
                    name: 'computer',
                    value: 2994
                },
                {
                    name: 'analysis',
                    value: 3038
                },
                {
                    name: 'image',
                    value: 6724
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
</body>
</html>
