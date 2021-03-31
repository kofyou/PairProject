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
        <img src="./img/3.png" class="gray" onclick="window.history.back();">
    </div>
</div>

<div class="main">
</div>
<div class="message_box">
    <div class="message">
        <div class="circle"></div>
        <div class="message_title">
            <h4 id="title"> ${requestScope.paperInfo.title}</h4>
        </div>
        <div class="message_keyword">
            <p></p>
            <p></p>
            <h3>关键词：</h3>
            <p id="keywords"> ${requestScope.paperInfo.keywords} </p>
            <p></p>
        </div>
        <div class="message_summary">
            <h3>摘要：</h3>
            <p id="summary"> ${requestScope.paperInfo.summary} </p>
        </div>
        <div class="message_link">
            <span>原文链接：</span><a id="link" href="${requestScope.paperInfo.link}"> ${requestScope.paperInfo.link} </a>
        </div>
        <div class="message_year">
            <h4 id="year"> 发表年份：${requestScope.paperInfo.year} </h4>
        </div>
        <div class="message_type">

            <h4 id="conference"> 会议：${requestScope.paperInfo.conference} </h4>
        </div>
    </div>
</div>
<div id="zhanwei"></div>
</body>
</html>
