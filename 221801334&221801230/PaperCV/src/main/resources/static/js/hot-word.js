$(document).ready(function () {
    var httpRoot = "http://localhost:8080";
    // var httpRoot = "http://120.24.27.29:8080";

    var cvprValues;
    var cvprYears;
    var cvprCounts;
    $.ajax({
        url:httpRoot + `/data/trend/cvpr`,
        type:"GET",
        dataType:"json",
        async:false,
        success:function(result){
            cvprValues = [result[0].value, result[1].value, result[2].value];
            cvprYears = result[0].years;
            cvprCounts = [result[0].counts, result[1].counts, result[2].counts];
        },
    });

    var iccvValues;
    var iccvYears;
    var iccvCounts;
    $.ajax({
        url:httpRoot + `/data/trend/iccv`,
        type:"GET",
        dataType:"json",
        async:false,
        success:function(result){
            iccvValues = [result[0].value, result[1].value, result[2].value];
            iccvYears = result[0].years;
            iccvCounts = [result[0].counts, result[1].counts, result[2].counts];
        },
    });

    var eccvValues;
    var eccvYears;
    var eccvCounts;
    $.ajax({
        url:httpRoot + `/data/trend/eccv`,
        type:"GET",
        dataType:"json",
        async:false,
        success:function(result){
            eccvValues = [result[0].value, result[1].value, result[2].value];
            eccvYears = result[0].years;
            eccvCounts = [result[0].counts, result[1].counts, result[2].counts];
        },
    });


    // CVPR
    option = {
        title: {
            text: ''
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: cvprValues
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: cvprYears
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name: cvprValues[0],
                type: 'line',
                stack: 'num1',
                data: cvprCounts[0]
            },
            {
                name: cvprValues[1],
                type: 'line',
                stack: 'num2',
                data: cvprCounts[1]
            },
            {
                name: cvprValues[2],
                type: 'line',
                stack: 'num3',
                data: cvprCounts[2]
            },
        ]
    };
    echartBar = echarts.init(document.getElementById("pills-home"));
    echartBar.setOption(option);

    // ICCV
    option = {
        title: {
            text: ''
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: iccvValues
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: iccvYears
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name: iccvValues[0],
                type: 'line',
                stack: 'num1',
                data: iccvCounts[0]
            },
            {
                name: iccvValues[1],
                type: 'line',
                stack: 'num2',
                data: iccvCounts[1]
            },
            {
                name: iccvValues[2],
                type: 'line',
                stack: 'num3',
                data: iccvCounts[2]
            },
        ]
    };
    echartBar = echarts.init(document.getElementById("pills-profile"));
    echartBar.setOption(option);

    // ECCV
    option = {
        title: {
            text: ''
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: eccvValues
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: eccvYears
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name: eccvValues[0],
                type: 'line',
                stack: 'num1',
                data: eccvCounts[0]
            },
            {
                name: eccvValues[1],
                type: 'line',
                stack: 'num2',
                data: eccvCounts[1]
            },
            {
                name: eccvValues[2],
                type: 'line',
                stack: 'num3',
                data: eccvCounts[2]
            }
        ]
    };
    echartBar = echarts.init(document.getElementById("pills-contact"));
    echartBar.setOption(option);




});
