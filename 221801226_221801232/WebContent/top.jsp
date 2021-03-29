<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="dao.*"%>
<%@ page import="entity.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<head>
    <base href="<%=basePath%>">
    
    <title>Paper Search For U -热门领域</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
 
    <link rel="stylesheet" type="text/css" href="top.css">


<style>
    body {
	    background-image: url(back.JPG);
	    margin: 0;
	}
	a{text-decoration:none;}
	.divimg img {
	    margin-bottom: 10px;
	    width: 150px;
	    height: 150px;
	    margin-top: 10px;
	    margin-left: 50px;
	}
	
	#nav {
	    margin-top: 40px;
	    text-align: center;
	    line-height: 30px;
	    width: 300px;
	    float: left;
	    padding: 5px;
	    font-size: 25px;
	    border-style: dotted solid;
	    color: white;
	    border-width: 0 1px 0 0;
	}
	
	#menu {
	    margin-left: 0;
	    margin-right: 0;
	    border-style: groove;
	    border-color: #8b8b8b;
	}
	
	#papersearch {
	    color: #eeeeee;
	    border: 0;
	    background-color: transparent;
	    width: 300px;
	    height: 100px;
	    font-size: 30px;
	    cursor: pointer;
	    font-family: Verdana, Geneva, Tahoma, sans-serif;
	}
	
	#section {
	    width: 1000px;
	    float: left;
	    padding: 10px;
	}
	
	#h1 {
	    color: #1d6141;
	    text-align: center;
	    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	}
	
	hr {
	    border-color: FF7F00;
	}
	
	#toplist {
	    width: 980px;
	    border-style: dotted dashed solid double;
	    border-radius: 12px;
	    background-color: #ffffff;
	    margin-left: 20px;
	    margin-top: 30px;
	    background: rgba( 255, 255, 255, 0.8);
	}
	
	#p1 {
	    color: #043820;
	    font-size: 32px;
	    margin-left: 30px;
	}
	
	#p2 {
	    color: #1b4b35;
	    font-size: 30px;
	    margin-top: 150px;
	    margin-left: 370px;
	}
	
	#p3 {
	    color: #21503b;
	    font-size: 28px;
	    margin-top: 100px;
	    margin-left: 300px;
	}
	
	#p4 {
	    color: #255f44;
	    font-size: 26px;
	    float: left;
	    margin-left: 30px;
	}
	
	#p5 {
	    color: #3d7a5e;
	    font-size: 24px;
	    margin-top: 10px;
	    margin-left: 80px;
	    float: left;
	}
	
	#p6 {
	    color: #398050;
	    font-size: 22px;
	    margin-top: 0px;
	    margin-left: 0px;
	    float: left;
	}
	
	#p7 {
	    color: #329632;
	    font-size: 20px;
	    margin-left: 300px;
	    float: left;
	}
	
	#p8 {
	    color: #59a15c;
	    font-size: 18px;
	    margin-top: 0px;
	    margin-left: 160px;
	    float: left;
	}
	
	#p9 {
	    color: #6ea85f;
	    font-size: 16px;
	    float: left;
	    margin-left: 50px;
	}
	
	#p10 {
	    color: #84b667;
	    font-size: 14px;
	    margin-left: 120px;
	}

</style>

    
</head>
<body>
        <div id="nav">
        <div class="divimg">
            <img src="images/flower.jpg" />
        </div>
        <div id="menu">
            <a href="main.jsp"><button id="papersearch">论文检索</button><br></a>
        </div>
        <div id="menu">
            <a href="collection.jsp"><button id="papersearch">论文收藏夹</button><br></a>
        </div>
        <div id="menu">
            <a href="top.jsp"><button id="papersearch">热门领域</button><br></a>
        </div>
        <div id="menu">
            <a href="hotline.jsp"><button id="papersearch">热词趋势</button><br></a>
        </div>
    </div>
    <div id="section">

        <div id="toplist">
            <h1 id="h1">top10个热门领域</h1>
             <% 
                    ItemsDao itemsDao = new ItemsDao(); 
		            String allKw = itemsDao.getKeywordsFromDB();
		            String[] topkw = itemsDao.words(itemsDao.getHotkw(allKw));
             %>
            <div>
                <a href="main.jsp"><p id="p3"><%= topkw[2] %></p></a>
            </div>
            <div>
                <a href="main.jsp"><p id="p8"><%= topkw[7] %></p></a>
                <a href="main.jsp"><p id="p4"><%= topkw[3] %></p></a>

                <a href="main.jsp"><p id="p5"><%= topkw[4] %></p></a>
                <a href="main.jsp"><p id="p10"><%= topkw[9] %></p></a>
            </div>
            <div>

                <a href="main.jsp"><p id="p1"><%= topkw[0] %></p></a>
                <a href="main.jsp"><p id="p6"><%= topkw[5] %></p></a>

            </div>
            <a href="main.jsp"><p id="p7"><%= topkw[6] %></p></a>
            <a href="main.jsp"><p id="p9"><%= topkw[8] %></p></a>
            <div>
                <a href="main.jsp"><p id="p2"><%= topkw[1] %></p></a>
            </div>
        </div>


    </div>
</body>
</html>