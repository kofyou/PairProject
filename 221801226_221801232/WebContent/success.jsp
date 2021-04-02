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
    
    <title>collect success</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
</head>
<body>
    <hr>
      <% 
         int id = Integer.parseInt(request.getParameter("id"));
         ItemsDao itemsDao = new ItemsDao(); 
         items item = new items();
         item = itemsDao.getItemsById(id);
         String title = item.getTitle();
      %>
                wow!您成功收藏了论文<%=title%>，记得去收藏夹看看
      
</body>
</html>