<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>template</title>
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
			 <form><input type="text" class="message" name="title" readonly="readonly"></form>
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
</html>
