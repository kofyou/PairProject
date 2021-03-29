<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>echarts.js案例一</title>
    <script type="text/javascript" src='echarts.min.js'></script>
</head>
<body>
    <h1>关键词</h1>
     <a href="keyword.php?keyword=computer vision">computer vision</a>
     <a href="keyword.php?keyword=feature extraction">feature extraction</a>
     <a href="keyword.php?keyword=image segmentation">image segmentation</a>
     <a href="keyword.php?keyword=learning (artificial intelligence)">learning (artificial intelligence)</a>
     <a href="keyword.php?keyword=cameras">cameras</a>
     <a href="keyword.php?keyword=object detection">object detection</a>
     <a href="keyword.php?keyword=image reconstruction">image reconstruction</a>
     <a href="keyword.php?keyword=training">training</a>
     <a href="keyword.php?keyword=image classification">image classification</a>
     <a href="keyword.php?keyword=face recognition">face recognition</a>
    <div id="chart" style="width:400px;height:400px;"></div>
</body>
<script type="text/javascript">
    // 初始化图表标签
    var myChart = echarts.init(document.getElementById('chart'));
    var options={
        //定义一个标题
        title:{
            text:'测试成绩'
        },
        legend:{
            data:['销量']
        },
        //X轴设置
        xAxis:{
            data:['60分','70分','80分','90分','100分']
        },
        yAxis:{
        },
        //name=legend.data的时候才能显示图例
        series:[{
            name:'销量',
            type:'bar',
            data:['12','32','45','21','1']
        }]

    };
    myChart.setOption(options);
</script>
</html>