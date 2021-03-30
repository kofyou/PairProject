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
			wc_chart.on( 'click', ( param ) => {
				alert( param.name );
			} );
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
				xAxis: {
					type: 'category',
					boundaryGap: false,
					data: [ '周一', '周二', '周三', '周四', '周五', '周六', '周日' ]
				},
				yAxis: {
					type: 'value'
				},
				series: [ {
					name: 'CVPR',
					type: 'line',
					stack: '总量',
					data: [ 120, 132, 101, 134, 90, 230, 210 ]
				}, {
					name: 'ICCV',
					type: 'line',
					stack: '总量',
					data: [ 220, 182, 191, 234, 290, 330, 310 ]
				}, {
					name: 'ECCV',
					type: 'line',
					stack: '总量',
					data: [ 150, 232, 201, 154, 190, 330, 410 ]
				}, ]
			};
			myChart.setOption( option );
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

			},
			error: function ( data ) {
				console.log( "error" )
			}
		} );
		return false;
	} );
</script>
</html>