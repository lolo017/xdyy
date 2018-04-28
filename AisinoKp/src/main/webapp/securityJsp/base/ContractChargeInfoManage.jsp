<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String id = request.getParameter("contract_id");
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
    //var width=Math.round((window.screen.width-850)/2); 
    //var height=Math.round((window.screen.height-550)/2);
	var submitNow = function($dialog, $grid, $pjq) {
		var url;
		url = sy.contextPath + '/base/contract-charge-info!doNotNeedSessionAndSecurity_saveCharge.sy';
		$.post(url, sy.serializeObject($('form')), function(result) {

			if (result.success) {
				
				//$dialog.dialog('destroy');
				$grid.datagrid('load');
				var url= sy.contextPath + '/securityJsp/base/ContractChargeInfoManage.jsp?charge_id=' + result.obj;
				self.location.href=url;
				$pjq.messager.alert('提示', result.msg, 'info');
				parent.$.messager.progress('close');
			} else {
				parent.$.messager.progress('close');
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
		  parent.$.messager.progress('close');
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
		
			if ($(':input[name="ccinfo.charge_id"]').val().length > 0) {
			/**parent.$.messager.progress({
				text : '数据加载中....'
			});
			*/
			$.post(sy.contextPath + '/base/contract-info-main!doNotNeedSecurity_getOpenAllInfoById.sy', {
				charge_id : $(':input[name="ccinfo.charge_id"]').val()
			}, function(result) {
				
					$("#charge_id").val(setnull(result.CHARGE_ID));
					$("#rent_cust_id").val(setnull(result.RENT_CUST_ID));
					$("#rent_cust_code").val(setnull(result.RENT_CUST_CODE));
					$("#rent_cust_name").val(setnull(result.RENT_CUST_NAME));
					$("#contract_id").val(setnull(result.CONTRACT_ID));
					$("#charge_date").datebox('setValue', result.CHARGE_DATE);
					$("#start_date").datebox('setValue', result.START_DATE);
					$("#end_date").datebox('setValue', result.END_DATE);
					$("#payment_method").val(result.PAYMENT_METHOD);
					$("#contract_number").combobox({readonly:true,width:180});
					$("#contract_number").combobox('setValue',result.CONTRACT_NUMBER);
					$("#rent_sum").val(result.RENT_SUM);
					$("#step_amount").val(result.STEP_AMOUNT);
					$("#late_fee").val(result.LATE_FEE);
					$("#pre_fee").val(result.PRE_FEE);
					$("#is_invoice").val(result.IS_INVOICE);
					$("#is_charge").val(result.IS_CHARGE);
					$("#water_fee").val(result.WATER_FEE);
					$("#electric_fee").val(result.ELECTRIC_FEE);
					$("#create_by").val(result.CREATE_BY);
					$("#create_date").val(result.CREATE_DATE);
					$("#update_by").val(result.UPDATE_BY);
					$("#update_date").val(result.UPDATE_DATE);
		
				parent.$.messager.progress('close');
				
			}, 'json');
		} 

		$("#contract_number").combogrid({
			url: sy.contextPath + '/base/contract-charge-info!doNotNeedSessionAndSecurity_queryContract.sy',
			panelWidth:580,
			width:170,
		    idField: 'CONTRACT_NUMBER',
	        textField: 'CONTRACT_NUMBER',
	        fitColumns: true,  
	        striped: true,
	        mode:'remote',
	        editable: true,  
	        rownumbers: true,
	        collapsible: false,
	        fit: true,
			columns:[[
				{field:'RENT_CUST_ID',title:'RENT_CUST_ID',width:60,hidden:true},
				{field:'CONTRACT_ID',title:'CONTRACT_ID',width:60,hidden:true},
				{field:'CONTRACT_NUMBER',title:'合同编号',width:200}, 
				{field:'DOORS_NAME',title:'门市名称',width:100},
				{field:'RENT_CUST_NAME',title:'名称',width:200},    
				{field:'RENT_CUST_CODE',title:'编码',width:150},    
				{field:'TEL',title:'电话',width:150},
				{field:'CONTACT',title:'联系人',width:150},
				{field:'ADDRESS',title:'地址',width:150,hidden:true},
				{field:'PAYMENT_METHOD',title:'缴费方式',width:150,hidden:true},
				
			]],
			onChange:function(newValue,oldValue){
				//$("#cust_name").val(oldValue);
			},
			onHidePanel:function(){
			},
			onLoadSuccess: function () {
	    		//if(cust_name!=''){
				//$("#cust_name").combogrid('setValue',cust_name);
	    		//}
	    	},
	    	onSelect:function(rowIndex, rowData){
	    		$('#rent_cust_id').val(rowData.RENT_CUST_ID);
		    	$('#rent_cust_code').val(rowData.RENT_CUST_CODE);
		    	$('#rent_cust_name').val(rowData.RENT_CUST_NAME);
				$('#tel').val(rowData.TEL);
				$('#address').val(rowData.ADDRESS);
				$('#contact').val(rowData.CONTACT);
				$('#contract_id').val(rowData.CONTRACT_ID);
				$('#payment_method').val(rowData.PAYMENT_METHOD);
				$('#contract_number').combogrid('setValue',setnull(rowData.CONTRACT_NUMBER));
				setListByProduct(rowData.CONTRACT_ID,rowData.RENT_CUST_ID);
			},
	    	onClickRow:function(rowIndex, rowData){
	     		$('#rent_cust_id').val(rowData.RENT_CUST_ID);
		    	$('#rent_cust_code').val(rowData.RENT_CUST_CODE);
		    	$('#rent_cust_name').val(rowData.RENT_CUST_NAME);
				$('#tel').val(rowData.TEL);
				$('#address').val(rowData.ADDRESS);
				$('#contact').val(rowData.CONTACT);
				$('#contract_id').val(rowData.CONTRACT_ID);
				$('#payment_method').val(rowData.PAYMENT_METHOD);
				$('#contract_number').combogrid('setValue',setnull(rowData.CONTRACT_NUMBER));
				setListByProduct(rowData.CONTRACT_ID,rowData.RENT_CUST_ID);
	    	}
		});
		
	});
	
	/**
	 * 根据选择的门市，调出楼层信息表
	 **/
	function setListByProduct(contractid,custid){
		parent.$.messager.progress({
			text : '数据加载中....'
		});
		for (i=0;i<count;i++){
		  $('#tr_'+i).remove();
		}
		count = 0;
		$.post(sy.contextPath + '/base/contract-info-main!doNotNeedSecurity_findOtherSet.sy', {
			doors_id : id
		}, function(result) {
		        alert(result.STEP);
			 
				//$("#contractinfodetail_"+j + "_doors_line_id").val(result.list[j].DOORS_LINE_ID);
				//$("#contractinfodetail_"+j + "_doors_id").val(result.list[j].DOORS_ID);
				//$("#contractinfodetail_"+j + "_doors_floor").val(result.list[j].DOORS_FLOOR);
				//$("#contractinfodetail_"+j + "_doors_size").val(result.list[j].DOORS_SIZE);
				//$("#contractinfodetail_"+j + "_doors_rent").val(result.list[j].DOORS_RENT);
			
			parent.$.messager.progress('close');
		}, 'json');
	}
	



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
		<input type="hidden" id="charge_id" name="ccinfo.charge_id" class="transinput" value="<%=id%>" readonly="readonly" />
		<input type="hidden" id="rent_cust_id" name="ccinfo.rent_cust_id" class="transinput"  readonly="readonly" />
		<input type="hidden" id="contract_id" name="ccinfo.contract_id" class="transinput"  readonly="readonly" />
		<input type="hidden" id="is_invoice" name="ccinfo.is_invoice" class="transinput"  readonly="readonly" />
		<input type="hidden" id="is_charge" name="ccinfo.is_charge" class="transinput"  readonly="readonly" />
		<input type="hidden" id="create_date" name="ccinfo.create_date" class="transinput"  readonly="readonly" />
		<input type="hidden" id="create_by" name="ccinfo.create_by" class="transinput"  readonly="readonly" />
		<legend>费用收取</legend>
		<div style="font-size:14px;color:#333;">
			<table class="table" style="width: 100%;font-size:14px;color:#333;">
				<tr>
					<th style="width:80px;">租户名称</th>
					<td><input id="rent_cust_name" name="ccinfo.rent_cust_name"  readonly style="width:100%;" data-options="required:true" /></td>					
					<th style="width:80px;">租户编码</th>
					<td><input id="rent_cust_code" name="ccinfo.rent_cust_code" readonly style="width:100%;" class="easyui-validatebox" /></td>
					<th style="width:80px;">合同编码</th>
					<td><input id="contract_number" name="contract_number"  class="easyui-combobox" style="width:170px;" class="easyui-validatebox" /></td>
				</tr>
				<tr>
					<th>联系电话</th>
					<td><input id="tel" name="tel" class="easyui-validatebox" readonly style="width:100%;"  /></td>					
					<th>联系人</th>
					<td><input id="contact" name="contact"  style="width:100%;" readonly class="easyui-validatebox" /></td>
					<th>缴费时间</th>
					<td><input id="charge_date" name="ccinfo.charge_date" style="width:170px;"  class="easyui-datebox" data-options="formatter:myformatter,parser:myparser,required:true"  /></td>	
				</tr>
				<tr>
					<th>开始时间</th>
					<td><input id="start_date" name="ccinfo.start_date" style="width:170px;" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser,required:true"  /></td>					
					<th>结束时间</th>
					<td><input id="end_date" name="ccinfo.end_date" style="width:160px;" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser,required:true" /></td>
					<th>缴费方式</th>
					<td><select  id="payment_method" name="ccinfo.payment_method"  style="width:170px;">
                          <option value="1" selected>按月缴费</option>
                          <option value="2">按季缴费</option>
                          <option value="3">按半年缴费</option>
                          <option value="4">按年缴费</option>
                        </select>
                    </td>
				</tr>
				<tr>
					<th>租金</th>
					<td><input id="rent_sum" name="ccinfo.rent_sum" class="easyui-validatebox" style="width:100%;"  data-options="required:true" /></td>					
					<th>滞纳金</th>
					<td><input id="late_fee" name="ccinfo.late_fee"  style="width:100%;"  class="easyui-validatebox" /></td>
					<th>预收款</th>
					<td><input id="pre_fee" name="ccinfo.pre_fee" readonly  style="width:100%;" /></td>
				</tr>
				<tr>
					<th>代收水费</th>
					<td><input id="water_fee" name="ccinfo.water_fee" style="width:100%;"  data-options="required:true" /></td>					
					<th>代收电费</th>
					<td><input id="electric_fee" name="ccinfo.electric_fee"  style="width:100%;"  class="easyui-validatebox" /></td>
					<th>缴纳次数</th>
					<td><input id="step_amount" name="ccinfo.step_amount" readonly  style="width:100%;" /></td>
				</tr>
			</table>
		</div>
		</fieldset>
	</form>
	
  </body>
</html>
