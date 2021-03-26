<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ page import="pojo.Paper" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% String path = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/paperList.css" />
    <script type="text/javascript">
        function deletePaper(paperTitle) {
            if (window.confirm("是否删除此篇论文？")) {
                window.location.href = "<%=path%>/PaperListServlet?operation=deletePaper&paperTitle="+paperTitle;
            }
        }

        function show(paperTitle) {
            window.location.href = "<%=path%>/PaperListServlet?operation=showPaper&paperTitle="+paperTitle;
        }
    </script>
</head>
<body>
    <form action="<%=path%>/PaperListServlet" method="get" id="queryForm">
        查询:<br>
        <input type="text" name="query" id="text">
        <input type="submit" value="查询" >
    </form>

    <table id="papertable">
        <tr>
            <th class="num">序号</th>
            <th class="title">论文题目</th>
            <th class="conference">会议</th>
            <th class="summary">摘要</th>
            <th class="link">原文链接</th>
            <th class="link">关键词</th>
            <th class="delete">操作</th>
        </tr>
        <c:forEach items="${requestScope.paperList}" var="pl" varStatus="vs">
            <tr>
                <td class="num" onclick="show('${pl.title}')"> ${vs.index + 1}</td>
                <td class="title" onclick="show('${pl.title}')"> ${pl.title} </td>
                <td class="conference" onclick="show('${pl.title}')"> ${pl.conference} </td>
                <td class="summary" onclick="show('${pl.title}')"> ${pl.summary} </td>
                <td class="link" onclick="show('${pl.title}')"> <a href=${pl.link} >${pl.link}</a> </td>
                <td class="link" onclick="show('${pl.title}')"> ${pl.keywords} </td>
                <td class="delete"> <button type="button" onclick="deletePaper('${pl.title}')">删除</button> </td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>