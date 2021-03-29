<%--
  Created by IntelliJ IDEA.
  User: test1
  Date: 2021/3/29
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="./dist/echarts.js"></script>

</head>
<body>

<div id="main" style="width: 1536px;height:864px;"></div>
<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('main'));

    var yearlist = ['2018','2019','2020'];
    var countryList = ['image','learning','neural','feature','analysis ','object ','segmentation','extraction ','detection ','recognition '];
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
                    ['year','image','learning','neural','feature','analysis ','object ','segmentation','extraction ','detection ','recognition '],
                    ['2018',937 ,370 ,301 ,269 ,251 ,226 ,223 ,217 ,199 ,192],
                    ['2019',1029,
                        653,
                        337,
                        247,
                        250,
                        261,
                        234,
                        177,
                        302,
                        270],
                    ['2020',1360,
                        624,
                        555,
                        305,
                        342,
                        204,
                        136,
                        221,
                        181,
                        170]
                ]
            },

            title: {
                text: '2018年',
                left: 'center',
                textStyle:{
                    fontSize:24
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
            xAxis: [{
                type:'category',
                data: countryList,}
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
                    type: 'bar',
                    seriesLayoutBy: 'row',
                    encode:{
                        x:'year',
                        y:'2018'
                    }
                },
            ]
        },
        options:[]
    };

    for (var n = 0; n<yearlist.length; n++){
        option.options.push({
            title:{
                show:true,
                text:yearlist[n],
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

    myChart.setOption(option);
</script>

</body>
</html>
