<%@ page import="pojo.Paper" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath(); %>
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

    #di2{
        margin:0 auto;
        width:1500px;
        height:55px;
        background-color: #333333;
    }
</style>

<style>
    /*表格样式*/

    .table thead tr th, .table tbody tr td {
        vertical-align: middle;
        text-align: center;
        white-space: nowrap;/*规定段落的文本不进行换行*/
        overflow: hidden;/*超出隐藏*/
        text-overflow: ellipsis;/*隐藏的字符用省略号表示  IE*/
        -moz-text-overflow: ellipsis;/*隐藏的字符用省略号表示  火狐*/
    }


    table {
        table-layout: fixed;
        word-wrap:break-word;
        word-break: break-all;
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

    div p {
        align-content: center;
        font-size: 20px;
    }
</style>

<div class="topnav">
    <a href="PaperList.jsp" target="leftFrame"> 论文列表</a>
    <a href="DataAnalysis.jsp"target="leftFrame"> 数据分析</a>

</div>

<div class="topnav2">
    <a href="KeyWordChart.jsp" target="leftFrame">返回</a>

</div>

<%
    String word = (String) request.getAttribute("word");
%>

<body>

<div><p>包含<%=word%>的相关论文</p></div>

<%
    List<Paper> list = new ArrayList<>();
    list = (List<Paper>) request.getAttribute("list");
    if(list != null) {
%>

<table border="1" width="400">
    <tr>
        <th width="200px">论文标题</th>
        <th width="600px">摘要</th>
        <th>原文链接</th>
        <th width="200px">关键词</th>
        <th>年份</th>
        <th>类别</th>
    </tr>

    <%
        for (Paper paper : list) {
    %>

    <tr>
        <td width="200px"><%=paper.getTitle() %></td>
        <td width="600px"><%=paper.getSummary() %></td>
        <td><a href=<%=paper.getLink() %>> <%=paper.getLink() %></a></td>
        <td width="200px"><%=paper.getKeyword() %></td>
        <td><%=paper.getYear() %></td>
        <td><%=paper.getType() %></td>
    </tr>
    <%
            }
        }
    %>

</table>


</body>
</html>