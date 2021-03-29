<%@ page import="pojo.Paper" %>
<%@ page import="dao.PaperDAO" %>
<%@ page import="dao.PaperDAOImpl" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="pojo.Keyword" %>
<%@ page import="dao.KeywordDAO" %>
<%@ page import="dao.KeywordDAOImpl" %><%--
  Created by IntelliJ IDEA.
  User: Lin Minghao
  Date: 2021/3/28
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="./dist/echarts.js"></script>
    <script type="text/javascript" src="./dist/echarts-wordcloud.js"></script>

    <title>Title</title>
    <%
        KeywordDAO keywordDAO = new KeywordDAOImpl();
        ArrayList<Keyword> keywordList = keywordDAO.GetTop10Keywords();
    %>
</head>
<body>

<div id='wcloud' style="width: 600px;height:350px;"></div>
<script>
    var chart = echarts.init(document.getElementById('wcloud'));

    var option = {
        tooltip: {},
        series: [ {
            type: 'wordCloud',
            gridSize: 2,
            sizeRange: [12, 50],
            rotationRange: [-90, 90],
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
</script>

</body>
</html>
