<%--
  Created by IntelliJ IDEA.
  User: Lin Minghao
  Date: 2021/3/26
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<p>
    <input type ="button" value ="返回"  onclick="window.history.back();">
</p>

<h3>论文题目：${requestScope.paperInfo.title} </h3>
<h4>摘要：</h4>
<p> ${requestScope.paperInfo.summary} </p>
<h4>关键词：</h4>
<p> ${requestScope.paperInfo.keywords} </p>
<h4>发表年份：</h4>
<p> ${requestScope.paperInfo.year} </p>
<h4>会议：</h4>
<p> ${requestScope.paperInfo.conference} </p>
<h4>原文链接：</h4>
<a href="${requestScope.paperInfo.link}"> ${requestScope.paperInfo.link} </a>


</body>
</html>
