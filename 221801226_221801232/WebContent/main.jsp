<%@page import="javar.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Paper Search For U</title>
</head>
<body>
<%
        PaperDao dao = new PaperDao();
        dao.createPaperTable("paperlist");
        LogFiles logfile = new LogFiles("D://ECCV");
 %>
</body>
</html>