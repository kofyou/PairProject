// JavaScript Document
$(function($){
	$("#loginbtn").on("click",function(e)
	{
		var userName=$("#username").val();
		var pwd=$("#pwd").val();
		if(pwd==userName){
			window.location.href="../index.html";
		}
		
		//alert(userName +" password:["+pwd+"]");
		/*$.ajax({
			url:'../html/login.html',
			type:'post',
			data:{
				username:userName,
				pwd:pwd,			
			},
			error:function()
			{
				alert("服务器超时！");
			},
		
			success:function(res)
			{
				window.location.href="../html/index.html";
			}	
	
	     });*/
	});
	
});