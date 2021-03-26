<%@ page import="dao.*"%>
<%@ page import="entity.*"%>
<%@ page import="servlet.*"%>
<%@ page import="util.*"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
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
        <title>Paper Search For U</title>
        
	    <link rel="stylesheet" type="text/css" href="css/styles.css">
	    
    </head>
<body>
<!-- 读取本地数据到数据库 -->
<%
        //GetDataToDB logfile = new GetDataToDB("D://ECCV");

 %>
    <h1>论文库</h1>
        <hr>
    
        <table width="750" height="60" cellpadding="0" cellspacing="0" border="0">
        <tr>
            <td>
            
            <!-- 论文循环开始 -->
            <% 
                ItemsDao itemsDao = new ItemsDao(); 
                ArrayList<items> list = itemsDao.getAllItems();
                //ArrayList<items> list = itemsDao.getItemsByTitle("Deep");
                if(list!=null&&list.size()>0)
                {
                    for(int i=0;i<list.size();i++)
                    {
                        items item = list.get(i);
            %>   
            <div>
                <dl>
                <dt>
                    <a href="details.jsp?id=<%=item.getId()%>"></a>
                </dt>
                <dd class="dd_title"><%=item.getTitle() %></dd> 
                <dd class="dd_abstract">abstract:&nbsp;&nbsp;&nbsp;&nbsp;<%=item.getAbstracts() %></dd> 
                <dd class="dd_conference">conference: &nbsp;&nbsp;<%=item.getConference() %></dd> 
                
                </dl>
            </div>
            <!-- 论文循环结束 -->
            
            <%
                    }
                } 
            %>
            </td>
        </tr>
        </table>
</body>
</html>