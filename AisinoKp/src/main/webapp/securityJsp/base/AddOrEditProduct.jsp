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
    <title>My JSP 'AddOrEditProduct.jsp' starting page</title>
    <script type="text/javascript">
	var submitNow = function($dialog, $grid, $pjq) {
		var url;
		url = sy.contextPath + '/base/product-list!doNotNeedSecurity_addOrEditProduct.sy';
		$.post(url, sy.serializeObject($('form')), function(result) {
			parent.sy.progressBar('close');//关闭上传进度条

			if (result.success) {
				$pjq.messager.alert('提示', result.msg, 'info');
				$grid.datagrid('load');
				$dialog.dialog('destroy');
			} else {
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

		if ($(':input[name="data.id"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sy.contextPath + '/base/product-list!doNotNeedSecurity_findById.sy', {
				id : $(':input[name="data.id"]').val()
			}, function(result) {
				if (result.ID != undefined) {
					$('form').form('load', {
						'data.id' : result.ID,
						'data.product_number' : result.PRODUCT_NUMBER,
						'data.product_name' : result.PRODUCT_NAME,
						'data.tax_category' : result.TAX_CATEGORY,
						'data.unit' : result.UNIT,
						'data.tax_price' : result.TAX_PRICE
					});
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
	});
    </script>
  </head>
  
  <body  class="easyui-layout" data-options="fit:true,border:false">
    <form method="post" class="form">
    <br />
		<fieldset>
		<legend>产品信息维护</legend>
			<input type="hidden" name="data.id" class="transinput" value="<%=id%>" readonly="readonly" />
			<table class="table" style="width: 100%;font-size:14px">
				<tr>
					<th>产品编号</th>
					<td><input name="data.product_number" class="easyui-validatebox" style="width:173px"/></td>
					<th>产品名称</th>
					<td><input name="data.product_name" class="easyui-validatebox" data-options="required:true"  /></td>
				</tr>
				<tr>
					<th>税种</th>
					<td>					
					<!-- <input name="data.tax_category" class="easyui-validatebox" data-options="required:true"/> -->
					
					<select name="data.tax_category" class="easyui-combogrid"
																		    data-options="
																		    panelWidth:220,
																		    panelHeight:200,
																		    width:180,
																		    required:true,
																		    idField:'ID',
																		    textField:'NAME',
																		    striped : true,
																			rownumbers : true,
																			singleSelect : true,
																		    url:sy.contextPath + '/base/rax-list!doNotNeedSecurity_getRaxList.sy',
																		    method:'post',																		    
																		    columns:[[															    
																		    {field:'NAME',title:'税种',width:80},
																		    {field:'RATE',title:'税率',width:80}
																		    ]]
																		    "></select>												    
					
					</td>
					
					<th>含税单价</th>
					<td><input name="data.tax_price" class="easyui-numberbox" data-options="min:0,precision:2,required:true"  /></td>
				</tr>
				<tr>
				<th>单位</th>
					<td><input name="data.unit" style="width:175px" class="easyui-validatebox" data-options="required:true"  /></td>
				</tr>
				
			</table>
		</fieldset>
	</form>
  </body>
</html>
