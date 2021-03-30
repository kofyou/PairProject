<%--
  Created by IntelliJ IDEA.
  User: Flut
  Date: 2021/3/26
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/chartPage.css">
    <title>Chart Page</title>
</head>
<body>
<a class="title_otherPage" href="mainPage.jsp">Crwaler</a>
<a class="leftpage_btn" href="#"><img src="png/leftpage.png"></a>//页面切换到trendPage
<a class="rightpage_btn" href="/partnerwork_war_exploded/ListServlet"><img src="png/rightpage.png"></a>
<div class="main">
    <div class="chartname">热词统计</div>
    <div class="selectbox">
        <input type="checkbox" name="select" id="slide_1" class="checkbox" checked>
        <input type="checkbox" name="select" id="slide_2" class="checkbox" checked>
        <input type="checkbox" name="select" id="slide_3" class="checkbox" checked>
        <input type="checkbox" name="select" id="slide_4" class="checkbox" checked>
        <input type="checkbox" name="select" id="slide_5" class="checkbox" checked>
        <input type="checkbox" name="select" id="slide_6" class="checkbox" checked>
        <input type="checkbox" name="select" id="slide_7" class="checkbox" checked>
        <input type="checkbox" name="select" id="slide_8" class="checkbox" checked>
        <input type="checkbox" name="select" id="slide_9" class="checkbox" checked>
        <input type="checkbox" name="select" id="slide_10" class="checkbox" checked>
        <div class="chart">
            <%
                /*这个地方获取tag列表然后下面每一个地方都要放tag的具体内容，链接跳转到searchServler去进行搜索然后重定向到listPage*/
                /*String tag[10]*/
                out.print("<div class=\"chart_slide\" id=\"slide_chart_1\" onclick=\"document.location.href='/partnerwork_war_exploded/SearchServlet?search="+1+/*tag的具体内容*/"'\">"+1/*tag的具体内容*/+"<br>"+1/*tag词频数量*/+"</div>\n" +
                        "<div class=\"chart_slide\" id=\"slide_chart_2\" onclick=\"document.location.href='/partnerwork_war_exploded/SearchServlet?search="+1+/*tag的具体内容*/"'\">"+1/*tag的具体内容*/+"<br>"+1/*tag词频数量*/+"</div>\n" +
                        "<div class=\"chart_slide\" id=\"slide_chart_3\" onclick=\"document.location.href='/partnerwork_war_exploded/SearchServlet?search="+1+/*tag的具体内容*/"'\">"+1/*tag的具体内容*/+"<br>"+1/*tag词频数量*/+"</div>\n" +
                        "<div class=\"chart_slide\" id=\"slide_chart_4\" onclick=\"document.location.href='/partnerwork_war_exploded/SearchServlet?search="+1+/*tag的具体内容*/"'\">"+1/*tag的具体内容*/+"<br>"+1/*tag词频数量*/+"</div>\n" +
                        "<div class=\"chart_slide\" id=\"slide_chart_5\" onclick=\"document.location.href='/partnerwork_war_exploded/SearchServlet?search="+1+/*tag的具体内容*/"'\">"+1/*tag的具体内容*/+"<br>"+1/*tag词频数量*/+"</div>\n" +
                        "<div class=\"chart_slide\" id=\"slide_chart_6\" onclick=\"document.location.href='/partnerwork_war_exploded/SearchServlet?search="+1+/*tag的具体内容*/"'\">"+1/*tag的具体内容*/+"<br>"+1/*tag词频数量*/+"</div>\n" +
                        "<div class=\"chart_slide\" id=\"slide_chart_7\" onclick=\"document.location.href='/partnerwork_war_exploded/SearchServlet?search="+1+/*tag的具体内容*/"'\">"+1/*tag的具体内容*/+"<br>"+1/*tag词频数量*/+"</div>\n" +
                        "<div class=\"chart_slide\" id=\"slide_chart_8\" onclick=\"document.location.href='/partnerwork_war_exploded/SearchServlet?search="+1+/*tag的具体内容*/"'\">"+1/*tag的具体内容*/+"<br>"+1/*tag词频数量*/+"</div>\n" +
                        "<div class=\"chart_slide\" id=\"slide_chart_9\" onclick=\"document.location.href='/partnerwork_war_exploded/SearchServlet?search="+1+/*tag的具体内容*/"'\">"+1/*tag的具体内容*/+"<br>"+1/*tag词频数量*/+"</div>\n" +
                        "<div class=\"chart_slide\" id=\"slide_chart_10\" onclick=\"document.location.href='/partnerwork_war_exploded/SearchServlet?search="+1+/*tag的具体内容*/"'\">"+1/*tag的具体内容*/+"<br>"+1/*tag词频数量*/+"</div>");
            %>
        </div>
        <div class="slide">
            <label for="slide_1" id="slide_btn_1">TOP 1</label>
            <label for="slide_2" id="slide_btn_2">TOP 2</label>
            <label for="slide_3" id="slide_btn_3">TOP 3</label>
            <label for="slide_4" id="slide_btn_4">TOP 4</label>
            <label for="slide_5" id="slide_btn_5">TOP 5</label>
            <label for="slide_6" id="slide_btn_6">TOP 6</label>
            <label for="slide_7" id="slide_btn_7">TOP 7</label>
            <label for="slide_8" id="slide_btn_8">TOP 8</label>
            <label for="slide_9" id="slide_btn_9">TOP 9</label>
            <label for="slide_10" id="slide_btn_10">TOP 10</label>
        </div>
    </div>
</div>
</body>
</html>
