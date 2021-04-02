<%--
  Created by IntelliJ IDEA.
  User: 陈鹏桢
  Date: 2021/3/29
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" import="java.util.*" %>
<%@ page import="pojo.User" %>
<html>
<style type="text/css">
    .table{
        overflow: hidden;
    }
</style>
<head>
    <title>Title</title>
</head>
<body>
<div class="workingArea">
    <h1 class="label label-info">用户管理</h1>
    <form action="InfoServlet">
        <input value="输入你要的论文id" type="text" name="searchId" id="searchId"/>
        <button>Search</button>
    </form>
    <br>
    <br>
    <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover table-condensed">
            <thead>
            <tr class="success">
                <th>论文名称</th>
                <th>摘要</th>
                <th>会议或年份</th>
                <th>关键字</th>
                <th>发布时间</th>
                <th>链接</th>
                <th>操作</th>
            </tr>
            <%
                String id=request.getParameter("id");
                int cur=Integer.parseInt(id);
                int last=1;
                int next=100;
                if(cur!=1) {
                    last = cur - 10;
                }
                if(cur!=100) {
                     next = cur + 10;
                }
                int top=1;
                int end=100;
            %>
            </thead>
            <tbody>
            <%
                List<User> list=(List<User>)request.getAttribute("users");

                for(int i=0;i<list.size();i++){
                    User user=list.get(i);
            %>
            <tr>
                <td><%=user.getTitle() %></td>
                <td><%=user.getDigest()%></td>
                <td><%=user.getYear()%></td>
                <td><%=user.getKey() %></td>
                <td><%=user.getTime() %></td>
                <td><%=user.getLink() %></td>
                <td><button><a href="DeleteServlet?id=<%=user.getId()%>">删除</a></button></td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
        <button><a href="InfoServlet?id=<%=top%>">首页</a></button>
        <button><a href="InfoServlet?id=<%=cur-10%>">上一页</a></button>
        <button><a href="InfoServlet?id=<%=cur+10%>">下一页</a></button>
        <button><a href="InfoServlet?id=<%=end%>">尾页</a></button>
    </div>
    <div class="pageDiv">
        <%--  <%@include file="../include/admin/adminPage.jsp" %> --%>
    </div>
</div>
</body>
</html>
