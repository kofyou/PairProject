<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="dao.*"%>
<%@ page import="entity.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<head>
    <base href="<%=basePath%>">
    
    <title>Paper Search For U -热门领域</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.js" ></script>
    <link rel="stylesheet" type="text/css" href="top.css">
    
</head>
<body>
    <div id="nav">
        <div class="divimg">
            <img src="flower.jpg" />
        </div>
        <div id="menu">

            <a href="main.jsp"><button id="papersearch">论文检索</button><br></a>
        </div>
        <div id="menu">
            <a href="collection.jsp"><button id="papersearch">论文收藏夹</button><br></a>
        </div>
        <div id="menu">
            <a href="top.jsp"><button id="papersearch">热门领域</button><br></a>
        </div>
        <div id="menu">
            <button id="papersearch">研究热词</button><br>
        </div>
    </div>
    <div id="section">
        <div id="tablelist">
            <h1 id="h1">top10个热门领域</h1>
                    
        </div>
    </div>
</body>
</html>