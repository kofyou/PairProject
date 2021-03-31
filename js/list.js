
  var app= new Vue({
    el: '#app',
    data: {
     value:"",
      type:"keyword",
      item:[

      ],
      link:" ",
      pageNum:1,
      word:
      { 
        searchType:"title",
        content:"regularized approach"
      }
    },
    created: function (variable) {
        query = window.location.search.substring(1);
        vars = query.split("&");
        for (i=0;i<vars.length;i++) {
                pair = vars[i].split("=");
                if(pair[0] == "value"){
                 console.log(pair[1])   
                 this.value=pair[1]
                 this.value=decodeURI(this.value)
                }
                if(pair[0] == "type"){
                    console.log(pair[1])   
                    this.type=pair[1]
                    this.type=decodeURI(this.type)
                   }

        }
        var that=this;
        axios.post('http://81.68.149.69:18902/search/searchPaper',{
            searchType:this.type,
            content:this.value,
            pageNum:1,
            pageSize:5
        })
        .then(function(response)
        {   
            that.item=response.data.data.list;
            console.log(that.item.data)
        }
        )
        .catch(function (error) { // 请求失败处理
          console.log(error);
        });
        return(false); 
        },

    methods:{
      search:function(pageNum){   
        var that=this;
        axios.post('http://81.68.149.69:18902/search/searchPaper',{
            searchType:this.type,
            content:this.value,
            pageNum:this.pageNum,
            pageSize:5
        })
        .then(function(response)
        {   console.log(response)
            that.item=response.data.data.list;
            scrollTo(0,0);
            
        }
        )
        .catch(function (error) { // 请求失败处理
          console.log(error);
        });
       
          },
          Next:function()
          {
            this.pagNume=this.pagNume+1,
            console.log(this.pagNume)
           this.search()
          }
  },}) 

