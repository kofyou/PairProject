<%@ page import="java.util.List" %>
<% String path = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html lang="en">
<head>
    <title>分析论文</title>
    <script src='js/dist_echarts.js'></script>
    <!-- <script src="../../echarts/dist/echarts.js"></script> -->
    <script src='js/echarts-wordcloud.js'></script>
    <link href="mainCSS.css" type="text/css" rel="stylesheet">
    <link href="analyzeCSS.css" type="text/css" rel="stylesheet">
</head>
<body style="margin: 0px">
<div id="container">
    <div id="heading">
        热 词 分 析 统 计 平 台
    </div>
    <div id="menu">
        <form method="post" id="form" action="<%=path%>/index.jsp" >
            <input type="submit" id="searchText" value="论文列表">
        </form>
        <form method="post" id="form1" action="<%=path%>/iccv.jsp" >
            <input type="submit" id="analyse" value="热度走势">
        </form>
        <form method="post" id="form12" action="<%=path%>/analyze" >
            <input type="submit" id="top" value="TOP10">
        </form>
    </div>
    <div id="contentBody">
        <div id="KeyWordDiv">
            <p id="TOP10title">热词TOP10</p>
            <div id='cloud' style="width: 60%;height: 60%;left: 20%"></div>
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
                                name: '${keyWordList[0]}',
                                value: ${valueList[0]}
                            },
                            {
                                name: '${keyWordList[1]}',
                                value: ${valueList[1]}
                            },
                            {
                                name: '${keyWordList[2]}',
                                value: ${valueList[2]}
                            },
                            {
                                name: '${keyWordList[3]}',
                                value: ${valueList[3]}
                            },
                            {
                                name: '${keyWordList[4]}',
                                value: ${valueList[4]}
                            },
                            {
                                name: '${keyWordList[5]}',
                                value: ${valueList[5]}
                            },
                            {
                                name: '${keyWordList[6]}',
                                value: ${valueList[6]}
                            },
                            {
                                name: '${keyWordList[7]}',
                                value: ${valueList[7]}
                            },
                            {
                                name: '${keyWordList[8]}',
                                value: ${valueList[8]}
                            },
                            {
                                name: '${keyWordList[9]}',
                                value: ${valueList[9]}
                            }
                        ]
                    } ]
                };

                chart.setOption(option);

                chart.on('click',function(params){
                    var name = params.data.name;
                    window.location.href="hello?string="+name;
                    //alert(name);
                    console.log(name);
                });
                window.onresize = chart.resize;
            </script>

        </div>
    </div>
</div>
</body>
</html>

<script>

</script>