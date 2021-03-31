<%@ page import="cn.itcast.domain.PaperInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.itcast.domain.PaperInfo" %>
<%@ page import="cn.itcast.domain.Keynum" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
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
    <script src="<%= basePath+"javascript/bootstrap-table.js" %>"></script>

    <link rel="stylesheet" href="<%= basePath+"css/cloud.css" %>" type="text/css">
    <link rel="stylesheet" href="<%= basePath+"css/style1.css" %>" type="text/css">
    <link rel="stylesheet" href="<%= basePath+"css/bootstrap-table.css" %>" type="text/css">

    <style>
        .table-normal {
            width: 80%;
            border: 1px solid #ccc;
            border-collapse: collapse;
        }

        .table-normal th,
        .table-normal td {
            padding: 8px;
            border: 1px solid #ccc;
            text-align: left;
        }

        .outBox .checkbox {
            width: 2px;
            height: 2px;
            border: 1px solid #ccc;
            display: inline-block;
            margin-right: 10px;
            cursor: pointer;
        }

        .btnShow {
            size: 12px;
            width: 20%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.7);
            border: 2px solid black;
            border-radius: 10%;
            color: rgb(255, 255, 255);
        }
    </style>

    <script>
        $(document).ready(function(){
            $("#table_page").bootstrapTable({
                columns:[
                    {
                        field:'name',
                        title:'标题'
                    },
                    {
                        field:'year',
                        title:'年份'
                    },
                    {
                        field:'meeting',
                        title:'会议'
                    },
                    {
                        field:'abstract',
                        title:'摘要'
                    },
                    {
                        field:'url',
                        title:'链接'
                    },
                    {
                        field:'keyword',
                        title:'关键词'
                    },
                    {
                        field:'view',
                        title:'查看'
                    },
                    {
                        field:'delete',
                        title:'删除'
                    },
                ]
            })
        })
    </script>
</head>

<body style="background: url(<%= basePath+"Photos/bg1.png" %>);background-origin: content-box; background-position: 50% 50%; opacity: 0.8; background-attachment: fixed; background-repeat: repeat;">
<style type="text/css">
    .abstract{
        width:300px;
        flaot:left;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
    }
    .absrtr{
        width:200px;
        flaot:left;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
    }
</style>
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

            <table class="table table-bordered" id="table_page"
                   data-pagination="true"
                   data-side-pagination="client"
                   data-page-size="5">
                <% List<PaperInfo> list= (List<PaperInfo>) request.getAttribute("paperInfos"); %>
                <% int i=0;%>
                <% for(PaperInfo user : list){ %>
                <tr>
                    <td><div class="absrtr"><%=user.getTitle()%></div></td>
                    <td><%=user.getYear()%></td>
                    <td><%=user.getMeeting()%></td>
                    <td><div class="abstract"><%=user.getAbstr()%></div></td>
                    <td><%=user.getUrl()%></td>
                    <td><%=user.getKeyword()%></td>
                    <td>
                        <form action="homepage" method="get">
                            <input type="hidden" name="view" value=
                                    "<%=user.getTitle()%>"
                            >
                            <input type="submit" value="查看" />
                        </form>
                    </td>
                    <td>
                        <form action="homepage" method="get">
                            <input type="hidden" name="delete" value=
                                "<%=user.getTitle()%>"
                            >
                            <input type="submit" value="删除" />
                        </form>
                    </td>
                </tr>
                <% } %>
            </table>

        </div>
    </div>
</div>

</div>
</div>
</div>
</body>

</html>