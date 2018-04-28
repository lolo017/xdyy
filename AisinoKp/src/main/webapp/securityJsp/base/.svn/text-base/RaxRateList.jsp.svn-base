<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="aisino.reportform.util.base.SecurityUtil"%>
<%
	//String contextPath = request.getContextPath();
	//SecurityUtil securityUtil = new SecurityUtil(session);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <jsp:include page="../../inc.jsp"></jsp:include>
    
    <title>My JSP 'TicketCusManage.jsp' starting page</title>
<script>
var grid;

var deleteProduct = function(id){
	parent.$.messager.confirm('询问', '您确定要删除该税率？', function(r) {
		if (r) {
			$.post(sy.contextPath + '/base/rax-list!doNotNeedSecurity_deleteRate.sy', {
				id : id
			}, function(result) {
				if(result.success){
					parent.$.messager.show({
						title:'提示',
						msg:result.msg,
						showType:'slide',
						timeout:4000,
						style:{
							right:'',
							top:document.body.scrollTop+document.documentElement.scrollTop,
							bottom:''
						}
					});
					grid.datagrid('reload');
				}
				else{r
					parent.$.messager.alert('提示', result.msg, 'error');
					}
			}, 'json');
		}
	});
};

var addProduct = function() {
	var dialog = parent.sy.modalDialog({
		title : '新增税率',
		url : sy.contextPath + '/securityJsp/base/AddOrEditRax.jsp',
		height:250,
		buttons : [ {
			text : '新增税率',
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
			}
		} ]
	});
};

var editProduct = function(id) {
	var dialog = parent.sy.modalDialog({
		title : '税率维护',
		url : sy.contextPath + '/securityJsp/base/AddOrEditRax.jsp?id=' + id,
		height:250,
		buttons : [ {
			text : '税率编辑',
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
			}
		} ]
	});
};

$(function() {
	grid = $('#grid').datagrid(
					{
						title : '',
						url : sy.contextPath + '/base/rax-list!doNotNeedSecurity_getRaxList.sy',
						striped : true,
						rownumbers : true,
						pagination : true,
						singleSelect : true,
						idField : 'ID',
						sortName : 'PRODUCT_ID',
						sortOrder : 'desc',
						pageSize : 20,
						pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
								400, 500 ],
						frozenColumns : [ [ {
							width : '100',
							title : 'ID',
							field : 'ID',
							sortable : true,
							hidden:true
						}, {
							width : '120',
							title : '税率名称',
							field : 'NAME',
							align : 'center',
							sortable : true
						} ] ],
						columns : [ [
								{
									width : '180',
									title : '税率',
									field : 'RATE',
									align : 'center',
									sortable : true
								},
								{
									title : '操作',
									field : 'action',
									width : '120',
									align : 'center',
									formatter : function(value, row) {
										var str = '';
										str += sy.formatString('<img class="iconImg ext-icon-vcard_edit" title="编辑" onclick="editProduct(\'{0}\');"/>&nbsp;&nbsp;&nbsp;',row.ID);
										str += sy.formatString('<img class="iconImg ext-icon-cross" title="删除" onclick="deleteProduct(\'{0}\');"/>',row.ID);
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
	<form id="searchForm">
		<table style="font-size:12px;">
			<tr>
				<td>税率名称:</td>
				<td><input name="name" style="width: 100px;" /></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">过滤</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a>
				</td>
			</tr>
			<tr>
				<td colspan="7"><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-package_add'" onclick="addProduct()">新增税率</a></td>
			</tr>
		</table>
		</form>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
  </body>
</html>
