var app= new Vue({
  el: '#app',
  data: {
    info:[ ],
   value:"",
    type:"keyword"
  },
  methods:{
    search:function(){                
      
          axios
            .get('http://47.100.89.20:8080/homepage/agenda')
            .then(response => (console.log(response) ))
            .catch(function (error) { // 请求失败处理
              console.log(error);
            });
            },
            to:function(){             
              console.log(this.value)   
              console.log(this.type)   
              window.location.assign("list.html?value="+this.value+"&type="+this.type)
                }
            
        }
})