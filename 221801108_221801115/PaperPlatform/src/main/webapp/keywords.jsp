<%@ page import="pojo.Paper" %>
<%@ page import="dao.PaperDAO" %>
<%@ page import="dao.PaperDAOImpl" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="pojo.Keyword" %>
<%@ page import="dao.KeywordDAO" %>
<%@ page import="dao.KeywordDAOImpl" %><%--
  Created by IntelliJ IDEA.
  User: Lin Minghao
  Date: 2021/3/28
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    KeywordDAO keywordDAO = new KeywordDAOImpl();
    ArrayList<Keyword> keywordList = keywordDAO.GetTop10Keywords();
    for (Keyword keyword:keywordList) {
        out.println(keyword.getName() + ": " + keyword.getFrequency());
        out.print("<br/>");
    }
%>

</body>
</html>
