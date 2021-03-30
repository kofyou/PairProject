<%--
  Created by IntelliJ IDEA.
  User: fjf
  Date: 2021/3/28
  Time: 23:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <link rel="stylesheet" type="text/css" href="./css/defalt.css">
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

    <title>论文爬取系统</title>
    <link rel="icon" href="img/logo.png" sizes="32x32">
</head>

<body OnLoad="swapPic()">
<div id="gundong">
    <script language="javascript" type="text/javascript">
        var ranl = 0;
        var useRand = 0;
        images = new Array;
        images[1] = new Image();
        images[1].src = "./img/pic5.jpg";
        images[2] = new Image();
        images[2].src = "./img/pic4.jpg";
        images[3] = new Image();
        images[3].src = "./img/pic3.jpg";
        images[4] = new Image();
        images[4].src = "./img/pic2.jpg";
        function swapPic() {
            var imgnum = images.length - 1;
            do {
                var randnum = Math.random();
                randl = Math.round((imgnum - 1) * randnum) + 1;
            } while (randl == useRand);
            useRand = randl;
            var img = document.getElementById("randimg");
            img.src = images[useRand].src
            setTimeout('swapPic()', 2000);
        }

    </script>
    <p align="center"><img id="randimg" src="./img/pic2.jpg" width="50%" height="300"></p>
</div>

<div id="buttons">
    <div>
        <a href="search.jsp" title="论文查询"><img src="./img/fa5-search.svg" width="80%"></a><br>
        <p>论文查询</p>
    </div>
    <div>
    <a href="postList?search=" title="论文列表"><img src="./img/fa5-th-list.svg" width="80%"></a><br>
        <p>论文列表</p>
    </div>
    <div>
    <a href="analyze.jsp" title="热词分析"><img src="./img/md-trending_up.svg" width="80%"></a><br>
        <p>热词分析</p>
    </div>
</div>
</body>
</html>
