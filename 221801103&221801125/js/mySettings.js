$(function(){
    var logOut = $(".slide-down li:eq(1)")
    logOut.click(logOutFunc)
    var btn = $("#search_btn")
    btn.click(searchFunc)
    function searchFunc(){
        let search = $(".search:eq(0)").val()
        if(search!="")
        {
          window.open("./paper/searchList.html?search="+search,"_self")
        }
        else
        {
          window.open("../pages/paper/allPaperList.html","_self")
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
            localStorage.setItem("name", USER_INFO.userID);
            localStorage.setItem("sign", USER_INFO.userID);
            localStorage.setItem("company", USER_INFO.userID);
            localStorage.setItem("address", USER_INFO.userID);
        /**
         * 
         * 发送数据
         * 
         * 
         */
        $.ajax({
            url : "../GetUserInfoServlet",
            type:"post",
            data : {
                "account" : USER_INFO.userID,
                "userName" : USER_INFO.name,
                "address" : USER_INFO.address,
                "company" : USER_INFO.company,
                "info" : USER_INFO.sign
            },
            success:data=>{
                $("#set_wait").css("display","none")
                $("#saveWord").css("display","inline")
                if(data==true)
                    alert("保存成功")
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