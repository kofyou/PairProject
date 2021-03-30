<%--
  Created by IntelliJ IDEA.
  User: Charley Chen
  Date: 2021/3/29
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath(); %>
<html>
<head>
    <meta charset="utf-8">
    <script src='./js/echarts.simple.js'></script>
    <script src='./js/echarts-wordcloud.js'></script>
</head>
<body>

<div style="color:#c1cbd7">
    <h1 style="background-color:#656565">
        论文检索系统
    </h1>
</div>

<style>
    html, body, #main {
        width: 100%;
        height: 100%;
        margin: 0;
    }
</style>
<form method="post" id="queryForm" action="<%=path%>/SearchServlet">
    <input type="hidden" name="pTitle" value="${paper.paperTitle}" placeholder="论文标题"/>
    <input type="hidden" name="pKeyword" value="image" placeholder="论文关键词"/>
    <input type="hidden" name="pLink" value="${paper.paperLink}" placeholder="论文链接"/>
    <input type="hidden" name="type" value="0" />
    <input type="hidden" name="pageNum" value="${pageNum}" />
    <input type="hidden" id="changeNum" name="changeNum" value="" />
</form>
<div id='main'></div>
<script>
    var chart = echarts.init(document.getElementById('main'));

    var option = {
        tooltip: {},
        series: [ {
            type: 'wordCloud',
            gridSize: 2,
            sizeRange: [120, 190],
            rotationRange: [-90, 90],
            shape: 'pentagon',
            width: 600,
            height: 400,
            drawOutOfBound: true,
            textStyle: {
                normal: {
                    color: function () {
                        return 'rgb(' + [
                            Math.round(Math.random() * 160),
                            Math.round(Math.random() * 160),
                            Math.round(Math.random() * 160)
                        ].join(',') + ')';
                    }
                },
                emphasis: {
                    shadowBlur: 10,
                    shadowColor: '#333'
                }
            },
            data: [
                {
                    name: 'iamge',url:"SearchServlet?name=image",
                    value: 10000,
                    textStyle: {
                        normal: {
                            color: 'black'
                        },
                        emphasis: {
                            color: 'red'
                        }
                    }
                },
                {
                    name: 'learning',url:"SearchServlet?name=learning",
                    value: 6181
                },
                {
                    name: 'object',url:"SearchServlet?name=object",
                    value: 4386
                },
                {
                    name: 'recognition',url:"SearchServlet?name=recognition",
                    value: 4055
                },
                {
                    name: 'analysis',url:"SearchServlet?name=analysis",
                    value: 2467
                },
                {
                    name: 'detection',url:"SearchServlet?name=detection",
                    value: 2244
                },
                {
                    name: 'computer',url:"SearchServlet?name=computer",
                    value: 1898
                },
                {
                    name: 'feature',url:"SearchServlet?name=feature",
                    value: 1484
                },
                {
                    name: 'vision',url:"SearchServlet?name=vision",
                    value: 1112
                },
                {
                    name: 'segmentation',url:"SearchServlet?name=segmentation",
                    value: 965
                },

            ]
        } ]
    };

    chart.setOption(option);
    chart.on("click",function(e)
    {
        window.open(e.data.url);
    });
    window.onresize = chart.resize;


</script>
</body>
</html>
