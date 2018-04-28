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
    
    <title>My JSP 'TicketZfqkbList.jsp' starting page</title>
    <script type="text/javascript">
    
    var printZfqkb=function(id){
    	var dialog = parent.sy.modalDialog({
			title : '作废情况表打印',
			width:1000,
			height:600,
			url : sy.contextPath + '/securityJsp/base/TicketZfqkbPrintForm.jsp?id='+id
		});
    };
    
    $(function() {
		grid = $('#grid').datagrid(
						{
							title : '',
							url : sy.contextPath + '/base/ticket-store!doNotNeedSessionAndSecurity_gridZfqkList.sy',
							striped : true,
							rownumbers : true,
							pagination : true,
							singleSelect : true,
							idField : 'ID',
							sortName : 'LRRQ',
							sortOrder : 'desc',
							pageSize : 20,
							pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,400, 500 ],
							frozenColumns : [ [ {
								width : '100',
								title : '票据ID',
								field : 'ID',
								sortable : true,
								hidden:true
							}, {
								width : '170',
								title : '名称',
								field : 'NAME',
								sortable : true
							} ] ],
							columns : [ [
									{
										width : '100',
										title : '字轨',
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
										width : '100',
										title : '开票金额',
										field : 'SJJE',
										sortable : true
									},
									{
										width : '50',
										title : '状态',
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
											case '9':
												return '可作废';
											}
										}
									},
									{
										width : '150',
										title : '开票日期',
										field : 'KPRQ',
										sortable : true
									},
									{
										title : '作废情况表',
										field : 'action',
										width : '90',
										formatter : function(value, row) {
											var str = '';
											str += sy.formatString('<a title="打印" href="javascript:void(0);" onclick="printZfqkb(\'{0}\');">打印</a>',row.ID);
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
				<td>税票号码</td>
				<td><input name="fphm" style="width: 100px;" /></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">过滤</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');$('#statusselector').val('0');grid.datagrid('load',{});">重置过滤</a>
				</td>
			</tr>
		</table>
		</form>
	</div>
    	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
  </body>
</html>
