import { withRouter } from 'react-router-dom';
import * as echarts from 'echarts';
import React, { Component } from "react";



class StatisticPage extends Component {
    componentDidMount() {
        // const _this = this
        // let urlAdd = _this.state.pageNum == 0 ? '' : '&page=' + _this.state.pageNum
        // let url = 'http://localhost:3000/api/v1/user/list' + urlAdd
        // axios.get(url)
        //     .then(function (response) {
        //         if (response.data.code == 0) {
        //             paperDataJson.paperData = response.data.data.papers
        //             paperDataJson.paperCount = response.data.data.page_count
        //             paperDataJson.pageAt = response.data.data.page
        //             // window.location.reload()
        //         }
        //         _this.setState({
        //             isLoad: true
        //         })
        //     }).catch(function (error) {
        //         console.log(error);
        //     }).finally(function () {
        //     });
        // 基于准备好的dom，初始化echarts实例
        this.myChart = echarts.init(this.refs.main);
        // 绘制图表
        this.myChart.setOption({
            title: {
                text: '折线图堆叠'
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: ['邮件营销', '联盟广告', '视频广告', '直接访问', '搜索引擎']
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
                data: ['2014', '2015', '2016', '2017', '2018', '2019', '2020']
            },
            yAxis: {
                type: 'value'
            },
            series: [
                {
                    name: '邮件营销',
                    type: 'line',
                    stack: '总量',
                    data: [120, 132, 101, 134, 90, 230, 210]
                },
                {
                    name: '联盟广告',
                    type: 'line',
                    stack: '总量',
                    data: [220, 182, 191, 234, 290, 330, 310]
                },
                {
                    name: '视频广告',
                    type: 'line',
                    stack: '总量',
                    data: [150, 232, 201, 154, 190, 330, 410]
                },
                {
                    name: '直接访问',
                    type: 'line',
                    stack: '总量',
                    data: [320, 332, 301, 334, 390, 330, 320]
                },
                {
                    name: '搜索引擎',
                    type: 'line',
                    stack: '总量',
                    data: [820, 932, 901, 934, 1290, 1330, 1320]
                }
            ]
        });
    }
    render() {
        return (
            <div>
                {/*作为初始化的DOM，必须给宽高*/}
                <div ref="main" style={{ width: "500px", height: "300px" }} />
            </div>
        );
    }
}


export default withRouter(StatisticPage);
