<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.partnerwork.Paper" %>
<%@ page import="com.example.partnerwork.PaperDao" %><%--
  Created by IntelliJ IDEA.
  User: Flut
  Date: 2021/3/29
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/trendPage.css">
    <title>Trend Page</title>
    <script src="https://cdn.bootcdn.net/ajax/libs/echarts/5.0.2/echarts.common.js"></script>
</head>
<body>
<a class="title_otherPage" href="mainPage.jsp">Crwaler</a>
<a class="leftpage_btn" href="/partnerwork_war_exploded/ListServlet"><img src="png/leftpage.png"></a>
<a class="rightpage_btn" href="/partnerwork_war_exploded/ChartPageServlet"><img src="png/rightpage.png"></a>

<div class="main">
    <div class="selectbox">
        <input type="radio" name="select" id="slide_1" class="checkbox" checked>
        <input type="radio" name="select" id="slide_2" class="checkbox" >
        <input type="radio" name="select" id="slide_3" class="checkbox" >
        <input type="radio" name="select" id="slide_4" class="checkbox" >
        <input type="radio" name="select" id="slide_5" class="checkbox" >
        <input type="radio" name="select" id="slide_6" class="checkbox" >
        <input type="radio" name="select" id="slide_7" class="checkbox" >
        <input type="radio" name="select" id="slide_8" class="checkbox" >
        <input type="radio" name="select" id="slide_9" class="checkbox" >
        <input type="radio" name="select" id="slide_10" class="checkbox" >
        <div id="chartbar1" class="chartbar"></div>
        <div id="chartbar2" class="chartbar"></div>
        <div id="chartbar3" class="chartbar"></div>
        <div id="chartbar4" class="chartbar"></div>
        <div id="chartbar5" class="chartbar"></div>
        <div id="chartbar6" class="chartbar"></div>
        <div id="chartbar7" class="chartbar"></div>
        <div id="chartbar8" class="chartbar"></div>
        <div id="chartbar9" class="chartbar"></div>
        <div id="chartbar10" class="chartbar"></div>
        <div class="slide">
            <%
                PaperDao paperDao = PaperDao.getInstance();
                if (paperDao.getSortTagList() == null){
                    paperDao.setSortTagList();
                }
                List<HashMap.Entry<String, Integer>> list = paperDao.getSortTagList();
                out.print("<label for=\"slide_1\" id=\"slide_btn_1\">"+list.get(0).getKey()+"</label>\n" +
                        "            <label for=\"slide_2\" id=\"slide_btn_2\">"+list.get(1).getKey()+"</label>\n" +
                        "            <label for=\"slide_3\" id=\"slide_btn_3\">"+list.get(2).getKey()+"</label>\n" +
                        "            <label for=\"slide_4\" id=\"slide_btn_4\">"+list.get(3).getKey()+"</label>\n" +
                        "            <label for=\"slide_5\" id=\"slide_btn_5\">"+list.get(4).getKey()+"</label>\n" +
                        "            <label for=\"slide_6\" id=\"slide_btn_6\">"+list.get(5).getKey()+"</label>\n" +
                        "            <label for=\"slide_7\" id=\"slide_btn_7\">"+list.get(6).getKey()+"</label>\n" +
                        "            <label for=\"slide_8\" id=\"slide_btn_8\">"+list.get(7).getKey()+"</label>\n" +
                        "            <label for=\"slide_9\" id=\"slide_btn_9\">"+list.get(8).getKey()+"</label>\n" +
                        "            <label for=\"slide_10\" id=\"slide_btn_10\">"+list.get(9).getKey()+"</label>");
            %>
        </div>
    </div>
</div>
<script type="text/javascript">
    var chartDom1 = document.getElementById('chartbar1');
    var myChart1 = echarts.init(chartDom1,'dark');
    var option1;

    option1 = {
        legend: {},
        toolbox:{
            show:true,
            feature:{
                restore:{
                    show:true},
                magicType:{
                    type:['line','bar']
                }
            }},
        title: {
            text:'热词趋势'
        },
        tooltip: {},
        xAxis: [
            {type: 'category'},
        ],
        yAxis: {},
        dataset: {
            source: [
                ['product', '2015-2016', '2017-2018', '2019-2020'],
                ['CVPR', 250, 368, 282],
                ['ICCV', 113, 179, 233],
                ['ECCV', 9, 12, 31],
            ]
        },
        series: [
            {type: 'bar', seriesLayoutBy: 'row'},
            {type: 'bar', seriesLayoutBy: 'row'},
            {type: 'bar', seriesLayoutBy: 'row'},
        ]
    }/*jasoshuzu放在这里*/;
    option1 && myChart1.setOption(option1);

    var chartDom2 = document.getElementById('chartbar2');
    var myChart2 = echarts.init(chartDom2,'dark');
    var option2;

    option2 = {
        legend: {},
        toolbox:{
            show:true,
            feature:{
                restore:{
                    show:true},
                magicType:{
                    type:['line','bar']
                }
            }},
        title: {
            text:'热词趋势'
        },
        tooltip: {},
        xAxis: [
            {type: 'category'},
        ],
        yAxis: {},
        dataset: {
            source: [
                ['product', '2015-2016', '2017-2018', '2019-2020'],
                ['CVPR', 232, 254, 228],
                ['ICCV', 92, 118, 145],
                ['ECCV', 1, 0, 3],
            ]
        },
        series: [
            {type: 'bar', seriesLayoutBy: 'row'},
            {type: 'bar', seriesLayoutBy: 'row'},
            {type: 'bar', seriesLayoutBy: 'row'},
        ]
    }/*jasoshuzu放在这里*/;
    option2 && myChart2.setOption(option2);

    var chartDom3 = document.getElementById('chartbar3');
    var myChart3 = echarts.init(chartDom3,'dark');
    var option3;

    option3 = {
        legend: {},
        toolbox:{
            show:true,
            feature:{
                restore:{
                    show:true},
                magicType:{
                    type:['line','bar']
                }
            }},
        title: {
            text:'热词趋势'
        },
        tooltip: {},
        xAxis: [
            {type: 'category'},
        ],
        yAxis: {},
        dataset: {
            source: [
                ['product', '2015-2016', '2017-2018', '2019-2020'],
                ['CVPR', 136, 171, 163],
                ['ICCV', 92, 124, 112],
                ['ECCV', 0, 0, 0],
            ]
        },
        series: [
            {type: 'bar', seriesLayoutBy: 'row'},
            {type: 'bar', seriesLayoutBy: 'row'},
            {type: 'bar', seriesLayoutBy: 'row'},
        ]
    }/*jasoshuzu放在这里*/;
    option3 && myChart3.setOption(option3);

    var chartDom4 = document.getElementById('chartbar4');
    var myChart4 = echarts.init(chartDom4,'dark');
    var option4;

    option4 = {
        legend: {},
        toolbox:{
            show:true,
            feature:{
                restore:{
                    show:true},
                magicType:{
                    type:['line','bar']
                }
            }},
        title: {
            text:'热词趋势'
        },
        tooltip: {},
        xAxis: [
            {type: 'category'},
        ],
        yAxis: {},
        dataset: {
            source: [
                ['product', '2015-2016', '2017-2018', '2019-2020'],
                ['CVPR', 175, 213, 130],
                ['ICCV', 92, 91, 66],
                ['ECCV', 3, 6, 4],
            ]
        },
        series: [
            {type: 'bar', seriesLayoutBy: 'row'},
            {type: 'bar', seriesLayoutBy: 'row'},
            {type: 'bar', seriesLayoutBy: 'row'},
        ]
    }/*jasoshuzu放在这里*/;
    option4 && myChart4.setOption(option4);

    var chartDom5 = document.getElementById('chartbar5');
    var myChart5 = echarts.init(chartDom5,'dark');
    var option5;

    option5 = {
        legend: {},
        toolbox:{
            show:true,
            feature:{
                restore:{
                    show:true},
                magicType:{
                    type:['line','bar']
                }
            }},
        title: {
            text:'热词趋势'
        },
        tooltip: {},
        xAxis: [
            {type: 'category'},
        ],
        yAxis: {},
        dataset: {
            source: [
                ['product', '2015-2016', '2017-2018', '2019-2020'],
                ['CVPR', 147, 141, 79],
                ['ICCV', 84, 107, 88],
                ['ECCV', 0, 2, 2],
            ]
        },
        series: [
            {type: 'bar', seriesLayoutBy: 'row'},
            {type: 'bar', seriesLayoutBy: 'row'},
            {type: 'bar', seriesLayoutBy: 'row'},
        ]
    }/*jasoshuzu放在这里*/;
    option5 && myChart5.setOption(option5);

    var chartDom6 = document.getElementById('chartbar6');
    var myChart6 = echarts.init(chartDom6,'dark');
    var option6;

    option6 = {
        legend: {},
        toolbox:{
            show:true,
            feature:{
                restore:{
                    show:true},
                magicType:{
                    type:['line','bar']
                }
            }},
        title: {
            text:'热词趋势'
        },
        tooltip: {},
        xAxis: [
            {type: 'category'},
        ],
        yAxis: {},
        dataset: {
            source: [
                ['product', '2015-2016', '2017-2018', '2019-2020'],
                ['CVPR', 175, 213, 130],
                ['ICCV', 92, 91, 66],
                ['ECCV', 3, 6, 4],
            ]
        },
        series: [
            {type: 'bar', seriesLayoutBy: 'row'},
            {type: 'bar', seriesLayoutBy: 'row'},
            {type: 'bar', seriesLayoutBy: 'row'},
        ]
    }/*jasoshuzu放在这里*/;
    option6 && myChart6.setOption(option6);

    var chartDom7 = document.getElementById('chartbar7');
    var myChart7 = echarts.init(chartDom7,'dark');
    var option7;

    option7 = {
        legend: {},
        toolbox:{
            show:true,
            feature:{
                restore:{
                    show:true},
                magicType:{
                    type:['line','bar']
                }
            }},
        title: {
            text:'热词趋势'
        },
        tooltip: {},
        xAxis: [
            {type: 'category'},
        ],
        yAxis: {},
        dataset: {
            source: [
                ['product', '2015-2016', '2017-2018', '2019-2020'],
                ['CVPR', 91, 135, 101],
                ['ICCV', 65, 76, 66],
                ['ECCV', 1, 16, 14],
            ]
        },
        series: [
            {type: 'bar', seriesLayoutBy: 'row'},
            {type: 'bar', seriesLayoutBy: 'row'},
            {type: 'bar', seriesLayoutBy: 'row'},
        ]
    }/*jasoshuzu放在这里*/;
    option7 && myChart7.setOption(option7);

    var chartDom8 = document.getElementById('chartbar8');
    var myChart8 = echarts.init(chartDom8,'dark');
    var option8;

    option8 = {
        legend: {},
        toolbox:{
            show:true,
            feature:{
                restore:{
                    show:true},
                magicType:{
                    type:['line','bar']
                }
            }},
        title: {
            text:'热词趋势'
        },
        tooltip: {},
        xAxis: [
            {type: 'category'},
        ],
        yAxis: {},
        dataset: {
            source: [
                ['product', '2015-2016', '2017-2018', '2019-2020'],
                ['CVPR', 70, 139, 80],
                ['ICCV', 40, 69, 67],
                ['ECCV', 2, 1, 3],
            ]
        },
        series: [
            {type: 'bar', seriesLayoutBy: 'row'},
            {type: 'bar', seriesLayoutBy: 'row'},
            {type: 'bar', seriesLayoutBy: 'row'},
        ]
    }/*jasoshuzu放在这里*/;
    option8 && myChart8.setOption(option8);

    var chartDom9 = document.getElementById('chartbar9');
    var myChart9 = echarts.init(chartDom9,'dark');
    var option9;

    option9 = {
        legend: {},
        toolbox:{
            show:true,
            feature:{
                restore:{
                    show:true},
                magicType:{
                    type:['line','bar']
                }
            }},
        title: {
            text:'热词趋势'
        },
        tooltip: {},
        xAxis: [
            {type: 'category'},
        ],
        yAxis: {},
        dataset: {
            source: [
                ['product', '2015-2016', '2017-2018', '2019-2020'],
                ['CVPR', 97, 103, 82],
                ['ICCV', 63, 63, 57],
                ['ECCV', 0, 0, 0],
            ]
        },
        series: [
            {type: 'bar', seriesLayoutBy: 'row'},
            {type: 'bar', seriesLayoutBy: 'row'},
            {type: 'bar', seriesLayoutBy: 'row'},
        ]
    }/*jasoshuzu放在这里*/;
    option9 && myChart9.setOption(option9);

    var chartDom10 = document.getElementById('chartbar10');
    var myChart10 = echarts.init(chartDom10,'dark');
    var option10;

    option10 = {
        legend: {},
        toolbox:{
            show:true,
            feature:{
                restore:{
                    show:true},
                magicType:{
                    type:['line','bar']
                }
            }},
        title: {
            text:'热词趋势'
        },
        tooltip: {},
        xAxis: [
            {type: 'category'},
        ],
        yAxis: {},
        dataset: {
            source: [
                ['product', '2015-2016', '2017-2018', '2019-2020'],
                ['CVPR', 98, 117, 101],
                ['ICCV', 46, 55, 56],
                ['ECCV', 4, 5, 6],
            ]
        },
        series: [
            {type: 'bar', seriesLayoutBy: 'row'},
            {type: 'bar', seriesLayoutBy: 'row'},
            {type: 'bar', seriesLayoutBy: 'row'},
        ]
    }/*jasoshuzu放在这里*/;
    option10 && myChart10.setOption(option10);
</script>
</body>
</html>