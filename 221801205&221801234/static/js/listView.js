// 服务端请求地址
// let url = 'http://localhost:8080/crawler_war_exploded/PaperListByTitle';
let url="http://39.102.39.208/crawler_war/PaperListByTitle";
let vm = new Vue({
    el: ".container",
    data: {
        error:false,
        title:"",
        keywords:"",
        meeting:"",
        name:"",
        year:"",
        option:1,
        tData:"",
        tp:[],

        //后端向前端接收数据
        list: [],
        //向后端传递数据

    },
    methods: {
        getInfo() {
            this.tp=this.list;
            var that=this;
            axios.post(url).then(function (response) {
                that.list = response.data;
            }).catch(function (error) {
                    console.log(error);
                });
        },

        selectByKey(params){
            let data = JSON.stringify({
                keyword: params,
            });
            this.tp = this.list;
            var that = this;
            axios.post('http://39.102.39.208/crawler_war/PaperListByKeyword',data).then(function (response) {
                that.list = response.data;
                that.tp=that.list;
            }).catch(function (error) {
                console.log(error);
            });
        },
        //向后端搜索
        select(){
            if(this.title==''){
                this.error=true;
            }else{
                var that=this;
                let data = JSON.stringify({
                    title: this.title,

                });
                axios.post(url,data).then(function (response) {
                    that.list = response.data;
                    that.tp=that.list;

                }).catch(function (error) {
                    console.log(error);
                });
            }
        },

        //列内搜索
        select_list(){
            this.tp=this.list;
            if(this.tData==''){
                this.error=true;
            }
            else{
                var t = this.option;
                if(t==1){//key
                    this.tp = this.list.filter(value=>{ return value.keywords.toUpperCase().indexOf(this.tData.toUpperCase()) > -1});
                }else if(t==2){//year
                    this.tp = this.list.filter(value=>{ return value.year.toUpperCase().indexOf(this.tData.toUpperCase()) > -1});
                }else if(t==3){//meeting
                    this.tp = this.list.filter(value=>{ return value.meeting.toUpperCase().indexOf(this.tData.toUpperCase()) > -1});
                }else if(t==4){//name
                    this.tp = this.list.filter(value=>{ return value.name.toUpperCase().indexOf(this.tData.toUpperCase()) > -1});
                }
            }
        },

        changeErr(){
            if(this.error){
                this.error=!this.error;
            }

        },


        //删除
        deleteD(index){
            this.list.splice(index,1);
            this.tp=this.list;
        },

        //跳转详情页
        send(title){
            window.location.href="paperView.html"+"?"+"title"+"="+encodeURI(title);
        },

        get(){
            this.params = window.location.href.split('=')[1];
            if(this.params!=undefined)
                this.selectByKey(decodeURI(this.params));
        },
    }
});