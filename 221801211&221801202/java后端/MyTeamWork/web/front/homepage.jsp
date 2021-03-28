<%@ page import="cn.itcast.domain.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 99647
  Date: 2021/3/24
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="listDataTableDiv">
    <table class="table table-striped table-bordered table-hover table-condensed">
        <thead>
        <tr class="success">
            <th>ID</th>
            <th>用户名称</th>
        </tr>
        </thead>
        <tbody>
        <% List<User> list= (List<User>) request.getAttribute("users"); %>
        <% for(User user : list){ %>
        <tr>
            <td><%=user.getId() %></td>
            <td><%=user.getName() %></td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
</body>
</html>
