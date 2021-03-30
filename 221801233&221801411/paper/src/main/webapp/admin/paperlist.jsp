<%@ page import="pojo.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" import="java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<style type="text/css">
    a{
        text-decoration: none;

    }
    .message_digest{
        display: block;
        width: 300px;
        overflow:hidden;
        text-overflow:ellipsis;
        display: -webkit-box;
        -webkit-line-clamp: 2;
        -webkit-box-orient: vertical;
        color: black;

    }
</style>
<head>
    <meta charset="UTF-8">
    <!--    预览设置-->
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>首页</title>
    <link rel="stylesheet" href="http://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.css">
    <link rel="stylesheet" href="./css/my.css">
</head>
<body >
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
                        <input placeholder="Search...." type="text" name="searchId" id="searchId" autocomplete="off"/>
                        <button class="ui secondary button">Search</button>

                    </form>
                    <!--input type="text" placeholder="Search...." -->

                </div>
            </div>
        </div>
    </div>
</nav>

<!--    中间内容-->
<div class="my-container my-padded-tb-large">
    <div class="ui container">
        <div class="ui stackable grid">

            <!--           页面左边卡片部分-->
            <div class="five wide column">
                <!--                    三大会议-->
                <div class=" ui segments">
                    <div class="ui secondary segment">
                        <div class="ui two column grid">
                            <div class="column">
                                <i style="color: steelblue">三大会议</i>
                            </div>
                            <!--                                点击更多跳转到分类页面-->
                            <div class="right aligned column">
                                <a href="GraphServlet"  >更多 <i class="angle double right icon"></i></a>
                            </div>
                        </div>
                    </div>
                    <div class="ui blue segment">
                        <div class="ui fluid vertical menu">
                            <a href="InfoServlet?searchId=<%="CVPR"%>" class="item">
                                CVPR
                                <div class="ui blue basic left point label">相关文章</div>
                            </a>
                            <a href="InfoServlet?searchId=<%="ICCV"%>" class="item">
                                ICCV
                                <div class="ui blue basic left point label">相关文章</div>
                            </a>
                            <a href="InfoServlet?searchId=<%="ECCV"%>" class="item">
                                ECCV
                                <div class="ui blue basic left point label">相关文章</div>
                            </a>
                        </div>
                    </div>
                </div>
                <!--                    热词标签-->
                <div class="ui segments">
                    <div class="ui secondary segment">
                        <div class="ui two column grid">
                            <div class="column">
                                <i style="color: steelblue">热词Top10</i>
                            </div>
                            <!--                                点击more跳转到年度热词图谱-->
                            <div class="right aligned column">
                                <a href="GraphServlet" target="_blank" >更多 <i class="angle double right icon"></i></a>
                            </div>
                        </div>
                    </div>
                    <div class="ui blue segment">
                        <a href="GraphServlet" class="ui blue basic label my-marin-tb-tiny">热词1
                            <div class="detail">文章数量</div>
                        </a>
                        <a href="GraphServlet" class="ui blue basic  label my-marin-tb-tiny">热词2
                            <div class="detail">文章数量</div>
                        </a>
                        <a href="GraphServlet" class="ui blue basic  label my-marin-tb-tiny">热词3
                            <div class="detail">文章数量</div>
                        </a>
                        <a href="GraphServlet" class="ui blue basic label my-marin-tb-tiny">热词4
                            <div class="detail">文章数量</div>
                        </a>
                        <a href="GraphServlet" class="ui blue basic  label my-marin-tb-tiny">热词5
                            <div class="detail">文章数量</div>
                        </a>
                        <a href="GraphServlet" class="ui blue basic  label my-marin-tb-tiny">热词6
                            <div class="detail">文章数量</div>
                        </a>
                        <a href="GraphServlet" class="ui blue basic label my-marin-tb-tiny">热词7
                            <div class="detail">文章数量</div>
                        </a>
                        <a href="GraphServlet" class="ui blue basic  label my-marin-tb-tiny">热词8
                            <div class="detail">文章数量</div>
                        </a>
                        <a href="GraphServlet" class="ui blue basic  label my-marin-tb-tiny">热词9
                            <div class="detail">文章数量</div>
                        </a>
                        <a href=GraphServlet" class="ui blue basic  label my-marin-tb-tiny">热词10
                            <div class="detail">文章数量</div>
                        </a>
                    </div>
                </div>
            </div>


            <!--           页面右边论文列表部分-->
            <div class="eleven wide column">
                <!--                    header-->
                <div class="ui to attached segment">
                    <div class="ui middle aligned two column grid">
                        <div class="column">
                            <h3 class="ui blue header">论文</h3>
                        </div>
                        <div class=" right aligned column">
                            共<h3 class="ui teal header my-inline-block my-text-thin">9365</h3>篇论文
                        </div>
                    </div>
                </div>
                <div>

                        <%
                            String id=request.getParameter("id");
                            int cur=Integer.parseInt(id);
                            int last=1;
                            int next=100;
                            if(cur!=1) {
                                last = cur - 10;
                            }
                            if(cur!=100) {
                                next = cur + 10;
                            }
                            int top=1;
                            int end=100;
                        %>
                        <%
                            List<User> list=(List<User>)request.getAttribute("users");

                            for(int i=0;i<list.size();i++){
                                User user=list.get(i);
                        %>


                <div class="ui attached segment my-padded-tb-large">
                    <div class="ui padded  vertical segment">
                        <div class="ui mobile reversed stackable grid">
                            <!--                            文章内容-->
                            <div class="eleven wide column">
                                <tr>
                                    <td>
                                        <div>
                                            <dl>
                                                <dt>

                                                </dt>
                                                <dd class="message_title"><%=user.getTitle() %></dd>
                                                <dd class="message_digest"><%=user.getDigest()%></dd>
                                                <dd class="message_details"><%=user.getYear()%></dd>
                                                <dd class="message_details"><%=user.getKey() %></dd>
                                                <dd class="message_details"><%=user.getTime() %></dd>
                                                <dd class="message_details"><%=user.getLink() %></dd>
                                                <button class="ui button"><a href="DeleteServlet?id=<%=user.getId()%>">删除</a></button>
                                                <div class="five wide column">
                                                    <a href="SearchServlet?id=<%=user.getId()%>"    class="ui teal basic label my-padded-tb-mini ">详情>></a>
                                                </div>
                                            </dl>
                                        </div>


                                    </td>
                                </tr>
                            </div>
                            <!--                            文章图片（点击跳转文章详情页）-->

                        </div>
                    </div>
                </div>
                        <%
                            }
                        %>

                </div>

                <!--                    底部bottom-->
                <div class="ui bottom attached segment">
                    <div class="ui middle aligned two column grid">
                        <div class="column">
                            <a href="InfoServlet?id=<%=top%>" class="ui  blue basic button">首页</a>
                            <a href="InfoServlet?id=<%=cur-5%>" class="ui  blue basic button">上一页</a>
                        </div>
                        <div class=" right aligned column">
                            <a href="InfoServlet?id=<%=cur+5%>" class="ui  blue basic button">下一页</a>
                            <a href="InfoServlet?id=<%=end%>" class="ui  blue basic button">尾页</a>
                        </div>
                    </div>
                </div>
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
