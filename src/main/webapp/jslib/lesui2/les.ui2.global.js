/**
 *
 * @class $Global(全局函数)
 */


//------------页面全局参数----------------start-----------
$.extend({
	// 全局配置参数
	/**
	 * 页面全局参数,等同于,$.GLOBALCONFIG.变量名
	 * <p>页面的全局参数，由&lt;inc:head&gt;设置好，页面中可以取参数使用</p>
	 * @method $.globalConfig 
	 * @param name {string} 变量名
	 * @return {string} 变量值
	 * @example
	 *	//
	 *	$.globalConfig('dateformat'); //return 'yyyy-MM-dd HH:mi:ss'
	 *	$.GLOBALCONFIG.dateformat; //= 'yyyy-MM-dd HH:mi:ss'
	 *	$.GLOBALCONFIG.basePath;//项目完全基路径，http://127.0.0.1:8080/lesusp2
	 *	$.GLOBALCONFIG.ctxpath; //应用名称, webapp的名称,如上例的lesusp2
	 */
	GLOBALCONFIG: {
		// 默认日期时间格式
		'dateformat'        : 'yyyy-MM-dd HH:mi:ss',
		// 应用名称, 类似 /lylss
		'ctxpath'           : '',
		// 项目完全基路径
		'basePath'          : 'http://127.0.0.1:8080/les-sysm/',
		// 文件服务器上传路径
		'fileServerBasePath': '',
		// 文件上传 ACTION
		'uploadFileAction'  : '',
		// 文件下载 ACTION
		'downloadFileAction': '',
		// 输出图片的 ACTION
		'outputImgAction'   : '',
		// 默认图片路径
		'defaultImg'        : '',
		// 发生错误时显示的图片路径
		'errorImg'          : '',
		// 导入报盘文件 action
		'uploadBizFile': '',
		// 程序跳转 action
		'gotoURLAction': '',
		//grid的actionType默认方式
		'gridDefaultType':'opensql',
		'lesweb':'',
		'AppWebSocketSVR':''
	},
	
	//配置全局参数
	globalConfig: function ( conf ) {
		var val, globalConf, L;
		if ( !$.isPlainObject( conf ) ) {
			if (typeof conf == 'string'){
				return $.GLOBALCONFIG[conf];
			}
			return;
		}
		globalConf = $.GLOBALCONFIG;
		L = $.Lang;
		// 遍历全局配置参数, 查看传入的参数对象中与全局配置参数相同的属性, 并进行赋值
		$.each( globalConf, function ( name, ele ) {
			//conf.hasOwnProperty( name ) && L.isString( val = conf[ name ] ) 
			//&& val.length && ( globalConf[ name ] = val );
			if(conf.hasOwnProperty( name )){
				val = conf[ name ];
				globalConf[ name ] = val;
			}
		} );
	},
	/**
	 * 取GUID唯一号
	 * @method $.getGuid
	 * @return id {string} 返回32位唯一随机号
	 * @example
	 *	//
	 *	var id = $.getGuid();
	 *	
	 */
	getGuid: function(){
		var uuid = false;
		$.ajax({
			url: 'sqlmap/getuuid',
			type: 'POST',
			data:{},
			dataType:'json',
			async: false,
			success: function(data){
				if (data.uuid) uuid = data.uuid;
				else{
					alert("生成guid异常!");
					throw new Error();
				}
			},
			error: function(){
				alert("生成guid异常!");
				throw new Error("生成guid异常!");
			}
		});
		return uuid;
	},
	/**
	 * 获取浏览器视口大小( 不包含滚动条 ), 实现参考 jQuery-1.4.4 / YUI dom-screen
	 * <p>通过获取 document.body/document.documentElement 对象的客户区大小来获取</p> 
	 * @method $.getViewportSize
	 * @param {object} window对象
	 * @return {object} 返回一个表示浏览视口尺寸的对象 { w: 123, h: 456 }
	 */
	getViewportSize: function ( win ) {
		var doc;
		// 如果传入的不是一个 window 对象, 则直接返回视口尺寸大小为 0 的对象
		if ( !$.isWindow( win ) ) {
			return {
				w: 0,
				h: 0
			};
		}
		doc = win.document;
		// 通过获取 document.body/document.documentElement 对象的客户区大小来获取
		// 视口的尺寸, 严格模式下使用 document.documentElement 对象来获取
		// 混杂模式下使用 document.body 对象来获取
		if ( doc.compatMode === 'CSS1Compat' ) {
			return {
				w: doc.documentElement.clientWidth,
				h: doc.documentElement.clientHeight
			};
		}
		return {
			w: doc.body.clientWidth,
			h: doc.body.clientHeight
		};
	},
	/**
	 * 获取浏览器窗口滚动条位置
	 * <p>除了 IE <=8 以外的浏览器, 其它浏览器都能用 window.pageXOffset/window.pageYOffset 来获取浏览器窗口滚动条位置
	 *    但是在所有浏览器中也可以通过 scrollLeft/scrollTop 来获取, 有一点要注意, 
	 *    在严格模式下是通过查询 document.documentElement(html) 节点的 scrollLeft/scrollTop 属性来获取
	 *    在混杂模式下是通过查询 document.body(body) 节点的 scrollLeft/scrollTop 属性来获取的
	 * </p>
	 * 
	 * @method $.getScrollOffsets
	 * @param win {object} window 对象
	 * @return {object} 返回一个包含滚动位置的对象, { x: 345, y: 123 }
	 */
	getScrollOffsets: function ( win ) {
		var doc;
		// 如果传入的 win 不是 window 对象, 则直接返回滚动条位置为 0 的对象
		if ( !$.isWindow( win ) ) {
			return {
				x: 0,
				y: 0
			};
		}
		// 优先查询当前浏览器是否支持 pageXOffset/pageYOffset 属性
		// IE <= 8 不支持, 其它现代浏览器都支持
		if ( 'pageXOffset' in win ) {
			return {
				x: win.pageXOffset,
				y: win.pageYOffset
			};
		}
		doc = win.document;
		// 对于不支持 pageXOffset/pageYOffset 属性的浏览器, 还可以通过
		// 查询 scrollLeft/scrollTop 属性来获取滚动条的位置
		// 在严格模式下通过查询 document.documentElement(html) 节点的 scrollLeft/scrollTop 属性来获取
		// 在混杂模式下通过查询 document.body(body) 节点的 scrollLeft/scrollTop 属性来获取
		if ( doc.compatMode === 'CSS1Compat' ) {
			return {
				x: doc.documentElement.scrollLeft,
				y: doc.documentElement.scrollTop
			};
		}
		return {
			x: doc.body.scrollLeft,
			y: doc.body.scrollTop
		};
	}
});
//------------页面全局参数----------------end--------------------------------------------------
//--------------------------------图片载入错误-----------------------start---------------------
;( function ( win, $ ) {
	
	// 当图片载入错误时, 触发 onerror 事件, 调用绑定的事件处理函数
	function imgErrorHandler ( p ) {
		showErrorImg( p.self, $.GLOBALCONFIG.ctxpath + '/' + $.GLOBALCONFIG.errorImg );
	}
	
	// 显示配置的错误图片
	function showErrorImg ( self, imgSrc ) {
	
		// 防止当错误图片无法载入时, 又触发 onerror 事件重复执行, 导致 IE stack overflow
		self.onerror = null;
		self.src = imgSrc;
	}
	
	win.imgErrorHandler = imgErrorHandler;
	
} )( window, jQuery );
//--------------------------------图片载入错误-----------------------end-------------
//
//---------------------- 对IE一些交互特征进行设置----------start----------------------
(function (window, $) {
	//对IE进行设置
	if ($.browser.msie) {
		function $IE_Help () {
			return false;
		}
		window.onhelp = $IE_Help; //屏蔽F1帮助 
		//var $forbidRC = true;
		function $IE_KeyDown () { 
			//屏蔽鼠标右键、Ctrl+n、shift+F10、F5刷新、退格键
			if ((window.event.altKey) &&
			    ((window.event.keyCode == 37) ||   //屏蔽 Alt+ 方向键 ←
			     (window.event.keyCode == 39))) {  //屏蔽 Alt+ 方向键 →
			   alert("不准使用ALT+方向键前进或后退网页！");
			   event.returnValue = false;
			}
			//换页键
			if (event.keyCode == 166 ||
			        event.keyCode == 167) {
			     event.keyCode = 0;
			     event.returnValue = false;
			}   
			if(event.keyCode == 8){ //退格键
				if (event.srcElement.type != "text" && 
					event.srcElement.type != "textarea" && 
					event.srcElement.type != "password")
				{
				    event.keyCode = 0;
				    event.returnValue = false;	
			    } else { //只读的文本退格
					if ($(event.srcElement).attr('readonly')) {
						event.keyCode = 0;
						event.returnValue = false;
					}
				}
			}
			if ((event.ctrlKey && event.keyCode == 82)) { //Ctrl + R
			   event.keyCode = 0;
			   event.returnValue = false;
			}
			if ((event.ctrlKey) && (event.keyCode == 78)){   //屏蔽 Ctrl+n
			   event.returnValue = false;
			}
			if (window.event.srcElement.tagName == "A" && (window.event.shiftKey || window.event.ctrlKey)) { 
			    window.event.returnValue = false;  //屏蔽 shift 加鼠标左键新开一网页
			}
			if ((window.event.altKey) && (window.event.keyCode == 115)) { //屏蔽Alt+F4
			    window.showModelessDialog("about:blank","","dialogWidth:1px;dialogheight:1px");
			    return false;
			}
			if (window.event.keyCode>=112 && window.event.keyCode<=123) { //屏蔽Fn
			   window.event.keyCode = 0;
			   window.event.returnValue = false;      
			} 
			//if(window.event.keyCode == 70 && window.event.shiftKey && event.ctrlKey) {
			//		$forbidRC = !$forbidRC;
			//}
		} 
		function $IE_lclick () { 
		 if (window.event.shiftKey) {
		      window.event.returnValue = false;  //屏蔽 shift 加鼠标左键新开一网页
		  } 
		} 
		document.onkeydown = $IE_KeyDown;
		document.onclick = $IE_lclick;
		//document.oncontextmenu = function () { if ($forbidRC) {return false;} };
	}
	//
})(window, jQuery);
//------------------- 对IE一些交互特征进行设置----------end----------------------
//
//---------------ajax----调用封装------------------start------------------------
//递归遍历对象、数组
function traversalObject(obj)
{
    for (var n in obj) {
        if (typeof (obj[n]) == "object") {
        	if(obj[n] instanceof Date){
        		obj[n]=mini.formatDate(obj[n],"yyyy-MM-dd HH:mm:ss");
        	}else{
	            traversalObject(obj[n]); //递归遍历
        	}
        }else if(typeof obj[n] == "string"){
			obj[n]=obj[n].trim(" ");
		}
    }
} 
$.extend({
	
	callJson:function(p){
		this.ops = $.extend({
			dataType: 'json',
			type: 'post',
			url:'',
			async:true,
			data:{}
        }, p);
		/**
		*循环遍历对象，将日期格式化字符串
		*/
		var obj = this.ops.data;
		if((typeof obj=='object')&&(typeof traversalObject=='function')){
			traversalObject(obj);
		}
		$.ajax({
			dataType: this.ops.dataType,
			type: this.ops.type,
			url:this.ops.url,
			data:obj,
			async:this.ops.async,
			beforeSend: function (xhr) {
				if (p.showWaiting){
					 mini.mask({
				            el: document.body,
				            cls: 'mini-mask-loading',
				            html: '加载中...'
				        });
				}
			},
			complete: function (xhr, ts) {
				if (p.showWaiting) {
					 mini.unmask(document.body);
				}
			},
			error: function(xhr, ts, e){
				if(p.showError == undefined || p.showError){
					var name = "";
					if (p.name) name = '['+p.name+']';
						mini.alert('调用Ajax服务'+name+'出错!\n可能原因:\n	■与服务器的网络中断\n	■找不到请求的页面['+this.url+']');
					if (p.fail) p.fail(ts, true);
					else if (typeof p.error == 'function') p.error(xhr, ts, e); 
				}
			},
			success: function (data) {
				if (data) {
					if (data.errorCode) {
						var msg = "未知业务异常["+data.errorCode+"]";
						if (data.errorMsg) msg = data.errorMsg;
						if(p.showError == undefined || p.showError == true){
							mini.alert(msg);
						}
						if (p.fail) p.fail(data,false);
						else if (typeof p.error == 'function') p.error(data,false); 
						return;
					}
				}
				if (typeof p.ok=='function') p.ok(data); 
				else if (typeof p.success == 'function') p.success(data,false); 
			}
		})
	},
	
	/**
	 * $.ajax函数的封装，可以设置遮罩效果
	 * @method $.callAjax
	 * @param options {object} 参数对象
	 * @example
	 *	//
	 *	$.callAjax({
	 *		url: 'jspaction/example',
	 *		data: {name:'val'},
	 *		ok: function(data){},
	 *		err: function(err){}
	 *	});
	 */
	callAjax: function (p) {
		if(!$.isPlainObject(p.data)) p.data = {};
		//转换JspAction参数,需要json2.js
		if (p.url){
			if (p.url.indexOf("jspaction/")>=0)
			{
				p.data = {jspactionreqjson: JSON.stringify(p.data || {})};
			}else if (p.url.indexOf('workflow/')>=0)
			{
				p.data = {workflowreqjson: JSON.stringify(p.data || {})};
			}
		}
		$.ajax($.extend( {
			dataType: 'JSON',
			type: 'post',
			beforeSend: function (xhr) {
				if (p.showWaiting){
					if (top.$showwaiting) top.$showwaiting();
					else if($.ShowWaiting) $.ShowWaiting();
				}
			},
			complete: function (xhr, ts) {
				if (p.showWaiting) {
					if (top.$hidewaiting) top.$hidewaiting();
					else if ($.HideWaiting) $.HideWaiting();
				}
			},
			error: function(xhr, ts, e){
				var name = "";
				if (p.name) name = '['+p.name+']';
				alert('调用Ajax服务'+name+'出错!\n可能原因:\n	■与服务器的网络中断\n	■找不到请求的页面['+this.url+']');
				if (p.fail) p.fail(ts, true);
				else if (typeof p.err == 'function') p.err(ts, true); 
			},
			success: function (data) {
				if (data) {
					if (data.errorCode) {
						var msg = "未知业务异常["+data.errorCode+"]";
						if (data.errorMsg) msg = data.errorMsg;
						alert(msg);
						if (p.fail) p.fail(data,false);
						else if (typeof p.err == 'function') p.err(data,false); 
						return;
					}
				}
				if (typeof p.ok == 'function') p.ok(data); 
			}
		}, p));
	},
	/**
	 * 调用返回二进制或文件结果的请求
	 * @method $.callFile
	 * @param url {string} 请求
	 * @param data {object} 参数
	 * @example
	 *	//
	 *	$.callFile('jspaction/example?_resultType=csv&_resultFilename=1.csv',{name:'val'});
	 *	$.callFile({fid:'wrqewrqrqewr'}); //使用框架中默认的文件下载功能  使用fid下载lesys_file中的附件
	 *	//
	 */
	callFile: function(url, data)
	{
		var html = [];
		var $form = $('#lesfiledownform');
		if ($form.length<1)
		{
			$('body').append('<iframe style="display:none" id="lesfiledownf" name="lesfiledownf"></iframe><form id="lesfiledownform" style="display:none;" action="" method="post" target="lesfiledownf"></form>');
			$form = $('#lesfiledownform');
//			data = arguments[0];
//			url = false;
		}
		if (arguments.length==1 && $.isPlainObject(arguments[0]))
		{
			data = arguments[0];
			url = false;
		}
		if (url===undefined || !url){
			if ($.GLOBALCONFIG.downloadFileAction) url = $.GLOBALCONFIG.downloadFileAction;
			else url = 'file/filedownload';//使用框架中默认的文件下载功能  使用fid下载lesys_file中的附件
		}
		if (data)
		{
			//转换JspAction参数,需要json2.js
			if (url.indexOf("jspaction/")>=0)
			{
				data = {jspactionreqjson: JSON.stringify(data || {})};
			}
			for(var n in data)
			{
				var _v = data[n];
				if (typeof _v == 'string') html.push('<input type="hidden" name="'+n+'" value=""/>');
			}
		}
		//url中的参数转为post
		var pos = url.indexOf('?');
		if (pos>1)
		{
			var paramstr = url.substring(pos+1);
			var params = paramstr.split('&');
			for(var i=0;i<params.length;i++)
			{
				var param = params[i].split('=');
				if (param.length>1)
				{
					html.push('<input type="hidden" name="'+param[0]+'" value=""/>');
					data[param[0]] = param[1];
				}
			}
			url = url.substring(0, pos);
		}
		$form.attr('action', url).empty().append(html.join(''));
		$form.find(':input').each(function(i){
			var $this = $(this);
			$this.val(data[$this.attr('name')]); //有引号的问题
		});
		$form.submit();
		return true;
	},
	/**
	 * 将请求的结果按word模板格式导出,word模板中的${name}占位符用数据中name对应的值替换
	 * @method $.callDoc
	 * @param ops {object} 参数
	 * @example
	 *	//
	 *	$.callDoc({
	 *		url:'jspaction/example', //执行返回数据的action
	 *		fileName:'xxx123.doc', //保存下载的文件名
	 *		templateUrl:'pages/xjjy/zy/xxx.doc',//模板文件路径全名
	 *		data: {} //参数
	 *	};
	 */
	callDoc: function(ops)
	{
		if (ops && ops.url)
		{
			var url = ops.url;
			var data = ops.data || {};
			var tdoc = ops.templateUrl || '';
			data['_resultFilename'] = ops.fileName || '未命名.doc';
			if (url.indexOf('?')>0){
				url += '&_resultTempfile=' + tdoc;
			}else{
				url += '?_resultTempfile=' + tdoc;
			}
			//
			$.callFile(url, data);
			//
		}else{
			throw new Error('callDoc参数错误');
		}
	},
	/**
	 * 将请求的结果按EXCEL模板格式导出,EXCEL模板中使用#foreach r in ${mx} 取行list数据，列${r.col1}取数据
	 * @method $.callXls
	 * @param ops {object} 参数
	 * @example
	 *	//
	 *	$.callXls({
	 *		url:'jspaction/example', //执行返回数据的action
	 *		fileName:'xxx123.xls', //保存下载的文件名
	 *		templateUrl:'pages/xjjy/zy/1.xls',//模板文件路径全名
	 *		data: {} //参数
	 *	};
	 */
	callXls: function(ops)
	{
		if (ops && ops.url)
		{
			var url = ops.url;
			var data = ops.data || {};
			var tdoc = ops.templateUrl || '';
			data['_resultFilename'] = ops.fileName || '未命名.xls';
			if (url.indexOf('?')>0){
				url += '&_resultTempfile=' + tdoc;
			}else{
				url += '?_resultTempfile=' + tdoc;
			}
			//
			$.callFile(url, data);
			//
		}else{
			throw new Error('callXls参数错误');
		}
	},
	/**
	 * Ajax文件上传函数
	 * @method $.ajaxFileUpload
	 * @param ops {object} 参数
	 * @example
	 *	//
	 *	$.ajaxFileUpload({
	 *		url:'fileservice/fileupload', //上传文件到后台接收文件的服务
	 *		data:{},//参数
	 *		fileElementId:'fileId', //上传文件的input type=file 的id
	 *		beforeSend:function(){}, //上传开始事件（用于进度动画等特效）
	 *		success:function(f){alert(f.fid);}//上传成功后执行，返回文件信息f.fileName,f.fileSize,f.fileType
	 *		error:function(errmsg){alert(errmsg);} //上传出错
	 *		complete:function(){}, //上传结束事件
	 *		timeout: 30//设置上传超时时间（秒）
	 *	});
	 *	//
	 */
	ajaxFileUpload: function(ops) 
	{
		var id = new Date().getTime();
		var frameId = 'fuiframe' + id;
		var formId = 'fuform' + id;
		var dataCnt = 0;
		var htmls = [];
		htmls.push('<iframe id="' + frameId + '" name="' + frameId + '" style="position:absolute;top:-999px;left:-999px;width:10px;height:10px;"');
		if(window.ActiveXObject) //
		{
			htmls.push(' src="javascript:false;"');
		}
		htmls.push('></iframe>');
		htmls.push('<form style="position:absolute;top:-999px;left:-999px;width:10px;height:10px;" action="" method="POST" name="' + formId + '" id="' + formId + '" enctype="multipart/form-data">');
		if(ops.data)
		{
			for(var n in ops.data)
			{
				var _v = ops.data[n];
				if (_v && (typeof _v == 'string' || typeof _v == 'number') ) //数值传不了的bug
				{
					htmls.push('<input type="hidden" name="' + n + '" value="" />');
					dataCnt ++;
				}
			}
		}
		htmls.push('</form>');
		$('body').append(htmls.join(''));
		var $form = $('#'+formId);
		if (dataCnt>0)
		{
			$form.find(':input').each(function(i){
				var $this = $(this);
				$this.val(''+ops.data[$this.attr('name')]);
			});
		}
		//把原FILE元素移动表单中，复制一份放到原来，原file中已有的文件名是复制不了的
		var $fileElement = $('#' + ops.fileElementId);
		var $copyFileElement = $fileElement.clone(true);
		$fileElement.attr('id', 'fufile'+id);
		$fileElement.before($copyFileElement);
		$form.append($fileElement);
		// 
		if ( $.isFunction(ops.beforeSend)) ops.beforeSend();           
		var requestDone = false;
		var responseJson = false;
		var uploadCallback = function(isTimeout)
		{
			var errmsg = '上传文件失败:';
			requestDone = true;
			if (isTimeout == "timeout"){
				if ( $.isFunction(ops.complete)) ops.complete();
				errmsg += '请求超时['+ops.timeout+']';
				if ( $.isFunction(ops.error)) ops.error(errmsg);  
				else{
					alert(errmsg);
					throw new Error(errmsg);
				}
				return false;
			}
			//
			var frameDom = document.getElementById(frameId);
			try{
				var childWin = frameDom.contentWindow || frameDom.contentDocument;
				
				if( childWin && $.isFunction (childWin._uploadComplete)){
				
					responseJson = childWin._uploadComplete();
				}
			}
			catch(e)
			{
				responseJson = false;
				errmsg += '未知异常('+e+')';
			}
			if (responseJson && responseJson.error)//java后台异常
			{
				errmsg = responseJson.errorMsg;
				responseJson = false;
			}
			if (!responseJson) 
			{
				if ( $.isFunction(ops.complete)) ops.complete();
				if ( $.isFunction(ops.error)) ops.error(errmsg);  
				else{
					alert(errmsg);
					throw new Error(errmsg);
				}
				return false;
			}
			if ( $.isFunction(ops.success) ) ops.success( responseJson );
			$(frameDom).unbind();
			setTimeout(function(){
				$('#'+frameId).remove();
				$('#'+formId).remove();
			}, 100);
			if ( $.isFunction(ops.complete)) ops.complete();  
		}
		// Timeout checker
		if ( ops.timeout > 0 ) 
		{
			setTimeout(function(){
				if( !requestDone ) uploadCallback( 'timeout' );
			}, ops.timeout * 1000);
		}
		$form.attr('action', ops.url).attr('target', frameId);
		$('#' + frameId).load(uploadCallback);
		$form.submit();
	}
});
//---------------ajax----调用封装------------------end------------------------

//表单回车--------------------------------------------------------------------------start
//del by szh 2016.2.2 水科院不要 回车 textarea有问题
//表单回车--------------------------------------------------------------------------end


//-----------------------------------------------OCX----------------------------------------//
(function(){var e=19;var d="lib/ocx/";var b=10;function c(m,g,l,f,h){this.$ocx=false;var j=g.indexOf(".");if(!l){if(j>0){l=g.substr(0,j)}else{l=g}}var k=$("#"+l);if(k.length<1){var i=m+","+d;if($.browser.msie){f.append('<object id="'+l+'" style="'+h+'" classid="clsid:B32500F9-66AB-4C93-A352-92847096A0CD"><param name="bean" value="'+g+'"/><param name="path" value="'+i+'"/></object>')}else{f.append('<embed id="'+l+'" style="'+h+'" type="application/x-leswdl-plugin" path="'+i+'" bean="'+g+'" />')}k=$("#"+l)}this.$ocx=k.get(0);this.basePath=m;if(this.basePath.substr(this.basePath.length-1,1)!="/"){this.basePath+="/"}}c.prototype.init=function(){try{var f=this.$ocx.getv();if(f<e){this.$ocx=false;a("<h3>本网页部分功能需要使用到的浏览器安全插件版本需要升级,请重新安装</h3>",this.basePath+"lib/ocx/install.html")}}catch(g){this.$ocx=false;var h="<h3>本网页部分功能需要使用到浏览器安全插件,请安装</h3>";if($.browser.msie){h+='<h4 style="color:#f44;border:0 none;margin:8px;">如果已经安装过，还出现本页面，请注意IE浏览器是否提示运行控件，请允许运行后，关闭IE浏览器重新打开即可!</h4>'}else{h+='<h4 style="color:#f44;border:0 none;margin:8px;">如果已经安装过，还出现本页面，请注意安装后浏览器是否关闭，关闭浏览器重新打开插件方可生效!</h4>'}a(h,this.basePath+"lib/ocx/install.html")}};function a(j,i){var g=$("#wdlsetup");if(g.length<1){var f=[];f.push('<table id="wdlsetup"  style="position:absolute;top:0px;left:0px;border:4px solid #e00;margin:4px;width:98%;height:98%;background-color:#ccf;font-family: 微软雅黑, 宋体;font-size:12pt"><tbody>');f.push('<tr style="height:12%;"><td style="width:32px;"></td><td align="center" style="">'+j+'</td><td style="width:32px"></td></tr>');f.push('<tr style="height:88%;"><td align="center" colspan="3"><iframe frameborder="1" width="99%" height="99%" src="'+i+'"/></td></tr>');f.push("</tbody></table>");$("body").append(f.join(""));g=$("#wdlsetup")}g.fadeTo(200,0.95)}$.Ocx=function(j,g){var f,h;if(arguments.length==1){f=false;h=arguments[0];if($.GLOBALCONFIG){if($.GLOBALCONFIG.basePath){f=$.GLOBALCONFIG.basePath}}}else{f=j;h=g}if(!f||f.length<1){alert("无法创建$.Ocx对象,basePath参数为空");return false}var i=new c(f,h,false,$("body"),$.browser.msie?"display:none;":"position:absolute;width:1px;height:1px");i.init();return i.$ocx};$.OcxCtrl=function(f){b++;var g=$.extend({basePath:false,id:"ocxc"+b,append:$("body"),style:"border:0 none;display:block;width:100%;height:100%;",initParam:""},f);if(!g.basePath||g.basePath.length<1){if($.GLOBALCONFIG){if($.GLOBALCONFIG.basePath){g.basePath=$.GLOBALCONFIG.basePath}}if(!g.basePath||g.basePath.length<1){alert("无法创建$.OcxCtrl对象,basePath参数为空");return false}}if(!g.name||g.name.length<1){alert("无法创建$.OcxCtrl对象,名称为空");return false}var h=new c(g.basePath,g.name,g.id,g.append,g.style);h.init();h.$ocx.loadlib(g.initParam);return h.$ocx}})();
