<%@ page contentType="text/html;charset=UTF-8"  %>
<% String path = request.getContextPath(); %>

<!DOCTYPE html>
<html>
<head>
    <title>CVPR</title>
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
    <a href="ICCV.jsp" target="leftFrame"> ICCV</a>
    <a href="CVPR.jsp" target="leftFrame">CVPR</a>
</div>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 1280px;height:600px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var yearlist = ['2000','2003','2004','2006','2008','2010','2012','2014','2016','2018','2020'];
    var wordList = ['image','computer','analysis','motion','vision','layout','cameras'];
    var option = {
        timeline:{
            axisType: 'category',
            autoPlay: true,
            playInterval: 1500,
            data: yearlist,
            label:{
                fontSize: 18
            }
        },

        baseOption:{
            dataset:{
                source:[
                    ['year','image','computer','analysis','motion','vision','layout','cameras'],
                    ['2000',216,110,89,65,65,63,58],
                    ['2003',35,39,21,10,45,5,26],
                    ['2004',157,150,63,41,98,37,44],
                    ['2006',158,126,101,24,87,32,39],
                    ['2008',100,70,59,89,58,35,30],
                    ['2010',80,89,30,80,35,25,36],
                    ['2012',48,39,57,86,43,76,23],
                    ['2014',43,78,63,42,46,48,35],
                    ['2016',80,76,46,73,46,85,37],
                    ['2018',100,56,73,38,46,73,46],
                    ['2020',95,73,81,60,75,35,35]
                ]
            },

            title: {
                text: 'CVPR热词热度走势图',
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
                    data: wordList,
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
                        y:'2000'
                    }
                },
            ]
        },
        options:[]
    };

    for (var n = 0; n < yearlist.length; n++){
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
                        y:yearlist[n]
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