import { withRouter } from 'react-router-dom';
import React, { Component } from "react";
import ReactWordcloud from "react-wordcloud";
import ReactECharts from 'echarts-for-react';
const axios = require('axios');

var paperDataJson = {}
const words = [
];
var hot = new Array(10);
var a = new Array(7);
var b = new Array(7);
var k = new Array(7);
var d = new Array(7);
var e = new Array(7);
var f = new Array(7);
var g = new Array(7);
var h = new Array(7);
var i = new Array(7);
var j = new Array(7);

class StatisticPage extends Component {
    constructor(props) {
        super(props);
        this.state = { isLoad: false };
    }
    getOption(){return({
        title: {
            text: '热词趋势'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: hot
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
                name: hot[0],
                type: 'line',
                stack: '总量',
                data: a
            },
            {
                name: hot[1],
                type: 'line',
                stack: '总量',
                data: b
            },
            {
                name: hot[2],
                type: 'line',
                stack: '总量',
                data: k
            },
            {
                name: hot[3],
                type: 'line',
                stack: '总量',
                data: d
            },
            {
                name: hot[4],
                type: 'line',
                stack: '总量',
                data: e
            },
            {
                name: hot[5],
                type: 'line',
                stack: '总量',
                data: f
            },
            {
                name: hot[6],
                type: 'line',
                stack: '总量',
                data: g
            },
            {
                name: hot[7],
                type: 'line',
                stack: '总量',
                data: h
            },
            {
                name: hot[8],
                type: 'line',
                stack: '总量',
                data: i
            },
            {
                name: hot[9],
                type: 'line',
                stack: '总量',
                data: j
            }
        ]
    })}
    componentDidMount() {
        const _this = this
        let url = 'http://pingleme.top:3000/api/v1/statistics/trend'

        axios.get(url)
            .then(function (response) {
                if (response.data.code == 0) {
                    paperDataJson.paperData = response.data.data.keyword_stat
                    paperDataJson.paperData.forEach(element => {
                        element.keyword_stat_units.forEach(element2 => {
                            switch (element2.id) {
                                case 1:
                                    a[element2.year - 2014] = element2.freq
                                    break
                                case 2:
                                    b[element2.year - 2014] = element2.freq
                                    break
                                case 3:
                                    k[element2.year - 2014] = element2.freq
                                    break
                                case 4:
                                    d[element2.year - 2014] = element2.freq
                                    break
                                case 5:
                                    e[element2.year - 2014] = element2.freq
                                    break
                                case 6:
                                    f[element2.year - 2014] = element2.freq
                                    break
                                case 7:
                                    g[element2.year - 2014] = element2.freq
                                    break
                                case 8:
                                    h[element2.year - 2014] = element2.freq
                                    break
                                case 9:
                                    i[element2.year - 2014] = element2.freq
                                    break
                                case 10:
                                    j[element2.year - 2014] = element2.freq
                                    break
                                default:
                                    break
                            }
                        })

                  });
                    _this.setState({
                        isLoad: true
                      });
                }
                
            }).catch(function (error) {
                console.log(error);
            }).finally(function () {
            });
        axios.get('http://pingleme.top:3000/api/v1/statistics/top')
            .then(function (response) {
                if (response.data.code == 0) {
                    paperDataJson.paperData = response.data.data.keyword_stat_units
                    paperDataJson.paperData.forEach(element => {
                        hot[element.id - 1] = element.content
                        words.push({ text: element.content, value: element.freq })
                    })
                }
            }).catch(function (error) {
                console.log(error);
            }).finally(function () {
            });

        
    }
    render() {
        return (
            !this.state.isLoad ? "loading" : (
            <div>
            <h2>TOP10关键词</h2>
                <ReactWordcloud style={{ width: 600, height: 400 }} words={words} />
                <ReactECharts
                style={{ width: 1200, height: 350 }}
                    option={this.getOption()}
                    notMerge={true}
                    lazyUpdate={true}
                    theme={"theme_name"}
                />
            </div>)
        );
    }
}


export default withRouter(StatisticPage);
