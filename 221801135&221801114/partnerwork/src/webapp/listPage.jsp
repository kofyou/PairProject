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
<div>
    <form class="searchBox_listPage" method="get" action="/paper/SearchServlet">
        <input class="searchBox_text" type="text" name="search" placeholder="Type to search">
        <input type="submit" class="searchBox_btn" value="">
    </form>
</div>
<a class="title_otherPage" href="mainPage.jsp">Crwaler</a>
<a class="leftpage_btn" href="/paper/ChartPageServlet"><img src="png/leftpage.png"></a>
<a class="rightpage_btn" href="trendPage.jsp"><img src="png/rightpage.png"></a>
<%
    out.print("<div class=\"totalNumber\">共查询到"+request.getAttribute("totalNumber")+"篇论文</div>");
%>
<ul class="main">
    <%
      List<Paper> list = (List<Paper>) request.getAttribute("list");
      int iterator=(int)request.getAttribute("iterator");
      for (int i=0;i<list.size();++i)
      {
          out.print("<li class=\"single_paper boxshadow\">" +
                        "<a class=\"paper_number\">"+(iterator+i+1)+"</a>"+//显示数字比迭代器多+1
                        "<a class=\"paper_title\" href=\"/paper/InfoServlet?iterator="+(iterator+i)+"\">"+list.get(i).getTitle()+"</a>"+
                        "<div class=\"buttonbox\">" +
                            "<a class=\"delete_btn\" onclick=\"func("+(iterator+i)+","+request.getAttribute("currentPage")+")\">" +
                                "<img src=\"png/delete.png\">" +
                            "</a>" +
                        "</div>" +
                    "</li>");
      }
    %>
    <li class="single_paper">
        <%
            out.print("<a class=\"lastpage_btn\" href=\"/paper/LastPageServlet?page="+((int) request.getAttribute("currentPage") - 1)+"\">\n");
        %>
        <img src="png/lastpage.png">
        </a>
        <div class="pagenumber">
            <%
                out.print("<form method=\"get\" action=\"/paper/SkipServlet?currentPage="+request.getAttribute("currentPage")+"\"><input type=\"text\" name=\"page\" class=\"currentpage\" placeholder=\""+request.getAttribute("currentPage")+"\"/></form>");

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
            out.print("<a class=\"nextpage_btn\" href=\"/paper/NextPageServlet?page=" + ((int) request.getAttribute("currentPage") + 1) + "\">\n");
        %>
        <img src="png/nextpage.png">
        </a>
    </li>
</ul>

<script>
    function func(iterator,page){    /* 绑定事件 */
        var r = confirm("确定从库中移除该论文？")
        if (r == true) {
            var str="/paper/DeleteServlet?iterator="+iterator+"&page="+page;
            window.location.href=str;
        }
    }
</script>
</body>
</html>
