<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>关键词</title>
    <link rel="stylesheet" href="data.css" type="text/css" />
    <script type="text/javascript" src='echarts.min.js'></script>
</head>
<body>
    <h1 id="title">关键词</h1>
     <a href="keyword.php?keyword=computer vision"><button id="a"> vision</button></a>
     <a href="keyword.php?keyword=feature extraction"><button id="a">feature extraction</button></a>
     <a href="keyword.php?keyword=image segmentation"><button id="a">image segmentation</button></a>
     <a href="keyword.php?keyword=learning (artificial intelligence)"><button id="a">learning</button></a>
     <a href="keyword.php?keyword=cameras"><button id="a">cameras</button></a>
     <a href="keyword.php?keyword=object detection"><button id="a">object detection</button></a>
     <a href="keyword.php?keyword=image reconstruction"><button id="a">image reconstruction</button></a>
     <a href="keyword.php?keyword=training"><button id="a">training</button></a>
     <a href="keyword.php?keyword=image classification"><button id="a">image classification</button></a>
     <a href="keyword.php?keyword=face recognition"><button id="a">face recognition</button></a>
 <div id="chart1" style="width:1000px;height:400px;"></div>
   <script type="text/javascript">
    // 初始化图表标签
    var myChart = echarts.init(document.getElementById('chart1'));
    var options={
        //定义一个标题
        title:{
            text:'CVPR2020'
        },
        legend:{
            data:['CVPR2020']
        },
        //X轴设置
        xAxis:{
            data:['learning(artifical intelligence)','feature extraction','training','image reconstruction','neural nets','task analysis','computer vision','cameras','object detection','convolutional neural nets']
        },
        yAxis:{
        },
        //name=legend.data的时候才能显示图例
        series:[{
            name:'CVPR2020',
            type:'bar',
            data:['284','192','152','143','140','127','113','111','88','86']
        }]

    };
    myChart.setOption(options);
</script>
<div id="chart2" style="width:1000px;height:400px;"></div>
<script type="text/javascript">
var myChart = echarts.init(document.getElementById('chart2'));
    var options={
        //定义一个标题
        title:{
            text:'CVPR2019'
        },
        legend:{
            data:['CVPR2019']
        },
        //X轴设置
        xAxis:{
            data:['learning(artifical intelligence)','feature extraction','object detection','image segmentation','image classification','neural nets','deep learning','categorization','computer vision','training']
        },
        yAxis:{
        },
        //name=legend.data的时候才能显示图例
        series:[{
            name:'CVPR2019',
            type:'bar',
            data:['184','96','84','83','70','61','59','57','53','52']
        }]

    };
    myChart.setOption(options);
    </script>
    <div id="chart3" style="width:1000px;height:400px;"></div>
   <script type="text/javascript">
    // 初始化图表标签
    var myChart = echarts.init(document.getElementById('chart3'));
    var options={
        //定义一个标题
        title:{
            text:'CVPR2018'
        },
        legend:{
            data:['CVPR2018']
        },
        //X轴设置
        xAxis:{
            data:['feature extraction','learning(artifical intelligence)','image segmentation','computer vision','training','task analysis','cameras','object detection','image reconstruction','convolution']
        },
        yAxis:{
        },
        //name=legend.data的时候才能显示图例
        series:[{
            name:'CVPR2018',
            type:'bar',
            data:['158','145','99','94','94','89','88','82','69','59']
        }]

    };
    myChart.setOption(options);
</script>
<div id="chart4" style="width:1000px;height:400px;"></div>
   <script type="text/javascript">
    // 初始化图表标签
    var myChart = echarts.init(document.getElementById('chart4'));
    var options={
        //定义一个标题
        title:{
            text:'ECCV2020'
        },
        legend:{
            data:['ECCV2020']
        },
        //X轴设置
        xAxis:{
            data:['deep learning','object detection','domain adaptation','semantic segmentation','instance segmentation','self-supervised learning','attention','convolutional neural networks','semi-supervised learning','transfer learning']
        },
        yAxis:{
        },
        //name=legend.data的时候才能显示图例
        series:[{
            name:'ECCV2020',
            type:'bar',
            data:['21','21','14','12','11','11','10','10','10','10']
        }]

    };
    myChart.setOption(options);
</script>
<div id="chart5" style="width:1000px;height:400px;"></div>
   <script type="text/javascript">
    // 初始化图表标签
    var myChart = echarts.init(document.getElementById('chart5'));
    var options={
        //定义一个标题
        title:{
            text:'ECCV2018'
        },
        legend:{
            data:['ECCV2018']
        },
        //X轴设置
        xAxis:{
            data:['deep learning','semantic segmentation','convolutional neural networks','convolutional neural network','object detection','damain adaptation','unsupervised learning','attention','computer vision','neural networks']
        },
        yAxis:{
        },
        //name=legend.data的时候才能显示图例
        series:[{
            name:'ECCV2018',
            type:'bar',
            data:['39','17','13','12','12','11','10','8','8','8']
        }]

    };
    myChart.setOption(options);
</script>
<div id="chart6" style="width:1000px;height:400px;"></div>
   <script type="text/javascript">
    // 初始化图表标签
    var myChart = echarts.init(document.getElementById('chart6'));
    var options={
        //定义一个标题
        title:{
            text:'ECCV2016'
        },
        legend:{
            data:['ECCV2016']
        },
        //X轴设置
        xAxis:{
            data:['deep learning','convolutional neural network','convolutional layer','deep neural network ','object detection','attributes','cnn','context','convolutional neural networks','object recognition']
        },
        yAxis:{
        },
        //name=legend.data的时候才能显示图例
        series:[{
            name:'ECCV2016',
            type:'bar',
            data:['15','12','5','4','4','3','3','3','3','3']
        }]

    };
    myChart.setOption(options);
</script>
<div id="chart7" style="width:1000px;height:400px;"></div>
   <script type="text/javascript">
    // 初始化图表标签 
    var myChart = echarts.init(document.getElementById('chart7'));
    var options={
        //定义一个标题
        title:{
            text:'ICCV2019'
        },
        legend:{
            data:['ICCV2019']
        },
        //X轴设置
        xAxis:{
            data:['learning(artifical intelligence)','feature extraction','training','task analysis','object detection','computer vision','image segmentation','neural nets','image classification','convolutional neural nets']
        },
        yAxis:{
        },
        //name=legend.data的时候才能显示图例
        series:[{
            name:'ICCV2019',
            type:'bar',
            data:['263','261','201','168','162','158','142','119','111','91']
        }]

    };
    myChart.setOption(options);
</script>
<div id="chart8" style="width:1000px;height:400px;"></div>
   <script type="text/javascript">
    // 初始化图表标签
    var myChart = echarts.init(document.getElementById('chart8'));
    var options={
        //定义一个标题
        title:{
            text:'ICCV2017'
        },
        legend:{
            data:['ICCV2017']
        },
        //X轴设置
        xAxis:{
            data:['learning(artifical intelligence)','feature extraction','computer vision','training','object detection','neural nets','cameras','image segmentation','image reconstruction','image classification']
        },
        yAxis:{
        },
        //name=legend.data的时候才能显示图例
        series:[{
            name:'ICCV2017',
            type:'bar',
            data:['238','208','203','146','127','111','110','110','100','92']
        }]

    };
    myChart.setOption(options);
</script>
<div id="chart9" style="width:1000px;height:400px;"></div>
   <script type="text/javascript">
    // 初始化图表标签
    var myChart = echarts.init(document.getElementById('chart9'));
    var options={
        //定义一个标题
        title:{
            text:'ICCV2015'
        },
        legend:{
            data:['ICCV2015']
        },
        //X轴设置
        xAxis:{
            data:['computer vision','feature extraction','image reconstruction','training','image segmentation','learning(artifical intelligence)','cameras','object detection','visualization','three-dimensional displays']
        },
        yAxis:{
        },
        //name=legend.data的时候才能显示图例
        series:[{
            name:'ICCV2015',
            type:'bar',
            data:['175','147','98','97','95','95','86','76','67','64']
        }]

    };
    myChart.setOption(options);
</script>
</body>
</html>