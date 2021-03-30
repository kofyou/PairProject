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
  <div class="page">
	 <div class="message" id="pagemessage">
	   <p>标题</p>
			 <textarea class="mutitext"  id="title" rows="5"  readonly="readonly"></textarea>
	   <p>摘要</p>
			 <textarea class="mutitext"  id="abstract" rows="5"  readonly="readonly"></textarea>
	   <p>关键词</p>
			 <textarea class="mutitext" id="keyword" rows="5"  readonly="readonly"></textarea>
	   <p>原文链接</p>
			 <textarea class="mutitext" id="pagelink" rows="5"  readonly="readonly"></textarea>
     </div>
  </div>
</div>
</body>
<script src=<?= bloginfo('template_directory').'/quickfind/jquery.min.js'; ?>></script>
<script>
$(document).ready(function() {
			var ajaxurl = '<?= admin_url('admin-ajax.php'); ?>';  
			$.ajax({
				type:'post',
				url:ajaxurl,
                data:{'action':'paper_detail',
					  'paper_id':sessionStorage['paper_id']},
                cache:false,
                dataType:'json',
                success:function(result){
					document.getElementById('title').innerHTML = result.title;
					document.getElementById('abstract').innerHTML = result.abstract;
					document.getElementById('keyword').innerHTML = result.keywords;
					document.getElementById('pagelink').innerHTML = result.url;
					document.title = result.title;
				},
                error:function(data){
					console.log("error")
				}
			});
			return false;
});
</script>
</html>
