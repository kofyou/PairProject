<%@ page import="pojo.Paper" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: LQ
  Date: 2021/3/23
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    #box{
        width: 1250px;
        height:50px;
        margin: 10px auto;
        padding: 0 0 0 0;
        border-radius: 12px 12px 12px 12px;
        font-family: 'Microsoft YaHei';
        font-size: 14px;

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
    <a href="index.jsp"target="leftFrame"> 首页</a>
    <a class="active" href="PaperList.jsp" target="leftFrame"> 论文列表</a>
    <a href="DataAnalysis.jsp"target="leftFrame"> 数据分析</a>
</div>

<body>
<<<<<<< Updated upstream
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
</div>

<table border="1">
    <tr>
        <td>论文标题</td>
        <td>摘要</td>
        <td>原文链接</td>
        <td>关键词</td>
        <td>年份</td>
        <td>类别</td>
    </tr>

</table>
=======
    <div id="box">
        <form method="post" id="form" action="/ListServlet">
            <input type="text" id="search" name="search" value="${search}" >
            <div><button type="button" name="searchButton" onclick="search();" > 搜索 </button></div>
            <select id="downList" name="option">
                <option value="title">篇名</option>
                <option value="keyword">关键词</option>
                <option value="type">类别</option>
            </select>
        </form>
    </div>

    <%
        List<Paper> list = new ArrayList<>();
        list = (List<Paper>) request.getAttribute("list");
    %>
    <table border="1">
        <tr>
            <td>论文标题</td>
            <td>摘要</td>
            <td>原文链接</td>
            <td>关键词</td>
            <td>年份</td>
            <td>类别</td>
        </tr>
        <%
            for (Paper paper : list) {
        %>
        <tr>
            <td><%=paper.getTitle() %></td>
            <td><%=paper.getSummary() %></td>
            <td><%=paper.getLink() %></td>
            <td><%=paper.getKeyword() %></td>
            <td><%=paper.getYear() %></td>
            <td><%=paper.getType() %></td>
            <td><button type="button" name="deleteButton" onclick="cut();" > 删除 </button></td>
        </tr>
        <%
            }
        %>
    </table>
>>>>>>> Stashed changes

</body>
</html>

<script>
    function cut() {
<<<<<<< Updated upstream
        var username = document.getElementById("id").value;
        var password = document.getElementById("password").value;
        if (username == '') {
            alert("用户名不能为空！请您输入");
            return;
        }
        if (password == '') {
            alert("密码不能为空！请您输入");
            return;
        }
        document.getElementById("form1").submit();
=======

    }
</script>

<script>
    function search() {
        var search = document.getElementById("search").value;
        var option = document.getElementById("downList").value;
        if (search == '') {
            alert("输入内容不能为空！");
            return;
        }
        document.getElementById("form").submit();
>>>>>>> Stashed changes

    }
</script>