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
			title : '新增票据信息',
			url : sy.contextPath + '/securityJsp/base/TicketAddForm.jsp',
			height:500,
			buttons : [ {
				text : '添加税票',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	
	var deleteTicket = function(id){
		parent.$.messager.confirm('询问', '您确定要删除该条记录？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/base/ticket-store!doNotNeedSessionAndSecurity_delete.sy', {
					id : id
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
							url : sy.contextPath + '/base/ticket-store!doNotNeedSessionAndSecurity_grid.sy',
							striped : true,
							rownumbers : true,
							pagination : true,
							singleSelect : true,
							idField : 'ID',
							sortName : 'LRRQ',
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
							},
							{
								width : '100',
								title : '发票类型ID',
								field : 'TYPE_ID',
								hidden:true
							},
							{
								width : '220',
								title : '名称',
								field : 'NAME',
								sortable : true
							} ] ],
							columns : [ [
									{
										width : '120',
										title : '发票代码',
										field : 'FPDM',
										sortable : true
									},
									{
										width : '80',
										title : '税票号码',
										field : 'FPHM',
										sortable : true
									},
									{
										width : '50',
										title : '数量',
										field : 'COUNT',
										sortable : true,
										hidden:true
									},
									{
										width : '60',
										title : '领用状态',
										field : 'LYR',
										sortable : true,
										formatter : function(value, row, index) {
											switch (value) {
											case '0':
												return '未领用';
											case '1':
												return '已领用';
											}
										}
									},
									{
										width : '60',
										title : '开票状态',
										field : 'STATUS',
										sortable : true,
										formatter : function(value, row, index) {
											switch (value) {
											case '-1':
												return '已作废';
											case '0':
												return '未开票';
											case '1':
												return '已开票';
											case '2':
												return '已红冲';
											}
										}
									},
									{
										width : '80',
										title : '录入日期',
										field : 'LRRQ',
										sortable : true
									},
									{
										width : '80',
										title : '修改日期',
										field : 'XGRQ'
									},
									{
										title : '删除',
										field : 'action',
										width : '120',
										formatter : function(value, row) {
											var str = '';
											str += sy.formatString('&nbsp;&nbsp;<a title="删除" href="javascript:void(0);" onclick="deleteTicket(\'{0}\');">删除</a>',row.ID);
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
				<td>税票代码</td>
				<td><input name="fpdm" style="width: 100px;" /></td>
				<td>税票号码(起)</td>
				<td><input name="fphm1" style="width: 100px;" /></td>
				<td>税票号码(止)</td>
				<td><input name="fphm2" style="width: 100px;" /></td>
				<td>状态</td>
				<td>
					<select id="statusselector" name="status" style="width: 100px;" >
						<option value ="0">未开票</option>
  						<option value ="1">已开票</option>
  						<option value ="2">已红冲</option>
  						<option value ="-1">已作废</option>
  					
					</select>
				</td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">过滤</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');$('#statusselector').val('0');grid.datagrid('load',{});">重置过滤</a>
				</td>
			</tr>
			<tr>
				<td colspan="7"><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-brick_go'" onclick="addFun()">导入票据</a></td>
			</tr>
		</table>
		</form>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>
