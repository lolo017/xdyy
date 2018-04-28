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
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var submitForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {
			var url;
			if ($(':input[name="data.id"]').val().length > 0) {
				url = sy.contextPath + '/base/fpmng/buyerinfo!update.sy';
			} else {
				url = sy.contextPath + '/base/fpmng/buyerinfo!save.sy';
			}
			$.post(url, sy.serializeObject($('form')), function(result) {
				console.log(sy.serializeObject($('form')));
				if (result.success) {
					$grid.datagrid('load');
					$dialog.dialog('destroy');
				} else {
					$pjq.messager.alert('提示', result.msg, 'error');
				}
			}, 'json');
		}
	};
	$(function() {
		if ($(':input[name="data.id"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			//alert($(':input[name="data.id"]').val());
			$.post(sy.contextPath + '/base/fpmng/buyerinfo!getById.sy', {
				id : $(':input[name="data.id"]').val()
			}, function(result) {
				if (result.id != undefined) {
					$('form').form('load', {
						'data.gfhm' : result.gfhm,
						'data.gfmc' : result.gfmc,
						'data.gfsh' : result.gfsh,
						'data.dzdh' : result.dzdh,
						'data.yhzh' : result.yhzh,
						'data.email' : result.email,
						'data.mobile' : result.mobile,
					});
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
	});
</script>
</head>
<body>
	<form method="post" class="form">
		<fieldset>
			<legend>购方基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					
					<th>购方号码</th>
					<td><input name="data.gfhm" style="width: 200px;" class="easyui-validatebox" data-options="required:true"/></td>
					<th>购方名称</th>
					<td><input name="data.gfmc" style="width: 200px;" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>购方税号</th>
					<td><input name="data.gfsh"  style="width: 200px;"  /></td>
					<th>地址电话</th>
					<td><input name="data.dzdh" style="width: 200px;" /></td>
				</tr>
				<tr>
					<th>银行账户</th>
					<td><input name="data.yhzh"  style="width: 200px;"  /></td>
					<th>手机号码</th>
					<td><input name="data.mobile" style="width: 200px;" /></td>
				</tr>
				<tr>
					<th>邮箱地址</th>
					<td><input name="data.email"  style="width: 200px;"  /></td>
					<td><input name="data.id" type="hidden" value="<%=id %>"/></td>
				</tr>
				
			</table>
		</fieldset>
	</form>
</body>
</html>