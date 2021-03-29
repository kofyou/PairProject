<%--
  Created by IntelliJ IDEA.
  User: LQ
  Date: 2021/3/23
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  %>
<!DOCTYPE html>
<html>
<style type="text/css">
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

    #div1{
        margin:0 auto;
        width:1500px;
        height:55px;
        background-color: #333333;
    }


</style>
<script type = "text/javascript">
</script>

<div id="div1" class="topnav">
    <a href="PaperList.jsp" target="leftFrame"> 论文列表</a>
    <a class="active" href="DataAnalysis.jsp" target="leftFrame"> 数据分析</a>
    <a href="Chart.jsp"target="leftFrame"> 图表</a>
</div>

<body>



</body>
</html>
