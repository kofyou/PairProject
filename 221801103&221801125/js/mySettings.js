$(function(){
    $(".setting-userName").val(USER_INFO.userID)
    $(".setting-name").val(USER_INFO.name)
    $(".setting-company").val(USER_INFO.company)
    $(".setting-address").val(USER_INFO.address)
    $(".setting-info").val(USER_INFO.sign)
    $(".baocun").click(()=>{
        USER_INFO.name = $(".setting-name").val().toString()
        USER_INFO.company = $(".setting-company").val().toString()
        USER_INFO.address = $(".setting-address").val().toString()
        USER_INFO.sign = $(".setting-info").val().toString()
        /**
         * 
         * 发送数据
         * 
         * 
         */
        $.ajax({
            url : "",
            data : {
                "username" : USER_INFO.userID,
                "name" : USER_INFO.name,
                "address" : USER_INFO.address,
                "company" : USER_INFO.company,
                "info" : USER_INFO.sign
            },
            dataType:"json",
            success:data=>{
                if(data.type==true)
                    alert("保存成功")
                else
                    alert("也不知道为什么就保存失败了qwq")
            },
            error:()=>{
                alert("网络可能已经炸了，再等等吧orz")
            }
        })
    })
})