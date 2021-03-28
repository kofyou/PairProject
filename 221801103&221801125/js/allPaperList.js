$(function(){
    var addInLike = new Array()
    var addIndex = new Array()
    var removeInLike = new Array()
    var removeIndex = new Array()
    if(ALL_PAGE_LIST.length==0){
        /**
         * 
         * 获取后端数据
         */
        $.ajax({
            url:"",
            type:"post",
            data:{
                "userName":USER_INFO.userID
            },
            dataType:"json",
            success:data=>{
                DATA_NEEDED_TO_BE_CHANGED = false
                $.each(data,function(index,paper){
                    $(".container").append("<div class='paper-item'>"+
                    "<div class='paper-head'>"+
                        "<span class='paper-title'><a href='"+paper.link+"'>"+paper.title+"</a></span>"+
                        "<!-- <span>作者：</span>"+
                        "<span class='paper-author'>"+paper.author+"</span> -->"+
                        "<span class='iconfont icon-shoucang1 kongxin'></span>"+
                        "<span class='iconfont icon-shoucang xiaoshi shixin'></span>"+
                    "</div>"+
                    "<div class='paper-mid'>"+
                        "<span class='mid-word'>关键字：</span>"+
                        "<span class='paper-keyword'>"+paper.keyword.toString()+"</span>"+
                    "</div>"+
                    "<div class='paper-foot'>"+
                        "<span>摘要：</span>"+
                        "<p class='paper-info'>"+paper.info+"</p>"+
                    "</div>"+
                    "<div class='line'></div>"+
                "</div>")
                })
            },
            error:()=>{
                alert("网络烂掉了，你什么也看不到了")
            }
        })
        $("container").append("<div>正在加载中.......</div>")
    }
    else{
        ALL_PAGE_LIST.forEach(paper=>{
            $(".container").append("<div class='paper-item'>"+
                    "<div class='paper-head'>"+
                        "<span class='paper-title'><a href='"+paper.link+"'>"+paper.title+"</a></span>"+
                        "<!-- <span>作者：</span>"+
                        "<span class='paper-author'>"+paper.author+"</span> -->"+
                        "<span class='iconfont icon-shoucang1 kongxin'></span>"+
                        "<span class='iconfont icon-shoucang xiaoshi shixin'></span>"+
                    "</div>"+
                    "<div class='paper-mid'>"+
                        "<span class='mid-word'>关键字：</span>"+
                        "<span class='paper-keyword'>"+paper.keyword.toString()+"</span>"+
                    "</div>"+
                    "<div class='paper-foot'>"+
                        "<span>摘要：</span>"+
                        "<p class='paper-info'>"+paper.info+"</p>"+
                    "</div>"+
                    "<div class='line'></div>"+
                "</div>")
        })
    }
    for(let index = 0;index<ALL_PAGE_LIST.length;index++)
    {
        if(ALL_PAGE_LIST[index].like)
        {
            $(".kongxin").eq(index).addClass("xiaoshi")
            $(".shixin").eq(index).removeClass("xiaoshi")
            /**
             * 
             * 后续要改成唯一标识
             * 
             */
            removeInLike.push(ALL_PAGE_LIST[index].title)
            removeIndex.push(index)
        }
    }
    $(".kongxin").click(function(){
        var index = $(".kongxin").index(this);
        var delIndex = removeIndex.indexOf(index)
        $(".kongxin").eq(index).addClass("xiaoshi")
        $(".shixin").eq(index).removeClass("xiaoshi")
        /**
         * 
         * 后续需要改成唯一表示论文的内容
         * 不能用text()标题内容来表示
         * 这样会使得后端人员崩溃
         */
        //只有这个不在表当中,
        addInLike.push($(".paper-title").eq(index).text())
        addIndex.push(index)
        removeIndex.splice(delIndex,1)
        removeInLike.splice(delIndex,1)
        $(".addItem").css("width","40px")
        $(".addItem").css("border","1px solid")
        $(".removeItem").css("width","0px")
        $(".removeItem").css("border","0px solid")
      });
    $(".shixin").click(function(){
         /**
         * 
         * 后续需要改成唯一表示论文的内容
         * 不能用text()标题内容来表示
         * 这样会使得后端人员崩溃
         */
        var index = $(".shixin").index(this);
        var delIndex = addIndex.indexOf(index);
        addInLike.splice(delIndex,1)
        addIndex.splice(delIndex,1)
        removeInLike.push($(".paper-title").eq(index).text())
        removeIndex.push(index)
        console.log(addInLike)
        console.log(addIndex)
        $(".shixin").eq(index).addClass("xiaoshi")
        $(".kongxin").eq(index).removeClass("xiaoshi")
        if(addInLike.length==0)
        {
            $(".addItem").css("width","0px")
            $(".addItem").css("border","0px solid")
            $(".removeItem").css("width","40px")
            $(".removeItem").css("border","1px solid")
        }
    })
    $(".addItem").click(function(){
        $.ajax({
            url:"",
            data:{
                "userName":USER_INFO.userID,
                "likeList":addIndex
            },
            type:"POST",
            dataType:"json",
            success:data=>{
                if(data.type==true)
                    alert("收藏成功")
                else
                    alert("不知道为啥没成功哦，可能去了火星")
            },
            error:()=>{
                alert("啊哦，网络可能出问题了，请重新收藏")
            }
        })
    })

    $(".removeItem").click(function(){
        $.ajax({
            url:"",
            data:{
                "userName":USER_INFO.userID,
                "likeList":addIndex
            },
            type:"POST",
            dataType:"json",
            success:data=>{
                if(data.type==true)
                    alert("收藏成功")
                else
                    alert("不知道为啥没成功哦，可能去了火星")
            },
            error:()=>{
                alert("啊哦，网络可能出问题了，请重新收藏")
            }
        })
    })

})