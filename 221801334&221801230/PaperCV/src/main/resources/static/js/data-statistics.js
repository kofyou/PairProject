$(document).ready(function () {
    var httpRoot = "http://localhost:8080";
    // var httpRoot = "http://120.24.27.29:8080";

    var meetingCount;
    $.ajax({
        url:httpRoot + `/data/meeting/count`,
        type:"GET",
        dataType:"json",
        async:false,
        success:function(result){
            meetingCount = result;
        },
    });
    option = {
        legend: {},
        tooltip: {},
        dataset: {
            source: [
                ['meetings', '发布数量'],
                ['CVPR', meetingCount[0]],
                ['ICCV', meetingCount[1]],
                ['ECCV', meetingCount[2]],
            ]
        },
        xAxis: {type: 'category'},
        yAxis: {},

        series: [
            {type: 'bar'},
            // {type: 'bar'},
        ]
    };
    echartBar = echarts.init(document.getElementById("table2"));
    echartBar.setOption(option);

    $.ajax({
        url:httpRoot + `/data/meeting/count`,
        type:"GET",
        dataType:"json",
        async:false,
        success:function(result){
            meetingCount = result;
        },
    });
    option = {
        legend: {
            top: 'bottom'
        },
        toolbox: {
            show: true,
            feature: {
                mark: {show: true},
                dataView: {show: false, readOnly: true},
                restore: {show: false},
                saveAsImage: {show: true}
            }
        },
        series: [
            {
                name: '面积模式',
                type: 'pie',
                radius: [50, 250],
                center: ['50%', '50%'],
                roseType: 'area',
                itemStyle: {
                    borderRadius: 8
                },
                data: [
                    {value: 40, name: '热词1'},
                    {value: 38, name: '热词2'},
                    {value: 32, name: '热词3'},
                    {value: 30, name: '热词4'},
                    {value: 28, name: '热词5'},
                    {value: 26, name: '热词6'},
                    {value: 22, name: '热词7'},
                    {value: 18, name: '热词8'},
                    {value: 18, name: '热词9'},
                    {value: 18, name: '热词10'}
                ]
            }
        ]
    };
    echartBar = echarts.init(document.getElementById("table3"));
    echartBar.setOption(option);
});
