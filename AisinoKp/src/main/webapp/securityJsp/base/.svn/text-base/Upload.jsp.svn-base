<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();%>
<%String contextPath = request.getContextPath();%>
<%
	String id = request.getParameter("id");
	if (id == null) {
		id = "";
	}
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>

<script type="text/javascript">
var uploader=$("#uploader");//上传对象

//-----------------------------------------------
var flash_swf_url = '<%=contextPath%>/jslib/plupload-2.0.0/js/Moxie.swf';
var silverlight_xap_url ='<%=contextPath%>/jslib/plupload-2.0.0/js/Moxie.xap';
var submitNow = function($dialog, $grid, $pjq) {
	var url = sy.contextPath + '/base/fileupload!save.sy';
	$.post(url, sy.serializeObject($('form')), function(result) {
		
		if (result.success) {
			$pjq.messager.alert('提示', result.msg, 'info');
			$grid.datagrid('load');
			$dialog.dialog('destroy');
		} else {
			$pjq.messager.alert('提示', result.msg, 'error');
		}
	}, 'json');
};
var test=function(){};
var submitForm = function($dialog, $grid, $pjq) {
	if ($('form').form('validate')) {
		var uploader = $('#uploader').pluploadQueue();
		if (uploader.files.length > 0) {// 判断队列中是否有文件需要上传
			/*uploader.bind('StateChanged', function() {// 在所有的文件上传完毕时，提交表单
				if (uploader.files.length === (uploader.total.uploaded + uploader.total.failed)) {
					//alert();//问题在这里
				
					submitNow($dialog, $grid, $pjq);
				}
			});*/
			submitNow($dialog, $grid, $pjq);
			uploader.start();
		} else {
			alert('请选择至少一个文件进行上传！');
		}
		
		
	}
};

function sleep(numberMillis) { 
	   var now = new Date();
	   var exitTime = now.getTime() + numberMillis;  
	   while (true) { 
	       now = new Date(); 
	       if (now.getTime() > exitTime)    return;
	    }
	}
	$(function() {
		$("#uploader").pluploadQueue({
			runtimes : 'html5,flash',//设置运行环境，会按设置的顺序，可以选择的值有html5,gears,flash,silverlight,browserplus,html4
			flash_swf_url : flash_swf_url,// Flash环境路径设置
			silverlight_xap_url : silverlight_xap_url,//silverlight环境路径设置
			url : sy.contextPath + '/UploadServlet',//上传文件路径
			max_file_size : '1024mb',//100b, 10kb, 10mb, 1gb
			chunk_size : '10mb',//分块大小，小于这个大小的不分块
			unique_names : true,//生成唯一文件名
			// 如果可能的话，压缩图片大小
			// resize : { width : 320, height : 240, quality : 90 },
			// 指定要浏览的文件类型
			filters : [  {title : 'Txt',extensions : 'txt'},
			             {title : 'Tsv',extensions : 'tsv'},
			             {title : 'xls',extensions : 'xls'}],
			init : {
				FileUploaded : function(up, file, info) {//文件上传完毕触发
					console.info(up);
					console.info(file);
					console.info(info);
					var response = $.parseJSON(info.response);
					if (response.status) {
						$('#f1').append('<input type="hidden" name="fileUrl" value="'+response.fileUrl+'"/>');
						$('#f1').append('<input type="hidden" name="fileName" value="'+file.name+'"/><br/>');
					}
				}
			}
		});

		// 客户端表单验证
		/*$('form').submit(function(e) {
			var uploader = $('#uploader').pluploadQueue();
			if (uploader.files.length > 0) {// 判断队列中是否有文件需要上传
				uploader.bind('StateChanged', function() {// 在所有的文件上传完毕时，提交表单
					if (uploader.files.length === (uploader.total.uploaded + uploader.total.failed)) {
						$('form')[0].submit();
					}
				});
				uploader.start();
			} else {
				alert('请选择至少一个文件进行上传！');
			}
			return false;
		});*/

	});
</script>
</head>





<body>
	<form id="f1" method="post"  class="form">
		<fieldset>
			<legend>上传文件</legend>
			<table class="table" style="width: 100%;">
						<div id="uploader">您的浏览器没有安装Flash插件，或不支持HTML5！</div>
						  <!--  	<button type="submit">提交表单</button>-->
			</table>
		</fieldset>
	</form>
</body>
</html>