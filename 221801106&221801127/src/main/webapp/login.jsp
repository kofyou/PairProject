<%--
  Created by IntelliJ IDEA.
  User: 1647
  Date: 2021/3/28
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" import="java.util.*" %>
<html style="width: 100%;height: 100%;">
<head>
    <title>登录</title>
</head>
<body style="background-image: url(./img/preview.jpg);width: 100%;height: 100%;">
<div style="border: 1px solid white;width: 35%;height: 60%;position: relative;top: 20%;left: 32%;">
    <img src="./img/icon.png" alt="官网图标" id = "websiteicon" style="width: 12%;height: 15%;margin-left: 25%;margin-top: 3%;">
    <h1 style="color: #90CA6C;float: right;margin-right: 25%;">论文查找网</h1>
    <div style="position:absolute;top: 80%;left: 80%;">
        <a href="./login.jsp" style="color: white;">登录</a>
        <a href="./register.jsp" style="color: white;">注册</a>
    </div>
    <form action="../demo_war_exploded/LoginServlet?type=login" method="post">
        <h2 style="color: white;position: absolute;margin-top: 5%;margin-left: 5%;margin-left: 10%;">账号</h2>
        <input type="text" name="account" style="color: black;position: absolute;margin-top: 5%;margin-left: 30%;width: 60%;height: 10%;border-radius: 3px;border: white;">

        <h2 style="color: white;position: absolute;margin-top: 25%;margin-left: 5%;margin-left: 10%;">密码</h2>
        <input type="password" name="password" style="color:black;position: absolute;margin-top: 25%;margin-left: 30%;width: 60%;height: 10%;border-radius: 3px;border: white;">

        <input type="submit" value="登录" style="position: absolute;top: 68%;left: 40%;width: 30%;height: 10%;background-color: #90CA6C;border: white;border-radius: 5px;">
    </form>
    <%
        String result;
        result =(String) request.getAttribute("login_error");
        if (result == null){}
        else {
            if (result.equals("true")) {
    %>
    <script>
        alert("用户名或者密码错误！");
    </script>
    <%
            }
        }
    %>

    <%
        String result1;
        result1 =(String) request.getAttribute("register_success");
        if (result1 == null){}
        else {
            if (result1.equals("true")) {
    %>
    <script>
        alert("注册成功，请登入！");
    </script>
    <%
            }
        }
    %>

</div>
</body>
</html>
