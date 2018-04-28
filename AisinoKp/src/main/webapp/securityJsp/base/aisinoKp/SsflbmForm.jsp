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
				url = sy.contextPath + '/base/fpmng/ssflbm!updateSsflbm.sy';
			} else {
				url = sy.contextPath + '/base/fpmng/ssflbm!saveSsflbm.sy';
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
			$.post(sy.contextPath + '/base/fpmng/ssflbm!doNotNeedSecurity_getById.sy', {
				id : $(':input[name="data.id"]').val()
			}, function(result) {
				if (result.id != undefined) {
					$('form').form('load', {
						'data.id' : result.id,
						'data.sphm' : result.sphm,
						'data.spmc' : result.spmc,
						'data.ggxh' : result.ggxh,
						'data.dw' : result.dw,
						'data.slv' : result.slv,
						'data.ssflbm' : result.ssflbm,
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
			<legend>税收分类编码信息</legend>
			<table class="table" style="width: 100%;">
				<tr><td colspan="4"><input name="data.id" id="id" value="<%=id %>" style="display:none"></td></tr>
				<tr>
					
					<th>商品号码</th>
					<td><input name="data.sphm" style="width: 200px;" class="easyui-validatebox" data-options="required:true" /></td>
					<th>商品名称</th>
					<td><input name="data.spmc" style="width: 200px;" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>规格型号</th>
					<td><input name="data.ggxh"  style="width: 200px;"  /></td>
					<th>单位</th>
					<td><input name="data.dw" style="width: 200px;" /></td>
				</tr>
				<tr>
					<th>税率</th>
					<td><select id="slv" name="data.slv" style="width: 50px;">
						<option value=17>17%</option>
						<option value=13>13%</option>
						<option value=11>11%</option>
						<option value=6>6%</option>
						<option value=3>3%</option>
						<option value=0>0%</option>
					</select></td>
					<th>税收分类编码</th>
					<td><input name="data.ssflbm" style="width: 200px;" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				
			</table>
		</fieldset>
	</form>
</body>
</html>