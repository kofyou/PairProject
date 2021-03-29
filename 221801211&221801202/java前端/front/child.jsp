<%@ page import="cn.itcast.domain.PaperInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.itcast.domain.PaperInfo" %>
<%@ page import="cn.itcast.domain.Keynum" %><%--
  Created by IntelliJ IDEA.
  User: 99647
  Date: 2021/3/28
  Time: 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
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
    <script src="https://img.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
    <script src="https://img.hcharts.cn/highcharts/highcharts.js"></script>
    <script src="https://img.hcharts.cn/highcharts/modules/exporting.js"></script>
    <script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
    <script src="<%= basePath+"javascript/Jcloud.js" %>"></script>

    <link rel="stylesheet" href="<%= basePath+"css/cloud.css" %>" type="text/css">
    <link rel="stylesheet" href="<%= basePath+"css/style1.css" %>" type="text/css">
</head>

<body style="background: url(<%= basePath+"Photos/back.jpg" %>);background-origin: content-box; background-position: 50% 50%; opacity: 0.8; background-attachment: fixed;">

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="row clearfix">

                <nav class="navbar  navbar-default navbar-inverse" role="navigation">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="<%= basePath+"homepage" %>">主页</a>
                    </div>
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                            <li>
                                <a href="<%= basePath+"child" %>">关键词图谱</a>
                            </li>
                        </ul>
                    </div>
                </nav>

            </div>
        </div>
    </div>

    <div>
        <div class="row clearfix" style="width: 50%">
            <div class="col-md-6 column">
                <div class="row" id="hh">
                    <div class="container">
                        <canvas id="canvas" width="600" height="500"></canvas>
                        <script src="<%= basePath+"javascript/chart.js" %>" style="width: 50%"></script>
                        <script style="width: 50%">
                            var data = [
                                <% List<Keynum> list= (List<Keynum>) request.getAttribute("keynumList"); %>
                                <% for(Keynum keynum : list){ %>
                                {xAxis:'<%=keynum.getKeyword()%>',value:<%=keynum.getAppeartimes()%>},
                                <% } %>
                            ]
                            var chart = new sBarChart('canvas',data,{
                                title: '关键词统计',
                                titleColor: '#ce0000',      // 标题颜色
                                titlePosition: 'top',       // 标题位置
                                fillColor: '#02f78e',       // 柱状填充色
                                axisColor: '#a23400',       // 坐标轴颜色
                                contentColor: '#bbbbbb'     // 内容横线颜色
                            });
                        </script>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript" style="width: 50%">
            var word_array = [
                <% List<Keynum> list1= (List<Keynum>) request.getAttribute("keynumList"); %>
                <% double size = 300; %>
                <% for(Keynum keynum : list1){ %>
                <% size = size * 0.9; %>
                {text: "<%=keynum.getKeyword()%>", weight: <%=size%>, link:"<%=basePath + "/child?p=" + keynum.getKeyword()%>"},
                <% } %>
            ];
            $(function() {
                $("#example").jQCloud(word_array);
            });
        </script>
        <div id="example" style="width: 50%; height: 650px;"></div>
    </div>
</div>
</body>
</html>