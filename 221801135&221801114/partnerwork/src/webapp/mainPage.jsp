<%--
  Created by IntelliJ IDEA.
  User: Flut
  Date: 2021/3/26
  Time: 13:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" href="css/mainPage.css">
        <title>Main Page</title>
    </head>
    <body>
        <a class="title_mainPage">Crwaler</a>
        <div class="searchBox_mainPage">
            <form method="get" action="/partnerwork_war_exploded/SearchServlet">
                <input class="searchBox_text" type="text" name="search" placeholder="Type to search">
            </form>
            <a class="searchBox_btn" href="/partnerwork_war_exploded/SearchServlet">
                <img src="png/search.png" width="25px">
            </a>
            <a class="searchBox_btn" href="/partnerwork_war_exploded/ListServlet">
                <img src="png/addfile.png" width="25px">
            </a>
        </div>
    </body>
</html>
