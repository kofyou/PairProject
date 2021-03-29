
new Vue().$mount('#app');
window.onload = function(){
    var href = window.location.search;
    var word = href.substring(href.lastIndexOf('=') + 1, href.length);
    console.log(word);
    word=word.replaceAll("%20"," "); 
    $.ajax({
        url: '?data=' + word,
        type: 'get',
        data: {},
        dataType: 'json'
    }).then(function(res) {
        　　　　　　console.log(res);
    }).fail(function() {
        console.log('失败');
    })
}
    var title = document.getElementById("title");
    var abstract = document.getElementById("abstract");
    var link = "http://www.baidu.com";
    title.setAttribute("href", link);
    title.textContent = "abc";
    var post ;
    abstract.textContent=post;
}