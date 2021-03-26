<% String path = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
</head>
<body style="margin: 0px" >

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
</style>

<div id="bg">
    <div id="heading"></div>
    <div id="menu">

        <input type="submit" id="searchText" value="论文列表">
        
        <input type="submit" id="analyse" value="分析论文">
        
    </div>
    

</div>
</body>
</html>

<script>
 
</script>