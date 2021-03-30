<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>template</title>
<link href="<?= bloginfo('template_url'); ?>/quickfind/QuickFind.css" rel="stylesheet" type="text/css">
<script>
	function checkboxfunc(){
			document.getElementById('deletebtn').style.backgroundColor='#D60104';
			}
		
	function jump(a){
		alert(a);
	}
	</script>
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
			<input type="button" class="button" id="searchbtn" value="搜索">
	    </form>
    </div>
		
		
		
	<div class="middle">
		<div class="texttitle" id="paper_template" style="display:none;">
			<input type="checkbox" class="checkbox" name="text" value="" onclick="checkboxfunc();" />
			<p class="list" onclick="jump('x');"></p>	
		</div>
		<form id="paperlist">
	    </form>
    </div>
		
	<div class="down">
		<div class="delete">
		  <form >
			  <input type="button" class="deletebtn" id="deletebtn" value="删除">
		  </form>
	    </div>
		<div class="pagelist">
			<a href="#">«</a>
			<a href="#">1</a>
			<a href="#">2</a>
			<a href="#">3</a>
			<a href="#">4</a>
			<a href="#">5</a>
			<a href="#">6</a>
	 		<a href="#">»</a>
			
	    </div>
    </div>
	
</div>

<script src=<?= bloginfo('template_directory').'/quickfind/jquery.min.js'; ?>></script>
<script>
		function succ(result){
        	var childs=document.getElementById('paperlist').childNodes;  
			for(var i=childs.length-1;i>=0;i--){  
				document.getElementById('paperlist').removeChild(childs.item(i));  
			}
			for(var i=0;i<result.length;i++){
				var paper = document.getElementById('paper_template').cloneNode(true);
				paper.style = '';
				paper.getElementsByTagName('p')[0].innerHTML = result[i].title;
				document.getElementById('paperlist').appendChild(paper);
			}
        }
		$(document).ready(function() {
		var ajaxurl = '<?= admin_url('admin-ajax.php'); ?>';
		document.getElementById('searchbtn').onclick = function(searchbtn_event){     
			$.ajax({
				type:'post',
				url:ajaxurl,
                data:{'action':'keyword_search',
					  'kwd':document.getElementById('searchtext').value.trim(),
					  'except':sessionStorage['except']},
                cache:false,
                dataType:'json',
                success:succ,
                error:function(data){
					console.log("error")
				}
			});
			return false;
			};
		});
</script>
</body>
</html>