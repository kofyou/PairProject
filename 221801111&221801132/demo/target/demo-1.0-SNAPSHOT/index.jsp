<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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

    div{
        margin:0 auto;
        width:1500px;
        height:55px;
        background-color: #333333;
    }

    #box{
        width: 1250px;
        height:50px;
        margin: 410px auto;
        padding: 0 0 0 0;
        border-radius: 12px 12px 12px 12px;
        font-family: 'Microsoft YaHei';
        font-size: 14px;

    }

    #search{
        width: 100px;
        height: 50px;
        float: right;
        background: black;
        color: white;
        text-align: center;
        line-height: 50px;
        border-radius: 0px 12px 12px 0px;
        cursor: pointer;
    }

    input{
        width: 1000px;
        border: 1px solid black;
        height: 50px;
        border-left:0px;
        font-size: 25px;
        padding:0 0 0 40px;

    }

    #downList
    {
        width: 150px;
        height:50px;
        float: left;
        border: 1px solid black;
        text-align: center;
        border-right:0px;
        border-radius: 12px 0px 0px 12px;
        font-size: 20px;
    }

    body{
        background:url(/picture/index.png) center 60px no-repeat;
        background-size: 1500px;
    }

</style>
<script type = "text/javascript">
</script>

<div class="topnav">
    <a class="active" href="index.jsp"target="leftFrame"> 首页</a>
    <a href="PaperList.jsp" target="leftFrame"> 论文列表</a>
    <a href="DataAnalysis.jsp"target="leftFrame"> 数据分析</a>
</div>

<body>
<div id="box">
    <input type="search" name="search">
    <div id="search"><font size="4">搜索</font></div>
    <select id="downList">
        <option value ="volvo">篇名</option>
        <option value ="saab">关键词</option>
        <option value="opel">作者</option>
        <option value="audi">主题</option>
        <option value="audi">编号</option>
    </select>
</body>

</html>