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
    <link rel="icon" href="img/logo.png" sizes="32x32">
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <!-- CSS样式 -->
    <script src="./js/layer/layer.js"></script>
    <link rel="stylesheet" type="text/css" href="./css/defalt.css" />
</head>
<body id="Top">
<%
    String flag = request.getParameter("search");
    if (flag.equals("")){
%>
<h1 style="font-family:verdana">论文列表</h1>
<%
    }
    else {
%>
<h1 style="font-family:verdana"><%=flag%>的搜索结果</h1>
<%
    }
%>
    <div class="div-btn">
        <a href="JavaScript:history.back(-1)">
    <button id="fat-btn" class="btn btn-default" data-loading-text="Loading..." type="button"> 返回
        <span class="glyphicon glyphicon-arrow-left"></span>
    </button>
    <script>
        $(function () {
            $(".btn").click(function () {
                $(this).button('loading').delay(1000).queue(function () {
                    // $(this).button('reset');
                    // $(this).dequeue();
                });
            });
        });
    </script>
</a>
    </div>
    <table align="center" border="0" width="85%">
        <thead>
        <tr bgcolor="#6699FF" width="30">
            <th>标题</th>
            <th>摘要</th>
            <th>关键词</th>
            <th>原文链接</th>
            <th>年份</th>
            <th>顶会</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (flag != null){
            int pageNum = (int) request.getAttribute("pageNum");
            int maxPage = (int) request.getAttribute("maxPage");
            int end = (pageNum + 1) * 31 - 1;
            int begin = pageNum * 31;
            List<Post> posts = (List<Post>) request.getAttribute("posts");

            for(int i = begin;i < end && i < posts.size();i++)
            {
                Post post = (Post)posts.get(i);
        %>
        <tr >
            <td><%=post.getTitle() %></td>
            <td class="td1"><%=post.getAbs() %></td>
            <td>
                <%
                    List<String> kwd = post.getKwds();
                    for (int j = 0;j<kwd.size();j++){
                %>
                    <a href="postList?search=<%=kwd.get(j)%>"><%= kwd.get(j)%>
                    </a>
                <%
                    }
                %>
            </td>
            <td><a href="<%=post.getLink()%>"><%=post.getLink() %></a></td>
            <td><%=post.getYear() %></td>
            <td><%=post.getType() %></td>
            <td><input type="button" style="" value="删除数据" onclick="if(confirm('确认？')==false)return false;location.href='DeleteServlet?id=<%=post.getId()%>&search=<%=flag%>'" /></td>
        </tr>
        <%
            }
        %>
        <tr  width="30" align="center">
            <td colspan="7">
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
                <input type="text" id="val" />
                <input type="button" value="跳转" onclick="location.href='postList?search=<%=flag%>&pageNum='+document.getElementById('val').value" />
                <a href="#Top">返回顶部</a>
            </td>
        </tr>
        </tbody>
        <%
            }
        %>
    </table>
</body>
</html>

<script>
    $(function () {
        $("td").on("click",function() {
            if (this.offsetWidth < this.scrollWidth) {
                var that = this;
                var text = $(this).text();
                //layer.alert(text);
                layer.open({
                    title: '内容简介',
                    offset: '25%',
                    content: text
                });
            }
        });
    })
</script>