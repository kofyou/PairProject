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
	function delUser(){					
		var selects =$("#usertab").bootstrapTable("getSelections");
		if(selects.length==0){				
			return;
		}	
		var Title="";
		for(var i = 0;i < selects.length; i++){
			//userIds=userIds+selects[i].id+",";
			Title=Title+selects[i].title;
		}
		var param={
				Title:Title
				
		};	
		
		$.ajax({
			url:"/PairWork1/deletePaperController.do",
			data:param,				
			success:function(data){
				alert("helloworldnew11");
				if((typeof(data)!="undefined")&&(data==0)){
					alert("删除成功，！");
					$table.bootstrapTable('refresh');
				}
			},complete:function(){
				//$('#remove').attr("disabled","disabled");
			},
			context:this
		});
	}
	$("#remove").bind("click",delUser);
});