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
		var url = sy.contextPath + '/base/change!save.sy';
		
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
				
					<th style="text-align:center;">参与了哪些工作变革</th>
					<td>
					<input type="hidden" id="userid" value=<%=id%> name="data.syuser.id"/>
					<textarea style=" width: 480px; height: 40px;" name="data.cjbg"></textarea></td>
					</tr>
					<tr>
					<th style="text-align:center;">推动了哪些工作变革</th>
					<td><textarea style=" width: 480px; height: 40px;" name="data.tdbg"></textarea></td>
				</tr>
				<tr>
					<th style="text-align:center;">被总经理采纳</th>
					<td><textarea style=" width: 480px; height: 40px;" name="data.jjlcn"></textarea></td>
					</tr>
					<tr>
					<th style="text-align:center;">被分管领导采纳</th>
					<td><textarea style=" width: 480px; height: 40px;" name="data.fgldcn"></textarea></td>
				</tr>
				<tr>
				<th style="text-align:center;">被非分管领导采纳</th>
				<td><textarea style=" width: 480px; height: 40px;" name="data.ffgldcn"></textarea></td>
				</tr>
					
				
			</table>
		</fieldset>
	</form>
</body>
</html>