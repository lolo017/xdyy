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
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var grid;
	var addFun = function() {
		var dialog = parent.sy.modalDialog({
			title : '上传文件',
			url : sy.contextPath + '/securityJsp/base/Upload.jsp',
			buttons : [ {
				text : '提交表单',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};

	var editFun = function(path,id) {
		//alert(path+"//"+id);
		parent.$.messager.progress({
			title : '提示',
			text : '数据处理中，请稍后....'
		});
		var types = $('#types').combobox('getValue');
		
		var config={
				filepath:path,
				types:types,
				id:id
		};
		//alert(types);
		if(types=="0"){
			parent.$.messager.progress('close');
			parent.$.messager.alert('提示',
					"请选择文件读取种类",
					'info');
		}else{
			$.post(sy.contextPath + '/base/fileupload!insertBb.sy', config , function(result) {
				//grid.datagrid('reload');
				parent.$.messager.progress('close');
				parent.$.messager.alert('提示',
						result.msg,
						'info');
			}, 'json');
		}
		
	};
	var removeFun = function(id) {

		parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/base/fileupload!deletefile.sy', {
					id : id
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
		
	
	};

	
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : sy.contextPath + '/base/fileupload!grid.sy',
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
				width : '200',
				title : '文件名',
				field : 'id',
				sortable : true
			},{
				width : '200',
				title : '文件名',
				field : 'name',
				sortable : true
			}, {
				width : '360',
				title : '文件路径',
				field : 'filepath',
				sortable : true
			} ] ],
			columns : [ [ {
				width : '150',
				title : '上传时间',
				field : 'uploadtime',
				sortable : true
			}, {
				width : '70',
				title : '上传人',
				field : 'uploadauthor',
				sortable : true
			}, {
				width : '120',
				title : '文件类型',
				field : 'alias'
				
			}, {
				title : '操作',
				field : 'action',
				width : '90',
				formatter : function(value, row) {
					var str = '';
				
					
						str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="编辑" onclick="editFun(\'{0}\',\'{1}\');"/>', row.alias,row.id);
					
						str += sy.formatString('<img class="iconImg ext-icon-note_delete" title="删除" onclick="removeFun(\'{0}\');"/>', row.id);
					
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
								<td>文件名</td>
								<td><input name="QUERY_t#loginname_S_LK" style="width: 80px;" /></td>
								
								
								<td>创建时间</td>
								<td><input name="QUERY_t#createdatetime_D_GE" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" />-<input name="QUERY_t#createdatetime_D_LE" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" /></td>
								<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
							</tr>
							<tr>
								<td>导入类型</td>
								<td><select id="types" name="types" class="easyui-combobox" data-options="panelHeight:'auto',editable:false"><option value="0">请选择</option>
										<option value="3">交易记录</option>
										<option value="1">MerchData</option>
										<option value="5">RatioData</option>
										<option value="2">TermData</option>
										<option value="6">对公账号</option>
										<option value="7">对私账号</option>
										<option value="4">卡号</option>
										<option value="8">新扣率方案</option>
										</select></td>
						
							</tr>
						</table>
					</form>
				</td>
			</tr>
			
			 <tr>
				<td>
					<table>
						<tr>
						<!-- 
							<%if (securityUtil.havePermission("/base/syuser!save")) {%>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
							<%}%>
							<td><div class="datagrid-btn-separator"></div></td> -->
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_add',plain:true" onclick="addFun();">导入</a></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_go',plain:true" onclick="">导出</a></td>
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