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
		],
		onCheck : function(row) {
			$("#remove_paper").removeAttr("disabled");
		},
		onUncheck:function(){
			$("#remove_paper").attr("disabled","disabled");
		},
		onCheckAll:function(){
			$("#remove_paper").removeAttr("disabled");
		},
		onUncheckAll:function(){
			$("#remove_paper").attr("disabled","disabled");
		},
		
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
	$("#remove").bind("click",delPaper);
	function delPaper(){	

		var selects =$("#papertab").bootstrapTable("getSelections");
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
				if((typeof(data)!="undefined")&&(data==0)){
					alert("删除成功，！");
					$table.bootstrapTable('refresh');
				}
			},complete:function(){
				$('#myModal').modal('hide');
			},
			context:this
		});
	}
	$("#remove_paper").bind("click",delPaper);
	function findPaper(){	

		var id =$("#id1").val();
		var title=$("#name").val();
		var about=$("#about1").val();
		if(id.length==0){				
			return;
		}
		
		var param={
				id:id,	
				title:title,
				about:about
		};	

		$.ajax({
			url:"/PairWork1/findPaperController.do",
			data:param,				
			success:function(data){			
				 var obj = JSON.parse(data);
                 alert("论文编号：   "+obj[0].id+"\n论文题目：   "+obj[0].title+"\n论文链接：   "+obj[0].url+"\n论文相关论坛：   "+obj[0].about	);
					
			},complete:function(){
				$('#myModal').modal('hide');
			},
			context:this
		});		
	}
	$("#find").bind("click",findPaper);
	
	$("#looking").on("click",function(e){
		var b1="Cameras";
		var b2="Training";
		var b3="Computer version";
		var b4="Feature extraction";
		var b5="Shape";
		var b6="Image segmensional";
		var b7="Robustness";
		var b8="Visualization";
		var b9="Three-dimensional displays";
		var b10="Image reconstruction";
		var a1="2176";
		var a2="2023";
		var a3="1819";
		var a4="1752";
		var a5="1425";
		var a6="1384";
		var a7="1266";
		
		var a8="1108";
		var a9="1055";
		var a10="978";
				alert(b1 + "     :" + a1 + "\n" + b2 + "    :" + a2 + "\n" + b3 + "     :"
						+ a3+ "\n" + b4 + "     :" + a4 + "\n" + b5 + "     :" + a5
						+ "\n" + b6 + "     :" + a6 + "\n" + b7 + "     :" + a7 + "\n"
						+ b8+ "     :" + a8 + "\n" + b9 + "     :" + a9 + "\n" + b10
						+ "     :" + a10 + "\n");
	});
});