<%--
  Created by IntelliJ IDEA.
  User: 1647
  Date: 2021/3/28
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <form action="../demo_war_exploded/LoginServlet?type=register" method="post">
        <h3 style="color: white;position: absolute;margin-top: 5%;margin-left: 5%;margin-left: 10%;">账号</h3>
        <input type="text" name="account" style="color: black;position: absolute;margin-top: 5%;margin-left: 30%;width: 60%;height: 10%;border-radius: 3px;border: white;">

        <h3 style="color: white;position: absolute;margin-top: 18%;margin-left: 5%;margin-left: 10%;">密码</h3>
        <input type="password" name="password" style="color:black;position: absolute;margin-top: 18%;margin-left: 30%;width: 60%;height: 10%;border-radius: 3px;border: white;">

        <h3 style="color: white;position: absolute;margin-top: 32%;margin-left: 5%;margin-left: 2%;">再次输入密码</h3>
        <input type="password" name="password2" style="color:black;position: absolute;margin-top: 31%;margin-left: 30%;width: 60%;height: 10%;border-radius: 3px;border: white;">

        <input type="submit" value="注册" style="position: absolute;top: 75%;left: 40%;width: 30%;height: 10%;background-color: #90CA6C;border: white;border-radius: 5px;">
    </form>
</div>

<%
    String result;
    result =(String) request.getAttribute("register_error");
    if (result == null){}
    else {
        if (result.equals("true1")) {
%>
<script>
    alert("输入的密码密码不同！");
</script>
<%
        }
        else if (result.equals("true2"))
        {
%>
<script>
    alert("账户已存在！");
</script>
<%
        }
    }
%>
</body>
</html>