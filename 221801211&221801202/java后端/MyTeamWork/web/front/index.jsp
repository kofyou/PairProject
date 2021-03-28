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
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-social=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="style.css">
    <!--Jquery-->
    <link rel="stylesheet" href="../jquery-3.5.1/jquery-3.5.1.js">
    <link rel="stylesheet" href="../jquery-3.5.1/jquery-3.5.1.min.js">
    <link rel="stylesheet" href="../jquery-3.5.1/jquery插件库.url">
    <!--bootstrap-->

    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.0.1/css/bootstrap-theme.css" rel="stylesheet">
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.0.1/css/bootstrap.css" rel="stylesheet">
    <script type="text/javascript" src="../jquery-3.5.1/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="../jquery-3.5.1/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="../JS/bootstrap.js"></script>
    <script type="text/javascript" src="../JS/bootstrap.min.js"></script>
    <script type="text/javascript" src="../JS/npm.js">
    </script><!--VUE开发环境版本-->
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>

</head>

<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="row clearfix">
                <div class="col-md-3 column">
                    <img alt="140x140" src="../Photos/姐就是女王1.jpg" class="img-circle" />
                </div>
                <div class="col-md-9 column">
                    <div class="carousel slide" id="carousel-564592">
                        <ol class="carousel-indicators">
                            <li data-slide-to="0" data-target="#carousel-564592">
                            </li>
                            <li data-slide-to="1" data-target="#carousel-564592" class="active">
                            </li>
                            <li data-slide-to="2" data-target="#carousel-564592">
                            </li>
                        </ol>
                        <div class="carousel-inner">
                            <div class="item">
                                <img alt="" src="../Photos/2015.png" />
                                <div class="carousel-caption">

                                </div>
                            </div>
                            <div class="item active">
                                <img alt="" src="../Photos/2016.png" />
                                <div class="carousel-caption">

                                </div>
                            </div>
                            <div class="item">
                                <img alt="" src="../Photos/2017.png" />
                                <div class="carousel-caption">

                                </div>
                            </div>
                            <div class="item">
                                <img alt="" src="../Photos/2018.png" />
                                <div class="carousel-caption">

                                </div>
                            </div>
                            <div class="item">
                                <img alt="" src="../Photos/2019.png" />
                                <div class="carousel-caption">

                                </div>
                            </div>
                            <div class="item">
                                <img alt="" src="../Photos/2020.png" />
                                <div class="carousel-caption">

                                </div>
                            </div>
                        </div> <a class="left carousel-control" href="#carousel-564592" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a> <a class="right carousel-control" href="#carousel-564592" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
                    </div>
                    <nav class="navbar navbar-default navbar-fixed-top navbar-inverse" role="navigation">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="#">主页</a>
                        </div>

                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                            <ul class="nav navbar-nav">
                                <li class="active">
                                    <a href="#">热词走势图</a>
                                </li>
                                <li>
                                    <a href="#">关键词图谱</a>
                                </li>
                            </ul>
                            <form class="navbar-form navbar-left" role="search">
                                <div class="form-group">
                                    <input type="text" class="form-control" />
                                </div> <button type="submit" class="btn btn-default">Submit</button>
                            </form>

                        </div>

                    </nav>
                </div>
            </div>
            <div class="row clearfix">
                <div class="col-md-3 column">
                    <ol>
                        <li>
                            Lorem ipsum dolor sit amet
                        </li>
                        <li>
                            Consectetur adipiscing elit
                        </li>
                        <li>
                            Integer molestie lorem at massa
                        </li>
                        <li>
                            Facilisis in pretium nisl aliquet
                        </li>
                        <li>
                            Nulla volutpat aliquam velit
                        </li>
                        <li>
                            Faucibus porta lacus fringilla vel
                        </li>
                        <li>
                            Aenean sit amet erat nunc
                        </li>
                        <li>
                            Eget porttitor lorem
                        </li>
                    </ol>
                </div>
                <div class="col-md-6 column">
                    <table class="table table-condensed table-hover table-striped">
                        <thead>
                        <tr>
                            <th>
                                编号
                            </th>
                            <th>
                                产品
                            </th>
                            <th>
                                交付时间
                            </th>
                            <th>
                                状态
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <% List<PaperInfo> list= (List<PaperInfo>) request.getAttribute("users"); %>
                        <% for(PaperInfo user : list){ %>
                        <tr>
                            <td><%=user.getTitle()%></td>
                            <td><%=user.getAuthor()%></td>
                            <td><%=user.getMeeting()%></td>
                            <td><%=user.getYear()%></td>
                            <td><%=user.getAbstr()%></td>
                            <td><%=user.getUrl()%></td>
                            <td><%=user.getAccesstimes()%></td>
                        </tr>
                        <% } %>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-3 column">
                    <img alt="140x140" src="http://cdn.ibootstrap.cn/lorempixel.com/140/140/default.jpg" />
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>