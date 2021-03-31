<%--
  Created by IntelliJ IDEA.
  User: 陈鹏桢
  Date: 2021/3/29
  Time: 22:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" import="java.util.*" %>
<%@ page import="pojo.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!--    预览设置-->
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>论文详情页</title>
    <link rel="stylesheet" href="http://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.css">
    <link rel="stylesheet" href="./css/my.css">
</head>
<body >
<%
    User user=(User)request.getAttribute("user");
    /*String idstring=(String)request.getAttribute("id");
    String digest=request.getParameter("digest");
    String title=request.getParameter("title");
    String key=request.getParameter("key");
    String year=request.getParameter("year");
    String time=request.getParameter("time");
    String link=request.getParameter("link");
    int id=Integer.parseInt(idstring);*/
%>
<!--    网站导航-->
<!--使用inverted的黑化效果突出导航栏-->
<nav class="ui inverted attached segment my-padded-tb-mini">
    <div class="ui container">
        <div class="ui inverted secondary stackable menu" ><!--                在menu里面加一个条目-->
            <h2 class="ui blue header item ">MyPaper</h2>
            <a href="InfoServlet" class="item" style="width: 7em " ><i class="home icon"></i>首页</a>
            <a href="GraphServlet" class="item" style="width: 10em"><i class="chart pie icon"></i>年度热词图谱</a>
            <a href="TrendServlet" class="item" style="width: 8em"><i class="chart line icon"></i>趋势走向</a>
            <div class="right item " >
                <div class="ui icon inverted transparent input">
                    <form action="InfoServlet">
                        <input placeholder="Search...." type="text" name="searchId" id="searchId"/>
                        <button class="ui secondary button">Search</button>

                    </form>
                </div>
            </div>
        </div>
    </div>
</nav>

<!--    中间内容-->
<div class="my-container-small my-padded-tb-big">
    <div class="ui container">
        <div class="ui top attached segment">
            <div class="ui horizontal link list">
                <div class="item">
                    <div class="content"><a href="#"class="header">Author</a></div>
                </div>
                <div class="item">
                    <i class="calendar icon"></i><%=user.getTime()%>
                </div>
                <div class="item">
                    <i class="eye icon"></i>Times
                </div>
            </div>
        </div>
        <div class="ui attached padded segment">
            <!--            文章内容-->
            <div class="ui right aligned basic segment">
                <div class="ui blue basic label">Original</div>
            </div>
            <h3 class="ui center aligned header"><%=user.getTitle()%></h3>
            <div id="content" class="my-padded-lr-response my-padded-tb-large">
                <h3>LINK</h3>
                <p><a href="<%=user.getLink()%>"><%=user.getLink()%></a></p>
                <h2>DIGEST</h2>
                <p><%=user.getDigest()%></p>
                <h3>KeyWords</h3>
                <p><%=user.getKey()%></p>

                <i><%=user.getYear()%></i>

            </div>

        </div>
    </div>
    <div class="ui bottom attached segment">
        <!--            论文信息-->
        <div class="ui grid">
            <div class="eleven wide column">
                <ui class="list">
                    <li>Author：Jerome</li>
                    <li>发表时间：2021-03-28</li>
                    <li>版权声明：自由转载-非商用-非衍生-标明出处</li>
                    <li>Pengzhan Chan</li>
                </ui>
            </div>
            <div class="five wide column">
                <img src="./images/MyIDcard.png" class="ui right floated rounded png" alt="" style="width: 100px">
            </div>
        </div>

    </div>
</div>

<!--footer-->
<footer class="ui inverted vertical segment ">
    <!--      center aligned将容器内的所有块居中显示-->
    <div class="ui center aligned container">
        <!--            style="width: 100em "-->
        <div class="ui inverted divided stackable grid">
            <div  class="three wide column">
                <div class="ui inverted link list" >
                    <div class="item">
                        <img src="./images/MyIDcard.png" class="ui rounded png" alt="" style="width: 100px">
                    </div>
                </div>
            </div>
            <div  class="three wide column">
                <h4 class="ui inverted header my-opacity-adjust">最新论文</h4>
                <div class="ui inverted link list" >
                    <a href="#" class="item"style="width: 15em ">论文作者故事(Writer Story)</a>
                    <a href="#" class="item"style="width: 15em ">团队合作很重要</a>
                </div>
            </div>
            <div  class="three wide column">
                <h4 class="ui inverted header my-opacity-adjust">联系我们</h4>
                <div class="ui inverted link list" >
                    <a href="#" class="item"style="width: 15em ">Email:3360068374@qq.com</a>
                    <a href="#" class="item"style="width: 15em ">QQ:3360068374</a>
                </div>
            </div>
            <div  class="three wide column">
                <h4 class="ui inverted header my-opacity-adjust" style="width:30em" >网站介绍</h4>
                <p class="my-text-thin my-text-spaced my-opacity-adjust"style="width:35em">我们致力于将来自计算机视觉领域世界三大顶级会议（即CVPR、ICCV和ECCV）的论文进行资源整合并进行数据分析。希望对来访者有所帮助...</p>
            </div>
        </div>
        <div class="ui inverted section divider"></div>
        <p class="my-text-thin my-text-spaced my-opacity-adjust">Copyright by 2021 MyPaperTeam LilCrab&CPZ</p>
    </div>
</footer>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.2/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.js"></script>
</body>
</html>
