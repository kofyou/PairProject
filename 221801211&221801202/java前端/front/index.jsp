<%@ page import="cn.itcast.domain.PaperInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.itcast.domain.PaperInfo" %><%--
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
                        <form class="navbar-form navbar-left" role="search">
                            <div class="form-group">
                                <input type="text" class="form-control" name="search"/>
                            </div> <button type="submit" class="btn btn-default">搜索</button>
                        </form>
                    </div>
                </nav>

            </div>
        </div>
    </div>

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div id="container" style="min-width:400px;height:400px"></div>
            <script>
                $(function () {
                    $('#container').highcharts({
                        chart: {
                            type: 'line'
                        },
                        title: {
                            text: '三大顶会热词走势'
                        },
                        xAxis: {
                            categories: ['Machine Learning', 'Algebraic Geometry', 'Numerical Analysis', 'Image and Video Processing', 'Optimization and Control', 'Robotics', 'Graphic', 'Databases', 'MVC', 'Distributed,Parallel,and Cluster Computing']
                        },
                        yAxis: {
                            title: {
                                text: '热度'
                            }
                        },
                        plotOptions: {
                            line: {
                                dataLabels: {
                                    enabled: true          // 开启数据标签
                                },
                                enableMouseTracking: false // 关闭鼠标跟踪，对应的提示框、点击事件会失效
                            }
                        },
                        series: [{
                            name: 'CVPR',
                            data: [106,132,33,137,35,157,100,44,92,58]
                        }, {
                            name: 'ICCV',
                            data: [121,130,120,128,136,66,90,129,38,149]
                        },{
                            name:'ECCV',
                            data:[159,73,36,56,74,66,140,124,57,44]
                        }]
                    });
                });
            </script>

            <div class="row clearfix">
                <div class="col-md-6 column">
                    <table class="table table-condensed table-hover table-striped">
                        <thead>
                        <tr>
                            <th>
                                论文标题
                            </th>
                            <th>
                                作者
                            </th>
                            <th>
                                会议名称
                            </th>
                            <th>
                                会议年份
                            </th>
                            <th>
                                摘要
                            </th>
                            <th>
                                链接
                            </th>
                            <th>
                                论文上传时间
                            </th>
                            <th>
                                关键词
                            </th>
                            <th>
                                删除
                            </th>
                        </tr>
                        </thead>
                        <!--表格内容-->
                        <tbody>
                        <% List<PaperInfo> list= (List<PaperInfo>) request.getAttribute("paperInfos"); %>
                        <% for(PaperInfo user : list){ %>
                        <tr>
                            <td><%=user.getTitle()%></td>
                            <td><%=user.getAuthor()%></td>
                            <td><%=user.getMeeting()%></td>
                            <td><%=user.getYear()%></td>
                            <td><%=user.getAbstr()%></td>
                            <td><%=user.getUrl()%></td>
                            <td><%=user.getAccesstimes()%></td>
                            <td><%=user.getKeyword()%></td>
                            <td>
                                <form action="homepage" method="get">
                                    <input type="hidden" name="delete" value=
                                        <%=user.getTitle()%>
                                    >
                                    <input type="submit" value="删除" />
                                </form>
                            </td>
                        </tr>
                        <% } %>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div>
</div>

        </div>
    </div>
</div>
</body>
</html>