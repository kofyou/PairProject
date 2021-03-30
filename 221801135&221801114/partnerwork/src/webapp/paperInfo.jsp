<%@ page import="com.example.partnerwork.Paper" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Flut
  Date: 2021/3/26
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/paperinfo.css">
    <title>info Page</title>
</head>
<body>
<a href="mainPage.jsp" class="title_otherPage">Crwaler</a>
<%
    out.print("<a class=\"back\" href=\"/partnerwork_war_exploded/BackListServlet?iterator="+(int) request.getAttribute("iterator")+"\">");
%>
<img src="png/back.png"></a>
<div class="main">
    <div class="paperinfo">
        <%
            Paper paper = (Paper) request.getAttribute("paper");
                out.print("<a class=\"papertitle\">"+paper.getTitle()+"</a>\n" +
                        "        <a href=\""+paper.getDoiLink()+"\" class=\"link\">(原文链接)</a>\n" +
                        "        <div class=\"tagbox\">\n" );
            List<String> tagList = paper.getTagList();
                for (int i=0;i<tagList.size();++i)
                {
                    if(i%3==0)
                        out.print("<div class=\"tag1\" onclick=\"document.location.href=\'/partnerwork_war_exploded/TagSearchServlet?search="+paper.getTagList().get(i)+"\'\">"+paper.getTagList().get(i)+"</div>");
                    if(i%3==1)
                        out.print("<div class=\"tag2\" onclick=\"document.location.href=\'/partnerwork_war_exploded/TagSearchServlet?search="+paper.getTagList().get(i)+"\'\">"+paper.getTagList().get(i)+"</div>");
                    if(i%3==2)
                        out.print("<div class=\"tag3\" onclick=\"document.location.href=\'/partnerwork_war_exploded/TagSearchServlet?search="+paper.getTagList().get(i)+"\'\">"+paper.getTagList().get(i)+"</div>");
                }

                out.print("        </div>\n" +
                        "        <p class=\"info\">\n" +
                        paper.getAbstractText() +
                        "</p>");
        %>
    </div>
</div>
</body>
</html>
