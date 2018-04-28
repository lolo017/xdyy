<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String id = request.getParameter("id");
	if (id == null) {
		id = "";
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<jsp:include page="../../inc.jsp"></jsp:include>
    <title></title>
    <script type="text/javascript">
	var submitNow = function($dialog, $grid, $pjq) {
		parent.$.messager.progress({
			text : '数据处理中(基于目前处理的数据量，可能需要等待1分钟)....'
		});
		var url;
		
		url = sy.contextPath + '/base/ticket-store!doNotNeedSessionAndSecurity_save.sy';
		$.post(url, sy.serializeObject($('form')), function(result) {
			if (result.success) {
				$pjq.messager.show({
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
				$grid.datagrid('load');
				$dialog.dialog('destroy');
				parent.$.messager.progress('close');
			} else {
				parent.$.messager.progress('close');
				$pjq.messager.alert('提示', result.msg, 'error');
			}
		}, 'json');
		
	};
	var submitForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {
			submitNow($dialog, $grid, $pjq);
		}
	};
	
	$(function() {
		
		$("#invoice_name").combogrid({
			url: sy.contextPath + '/base/ticket-store!doNotNeedSecurity_popTypeInfo.sy',
			panelWidth:400,
			panelHeigh:200,
		    idField: 'NAME',
	        textField: 'NAME',
	        fitColumns: true,  
	        striped: true,
	        mode:'remote',
	        editable: true,  
	        rownumbers: true,
	        collapsible: false,
	        fit: true,
			columns:[[
				{field:'ID',title:'代开ID',width:60,hidden:true},    
				{field:'CODE',title:'类型编码',width:80},    
				{field:'NAME',title:'类型名称',width:150}
			]],
			onHidePanel:function(){
			},
			onSelect:function(rowIndex, rowData){
				$('#type_id').val(rowData.ID);
				$('#invoice_name').val(rowData.NAME);
			},
	    	onClickRow:function(rowIndex, rowData){
	    		$('#type_id').val(rowData.ID);
				$('#invoice_name').val(rowData.NAME);
	    	}
		});

		$("#lyr").combogrid({
			url: sy.contextPath + '/base/ticket-store!doNotNeedSecurity_popUsrInfo.sy',
			panelWidth:300,
			panelHeigh:200,
		    idField: 'USRID',
	        textField: 'NAME',
	        fitColumns: true,  
	        striped: true,
	        mode:'remote',
	        editable: true,  
	        rownumbers: true,
	        collapsible: false,
	        fit: true,
			columns:[[
				{field:'USRID',title:'USRID',width:60,hidden:true}, 
				{field:'ORG_ID',title:'ORG_ID',width:60,hidden:true},  
				{field:'NAME',title:'使用人姓名',width:150}
			]],
			onHidePanel:function(){
			},
			onSelect:function(rowIndex, rowData){
				$('#org_id').val(rowData.ORG_ID);
			},
	    	onClickRow:function(rowIndex, rowData){
	    		$('#org_id').val(rowData.ORG_ID);
	    	}
		});
		
	});
	
	function cgrow(){
		var amount = $("#amount").val();
    	var fphm1 = $("#fphm1").val();
    	$("#fphm2").val(parseInt(amount)- parseInt(1)+ parseInt(fphm1));
	}
    </script>
  </head>
  
  <body>
    <form method="post" class="form">
		<fieldset>
			<legend>新增票据信息</legend>
			<br />
			<input type="hidden" name="data.id" value="<%=id%>" readonly="readonly" />
			<input type="hidden" id="type_id" name="data.type_id"  readonly="readonly" />
			<table class="table" style="width: 100%;">
				<tr>
					<th>发票种类</th>
					<td colspan="3"><input id="invoice_name" name="data.name" class="easyui-validatebox" style="width:300px" data-options="required:true"  /></td>
				</tr>
				<tr>
					<th>发票代码</th>
					<td><input name="data.fpdm"  class="easyui-validatebox" style="width:295px" data-options="required:true" /></td>					
				</tr>
				<tr>
					<th>发票开始号段</th>
					<td><input  id="fphm1" name="fphm1"  class="easyui-validatebox" style="width:295px" data-options="required:true" /></td>					
				</tr>
				<tr>
					<th>发票数量</th>
					<td><input id="amount" name="amount"  class="easyui-validatebox" onchange="cgrow()" style="width:295px"/></td>					
				</tr>
				<tr>
					<th>发票结束号段</th>
					<td><input id="fphm2" name="fphm2"  class="easyui-validatebox" style="width:295px" data-options="required:true" /></td>					
				</tr>
				
<!-- 				<tr> -->
<!-- 					<th>发票号码</th> -->
<!-- 					<td><input name="data.fphm"  class="easyui-validatebox" style="width:295px" data-options="required:true,validType:'number'" /></td> -->
<!-- 				</tr> -->
				<tr>
					<th>使用人</th>
					<td colspan="3"><input id="lyr" name="data.lyr" class="easyui-validatebox" style="width:300px"  />
					<input id="org_id" name="data.org_id" class="easyui-validatebox" style="width:300px;display:none;"  />
					</td>
				</tr>
			</table>
			<br />
			<div style="font-size:14px" align="center">输入数量后，系统直接会把发票的结束号码算出来！</div>
		</fieldset>
	</form>
  </body>
</html>
