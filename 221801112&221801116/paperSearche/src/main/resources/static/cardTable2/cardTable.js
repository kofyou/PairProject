layui.define(['table', 'laypage','jquery', 'element'], function(exports) {
	"use strict";
	var filePath = layui.cache.modules.cardTable
		.substr(0, layui.cache.modules.cardTable.lastIndexOf('/'));
	// 引入tablePlug.css
	layui.link(filePath + '/cardTable.css');
	var MOD_NAME = 'cardTable',
		$ = layui.jquery,
		element = layui.element,
		laypage = layui.laypage;
	var _instances = {};  // 记录所有实例
	/* 默认参数 */
	var defaultOption = {
		elem: "#currentTableId",// 构建的模型
		url: "cardTable/card.json",// 数据 url 连接
		loading: true,//是否加载
		limit: 2, //每页数量默认是每行数量的双倍
		linenum: 1, //每行数量 2,3,4,6
		currentPage: 0,//当前页
		limits:[],     //页码
		page: true, //是否分页
		layout: ['count', 'prev', 'page', 'next','skip'],//分页控件
		request: {
			pageName: 'start' //页码的参数名称，默认：page
			, limitName: 'limit' //每页数据量的参数名，默认：limit
			, paperId: 'paperId'       //主键名称，默认：id
			//, authors: 'authors'
			, keywords: 'keywords'
			, abstrac: 'abstrac'
			, publicationTitle: 'publicationTitle' //标题名称，默认：title
			, publicationYear: 'publicationYear' //图片地址，默认：image
			, persistentLink: 'persistentLink' //备注名称，默认：remark
		},
		response: {
			statusName: 'code' //规定数据状态的字段名称，默认：code
			, statusCode: 0 //规定成功的状态码，默认：0
			, msgName: 'msg' //规定状态信息的字段名称，默认：msg
			, countName: 'count' //规定数据总数的字段名称，默认：count
			, dataName: 'data' //规定数据列表的字段名称，默认：data
		},
		// 完 成 函 数
		done: function () {

		}
	};
	var card = function(opt) {
		_instances[opt.elem.substring(1)] = this;
		this.reload(opt);
	};
	/** 参数设置 */
	card.prototype.initOptions = function (opt) {
		this.option = $.extend(true, {}, defaultOption, opt);
		if (!this.option.limit || this.option.limit == 0) {
			this.option.limit = this.option.linenum * 2;
		}
		if (!this.option.limits || this.option.limits.length == 0) {
			this.option.limits = [this.option.limit];
        }
	};
	card.prototype.init = function () {
		var option = this.option;
		var url = option.url;
		var html = "";
		html += option.loading == true ? '      <div class="ew-table-loading">' : '      <div class="ew-table-loading layui-hide">';
		html += '         <i class="layui-icon layui-icon-loading layui-anim layui-anim-rotate layui-anim-loop"></i>';
		html += '      </div>';
		$(option.elem).html(html);
		// 根 据 请 求 方 式 获 取 数 据
		html = "";
		if (url != null) {
			if (!!option.page) {
				if(url.indexOf('?')!=-1){
					url = url + '&' + option.request.pageName + '=' + (option.currentPage==0?option.currentPage:option.currentPage-1)*2;
					url = url + '&' + option.request.limitName + '=' + option.limit;
				}else{
					url = url + '?' + option.request.pageName + '=' + (option.currentPage==0?option.currentPage:option.currentPage-1)*2;
					url = url + '&' + option.request.limitName + '=' + option.limit;
				}

			}
			if (!!option.where) {
				for (let key in option.where) {
					url = url + '&' + key + '=' + option.where[key];
				}
            }
			var data = getData(url);
			data = initData(data, option);
			if (data.code != option.response.statusCode) {
				html = "<p>" + data.msg + "</p>";
			}
			else {
				option.data = data.data;
				option.count = data.count;
				// 根据结果进行相应结构的创建
				if (!!option.data && option.data.length > 0) {
					html = createComponent(option.elem.substring(1),option.linenum,data.data);
					html += "<div id='cardpage'></div>";
				}
				else {
					html = "<p>没有数据</p>";
				}
			}
		}
		$(option.elem).html(html);
		if (option.page) {
			// 初始化分页组件
			laypage.render({
				elem: 'cardpage'
				, count: option.count, limit: option.limit, limits:option.limits, curr: option.currentPage
				, layout: option.layout,theme: 'csk'
				, jump: function (obj, first) {
					option.limit = obj.limit;
					option.currentPage = obj.curr;
					if (!first) {
						_instances[option.elem.substring(1)].reload(option);
					}
				}
			});
        }
	}
	card.prototype.reload = function (opt) {
		this.initOptions(this.option ? $.extend(true, this.option, opt) : opt);
		this.init();  // 初始化表格
    }

	function createComponent(elem,linenum,data) {
		var html = "<div class='cloud-card-component'>"
		var content = createCards(elem, linenum,data);
        var page = "";
        content = content + page;
        html += content + "</div>"
        return html;
	}
	/** 创建指定数量的卡片 */
	function createCards(elem, linenum,data) {	
		var content = "<div class='layui-row layui-col-space30'>";
		for (var i = 0; i < data.length; i++) {
			content += createCard(elem, linenum,data[i],i);
        }
		content += "</div>";
		return content;
	}
	/** 创建一个卡片 */
	function createCard(elem, linenum, item, no) {
		var line = 12 / linenum;
		var card =
			// 加入模态面板
			'<div id = "'+item.paperId+'" data-toggle="modal" data-target="#myModal" onclick="cardTableCheckedCard('+elem+',this)"'+'class="layui-col-md'+line+'ew-datagrid-item"'+'data-index="'+no+'"data-number="1">'+
				'<div class="project-list-item">'+
					'<div class="project-list-item-body">'+
							'<h2 style="font-size: 22px" >' + item.publicationTitle + '</h2> '+
							'<div class="project-list-item-text layui-text" style="color: #20c997;font-size: medium">'+
								'<span >' +item.keywords + '</span>' +
							'</div> '+
							'<div class="project-list-item-desc" >' +
								'<span >' +item.abstrac + '</span>' +
								'<span class="time">' +item.publicationYear + '</span>' +
								'<span style="display: none">' +item.persistentLink + '</span>' +
								//'<a href="'+item.persistentLink+'" style="float: right;text-underline: none" >原文链接</a>'+
								' <div class="ew-head-list"></div>' +
							'</div> '+
					'</div >'+
				' </div > '+
			'</div > '
		return card;
	}
	/** 格式化返回参数 */
	function initData(tempData, option) {
		var data = {};
		data.code = tempData[option.response.statusName];
		data.msg = tempData[option.response.msgName];
		data.count = tempData[option.response.countName];
		var dataList = tempData[option.response.dataName];
		data.data = [];
		for (var i = 0; i < dataList.length; i++) {
			var item = {};
			item.paperId = dataList[i][option.request.paperId];
			item.abstrac = dataList[i][option.request.abstrac];
			item.keywords = dataList[i][option.request.keywords];
			item.publicationTitle = dataList[i][option.request.publicationTitle];
			item.publicationYear = dataList[i][option.request.publicationYear];
			item.persistentLink = dataList[i][option.request.persistentLink];
			item.persistentLink = item.persistentLink.replaceAll('"',"");
			data.data.push(item);
		}
		return data;
    }
	/** 同 步 请 求 获 取 数 据 */
	function getData(url) {

		$.ajaxSettings.async = false;
		var data = null;

		$.get(url, function (result) {
			data = result;
		});

		$.ajaxSettings.async = true;
		return data;
	}
	//卡片点击事件
	window.cardTableCheckedCard = function (elem,obj) {
		$(obj).addClass('layui-table-click').siblings().removeClass('layui-table-click');
		var item = {};
		item.persistentLink = $(obj).find('span')[3].innerHTML;
		//数据嵌入表单元素
		window.open(item.persistentLink);
	}
	/** 对外提供的方法 */
	var tt = {
		/* 渲染 */
		render: function (options) {
			return new card(options);
		},
		/* 重载 */
		reload: function (id, opt) {
			_instances[id].reload(opt);
		},
		/* 获取选中参数 */
		getChecked: function (id) {
			 // var option = _instances[id].option;
			 // var data = option.checkedItem;
			 // var item = {};
			 // item[option.request.paperId] = data.paperId;
			 // item[option.request.abstrac] = data.abstrac;
			 // item[option.request.keywords] = data.keywords;
			 // item[option.request.publicationTitle] = data.publicationTitle;
			 // item[option.request.publicationYear] = data.publicationYear;
			 // item[option.request.persistentLink] = data.persistentLink;
			 // return item;
		},
	}
	exports(MOD_NAME, tt);
})
