// let url = 'http://localhost:8080/crawler_war_exploded/KeywordAndCount';
// let url="http://39.102.39.208/crawler_war/KeywordAndCount";
let url = turl+'KeywordAndCount';
var JosnList = [

    {name: "龙头镇", value: "111"},
    {name: "大埔镇", value: "222"},
    {name: "太平镇", value: "458"},
    {name: "沙埔镇", value: "445"},
    {name: "东泉镇", value: "456"},
    {name: "凤山镇", value: "647"},
    {name: "六塘镇", value: "189"},
    {name: "冲脉镇", value: "864"},
    {name: "寨隆镇", value: "652"},
    {name: "寨镇", value: "564"},
];
var optionFour = {
    tooltip: {
        show: true
    },
    series: [{
        name: '关键词图谱',
        type: 'wordCloud',
        sizeRange: [10, 50],//文字范围
        //文本旋转范围，文本将通过rotationStep45在[-90,90]范围内随机旋转
        rotationRange: [-45, 90],
        rotationStep: 45,
        textRotation: [0, 45, 90, -45],
        //形状
        shape: 'circle',
        textStyle: {
            fontWight:"bold",

            color: function() {//文字颜色的随机色
                return 'rgb(' + [
                    Math.round(Math.random() * 160),
                    Math.round(Math.random() * 160),
                    Math.round(Math.random() * 160)
                ].join(',') + ')';

            },
            //悬停上去的字体的阴影设置
            emphasis: {
                shadowBlur: 10,
                shadowColor: '#333'
            }
        },
        data: JosnList,


    }],

};
//使用制定的配置项和数据显示图表

var vm = new Vue({
    el: ".container",
    data: {
        list: [],
    },
    methods: {
        fun() {
            let data = JSON.stringify({
                meeting: this.meeting,
            });
            var that = this;
            axios.post(url).then(function (response) {
                that.list = response.data;
                //alert(that.list);
                for(var i = 0;i < that.list.length;i++){
                    //alert(that.list[i].keyword);
                    JosnList[i].name=that.list[i].keyword;
                    JosnList[i].value=that.list[i].appeartimes;
                }
                var myChartFour = echarts.init(document.getElementById('main'));
                myChartFour.setOption(optionFour);
                myChartFour.on('click',function(params){

                    that.send(params.data.name);
                });
            }).catch(function (error) {
                console.log(error);
            });
        },

        send(params) {
            window.location.href = "listView.html" + "?" + "params" + "=" + params;
        }
    },
});