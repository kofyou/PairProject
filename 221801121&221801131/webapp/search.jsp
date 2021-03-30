<%--
  Created by IntelliJ IDEA.
  User: fjf
  Date: 2021/3/26
  Time: 23:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询</title>
    <link rel="icon" href="img/logo.png" sizes="32x32">
    <link rel="stylesheet" type="text/css" href="./css/defalt.css">
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <!-- CSS样式 -->
</head>
<body>
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
<div class="div1">
    <div class="div2">
        <form action="postList" method="get">
            <img src="./img/chalunwen.png" id="imgsearch"><br>
            <input type="text" name="search" id="search"/>
            <input type="submit" id="buttonsub"/><br>
        </form>
    </div>
</div>


</body>
</html>
