// 服务端请求地址
let url = 'http://localhost:8080/MyShop_war_exploded/User';

let vm = new Vue({
    el: ".listContent",
    data: {
        //后端向前端接收数据
        list: [],
        //向后端传递数据

    },
    methods: {
        getInfo() {
            var that=this;
            axios.post(url).then(function (response) {
                that.list = response.data;
            }).catch(function (error) {
                    console.log(error);
                });
        },

        select(){

        },

        send(title){
            //alert(title);
            window.location.href="paperView.html"+"?"+"title"+"="+title;
        }
    }
});