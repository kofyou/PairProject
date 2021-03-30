<%--
  Created by IntelliJ IDEA.
  User: LQ
  Date: 2021/3/23
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<style type="text/css">

    ul {
        list-style: none;
    }

    .siderbar {
        position: fixed;
        width: 184px;
        height: 100%;
        margin:0 auto;
        left: 210px;
        background-color: #333333;
        transition: all 0.5s;
        z-index: 998;
    }

    .siderbar ul {
        overflow: hidden;
    }

    .siderbar ul a {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 100%;
        height: 70px;
        box-sizing: border-box;
        border-top: 1px solid white;
        border-bottom: 1px solid black;
        color: white;
        text-decoration: none;
        font-size: 20px;
        font-weight:600;
        transition: all 0.5s;
    }
    .siderbar li a i {
        margin-right: 20px;
    }
    .siderbar ul a:hover {
        margin-left: 20px;
        color: #eee;
    }


    section {

        -webkit-background-size: cover;
        background-size: cover;
        z-index: 1;
        transition: all 0.5s;
        height: 100vh;
        padding: 5% 10%;
        box-sizing: border-box;
    }


    .main h3 {
        color: #fff;
    }

    .topnav {
        overflow: hidden;
        background-color: #333333;
    }

    .topnav a {
        float: left;
        display: block;
        color: white;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
        font-size: 20px;
        font-weight:600;
    }

    .topnav a:hover {
        background-color: white;
        color: #333333;
    }

    .topnav a.active {
        background-color: white;
        color: #333333;
    }

    div{
        margin:0 auto;
        width:1500px;
        height:55px;
        background-color: #333333;
    }

</style>
<script type = "text/javascript">
</script>

<div class="topnav">
    <a href="index.jsp"target="leftFrame"> 首页</a>
    <a href="PaperList.jsp" target="leftFrame"> 论文列表</a>
    <a class="active" href="DataAnalysis.jsp"target="leftFrame"> 数据分析</a>
</div>

<div class="siderbar">
    <ul>
        <li><a href="#"><i class="fa fa-paper-plane"></i>关键词图谱</a></li>
        <li><a href="#"><i class="fa fa-paper-plane"></i>热词图谱</a></li>
    </ul>
</div>

<body>

</body>
</html>
