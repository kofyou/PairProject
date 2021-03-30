<% String path = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="mainCSS.css">
    <link href="indexCSS.css" type="text/css" rel="stylesheet">
</head>
<body style="margin: 0px" >
    <div id="bg">
        <div id="heading">
            热 词 分 析 统 计 平 台
        </div>
        <div id="menu">
            <form method="post" id="form3" action="<%=path%>/index.jsp" >
                <input type="submit" id="searchText" value="论文列表">
            </form>
            <form method="post" id="form" action="<%=path%>/iccv.jsp" >
                <input type="submit" id="analyse" value="热度走势">
            </form>
            <form method="post" id="form8" action="<%=path%>/analyze" >
                <input type="submit" id="top" value="TOP10">
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
                        <td width="26%">${post.keyWord} <input type="hidden" id="key" name="key" value="${key}"></td>
                        <td width="20%">${post.link}</td>
                        <td width="8%">${post.time}</td>
                        <td width="8%">${post.platform}</td>
                        <td width="5%">
                            <button type="submit" id="see" name="see" >查看</button>
                            <!--button id="edit">编辑</button-->
                            <!--button id="delete">删除</button-->
                        </td>
                    </tr>
                </c:forEach>
            </table>
            </form>
            <div id="paging">
                <table>
                    <br>
                    <br>
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
                        共&nbsp;&nbsp;${page}页&nbsp;&nbsp;
                        当前第&nbsp;${count+1}页&nbsp;&nbsp;
                    </tr>
                </table>
            </div>
        </div>
    </div>
</body>
</html>

<script>
    function check() {
        // 调用后端servlet，并将数据进行传递
        document.getElementById("form2").submit();
    }
</script>