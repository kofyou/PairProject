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
            <a href="hotline.jsp"><button id="papersearch">热词趋势</button><br></a>
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
                    //三年最热热词出现次数测试
                    int[] num1 = itemsDao.keyYearNum(0);
                    for(int i=0;i<3;i++){
                        System.out.println(num1[i]+" ");
                    }
                    int[] num2 = itemsDao.keyYearNum(1);
                    for(int i=0;i<3;i++){
                        System.out.println(num2[i]+" ");
                    }
                    int[] num3 = itemsDao.keyYearNum(3);
                    for(int i=0;i<3;i++){
                        System.out.println(num3[i]+" ");
                    }
                    int[] num4 = itemsDao.keyYearNum(4);
                    for(int i=0;i<3;i++){
                        System.out.println(num4[i]+" ");
                    }
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