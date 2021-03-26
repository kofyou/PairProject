<% String path = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html lang="en">
<head>
    <title>文章详情</title>
</head>
<body style="margin: 0px">
<style>
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
    }

</style>

<div id="container">
    <div id="heading">

    </div>
    <div id="menu">
        <form method="post" id="form" action="<%=path%>/index.jsp" >
            <input type="submit" id="searchText" value="论文列表">
        </form>
        <input type="button" id="analyse" value="分析论文">
    </div>
    <div id="body" >
        <form style="margin-left: 20%; margin-top: 5%">
            论文标题
            <input id="title" type="text" style="width: 50%; height: 5%;" value="${title}">
            <br/><br/>
            论文内容
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
                    <form>
                        <input id="store" type="button" value="保存修改" style="margin-left: 30%">
                    </form>
                    
                    <input type="hidden" id="string1" name="string1" value="${string}" >
                    <input id="delete" type="submit" value="删除论文" style="margin-left: 5%">
                    
                 
                    <input type="hidden" id="string" name="string" value="${string}" >
                    <input id="return" type="submit" value="返回" style="margin-left: 5%">
                   
                </tr>

            </table>

        </div>
    </div>
</div>
</body>
</html>
