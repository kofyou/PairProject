<%--
  Created by IntelliJ IDEA.
  User: Charley Chen
  Date: 2021/3/26
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("context", request.getContextPath());
%>
<!DOCTYPE html>
<html>

<head>
    <style>
        /*侧边栏使用 #8696a7*/
        /*上条栏使用 #c1cbd7*/
        /*侧边文字使用 #7a7281*/
        /*上文字使用 #656565*/
        #info{
            background-color: #ffffff;
            width: 150px;
            height: 35px;
            line-height: 10px;
            border-radius: 15px;
            color: #7a7281;
            font-size: 14px;
            text-align: center;
            box-shadow: 0px 2px 6px 0px rgba(0, 0, 0, 0.4);
            border: none;
            font-weight: bold;
        }
        #msg{
            background-color: #ffffff;
            width: 150px;
            height: 35px;
            line-height: 10px;
            border-radius: 15px;
            color: #7a7281;
            font-size: 14px;
            text-align: center;
            box-shadow: 0px 2px 6px 0px rgba(0, 0, 0, 0.4);
            border: none;
            font-weight: bold;
        }
        #tendency{
            background-color: #ffffff;
            width: 150px;
            height: 35px;
            line-height: 10px;
            border-radius: 15px;
            color: #7a7281;
            font-size: 14px;
            text-align: center;
            box-shadow: 0px 2px 6px 0px rgba(0, 0, 0, 0.4);
            border: none;
            font-weight: bold;
        }
        #topten{
            background-color: #ffffff;
            width: 150px;
            height: 35px;
            line-height: 10px;
            border-radius: 15px;
            color: #7a7281;
            font-size: 14px;
            text-align: center;
            box-shadow: 0px 2px 6px 0px rgba(0, 0, 0, 0.4);
            border: none;
            font-weight: bold;
        }
        #wordcloud{
            background-color: #ffffff;
            width: 150px;
            height: 35px;
            line-height: 10px;
            border-radius: 15px;
            color: #7a7281;
            font-size: 14px;
            text-align: center;
            box-shadow: 0px 2px 6px 0px rgba(0, 0, 0, 0.4);
            border: none;
            font-weight: bold;
        }
    </style>
</head>

<%--/*侧边栏背景使用 #8696a7*/--%>
<body style="background-color: #8696a7;">

<div >
    <h1>
    </h1>
</div>


<a id = "msg" href ="paperInfo.jsp" target ="rightframe">  论文信息  </a><br />
<div >
    <h1>
    </h1>
</div>
<a id = "info" href ="paperList.jsp" target ="rightframe">  论文搜索  </a><br />
<div >
    <h1>
    </h1>
</div>
<a id = "tendency" href ="paperTendency.jsp" target ="rightframe">  论文趋势  </a><br />
<div >
    <h1>
    </h1>
</div>
<a id = "topten" href ="topten.jsp" target ="rightframe"> 热词TOP10 </a><br />
<div >
    <h1>
    </h1>
</div>
<a id = "wordcloud" href ="wordCloud.jsp" target ="rightframe"> 词云 </a><br />

</body>
</html>
