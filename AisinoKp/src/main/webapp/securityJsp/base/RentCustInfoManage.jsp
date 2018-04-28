<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String id = request.getParameter("rent_cust_id");
	if (id == null) {
		id = "";
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<jsp:include page="../../inc.jsp"></jsp:include>
    
    <title>My JSP 'FillInvoice.jsp' starting page</title>
<script type="text/javascript">
    var targetObj;
    var count=0;
    var is_click =0;
	var submitNow = function($dialog, $grid, $pjq) {
		var url;
		url = sy.contextPath + '/base/rent-custinfo!doNotNeedSessionAndSecurity_saveDoor.sy';
		$.post(url, sy.serializeObject($('form')), function(result) {

			if (result.success) {
				//$dialog.dialog('destroy');
				$grid.datagrid('load');
				var url= sy.contextPath + '/securityJsp/base/RentCustInfoManage.jsp?rent_cust_id=' + result.obj;
				self.location.href=url;
				//$pjq.messager.alert('提示', result.msg, 'info');
					$pjq.messager.show({
						title:'提示',
						msg:result.msg,
						showType:'slide',
						timeout:4000,
						style:{
							right:'',
							top:document.body.scrollTop+document.documentElement.scrollTop,
							bottom:''
						}
					});
				parent.$.messager.progress('close');//关闭上传进度条
			} else {
				parent.$.messager.progress('close');//关闭上传进度条
				$pjq.messager.alert('提示', result.msg, 'error');
				
			}
			
		}, 'json');
		
	};
	var submitForm = function($dialog, $grid, $pjq) {
		parent.$.messager.progress({
			text : '数据处理中....'
		});
			if ($('form').form('validate')) {
				submitNow($dialog, $grid, $pjq);
			} else {
			parent.$.messager.progress('close');//关闭上传进度条
		}
	};
	
	

	
	
	function setnull(num){
		if(num == undefined){
			return "" ;
		} else{
			return num;
		}
	}

	$(function() {
			if ($(':input[name="rentCustInfo.rent_cust_id"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sy.contextPath + '/base/rent-custinfo!doNotNeedSecurity_getOpenAllInfoById.sy', {
				rent_cust_id : $(':input[name="rentCustInfo.rent_cust_id"]').val()
			}, function(result) {
				$("#rent_cust_id").val(result.RENT_CUST_ID);
				$("#rent_cust_name").val(result.RENT_CUST_NAME);
				$("#rent_cust_code").val(result.RENT_CUST_CODE);
				$("#contact").val(result.CONTACT);
				$("#tel").val(result.TEL);
				$("#address").val(result.ADDRESS);
				$("#rent_type"+result.RENT_TYPE).attr("checked","checked");
				//$("#rent_type").val(result.RENT_TYPE);
				//$("#is_collection").val(result.IS_COLLECTION);
				$("#is_collection"+result.IS_COLLECTION).attr("checked","checked");
				$("#create_by").val(result.CREATE_BY);
				$("#create_date").val(result.CREATE_DATE1);
				$("#update_by").val(result.UPDATE_BY);
				$("#update_date").val(result.UPDATE_DATE1);
				parent.$.messager.progress('close');
				
			}, 'json');
		}
	});
	
	

	


	

    </script>
    <style>
    .transinput{
    	border:0px solid #c00;
    }
    </style>
  </head>
  
  <body  class="easyui-layout" data-options="fit:true,border:false">
    <form method="post" class="form">
    <fieldset>
		<input type="hidden" id="rent_cust_id" name="rentCustInfo.rent_cust_id" class="transinput" value="<%=id%>" readonly="readonly" />
		<input type="hidden" id="create_by" name="rentCustInfo.create_by" class="transinput"  readonly="readonly" />
		<input type="hidden" id="create_date" name="rentCustInfo.create_date" class="transinput"  readonly="readonly" />
		<input type="hidden" id="update_by" name="rentCustInfo.update_by" class="transinput"  readonly="readonly" />
		<input type="hidden" id="update_date" name="rentCustInfo.update_date" class="transinput"  readonly="readonly" />
		<legend>新租户登记</legend>
		<div style="font-size:14px;color:#333;">
			<table class="table" style="width: 100%;font-size:14px;color:#333;">
				<tr>
					<th style="width:80px;">租户编码</th>
					<td><input id="rent_cust_code" name="rentCustInfo.rent_cust_code" data-options="required:true" style="width:97%;" /></td>					
					<th style="width:80px;">租户名称</th>
					<td><input id="rent_cust_name" name="rentCustInfo.rent_cust_name" class="easyui-validatebox" style="width:97%;" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>联系人</th>
					<td><input id="contact" name="rentCustInfo.contact" style="width:97%;" data-options="required:true" /></td>					
					<th>联系电话</th>
					<td><input id="tel" name="rentCustInfo.tel" style="width:97%;" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>租户地址</th>
					<td colspan="3"><input id ="address" name="rentCustInfo.address" class="easyui-validatebox"  data-options="required:true"  style="width:99%;"/></td>
				</tr>
				<tr>
					<th>客户类型</th>
					<td> <input id="rent_type1" name="rentCustInfo.rent_type" type="radio" value="1" checked="true">临租户
                         <input id="rent_type2" name="rentCustInfo.rent_type" type="radio" value="2" >长租户
					</td>
					<th>是否代收水电费</th>
					<td>
					    <input id="is_collection1" name="rentCustInfo.is_collection" type="radio" value="1" >是
                        <input id="is_collection0" name="rentCustInfo.is_collection" type="radio" value="0" checked="true"> 否
					</td>
				</tr>
			</table>

		</div>
		</fieldset>
	</form>
	
  </body>
</html>
