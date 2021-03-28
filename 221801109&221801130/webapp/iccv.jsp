<% String path = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <!-- 引入 ECharts 文件 -->
    <script src="js/echarts.min.js"></script>
    <style>
        div#heading{
            background-color: dodgerblue;
            width: 100%;
            height: 12%;
            text-align: center;
        }
        div#menu{
            background-color: midnightblue;
            width: 12%;
            height: 88%;
            float: left;
        }
        #searchText{
            background: rgba(0,0,0,0);
            border-width: 0;
            color: white;
            text-align:center;
            font-size: 30px;
            position: absolute;
            top: 20%;
            left: 2%;
        }
        #analyse{
            background: rgba(0,0,0,0);
            border-width: 0;
            color: white;
            text-align:center;
            font-size: 30px;
            position: absolute;
            top: 40%;
            left: 2%;
        }
        #top{
            background: rgba(0,0,0,0);
            border-width: 0;
            color: white;
            text-align:center;
            font-size: 30px;
            position: absolute;
            top: 60%;
            left: 2%;
        }
    </style>
</head>

<body style="margin: 0px">

<div id="heading">

</div>
<div id="menu" style="width:12%;height:88%;float: left;">
    <form method="post" id="form" action="<%=path%>/index.jsp" >
        <input type="submit" id="searchText" value="论文列表">
    </form>
    <form method="post" id="form4" action="<%=path%>/iccv.jsp" >
        <input type="submit" id="analyse" value="热度走势">
    </form>
    <form method="post" id="form5" action="<%=path%>/analyze" >
        <input type="submit" id="top" value="TOP10">
    </form>
</div>
<table>
    <tr>
        <div id="menu1" style="float: left;">
            <form method="post" id="form1" action="<%=path%>/iccv.jsp" >
                <input type="submit" id="ICCV" value="ICCV">
            </form>
            <form method="post" id="form2" action="<%=path%>/eccv.jsp" >
                <input type="submit" id="ECCV" value="ECCV">
            </form>
            <form method="post" id="form3" action="<%=path%>/cvpr.jsp" >
                <input type="submit" id="CVPR" value="CVPR">
            </form>

        </div>
        <div id="main" style="width:84%;height:88%;float: right;"></div>
    </tr>
</table>

<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var yearlist = ['2001','2007','2009','2011','2013','2015','2017','2019'];
    var countryList = ['image','computer','analysis','vision','layout','recognition','detection'];
    var flagIcons = {
        'usa': 'usa.png',
        'chn': 'chn.png',
        'jpn': 'jpn.png',
        'deu': 'deu.png',
        'gbr': 'gbr.png',
        'fra': 'fra.png',
        'ita': 'ita.png',
        'ind': 'ind.png'
    };
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
                    ['year','image','computer','analysis','vision','layout','recognition','detection'],
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
                text: '2000年ICCV走势图',
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
                    data: countryList,
                    axisPointer: {
                        type: 'shadow'
                    },

                    axisLabel: {
                        formatter: function (value) {
                            return '{value|' + value + '}';
                        },
                        margin: 20,
                        rich: {
                            value: {
                                lineHeight: 30,
                                fontSize: 18,
                                align: 'center'
                            },
                            image: {
                                height: 20,
                                align: 'center',
                                backgroundColor: {
                                    image: flagIcons.usa
                                }
                            },
                            computer: {
                                height: 20,
                                align: 'center',
                                backgroundColor: {
                                    image: flagIcons.chn
                                }
                            },
                            analysis: {
                                height: 20,
                                align: 'center',
                                backgroundColor: {
                                    image: flagIcons.jpn
                                }
                            },
                            vision: {
                                height: 20,
                                align: 'center',
                                backgroundColor: {
                                    image: flagIcons.deu
                                }
                            },
                            layout: {
                                height: 20,
                                align: 'center',
                                backgroundColor: {
                                    image: flagIcons.gbr
                                }
                            },
                            recognition: {
                                height: 20,
                                align: 'center',
                                backgroundColor: {
                                    image: flagIcons.fra
                                }
                            },
                            detection: {
                                height: 20,
                                align: 'center',
                                backgroundColor: {
                                    image: flagIcons.ita
                                }
                            },
                            tomography: {
                                height: 20,
                                align: 'center',
                                backgroundColor: {
                                    image: flagIcons.ind
                                }
                            },
                        }
                    }

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

    for (var n = 0; n<yearlist.length; n++){
        option.options.push({
            title:{
                show:true,
                text:yearlist[n]+'年ICCV热度走势',
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