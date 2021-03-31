$(function(){
    var logOut = $(".slide-down li:eq(1)")
    logOut.click(logOutFunc)
    var btn = $("#search_btn")
    btn.click(searchFunc)
    function searchFunc(){
        let search = $(".search:eq(0)").val()
        if(search!="")
        {
          window.open("./paper/searchList.html?search="+search+"&page=1","_self")
        }
        else
        {
          window.open("../pages/paper/allPaperList.html?page=1","_self")
        }
      }

    function logOutFunc(){
        if(confirm("确定要退出吗")){
            window.location.replace("../login&regist/login.html")
          }
    }
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
        localStorage.setItem("userName", USER_INFO.userID);
        localStorage.setItem("name", USER_INFO.name);
        localStorage.setItem("sign", USER_INFO.sign);
        localStorage.setItem("company", USER_INFO.company);
        localStorage.setItem("address", USER_INFO.address);

        /**
         * 
         * 发送数据
         * 
         * 
         */
        $.ajax({
            url : AJAX_URL.mySetting,
            type:"post",
            data : JSON.stringify({
                "account" : USER_INFO.userID,
                "username" : USER_INFO.name,
                "address" : USER_INFO.address,
                "company" : USER_INFO.company,
                "info" : USER_INFO.sign
            }),
            contentType:"application/json",
            success:data=>{
                $("#set_wait").css("display","none")
                $("#saveWord").css("display","inline")
                if(data=="true")
                {
                    alert("保存成功")
                    $(".right-part:eq(3) div:eq(0)").html(USER_INFO.name.trim()==""?USER_INFO.userID : USER_INFO.name)
                }
                else
                    alert("也不知道为什么就保存失败了qwq")
            },
            error:()=>{
                $("#set_wait").css("display","none")
                $("#saveWord").css("display","inline")
                alert("网络可能已经炸了，再等等吧orz")
            }
        })
        $("#set_wait").css("display","inline-block")
        $("#saveWord").css("display","none")
    })
})