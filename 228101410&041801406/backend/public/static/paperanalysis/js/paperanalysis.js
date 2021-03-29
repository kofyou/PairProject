new Vue().$mount('#app')

var dom = document.getElementById("container");
var myChart = echarts.init(dom);
var app = {};

var option;



var data = [];
for (let i = 0; i < 10; ++i) {
    data.push(0);
}
option = {
    xAxis: {
        max: 'dataMax',
    },
    yAxis: {
        type: 'category',
        data: ['feature extraction', 'learning (artificial intelligence)', 'image segmentation', 'computer vision'
        , 'training','task analysis','cameras','object detection','image reconstruction','convolution'],
        inverse: true,
        animationDuration: 300,
        animationDurationUpdate: 300,
        max: 9 // only the largest 3 bars will be displayed
    },
    series: [{
        realtimeSort: true,
        name: '2018年CVPR热词走势',
        type: 'bar',
        data: data,
        label: {
            show: true,
            position: 'right',
            valueAnimation: true
        }
    }],
    legend: {
        show: true
    },
    animationDuration: 0,
    animationDurationUpdate: 3000,
    animationEasing: 'linear',
    animationEasingUpdate: 'linear'
};
var k=[];
k.push("feature extraction");
var num=[500,500,500];
function run () {
    $.ajax(
            {
                url: 'http://localhost/PairProject/228101410&041801406/backend/public/index.php/index/Serchfunction/get2018CVPRkey',
                type: 'get',
                data: {},
                dataType: 'json'
            }).then(function (res) 
            {
                var data = option.series[0].data;
                for (var i in res) 
                {
                    data[i]+=res[i].time;
                }　　　　　　　　　　
            }).fail(function () 
            {
                    console.log('失败');
            })
        
    
    myChart.setOption(option);
}
function add2019()
{
    var dataname=option.yAxis.data;
    var data = option.series[0].data;
    option.series[0].name='2019年CVPR热词走势';
    myChart.setOption(option);
    for(var j=0;j<k.length;j++)
    {
        for (var i = 0; i < data.length; ++i) {
            console.log(dataname[i]);
            if(dataname[i]==k[j])
            {
                data[i]+=num[j];
            }
    }
    }
    myChart.setOption(option);
}


setTimeout(function() {
    run();
}, 0);

setTimeout(function() {
    add2019();
}, 3000);
/*
setInterval(function () {
    run();
}, 3000);*/

if (option && typeof option === 'object') {
    myChart.setOption(option);
}