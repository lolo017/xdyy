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
	var addFun = function() {
		var dialog = parent.sy.modalDialog({
			title : '添加用户信息',
			url : sy.contextPath + '/securityJsp/base/CusposForm.jsp',
			buttons : [ {
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(
							dialog, grid, parent.$);
				}
			} ]
		});
	};
	//商户详情
	var showFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '查看用户信息',
			url : sy.contextPath + '/securityJsp/base/CusposForm.jsp?id=' + id
		});
	};
	//编辑商户信息
	var editFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '编辑用户信息',
			url : sy.contextPath + '/securityJsp/base/CusposForm.jsp?id=' + id,
			buttons : [ {
				text : '编辑',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(
							dialog, grid, parent.$);
				}
			} ]
		});
	};
	//删除商户（暂不删除）
	/*var removeFun = function(id) {
		parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/base/syuser!delete.sy', {
					id : id
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
	};*/

	$(function() {
		grid = $('#grid')
				.datagrid(
						{
							title : '',
							url : sy.contextPath + '/base/cuspos!grid.sy',
							striped : true,
							rownumbers : true,
							pagination : true,
							singleSelect : true,
							idField : 'id',
							sortName : 'id',
							//sortOrder : 'desc',
							pageSize : 50,
							pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
									400, 500 ],
							frozenColumns : [ [ {//冻结这两列
								width : '100',
								title : '编号',
								field : 'id',
								sortable : true,
								hidden : true
							}, {
								width : '120',
								title : '商户对外名称',
								field : 'customeroutname',
								sortable : true,
								align : 'center'
							} ] ],
							columns : [ [
									{
										width : '120',
										title : '商户编号',
										field : 'custposcode',
										sortable : true,
										align : 'center'
									},
									{
										width : '120',
										title : '营业执照编号',
										field : 'buscode',
										sortable : true,
										align : 'center'
									},
									{
										width : '120',
										title : '交易电话',
										field : 'bustel',
										sortable : true,
										align : 'center'
									},
									{
										width : '120',
										title : '法人名字',
										field : 'poslegalperson',
										sortable : true,
										align : 'center'
									},
									{
										width : '120',
										title : '法人身份证',
										field : 'legalcode',
										sortable : true,
										align : 'center'
									},
									{
										width : '120',
										title : '对公账号',
										field : 'outnumer',
										sortable : true,
										align : 'center'
									},
									{
										width : '120',
										title : '所属银行',
										field : 'posbranch',
										sortable : true,
										align : 'center'
									},
									{
										width : '60',
										title : '机具数量',
										field : 'poscount',
										sortable : true,
										align : 'center'
									},

									/*{
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
									}, 
									{
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
									}, */
									{
										title : '操作',
										field : 'action',
										width : '90',
										formatter : function(value, row) {
											var str = '';
<%if (securityUtil.havePermission("/base/cuspos!getById")) {%>
	str += sy
													.formatString(
															'<img class="iconImg ext-icon-note" title="查看" onclick="showFun(\'{0}\');"/>',
															row.id);
<%}%>
	
<%if (securityUtil.havePermission("/base/cuspos!update")) {%>
	str += sy
													.formatString(
															'<img class="iconImg ext-icon-note_edit" title="编辑" onclick="editFun(\'{0}\');"/>',
															row.id);
<%}%>
	
<%if (securityUtil.havePermission("/base/cuspos!delete")) {%>
	str += sy
													.formatString(
															'<img class="iconImg ext-icon-note_delete" title="删除" onclick="removeFun(\'{0}\');"/>',
															row.id);
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
								<td>商户名称</td>
								<td><input name="QUERY_t#customeroutname_S_LK"
									style="width: 80px;" /></td>
								<td>营业执照编号</td>
								<td><input name="QUERY_t#buscode_S_LK" style="width: 80px;" /></td>

								<td><a href="javascript:void(0);" class="easyui-linkbutton"
									data-options="iconCls:'ext-icon-zoom',plain:true"
									onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">过滤</a><a
									href="javascript:void(0);" class="easyui-linkbutton"
									data-options="iconCls:'ext-icon-zoom_out',plain:true"
									onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr>
				<td>
					<table>
						<tr>
							<%
								if (securityUtil.havePermission("/base/syuser!save")) {
							%>
							<td><a href="javascript:void(0);" class="easyui-linkbutton"
								data-options="iconCls:'ext-icon-note_add',plain:true"
								onclick="addFun();">添加</a></td>
							<%
								}
							%>
							<!-- 
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_add',plain:true" onclick="">导入</a></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_go',plain:true" onclick="">导出</a></td> -->
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>