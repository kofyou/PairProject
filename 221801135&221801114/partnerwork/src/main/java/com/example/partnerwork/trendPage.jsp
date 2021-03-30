<%--
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
<a class="leftpage_btn" href="#"><img src="png/leftpage.png"></a>
<a class="rightpage_btn" href="#"><img src="png/rightpage.png"></a>

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
                out.print("<label for=\"slide_1\" id=\"slide_btn_1\">"+1/*tag的具体名字*/+"</label>\n" +
                        "            <label for=\"slide_2\" id=\"slide_btn_2\">"+1+"</label>\n" +
                        "            <label for=\"slide_3\" id=\"slide_btn_3\">"+1+"</label>\n" +
                        "            <label for=\"slide_4\" id=\"slide_btn_4\">"+1+"</label>\n" +
                        "            <label for=\"slide_5\" id=\"slide_btn_5\">"+1+"</label>\n" +
                        "            <label for=\"slide_6\" id=\"slide_btn_6\">"+1+"</label>\n" +
                        "            <label for=\"slide_7\" id=\"slide_btn_7\">"+1+"</label>\n" +
                        "            <label for=\"slide_8\" id=\"slide_btn_8\">"+1+"</label>\n" +
                        "            <label for=\"slide_9\" id=\"slide_btn_9\">"+1+"</label>\n" +
                        "            <label for=\"slide_10\" id=\"slide_btn_10\">"+1+"</label>");
            %>
        </div>
    </div>
</div>
<script type="text/javascript">
    var chartDom1 = document.getElementById('chartbar1');
    var myChart1 = echarts.init(chartDom1,'dark');
    var option1;

    option1 = 1/*jasoshuzu放在这里*/;
    option1 && myChart1.setOption(option1);

    var chartDom2 = document.getElementById('chartbar2');
    var myChart2 = echarts.init(chartDom2,'dark');
    var option2;

    option2 = 1/*jasoshuzu放在这里*/;
    option2 && myChart2.setOption(option2);

    var chartDom3 = document.getElementById('chartbar3');
    var myChart3 = echarts.init(chartDom3,'dark');
    var option3;

    option3 = 1/*jasoshuzu放在这里*/;
    option3 && myChart3.setOption(option3);

    var chartDom4 = document.getElementById('chartbar4');
    var myChart4 = echarts.init(chartDom4,'dark');
    var option4;

    option4 = 1/*jasoshuzu放在这里*/;
    option4 && myChart4.setOption(option4);

    var chartDom5 = document.getElementById('chartbar5');
    var myChart5 = echarts.init(chartDom5,'dark');
    var option5;

    option5 = 1/*jasoshuzu放在这里*/;
    option5 && myChart5.setOption(option5);

    var chartDom6 = document.getElementById('chartbar6');
    var myChart6 = echarts.init(chartDom6,'dark');
    var option6;

    option6 = 1/*jasoshuzu放在这里*/;
    option6 && myChart6.setOption(option6);

    var chartDom7 = document.getElementById('chartbar7');
    var myChart7 = echarts.init(chartDom7,'dark');
    var option7;

    option7 = 1/*jasoshuzu放在这里*/;
    option7 && myChart7.setOption(option7);

    var chartDom8 = document.getElementById('chartbar8');
    var myChart8 = echarts.init(chartDom8,'dark');
    var option8;

    option8 = 1/*jasoshuzu放在这里*/;
    option8 && myChart8.setOption(option8);

    var chartDom9 = document.getElementById('chartbar9');
    var myChart9 = echarts.init(chartDom9,'dark');
    var option9;

    option9 = 1/*jasoshuzu放在这里*/;
    option9 && myChart9.setOption(option9);

    var chartDom10 = document.getElementById('chartbar10');
    var myChart10 = echarts.init(chartDom10,'dark');
    var option10;

    option10 = 1/*jasoshuzu放在这里*/;
    option10 && myChart10.setOption(option10);
</script>
</body>
</html>