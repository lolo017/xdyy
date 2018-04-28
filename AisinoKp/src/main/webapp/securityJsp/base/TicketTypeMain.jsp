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
<title>My JSP 'TicketStore.jsp' starting page</title>
<script type="text/javascript">
	var grid;
	var addFun = function() {
		var dialog = parent.sy.modalDialog({
			title : '新增发票类型',
			url : sy.contextPath + '/securityJsp/base/TicketTypeAddForm.jsp',
			height:300,
			buttons : [ {
				text : '添加类型',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	
	 var editFun = function(){
		   var row = $('#grid').datagrid('getSelected');
			if (row){
				var dialog = parent.sy.modalDialog({
					title : '发票类型维护',
					url : sy.contextPath + '/securityJsp/base/TicketTypeAddForm.jsp?id=' + row.ID,
					height:450,
					width:500,
					buttons : [ {
						text : '信息编辑',
						handler : function() {
							dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
						}
					} ]
				});
			}

		   
	   };
	   
	var deleteTicket = function(id){
		parent.$.messager.confirm('询问', '您确定要失效该条记录？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/base/ticket-store!doNotNeedSecurity_SetFlagByID.sy', {
					type_id : id
				}, function(result) {
					if(result.success){
						parent.$.messager.show({
							title:'提示',
							msg:result.msg,
							showType:'slide',
							timeout:2000,
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

	var startTicket = function(id){
		parent.$.messager.confirm('询问', '您确定要启用该条记录？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/base/ticket-store!doNotNeedSecurity_SetStartFlagByID.sy', {
					type_id : id
				}, function(result) {
					if(result.success){
						parent.$.messager.show({
							title:'提示',
							msg:result.msg,
							showType:'slide',
							timeout:2000,
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

	$(function() {
		grid = $('#grid').datagrid(
						{
							title : '',
							url : sy.contextPath + '/base/ticket-store!doNotNeedSecurity_getListALL.sy',
							striped : true,
							rownumbers : true,
							pagination : true,
							singleSelect : true,
							idField : 'ID',
							sortName : 'ID',
							sortOrder : 'desc',
							pageSize : 20,
							pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
									400, 500 ],
							frozenColumns : [ [ {
								width : '100',
								title : '票据ID',
								field : 'ID',
								sortable : true,
								hidden:true
							} ] ],
							columns : [ [
									{
										width : '120',
										title : '类型编码',
										field : 'CODE',
										sortable : true
									},
									{
										width : '200',
										title : '类型名称',
										field : 'NAME',
										sortable : true
									},
									
									{
										width : '60',
										title : '状态',
										field : 'FLAG',
										sortable : true,
										formatter : function(val,rec) {
											if (val == 1){ 
												  return '<span style="color:red;">失效</span>'; 
												 } else { 
													return '正常'; 
												 } 
										}
									},
									{
										title : '操作',
										field : 'action',
										width : '120',
										formatter : function(value, row) {
											var str = '';
											if(row.FLAG=='0') {
											  str += sy.formatString('&nbsp;&nbsp;<a title="作废" class="easyui-linkbutton" href="javascript:void(0);" onclick="deleteTicket(\'{0}\');">作废</a>',row.ID);
											} else {
											  str += sy.formatString('&nbsp;&nbsp;<a title="启用" class="easyui-linkbutton" href="javascript:void(0);" onclick="startTicket(\'{0}\');">启用</a>',row.ID);
											}
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
				<td>名称</td>
				<td><input name="invoice_name" style="width: 200px;" /></td>
				<td>是否作废</td>
				<td>
					<select id="statusselector" name="invoice_status" style="width: 100px;" >
						<option value ="0">未作废</option>
  						<option value ="1">已作废</option>
  						<option value ="9">全部</option>
					</select>
				</td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">过滤</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');$('#statusselector').val('0');grid.datagrid('load',{});">重置过滤</a>
				</td>
			</tr>
			<tr>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-brick_go'" onclick="addFun()">新增</a></td>
				<td colspan="6"><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-brick_go'" onclick="editFun()">修改</a></td>
			</tr>
		</table>
		</form>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>
