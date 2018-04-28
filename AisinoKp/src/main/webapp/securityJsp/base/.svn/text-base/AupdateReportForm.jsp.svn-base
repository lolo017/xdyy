<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	var submitForm = function($dialog,$pjq,$mainMenu) {
		if ($('form').form('validate')) {
			var url = sy.contextPath + '/base/report-form!updatePage.sy';		
			$.post(url, sy.serializeObject($('form')), function(result) {
				if (result.success) {
					$pjq.messager.alert('提示', result.msg, 'success');
					$dialog.dialog('destroy');
				//	$mainMenu.tree('reload');
				} else {
					$pjq.messager.alert('提示', result.msg, 'error');
				}
			}, 'json');
		}
	};
	
	function sendParam(id,pageName,tStr,cStr,sqlStr,arrayEn,arrayCn,arrayType,arraySql){
		$("#tableName").val(pageName);
		$("#id").val(id);
		$("#tStr").val(tStr);
		$("#cStr").val(cStr);
		$("#sql").val(sqlStr);
		if($("#paramTr").html()){
		for(var i=0;i<arrayEn.length;i++){
		$("#paramTr").append("<td><textarea  name='conditionList["+i+"].cncondition' >"+arrayCn[i]+"</textarea></td>");
		$("#paramTr").append("<td><textarea  name='conditionList["+i+"].encondition' >"+arrayEn[i]+"</textarea></td>");
		$("#paramTr").append("<td><textarea  name='conditionList["+i+"].conditiontype' >"+arrayType[i]+"</textarea></td>");
		$("#paramTr").append("<td><textarea  name='conditionList["+i+"].conditionsql' >"+arraySql[i]+"</textarea></td>");
		}
		}
	}
	
	$(function() {
		
	});
</script>
</head>
<body>
	<form method="post" class="form">
		<fieldset>
			<legend>操作</legend>
			<table class="table" style="width: 100%;">
				
				
				
				
				<tr>
					<th style="text-align: center;">请确认操作</th>
					
				</tr>
				<tr style="display: none">
					<td><textarea id="tStr" name="tNameStr" ></textarea></td>
					<td><textarea id="cStr" name="cNameStr" ></textarea></td>
					<td><textarea id="sql" name="sqlStr" ></textarea></td>
					
					<td><textarea id="id" name="id" ></textarea></td>
					<td><textarea id="tableName" name="tableName" ></textarea></td>
				</tr>
				<tr id="paramTr"style="display: none">
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>