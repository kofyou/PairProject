
new Vue().$mount('#app');
window.onload = function(){
    var href = window.location.search;
    var word = href.substring(href.lastIndexOf('=') + 1, href.length);
    console.log(word); 
    $.ajax({
        url: '../index.php/index/Serchfunction/serchforpaperscan?words=' + word,
        type: 'get',
        data: {},
        dataType: 'json'
    }).then(function(res) {
        var title = document.getElementById("title2");
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
