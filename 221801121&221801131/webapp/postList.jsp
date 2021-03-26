<%@ page import="edu.fzu.postsearch.pojo.Post" %>
<%@ page import="java.util.List" %>
<%@ page import="edu.fzu.postsearch.servlet.PostServlet" %>
<%--
  Created by IntelliJ IDEA.
  User: fjf
  Date: 2021/3/25
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>论文管理</title>
</head>
<body>

    <table align="center" border="0" width="85%">
        <thead>
        <tr bgcolor="#6699FF" width="30">
            <th>标题</th>
            <th>摘要</th>
            <th>关键词</th>
            <th>原文链接</th>
            <th>年份</th>
        </tr>
        </thead>
        <tbody>
        <%

            String flag = request.getParameter("search");
            if (flag != null){
            int pageNum = (int) request.getAttribute("pageNum");
            int maxPage = (int) request.getAttribute("maxPage");
            int end = (pageNum + 1) * 4 - 1;
            int begin = pageNum * 4;
            List<Post> posts = (List<Post>) request.getAttribute("posts");

            for(int i = begin;i < end && i < posts.size();i++)
            {
                Post post = (Post)posts.get(i);
        %>
        <tr>
            <td><%=post.getTitle() %></td>
            <td><%=post.getAbs() %></td>
            <td>
                <%
                    List<String> kwd = post.getKwds();
                    for (int j = 0;j<kwd.size();j++){
                %>
                    <a href=""><%= kwd.get(j)%>
                    </a>
                <%
                    }
                %>
            </td>
            <td><a href="<%=post.getLink()%>"><%=post.getLink() %></a></td>
            <td><%=post.getYear() %></td>
        </tr>
        <%
            }
        %>
        <tr bgcolor="#6699FF" width="30">
            <td colspan="4">
                <%
                    if (pageNum != 0){
                %>
                <a href="postList?search=<%=flag%>&pageNum=0">[首页]</a>
                <%
                    }
                    else
                        {
                %>
                [首页]
                <%
                        }
                    if (pageNum > 0 && maxPage != 0){
                %>
                <a href="postList?search=<%=flag%>&pageNum=${pageNum-1}">[上一页]</a>
                <%
                    }
                    else
                        {
                %>
                    [上一页]
                <%
                        }
                    if (pageNum < maxPage - 1 && maxPage != 0){
                %>
                <a href="postList?search=<%=flag%>&pageNum=${pageNum+1 }">[下一页]</a>
                <%
                    }
                    else
                        {
                %>
                    [下一页]
                <%
                        }
                    if (pageNum < maxPage - 1 && maxPage != 0){
                %>
                <a href="postList?search=<%=flag%>&pageNum=${maxPage-1 }">[尾页]</a>
                <%
                    }
                    else {
                %>
                [尾页]
                <%
                    }
                %>
            </td>
        </tr>
        </tbody>
        <%
            }
        %>
    </table>
</body>
</html>
