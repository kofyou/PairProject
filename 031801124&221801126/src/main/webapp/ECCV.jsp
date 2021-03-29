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

    var yearlist = ['2015','2017','2019'];
    var countryList = ['image','learning','object','feature','computer','analysis','recognition','detection','vision','reconstruction'];
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
                    ['year','image','learning','object','feature','computer','analysis','recognition','detection','vision','reconstruction'],
                    ['2015',9.0628,15.029,14.0325,12.239,8.6502,4.5031,10.4214,11.2701,3,12],
                    ['2017',10.2523,13.2113,14.8875,11.9431,17.6578,8.3622,4.4684,11.1438,6,3],
                    ['2019',13.9364,15.4706,4.1151,7.0686,1.7845,1.4943,10.5149,11.2707,14,7]
                ]
            },

            title: {
                text: '2015年',
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
                        y:'2015'
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

