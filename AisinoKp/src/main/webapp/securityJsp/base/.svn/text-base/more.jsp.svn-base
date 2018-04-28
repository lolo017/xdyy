<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
var row='${param.row}';
var rowspan='${param.rowspan}';
var colspan='${param.colspan}';
var width='${param.width}';
var urlGrid;
$(function(){
	$("#row").val(row);
	$("#row").validatebox('validate');
	$("#rowspan").val(rowspan);
	$("#rowspan").validatebox('validate');
	$("#colspan").val(colspan);
	$("#colspan").validatebox('validate');
	$("#wid").val(width);
	$("#wid").validatebox('validate');
});

function setMore($dialog){
	if(!$('#moreForm').form('validate')){
		return false;
	}
	var array= new Array();
	$dialog.dialog('destroy');
	array[0]=$("#row").val();
	array[1]=$("#rowspan").val();
	array[2]=$("#colspan").val();
	array[3]=$("#wid").val();
	return array;
}
</script>
</head>
<body>
<form  id="moreForm">
<table id="tTable" border="1" style="table-layout:fixed;BORDER-COLLAPSE: collapse;border: solid 1px #AAAAAA;text-align:center;" width="100%">
<tr><td>宽度</td><td><input class='easyui-validatebox' data-options='required:true'  id="wid"/></td></tr>
<tr><td>行</td><td><input class='easyui-validatebox' data-options='required:true'  id="row" /></td></tr>
<tr><td>rowspan</td><td><input class='easyui-validatebox' data-options='required:true'  id="rowspan" /></td></tr>
<tr><td>colspan</td><td><input class='easyui-validatebox' data-options='required:true'   id="colspan"/></td></tr>

</table>
</form>

</body>
</html>