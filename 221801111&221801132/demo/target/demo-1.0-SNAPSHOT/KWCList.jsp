<%@ page import="pojo.Paper" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.PaperDAO" %>
<%@ page import="dao.PaperDAOImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath(); %>
<!DOCTYPE html>
<html>

<body>

<%
    String type = (String) request.getAttribute("type");
    PaperDAO paperDAO = new PaperDAOImpl();
    List<Paper> list = new ArrayList<>();
    list = paperDAO.listGetByKeyword(type);
    if(list != null) {
%>

<table border="1" width="400">
    <tr>
        <td>论文标题</td>
        <td>摘要</td>
        <td>原文链接</td>
        <td>关键词</td>
        <td>年份</td>
        <td>类别</td>
    </tr>

    <%
        for (Paper paper : list) {
    %>

    <tr>
        <td><%=paper.getTitle() %></td>
        <td><%=paper.getSummary() %></td>
        <td><a href=<%=paper.getLink() %>> <%=paper.getLink() %></a></td>
        <td><%=paper.getKeyword() %></td>
        <td><%=paper.getYear() %></td>
        <td><%=paper.getType() %></td>
    </tr>
    <%
            }
        }
    %>

</table>


</body>
</html>


