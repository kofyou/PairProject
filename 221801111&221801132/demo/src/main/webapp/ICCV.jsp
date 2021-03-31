<%@ page contentType="text/html;charset=UTF-8"  %>
<% String path = request.getContextPath(); %>

<!DOCTYPE html>
<html>
<head>
    <title>ICCV</title>
    <meta charset="utf-8">
    <!-- 引入 ECharts 文件 -->
    <script src="dist/echarts.min.js"></script>

    <style type="text/css">

        .topnav2 {
            overflow: hidden;
            background-color: white;
        }

        .topnav2 a {
            float: left;
            display: block;
            color: #333333;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 20px;
            font-weight:600;
        }

        .topnav2 a:hover {
            background-color: deepskyblue;
            color: white;
        }

        .topnav2 a.active {
            background-color: white;
            color: deepskyblue;
        }

        .topnav {
            overflow: hidden;
            background-color: #333333;
        }

        .topnav a {
            float: left;
            display: block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 20px;
            font-weight:600;
        }

        .topnav a:hover {
            background-color: white;
            color: #333333;
        }

        .topnav a.active {
            background-color: white;
            color: #333333;
        }

        #di2{
            margin:0 auto;
            width:1500px;
            height:55px;
            background-color: #333333;
        }
    </style>
</head>

<body>

<div id="di2" class="topnav">
    <a href="PaperList.jsp" target="leftFrame"> 论文列表</a>
    <a class="active" href="DataAnalysis.jsp"target="leftFrame"> 数据分析</a>
</div>

<div class="topnav2">
    <a href="ECCV.jsp" target="leftFrame">ECCV</a>
    <a href="ICCV.jsp" target="leftFrame">ICCV</a>
    <a href="CVPR.jsp" target="leftFrame">CVPR</a>
    <a href="DataAnalysis.jsp" target="leftFrame">返回</a>
</div>

<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 1280px;height:600px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var year = ['2001','2007','2009','2011','2013','2015','2017','2019'];
    var word = ['Image','Computer','Analysis','Vision','Layout','Recogn','Detection'];
    var option = {
        timeline:{
            axisType: 'category',
            autoPlay: true,
            playInterval: 1500,
            data: year,
            label:{
                fontSize: 18
            }
        },

        baseOption:{
            dataset:{
                source:[
                    ['year','Image','Computer','Analysis','Vision','Layout','Recogn','Detection'],
                    ['2001',115,76,26,45,39,26,25],
                    ['2007',249,143,117,103,81,81,78],
                    ['2009',404,341,162,279,162,111,140],
                    ['2011',272,150,106,41,32,20,36],
                    ['2013',158,126,101,24,87,32,39],
                    ['2011',273,120,136,61,82,10,56],
                    ['2017',102,80,59,59,48,20,30],
                    ['2019',200,78,63,42,46,48,35],
                ]
            },

            title: {
                text: 'ICCV热词热度走势图',
                left: 'center',
                textStyle:{
                    fontSize:15
                }
            },
            tooltip: {
                trigger: 'axis'
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            legend: {
            },
            grid: {
                left: '10%',
                bottom: '15%',
                containLabel: true
            },
            xAxis: [
                {
                    type:'category',
                    data: word,
                    axisPointer: {
                        type: 'shadow'
                    },
                },
            ],
            yAxis:[
                {
                    name:'热度',
                    type:'value',
                    nameTextStyle:{
                        fontSize:18
                    },
                    axisLabel:{
                        fontSize:18
                    }
                },
            ],
            series: [
                {
                    //name: 'GDP',
                    type: 'bar',
                    seriesLayoutBy: 'row',
                    encode:{
                        x:'year',
                        y:'2001'
                    }
                },
            ]
        },
        options:[]
    };

    for (var n = 0; n<year.length; n++){
        option.options.push({
            title:{
                show:true,
                left: 'center',
                textStyle:{
                    fontSize:24
                }
            },
            series:[
                {
                    type: 'bar',
                    seriesLayoutBy: 'row',
                    encode:{
                        x:'year',
                        y:year[n]
                    }
                },
            ]
        });
    }

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>

</body>
</html>