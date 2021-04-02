<% String path = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <!-- 引入 ECharts 文件 -->
    <script src="js/echarts.min.js"></script>
    <link rel="stylesheet" type="text/css" href="mainCSS.css">
    <link rel="stylesheet" type="text/css" href="cvCSS.css">
</head>

<body style="margin: 0px">
    <div id="heading">
        热 词 分 析 统 计 平 台
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
    <div id="body">
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
                <div id="main" style="width:88%;height:88%;float: right;"></div>
            </tr>
        </table>
    </div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数据
        var yearlist = ['2016','2018','2020'];
        var countryList = ['learning','image','neural','convolut','network','deep','object'];
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
                        ['year','learning','image','neural','convolut','network','deep','object'],
                        ['2016',81,73,63,55,55,50,42],
                        ['2018',313,160,145,94,138,174,106],
                        ['2020',449,250,141,53,118,150,151]
                    ]
                },

                title: {
                    text: '2016年ECCV走势图',
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
                                learning: {
                                    height: 20,
                                    align: 'center',
                                    backgroundColor: {
                                        image: flagIcons.usa
                                    }
                                },
                                image: {
                                    height: 20,
                                    align: 'center',
                                    backgroundColor: {
                                        image: flagIcons.chn
                                    }
                                },
                                neural: {
                                    height: 20,
                                    align: 'center',
                                    backgroundColor: {
                                        image: flagIcons.jpn
                                    }
                                },
                                convolut: {
                                    height: 20,
                                    align: 'center',
                                    backgroundColor: {
                                        image: flagIcons.deu
                                    }
                                },
                                network: {
                                    height: 20,
                                    align: 'center',
                                    backgroundColor: {
                                        image: flagIcons.gbr
                                    }
                                },
                                deep: {
                                    height: 20,
                                    align: 'center',
                                    backgroundColor: {
                                        image: flagIcons.fra
                                    }
                                },
                                object: {
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
                    text:yearlist[n]+'年ECCV热度走势',
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