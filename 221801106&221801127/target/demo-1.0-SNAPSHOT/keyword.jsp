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
    <div id="main">
        <div>
            <form id="keywordselect" style="margin-top: 1%;">
                <div style="width: 15%;position: absolute;left: 30%;top: 10%;">
                    <label for="key1">论文列表</label>
                    <input type="radio" name="tenkey" id="key1" checked="true"/>
                </div>
                <div style="width: 15%;position: absolute;left: 42%;top: 10%;">
                    <label for="key2">CVPR</label>
                    <input type="radio" name="tenkey" id="key2" />
                </div>
                <div style="width: 15%;position: absolute;left: 54%;top: 10%;">
                    <label for="key3">ICCV</label>
                    <input type="radio" name="tenkey" id="key3" />
                </div>
                <div style="width: 15%;position: absolute;left: 66%;top: 10%;">
                    <label for="key4" >ECCV</label>
                    <input type="radio" name="tenkey" id="key4" />
                </div>
            </form>
            <div id="words" style="border:1px solid rgb(103, 100, 151);position: absolute;top: 20%;left: 25%; width: 60%; height: 70%;">
                <p style="position: relative;left: 40%; background-color: salmon;width:fit-content;border-radius: 5px;">1111111111</p>
                <p style="position: relative;left: 30%; background-color: rgb(124, 175, 133);width:fit-content;border-radius: 5px;">2222222222</p>
                <p style="position: relative;left: 52%;top: -7%; background-color: rgb(201, 87, 116);width:fit-content;border-radius: 5px;">3333333333</p>
                <p style="position: relative;left: 20%; background-color: rgb(114, 184, 250);width:fit-content;border-radius: 5px;">4444444444444</p>
                <p style="position: relative;left: 60%;top: -10%; background-color: rgb(114, 250, 193);width:fit-content;border-radius: 5px;">55555555555555555</p>
                <p style="position: relative;left: 25%; background-color: rgb(250, 114, 216);width:fit-content;border-radius: 5px;">66666666666666</p>
                <p style="position: relative;left: 60%;top: -15%; background-color: rgb(211, 202, 127);width:fit-content;border-radius: 5px;">777777777777777</p>
                <p style="position: relative;left: 55%;top: -9%; background-color: rgb(206, 55, 38);width:fit-content;border-radius: 5px;">88888888888888888</p>
                <p style="position: relative;left: 25%;top: -14%; background-color: rgb(31, 138, 63);width:fit-content;border-radius: 5px;">99999999999</p>
                <p style="position: relative;left: 35%;top: -7%; background-color: rgb(109, 71, 124);width:fit-content;border-radius: 5px;">00000000000000</p>
            </div>
        </div>
    </div>
</div>
</body>
<footer>

</footer>
</html>

