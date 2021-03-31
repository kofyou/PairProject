<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="baseclass.Paper" %>
<%--
  Created by IntelliJ IDEA.
  User: 1647
  Date: 2021/3/28
  Time: 15:14
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
<div id = "wrap">
    <div id = "sidebar" style="height: 298.5%">
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
    <div id="main">
        <%
            List<Map.Entry<String,Integer>> s1 = (List<Map.Entry<String,Integer>>) session.getValue("CVPR");
            List<Map.Entry<String,Integer>> s2 = (List<Map.Entry<String,Integer>>) session.getValue("ECCV");
            List<Map.Entry<String,Integer>> s3 = (List<Map.Entry<String,Integer>>) session.getValue("ICCV");
        %>
        <div>
            <div id="CVPR" style="font-size: 20px;border:1px Dashed rgb(103, 100, 151);float: left;width: 100%">
                <h2>CVPR</h2>
                <p style="position: relative;left: 40%; background-color: salmon;width:fit-content;border-radius: 5px;"><%=s1.get(0).getKey()%></p>
                <p style="position: relative;left: 30%; background-color: rgb(124, 175, 133);width:fit-content;border-radius: 5px;"><%=s1.get(1).getKey()%></p>
                <p style="position: relative;left: 52%;top: -7%; background-color: rgb(201, 87, 116);width:fit-content;border-radius: 5px;"><%=s1.get(2).getKey()%></p>
                <p style="position: relative;left: 20%; background-color: rgb(114, 184, 250);width:fit-content;border-radius: 5px;"><%=s1.get(3).getKey()%></p>
                <p style="position: relative;left: 60%;top: -10%; background-color: rgb(114, 250, 193);width:fit-content;border-radius: 5px;"><%=s1.get(4).getKey()%></p>
                <p style="position: relative;left: 25%; background-color: rgb(250, 114, 216);width:fit-content;border-radius: 5px;"><%=s1.get(5).getKey()%></p>
                <p style="position: relative;left: 60%;top: -15%; background-color: rgb(211, 202, 127);width:fit-content;border-radius: 5px;"><%=s1.get(6).getKey()%></p>
                <p style="position: relative;left: 55%;top: -9%; background-color: rgb(206, 55, 38);width:fit-content;border-radius: 5px;"><%=s1.get(7).getKey()%></p>
                <p style="position: relative;left: 25%;top: -14%; background-color: rgb(31, 138, 63);width:fit-content;border-radius: 5px;"><%=s1.get(8).getKey()%></p>
                <p style="position: relative;left: 35%;top: -7%; background-color: rgb(109, 71, 124);width:fit-content;border-radius: 5px;"><%=s1.get(9).getKey()%></p>
            </div>
            <div id="ECCV" style="font-size: 20px;border:1px Dashed rgb(103, 100, 151);float: left;width: 100%">
                <h2>ECCV</h2>
                <p style="position: relative;left: 40%; background-color: salmon;width:fit-content;border-radius: 5px;"><%=s2.get(0).getKey()%></p>
                <p style="position: relative;left: 30%; background-color: rgb(124, 175, 133);width:fit-content;border-radius: 5px;"><%=s2.get(1).getKey()%></p>
                <p style="position: relative;left: 52%;top: -7%; background-color: rgb(201, 87, 116);width:fit-content;border-radius: 5px;"><%=s2.get(2).getKey()%></p>
                <p style="position: relative;left: 20%; background-color: rgb(114, 184, 250);width:fit-content;border-radius: 5px;"><%=s2.get(3).getKey()%></p>
                <p style="position: relative;left: 60%;top: -10%; background-color: rgb(114, 250, 193);width:fit-content;border-radius: 5px;"><%=s2.get(4).getKey()%></p>
                <p style="position: relative;left: 25%; background-color: rgb(250, 114, 216);width:fit-content;border-radius: 5px;"><%=s2.get(5).getKey()%></p>
                <p style="position: relative;left: 60%;top: -15%; background-color: rgb(211, 202, 127);width:fit-content;border-radius: 5px;"><%=s2.get(6).getKey()%></p>
                <p style="position: relative;left: 55%;top: -9%; background-color: rgb(206, 55, 38);width:fit-content;border-radius: 5px;"><%=s2.get(7).getKey()%></p>
                <p style="position: relative;left: 25%;top: -14%; background-color: rgb(31, 138, 63);width:fit-content;border-radius: 5px;"><%=s2.get(8).getKey()%></p>
                <p style="position: relative;left: 35%;top: -7%; background-color: rgb(109, 71, 124);width:fit-content;border-radius: 5px;"><%=s2.get(9).getKey()%></p>
            </div>
            <div id="ICCV" style="font-size: 20px;border:1px Dashed rgb(103, 100, 151);float: left;width: 100%">
                <h2>ICCV</h2>
                <p style="position: relative;left: 40%; background-color: salmon;width:fit-content;border-radius: 5px;"><%=s3.get(0).getKey()%></p>
                <p style="position: relative;left: 30%; background-color: rgb(124, 175, 133);width:fit-content;border-radius: 5px;"><%=s3.get(1).getKey()%></p>
                <p style="position: relative;left: 52%;top: -7%; background-color: rgb(201, 87, 116);width:fit-content;border-radius: 5px;"><%=s3.get(2).getKey()%></p>
                <p style="position: relative;left: 20%; background-color: rgb(114, 184, 250);width:fit-content;border-radius: 5px;"><%=s3.get(3).getKey()%></p>
                <p style="position: relative;left: 60%;top: -10%; background-color: rgb(114, 250, 193);width:fit-content;border-radius: 5px;"><%=s3.get(4).getKey()%></p>
                <p style="position: relative;left: 25%; background-color: rgb(250, 114, 216);width:fit-content;border-radius: 5px;"><%=s3.get(5).getKey()%></p>
                <p style="position: relative;left: 60%;top: -15%; background-color: rgb(211, 202, 127);width:fit-content;border-radius: 5px;"><%=s3.get(6).getKey()%></p>
                <p style="position: relative;left: 55%;top: -9%; background-color: rgb(206, 55, 38);width:fit-content;border-radius: 5px;"><%=s3.get(7).getKey()%></p>
                <p style="position: relative;left: 25%;top: -14%; background-color: rgb(31, 138, 63);width:fit-content;border-radius: 5px;"><%=s3.get(8).getKey()%></p>
                <p style="position: relative;left: 35%;top: -7%; background-color: rgb(109, 71, 124);width:fit-content;border-radius: 5px;"><%=s3.get(9).getKey()%></p>
            </div>
        </div>
    </div>
</div>
</body>
<footer>

</footer>
</html>

