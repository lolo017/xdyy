<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String id = request.getParameter("id");
	if (id == null) {
		id = "";
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<jsp:include page="../../inc.jsp"></jsp:include>
    <title>My JSP 'AddOrEditProduct.jsp' starting page</title>
    <script type="text/javascript">
	var submitNow = function($dialog, $grid, $pjq) {
		var url;
		url = sy.contextPath + '/base/rax-list!doNotNeedSecurity_addOrEditRax.sy';
		$.post(url, sy.serializeObject($('form')), function(result) {
			parent.sy.progressBar('close');//关闭上传进度条

			if (result.success) {
				$pjq.messager.alert('提示', result.msg, 'info');
				$grid.datagrid('load');
				$dialog.dialog('destroy');
			} else {
				$pjq.messager.alert('提示', result.msg, 'error');
			}
		}, 'json');
	};
	var submitForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {
			submitNow($dialog, $grid, $pjq);
		}
	};
	
	$(function() {

		if ($(':input[name="data.id"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sy.contextPath + '/base/rax-list!doNotNeedSecurity_findById.sy', {
				id : $(':input[name="data.id"]').val()
			}, function(result) {
				if (result.ID != undefined) {
					$('form').form('load', {
						'data.id' : result.ID,
						'data.name' : result.NAME,
						'data.rate' : result.RATE
					});
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
	});
    </script>
  </head>
  
  <body  class="easyui-layout" data-options="fit:true,border:false">
    <form method="post" class="form">
    <br />
		<fieldset>
		<legend>产品信息维护</legend>
			<input type="hidden" name="data.id" class="transinput" value="<%=id%>" readonly="readonly" />
			<table class="table" style="width: 100%;font-size:14px">
				<tr>
					<th>税率名称</th>
					<td><input name="data.name" class="easyui-validatebox" data-options="required:true"/></td>
					<th>税率（%）</th>
					<td><input name="data.rate" type="text" class="easyui-numberbox" value="" data-options="min:0,max:100,precision:2,required:true" /></td>
				</tr>				
			</table>
		</fieldset>
	</form>
  </body>
</html>
