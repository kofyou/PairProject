<%@ page import="cn.itcast.domain.PaperInfo" %><%--
  Created by IntelliJ IDEA.
  User: 99647
  Date: 2021/3/30
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-social=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>index</title>

    <link rel="icon" href="https://static.jianshukeji.com/highcharts/images/favicon.ico">
    <meta name="viewport" content="width=device-width, initial-scale=1">	<meta name="description" content="">
    <%--    <script src="https://img.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>--%>
    <%--    <script src="https://img.hcharts.cn/highcharts/highcharts.js"></script>--%>
    <%--    <script src="https://img.hcharts.cn/highcharts/modules/exporting.js"></script>--%>
    <script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>

    <script src="<%= basePath+"javascript/jquery-1.8.3.js" %>"></script>
    <script src="<%= basePath+"javascript/highchart.js" %>"></script>
    <script src="<%= basePath+"javascript/exporting.js" %>"></script>
    <script src="<%= basePath+"javascript/Jcloud.js" %>"></script>
    <script src="<%= basePath+"javascript/bootstrap.js" %>"></script>
    <link rel="stylesheet" href="<%= basePath+"css/cloud.css" %>" type="text/css">
    <link rel="stylesheet" href="<%= basePath+"css/style1.css" %>" type="text/css">
</head>
<body style="background: url(<%= basePath+"Photos/bg1.png" %>);background-origin: content-box; background-position: 50% 50%; opacity: 0.8; background-attachment: fixed; background-repeat: repeat;">
<style type="text/css">
    .abstract{
        width: 400px;;
        overflow: hidden;
        white-space:inherit;
        text-overflow: ellipsis;
    }
</style>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <nav class="navbar navbar-default navbar-inverse" role="navigation">
                <div class="navbar-header">
                </div>

                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li class="active">
                        </li>

                    </ul>
                </div>

            </nav>
        </div>
    </div>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">

                <div class="page-header">
                    <%PaperInfo paperInfo = (PaperInfo) request.getAttribute("paperInfo");%>
                    <h1>
                        <%=paperInfo.getTitle()%>
                    </h1>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            <%=paperInfo.getMeeting()%>
                        </h3>
                    </div>
                    <div class="panel-body">
                        <%=paperInfo.getYear()%>
                    </div>
                    <div class="panel-body">
                        <%=paperInfo.getKeyword()%>
                    </div>

                </div>
                <h2>
                    <%=paperInfo.getAbstr()%>
                </h2>
                <!--点击相关链接-->
                <p>
                    <a class="btn" href="<%=paperInfo.getUrl()%>">相关链接 -»</a>
                </p>
                <!--点击返回到首页-->
                <a href="<%= basePath+"homepage" %>"><button type="button" class="btn btn-info btn-lg" >返回</button></a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
