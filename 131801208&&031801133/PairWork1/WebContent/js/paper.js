// JavaScript Document
$ (function($){
	
	var table =$("#papertab");
	
	table.bootstrapTable({
		height:"600px",
		url : "/PairWork1/paperListController.do",
		method : "GET",
		/*data:[
			{
				"id":"1",
				"title":"出师表",
				"info":"论文内容我有什么什么什么什么",
				"about":"CCTV",
				"phone":"110",
			}
		],*/
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
});