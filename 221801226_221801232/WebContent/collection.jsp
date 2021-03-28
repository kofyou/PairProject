<%@ page import="dao.*"%>
<%@ page import="entity.*"%>
<%@ page import="servlet.*"%>
<%@ page import="util.*"%>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Set" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
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
    <title>Paper Search For U -收藏夹</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
    <link type="text/css" rel="stylesheet" href="collStyles.css" />
    <script language="javascript">
            function delcfm() {
                if (!confirm("确认要删除？")) {
                    window.event.returnValue = false;
                }
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
            <a href="top.jsp"><button id="papersearch">热门领域</button><br></a>
        </div>
        <div id="menu">
            <button id="papersearch">研究热词</button><br>
        </div>
    </div>
    <div id="section">
        <h1>我的收藏夹</h1>
        <form action="">

            <input type="text" name="key" value="" placeholder="输入论文题目查找   支持模糊查询">
            <a href="main.jsp?keyword=document.getElementById("key").value"><input type="submit" value="搜索"></a>
        </form>
        <div id="paperlist">
            <form action="" method="">
                <table>
                    <tr>
                        <th>论文</th>
                        <th>来源会议</th>
                        <th>关键词</th>
                        <th>发布时间</th>
                        <th>操作</th>
                    </tr>
                <% 
                   //首先判断session中是否有收藏夹对象
                   if(request.getSession().getAttribute("collection")!=null)
                   {
                %>
                <!-- 循环的开始 -->
                     <% 
                         collection collection = (collection)request.getSession().getAttribute("collection");
                         HashMap<items,Integer> paperlist = collection.getPaperList();
                         Set<items> items = paperlist.keySet();
                         Iterator<items> it = items.iterator();
                         
                         while(it.hasNext())
                         {
                            items i = it.next();
                     %> 
                <tr name="papers" id="papers_id_1">
                    <td class="title"><a href=""><%=i.getTitle()%></a></td>  
                    <td class="conference"><%=i.getConference() %></td>
                    <td class="keyword"><%=i.getKeyword() %></td>
                    <td class="time"><%=i.getTime() %></td>        
                    
                    <td class="delete">
                      <a href="servlet/CollectionServlet?action=delete&id=<%=i.getId()%>" onclick="delcfm();">删除</a>                                      
                    </td>
                </tr>
                     <% 
                         }
                     %>
                <!--循环的结束-->
                
            </table>
             
              <% 
                }
             %>
            
            </form>
        </div>
    </div> 
</body>
</html>