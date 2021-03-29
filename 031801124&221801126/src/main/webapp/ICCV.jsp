<%--
  Created by IntelliJ IDEA.
  User: test1
  Date: 2021/3/29
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ICCV</title>
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
                    ['2015',1125,
                        277,
                        271,
                        260,
                        245,
                        237,
                        228,
                        221,
                        220,
                        190

                    ],
                    ['2017',1268,
                        529,
                        356,
                        355,
                        306,
                        282,
                        245,
                        290,
                        301,
                        175
                    ],
                    ['2019',1242,
                        704,
                        398,
                        415,
                        252,
                        398,
                        314,
                        334,
                        227,
                        116
                    ]
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
