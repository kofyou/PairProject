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
<html>
<body>

<a href ="paperInfo.jsp" target ="rightframe">论文信息</a><br />
<a href ="paperList.jsp" target ="rightframe">论文搜索</a><br />

</body>
</html>
