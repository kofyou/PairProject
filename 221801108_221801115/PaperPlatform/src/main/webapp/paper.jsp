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
    <link rel="stylesheet" href="css/paper.css" />
</head>
<body>

<div class="header" >
    <div class="logo_box">
        <img class="logo" src="./img/2.png" >
    </div>
    <div id="back_btn">
        <input type="button" value="返回" class="gray" onclick="window.history.back();">
    </div>
</div>

<div class="main">
</div>
<div class="message_box">
    <div class="message">
        <div class="message_title">
            <h4>论文题目：</h4>
            <h3 id="title"> ${requestScope.paperInfo.title}</h3>
        </div>
        <div class="message_summary">
            <h4>摘要：</h4>
            <p id="summary"> ${requestScope.paperInfo.summary} </p>
        </div>
        <div class="message_keyword">
            <h4>关键词：</h4>
            <p id="keywords"> ${requestScope.paperInfo.keywords} </p>
        </div>
        <div class="message_year">
            <h4>发表年份：</h4>
            <p id="year"> ${requestScope.paperInfo.year} </p>
        </div>
        <div class="message_type">
            <h4>会议：</h4>
            <p id="conference"> ${requestScope.paperInfo.conference} </p>
        </div>
        <div class="message_link">
            <h4>原文链接：</h4>
            <a id="link" href="${requestScope.paperInfo.link}"> ${requestScope.paperInfo.link} </a>
        </div>
    </div>
</div>
</body>
</html>
