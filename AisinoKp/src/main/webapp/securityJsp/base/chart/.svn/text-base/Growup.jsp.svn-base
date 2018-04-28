<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="aisino.reportform.util.base.SecurityUtil"%>
<%
	String contextPath = request.getContextPath();
	SecurityUtil securityUtil = new SecurityUtil(session);
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var grid;
	//沟通自评
	var addCommunicate=function(id){
		var dialog = parent.sy.modalDialog({
			title : '自我评价',
			url : sy.contextPath + '/securityJsp/base/WorkForm.jsp?id=' + id,
			width:850,
			height:500,
			buttons : [ {
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	//沟通评价
	var Evaluation = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '编辑用户信息',
			url : sy.contextPath + '/securityJsp/base/WorkFormUpdate.jsp?id=' + id,
			width:1050,
			height:600,
			buttons : [ {
				text : '编辑',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	//沟通评价
	var workSelect = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '编辑用户信息',
			url : sy.contextPath + '/securityJsp/base/WorkSelect.jsp?id=' + id,
			width:1050,
			height:600,
			buttons : [ {
				text : '编辑',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	
	
	var study = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '编辑用户信息',
			url : sy.contextPath + '/securityJsp/base/StudyForm.jsp?id=' + id,
			width:1050,
			height:600,
			buttons : [ {
				text : '编辑',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};

	
	
	var editFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '编辑用户信息',
			url : sy.contextPath + '/securityJsp/base/StudySelect.jsp?id=' + id,
			width:1050,
			height:600,
			buttons : [ {
				text : '编辑',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	
	
	

	
	
	
	
	
	
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : sy.contextPath + '/base/syuser!grid.sy',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'id',
			sortName : 'createdatetime',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			frozenColumns : [ [
			                   {
				width : '100',
				title : '编号',
				field : 'id',
				sortable : true
			},  {
				width : '80',
				title : '姓名',
				field : 'name',
				sortable : true
			} ] ],
			columns : [ [  {
				width : '50',
				title : '性别',
				field : 'sex',
				sortable : true,
				formatter : function(value, row, index) {
					switch (value) {
					case '0':
						return '女';
					case '1':
						return '男';
					}
				}
			}, {
				width : '50',
				title : '年龄',
				field : 'age',
				hidden : true
			}, {
				width : '250',
				title : '照片',
				field : 'photo',
				formatter : function(value, row) {
					if(value){
						return sy.formatString('<span title="{0}">{1}</span>', value, value);
					}
				}
			}, {
				title : '系统学习',
				field : 'study',
				width : '60',
				formatter : function(value, row) {
					var str = '';
					<%if (securityUtil.havePermission("/base/syuser!getById")) {%>
						str += sy.formatString('<img class="iconImg ext-icon-note" title="查看" onclick="study(\'{0}\');"/>', row.id);
					<%}%>
					<%if (securityUtil.havePermission("/base/syuser!update")) {%>
						str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="编辑" onclick="editFun(\'{0}\');"/>', row.id);
					<%}%>
					return str;
				}
			} , {
				title : '成长',
				field : 'communicate',
				width : '60',
				formatter : function(value, row) {
					var str = '';
					<%if (securityUtil.havePermission("/base/syuser!getById")) {%>
						str += sy.formatString('<img class="iconImg ext-icon-note" title="查看1" onclick="addCommunicate(\'{0}\');"/>', row.id);
					<%}%>
					
					<%if (securityUtil.havePermission("/base/syuser!update")) {%>
					str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="编辑2" onclick="workSelect(\'{0}\');"/>', row.id);
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
					<form id="searchForm">
						<table>
							<tr>
							
								<td>姓名</td>
								<td><input name="QUERY_t#name_S_LK" style="width: 80px;" /></td>
								<td>性别</td>
								<td><select name="QUERY_t#sex_S_EQ" class="easyui-combobox" data-options="panelHeight:'auto',editable:false"><option value="">请选择</option>
										<option value="1">男</option>
										<option value="0">女</option></select></td>
								<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		</table>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>