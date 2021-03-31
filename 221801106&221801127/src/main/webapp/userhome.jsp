<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="baseclass.Paper" %>
<%@ page import="baseclass.User" %>
<%--
  Created by IntelliJ IDEA.
  User: 1647
  Date: 2021/3/28
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
    <link rel="stylesheet" href="./css/leftStyle.css">
    <link rel="stylesheet" href="./css/right1Style.css">
</head>
<body>

<%
    String result1;
    result1 =(String) request.getAttribute("changeMessage_result");
    if (result1 == null){}
    else {
        if (result1.equals("true")) {
            out.println("<script>\n" +
                    "    alert(\"个人信息修改成功！\");\n" +
                    "</script>");
        }
        else{
            out.println("<script>\n" +
                    "    alert(\"修改失败！\");\n" +
                    "</script>");
        }
    }
%>

<%
    String result2;
    result2 =(String) request.getAttribute("changePassword_result");
    if (result2 == null){}
    else {
        if (result2.equals("true")) {
            out.println("<script>\n" +
                    "    alert(\"个人密码修改成功！\");\n" +
                    "</script>");
        }
        else{
            out.println("<script>\n" +
                    "    alert(\"密码修改失败！\");\n" +
                    "</script>");
        }
    }
%>

<div id = "wrap">
    <div id = "sidebar">
        <a href="./login.jsp" id="websitelink" class="lefta">
            <img src="./img/icon.png" alt="官网图标" id = "websiteicon">
            <span id="websitename">论文爬取网</span>
        </a>
        <div id = "selection">
            <a href="./right1.jsp" id="crawl" class="lefta">
                <img src="./img/搜索.png" alt="搜索图标">
                <span>爬取论文</span>
            </a>

            <a href="./right2.jsp" id="list" class="lefta">
                <img src="./img/列表.png" alt="列表图标">
                <span>论文列表</span>
            </a>

            <a href="./keyword.jsp" id="keyword" class="lefta">
                <img src="./img/关键词.png" alt="关键词图标" class="lefta">
                <span>关键词图谱</span>
            </a>

            <a href="./hotword.jsp" id="hotword" class="lefta">
                <img src="./img/热度.png" alt="热度图标" class="lefta">
                <span>热词走势</span>
            </a>

            <a href="./userhome.jsp" id="user" class="lefta">
                <img src="./img/用户.png" alt="用户图标">
                <span>个人中心</span>
            </a>
        </div>
    </div>

    <%
        if (session.getValue("user")==null) {}
        else
        {
            User user = (User) session.getValue("user");
    %>

        <div id="main" style="background-image: url(./img/preview.jpg);color: white;">
            <img src="./img/头像.PNG" alt="头像" style="width: 12%;height: 25%;position: absolute;left: 85%;top: 5%;">
            <button  style="width: 10%;height: 5%;position: absolute;left: 86%;top: 32%;">修改头像</button>
            <div style="float: left; margin-left: 10%;width: 350px;">
                <form action="../demo_war_exploded/MainServlet?type=changeMessage" method="post">
                    <p>用户昵称</p>
                    <input type="text" name="nickname" value="<%=user.getNickname()%>" style="height: 6%;">
                    <p>用户账号</p>
                    <input type="text" name="account" value="<%=user.getName()%>" readonly="readonly" style="height: 6%;">
                    <p>学校</p>
                    <input type="text" name="school" value="<%=user.getSchool()%>" style="height: 6%;">
                    <p>专业</p>
                    <input type="text" name="discipline" value="<%=user.getProfession()%>" style="height: 6%;">
                    <br>
                    <input type="submit" value="修改" style="width: 80px;height: 30px;position: relative;left: 30px;top: 20px;">
                </form>
            </div>
            <div style="margin-left: 40%;margin-top: 10%;">
                <form action="../demo_war_exploded/MainServlet?type=changePassword" method="post">
                    <span>密码：</span>
                    <input type="password" name="password" value="" style="height: 6%;">
                    <br>
                    <input type="submit" value="修改密码" name="changePW" style="width: 15%;height: 5%;margin-left: 21%;margin-top: 2%;">
                    <br>
                    <br>
                    <br>
                </form>
                <form action="" method="">
                    <span>手机：</span>
                    <input type="text" name="telnumber" value="<%=user.getPhonenumber()%>" style="height: 6%;" readonly="readonly">
                    <br>
                </form>
            </div>
        </div>

    <%
        }
    %>
</div>
</body>
<footer>

</footer>
</html>
