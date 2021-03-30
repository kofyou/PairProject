<%--
  Created by IntelliJ IDEA.
  User: 陈鹏桢
  Date: 2021/3/30
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" import="java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Chart page</title>
    <link rel="stylesheet" href="http://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.css">
    <link rel="stylesheet" href="./css/my.css">
    <!--    <link rel="stylesheet" href="./css/chartPage.css">-->
    <!-- 引入 echarts.js -->
    <script src="http://echarts.baidu.com/build/dist/echarts-all.js" type="text/javascript"></script>
</head>
<body >
<!--    网站导航-->
<!--使用inverted的黑化效果突出导航栏-->
<div>
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
</div>



<div style="margin: 20px auto; width: 500px;">
    <!-- 图一所占空间 -->
    <div id="chart1" style="width: 600px;height:400px; margin-bottom: 20px"></div>

    <!-- 图二所占空间 -->
    <div id="chart2" style="width: 600px;height:500px;"></div>

</div>
<script type="text/javascript">
    window.onload = function () {
        echart01();
        echart02();
        function echart01() {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('chart1'),'light');

            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '热词top10'
                },
                tooltip: {},
                legend: {
                    data: ['个数']
                },
                xAxis: {
                    data: ["Facial Landmark", "Surface", "3-D acition", "Object motion", "Face detection", "Forward Passstereo","video caption", "Image Retrieval","Human parsing", "Face alignment",]
                },
                yAxis: {},
                series: [{
                    name: '热词搜索次数',
                    type: 'bar',
                    data: [
                        { value:198,  name:"Facial Landmark",url:"SearchServlet?id=241"},
                        { value:176,  name:"Surface",url:"SearchServlet?id=114"},
                        { value:389,  name:"3-D acition",url:"SearchServlet?id=267"},
                        { value:451,  name:"Object motion",url:"SearchServlet?id=273"},
                        { value:226,  name:"Face detection",url:"SearchServlet?id=102"},
                        { value:115,  name:"Forward Passstereo",url:"SearchServlet?id=290"},
                        { value:68,  name:"video caption",url:"SearchServlet?id=292"},
                        { value:97,  name:"Image Retrieval",url:"SearchServlet?id=160"},
                        { value:78,  name:"Human parsing",url:"SearchServlet?id=938"},
                        { value:54,  name:"Face alignment",url:"SearchServlet?id=339"},
                    ]
                }]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            myChart.on("click",function(e){
                console.log(e);
                window.open(e.data.url,'_self');
            });
        }
        function echart02() {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('chart2'),"light");
            // var myChart = echarts.init(chartDom);
            var option;
            option = {
                tooltip: {
                    trigger: 'item'
                },
                lengend:{
                    data:['搜索次数'],
                    top:'5%',
                    left:'center'

                },
                series: [
                    {
                        name: '搜索次数',
                        type: 'pie',
                        radius: ['40%', '70%'],
                        avoidLabelOverlap: false,
                        itemStyle: {
                            borderRadius: 10,
                            borderColor: '#fff',
                            borderWidth: 2
                        },
                        label: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            label: {
                                show: true,
                                fontSize: '40',
                                fontWeight: 'bold'
                            }
                        },
                        labelLine: {
                            show: false
                        },
                        data: [
                            { value:198,  name:"Facial Landmark",url:"SearchServlet?id=241"},
                            { value:176,  name:"Surface",url:"SearchServlet?id=114"},
                            { value:389,  name:"3-D acition",url:"SearchServlet?id=267"},
                            { value:451,  name:"Object motion",url:"SearchServlet?id=273"},
                            { value:226,  name:"Face detection",url:"SearchServlet?id=102"},
                            { value:115,  name:"Forward Passstereo",url:"SearchServlet?id=290"},
                            { value:68,  name:"video caption",url:"SearchServlet?id=292"},
                            { value:97,  name:"Image Retrieval",url:"SearchServlet?id=160"},
                            { value:78,  name:"Human parsing",url:"SearchServlet?id=938"},
                            { value:54,  name:"Face alignment",url:"SearchServlet?id=339"},
                        ]
                    }
                ]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            myChart.on("click",function(e){
                console.log(e);
                window.open(e.data.url,'_self');
            });
        }
    }
</script>

<div>
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
</div>

</body>
</html>
