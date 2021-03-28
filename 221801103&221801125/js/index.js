$(function(){
    function Page() {  }
    $.extend(Page.prototype,{
        init: function(){
            alert(1234)
        },
        bindEvents: function(){
            // var btn = $("#btn")
            // btn.on('click',$.proxy(this.handleButtonClick,this))//使得调用handleButtonClick的this指针可以绑定在这里
        },
        handleButtonClick: function(){

        }
    })

    var page = new Page()
    page.init();
})