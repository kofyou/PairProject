<% String path = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <style>
        #table01{
            border-collapse: collapse;
            padding: 0;
            margin: 0;
            table-layout:fixed;
            position: absolute;
            top: 30%;
            left: 20%;
        }
        th{
            font: bold 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
            color: #4f6b72;
            border-right: 1px solid #C1DAD7;
            border-bottom: 1px solid #C1DAD7;
            border-top: 1px solid #C1DAD7;
            letter-spacing: 2px;
            text-transform: uppercase;
            text-align: left;
            padding: 6px 6px 6px 12px;
            background: #CAE8EA  no-repeat;
        }
        td{
            font: bold 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
            color: #4f6b72;
            border-right: 1px solid #C1DAD7;
            border-bottom: 1px solid #C1DAD7;
            border-top: 1px solid #C1DAD7;
            letter-spacing: 2px;
            text-transform: uppercase;
            text-align: left;
            padding: 6px 6px 6px 12px;
            background: white  no-repeat;
            word-break:keep-all;     /* 不换行 */
            white-space:nowrap;      /* 不换行 */
            overflow:hidden;         /* 内容超出宽度时隐藏超出部分的内容 */
            text-overflow:ellipsis;  /* 溢出时显示省略标记...；需与overflow:hidden;一起使用*/

        }
        #see{
            background: #C7E1E2  no-repeat;
            border:none;
        }
        #edit{
            background: #C7E1E2  no-repeat;
            border:none;
        }
        #delete{
            background: #C7E1E2  no-repeat;
            border:none;
        }
        #test02{
            background: #94E7E2  no-repeat;
        }
        #string{
            backgroundcolor: black;
            height: 55px;
            width: 550px;
        }
        #button1{
            height: 50px;
            width: 100px;
            font-size: 20px;
        }
        div#input{
            position: absolute;
            top: 20%;
            left: 34%;
        }
        div#bg{
            width: 100%;
            height: 100%;
        }
        #paging{
            margin-top: 40%;

        }
        #previous{
            border-radius: 20px;
            background-color: transparent;
            width: 80px;
            height: 35px;
            border:1px solid gray;
            outline: none;
        }
        #previous:hover{
            background-color: lightgray;
        }
        #previous:active{
            background-color: gray;
        }
        #next{
            border-radius: 20px;
            background-color: transparent;
            width: 80px;
            height: 35px;
            border:1px solid gray;
            outline: none;
        }
        #next:hover{
            background-color: lightgray;
        }
        #next:active{
            background-color: gray;
        }
        div#heading{
            background-color: dodgerblue;
            width: 100%;
            height: 12%;
            text-align: center;
        }
        div#menu{
            background-color: midnightblue;
            width: 12%;
            height: 88%;
            float: left;
        }
        div#body{
            background-color: aliceblue;
            width: 88%;
            height: 88%;
            float: left;
        }
        #searchText{
            background: rgba(0,0,0,0);
            border-width: 0;
            color: white;
            text-align:center;
            font-size: 30px;
            position: absolute;
            top: 20%;
            left: 2%;
            border-radius: 10px;
        }
        #analyse{
            background: rgba(0,0,0,0);
            border-width: 0;
            color: white;
            text-align:center;
            font-size: 30px;
            position: absolute;
            top: 40%;
            left: 2%;
            border-radius: 10px;
        }
        #contentBody{
            width: 88%;
            height: 88%;
            float: left;
            background-color: lightgray;
        }
        #searchText:hover{
            background-color: lightgray;
            color: midnightblue;
            border-radius: 10px;
        }
        #analyse:hover{
            background-color: lightgray;
            color: midnightblue;
            border-radius: 10px;
        }
    </style>
</head>
<body style="margin: 0px" >
    <div id="bg">
        <div id="heading"></div>
        <div id="menu">
            <form method="post" id="form3" action="<%=path%>/index.jsp" >
                <input type="submit" id="searchText" value="论文列表">
            </form>

            <form method="post" id="form6" action="<%=path%>/analyze" >
                <input type="submit" id="analyse" value="分析论文">
            </form>
        </div>
        <div id="body">
            <div id="input">
                <form method="post" id="form1" action="<%=path%>/hello">
                    <input type="text" id="string" name="string" placeholder="请输入相关信息进行查询" >
                    <input type="hidden" id="count" name="count" value="0">
                    <input type="submit" id="button1" value="搜索"  >
                </form>
            </div>
            <form method="post" id="form2" action="<%=path%>/content">
            <table id="table01"  width="70%">
                <th width="15%">题目</th>
                <th width="15%">摘要</th>
                <th width="15%">关键词</th>
                <th width="14%">原文链接</th>
                <th width="14%">发布时间</th>
                <th width="14%">发布平台</th>
                <th width="10%">操作</th>
                <c:forEach items="${postList}" var="post">
                    <tr>
                        <td width="15%">${post.title} </td>
                        <td width="15%">${post.content} <input type="hidden" id="title" name="title" value="${post.title}"></td>
                        <td width="15%">${post.keyWord} <input type="hidden" id="key" name="key" value="${key}"></td>
                        <td width="14%">${post.link}</td>
                        <td width="14%">${post.time}</td>
                        <td width="14%">${post.platform}</td>
                        <td width="10%">
                            <button type="submit" id="see" name="see" >查看</button>
                            <button id="edit">编辑</button>
                            <!--button id="delete">删除</button-->
                        </td>
                    </tr>
                </c:forEach>
            </table>
            </form>
            <div id="paging"> <!-----------翻页功能-------------->
                <table>
                    <tr>
                        <form method="post" id="form4" action="<%=path%>/hello">
                            <c:if test="${key!=null}">
                                <input type="hidden" id="string1" name="string1" value="${key}">
                                <input type="hidden" id="pre" name="pre" value="${count}">
                            </c:if>
                            <input type="submit" id="previous" value="Previous" style="margin-left: 70%" >

                        </form>
                        <form method="post" id="form5" action="<%=path%>/hello">
                            <c:if test="${key!=null}">
                                <input type="hidden" id="string2" name="string2" value="${key}">
                                <input type="hidden" id="nex" name="nex" value="${count}">
                            </c:if>
                            <input type="submit" id="next" value="Next" style="margin-left: 1%" >
                        </form>
                        共&nbsp;&nbsp;${page}页
                        当前第&nbsp;${count+1}页&nbsp;&nbsp;
                    </tr>
                </table>

            </div>
        </div>
    </div>
</body>
</html>
