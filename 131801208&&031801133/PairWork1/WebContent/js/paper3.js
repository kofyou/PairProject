// JavaScript Document
$ (function($){
	
	var table =$("#papertab3");
	
	table.bootstrapTable({
		height:"600px",
		url : "/PairWork1/paperListController3.do",
		method : "GET",		
		columns:[
			{
			field:'select',
			checkbox:true,
			align:"center",
			valign:"middle",
			},
			
			{
			field:'id',
			title:"编号",
			align:"center",
			valign:"middle",
			},
			
			{
			field:'title',
			title:"论文题目",
			align:"center",
			valign:"middle",
			},
			
			{
			field:'url',
			title:"论文链接",
			align:"center",
			valign:"middle",
			},
			
			{
			field:'about',
			title:"相关论坛",
			align:"center",
			valign:"middle",
			},
			
			{
			field:'keywords',
			title:"关键字",
			align:"center",
			valign:"middle",
			},
		]
	
	});
	$("#ICCV").on("click",function(e){
		alert("hellw33333");
		/*$.ajax({
			type : "post",
			url : "/PairWork1/paperListController3.do",
			
			dataType : "json",
			success : function(json) {

				$("#papertab").bootstrapTable('load', json);
			}
		});*/
			 

		// $("#papertab").load("/PairWork1/paperListController3.do");
		
		$("#papertab").bootstrapTable('refresh',"/PairWork1/paperListController.do");
		
	});

});