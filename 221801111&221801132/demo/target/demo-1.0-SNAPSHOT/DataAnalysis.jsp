<%--
  Created by IntelliJ IDEA.
  User: LQ
  Date: 2021/3/23
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<style type="text/css">

    .topnav2 {
        overflow: hidden;
        background-color: white;
    }

    .topnav2 a {
        float: left;
        display: block;
        color: #333333;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
        font-size: 20px;
        font-weight:600;
    }

    .topnav2 a:hover {
        background-color: deepskyblue;
        color: white;
    }

    .topnav2 a.active {
        background-color: white;
        color: deepskyblue;
    }

    .topnav {
        overflow: hidden;
        background-color: #333333;
    }

    .topnav a {
        float: left;
        display: block;
        color: white;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
        font-size: 20px;
        font-weight:600;
    }

    .topnav a:hover {
        background-color: white;
        color: #333333;
    }

    .topnav a.active {
        background-color: white;
        color: #333333;
    }

    div{
        margin:0 auto;
        width:1500px;
        height:55px;
        background-color: #333333;
    }

</style>
<script type = "text/javascript">
</script>

<div class="topnav">

    <a href="PaperList.jsp" target="leftFrame"> 论文列表</a>
    <a class="active" href="DataAnalysis.jsp"target="leftFrame"> 数据分析</a>
</div>

<div class="topnav2">
    <a href="KeyWordChart.jsp" target="leftFrame">关键词图谱</a>
    <a href="Type.jsp" target="leftFrame"> 热词动图</a>
</div>

<body>

</body>
</html>
