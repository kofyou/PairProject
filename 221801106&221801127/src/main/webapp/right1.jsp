<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="baseclass.Paper" %><%--
  Created by IntelliJ IDEA.
  User: 1647
  Date: 2021/3/28
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
    <link rel="stylesheet" href="./css/leftStyle.css">
    <link rel="stylesheet" href="./css/right1Style.css">
</head>
<body>

<%
    String result1;
    result1 =(String) request.getAttribute("collect_result");
    if (result1 == null){}
    else {
        if (result1.equals("true")) {
            out.println("<script>\n" +
                    "    alert(\"收藏成功！\");\n" +
                    "</script>");
        }
        else{
            out.println("<script>\n" +
                    "    alert(\"已经收藏过该论文！\");\n" +
                    "</script>");
        }
    }
%>

<div id = "wrap">
    <div id = "sidebar">
        <a href="./login.jsp" id="websitelink" class="lefta">
            <img src="./img/icon.png" alt="官网图标" id = "websiteicon">
            <span id="websitename">论文爬取网</span>
        </a>
        <div id = "selection">
            <a href="./right1.jsp" id="crawl" class="lefta">
                <img src="./img/搜索.png" alt="搜索图标">
                <span>爬取论文</span>
            </a>

            <a href="./right2.jsp" id="list" class="lefta">
                <img src="./img/列表.png" alt="列表图标">
                <span>论文列表</span>
            </a>

            <a href="./keyword.jsp" id="keyword" class="lefta">
                <img src="./img/关键词.png" alt="关键词图标" class="lefta">
                <span>关键词图谱</span>
            </a>

            <a href="./hotword.jsp" id="hotword" class="lefta">
                <img src="./img/热度.png" alt="热度图标" class="lefta">
                <span>热词走势</span>
            </a>

            <a href="./userhome.jsp" id="user" class="lefta">
                <img src="./img/用户.png" alt="用户图标">
                <span>个人中心</span>
            </a>
        </div>
    </div>
    <div id = "main">
        <div id = "shearchbox">
            <form action="../demo_war_exploded/MainServlet?type=crawl_paper" method="post">
                <span style="font-size: 18px;">标题：</span>
                <input type="text" name="search_all_paper" style="width:400px;height: 35px;border-radius:8px 8px 8px 8px;border:1px solid #000000;outline:none;">
                <input type="submit" value="搜索" style="width: 90px;height: 40px;border-radius:8px 8px 8px 8px;border:1px solid #000000;outline:none;background-color: #7f7f7f;">
                <input type="file">
            </form>
        </div>
        <%
            if (request.getAttribute("right1Search") == null) {
                out.println("<h3 style=\"margin-left:20%;\">无搜索结果<h3>");
            }
//            else{
//                List<Paper> list = (List<Paper>) request.getAttribute("right1Search");
//                out.println("<p>"+list.size()+"</p>");
//            }
            else {
                List<Paper> list = (List<Paper>) request.getAttribute("right1Search");
                out.println("<div  id = \"contentbyshearch\">");
                for (int i = 0; i < list.size()&&i<20; i++){
                    out.println("<p style=\"color: black\">"+list.get(i).论文名称+"</p>");
                    out.println("<P>");
                    out.println("关键词:");
                    for (String s : list.get(i).关键词){
                        out.println("<label style=\"background-color: #B2D235;border-radius:5px 5px 5px 5px;border:1px solid #B2D235;\">"+s+"</label>");
                    }
                    out.println("</p>");
//                    out.println("<p style=\"color: #D71345\">");
//                    out.println("摘要:"+list.get(i).摘要);
//                    out.println("</p>");
                    out.println("<p>摘要：</p>");
                    out.println("<p style=\"overflow-y: scroll;height: 100px;color: #D71345;border:1px solid black\">");
                    out.println(list.get(i).摘要);
                    out.println("</p>");
                    out.println("<p>");
                    out.println("<span style=\"color: goldenrod\">编号："+list.get(i).ID+"</span>");
                    out.println("<span>链接：</span>");
                    out.println("<a href=\"https://arxiv.org/pdf/2103.05494.pdf\" target=\"_blank\" style=\"display: inline;background-color: white;color: blue;\">"+list.get(i).原文链接+"</a>");
                    out.println("</p>");
        %>
            <form action="../demo_war_exploded/MainServlet?type=collect" method="post">
                <input name="<%=list.get(i).ID%>" type="submit" value="收藏" style="border:#00A6AC 1px solid ;margin-left: 80%;height: 35px;width: 80px;background-color: #00A6AC;border-radius: 5px;">
                <input name="收藏" type="text" value="<%=list.get(i).ID%>" style="display: none">
            </form>
            <hr>
        <%
                }
                out.println("</div>");
            }
        %>
<%--        <div  id = "contentbyshearch">--%>
<%--            <p style="color: black">Software Development During COVID-19 Pandemic: an Analysis of Stack Overflow and GitHub Mining Program Properties From Neural Networks Trained on Source Code Embeddings</p>--%>
<%--            <P>--%>
<%--                关键词：--%>
<%--                <label style="background-color: #B2D235;border-radius:5px 5px 5px 5px;border:1px solid #B2D235;">Software Engineering</label>--%>
<%--                <label style="background-color: #B2D235;border-radius:5px 5px 5px 5px;border:1px solid #B2D235;">Computers and Society</label>--%>
<%--            </P>--%>
<%--            <p style="color: #D71345">--%>
<%--                摘要：Abstract—The new coronavirus became a severe health issue for the world. This situation has motivated studies of differen tareas to combat this pandemic.--%>
<%--            </p>--%>
<%--            <p>--%>
<%--                <span style="color: goldenrod">编号：2131001</span>--%>
<%--                <a href="https://arxiv.org/pdf/2103.05494.pdf" target="_blank" style="display: inline;background-color: white;color: blue;">网址：https://arxiv.org/pdf/2103.05494.pdf</a>--%>
<%--            </p>--%>
<%--            <form action="" method="">--%>
<%--                <input type="button" value="收藏" style="border:#00A6AC 1px solid ;margin-left: 80%;height: 35px;width: 80px;background-color: #00A6AC;border-radius: 5px;">--%>
<%--            </form>--%>
<%--        </div>--%>
        <%
            if ((List<Map.Entry<String, Integer>>) session.getValue("tensearchwords")==null) { }
            else{
                String []color1 = {"#D71345","#F05B72","#FAA755","blue","blue","blue","blue","blue","blue","blue"};
                List<Map.Entry<String,Integer>> list =(List<Map.Entry<String, Integer>>) session.getValue("tensearchwords");
                out.println("<div id = \"tenhotshearch\" style=\"background-color: white\">");
                out.println("<p style=\"font-size: 16px;\">热门搜索</p>");
                for (int i = 0 ; i < list.size(); i++){
                out.println("<p class=\"tenword\" style=\"color: "+color1[i]+";\">"+(i+1)+list.get(i).getKey()+"</p>");
                }
                out.println("</div>");
            }
        %>
<%--        <div id = "tenhotshearch" style="background-color: white">--%>
<%--            <p style="font-size: 16px;">热门搜索</p>--%>
<%--            <p class="tenword" style="color: #D71345;">1.</p>--%>
<%--            <p class="tenword" style="color: #F05B72;">2.</p>--%>
<%--            <p class="tenword" style="color: #FAA755;">3.</p>--%>
<%--            <p class="tenword" style="color: blue">4.</p>--%>
<%--            <p class="tenword" style="color: blue">5.</p>--%>
<%--            <p class="tenword" style="color: blue">6.</p>--%>
<%--            <p class="tenword" style="color: blue">7.</p>--%>
<%--            <p class="tenword" style="color: blue">8.</p>--%>
<%--            <p class="tenword" style="color: blue">9.</p>--%>
<%--            <p class="tenword" style="color: blue">10.</p>--%>
<%--        </div>--%>
    </div>
</div>
</body>
<footer>

</footer>
</html>
