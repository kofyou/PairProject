<%@ page import="pojo.Keyword" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="pojo.YearFrequency" %>
<%@ page import="dao.*" %><%--
  Created by IntelliJ IDEA.
  User: Lin Minghao
  Date: 2021/3/28
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath(); %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../css/Analysis.css" />
    <script type="text/javascript" src="../dist/echarts.js"></script>
    <script type="text/javascript" src="../dist/echarts-wordcloud.js"></script>
    <%
        KeywordDAO keywordDAO = new KeywordDAOImpl();
        ArrayList<Keyword> keywordList = keywordDAO.GetTop10Keywords();
        ArrayList<YearFrequency> CVPRYearFrequencyList = keywordDAO.getKeywordFrequencyEachYear("CVPR");
        ArrayList<YearFrequency> ECCVYearFrequencyList = keywordDAO.getKeywordFrequencyEachYear("ECCV");
        ArrayList<YearFrequency> ICCVYearFrequencyList = keywordDAO.getKeywordFrequencyEachYear("ICCV");
        YearDAO yearDAO = new YearDAOImpl();
        ArrayList<Integer> yearList = yearDAO.getYears();
        int keywordNum = keywordList.size();
        int yearNum = yearList.size();
    %>
</head>
<body>
<div class="header" >
    <div class="logo_box">
        <img class="logo" src="../img/2.png" >
    </div>
    <div class="navigation_box">
        <div class="nav">
            <ul>
                <li><a href="<%=path%>/paperList.jsp" >论文列表</a></li>
                <li class="nav_study"><a class="a2" href="<%=path%>/paperAnalysisPage/chartPage.jsp" >热门研究</a></li>
            </ul>
        </div>
    </div>
    <div class="box">
        <div class="cloud_box">
            <div class="circle"><span>Top 10 热词词云</span></div>
            <div id='wcloud' style="width: 600px;height:370px;"></div>
            <script>
                var chart = echarts.init(document.getElementById('wcloud'));

                var option = {
                    tooltip: {},
                    series: [ {
                        type: 'wordCloud',
                        gridSize: 2,
                        sizeRange: [12, 50],
                        rotationRange: [-30, 50],
                        shape: 'pentagon',
                        width: 600,
                        height: 400,
                        drawOutOfBound: true,
                        textStyle: {
                            color: function () {
                                return 'rgb(' + [
                                    Math.round(Math.random() * 160),
                                    Math.round(Math.random() * 160),
                                    Math.round(Math.random() * 160)
                                ].join(',') + ')';
                            }
                        },
                        emphasis: {
                            textStyle: {
                                shadowBlur: 10,
                                shadowColor: '#333'
                            }
                        },
                        data: [
                            <%
                                for (Keyword kw:keywordList) {
                                    out.print("{");
                                    out.print("    name:'"+kw.getName()+"',");
                                    out.print("    value:"+kw.getFrequency()+",");
                                    out.print("    emphasis: {");
                                    out.print("        textStyle: {");
                                    out.print("            color: 'red'");
                                    out.print("        }");
                                    out.print("    }");
                                    out.print("},");
                                }
                            %>
                        ]
                    } ]
                };

                chart.setOption(option);

                window.onresize = chart.resize;
                chart.on('click',function(params) {
                    window.location.href = "<%=path%>/PaperListServlet?operation=queryKeyPaper&query=" + params.name;
                });
            </script>
        </div>
    </div>
    <div id="kws_ul_div">
        <ul id="kws_ul">
            <li id="li_0"><%=keywordList.get(0).getName()%></li>
            <li id="li_1"><%=keywordList.get(1).getName()%></li>
            <li id="li_2"><%=keywordList.get(2).getName()%></li>
            <li id="li_3"><%=keywordList.get(3).getName()%></li>
            <li id="li_4"><%=keywordList.get(4).getName()%></li>
            <li id="li_5"><%=keywordList.get(5).getName()%></li>
            <li id="li_6"><%=keywordList.get(6).getName()%></li>
            <li id="li_7"><%=keywordList.get(7).getName()%></li>
            <li id="li_8"><%=keywordList.get(8).getName()%></li>
            <li id="li_9"><%=keywordList.get(9).getName()%></li>
        </ul>
    </div>
</div>


<div class="main">
    <div id="wordbargraph" style="width: 1500px;height:400px;"></div>
    <script type="text/javascript">
        var myChart = echarts.init(document.getElementById('wordbargraph'));

        var categoryData = [
            <% for (Keyword kw:keywordList) {
                out.print("'" + kw.getName() + "',");
            } %>
        ];

        option = {
            title: {
                align: 'center'
            },
            baseOption: {
                timeline: {
                    axisType: 'category',
                    autoPlay: true,
                    playInterval: 1200,
                    data: [
                        <% for (Integer y:yearList) {
                            out.print("'" + y + "',");
                        } %>
                    ]
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                xAxis: {
                    type: 'category',
                    data: categoryData,
                    axisLabel: {
                        interval: 0,
                        textStyle: {
                            fontSize: 10
                        }},
                    splitLine: {show: false}
                },
                yAxis: {
                    type: 'value',
                    name: '热度(出现次数)',
                    max: 250,
                    data: null
                },
                legend: {
                    right:'10%' ,
                    data: ['CVPR', 'ECCV', 'ICCV']
                },
                calculable : true,
                series: [
                    {name: 'CVPR', type: 'bar'},
                    {name: 'ECCV', type: 'bar'},
                    {name: 'ICCV', type: 'bar'},
                ]
            },
            options: [
                <%
                for (int i = 0; i < yearNum; i++) {
                    out.print("{title: {left:'center',text: '" + yearList.get(i) + "年关键词热度'}," + "series: [");

                    out.print("{data: [");
                    for (int j = 0; j < keywordNum; j++) {
                        out.print(CVPRYearFrequencyList.get(i).getFrequency().get(j) + ",");
                    }
                    out.print( "]}," );

                    out.print("{data: [");
                    for (int j = 0; j < keywordNum; j++) {
                        out.print(ECCVYearFrequencyList.get(i).getFrequency().get(j) + ",");
                    }
                    out.print( "]}," );

                    out.print("{data: [");
                    for (int j = 0; j < keywordNum; j++) {
                        out.print(ICCVYearFrequencyList.get(i).getFrequency().get(j) + ",");
                    }
                    out.print( "]}," );

                    out.print("]},");
                }
                %>
            ]
        };

        myChart.setOption(option);
    </script>

</div>
</body>
</html>
