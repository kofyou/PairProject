let url="http://39.102.39.208/crawler_war/MeetingWordTrend";
// let url = 'http://localhost:8080/crawler_war_exploded/MeetingWordTrend';

var chartDom = document.getElementById('main');
var myChart = echarts.init(chartDom);
var option;

option = {
    title: {
        text: '热词走势'
    },
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data: []
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
        data: []
    },
    yAxis: {
        type: 'value',
        min:0,

        interval:200
    },

    series: []
};

var vm = new Vue({
    el: "#MySelect",
    data: {
        year: [2015,2016,2017],
        keyCounts:[],
        meeting: "CVPR",
        list: {
            keywordCount: [{keyword:"word1","yearAndCount":[{"count":150,"year":2015},{"count":170,"year":2016},{"count":190,"year":2017}]},
                {keyword:"word2","yearAndCount":[{"count":120,"year":2015},{"count":180,"year":2016},{"count":390,"year":2017}]},
                {keyword:"word3","yearAndCount":[{"count":650,"year":2015},{"count":130,"year":2016},{"count":290,"year":2017}]}],
        //[{"keyword":"Training","yearAndCount":[{"count":192,"year":"2020"},{"count":0,"year":"2019"},{"count":184,"year":"2018"}]},{"keyword":"Task analysis","yearAndCount":[{"count":153,"year":"2020"},{"count":77,"year":"2019"},{"count":153,"year":"2018"}]},{"keyword":"Three-dimensional displays","yearAndCount":[{"count":125,"year":"2020"},{"count":0,"year":"2019"},{"count":88,"year":"2018"}]}]

        },
        option:"1",
    },
    methods: {
        fun() {
            if(this.option==1){
                this.meeting="CVPR";
                //alert("cvpr");
            }else if(this.option==2){
                this.meeting="ICCV";
                //alert("iccv");
            }else if(this.option==3){
                this.meeting="ECCV";
                //alert("eccv");
            }
            let data = JSON.stringify({
                meeting: this.meeting,
            });
            var that = this;
            axios.post(url, data).then(function (response) {
                that.list.keywordCount = response.data;
                for(var i=0;i<that.list.keywordCount[0].yearAndCount.length;i++){
                    var tpArray=[];
                    that.year[i]=that.list.keywordCount[0].yearAndCount[i].year;
                    for(var j=0;j<that.list.keywordCount.length;j++){
                        tpArray[j]=that.list.keywordCount[i].yearAndCount[j].count;
                    }
                    that.keyCounts[i]=(tpArray);

                    option.legend.data=[that.list.keywordCount[0].keyword.toString(),
                        that.list.keywordCount[1].keyword.toString(),
                        that.list.keywordCount[2].keyword.toString()];
                    option.xAxis.data = that.year;

                    option.series=[
                        {
                            name: that.list.keywordCount[0].keyword,
                            type: 'line',
                            data: that.keyCounts[0]
                        },
                        {
                            name: that.list.keywordCount[1].keyword,
                            type: 'line',
                            data: that.keyCounts[1]
                        },
                        {
                            name: that.list.keywordCount[2].keyword,
                            type: 'line',
                            data: that.keyCounts[2]
                        },
                    ];
                    option && myChart.setOption(option);
                }
            }).catch(function (error) {
                console.log(error);
            });
        }
    },
});