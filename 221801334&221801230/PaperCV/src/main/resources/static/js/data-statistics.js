$(document).ready(function () {
    // 数量统计
    $.ajax({
        url:httpRoot + `/data/meeting/count`,
        type:"GET",
        dataType:"json",
        async:true,
        success:function(result){
            var meetingCount = result;
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
        },
    });

    var res;
    // 热词
    $.ajax({
        url:httpRoot + `/data/hotWord`,
        type:"GET",
        dataType:"json",
        async:false,
        success:function(result){
            res = result;
            var hotWord = [];
            for (var i = 0;i < 10;i++) {
                for (var item in result[i]) {
                    hotWord.push([item, result[i][item]]);
                }
            }
            addButton(hotWord);
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
                            {value: hotWord[0][1], name: hotWord[0][0]},
                            {value: hotWord[1][1], name: hotWord[1][0]},
                            {value: hotWord[2][1], name: hotWord[2][0]},
                            {value: hotWord[3][1], name: hotWord[3][0]},
                            {value: hotWord[4][1], name: hotWord[4][0]},
                            {value: hotWord[5][1], name: hotWord[5][0]},
                            {value: hotWord[6][1], name: hotWord[6][0]},
                            {value: hotWord[7][1], name: hotWord[7][0]},
                            {value: hotWord[8][1], name: hotWord[8][0]},
                            {value: hotWord[9][1], name: hotWord[9][0]}
                        ]
                    }
                ]
            };
            echartBar = echarts.init(document.getElementById("table3"));
            echartBar.setOption(option);
        },
    });

    // 图谱
    JosnList = [];
    for (var i = 0;i < 500;i++) {
        for (var item in res[i]) {
            JosnList.push({name: item, value: res[i][item]});
        }
    }
    optionFour = {
        tooltip: {
            show: true
        },
        series: [{
            name: '项目分析',
            type: 'wordCloud',
            sizeRange: [10, 50],//文字范围
            //文本旋转范围，文本将通过rotationStep45在[-90,90]范围内随机旋转
            rotationRange: [-45, 90],
            rotationStep: 45,
            textRotation: [0, 45, 90, -45],
            //形状
            shape: 'circle',
            textStyle: {
                normal: {
                    color: function() {//文字颜色的随机色
                        return 'rgb(' + [
                            Math.round(Math.random() * 250),
                            Math.round(Math.random() * 250),
                            Math.round(Math.random() * 250)
                        ].join(',') + ')';
                    }
                },
                //悬停上去的字体的阴影设置
                emphasis: {
                    shadowBlur: 10,
                    shadowColor: '#333'
                }
            },
            data: JosnList
        }]
    };
    myChartFour = echarts.init(document.getElementById('table4'));
    //使用制定的配置项和数据显示图表
    myChartFour.setOption(optionFour);

    // 添加热词按钮
    function addButton(hotWord) {
        $("#hot-list-one").empty();
        $("#hot-list-two").empty();
        $("#hot-list-one").append(
            "<button name=\"" + hotWord[0][0] + "\" type=\"button\" class=\"btn btn-outline-danger mb-2" +
            " hotWordButton\">" + hotWord[0][0] + "</button>\n" +
            "<button name=\"" + hotWord[1][0] + "\" type=\"button\" class=\"btn btn-outline-dark mb-2" +
            " hotWordButton\">" + hotWord[1][0] + "</button>\n" +
            "<button name=\"" + hotWord[2][0] + "\" type=\"button\" class=\"btn btn-outline-secondary mb-2" +
            " hotWordButton\">" + hotWord[2][0] + "</button>\n" +
            "<button name=\"" + hotWord[3][0] + "\" type=\"button\" class=\"btn btn-outline-primary mb-2" +
            " hotWordButton\">" + hotWord[3][0] + "</button>\n" +
            "<button name=\"" + hotWord[4][0] + "\" type=\"button\" class=\"btn btn-outline-warning mb-2" +
            " hotWordButton\">" + hotWord[4][0] + "</button>");

        $("#hot-list-two").append(
            "<button name=\"" + hotWord[5][0] + "\" type=\"button\" class=\"btn btn-outline-warning" +
            " mb-2 hotWordButton\">" + hotWord[5][0] + "</button>\n" +
            "<button name=\"" + hotWord[6][0] + "\" type=\"button\" class=\"btn btn-outline-info" +
            " mb-2 hotWordButton\">" + hotWord[6][0] + "</button>\n" +
            "<button name=\"" + hotWord[7][0] + "\" type=\"button\" class=\"btn btn-outline-success" +
            " mb-2 hotWordButton\">" + hotWord[7][0] + "</button>\n" +
            "<button name=\"" + hotWord[8][0] + "\" type=\"button\" class=\"btn btn-outline-secondary" +
            " mb-2 hotWordButton\">" + hotWord[8][0] + "</button>\n" +
            "<button name=\"" + hotWord[9][0] + "\" type=\"button\" class=\"btn btn-outline-primary" +
            " mb-2 hotWordButton\">" + hotWord[9][0] + "</button>");

        $(".hotWordButton").click(function () {
            window.localStorage.removeItem("keyWord");
            window.localStorage.setItem("keyWord", $(this).attr("name"));
            window.location.href = "paper-list.html";
        });
    }
});
