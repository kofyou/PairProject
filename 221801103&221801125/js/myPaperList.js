$(function(){

    if(DATA_NEEDED_TO_BE_CHANGED)
    {
        //发个ajax数据
        $.ajax({
            url : "",
            type : "post",
            data : {
                "like" : LIKE_LIST
            },
            success:()=>{
                DATA_NEEDED_TO_BE_CHANGED = false
            }
        })
    }
    if(LIKE_LIST.length==0)
    {
        //要个ajax数据
        $.ajax({
            url : "",
            type : "post",
            data : {
                "like" : LIKE_LIST
            },
            dataType : "json",
            success:data=>{
                $.each(data,function(index,like){
                    var obj = {
                        "title" : like.title,
                        "keyword" : like.keyword,
                        "info" : like.info,
                        "author" : like.author,
                        "link" : like.link
                    }
                    LIKE_LIST.push(obj)
                })
            },
            error:()=>{
                alert("掉线了，您再等等吧（估摸着是不行了）")
            }
        })
    }

    LIKE_LIST.forEach(like=>{
        $(".container").append("<div class='paper-item'>"+
        "<div class='paper-head'>"+
            "<span class='paper-title'><a href='"+like.link+"'>"+like.title+"</a></span>"+
            "<!-- <span>作者：</span>"+
            "<span class='paper-author'>"+like.author+"</span> -->"+
            "<span class='iconfont icon-shanchu xiaoshi '></span>"+
        "</div>"+
        "<div class='paper-mid'>"+
            "<span class='mid-word'>关键字：</span>"+
            "<span class='paper-keyword'>"+like.keyword.toString()+"</span>"+
        "</div>"+
        "<div class='paper-foot'>"+
            "<span>摘要：</span>"+
            "<p class='paper-info'>"+like.info+"</p>"+
        "</div>"+
        "<div class='line'></div>"+
    "</div>")
    })

    $(".deleteLike").click(function(){
        $(".icon-shanchu").removeClass("xiaoshi")
    })

    $(".icon-shanchu").click(function(){
        if(confirm("确定要删除吗")){
            console.log(LIKE_LIST)
            DATA_NEEDED_TO_BE_CHANGED = true
            LIKE_LIST.splice($(".icon-shanchu").index(this),1)
            console.log(LIKE_LIST)
            location.reload()
        }
    })
})