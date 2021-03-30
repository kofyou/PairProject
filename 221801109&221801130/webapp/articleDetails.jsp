<% String path = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html lang="en">
<head>
    <title>文章详情</title>
    <link rel="stylesheet" type="text/css" href="mainCSS.css">
    <link rel="stylesheet" type="text/css" href="articleCSS.css">
</head>
<body style="margin: 0px">

    <div id="container">
        <div id="heading">
            热 词 分 析 统 计 平 台
        </div>
        <div id="menu">
            <form method="post" id="form" action="<%=path%>/index.jsp" >
                <input type="submit" id="searchText" value="论文列表">
            </form>
            <form method="post" id="form1" action="<%=path%>/iccv.jsp" >
                <input type="submit" id="analyse" value="热度走势">
            </form>
            <form method="post" id="form5" action="<%=path%>/analyze" >
                <input type="submit" id="top" value="TOP10">
            </form>
        </div>
        <div id="body"  >
            <form style="margin-left: 20%; margin-top: 5%">
                论文标题
                <input id="title" type="text" style="width: 50%; height: 5%;" value="${title}">
                <br/><br/>
                论文摘要
                <textarea id="content"  style="width: 50%; height: 200px; resize: none" >${content}</textarea>
                <br/><br/>
                论文链接
                <input id="tag" type="text" style="width: 50%; height: 8%;" value="${link}" >
                <br/><br/>
                论文标签
                <textarea id="address" type="text" style="width: 50%; height: 8%; " wrap="virtual">${keyWord}</textarea>
                <br/><br/>
                发布时间
                <input id="time" type="text" style="width: 50%; height: 5%;" value="${time}">
                <br/><br/>
                发布平台
                <input id="platform" type="text" style="width: 50%; height: 5%;" value="${platform}">

            </form>
            <div>
                <table>
                    <tr>
                        <form method="post" id="form2" action="<%=path%>/delete">
                            <input type="hidden" id="string1" name="string1" value="${string}" >
                            <input id="delete" type="button" value="删除论文" style="margin-left: 35%" onclick="del(${string})">
                        </form>
                        <form method="post" id="form3" action="<%=path%>/hello">
                            <input type="hidden" id="string" name="string" value="${string}" >
                            <input id="return" type="submit" value="返回" style="margin-left: 5%">
                        </form>
                    </tr>

                </table>

            </div>
        </div>
    </div>
</body>
</html>
<script>

    function del(url){

        if(confirm("确定要删除该论文？")){

            window.location.href = "delete?string="+url;

        }

        document.form1.action("delete?string="+url);

    }

</script>
