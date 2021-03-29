<%@ page import="pojo.KeyWord" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8"  %>
<% String path = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
    <title>第一个 ECharts 实例</title>
    <!-- 引入 echarts.js -->
    <script type="text/javascript" src="./dist/echarts.js"></script>
</head>
<body>

<form method="post" id="form" action="<%=path%>/ChartServlet">
    <div id="search"><input id="input2" type="submit" name="searchButton" value="生成图表">  </div>
</form>

<%!
    List<KeyWord> list = new ArrayList<>();
%>

<%
    list = (List<KeyWord>) request.getAttribute("list");
    if(list != null) {
%>

<table border="1" width="400">
    <tr>
        <td>关键词</td>
        <td>年份</td>
    </tr>

    <%
        for (KeyWord word : list) {
    %>

    <tr>
        <td><%=word.getWord() %></td>
        <td><%=word.getYear() %></td>
    </tr>
    <%
            }
        }
    %>

</table>

<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '第一个 ECharts 实例'
        },
        tooltip: {},
        legend: {
            data:['销量']
        },
        xAxis: {
            data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
        },
        yAxis: {},
        series: [{
            name: '销量',
            type: 'bar',
            data: [5, 20, 90, 10, 10, 20]
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>
</body>
</html>