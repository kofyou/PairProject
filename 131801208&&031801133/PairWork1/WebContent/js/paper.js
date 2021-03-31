// JavaScript Document
$ (function($){
	
	var table =$("#papertab");
	
	table.bootstrapTable({
		height:"600px",
		url : "/PairWork1/paperListController.do",
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
	/*$("#CVPR").on("click",function(e){
		$.ajax({
            url:"/PairWork1/paperListController.do",
            type:"post",
            dataType:"json",
            data:{              
                value:"CVPR",
            },
            
		});

		 //$("#papertab").load("/PairWork1/paperListController3.do");
		 //$("#papertab").bootstrapTable('destroy');
	
		//$("#papertab").bootstrapTable('refreshOptions',"/PairWork1/paperListController3.do");
	
		$("#papertab").bootstrapTable('refresh');
		
	});*/

});