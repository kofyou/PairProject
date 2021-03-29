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
                url: '../index.php/index/Serchfunction/get2018CVPRkey',
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
            url: '../index.php/index/Serchfunction/get2019CVPRkey',
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
            url: '../index.php/index/Serchfunction/get2020CVPRkey',
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


//以下是ECCV会议柱状图js

var dom1 = document.getElementById("container1");
var myChart1 = echarts.init(dom1);
var app = {};

var option1;



var data1 = [];
for (let i = 0; i < 10; ++i) {
    data1.push(0);
}
option1 = {
    xAxis: {
        max: 'dataMax',
    },
    yAxis: {
        type: 'category',
        data: ['deep learning','convolutional neural network','convolutional layer','deep neural network'
                ,'object detection','attributes','cnn','context','convolutional neural networks'
                ,'object recognition'],
        inverse: true,
        animationDuration: 300,
        animationDurationUpdate: 300,
        max: 9 
    },
    series: [{
        realtimeSort: true,
        name: '正在查询2016年ECCV热词走势，请等待',
        type: 'bar',
        data: data1,
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

function run_ECCV() {
    $.ajax(
            {
                url: 'http://localhost/PairProject/228101410&041801406/backend/public/index.php/index/Serchfunction/get2016ECCVkey',
                type: 'get',
                data: {},
                dataType: 'json'
            }).then(function (res) 
            {
                var data1 = option1.series[0].data;
                for (var i in res) 
                {
                    data1[i]+=res[i].time;
                }　　　　　　　　　　
            }).fail(function () 
            {
                    console.log('失败');
            })
        
    
    myChart1.setOption(option1);
}


function add2018_ECCV()
{

    $.ajax(
        {
            url: 'http://localhost/PairProject/228101410&041801406/backend/public/index.php/index/Serchfunction/get2018ECCVkey',
            type: 'get',
            data: {},
            dataType: 'json'
        }).then(function (res) 
        {
            var dataname=option1.yAxis.data;
            var k=[];
            var num=[];
            var data = option1.series[0].data;
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
            myChart1.setOption(option1);     　　　　　　　　　
        }).fail(function () 
        {
                console.log('失败');
        })
    
    option1.series[0].name='累加至2018年ECCV热词走势';
    myChart1.setOption(option1);
}

function add2020_ECCV()
{

    $.ajax(
        {
            url: 'http://localhost/PairProject/228101410&041801406/backend/public/index.php/index/Serchfunction/get2020ECCVkey',
            type: 'get',
            data: {},
            dataType: 'json'
        }).then(function (res) 
        {
            var dataname=option1.yAxis.data;
            var k=[];
            var num=[];
            var data = option1.series[0].data;
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
            myChart1.setOption(option1);     　　　　　　　　　
        }).fail(function () 
        {
                console.log('失败');
        })
    
    option1.series[0].name='累加至2020年ECCV热词走势';
    myChart1.setOption(option1);
}


setTimeout(function() {
    run_ECCV();
}, 0);

setTimeout(function() {
    add2018_ECCV();
}, 8000);

setTimeout(function() {
    add2020_ECCV();
}, 15000);

if (option1 && typeof option1 === 'object') {
    myChart1.setOption(option1);
}


//以下是ICCV会议柱状图js


var dom2 = document.getElementById("container2");
var myChart2 = echarts.init(dom2);
var app = {};

var option2;



var data2 = [];
for (let i = 0; i < 10; ++i) {
    data2.push(0);
}
option2 = {
    xAxis: {
        max: 'dataMax',
    },
    yAxis: {
        type: 'category',
        data: ['computer vision','feature extraction','image reconstruction','training'
                ,'image segmentation','learning (artificial intelligence)'
                ,'cameras','object detection','visualization','three-dimensional displays'],
        inverse: true,
        animationDuration: 300,
        animationDurationUpdate: 300,
        max: 9 
    },
    series: [{
        realtimeSort: true,
        name: '正在查询2015年ICCV热词走势，请等待',
        type: 'bar',
        data: data2,
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

function run_ICCV() {
    $.ajax(
            {
                url: 'http://localhost/PairProject/228101410&041801406/backend/public/index.php/index/Serchfunction/get2015ICCVkey',
                type: 'get',
                data: {},
                dataType: 'json'
            }).then(function (res) 
            {
                var data1 = option2.series[0].data;
                for (var i in res) 
                {
                    data1[i]+=res[i].time;
                }　　　　　　　　　　
            }).fail(function () 
            {
                    console.log('失败');
            })
        
    
    myChart2.setOption(option2);
}


function add2017_ICCV()
{

    $.ajax(
        {
            url: 'http://localhost/PairProject/228101410&041801406/backend/public/index.php/index/Serchfunction/get2017ICCVkey',
            type: 'get',
            data: {},
            dataType: 'json'
        }).then(function (res) 
        {
            var dataname=option2.yAxis.data;
            var k=[];
            var num=[];
            var data = option2.series[0].data;
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
            myChart2.setOption(option2);     　　　　　　　　　
        }).fail(function () 
        {
                console.log('失败');
        })
    
    option2.series[0].name='累加至2017年ICCV热词走势';
    myChart2.setOption(option2);
}

function add2019_ICCV()
{

    $.ajax(
        {
            url: 'http://localhost/PairProject/228101410&041801406/backend/public/index.php/index/Serchfunction/get2019ICCVkey',
            type: 'get',
            data: {},
            dataType: 'json'
        }).then(function (res) 
        {
            var dataname=option2.yAxis.data;
            var k=[];
            var num=[];
            var data = option2.series[0].data;
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
            myChart2.setOption(option2);     　　　　　　　　　
        }).fail(function () 
        {
                console.log('失败');
        })
    
    option2.series[0].name='累加至2019年ICCV热词走势';
    myChart2.setOption(option2);
}


setTimeout(function() {
    run_ICCV();
}, 0);

setTimeout(function() {
    add2017_ICCV();
}, 8000);

setTimeout(function() {
    add2019_ICCV();
}, 15000);

if (option2 && typeof option2 === 'object') {
    myChart2.setOption(option2);
}