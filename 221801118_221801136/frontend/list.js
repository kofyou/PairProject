$(function (){
    $("#searchBtn").click(function (){
       var keyword=$("#searchInput").val();
       //如果为空
       if(keyword==''){
           alert("请输入查找内容");
       }
       //不为空则从后端获得论文数据
        else {
            $.ajax({
                url:"",
                method:"post",
                data:{
                    "keyword":contain,
                },
                success:function (data){
                    //显示论文数据
                },
                error:function (){

                },
            })
       }
    });
})