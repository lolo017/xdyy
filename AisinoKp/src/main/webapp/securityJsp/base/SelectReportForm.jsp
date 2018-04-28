<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="aisino.reportform.util.base.SecurityUtil"%>
<%
	String contextPath = request.getContextPath();
	SecurityUtil securityUtil = new SecurityUtil(session);
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var grid;

	

	var removeFun = function(id) {
		parent.$.messager.confirm('询问', '您确定要删除此报表？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/base/page!deletePage.sy', {
					pageId : id
				}, function(result) {
					if(result.success){
						grid.datagrid('reload');
						parent.mainMenu.tree('reload');
					}else{
						parent.$.messager.alert('提示', '删除出错!', 'error');
					}
					
				}, 'json');
			}
		});
	};

	//编辑信息
	var dialog;
	var editFun = function(id,pageName) {
		dialog = parent.sy.modalDialog({
			title : '编辑报表:'+pageName,
			url : sy.contextPath + '/securityJsp/base/UpdateReportForm.jsp?id=' + id,
			width:1300,
			height:570,
			buttons : [ {
				text : '保存',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.update(dialog, grid, parent.$, parent.mainMenu);
					
					
				}
			} ]
		});
	};
	
	function closeDialog(){
		dialog.dialog('destroy');
	}
	function reloadData(){
		grid.datagrid('reload');
	}
	
	$(function() {
		grid = $('#grid')
				.datagrid(
						{
							title : '',
							url : sy.contextPath + '/base/page!grid.sy',
							striped : false,
							rownumbers : true,
							pagination : true,
							singleSelect : true,
							idField : 'ID',
							sortName : 'ID',
							//sortOrder : 'desc',
							remoteSort: false,
							pageSize : 50,
							pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
									400, 500 ],
							frozenColumns : [ [ {//冻结这两列
								width : '100',
								title : '编号',
								field : 'ID',
								sortable : true,
								hidden : true
							}, {
								width : '120',
								title : '页面名称',
								field : 'PAGENAME',
								sortable : true,
								align : 'center'
							} ] ],
							columns : [ [
									{
										width : '900',
										title : 'SQL语句',
										field : 'SQLCODE',
										sortable : true,
										align : 'center'
									},
									{
										title : '操作',
										field : 'ACTION',
										width : '90',
										formatter : function(value, row) {
											var str = '';

<%if (securityUtil.havePermission("/base/report-form!updateSql")) {%>
	str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="编辑" onclick="editFun(\'{0}\',\'{1}\');"/>',row.ID,row.PAGENAME);
<%}%>
	
<%if (securityUtil.havePermission("/base/page!deletePage")) {%>
	str += sy.formatString('<img class="iconImg ext-icon-note_delete" title="删除" onclick="removeFun(\'{0}\');"/>',row.ID);
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
// 							,
// 							onDblClickRow:function(rowIndex,rowData){
// 								editFun(rowData.id,rowData.pageName);

// 								}
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
								<td>页面名称</td>
								<td><input name="QUERY_t#pageName_S_LK"
									style="width: 80px;" /></td>
								

								<td><a href="javascript:void(0);" class="easyui-linkbutton"
									data-options="iconCls:'ext-icon-zoom',plain:true"
									onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">查询</a><a
									href="javascript:void(0);" class="easyui-linkbutton"
									data-options="iconCls:'ext-icon-zoom_out',plain:true"
									onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置</a></td>
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