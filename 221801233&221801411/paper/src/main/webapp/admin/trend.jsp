<%--
  Created by IntelliJ IDEA.
  User: 陈鹏桢
  Date: 2021/3/30
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!--    预览设置-->
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>趋势走向页</title>
    <link rel="stylesheet" href="http://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.css">
    <link rel="stylesheet" href="./css/my.css">
    <script src="http://echarts.baidu.com/build/dist/echarts-all.js" type="text/javascript"></script>
</head>
<body >
<!--    网站导航-->
<!--使用inverted的黑化效果突出导航栏-->
<nav class="ui inverted attached segment my-padded-tb-mini">
    <div class="ui container">
        <div class="ui inverted secondary stackable menu" ><!--                在menu里面加一个条目-->
            <h2 class="ui blue header item ">MyPaper</h2>
            <a href="InfoServlet" class="item" style="width: 7em " ><i class="home icon"></i>首页</a>
            <a href="GraphServlet" class="item" style="width: 10em"><i class="chart pie icon"></i>年度热词图谱</a>
            <a href="TrendServlet" class="item" style="width: 8em"><i class="chart line icon"></i>趋势走向</a>
            <div class="right item " >
                <div class="ui icon inverted transparent input">
                    <form action="InfoServlet">
                        <input placeholder="Search...." type="text" name="searchId" id="searchId"/>
                        <button class="ui secondary button">Search</button>

                    </form>
                </div>
            </div>
        </div>
    </div>
</nav>

<!--    中间内容-->
<div style="text-align:center" class=" my-container-super my-padded-tb-large">
    <div style="margin: 20px auto; width: 500px;">
        <!-- 图一所占空间 -->
        <div id="chart1" style="width: 600px;height:400px; margin-bottom: 10px"></div>
    </div>
    <script type="text/javascript">
        window.onload = function () {
            echart01();
            function echart01() {
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('chart1'),'light');
                var option;
                option = {
                    // title: {
                    //     top:'0',
                    //     text: '热词趋势'
                    // },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: ['CVPR','ICCV','ECCV']
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis: {
                        type: 'category',
                        boundaryGap: false,
                        data: [ '2011', '2012', '2013', '2014', '2015', '2016','2017','2018','2019','2020',]
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [
                        {
                            name: 'ICCV',
                            type: 'line',
                            stack: '总量',
                            data: [120, 132, 101, 134, 90, 230, 210,324,210,222]
                        },
                        {
                            name: 'ECCV',
                            type: 'line',
                            stack: '总量',
                            data: [220, 182, 191, 234, 290, 330, 310,123,435,213]
                        },
                        {
                            name: 'CVPR',
                            type: 'line',
                            stack: '总量',
                            data: [150, 232, 201, 154, 190, 330, 410,231,245,261]
                        }

                    ]
                    // toolbox: {
                    //     feature: {
                    //         saveAsImage: {}
                    //     }
                    // },

                };
                myChart.setOption(option);
                // 使用刚指定的配置项和数据显示图表。
            }

        }
    </script>
</div>

<!--footer-->
<footer class="ui inverted vertical segment ">
    <!--      center aligned将容器内的所有块居中显示-->
    <div class="ui center aligned container">
        <!--            style="width: 100em "-->
        <div class="ui inverted divided stackable grid">
            <div  class="three wide column">
                <div class="ui inverted link list" >
                    <div class="item">
                        <img src="./images/MyIDcard.png" class="ui rounded png" alt="" style="width: 100px">
                    </div>
                </div>
            </div>
            <div  class="three wide column">
                <h4 class="ui inverted header my-opacity-adjust">最新论文</h4>
                <div class="ui inverted link list" >
                    <a href="#" class="item"style="width: 15em ">论文作者故事(Writer Story)</a>
                    <a href="#" class="item"style="width: 15em ">团队合作很重要</a>
                </div>
            </div>
            <div  class="three wide column">
                <h4 class="ui inverted header my-opacity-adjust">联系我们</h4>
                <div class="ui inverted link list" >
                    <a href="#" class="item"style="width: 15em ">Email:3360068374@qq.com</a>
                    <a href="#" class="item"style="width: 15em ">QQ:3360068374</a>
                </div>
            </div>
            <div  class="three wide column">
                <h4 class="ui inverted header my-opacity-adjust" style="width:30em" >网站介绍</h4>
                <p class="my-text-thin my-text-spaced my-opacity-adjust"style="width:35em">我们致力于将来自计算机视觉领域世界三大顶级会议（即CVPR、ICCV和ECCV）的论文进行资源整合并进行数据分析。希望对来访者有所帮助...</p>
            </div>
        </div>
        <div class="ui inverted section divider"></div>
        <p class="my-text-thin my-text-spaced my-opacity-adjust">Copyright by 2021 MyPaperTeam LilCrab&CPZ</p>
    </div>
</footer>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.2/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.js"></script>
</body>
</html>
