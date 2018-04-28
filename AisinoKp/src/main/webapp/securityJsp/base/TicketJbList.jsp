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
    
    <title>My JSP 'TicketJBList.jsp' starting page</title>
    <script type="text/javascript">
	var grid;
    $(function() {
		grid = $('#grid').datagrid(
						{
							title : '',
							url : sy.contextPath + '/base/ticket-jb!doNotNeedSessionAndSecurity_query.sy',
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
								width : '280',
								title : 'ID',
								field : 'ID',
								sortable : true
							} ] ],
							columns : [ [
									{
										width : '200',
										title : '税票名称',
										field : 'NAME',
										sortable : true
									},{
										width : '80',
										title : '结报起',
										field : 'JBKS',
										sortable : true
									},
									{
										width : '80',
										title : '结报至',
										field : 'JBJS',
										sortable : true
									},
									{
										width : '80',
										title : '操作日期',
										field : 'LRRQ',
										sortable : true
									},{
										title : '操作',
										field : 'action',
										width : '150',
										formatter : function(value, row) {
											var str = '';
											str += sy.formatString('&nbsp;&nbsp;&nbsp;<a title="打印" href="javascript:void(0);" onclick="printjb(\'{0}\',\'{1}\',\'{2}\');">打印</a>',row.ID,row.JBKS,row.JBJS);
											str += sy.formatString('&nbsp;&nbsp;&nbsp;<a title="回退当前结报" href="javascript:void(0);" onclick="fallbackjb(\'{0}\');">回退当前结报</a>',row.ID);
											return str;
										}
									}
									] ],
							singleSelect: true,
							selectOnCheck: false,
							checkOnSelect: false,
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
    
	var fallbackjb=function(id){
		parent.$.messager.confirm('询问', '您确定要回退当前记录？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/base/ticket-jb!doNotNeedSessionAndSecurity_fallback.sy', {
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
	
	var printjb=function(id,ksrq,jsrq){
    	var dialog = parent.sy.modalDialog({
			title : '结报单据打印',
			width : 1000,
			height : 600,
			url : sy.contextPath + '/securityJsp/base/TicketJbForm.jsp?id=' +id+'&ksrq='+ksrq+'&jsrq='+jsrq,
			onClose: function () {  
				grid.datagrid('reload'); 
		    } 
		});
	};
    
    var addFun=function() {
    	parent.$.messager.confirm('询问', '您确定要进行结报操作？', function(r) {
			if (r) {
    			var dialog = parent.sy.modalDialog({
					title : '税票结报',
					width : 1000,
					height : 600,
					url : sy.contextPath + '/securityJsp/base/TicketJbForm.jsp',
// 					buttons : [ {
// 						text : '关闭',
// 						handler : function() {
// 							dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
// 						}
// 					}]
					onClose: function () {  
						grid.datagrid('reload'); 
				    } 
				});
			}
		});
	};
    </script>
  </head>
  
  <body class="easyui-layout" data-options="fit:true,border:false">
  <div id="toolbar" style="display: none;">
		<form id="searchForm">
			<table style="font-size:12px;">
				<tr>
					<td colspan="7"><a href="javascript:void(0);"
						class="easyui-linkbutton"
						data-options="iconCls:'ext-icon-brick_go'" onclick="addFun()">税票结报</a></td>
				</tr>
			</table>
		</form>
	</div>
  <div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
  </body>
</html>
