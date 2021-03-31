<%--
  Created by IntelliJ IDEA.
  User: 99647
  Date: 2021/3/24
  Time: 19:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
  <head>
    <%
      String path = request.getContextPath();
      String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
    <title>221801211&221801202</title>
    <script src="<%= basePath+"javascript/jquery-3.5.1.js" %>"></script>
    <script src="<%= basePath+"javascript/bootstrap.js" %>"></script>
    <script src="<%= basePath+"javascript/bootstrap-table.js" %>"></script>

    <link rel="stylesheet" href="<%= basePath+"css/cloud.css" %>" type="text/css">
    <link rel="stylesheet" href="<%= basePath+"css/style1.css" %>" type="text/css">
    <link rel="stylesheet" href="<%= basePath+"css/bootstrap-table.css" %>" type="text/css">
  </head>
  <body style="background: url(<%= basePath+"Photos/x4.jpg" %>);background-origin: content-box; background-position: 50% 50%; opacity: 0.8; background-attachment: fixed; background-repeat: repeat;">
  <div class="container">
    <div class="row clearfix">
      <div class="col-md-12 column">
        <div class="carousel slide" id="carousel-527165">
          <ol class="carousel-indicators">
            <li data-slide-to="0" data-target="#carousel-527165">
            </li>
            <li data-slide-to="1" data-target="#carousel-527165" class="active">
            </li>
            <li data-slide-to="2" data-target="#carousel-527165">
            </li>
          </ol>
          <div class="carousel-inner">
            <div class="item">
              <div style="min-width:400px;height:400px">
                <img alt="" src="<%= basePath+"Photos/x1.png" %>" />
              </div>
              <div class="carousel-caption">
              </div>
            </div>
            <div class="item active">
              <div style="min-width:400px;height:400px">
                <img alt="" src="<%= basePath+"Photos/x2.png" %>" />
              </div>
              <div class="carousel-caption">
              </div>
            </div>
            <div class="item">
              <div style="max-width:1920px;max-height:400px">
                <img alt="" src="<%= basePath+"Photos/x3.png" %>" />
              </div>
              <div class="carousel-caption">
              </div>
            </div>
          </div> <a class="left carousel-control" href="#carousel-527165" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a> <a class="right carousel-control" href="#carousel-527165" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
        </div>
        <div class="jumbotron">
          <h1>
            Super crawler！
          </h1>
          <p>
            你还在为论文查找而烦恼吗？ 你还在因为没有追随“潮流”而沮丧吗? 恭喜你！这个网站可以解决你所有的论文爬取烦恼，赶快试试吧。
          </p>
          <p>
            <a class="btn btn-primary btn-large" href="<%= basePath+"homepage" %>">Learn about</a>
          </p>
        </div>
      </div>
    </div>
  </div>
  </body>
</html>
