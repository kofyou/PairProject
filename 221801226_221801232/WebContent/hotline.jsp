<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="dao.*"%>
<%@ page import="entity.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<head>
    <base href="<%=basePath%>">
    
    <title>Paper Search For U -热词走势</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <script src="echarts.min.js"></script>
    <link href="line.css" rel="stylesheet" type="text/css">
</head>
<body>
    <div id="nav">
        <div class="divimg">
            <img src="images/flower.jpg" />
        </div>
        <div id="menu">
            <a href="main.jsp"><button id="papersearch">论文检索</button><br></a>
        </div>
        <div id="menu">
            <a href="collection.jsp"><button id="papersearch">论文收藏夹</button><br></a>
        </div>
        <div id="menu">
            <a href="top.jsp"><button id="papersearch">热门领域</button><br></a>
        </div>
        <div id="menu">
            <a href="hotline.jsp"><button id="papersearch">热词趋势</button><br></a>
        </div>
    </div>
    <div id="section">

        <div id="toplist">
            <h1 id="h1">热词热度表</h1>

            <div>
                <div id="onetwo">
                <div id="main" style="width: 500px;height:400px;"></div>
                
                <script>
                    // 创建 echarts 实例。
                    var myChart=echarts.init(document.getElementById("main"));
                    var option = {
                        title:{
                            text:'deep learning'

                        },
                        legend: {
                            data:['热度']
                        },
                        grid: {

                        },
                        toolbox: {
                            show:true,
                            feature:{
                                saveAsImage:{
                                    show:true
                                }
                            }
                        },
                        xAxis: {
                            data:["2016","2018","2020"]
                            

                            
                        },
                        yAxis: {},
                        // 这里有多个系列，也是构成一个数组。
                        series: [{
                            name:"热度",
                            type:"line",
                            data:[25,101,90]
                            
                          
                        }]
                };
                myChart.setOption(option);
                </script>
                <div id="two" style="width: 500px;height:400px;"></div>
                <script>
                    // 创建 echarts 实例。
                    var myChart=echarts.init(document.getElementById("two"));
                    var option = {
                        title:{
                            text:'object detection'

                        },
                        legend: {
                            data:['热度']
                        },
                        grid: {

                        },
                        toolbox: {
                            show:true,
                            feature:{
                                saveAsImage:{
                                    show:true
                                }
                            }
                        },
                        xAxis: {
                            data:["2016","2018","2020"]
                            

                            
                        },
                        yAxis: {},
                        // 这里有多个系列，也是构成一个数组。
                        series: [{
                            name:"热度",
                            type:"line",
                            data:[11,48,87]
                            
                          
                        }]
                };
                myChart.setOption(option);
                </script>
                </div>
                <div id="three" style="width: 500px;height:400px;"></div>
                <script>
                    // 创建 echarts 实例。
                    var myChart=echarts.init(document.getElementById("three"));
                    var option = {
                        title:{
                            text:'semantic seqmentation'

                        },
                        legend: {
                            data:['热度']
                        },
                        grid: {

                        },
                        toolbox: {
                            show:true,
                            feature:{
                                saveAsImage:{
                                    show:true
                                }
                            }
                        },
                        xAxis: {
                            data:["2016","2018","2020"]
                            

                            
                        },
                        yAxis: {},
                        // 这里有多个系列，也是构成一个数组。
                        series: [{
                            name:"热度",
                            type:"line",
                            data:[4,27,46]
                            
                          
                        }]
                };
                myChart.setOption(option);
                </script>
                
                
                <div id="four" style="width: 500px;height:400px;"></div>
                <script>
                    // 创建 echarts 实例。
                    var myChart=echarts.init(document.getElementById("four"));
                    var option = {
                        title:{
                            text:'domain adaptation'

                        },
                        legend: {
                            data:['热度']
                        },
                        grid: {

                        },
                        toolbox: {
                            show:true,
                            feature:{
                                saveAsImage:{
                                    show:true
                                }
                            }
                        },
                        xAxis: {
                            data:["2016","2018","2020"]
                            

                            
                        },
                        yAxis: {},
                        // 这里有多个系列，也是构成一个数组。
                        series: [{
                            name:"热度",
                            type:"line",
                            data:[30,70,36]
                            
                          
                        }]
                };
                myChart.setOption(option);
                </script>

            </div>
        </div>


    </div>
</body>
</html>