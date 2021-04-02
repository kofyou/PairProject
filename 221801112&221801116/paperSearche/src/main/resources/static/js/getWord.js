var text = 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean bibendum erat ac justo sollicitudin, quis lacinia ligula fringilla. Pellentesque hendrerit, nisi vitae posuere condimentum, lectus urna accumsan libero, rutrum commodo mi lacus pretium erat. Phasellus pretium ultrices mi sed semper. Praesent ut tristique magna. Donec nisl tellus, sagittis ut tempus sit amet, consectetur eget erat. Sed ornare gravida lacinia. Curabitur iaculis metus purus, eget pretium est laoreet ut. Quisque tristique augue ac eros malesuada, vitae facilisis mauris sollicitudin. Mauris ac molestie nulla, vitae facilisis quam. Curabitur placerat ornare sem, in mattis purus posuere eget. Praesent non condimentum odio. Nunc aliquet, odio nec auctor congue, sapien justo dictum massa, nec fermentum massa sapien non tellus. Praesent luctus eros et nunc pretium hendrerit. In consequat et eros nec interdum. Ut neque dui, maximus id elit ac, consequat pretium tellus. Nullam vel accumsan lorem.';
// 注意：这里的代码只是对上面的句子进行分词并计算权重（重复次数）并构建词云图需要的数据，其中 arr.find 和
// 	reduce 函数可能在低版本 IE 中无法使用（属于ES6新增的函数），如果不能正常使用（对应的函数有报错），请自行加相应的 Polyfill
//  array.find 的 ployfill 参见：https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Array/find#Polyfill
// 	array.reduce 的 ployfill ：https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Array/reduce#Polyfill
$.when(
    $.getJSON('/topKeyWordCloud'),
).done(function (res0) {
    var data = res0;
    Highcharts.chart('wordContainer', {
        plotOptions: {
            series: {
                cursor: 'pointer',
                point: {
                    events: {
                        click: function () {
                            toKeyWord(this.name)
                        }
                    }
                }
            }
        },
        series: [{
            type: 'wordcloud',
            data: data
        }],
        title: {
            text: '词云图'
        }
    });
})