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
    <script src="https://cdn.staticfile.org/echarts/5.0.2/echarts.js"></script>
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

</body>
</html>
