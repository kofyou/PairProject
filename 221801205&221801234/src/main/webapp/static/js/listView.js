// 服务端请求地址
let url = turl+'PaperListByTitle';

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
        isActive:false,
        ImgSrc:"../img/em.png",
        //后端向前端接收数据
        list: [],
        //向后端传递数据
    },
    methods: {
        getInfo() {

            // var newArr4 = JSON.parse(JSON.stringify(arr));
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
            this.ImgSrc="../img/loading.png";
            axios.post(turl+'PaperListByKeyword',data).then(function (response) {
                that.list = response.data;
                that.tp=that.list;
                that.ImgSrc="../img/em.png";
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
                this.ImgSrc="../img/loading.png";
                axios.post(url,data).then(function (response) {
                    that.list = response.data;
                    that.tp=that.list;
                    that.ImgSrc="../img/em.png";
                    //隐藏
                    if(that.tp.length==0){
                        //alert("找不到");
                        //that.ImgSrc="../img/em.png";
                        that.changeEmptyStatus(false);
                    }else{
                        that.changeEmptyStatus(true);
                    }
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
                    this.tp = this.list.filter(value=>{ return value.year.indexOf(this.tData) > -1});
                }else if(t==3){//meeting
                    this.tp = this.list.filter(value=>{ return value.meeting.toUpperCase().indexOf(this.tData.toUpperCase()) > -1});
                }else if(t==4){//name
                    this.tp = this.list.filter(value=>{ return value.name.toUpperCase().indexOf(this.tData.toUpperCase()) > -1});
                }
                if(this.tp.length==0){
                    this.changeEmptyStatus(false);
                }else{
                    this.changeEmptyStatus(true);
                }
            }
        },

        reset_list(){

            this.tp=this.list;
            if(this.tp.length!=0){
                this.changeEmptyStatus(true)
            }else this.changeEmptyStatus(false)
        },

        changeErr(){
            if(this.error){
                this.error=!this.error;
            }
        },


        //删除
        deleteD(index){

            this.tp.splice(index,1);
            if(this.tp.length==0){
                this.changeEmptyStatus(false);
            }
        },

        changeEmptyStatus:function(par){
            this.isActive=par;
        },

        //跳转详情页
        send(title){
            window.open("paperView.html"+"?"+"title"+"="+encodeURI(title));
        },


        // send(title){
        //     window.open("paperView.html");
        // },

        get(){
            this.params = window.location.href.split('=')[1];
            if(this.params!=undefined)
                this.selectByKey(decodeURI(this.params));
        },
    }
});
