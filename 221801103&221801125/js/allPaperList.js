$(function(){
    var addInLike = new Array()
    var addIndex = new Array()
    $(".kongxin").click(function(){
        var index = $(".kongxin").index(this);
        $(".kongxin").eq(index).addClass("xiaoshi")
        $(".shixin").eq(index).removeClass("xiaoshi")
        /**
         * 
         * 后续需要改成唯一表示论文的内容
         * 
         */
        addInLike.push($(".paper-title").eq(index).text())
        addIndex.push(index)
        $(".addItem").css("width","40px")
        $(".addItem").css("border","2px solid")
      });
    $(".shixin").click(function(){
        var index = $(".shixin").index(this);
        var delIndex = addIndex.indexOf(index);
        addInLike.splice(delIndex,1)
        addIndex.splice(delIndex,1)
        console.log(addInLike)
        console.log(addIndex)
        $(".shixin").eq(index).addClass("xiaoshi")
        $(".kongxin").eq(index).removeClass("xiaoshi")
        if(addInLike.length==0)
        {
            $(".addItem").css("width","0px")
            $(".addItem").css("border","0px solid")
        }
    })
    $(".addItem").click(function(){
        $.ajax({
            url:"",
            data:{
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