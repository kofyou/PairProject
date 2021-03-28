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
        
	    <link rel="stylesheet" type="text/css" href="mainStyles.css">
	    
    </head>
<body>
<!-- 读取本地数据到数据库 -->
<%
        //GetDataToDB logfile = new GetDataToDB("D://ECCV");

 %>
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
            <button id="papersearch">研究热词</button><br>
        </div>
    </div>
    <div id="section">
        <form action="">

            <input type="text" name="key" value="" placeholder="输入论文题目查找   支持模糊查询">
            <a href="main.jsp?keyword=document.getElementById("key").value"><input type="submit" value="搜索"></a>
        </form>
        <table id="table" >
            <tr>
                <td>
                
                <!-- 论文循环开始 -->
                <% 
                    ItemsDao itemsDao = new ItemsDao(); 
                    String hotword = itemsDao.getKeywordsFromDB();
                    List<Map.Entry<String, Integer>> kwList = itemsDao.getHotkw(hotword);
                    String[] topKw = itemsDao.words(kwList);
                    int[] topKwNum = itemsDao.num(kwList);
                    ArrayList<items> list = new ArrayList<items>();
                    if(request.getParameter("key")!=null){
                        list = itemsDao.getItemsByTitle(request.getParameter("key"));
                    }
                    else{
                        list = itemsDao.getAllItems();
                    }
               
                    if(list!=null&&list.size()>0)
                    {
                        for(int i=0;i<list.size();i++)
                        {
                            items item = list.get(i);
                %>   
                <div>
                    <dl>
                    <dt clsaa="dt_title">
                        <a href="details.jsp?id=<%=item.getId()%>"><%=item.getTitle() %></a>
                    </dt>
                    
                    <dd class="dd_abstract">abstract:&nbsp;&nbsp;&nbsp;&nbsp;<%=item.getAbstracts() %></dd> 
                    <dd class="dd_conference">来源会议: &nbsp;&nbsp;<%=item.getConference() %></dd> 
                    
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
    </div>

</body>
</html>