<%@ page contentType="text/html;charset=UTF-8"  %>
<% String path = request.getContextPath(); %>

<!DOCTYPE html>
<html>
<head>
    <title>ECCV</title>
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
    <a href="DataAnalysis.jsp"target="leftFrame"> 数据分析</a>
</div>

<div class="topnav2">
    <a class="active" href="ECCV.jsp" target="leftFrame">ECCV</a>
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
    var year = ['2016','2018','2020'];
    var word = ['Learning','Image','Neural','Convolut','Network','Deep','Object'];
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
                    ['year','Learning','Image','Neural','Convolut','Network','Deep','Object'],
                    ['2016',81,73,63,55,55,50,42],
                    ['2018',313,160,145,94,138,174,106],
                    ['2020',449,250,141,53,118,150,151]
                ]
            },

            title: {
                text: 'ECCV热词热度走势图',
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
                        y:'2016'
                    }
                },
            ]
        },
        options:[]
    };

    for (var n = 0; n < year.length; n++){
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