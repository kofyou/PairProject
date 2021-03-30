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
<% String path = request.getContextPath(); %>
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
        width: 1300px;
        height:50px;
        margin: 10px auto;
        padding: 0 0 0 0;
        border-radius: 12px 12px 12px 12px;
        font-family: 'Microsoft YaHei';
        font-size: 14px;

    }

    #input1{
        width: 1009px;
        border: 1px solid black;
        height: 48px;
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

    #input2{
        width: 100px;
        height: 50px;
        float: right;
        border: 1px solid black;
        background: black;
        color: white;
        text-align: center;
        border-radius: 0px 12px 12px 0px;
        font-size: 20px;
        cursor: pointer;
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

    /*表格样式*/
    table {
        table-layout: fixed;
        word-wrap:break-word;
        width: 90%;
        background: #ccc;
        margin: 10px auto;
        border-collapse: collapse;/*border-collapse:collapse合并内外边距(去除表格单元格默认的2个像素内外边距*/
    }

    th,td {
        height: 100px;
        line-height: 25px;
        text-align: center;
        border: 1px solid #ccc;
    }

    td {
        white-space:nowrap;
        overflow:hidden;
        text-overflow: ellipsis;

    }
    th {
        background: #eee;
        font-weight: normal;
    }
    tr {
        background: #fff;
    }
    tr:hover {
        background: #8e8b8b;
    }
    td a {
        color: #06f;
        text-decoration: none;
    }
    td a:hover {
        color: #06f;
        text-decoration: underline;
    }

</style>
<script type = "text/javascript">
</script>

<div class="topnav">
    <a class="active" href="PaperList.jsp" target="leftFrame"> 论文列表</a>
    <a href="DataAnalysis.jsp"target="leftFrame"> 数据分析</a>

</div>

<body>

<div id="box">
    <form method="post" id="form" action="<%=path%>/ListServlet">
        <input id="input1" type="text" id="searchText" name="search" value="${search}" >
        <div id="search"><input id="input2" type="submit" name="searchButton" value="搜索">  </div>
        <select id="downList" name="option">
            <option value="title">篇名</option>
            <option value="keyword">关键词</option>
            <option value="type">类别</option>
        </select>
    </form>
</div>

<%!
    List<Paper> list = new ArrayList<>();
%>

<%
    list = (List<Paper>) request.getAttribute("list");
    if(list != null) {
%>

    <table border="1" width="400">
        <tr>
            <th>论文标题</th>
            <th>摘要</th>
            <th>原文链接</th>
            <th>关键词</th>
            <th>年份</th>
            <th>类别</th>
            <th>操作</th>
        </tr>

        <%
                for (Paper paper : list) {
        %>

        <tr>

                <td><%=paper.getTitle() %></td>
                <td><%=paper.getSummary() %></td>
                <td><a href=<%=paper.getLink() %>> <%=paper.getLink() %></a></td>
                <td><%=paper.getKeyword() %></td>
                <td><%=paper.getYear() %></td>
                <td><%=paper.getType() %></td>
                <td>
                    <form method="post" id="form1" action="<%=path%>/DeleteServlet">
                        <input type="submit" name="deleteButton" value="删除">
                        <input type="hidden" id="title" name="title" value="<%=paper.getTitle() %>">
                    </form>
                </td>


        </tr>
        <%
                }
            }
        %>

    </table>


</body>
</html>


