<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.catalina.connector.Request" %>
<%@ page import="com.example.partnerwork.Paper" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Flut
  Date: 2021/3/26
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/listPage.css">
    <title>List Page</title>
</head>
<body>
<div class="searchBox_listPage">
    <form method="get" action="/partnerwork_war_exploded/SearchServlet">
        <input class="searchBox_text" type="text" name="search" placeholder="Type to search">
        <input type="submit" class="searchBox_btn" value="">
    </form>
</div>
<a class="title_otherPage" href="mainPage.jsp">Crwaler</a>
<a class="leftpage_btn" href="/partnerwork_war_exploded/ChartPageServlet"><img src="png/leftpage.png"></a>
<a class="rightpage_btn" href="chartPage.jsp"><img src="png/rightpage.png"></a>

<ul class="main">
    <%
      List<Paper> list = (List<Paper>) request.getAttribute("list");
      int iterator=(int)request.getAttribute("iterator");
      for (int i=0;i<list.size();++i)
      {
          out.print("<li class=\"single_paper\">" +
                        "<a class=\"paper_number\">"+(iterator+i+1)+"</a>"+//显示数字比迭代器多+1
                        "<a class=\"paper_title\" href=\"/partnerwork_war_exploded/InfoServlet?id="+(iterator+i)+"\">"+list.get(i).getTitle()+"</a>"+
                        "<div class=\"buttonbox\">" +
                            "<a class=\"collect_btn\" href=\"/partnerwork_war_exploded/CollectServlet?id=1&page=1\">" +
                                "<img src=\"png/collected.png\">" +
                            "</a>" +
                            "<a class=\"delete_btn\" href=\"/partnerwork_war_exploded/DeleteServlet?iterator="+(iterator+i)+"&page="+request.getAttribute("currentPage")+"\">" +
                                "<img src=\"png/delete.png\">" +
                            "</a>" +
                        "</div>" +
                    "</li>");
      }
    %>
    <li class="single_paper">
        <%
            out.print("<a class=\"lastpage_btn\" href=\"/partnerwork_war_exploded/LastPageServlet?page="+request.getAttribute("currentPage")+"\">\n");
        %>
        <img src="png/lastpage.png">
        </a>
        <div class="pagenumber">
            <%
                out.print("<input type=\"text\" class=\"currentpage\" placeholder=\""+request.getAttribute("currentPage")+"\"/>");
            %>
            /<a class="maxpage">
            <%
                int num=1;
                if(request.getAttribute("maxPage")!=null)
                    num=(int)request.getAttribute("maxPage");
                out.print(num);
            %>
        </a>
        </div>
        <%
            out.print("<a class=\"nextpage_btn\" href=\"/partnerwork_war_exploded/NextPageServlet?page="+request.getAttribute("currentPage")+"\">\n");
        %>
        <img src="png/nextpage.png">
        </a>
    </li>
</ul>
</body>
</html>
