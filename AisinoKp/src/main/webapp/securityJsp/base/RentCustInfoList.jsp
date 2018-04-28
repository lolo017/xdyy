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
	parent.$.messager.confirm('询问', '您确定要删除租户', function(r) {
		if (r) {
			$.post(sy.contextPath + '/base/rent-custinfo!doNotNeedSecurity_deleteMById.sy', {
				rent_cust_id : id
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
				else{
					parent.$.messager.alert('提示', result.msg, 'error');
					}
			}, 'json');
		}
	});
};

var addProduct = function() {
	var dialog = parent.sy.modalDialog({
		title : '新增租户',
		url : sy.contextPath + '/securityJsp/base/RentCustInfoManage.jsp',
		height:350,
		buttons : [ {
			text : '保存',
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
			}
		} ]
	});
};

var editProduct = function(id) {
	var dialog = parent.sy.modalDialog({
		title : '租户维护',
		url : sy.contextPath + '/securityJsp/base/RentCustInfoManage.jsp?rent_cust_id=' + id,
		height:350,
		buttons : [ {
			text : '保存',
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
						url : sy.contextPath + '/base/rent-custinfo!doNotNeedSessionAndSecurity_grid.sy',
						striped : true,
						rownumbers : true,
						pagination : true,
						singleSelect : true,
						idField : 'DOORS_ID',
						sortName : 'DOORS_ID',
						sortOrder : 'desc',
						pageSize : 20,
						pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
								400, 500 ],
						frozenColumns : [ [ {
							width : '100',
							title : 'RENT_CUST_ID',
							field : 'RENT_CUST_ID',
							sortable : true,
							hidden:true
						}, {
							width : '120',
							title : '租户名称',
							field : 'RENT_CUST_NAME',
							align : 'center',
							sortable : true
						} ] ],
						columns : [ [
								{
									width : '180',
									title : '租户编码',
									field : 'RENT_CUST_CODE',
									align : 'center',
									sortable : true
								},
								{
									width : '200',
									title : '联系电话',
									field : 'TEL',
									align : 'center',
									sortable : true
								},
								{
									width : '200',
									title : '联系人',
									field : 'CONTACT',
									align : 'center',
									sortable : true
								},
								{
									width : '200',
									title : '地址',
									field : 'ADDRESS',
									align : 'center',
									sortable : true
								},
								{
									width : '200',
									title : '租户类型',
									field : 'RENT_TYPE',
									align : 'center',
									sortable : true,
								    formatter : function(value, row, index) {
										switch (value) {
										case 1:
											return '临租户';
										case 2:
											return '长租户';
										}
									}
								},
								
								{
									width : '200',
									title : '代收水费',
									field : 'IS_COLLECTION',
									align : 'center',
									sortable : true,
								    formatter : function(value, row, index) {
										switch (value) {
										case 1:
											return '代收水电费';
										case 0:
											return '不代收水电费';
										}
									}
								},
								{
									title : '操作',
									field : 'action',
									width : '120',
									align : 'center',
									formatter : function(value, row) {
										var str = '';
										str += sy.formatString('<img class="iconImg ext-icon-vcard_edit" title="编辑" onclick="editProduct(\'{0}\');"/>&nbsp;&nbsp;&nbsp;',row.RENT_CUST_ID);
										str += sy.formatString('<img class="iconImg ext-icon-cross" title="删除" onclick="deleteProduct(\'{0}\');"/>',row.RENT_CUST_ID);
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
				<td>产品编号:</td>
				<td><input name="product_number" style="width: 100px;" /></td>
				<td>产品名称:</td>
				<td><input name="product_name" style="width: 100px;" /></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">过滤</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a>
				</td>
			</tr>
			<tr>
				<td colspan="7"><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-package_add'" onclick="addProduct()">新增租户</a></td>
			</tr>
		</table>
		</form>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
  </body>
</html>
