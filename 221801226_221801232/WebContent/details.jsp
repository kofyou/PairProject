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
	<title>Paper Search For U -details</title>
	<!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <link href="css/main.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="js/lhgcore.js"></script>
    <script type="text/javascript" src="js/lhgdialog.js"></script>
    <script type="text/javascript">
        function selflog_show(id)
        { 
            var num =  document.getElementById("number").value; 
            J.dialog.get({id: 'haoyue_creat',title: '添加成功',width: 600,height:400, link: '<%=path%>/servlet/CollectionServlet?id='+id+'&num='+num+'&action=add', cover:true});
        }
        function add()
        {
            var num = parseInt(document.getElementById("number").value);
            if(num<100)
            {
                document.getElementById("number").value = ++num;
            }
        }
        function sub()
        {
            var num = parseInt(document.getElementById("number").value);
            if(num>1)
            {
                document.getElementById("number").value = --num;
            }
        }
     
    </script>
    
    <style type="text/css">
        hr{
            
            border-color:FF7F00; 
        }
        
        div{
            float:left;
            margin-left: 30px;
            margin-right:30px;
            margin-top: 5px;
            margin-bottom: 5px;
            
        }
        div dd{
            margin:0px;
            font-size:10pt;
        }
        div dd.dd_name
        {
            color:blue;
        }
        div dd.dd_city
        {
            color:#000;
        }
        div #cart
        {
            margin:0px auto;
            text-align:right; 
        }
        span{
            padding:0 2px;border:1px #c0c0c0 solid;cursor:pointer;
        }
        a{
            text-decoration: none; 
        }
    </style>
</head>
<body>
<h1>论文详情</h1>
    <a href="index.jsp">首页</a> >> <a href="index.jsp">论文列表</a>
    <hr>
        <table width="750" height="60" cellpadding="0" cellspacing="0" border="0">
        <tr>
            <!-- 论文详情 -->
            <% 
                ItemsDao itemDao = new ItemsDao();
                items item = itemDao.getItemsById(Integer.parseInt(request.getParameter("id")));
                if(item!=null)
                {
            %>
            <td width="70%" valign="top">
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
                <img src="images/buy_now.png"><a href="javascript:selflog_show(<%=item.getId()%>)"><img src="images/in_cart.png"></a><a href="servlet/CartServlet?action=show"><img src="images/view_cart.jpg"/></a>
                </div>
            </td>
            <% 
                }
            %>
          
          
          
        </tr>
        </table>
</body>
</html>