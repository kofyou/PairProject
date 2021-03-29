<%--
  Created by IntelliJ IDEA.
  User: Charley Chen
  Date: 2021/3/26
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>XX论文详情页</title>
</head>
<body style="background: url(bg.jpg)">
    <%--/*上条栏使用 #c1cbd7*/--%>
    <%--/*上文字使用 #656565*/--%>
    <div style="color:#c1cbd7">
        <h1 style="background-color:#656565">
            论文检索系统
        </h1>
    </div>
<%--<c:forEach items="${paperInfo}" var="l" varStatus="vs">--%>
<h2>论文标题</h2>
<textarea rows="10" cols="30" name="paperTitle" readonly="readonly">
论文标题${l.getPaperTitle()}
</textarea>
</br>
<h3>论文作者</h3>
<textarea rows="10" cols="30" name="paperAbstract" readonly="readonly">
论文作者${l.getPaperAuthor()}
</textarea>
</br>
<h3>论文摘要</h3>
<textarea rows="10" cols="30" name="paperAbstract" readonly="readonly">
论文摘要${l.getPaperAbstract()}
</textarea>
<br>
<h3>论文链接</h3>
<textarea rows="10" cols="30" name="paperAbstract" readonly="readonly">
论文链接
</textarea>
</br>
<h3>论文关键词</h3>
<textarea rows="10" cols="30" name="paperAbstract" readonly="readonly">
论文关键词${l.getPaperKeywords()}
</textarea>
</br>
<h3>论文会议及时间</h3>
<textarea rows="10" cols="30" name="paperAbstract" readonly="readonly">
论文会议及时间${l.getPaperYear()}
</textarea>
<%--</c:forEach>--%>
</body>
</html>