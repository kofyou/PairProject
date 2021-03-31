<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>加载中...</title>
<link href="<?= bloginfo('template_url'); ?>/quickfind/QuickFind.css" rel="stylesheet" type="text/css">
</head>

<body>
	<div class="nav">
		<ul class="template">
			<li><a href="index" id="navIcon"></a></li>
			<li><a href="index">论文列表</a></li>
			<li><a href="analy">趋势分析</a></li>
			<li><a href="about">关于我们</a></li>
		</ul>
	</div>
<div class="main">
	<div class="upper">
		<form class="operate">
			<input type="text" class="search" id="searchtext"  name="search">
			<script>
			</script>
			<input type="button" class="button" id="searchbtn" value="搜索">
	    </form>
    </div>
		
		
		
	<div class="middle">
		<div class="texttitle" id="paper_template" style="display:none;">
			<input type="checkbox" class="checkbox" name="text" value="" onclick="checkboxfunc();" />
			<p class="list" onClick="jump(this.type)"></p>	
		</div>
		<form id="paperlist">
	    </form>
    </div>
		
	<div class="down">
		<div class="delete">
		  <form >
			  <input type="button" class="deletebtn" id="deletebtn" onClick="killpaper()" value="删除">
		  </form>
	    </div>
		<div class="pagelist" id="pagelist">
			
	    </div>
    </div>
	
</div>

<script src=<?= bloginfo('template_directory').'/quickfind/jquery.min.js'; ?>></script>
	<script>
	function checkboxfunc(){ }
	function jump(a){
		sessionStorage['paper_id'] = a;
		window.location.href = "http://blog.tozzger.info/quickfind/paper";
	}
	function killpaper()
	{
		var divs = document.getElementById('paperlist').getElementsByTagName('input');
		var i = 0;
		var checked = new Array();
		for(var i = 0; i < divs.length; i++)
		{
			if(!divs[i].checked)
				continue;
			checked.push(divs[i].value);
		}
		if (checked.length > 0) {
			sessionStorage['except'] = sessionStorage['except'].split(',').concat(checked).join(',');
			document.getElementById('searchbtn').click();
		}
	}
	if (sessionStorage['curr_page'] == undefined)
		sessionStorage['curr_page'] = "1";
	if (sessionStorage['except'] == undefined)
		sessionStorage['except'] = "-1";
	</script>
<script>
		function addPageBtn(str, index)
		{

			var btn = document.createElement('a');
			btn.innerHTML = str;
			btn.type = index;
			btn.onclick = function() {
				sessionStorage['curr_page'] = btn.type;
				document.getElementById('searchbtn').click();
			};
			document.getElementById('pagelist').appendChild(btn);
			return btn;
		}
		function succ(result){
			document.title = '加载中...';
        	var childs=document.getElementById('paperlist').childNodes;  
			for(var i=childs.length-1;i>=0;i--){  
				document.getElementById('paperlist').removeChild(childs.item(i));  
			}
			for(var i=0;i<result["row"].length;i++){
				var paper = document.getElementById('paper_template').cloneNode(true);
				paper.style = '';
				paper.getElementsByTagName('input')[0].value = result["row"][i].id;
				paper.getElementsByTagName('p')[0].type = result["row"][i].id;
				paper.getElementsByTagName('p')[0].innerHTML = result["row"][i].title;
				document.getElementById('paperlist').appendChild(paper);
			}
			childs = document.getElementById('pagelist').childNodes;
			for(var i=childs.length-1;i>=0;i--){  
				document.getElementById('pagelist').removeChild(childs.item(i));  
			}
			var page = Math.ceil(result.leng / 5);
			sessionStorage['curr_page'] = Math.max(1, Math.min(page, sessionStorage['curr_page']));
			if (page > 1) {
				if (sessionStorage['curr_page'] != 1) {
					addPageBtn("<", sessionStorage['curr_page'] - 1)
				}
				var from = Math.max(1, sessionStorage['curr_page'] - 3);
				var to = Math.min(from + 9, page);
				for (var i = from; i < Math.min(from + 9, to); i++)
				{
					var btn = addPageBtn(i, i);
					if (i == sessionStorage['curr_page'])
						btn.style="color:red;"
				}
				if (sessionStorage['curr_page'] != page) {
					addPageBtn(">", parseInt(sessionStorage['curr_page']) + 1)
				}
			}
			document.title = '论文列表';
		}

		$(document).ready(function() {
			var ajaxurl = '<?= admin_url('admin-ajax.php'); ?>';
			function search(){
				if (document.getElementById('searchtext').value == "") {
					document.getElementById('searchtext').value = sessionStorage['lastChoice'] ? sessionStorage['lastChoice'] : "";
					sessionStorage['lastChoice'] = "";
				}
				$.ajax({
					
					type:'post',
					url:ajaxurl,
                	data:{'action':'keyword_search',
					  	'kwd':document.getElementById('searchtext').value.trim(),
					  	'except':sessionStorage['except'],
						 'curr_page':sessionStorage['curr_page']},
                	cache:false,
                	dataType:'json',
                	success:succ,
                	error:function(data){
						console.log("error")
					}
					
				});
				
				return false;
			}	
			document.getElementById('searchbtn').onclick = search;
			search();
		});
</script>
</body>
</html>