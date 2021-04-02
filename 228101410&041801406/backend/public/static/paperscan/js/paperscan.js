var Main ={
    data(){
        return {
        textarea: '',
        link: '',
    }
    },
    methods:{
        changes:function(){
            var href = window.location.search;
            var word = href.substring(href.lastIndexOf('=') + 1, href.length);
            console.log(word); 
            $.ajax({
                url: '../index.php/index/Serchfunction/serchforpaperscan?words=' + word,
                type: 'get',
                data: {},
                dataType: 'json'
            }).then(function(res) {
                var link = res[0].link;
                word=word.replaceAll("%20"," ");
                this.link=word;
                this.link.href=link;
                this.textarea=res[0].abstract;
                console.log(res);
            }).fail(function() {
                console.log('失败');
            })
        }
    }
}
var Ctor = Vue.extend(Main)
new Ctor().$mount('#app')
function init(){

    var href = window.location.search;
    var word = href.substring(href.lastIndexOf('=') + 1, href.length);
    console.log(word); 
    $.ajax({
        url: '../index.php/index/Serchfunction/serchforpaperscan?words=' + word,
        type: 'get',
        data: {},
        dataType: 'json'
    }).then(function(res) {
        var title = document.getElementById("title");
        var abstract = document.getElementById("abstract");
        var link = res[0].link;
        title.setAttribute("href", link);
        word=word.replaceAll("%20"," ");
        title.textContent = word;
        abstract.textContent=res[0].abstract;
        console.log(res);
    }).fail(function() {
        console.log('失败');
    })
}
window.onload = function(){
    init();
}
