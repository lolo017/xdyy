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
    
    <title>My JSP 'TicketZfqkForm.jsp' starting page</title>
    <script>
    var submitNow = function($dialog, $grid, $pjq) {
		var url;
		url = sy.contextPath + '/base/ticket-store!doNotNeedSessionAndSecurity_saveZfqk.sy';
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
				parent.sy.progressBar('close');//关闭上传进度条
			} else {
				parent.sy.progressBar('close');//关闭上传进度条
				$pjq.messager.alert('提示', result.msg, 'error');
				
			}
		}, 'json');
	};
	var submitForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {
			parent.$.messager.progress({
				text : '数据处理中....'
			});
			submitNow($dialog, $grid, $pjq);
		}
	};
	$(function() {
		parent.$.messager.progress({
			text : '数据加载中....'
		});
		$.post(sy.contextPath + '/base/ticket-store!doNotNeedSessionAndSecurity_getInvoiceDetailbyId.sy?fpid=<%=id%>', {}, function(result) {
			if (result.FPHM != undefined) {
				$('form').form('load', {
					'data.name' : result.NAME,
					'data.fpdm' : result.FPDM,
					'data.fphm' : result.FPHM,
					'data.gfsh' : result.GFSH,
					'data.gfmc' : result.GFMC,
					'data.sjje' : result.SJJE
				});
			}
			parent.$.messager.progress('close');
		}, 'json');
	});
    </script>
  </head>
  
  <body>
    <form method="post" class="form">
		<fieldset>
			<input type="hidden" name="data.id" class="transinput" value="<%=id%>" readonly="readonly" />
			<table class="table" style="width: 100%;font-size:14px;">
				<tr>
					<th>发票种类名称</th>
					<td><input name="data.name" style="width:300px" class="easyui-validatebox"
					 data-options="required:true"  readonly="readonly" /></td>
				</tr>
				<tr>
					<th>发票代码</th>
					<td><input name="data.fpdm" style="width:300px" class="easyui-validatebox"
					 data-options="required:true"  readonly="readonly" /></td>
				</tr>
				<tr>
					<th>发票号码</th>
					<td><input name="data.fphm" style="width:300px" class="easyui-validatebox" 
					data-options="required:true" readonly="readonly" /></td>
				</tr>
				<tr>
					<th>税号</th>
					<td><input name="data.gfsh" style="width:300px" class="easyui-validatebox" 
					 readonly="readonly" /></td>
				</tr>
				<tr>
					<th>名称</th>
					<td><input name="data.gfmc" style="width:300px" class="easyui-validatebox" 
					data-options="required:true" readonly="readonly" /></td>
				</tr>
				<tr>
					<th>金额</th>
					<td><input name="data.sjje" style="width:300px" class="easyui-validatebox" 
					data-options="required:true" readonly="readonly" /></td>
				</tr>
				<tr>
					<th>作废原因</th>
					<td><textarea name="zfqk" style="width:300px;resize:none;height:100px" class="textarea easyui-validatebox" data-options="required:true" /></textarea>
				</tr>
			</table>
		</fieldset>
	</form>
  </body>
</html>
