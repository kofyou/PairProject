$(function(){
    function Page() {  }
    var top = [90,20,130,190,180,260,60,105,230,150]
    var left = [-100,60,90,-20,180,60,180,300,280,400]
    var color = []
    var strs = []
    for(var i=0;i<10;i++){
        color.push(randomColor())
    }
    
    function randomColor() {
        return '#' +
		(function(color) {
			return(color += '5678956789defdef' [Math.floor(Math.random() * 16)]) &&
				(color.length == 6) ? color : arguments.callee(color);
		})('');
     }

    $.extend(Page.prototype,{
        init: function(){
            this.bindEvents()
            $.ajax({
                url:"",
                method:"post",
                success:data=>{
                    strs = data
                    for(let i = 0;i<10;i++)
                    {
                        let cloud = "#cloud"+(i+1)
                        $(cloud).html(strs[i].toString())
                        $(cloud).css("top",(top[i]+40)+"px")
                        $(cloud).css("left",(left[i]+10)+"px")
                        $(cloud).css("color",color[i])
                        $(cloud).css("opacity","1")
                        $(cloud).hover(function(){
                            $(cloud).css("text-shadow","2px 2px 5px "+color[i])
                        },function(){
                            $(cloud).css("text-shadow","none")
                        })
                    }
                },
                error:()=>{
                    alert("网烂掉了哦")
                }
            })
        },
        bindEvents:function(){
            var logOut = $(".slide-down li:eq(1)")
            logOut.click(this.logOutFunc)
            var btn = $("#search_btn")
            btn.click(this.searchFunc)
        },
        logOutFunc:function(){
            if(confirm("确定要退出吗")){
              window.location.replace("../login&regist/login.html")
            }
        },
        searchFunc:function(){
            let search = $(".search:eq(0)").val()
            if(search!="")
            {
              window.open("../pages/paper/searchList.html?search="+search,"_self")
            }
            else
            {
              window.open("../pages/paper/allPaperList.html","_self")
            }
          }
    })

    var page = new Page()
    page.init();
})