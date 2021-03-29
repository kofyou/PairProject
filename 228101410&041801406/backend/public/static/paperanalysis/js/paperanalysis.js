new Vue().$mount('#app')
//以下是CVPR会议数据分析部分
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
        max: 9 
    },
    series: [{
        realtimeSort: true,
        name: '正在查询2018年CVPR热词走势，请等待',
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

    $.ajax(
        {
            url: 'http://localhost/PairProject/228101410&041801406/backend/public/index.php/index/Serchfunction/get2019CVPRkey',
            type: 'get',
            data: {},
            dataType: 'json'
        }).then(function (res) 
        {
            var dataname=option.yAxis.data;
            var k=[];
            var num=[];
            var data = option.series[0].data;
            for (var i in res) 
            {
                k.push(res[i].keyword);
                num.push(res[i].time);
            }
            for(var j=0;j<k.length;j++)
            {
                var flag=0;
                for (var i = 0; i < data.length; ++i) 
                {
                    if(dataname[i]==k[j])
                    {
                        data[i]+=num[j];
                        flag=1;
                        break;
                    }
                }
                if(flag == 0)
                {
                    dataname.push(k[j]);
                    data.push(num[j]);
                }
            }
            myChart.setOption(option);     　　　　　　　　　
        }).fail(function () 
        {
                console.log('失败');
        })
    
    option.series[0].name='累加至2019年CVPR热词走势';
    myChart.setOption(option);
}

function add2020()
{

    $.ajax(
        {
            url: 'http://localhost/PairProject/228101410&041801406/backend/public/index.php/index/Serchfunction/get2020CVPRkey',
            type: 'get',
            data: {},
            dataType: 'json'
        }).then(function (res) 
        {
            var dataname=option.yAxis.data;
            var k=[];
            var num=[];
            var data = option.series[0].data;
            for (var i in res) 
            {
                k.push(res[i].keyword);
                num.push(res[i].time);
            }
            for(var j=0;j<k.length;j++)
            {
                var flag=0;
                for (var i = 0; i < data.length; ++i) 
                {
                    if(dataname[i]==k[j])
                    {
                        data[i]+=num[j];
                        flag=1;
                        break;
                    }
                }
                if(flag == 0)
                {
                    dataname.push(k[j]);
                    data.push(num[j]);
                }
            }
            myChart.setOption(option);     　　　　　　　　　
        }).fail(function () 
        {
                console.log('失败');
        })
    
    option.series[0].name='累加至2020年CVPR热词走势';
    myChart.setOption(option);
}


setTimeout(function() {
    run();
}, 0);

setTimeout(function() {
    add2019();
}, 8000);

setTimeout(function() {
    add2020();
}, 15000);

if (option && typeof option === 'object') {
    myChart.setOption(option);
}