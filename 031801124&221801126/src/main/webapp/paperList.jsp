<%--
  Created by IntelliJ IDEA.
  User: Charley Chen
  Date: 2021/3/25
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<% String path = request.getContextPath(); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.getAttribute("paperList2");
    String name = request.getParameter("name");//用request得到
%>
<html>
<head>
    <title>paperList测试</title>
    <style type="text/css">
        table
        {
            border-collapse: collapse;
            margin: 0 auto;
            text-align: center;
        }
        table td, table th
        {
            border: 1px solid #cad9ea;
            color: #666;
            height: 30px;
        }
        table thead th
        {
            background-color: #CCE8EB;
            width: 100px;
        }
        table tr:nth-child(odd)
        {
            background: #fff;
        }
        table tr:nth-child(even)
        {
            background: #F5FAFA;
        }
    </style>
    <meta content="text/html" charset="UTF-8">

    <style>
        #btnShuaXin{
            position: absolute;
            background-color: #ffffff;
            left: 90%;
            top:10%;
            width: 100px;
            height: 32px;
            line-height: 6px;
            border-radius: 10px;
            color: #c12e2e;
            font-size: 14px;
            text-align: center;
            box-shadow: 0px 2px 6px 0px rgba(0, 0, 0, 0.4);
            border: none;
            font-weight: bold;
        }
        #btnSearch{
            /*position: absolute;*/
            background-color: #ffffff;
            left: 90%;
            top:10%;
            width: 100px;
            height: 32px;
            line-height: 6px;
            border-radius: 10px;
            color: #c12e2e;
            font-size: 14px;
            text-align: center;
            box-shadow: 0px 2px 6px 0px rgba(0, 0, 0, 0.4);
            border: none;
            font-weight: bold;
        }
        body{ text-align:center}
        #divcss5{margin:0 auto;width:300px;height:100px}
    </style>
</head>

<body style="background: url(bg.jpg)">
<%--/*上条栏使用 #c1cbd7*/--%>
<%--/*上文字使用 #656565*/--%>
<div style="color:#c1cbd7">
    <h1 style="background-color:#656565">
论文检索系统
    </h1>
</div>

<%--<form action="PaperServlet" method="post">
    <button id="btnShuaXin">刷新列表</button>
</form>--%>
<div id="divcss5">
<form method="post" id="queryForm" action="<%=path%>/SearchServlet">
    <input  type="text" name="pTitle" value="${paper.paperTitle}" placeholder="论文标题"/><br><br>
    <input  type="text" name="pKeyword" value="${paper.paperKeyword}" placeholder="论文关键词"/><br>
    <input type="hidden" name="pLink" value="${paper.paperLink}" placeholder="论文链接"/>
    <input type="hidden" name="type" value="0" />
    <input type="hidden" name="pageNum" value="${pageNum}" />
    <input type="hidden" id="changeNum" name="changeNum" value="" />
    <button  onclick="changePage(0);" id="btnSearch">查 询</button>
</form>
</div>
<table border="1">
    <tr>
        <td>序号</td>
        <td>论文标题</td>
        <td>论文会议</td>
        <td>论文摘要</td>
        <td>关键词</td>
        <td>论文时间</td>
        <td>论文链接</td>
        <td>操作</td>
    </tr>
    <c:forEach items="${paperList2}" var="l" varStatus="vs">
        <tr>
            <td>${vs.index + 1}</td>
            <td>${l.getPaperTitle()}</td>
            <td>${l.getPaperTypeYear()}</td>
            <td style="font-size:5pt;width: 400px">${l.getPaperAbstract()}</td>
            <td>${l.getPaperKeyword()}</td>
            <td>${l.getPaperReleasetime()}</td>
            <td>${l.getPaperLink()}</td>
            <td>
                <%--<button  type="button" onclick="">编 辑</button>--%>
                <%--<button type="button" onclick="deletePaper(l.getPaperLink())">删 除</button>--%>
                <form method="post" id="deleteForm" action="<%=path%>/SearchServlet">
                    <input type="hidden" name="pLink" value="${l.getPaperLink()}"/>
                    <input type="hidden" name="type" value="3" />
                    <input type="hidden" name="pageNum" value="${pageNum}" />
                    <input type="hidden"  name="changeNum" value="" />
                    <button  onclick="deletePaper(${l.getPaperLink()});">删除</button>
                </form>
            </td>
        </tr>

    </c:forEach>
</table>
<button type="button" onclick="changePage(-1)">上一页</button>
<button type="button" onclick="changePage(1)">下一页</button>

第 ${pageNum}页 共${totalPageNum} 页　，共 ${totalNum} 条记录
</body>
</html>

<script>
    function changePage(num) {
        document.getElementById("changeNum").value = num;
        document.getElementById("queryForm").submit();
    }

    function deletePaper(link)
    {
        document.getElementById("changeNum").type=3;
        document.getElementById("queryForm").pLink=link;
        document.getElementById("queryForm").submit();

    }
</script>
