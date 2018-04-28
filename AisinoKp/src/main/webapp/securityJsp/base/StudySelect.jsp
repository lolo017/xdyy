<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="aisino.reportform.util.base.SecurityUtil"%>
<%
	String contextPath = request.getContextPath();
	SecurityUtil securityUtil = new SecurityUtil(session);
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
	var grid;
	
	var addPer=function(){
		var id='<%=id%>';
		var dialog = parent.sy.modalDialog({
			title : '新增用户信息',
			url : sy.contextPath + '/securityJsp/base/StudyForm.jsp?id=' + id,
			width:1050,
			height:600,
			buttons : [ {
				text : '保存',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	
	var removeFun = function(id) {
		parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/base/study!delete.sy', {
					id : id
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	
	
	var information=function(id){
		var dialog = parent.sy.modalDialog({
			title : '编辑用户信息',
			url : sy.contextPath + '/securityJsp/base/StudyUpdateForm.jsp?id=' + id,
			width:1050,
			height:600,
			buttons : [ {
				text : '保存',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
		
	};
	//沟通评价
	$(function() {
		var id='<%=id%>';
		grid = $('#grid').datagrid({
			title : '',
			url : sy.contextPath + '/base/study!grids.sy?data.empId='+id,
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'id',
			sortName : 'id',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			frozenColumns : [ [ 
			                    
			                    {
				width : '80',
				title : '编号',
				field : 'id',
				 hidden:true,
				sortable : true
			},{
				width : '100',
				title : '姓名',
				field : 'name',
				sortable : true
				
				
			}, {
				width : '80',
				title : '已取得学历',
				field : 'yqdxl',
				sortable : true
			} ] ],
			columns : [ [ {
				width : '150',
				title : '在读学历性质',
				field : 'zdxlxz',
				sortable : true
			}, 
			{
				width : '150',
				title : '在读学历学习科目',
				field : 'zdxlkm',
				sortable : true
			},
			{
				width : '150',
				title : '拟考取学历院校',
				field : 'nkqxlyxmc',
				sortable : true
			},
			
			
			{
				width : '150',
				title : '拟考取学历性质',
				field : 'nkqxlxlxz',
				sortable : true
			},
			
			
			{
				width : '150',
				title : '已取得职称',
				field : 'zcyqdzc',
				sortable : true
			},
			
			
			{
				width : '150',
				title : '拟考取职称',
				field : 'zcnkqzc',
				sortable : true
			},
			
			{
				width : '150',
				title : '考试科目',
				field : 'zckskm',
				sortable : true
			},
			{
				title : '操作',
				field : 'action',
				width : '90',
				formatter : function(value, row) {
					var str = '';
					<%if (securityUtil.havePermission("/base/study!update")) {%>
					str += sy.formatString('<img class="iconImg ext-icon-note" title="编辑" onclick="information(\'{0}\');"/>', row.id);
				<%}%>
					<%if (securityUtil.havePermission("/base/study!delete")) {%>
						str += sy.formatString('<img class="iconImg ext-icon-delete" title="删除" onclick="removeFun(\'{0}\');"/>', row.id);
					<%}%>
					
					return str;
				}
			} ] ],
			toolbar : '#toolbar',
			onBeforeLoad : function(param) {
				parent.$.messager.progress({
					text : '数据加载中....'
				});
			},
			onLoadSuccess : function(data) {
				$('.iconImg').attr('src', sy.pixel_0);
				parent.$.messager.progress('close');
			},
			onDblClickRow:function(rowIndex,rowData){
				information(rowData.id);

				}
		});
	});
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div id="toolbar" style="display: none;">
		<table>
           <tr>
			<td>
<a href="javascript:void(0);" class="easyui-linkbutton"
data-options="plain:true,iconCls:'ext-icon-disk'" onclick="addPer()">添加</a>
           </td>
			</tr>
		</table>
	</div>
	<div data-options="region:'center',fit:true,border:false,title:'专业知识'">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>