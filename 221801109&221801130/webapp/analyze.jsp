<%@ page import="java.util.List" %>
<% String path = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html lang="en">
<head>
    <title>分析论文</title>
    <link href="mainCSS.css" type="text/css" rel="stylesheet">
    <link href="analyzeCSS.css" type="text/css" rel="stylesheet">
</head>
<body style="margin: 0px">
    <div id="container">
        <div id="heading">
        </div>
        <div id="menu">
            <form method="post" id="form" action="<%=path%>/index.jsp" >
                <input type="submit" id="searchText" value="论文列表">
            </form>
            <form method="post" id="form1" action="<%=path%>/iccv.jsp" >
                <input type="submit" id="analyse" value="热度走势">
            </form>
            <form method="post" id="form12" action="<%=path%>/analyze" >
                <input type="submit" id="top" value="TOP10">
            </form>
        </div>
        <div id="contentBody">
            <div id="KeyWordDiv">
                <p>
                    <br/>
                <p id="TOPtitle">热词TOP10</p>
                    <table>
                        <br/><br/><br/><br/>
                        <form method="post" id="form2" action="<%=path%>/hello">
                            <input type="submit" id="key8" name="string" value="${keyWordList[7]}" />
                        </form>
                        <form method="post" id="form3" action="<%=path%>/hello">
                            <input type="submit" id="key9" name="string" value="${keyWordList[8]}" />
                        </form>
                        <form method="post" id="form4" action="<%=path%>/hello">
                            <input type="submit" id="key10" name="string" value="${keyWordList[9]}" />
                        </form>

                        <br/><br/><br/><br/><br/><br/>

                        <form method="post" id="form5" action="<%=path%>/hello">
                            <input type="submit" id="key7" name="string" value="${keyWordList[6]}" />
                        </form>
                        <form method="post" id="form6" action="<%=path%>/hello">
                            <input type="submit" id="key1" name="string" value="${keyWordList[0]}" />
                        </form>
                        <form method="post" id="form7" action="<%=path%>/hello">
                            <input type="submit" id="key2" name="string" value="${keyWordList[1]}"/>
                        </form>
                        <form method="post" id="form8" action="<%=path%>/hello">
                            <input type="submit" id="key3" name="string" value="${keyWordList[2]}" />
                        </form>

                        <br/><br/><br/><br/><br/><br/>

                        <form method="post" id="form9" action="<%=path%>/hello">
                            <input type="submit" id="key6" name="string" value="${keyWordList[5]}"/>
                        </form>
                        <form method="post" id="form10" action="<%=path%>/hello">
                            <input type="submit" id="key5" name="string" value="${keyWordList[4]}" />
                        </form>
                        <form method="post" id="form11" action="<%=path%>/hello">
                            <input type="submit" id="key4" name="string" value="${keyWordList[3]}" />
                        </form>
                    </table>
                </p>
            </div>
        </div>
    </div>
</body>
</html>

<script>

</script>
