// let url = 'http://localhost:8080/crawler_war_exploded/PaperListDetailByTitle';
// let url="http://39.102.39.208/crawler_war/PaperListDetailByTitle";
let url = turl+'PaperListDetailByTitle';
let vm = new Vue({
    el:".container",
    data:{
        list:{abstract:"信息: Server startup in 955 ms\n" +
"{\"abstract\":\"In this study, We present a representation based on a new 3D search technique for volumetric human poses which is\n" +
"then used to recognize actions in three dimensional video sequences. We generate a set of cylinder like 3D kernels in various\n" +
"These kernels are searched over 3D volumes to find high response regions. The distribution of these\n" +
"responses are then used to represent a 3D pose. We use the proposed representation for (i) pose retrieval using Nearest Neighbor\n" +
"(NN) based classification and Support Vector Machine (SVM) based classification methods, and for (ii) action recognition on a\n" +
"set of actions using Dynamic Time Warping (DTW) and Hidden Markov Model (HMM) based classification methods. Evaluations on IXMAS\n" +
"dataset supports the effectiveness of such a robust pose representation.\" , \" keywords\" :\"Conferences, Engine cylinders, Hidden Markov\n" +
"models, Histograms , Humans, Kernel , Robustness , Shape , Support vector machine classification, Support vector\n" +
"machines\" ,\"meeting\":\"ICCV\" , \"name\":\"3D human pose search using oriented\n" +
"cylinders\", \"ur1\" :\"https: //doi. org/10.1109/ICCVW.2009.5457722\", \"year\" :\"2009\"}",
    url:"http://www.baidu.com",
    year:"2020",
    meeting:"CPVR",
    keywords:"信息: Server startup in 955 ms\n" +
        "{\"abstract\":\"In this study, We present a representation based on a new 3D search technique for volumetric human poses which is\n" +
        "then used to recognize actions in three dimensional video sequences. We generate a set of cylinder like 3D kernels in various\n" +
        "These kernels are searched over 3D volumes to find high response regions. The distribution of these\n" +
        "responses are then used to represent a 3D pose. We use the proposed representation for (i) pose retrieval using Nearest Neighbor\n" +
        "(NN) based classification and Support Vector Machine (SVM) based classification methods, and for (ii) action recognition on a\n" +
        "set of actions using Dynamic Time Warping (DTW) and Hidden Markov Model (HMM) based classification methods. Evaluations on IXMAS\n" +
        "dataset supports the effectiveness of such a robust pose representation.\" , \" keywords\" :\"Conferences, Engine cylinders, Hidden Markov\n" +
        "models, Histograms , Humans, Kernel , Robustness , Shape , Support vector machine classification, Support vector\n" +
        "machines\" ,\"meeting\":\"ICCV\" , \"name\":\"3D human pose search using oriented\n" +
        "cylinders\", \"ur1\" :\"https: //doi. org/10.1109/ICCVW.2009.5457722\", \"year\" :\"2009\"}"},
         name:"",
        // abstract:"信息: Server startup in 955 ms\n" +
        //     "{\"abstract\":\"In this study, We present a representation based on a new 3D search technique for volumetric human poses which is\n" +
        //     "then used to recognize actions in three dimensional video sequences. We generate a set of cylinder like 3D kernels in various\n" +
        //     "These kernels are searched over 3D volumes to find high response regions. The distribution of these\n" +
        //     "responses are then used to represent a 3D pose. We use the proposed representation for (i) pose retrieval using Nearest Neighbor\n" +
        //     "(NN) based classification and Support Vector Machine (SVM) based classification methods, and for (ii) action recognition on a\n" +
        //     "set of actions using Dynamic Time Warping (DTW) and Hidden Markov Model (HMM) based classification methods. Evaluations on IXMAS\n" +
        //     "dataset supports the effectiveness of such a robust pose representation.\" , \" keywords\" :\"Conferences, Engine cylinders, Hidden Markov\n" +
        //     "models, Histograms , Humans, Kernel , Robustness , Shape , Support vector machine classification, Support vector\n" +
        //     "machines\" ,\"meeting\":\"ICCV\" , \"name\":\"3D human pose search using oriented\n" +
        //     "cylinders\", \"ur1\" :\"https: //doi. org/10.1109/ICCVW.2009.5457722\", \"year\" :\"2009\"}",
        // url:"http://www.baidu.com",
        // year:"2020",
        // meeting:"CPVR",
        // keywords:"weiwei,si"
    },
    methods:{
        fun(tit) {
            let data = JSON.stringify({
                title: tit,
            });
            var that = this;
            axios.post(url,data).then(function (response) {
                that.list = response.data;
                //that.list.name = that.list.name.decode('utf-8');
            }).catch(function (error) {
                console.log(error);
            });

        },

        get(){
            this.title = window.location.href.split('=')[1];
            //逻辑
           // alert(this.title);
            this.fun(this.title);
        },
    }
});

