<%@ page import="dao.*"%>
<%@ page import="entity.*"%>
<%@ page import="servlet.*"%>
<%@ page import="util.*"%>
<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<meta charset="UTF-8">
	<meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
	<title>Paper Search For U -论文详情</title>
	
    <link rel="stylesheet" type="text/css" href="detailStyles.css">
    
    <script type="text/javascript" src="js/lhgcore.js"></script>
    <script type="text/javascript" src="js/lhgdialog.js"></script>
    <script type="text/javascript">
        function selflog_show(id)
        { 
            var num =  1; 
            J.dialog.get({id: 'haoyue_creat',title: '添加成功',width: 600,height:400, link: '<%=path%>/servlet/CollectionServlet?id='+id+'&num='+num+'&action=add', cover:true});
        }
     
    </script>
    
</head>
<body>
    <div id="nav">
        <div class="divimg">
            <img src="flower.jpg" />
        </div>
        <div id="menu">

            <a href="main.jsp"><button id="papersearch">论文检索</button><br></a>
        </div>
        <div id="menu">
            <a href="collection.jsp"><button id="papersearch">论文收藏夹</button><br></a>
        </div>
        <div id="menu">
            <button id="papersearch">热门领域</button><br>
        </div>
        <div id="menu">
            <button id="papersearch">研究热词</button><br>
        </div>
    </div>
    <div id="section">
        <h1>论文详情</h1>
        <hr>
        <div id="tablelist">
        
            <table id="table" >
                <tr>
                <!-- 论文详情 -->
                <% 
                    ItemsDao itemDao = new ItemsDao();
                    items item = itemDao.getItemsById(Integer.parseInt(request.getParameter("id")));
                    if(item!=null)
                    {
                %>
                <td >
                    <table>
                
                        <tr>
                            <td><B><%=item.getTitle() %></B></td> 
                        </tr>
                        <tr>
                            <td>abstract：<%=item.getAbstracts()%></td>
                        </tr>
                        <tr>
                            <td>conference：<%=item.getConference() %></td>
                        </tr>
                        <tr>
                            <td>keyword：<%=item.getKeyword() %></td>
                        </tr>
                        <tr>
                            <td>time：<%=item.getTime() %></td>
                        </tr>
                        <tr>
                            <td>link：<%=item.getLink() %></td>
                        </tr>
                    </table>
                    <div id="collection">
                    <a href="javascript:selflog_show(<%=item.getId()%>)"><button id="collect">收藏</button></a>
                    
                    <a href="servlet/CollectionServlet?action=show"><button id="collection">查看收藏夹</button></a>
                    </div>
                </td>
                <% 
                    }
                %>
          
          
          
                </tr>
            </table>
        </div>
    </div>
    </body>
</html>