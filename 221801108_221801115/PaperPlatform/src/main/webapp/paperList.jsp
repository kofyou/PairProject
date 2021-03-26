<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ page import="pojo.Paper" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% String path = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>
<body>

<div>
    <form action="<%=path%>/PaperQueryServlet" method="GET">
        查询:<br>
        <input type="text" name="query" >
        <input type="submit" value="Submit">
    </form>

    <div>
        <table border="1" >
            <thead>
            <tr>
                <th>序号</th>
                <th>论文题目</th>
                <th>会议</th>
                <th>摘要</th>
                <th>原文链接</th>
                <th>关键词</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.paperList}" var="pl" varStatus="vs">
            <tr>
                <td> ${vs.index + 1}</td>
                <td> ${pl.title} </td>
                <td> ${pl.conference} </td>
                <td> ${pl.summary} </td>
                <td> <a href=${pl.link} >${pl.link}</a> </td>
                <td> ${pl.keywords} </td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>