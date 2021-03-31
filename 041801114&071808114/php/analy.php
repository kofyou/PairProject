<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<title>加载中...</title>
	<link href="<?= bloginfo('template_url'); ?>/quickfind/QuickFind.css" rel="stylesheet" type="text/css">
</head>
<script>
		sessionStorage['lastChoice'] = "";
</script>
<body>
	<div class="nav">
		<ul class="template">
			<li><a href="index" id="navIcon"></a>
			</li>
			<li><a href="index">论文列表</a>
			</li>
			<li><a href="analy">趋势分析</a>
			</li>
			<li><a href="about">关于我们</a>
			</li>
		</ul>
	</div>
	<div class="main">
		<div id="hotWordContainer"></div>
		<script src="<?= bloginfo('template_url'); ?>/quickfind/echarts.min.js"></script>
		<script src="<?= bloginfo('template_url'); ?>/quickfind/echarts-wordcloud.min.js"></script>
		<script type="text/javascript">
			var wc_chart = echarts.init( document.getElementById( 'hotWordContainer' ) );
		</script>

		<div Id="container"></div>

		<script type="text/javascript">
			var myChart = echarts.init( document.getElementById( 'container' ) );
			var option = {
				title: {
					text: '顶会热词热度走势图'
				},
				tooltip: {
					trigger: 'axis'
				},
				legend: {
					data: [ 'CVPR', 'ICCV', 'ECCV' ]
				},
				grid: {
					left: '3%',
					right: '4%',
					bottom: '3%',
					containLabel: true
				},
				toolbox: {
					feature: {
						saveAsImage: {}
					}
				},

				yAxis: {
					type: 'value'
				},

			};
		</script>
	</div>
</body>
<script src=<?= bloginfo( 'template_directory'). '/quickfind/jquery.min.js'; ?>>
</script>
<script>
	$( document ).ready( function () {
		var ajaxurl = '<?= admin_url('admin-ajax.php '); ?>';
		$.ajax( {
			type: 'post',
			url: ajaxurl,
			data: {
				'action': 'wordcloud_process',
				'except': sessionStorage[ 'except' ]
			},
			cache: false,
			dataType: 'json',
			success: function ( result ) {
				var wc_option = {
					title: {
						text: '热门领域'
					},
					tooltip: {},
					series: [ {
						type: 'wordCloud',
						gridSize: 2,
						sizeRange: [ 12, 50 ],
						// rotationRange: [-90, 90],
						rotationRange: [ -50, 50 ],
						shape: 'triangle',
						textStyle: {
							normal: {
								color: function () {
									return 'rgb(' + [
										Math.round( Math.random() * 160 ),
										Math.round( Math.random() * 160 ),
										Math.round( Math.random() * 160 )
									].join( ',' ) + ')';
								}
							},
							emphasis: {
								shadowBlur: 10,
								shadowColor: '#333'
							}
						},
						data: []
					} ]
				};

				for ( var i = 0; i < result.length; i++ ) {
					wc_option.series[ 0 ].data.push( {
						name: result[i].word,
						value: result[i].cnt
					} );
				}
				wc_chart.setOption( wc_option );
				wc_chart.resize();
				document.title = '趋势分析';
				wc_chart.on( 'click', ( param ) => {
					if (sessionStorage['lastChoice'] == param.name)
					{
						window.location.href = "http://blog.tozzger.info/quickfind/index";
					}
					sessionStorage['lastChoice'] = param.name;
					$.ajax({
					
					type:'post',
					url:ajaxurl,
                	data:{'action':'keyword_count',
					  	'kwd':param.name,
					  	'except':sessionStorage['except'],},
                	cache:false,
                	dataType:'json',
                	success:function(result){
						
						var set = new Set();
						for(var i = 0; i < result.length;i++){
							set.add(result[i].year);
						}
						var list = Array.from(set).sort();
						
						option.xAxis= {
					type: 'category',
					boundaryGap: false,
					data: list
					};
					var cvpr = {
					name: 'CVPR',
					type: 'line',
					stack: '总量1',
					data: new Array(list.length).fill(0, 0, list.length)
					}
					var iccv = {
					name: 'ICCV',
					type: 'line',
					stack: '总量2',
					data: new Array(list.length).fill(0, 0, list.length)
					}
					var eccv = {
					name: 'ECCV',
					type: 'line',
					stack: '总量3',
					data: new Array(list.length).fill(0, 0, list.length)
					}
					
					var map = new Array();
					for(var i = 0; i < list.length; i++){
						map[list[i]] = i;
					}
					
					for(var i = 0; i < result.length;i++){
						var year = result[i].year;
						var meeting = result[i].meeting;
						var count = parseInt(result[i].cnt);
						switch(meeting){
							case 'CVPR':
								meeting = cvpr;
								break;
							case 'ICCV':
								meeting = iccv;
								break;
							case 'ECCV':
								meeting = eccv;
								break;
						}
						meeting.data[map[year]] = count;
					}
					
					option.series= [ cvpr, iccv, eccv ];

					myChart.setOption( option );
					},
                	error:function(data){
						console.log("error")
					}
				});
				} );

			},
			error: function ( data ) {
				console.log( "error" )
				document.title = '趋势分析';
			}
		} );
		return false;
	} );
</script>
</html>