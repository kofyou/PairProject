var app= new Vue({
    el: '#app',
    data: {
        info

    },
    methods:{
      search:function(){                
            axios
              .get('http://47.100.89.20:8080/homepage/agenda')
              .then(response => (console(response)))
              .catch(function (error) { // 请求失败处理
                console.log(error);
              });
              }
          }
  })