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
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	
	var submitNow = function($dialog, $grid, $pjq) {
		var url = sy.contextPath + '/base/communicate!save.sy';
		
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


	});
</script>
</head>
<body>
	<form method="post" class="form">
	
		<fieldset>
			<legend>沟通</legend>
			<table class="table" style="width: 100%;">
				<tr>
				
					<th style="text-align:center;">与直接上级沟通</th>
					<td>
					<input type="hidden" id="userid" value=<%=id%> name="data.syuser.id"/>
					<textarea style=" width: 480px; height: 40px;" name="data.sjgt"></textarea></td>
					</tr>
					<tr>
					<th style="text-align:center;">与其他非分管领导沟通</th>
					<td><textarea style=" width: 480px; height: 40px;" name="data.fgldgt"></textarea></td>
				</tr>
				<tr>
					<th style="text-align:center;">与下级沟通</th>
					<td><textarea style=" width: 480px; height: 40px;" name="data.xjgt"></textarea></td>
					</tr>
					<tr>
					<th style="text-align:center;">与分子公司沟通</th>
					<td><textarea style=" width: 480px; height: 40px;" name="data.gzgsgt"></textarea></td>
				</tr>
				<tr>
				<th style="text-align:center;">与其它部门沟通</th>
				<td><textarea style=" width: 480px; height: 40px;" name="data.qtbmgt"></textarea></td>
				</tr>
					<tr>
					<th style="text-align:center;">与外部相关部门、合作伙伴沟通</th>
				<td><textarea style=" width: 480px; height: 40px;" name="data.hzhbgt"></textarea></td>
				</tr>
				
			</table>
		</fieldset>
	</form>
</body>
</html>