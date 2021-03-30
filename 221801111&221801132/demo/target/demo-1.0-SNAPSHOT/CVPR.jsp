<%@ page import="pojo.HotWord" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8"  %>
<% String path = request.getContextPath(); %>

<!DOCTYPE html>
<html>
<head>
    <title>CVPR</title>
    <meta charset="utf-8">
    <!-- 引入 ECharts 文件 -->
    <script src="dist/echarts.min.js"></script>
</head>

<form method="post" id="form1" action="<%=path%>/ChartServlet">
    <input type="submit" name="deleteButton" value="查看">
</form>

<body>

<%!
    List<HotWord> list = new ArrayList<>();
    int size = 0;

%>

<%

    list = (List<HotWord>) request.getAttribute("list");
    if(list != null) {
        size = list.size();
    }
    String[] year = new String[size];
    String[] word = new String[size];
    for (int i = 0; i < size; i++) {
        year[i] = list.get(i).getYear();
        word[i] = list.get(i).getWord();
    }
    for (int i = 0; i < size; i++) {
        System.out.println(word[i] + " " + year[i]);
    }
%>

<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 1280px;height:600px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));


    // 指定图表的配置项和数据
    var yearlist = <%=year%>;
    var wordList = <%=word%>;
    var option = {
        timeline:{
            axisType: 'category',
            autoPlay: true,
            playInterval: 1500,
            data: yearlist,
            label:{
                fontSize: 18
            }
        },

        baseOption:{
            dataset:{
                source:[
                    ['year','Training','Cameras','3D-displays','Feature_extraction', 'Visualization'],
                    ['2011',105,90,88,77,74],
                    ['2012',54,104,0,62,55],
                    ['2013',105,102,86,76,59],
                    ['2014',122,94,85,69,76],
                    ['2015',117,84,60,90,71],
                    ['2016',126,91,76,139,75],
                    ['2017',180,111,83,128,78],
                    ['2018',184,102,88,126,63],
                    ['2019',88,30,38,60,34],
                    ['2020',192,84,125,108,0]
                ]
            },

            title: {
                text: 'CVPR热词热度走势图',
                left: 'center',
                textStyle:{
                    fontSize:15
                }
            },
            tooltip: {
                trigger: 'axis'
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            legend: {
            },
            grid: {
                left: '10%',
                bottom: '15%',
                containLabel: true
            },
            xAxis: [
                {
                    type:'category',
                    data: wordList,
                    axisPointer: {
                        type: 'shadow'
                    },
                },
            ],
            yAxis:[
                {
                    name:'热度',
                    type:'value',
                    nameTextStyle:{
                        fontSize:18
                    },
                    axisLabel:{
                        fontSize:18
                    }
                },
            ],
            series: [
                {
                    //name: 'GDP',
                    type: 'bar',
                    seriesLayoutBy: 'row',
                    encode:{
                        x:'year',
                        y:'2011'
                    }
                },
            ]
        },
        options:[]
    };

    for (var n = 0; n<yearlist.length; n++){
        option.options.push({
            title:{
                show:true,
                left: 'center',
                textStyle:{
                    fontSize:24
                }
            },
            series:[
                {
                    type: 'bar',
                    seriesLayoutBy: 'row',
                    encode:{
                        x:'year',
                        y:yearlist[n]
                    }
                },
            ]
        });
    }

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>

</body>
</html>