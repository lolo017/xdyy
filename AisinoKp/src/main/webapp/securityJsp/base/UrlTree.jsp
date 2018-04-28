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
var url='${param.url}';
var urlGrid;
$(function(){
	urlGrid = $('#urlGrid').treegrid({
		title : '',
		url : sy.contextPath+'/base/report-form!doNotNeedSecurity_getResourceUrl.sy',
		idField : 'ID',
		treeField : 'NAME',
		parentField : 'SYRESOURCE_ID',
		rownumbers : true,
		pagination : false,
		sortName : 'ID',
		//sortOrder : 'asc',
		columns :  [ [ {
			width : '100',
			title : '编号',
			field : 'ID',
			checkbox:true
			//hidden:true
		},
		{
			width : '200',
			title : '名称',
			field : 'NAME'
		},
		{
			width : '200',
			title : 'url',
			field : 'URL'
		} 
		] ],
		onLoadSuccess : function(row, data) {
			$('#urlGrid').treegrid('collapseAll');
			$('#url').val(decodeURIComponent(url));
		},
		onClickRow:function(row){
			$('#url').val(row.URL);
		}
	});
});

function setUrl($dialog){
	$dialog.dialog('destroy');
	return $('#url').val();
}
</script>
</head>
<body>
<div data-options="region:'north',border:false,split:true,collapsed:true"
				title="查询条件" style="height: 80%; overflow: hidden;">
				<table id="urlGrid" data-options="fit:true,border:false"></table>
</div>


<div style="height:20%;width:100%;" class="easyui-layout" data-options="fit:true,border:false">
<div data-options="region:'center',fit:true,border:false,title:'编辑url'">
<textarea id="url" rows="3" cols="80"></textarea>
</div>
</div>


</body>
</html>