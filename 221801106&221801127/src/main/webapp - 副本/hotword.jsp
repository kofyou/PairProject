<%--
  Created by IntelliJ IDEA.
  User: 1647
  Date: 2021/3/28
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
    <link rel="stylesheet" href="./css/leftStyle.css">
    <link rel="stylesheet" href="./css/right1Style.css">
    <meta charset="utf-8">
    <!-- 引入 ECharts 文件 -->
    <script src="./js/echarts.min.js"></script>
</head>
<body>
<div id = "wrap">
    <div id = "sidebar" style="height: 260%">
        <a href="./login.jsp" id="websitelink" class="lefta">
            <img src="./img/icon.png" alt="官网图标" id = "websiteicon">
            <span id="websitename">论文爬取网</span>
        </a>
        <div id = "selection">
            <a href="./right1.jsp" id="crawl" class="lefta">
                <img src="./img/搜索.png" alt="搜索图标">
                <span>爬取论文</span>
            </a>

            <a href="./right2.jsp" id="list" class="lefta">
                <img src="./img/列表.png" alt="列表图标">
                <span>论文列表</span>
            </a>

            <a href="./keyword.jsp" id="keyword" class="lefta">
                <img src="./img/关键词.png" alt="关键词图标" class="lefta">
                <span>关键词图谱</span>
            </a>

            <a href="./hotword.jsp" id="hotword" class="lefta">
                <img src="./img/热度.png" alt="热度图标" class="lefta">
                <span>热词走势</span>
            </a>

            <a href="./userhome.jsp" id="user" class="lefta">
                <img src="./img/用户.png" alt="用户图标">
                <span>个人中心</span>
            </a>
        </div>
    </div>
    <div id="mymain">
        <div id="main" style="width: 750px;height: 500px; float: left; margin-left: 100px;">CVPR</div>
        <div id="main2" style="width: 750px;height: 500px; float: left; margin-left: 100px;">ECCV</div>
        <div id="main3" style="width: 750px;height: 500px; float: left; margin-left: 100px;">ICCV</div>


        <script type="text/javascript">
            // 基于准备好的dom，初始化echarts实例
            var chartDom = document.getElementById('main');
            var myChart = echarts.init(chartDom);
            var option;

            option = {
                title: {
                    text: 'CVPR热词'
                },
                tooltip: {},
                legend: {
                    data:['热词']
                },
                xAxis: {
                    data: ["CVPR","羊毛衫","雪纺衫","裤子","高跟鞋","袜子","郑龙嗨","张魈用","庄糠择","陈某人"]
                },
                yAxis: {},
                series: [{
                    name: '热词',
                    type: 'bar',
                    data: [5, 20, 36, 10, 10, 20,17,17,26,34]
                }]
            };
            myChart.setOption(option);
        </script>

        <script type="text/javascript">
            // 基于准备好的dom，初始化echarts实例
            var chartDom2 = document.getElementById('main2');
            var myChart2 = echarts.init(chartDom2);
            var option2;

            option2 = {
                title: {
                    text: 'ECCV热词'
                },
                tooltip: {},
                legend: {
                    data:['热词']
                },
                xAxis: {
                    data: ["ECCV","羊毛衫","雪纺衫","裤子","高跟鞋","袜子","郑龙嗨","张魈用","庄糠择","陈某人"]
                },
                yAxis: {},
                series: [{
                    name: '热词',
                    type: 'bar',
                    data: [5, 20, 36, 10, 10, 20,17,17,26,34]
                }]
            };
            myChart2.setOption(option2);
        </script>

        <script type="text/javascript">
            // 基于准备好的dom，初始化echarts实例
            var chartDom3 = document.getElementById('main3');
            var myChart3 = echarts.init(chartDom3);
            var option3;

            option3 = {
                title: {
                    text: 'ICCV热词'
                },
                tooltip: {},
                legend: {
                    data:['热词']
                },
                xAxis: {
                    data: ["ICCV","羊毛衫","雪纺衫","裤子","高跟鞋","袜子","郑龙嗨","张魈用","庄糠择","陈某人"]
                },
                yAxis: {},
                series: [{
                    name: '热词',
                    type: 'bar',
                    data: [5, 20, 36, 10, 10, 20,17,17,26,34]
                }]
            };
            myChart3.setOption(option3);
        </script>

    </div>
</div>
</body>
<footer>

</footer>
</html>