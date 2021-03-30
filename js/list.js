
  var app= new Vue({
    el: '#app',
    data: {
     value:"",
      type:"keyword",
      item:[

      ],
      
    },
    created: function (variable) {
        // `this` 指向 vm 实例      
        query = window.location.search.substring(1);
        vars = query.split("&");
        for (i=0;i<vars.length;i++) {
                pair = vars[i].split("=");
                if(pair[0] == "value"){
                 console.log(pair[1])   
                 this.value=pair[1]
                }
                if(pair[0] == "type"){
                    console.log(pair[1])   
                    this.type=pair[1]
                   }

        }
        return(false); 
        },
    methods:{
      search:function(){   
        var that=this;
        axios
        .get('./js/datas.json')
        .then(function(response)
        {
            that.item=response.data.data;
            console.log(that.item)
        }
        )
        .catch(function (error) { // 请求失败处理
          console.log(error);
        });
       
          }
  },}) 

