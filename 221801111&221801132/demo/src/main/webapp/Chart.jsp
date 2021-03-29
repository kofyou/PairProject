<%--
  Created by IntelliJ IDEA.
  User: zcdn
  Date: 2021/3/29
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <head>
        <meta charset="utf-8">
        <!-- 引入 ECharts 文件 -->
        <script src="${pageContext.request.contextPath}/dist/echarts.js"></script>
    </head>
</head>

<script type = "text/javascript">
</script>

<div id="div1" class="topnav">
    <a href="PaperList.jsp" target="leftFrame"> 论文列表</a>
    <a class="active" href="DataAnalysis.jsp" target="leftFrame"> 数据分析</a>
    <a href="CHart.jsp"target="leftFrame"> 图表</a>
</div>
<body>

<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '第一个 ECharts 实例'
        },
        tooltip: {},
        legend: {
            data:['销量']
        },
        xAxis: {
            data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
        },
        yAxis: {},
        series: [{
            name: '销量',
            type: 'bar',
            data: [5, 20, 36, 10, 10, 20]
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>

</body>
</html>
