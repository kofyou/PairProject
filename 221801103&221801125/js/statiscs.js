$(function(){
        console.log(HOTTEST_TOP10.length)
        if($.isEmptyObject(HOTTEST_THREE)&&HOTTEST_TOP10.length==0)
        {
            //发送ajax请求
            $.ajax({
                url:"",
                dataType:"json",
                type:"post",
                success:data=>{
                    var obj = {
                        "cvpr" : data.cvpr, //cvpr的前5月排行int数组
                        "iccv" : data.iccv,
                        "eccv" : data.eccv,
                    }
                    HOTTEST_THREE = obj
                    for(let i=0;i<data.hottest.length;i++)
                    {
                        let obj2 = {
                            "name" : data.hottest[i],
                            "value" : data.hottest_value[i] 
                        }
                        HOTTEST_TOP10.push(obj2)
                    }
                    
                }
            })
        }
        var height = window.innerHeight - 61;
        var dom = document.getElementsByClassName("container")[0];
        var dom2 = document.getElementsByClassName("wrap")[0];
        document.getElementsByClassName("container")[0].style.height = height + "px"
        document.getElementsByClassName("wrap")[0].style.height = height + "px"
        document.getElementsByClassName("wrap")[0].style.top = height+100 + "px"
        var myChart = echarts.init(dom);
        var myChart2 = echarts.init(dom2);
        var months = ["1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"]
        var myDate = new Date;
        var mon = myDate.getMonth() + 1;
        var dataMonth = []
        if(mon<5)
        {
            for(let k=0;k<5;k++)
            {
                if(k<(5-mon))
                    dataMonth.push(months[(12-5+mon+k)])
                else
                    dataMonth.push(months[mon-5+k])
            }
        }else{
            for(let l=0;l<5;l++)
            {
                dataMonth.push(months[mon-5+l])
            }
        }
        
        var app = {};
        var app2 = {};
        var option,option2;
        option = {
            title: {
                text: '论文数量对比'
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: ['cvpr', 'iccv', 'eccv']
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            // toolbox: {
            //     feature: {
            //         saveAsImage: {}
            //     }
            // },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: dataMonth
            },
            yAxis: {
                type: 'value'
            },
            series: [
                {
                    name: 'cvpr',
                    type: 'line',
                    stack: '总量',
                    data: [120, 132, 101, 134, 90, 230, 210]
                },
                {
                    name: 'iccv',
                    type: 'line',
                    stack: '总量',
                    data: [220, 182, 191, 234, 290, 330, 310]
                },
                {
                    name: 'eccv',
                    type: 'line',
                    stack: '总量',
                    data: [150, 232, 201, 154, 190, 330, 410]
                }
            ]
        };

        option2 = {
        title: {
            text: '排名前10数据'
        },
        xAxis: {
            type: 'category',
            data: ['第一项', '第二项', '第三项', '第四项', '第五项', '第六项', '第七项', '第八项', '第九项','第十项']
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: [120, 200, 150, 80, 70, 110, 130,20,80,100],
            type: 'bar',
            showBackground: true,
            backgroundStyle: {
                color: 'rgba(180, 180, 180, 0.2)'
            }
        }]
        };
        if (option && typeof option === 'object') {
            myChart.setOption(option);
        }
        if (option2 && typeof option === 'object') {
            myChart2.setOption(option2);
        }
})