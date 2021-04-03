<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath(); %>
<html>
<head>
    <title>关键词图谱</title>
</head>

<style type="text/css">

    #key1 {
        background-color: white;
        border: none;
        color: deepskyblue;
        transform: rotate(30deg);
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 115px;
    }

    #key2 {
        background-color: white;
        border: none;
        color: #ffa931;
        transform: rotate(-10deg);
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 80px;
    }

    #key3 {
        background-color: white;
        border: none;
        color: #5aa469;
        transform: rotate(20deg);
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 70px;
    }

    #key4 {
        background-color: white;
        border: none;
        color: #ff5e78;
        transform: rotate(-40deg);
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 55px;
    }

    #key5 {
        background-color: white;
        border: none;
        color: #16c79a;
        transform: rotate(60deg);
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 50px;
    }

    #key6 {
        background-color: white;
        border: none;
        color: #ffc764;
        transform: rotate(-60deg);
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 60px;
    }

    #key7 {
        background-color: white;
        border: none;
        color: #b8b5ff;
        transform: rotate(10deg);
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 70px;
    }

    #key8 {
        background-color: white;
        border: none;
        color: #d63447;
        transform: rotate(60deg);
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 70px;
    }

    #key9 {
        background-color: white;
        border: none;
        color: #b8de6f;
        transform: rotate(-30deg);
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 100px;
    }

    #key10 {
        background-color: white;
        border: none;
        color: #1b6ca8;
        transform: rotate(-10deg);
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 80px;
    }

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

    #div1{
        margin:0 auto;
        width:1500px;
        height:55px;
        background-color: #333333;
    }

    #div2{
        margin:0 auto;
        width:1500px;
        height:55px;
        background-color: white;
    }

    #div3{
        position: absolute; left: 25%; top: 10%;
    }

</style>
<script type = "text/javascript">
</script>

<body>

<div id="div1" class="topnav">
    <a href="PaperList.jsp" target="leftFrame"> 论文列表</a>
    <a class="active" href="DataAnalysis.jsp"target="leftFrame"> 数据分析</a>
</div>

<div id="div2" class="topnav2">
    <a href="DataAnalysis.jsp" target="leftFrame">返回</a>
</div>

<div id="div3">
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

        <form method="post" id="form8" action="<%=path%>/KWCServlet">
            <input type="submit" id="key3" name="button" value="image" />
        </form>
    </table>
</div>
</body>
</html>
