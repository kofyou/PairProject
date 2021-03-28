// 服务端请求地址
let url = 'http://localhost:8080/MyShop_war_exploded/User';

let vm = new Vue({

    el:"#app",
    data:{
        list:[],
        title:""
    },
    methods:{
        fun(tit) {
            let data = JSON.stringify({
                title: tit,
            });
            var that = this;
            axios.post(url,data).then(function (response) {
                that.list = response.data;
            }).catch(function (error) {
                console.log(error);
            });

        },


        get(){
            this.title = window.location.href.split('=')[1];
            //逻辑
            alert(this.title);
            this.fun(this.title);
        },

    }

});