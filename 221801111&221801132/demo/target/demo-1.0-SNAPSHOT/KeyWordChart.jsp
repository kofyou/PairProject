<%--
  Created by IntelliJ IDEA.
  User: zcdn
  Date: 2021/3/30
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath(); %>
<html>
<head>
    <title>关键词图谱</title>
</head>

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

<body>

<div class="topnav">

    <a href="PaperList.jsp" target="leftFrame"> 论文列表</a>
    <a class="active" href="DataAnalysis.jsp"target="leftFrame"> 数据分析</a>
</div>



<table>
    <br/><br/><br/><br/>

    <form method="post" id="form2" action="<%=path%>/KWCServlet">
        <input type="submit" id="key8" name="button" value="network" />
    </form>

    <form method="post" id="form3" action="<%=path%>/KWCServlet">
        <input type="submit" id="key9" name="button" value="analysis" />
    </form>

    <form method="post" id="form4" action="<%=path%>/KWCServlet">
        <input type="submit" id="key10" name="button" value="computer" />
    </form>

    <br/><br/><br/><br/><br/><br/>

    <form method="post" id="form5" action="<%=path%>/KWCServlet">
        <input type="submit" id="key7" name="button" value="neural" />
    </form>

    <form method="post" id="form6" action="<%=path%>/KWCServlet">
        <input type="submit" id="key1" name="button" value="learning" />
    </form>

    <form method="post" id="form7" action="<%=path%>/KWCServlet">
        <input type="submit" id="key2" name="button" value="vision"/>
    </form>

    <form method="post" id="form8" action="<%=path%>/KWCServlet">
        <input type="submit" id="key3" name="button" value="image" />
    </form>

    <br/><br/><br/><br/><br/><br/>

    <form method="post" id="form9" action="<%=path%>/KWCServlet">
        <input type="submit" id="key6" name="button" value="motion"/>
    </form>

    <form method="post" id="form10" action="<%=path%>/KWCServlet">
        <input type="submit" id="key5" name="button" value="layout" />
    </form>

    <form method="post" id="form11" action="<%=path%>/KWCServlet">
        <input type="submit" id="key4" name="button" value="cameras" />
    </form>
</table>

</body>
</html>
