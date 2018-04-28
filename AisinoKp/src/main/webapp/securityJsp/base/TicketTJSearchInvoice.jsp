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
<title>My JSP 'SearchTicket.jsp' starting page</title>
<script type="text/javascript">
	var grid;

	var addFun = function() {
		var dialog = parent.sy.modalDialog({
			title : '税票填开',
			width : 1000,
			height:550,
			url : sy.contextPath
					+ '/securityJsp/base/TicketFillInvoiceForm.jsp',
			buttons : [ {
				text : '填开',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(
							dialog, grid, parent.$);
				}
			} ]
		});
	};
	
	var zfqk = function(id){
		var dialog = parent.sy.modalDialog({
			title : '作废情况',
			url : sy.contextPath + '/securityJsp/base/TicketZfqkForm.jsp?id='+id,
			buttons : [ {
				text : '确定',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(
							dialog, grid, parent.$);
				}
			}]
		});
	};

	var changeInvoicestatus = function(id, status) {
		parent.$.messager.show({
			title : '提示',
			msg : '请直接在“单据打印”中打印作废情况表',
			showType : 'slide',
			timeout : 5000,
			height:'300px',
			style : {
				right : '',
				top : document.body.scrollTop
						+ document.documentElement.scrollTop,
				bottom : ''
			}
		});
	};

	var showAlertWindow = function(message) {
		parent.$.messager.alert('提示', message, 'error');
	};

	var deleteTicket = function(id) {
		parent.$.messager.confirm('询问','您确定要作废当前税票？',function(r) {
			if (r) {
				$.post(sy.contextPath+ '/base/ticket-store!doNotNeedSessionAndSecurity_disableInvoice.sy',{
					id : id
					},function(result) {
						if (result.success) {
							parent.$.messager.show({
								title : '提示',
								height:'300px',
								msg : result.msg,
								showType : 'slide',
								timeout : 5000,
								style : {
									right : '',
									top : document.body.scrollTop
											+ document.documentElement.scrollTop,
									bottom : ''
								}
							});
							grid.datagrid('reload');
						} else {
							parent.$.messager.alert('提示',result.msg,'error');
						}
					}, 'json');
				}
			});
	};

	var printFP = function(id) {
		$.post(sy.contextPath + '/base/ticket-print!doNotNeedSessionAndSecurity_PrintFP.sy?fpid='+ id,
				{},function(result) {}, 'json');
	};

	var printTicket = function(id, status) {
		var dialog = parent.sy.modalDialog({
			title : '单据打印（' + id + '）',
			width : 1024,
			height : 600,
			url : sy.contextPath + '/securityJsp/base/TicketPrintBaseForm.jsp?id=' + id + '&method=' + status,
			buttons : [ {
				text : '单据打印',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(
							dialog, grid, parent.$);
				}
			} ]
		});
	};

	$(function() {
		grid = $('#grid').datagrid({
							title : '',
							url : sy.contextPath + '/base/ticket-store!doNotNeedSessionAndSecurity_gridused.sy',
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
								width : '80',
								title : '票据ID',
								field : 'ID',
								sortable : true,
								hidden : true
							}, {
								width : '180',
								title : '名称',
								field : 'NAME',
								sortable : true
							},
							{
								width : '80',
								title : '订单ID',
								field : 'HEADER_ID',
								sortable : true,
								hidden : true
							}
							] ],
							columns : [ [
							{
							width : '80',
							title : '税票号码',
							field : 'FPHM',
							sortable : true
							},
							{
								width : '80',
								title : '发票代码',
								field : 'FPDM',
								sortable : true
							},
							{
							width : '150',
							title : '购方税号',
							field : 'GFSH',
							sortable : true
							},
							{
							width : '150',
							title : '购方名称',
							field : 'GFMC',
							sortable : true
							},
							{
								width : '70',
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
									return '废票';
								case '0':
									return '未开票';
								case '9':
									return '可作废';
								case '1':
									return '已开票';
								}
							}},
							{
							width : '80',
							title : '开票日期',
							field : 'KPRQ',
							sortable : true
							},
							{
							width : '80',
							title : '开票员',
							field : 'KPY',
							sortable : true
							},
							{
								width : '80',
								title : '红冲原因',
								field : 'BLUE_REASON',
								sortable : true
								},
							{
							title : '操作',
							field : 'action',
							width : '80',
							formatter : function(value, row) {
								var str = '';
								var url=sy.contextPath + '/base/ticket-store!doNotNeedSessionAndSecurity_PrintFP.sy?fpid=';
								str += sy.formatString('<a title="打印税票" href="javascript:void(0);" onclick="window.open(\''+url+'{0}\')">打印</a>&nbsp;&nbsp;',row.HEADER_ID);
								str += sy.formatString('&nbsp;&nbsp;&nbsp;<a title="作废" href="javascript:void(0);" class="easyui-linkbutton" style="color:#f00;" onclick="viewTicket(\'{0}\');">查看</a>',row.HEADER_ID);
								return str;
							}
						}]],
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
		
		$("#gfmc").combogrid({
			url: sy.contextPath + '/base/ticket-customer!doNotNeedSessionAndSecurity_queryCus.sy',
			panelWidth:400,
		    idField: 'NAME',
		    width:120,
	        textField: 'NAME',
	        fitColumns: true,  
	        striped: true,
	        mode:'remote',
	        editable: true,  
	        rownumbers: true,
	        collapsible: false,
	        fit: true,
			columns:[[
				{field:'ID',title:'ID',width:60,hidden:true},
				{field:'NAME',title:'名称',width:150},    
				{field:'TAXCODE',title:'税号',width:150},    
				{field:'DZDH',title:'DZDH',width:150,hidden:true}
			]],
			
			onHidePanel:function(){
			},
			onLoadSuccess: function () {
	    	},
	    	onClickRow:function(rowIndex, rowData){
// 		    	$('#cust_code').val(rowData.TAXCODE);
// 		    	$('#cust_id').val(rowData.ID);
// 				$('#cust_name').val(rowData.NAME);
// 				$('#addr').val(rowData.DZDH);
// 				$('#telphone').val(rowData.TELPHONE);
// 				$('#mobile').val(rowData.MOBILE);
				//$('#gfmc').focus();
				//$('#savecusinfo').attr("checked",false);
	    	}
		});
	});
	
	function myformatter(date){
		var y = date.getFullYear();
		var m = date.getMonth()+1;
		var d = date.getDate();
		return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
	}
	function myparser(s){
		if (!s) return new Date();
		var ss = (s.split('-'));
		var y = parseInt(ss[0],10);
		var m = parseInt(ss[1],10);
		var d = parseInt(ss[2],10);
		if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
			return new Date(y,m-1,d);
		} else {
			return new Date();
		}
	}
	viewTicket =function(id){
		var dialog = parent.sy.modalDialog({
			
			title : '发票查看',
			width : 800,
			height:550,
			url : sy.contextPath
					+ '/securityJsp/base/TicketFillInvoiceForm.jsp?id='+id,
			buttons : [ 
			{
				text : '打印',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.dy(
							dialog, grid, parent.$);
				}
			}
			
			]
		});
	}
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div id="toolbar" style="display: none;">
		<form id="searchForm">
			<table style="font-size:12px;">
				<tr>
					<td>发票代码</td>
					<td><input name="fpdm" style="width: 100px;" /></td>
					<td>发票开始号段</td>
					<td><input name="fphm1" style="width: 100px;" /></td>
					<td>发票结束号段</td>
					<td><input name="fphm2" style="width: 100px;" /></td>
					<td>状态</td>
					<td><select id="statusselector" name="status"
						style="width: 100px;">
							<option value="999">全部</option>
							<option value="1">已开票</option>
							<option value="2">已红冲</option>
							<option value="-1">已作废</option>
					</select></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'ext-icon-zoom',plain:true"
						onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">过滤</a>
						<a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'ext-icon-zoom_out',plain:true"
						onclick="$('#searchForm input').val('');$('#statusselector').val('999');grid.datagrid('load',{});">重置过滤</a>
					</td>
				</tr>
				<tr>
				 <td>购方名称</td>
				 <td><input id="gfmc" name="gfmc" style="width: 100px;" /></td>
				 <td>开具起始时间</td>
				 <td> <input name="sdate" class="easyui-datebox textbox"   data-options="formatter:myformatter,parser:myparser"  style="width:120px;height:20px"></td>
				 <td>开具结束时间</td>
				 <td> <input name="edate"  class="easyui-datebox textbox"   data-options="formatter:myformatter,parser:myparser"  style="width:120px;height:20px"></td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>
