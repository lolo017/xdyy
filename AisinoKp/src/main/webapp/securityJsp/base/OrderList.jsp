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
			height : 250,
			url : sy.contextPath + '/securityJsp/base/UploadExcel.jsp',
			buttons : [ {
				text : '关  闭',
				handler : function() {
					dialog.dialog('close');
					$('#grid').datagrid('load');
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
			url : sy.contextPath + '/base/order!grid.sy',
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
				field : 'id',
				hidden : true,
				sortable : true
			},{
				width : '100',
				title : '邮件号',
				field : 'ems',
				sortable : true
			}, {
				width : '100',
				title : '配货单号',
				field : 'distributionNum',
				sortable : true
			} ] ],
			columns : [ [ {
				width : '70',
				title : '送货人',
				field : 'sender',
				sortable : true
			}, {
				width : '70',
				title : '收件人姓名',
				field : 'recevier',
				sortable : true
			}, {
				width : '120',
				title : '收件人联系方式',
				field : 'telphone'
				
			}, 
			{
				width : '120',
				title : '收件人联系方式（2）',
				field : 'company'
				
			},
			{
				width : '120',
				title : '收件人地址',
				field : 'address'
				
			},
			{
				width : '120',
				title : '业务类型',
				field : 'type'
				
			},
			{
				width : '120',
				title : '内件信息',
				field : 'internalInfo'
				
			},
			{
				width : '120',
				title : '留白一',
				field : 'remarks'
				
			},
			{
				width : '120',
				title : '税号',
				field : 'taxCode'
				
			},{
				width : '120',
				title : '订单号',
				field : 'orderNum'
				
			},{
				width : '100',
				title : '金额',
				field : 'amount'
				
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
			<!-- tr>
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
								<td></td>
						
							</tr>
						</table>
					</form>
				</td>
			</tr-->
			
			 <tr>
				<td>
					<table>
						<tr>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_add',plain:true" onclick="addFun();">导入EXCEL</a></td>
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