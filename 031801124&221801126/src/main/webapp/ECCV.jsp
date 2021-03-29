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
    <title>ECCV</title>
    <script src="./dist/echarts.js"></script>

</head>
<body>

<div style="color:#c1cbd7">
    <h1 style="background-color:#656565">
        论文检索系统
    </h1>
</div>

<div id="main" style="width: 1536px;height:864px;"></div>
<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('main'));

    var yearlist = ['2016','2018','2020'];
    var countryList = ['learning','image','neural','convolutional','network ','deep','object ','detection','segmentation ','networks'];
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
                    ['year','learning','image','neural','convolutional','network ','deep','object ','detection','segmentation ','networks'],
                    ['2016',81,
                        73,
                        63,
                        55,
                        55,
                        50,
                        42,
                        39,
                        32,
                        31
                    ],
                    ['2018',281,
                        148,
                        127,
                        90,
                        107,
                        164,
                        100,
                        108,
                        90,
                        127
                    ],
                    ['2020',416,
                        228,
                        130,
                        48,
                        97,
                        126,
                        147,
                        177,
                        115,
                        111
                    ]
                ]
            },

            title: {
                text: '2016年',
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
                        y:'2016'
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

