// 服务端请求地址
let url = 'http://localhost:8080/MyShop_war_exploded/User';
let vm = new Vue({
    el: "#app",
    data: {
        //后端向前端接收数据
        list: [],
        //向后端传递数据
        name: 'sww',
        saying: '123',
    },
    methods: {
        add() {
            // 传送的数据为json格式
            let data = JSON.stringify({
                name: this.name,
                saying: this.saying
            });
            /**
             * url 请求路径
             * data 传递数据
             */

            // var instance = axios.create({
            //     baseURL: 'https://some-domain.com/api/',
            //     timeout: 1000,
            //     headers: {'X-Custom-Header': 'foobar'}
            // });

            axios.post(url, data,{
                // headers: {
                //     'Content-Type': 'application/json;charset=UTF-8'
                // },
            }).then(function (response) {
                    // console.log(response);
                    // 获取服务端返回的数据
                    vm.$data.list = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
});
// axios.get(url, {})
//     .then(function (response) {
//         vm.$data.list = response.data;
//     })
//     .catch(function (error) {
//         console.log(error);
//     })
//     .then(function () {
//         // always executed
//     });

/*

Content-Type: application/json ： 请求体中的数据会以json字符串的形式发送到后端
Content-Type: application/x-www-form-urlencoded：请求体中的数据会以普通表单形式（键值对）发送到后端
Content-Type: multipart/form-data： 它会将请求体的数据处理为一条消息，以标签为单元，用分隔符分开。既可以上传键值对，也可以上传文件。
*/