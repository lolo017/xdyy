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
		url = sy.contextPath + '/base/contract-info-main!doNotNeedSessionAndSecurity_saveContract.sy';
		$.post(url, sy.serializeObject($('form')), function(result) {

			if (result.success) {
				
				//$dialog.dialog('destroy');
				$grid.datagrid('load');
				var url= sy.contextPath + '/securityJsp/base/ContractInfoMainManage.jsp?contract_id=' + result.obj;
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
	

	
	var editStepFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '阶段收费',
			width : 600,
			height:450,
			url : sy.contextPath
					+ '/securityJsp/base/ContractStepChangeManage.jsp?contract_line_id='+$("#contractinfodetail_"+id+"_contract_line_id").val(),
			buttons : [ {
				text : '保存',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(
							dialog);
				}
			}
			
			]
		});
	};
	
	
	function setnull(num){
		if(num == undefined){
			return "" ;
		} else{
			return num;
		}
	}

	$(function() {
		
			if ($(':input[name="contractinfomain.contract_id"]').val().length > 0) {
			/**parent.$.messager.progress({
				text : '数据加载中....'
			});
			*/
			$.post(sy.contextPath + '/base/contract-info-main!doNotNeedSecurity_getOpenAllInfoById.sy', {
				contract_id : $(':input[name="contractinfomain.contract_id"]').val()
			}, function(result) {
			   count = result.list.length;
				for(var i=0;i<result.main.length;i++){
					$("#contract_id").val(setnull(result.main[i].CONTRACT_ID));
					$("#rent_cust_id").val(setnull(result.main[i].RENT_CUST_ID));
					$("#rent_cust_name").combogrid('setValue',result.main[i].RENT_CUST_NAME);
					$("#rent_cust_code").val(result.main[i].RENT_CUST_CODE);
					$("#contract_number").val(result.main[i].CONTRACT_NUMBER);
					$("#tel").val(result.main[i].TEL);
					$("#contact").val(result.main[i].CONTACT);
					$("#address").val(result.main[i].ADDRESS);
					$("#doors_id").val(result.main[i].DOORS_ID);
					$("#doors_id_name").combobox({readonly:true,width:169});
					$("#doors_id_name").combobox('setValue',result.main[i].DOORS_NAME);
					
					$("#start_date").datebox('setValue', result.main[i].START_DATE);
					$("#end_date").datebox('setValue', result.main[i].END_DATE);
					$("#payment_method").val(result.main[i].PAYMENT_METHOD);
					//$("#rent_type").val(result.main[i].RENT_TYPE);
					$("#rent_type"+result.main[i].RENT_TYPE).attr("checked","checked");
					$("#rent_rise_rote").val(result.main[i].RENT_RISE_ROTE);
					$("#is_invoice_mul").val(result.main[i].IS_INVOICE_MUL);
					$("#contract_sum").val(result.main[i].CONTRACT_SUM);
					$("#create_by").val(result.main[i].CREATE_BY);
					$("#create_date").val(result.main[i].CREATE_DATE);
					$("#update_by").val(result.main[i].UPDATE_BY);
					$("#update_date").val(result.main[i].UPDATE_DATE);
				}
				
                for(var j=0;j<result.list.length;j++){
                	targetObj=$(
                			'<tr id=tr_'+j+'>'+
                			'<td style="display:none"><input style="width:80px" id="contractinfodetail_'+j+'_contract_id" name="contractinfodetail['+j+'].contract_id" value="'+setnull(result.list[j].CONTRACT_ID)+'"/></td>' +
                		    '<td style="display:none"><input style="width:80px" id="contractinfodetail_'+j+'_contract_line_id" name="contractinfodetail['+j+'].contract_line_id" value="'+setnull(result.list[j].CONTRACT_LINE_ID)+'"/></td>' +
                		    '<td style="display:none"><input style="width:80px" id="contractinfodetail_'+j+'_doors_line_id" name="contractinfodetail['+j+'].doors_line_id" value="'+setnull(result.list[j].DOORS_LINE_ID)+'"/></td>' +
                		    '<td style="display:none"><input style="width:80px" id="contractinfodetail_'+j+'_doors_id" name="contractinfodetail['+j+'].doors_id" value="'+setnull(result.list[j].DOORS_ID)+'"/></td>' +
                		    '<td style="display:none"><input style="width:80px" id="contractinfodetail_'+j+'_is_step_charge" name="contractinfodetail['+j+'].is_step_charge" value="'+setnull(result.list[j].IS_STEP_CHARGE)+'"/></td>' +
                		    '<td style="display:none"><input style="width:80px" id="contractinfodetail_'+j+'_create_by" name="contractinfodetail['+j+'].create_by" value="'+setnull(result.list[j].CREATE_BY)+'"/></td>' +
                		    '<td style="display:none"><input style="width:80px" id="contractinfodetail_'+j+'_create_date" name="contractinfodetail['+j+'].create_date" value="'+setnull(result.list[j].CREATE_DATE)+'"/></td>' +
                			'<td><select style="width:98%" id="contractinfodetail_'+j+'_doors_floor" name="contractinfodetail['+j+'].doors_floor"  value="'+setnull(result.list[j].DOORS_FLOOR)+'" data-options="required:true" >' +
                			' <option value="1">一楼</option> ' +
                			' <option value="2">二楼</option> ' + 
                			' <option value="3">三楼</option> ' + 
                			' <option value="4">四楼</option> ' + 
                			' <option value="5">五楼</option> ' + 
                			' <option value="6">六楼</option> ' + 
                			' <option value="all">整栋</option> ' + 
                	        ' </select></td>' + 
                			'<td><input style="width:98%" id="contractinfodetail_'+j+'_doors_size" name="contractinfodetail['+j+'].doors_size" onchange="cgrow('+j+')" value="'+setnull(result.list[j].DOORS_SIZE)+'" /></td>' +
                			'<td><input style="width:98%" id="contractinfodetail_'+j+'_doors_rent" name="contractinfodetail['+j+'].doors_rent" onchange="cgrow('+j+')" value="'+setnull(result.list[j].DOORS_RENT)+'" data-options="required:true" /></td>' +
                			'<td><input style="width:98%" id="contractinfodetail_'+j+'_doors_rent_sum" readonly name="contractinfodetail['+j+'].doors_rent_sum"  value="'+setnull(result.list[j].DOORS_RENT_SUM)+'" data-options="required:true" /></td>' +
                			'<td><select id="contractinfodetail_'+j+'_rent_rise_type"  name="contractinfodetail['+j+'].rent_rise_type" value="'+setnull(result.list[j].RENT_RISE_TYPE)+'" style="width:100%">' + 
                			'<option value="0">不上浮</option> ' +
                			'<option value="1">按单价</option> ' + 
                			'<option value="2">按总价</option> ' + 
                	        '</select> </td>' + 
                	        '<td><input style="width:98%" id="contractinfodetail_'+j+'_rent_rise_rote" name="contractinfodetail['+j+'].rent_rise_rote" value="'+setnull(result.list[j].RENT_RISE_ROTE)+'"  data-options="required:true" /></td>' +
                			'<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:\'ext-icon-note_add\',plain:true" title="增行" onclick="addrow()"></a> ' +
                			'<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:\'ext-icon-note_delete\',plain:true" title="删行" onclick="delrow('+j+')"></a>'+
                			'<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:\'ext-icon-vcard_edit\',plain:true" title="阶段收费设置"  onclick="editStepFun('+j+')"></a></td>' +
                			'</tr>').appendTo("#gr2");
                    	$("#contractinfodetail_"+j + "_doors_floor").val(result.list[j].DOORS_FLOOR);
                	    $("#contractinfodetail_"+j + "_rent_rise_type").val(result.list[j].RENT_RISE_TYPE);
                	$.parser.parse(targetObj);
                	_changePlusAmout();
                }
				parent.$.messager.progress('close');
				
			}, 'json');
		} else {
			addrow();
		}
			
			$('#doors_id_name').combogrid({    
				valueField:'DOORS_ID',
				width:170,
		    	textField:'DOORS_NAME',
		        fitColumns: true,  
		        striped: true,
		        mode:'remote',
		        editable: true,  
		        rownumbers: true,
		        collapsible: false,
		        fit: true,
		    	url: sy.contextPath + '/base/contract-info-main!doNotNeedSessionAndSecurity_getDoors.sy',  
		    	columns:[[
		  				{field:'DOORS_ID',title:'门市ID',width:60,hidden:true},
		  				{field:'DOORS_NAME',title:'门市名称',width:270} 
		  			]],
		    	onLoadSuccess: function () {
		    		//$('#classfy_id').combogrid('setValue', tax_type);
		    	},
		    	onSelect: function(rowIndex, rowData){ 
		    		$('#doors_id').val(rowData.DOORS_ID);
		    		$('#doors_id_name').combogrid('setValue',rowData.DOORS_NAME);
		    		
		    		//$('#doors_id_name').val(rowData.DOORS_NAME);
		        },
		        onClickRow:function(rowIndex, rowData){
		        	$('#doors_id').val(rowData.DOORS_ID);
		    		$('#doors_id_name').combogrid('setValue',rowData.DOORS_NAME);
		    		setListByProduct(rowData.DOORS_ID);
		        	//$('#classfy_id').val(rowData.CLASSFY_ID);
		        }
			});  

		
		$("#rent_cust_name").combogrid({
			url: sy.contextPath + '/base/contract-info-main!doNotNeedSessionAndSecurity_queryCus.sy',
			panelWidth:580,
			width:170,
		    idField: 'RENT_CUST_NAME',
	        textField: 'RENT_CUST_NAME',
	        fitColumns: true,  
	        striped: true,
	        mode:'remote',
	        editable: true,  
	        rownumbers: true,
	        collapsible: false,
	        fit: true,
			columns:[[
				{field:'RENT_CUST_ID',title:'RENT_CUST_ID',width:60,hidden:true},
				{field:'RENT_CUST_NAME',title:'名称',width:480},    
				{field:'RENT_CUST_CODE',title:'编码',width:100},    
				{field:'TEL',title:'电话',width:150},
				{field:'CONTACT',title:'联系人',width:150},
				{field:'ADDRESS',title:'地址',width:150,hidden:true},
				{field:'RENT_TYPE',title:'租户类型',width:150,hidden:true},
				
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
				$('#tel').val(rowData.TEL);
				$('#address').val(rowData.ADDRESS);
				$('#contact').val(rowData.CONTACT);
				$('#rent_type'+rowData.RENT_TYPE).attr("checked","checked");
			},
	    	onClickRow:function(rowIndex, rowData){
	    		$('#rent_cust_id').val(rowData.RENT_CUST_ID);
		    	$('#rent_cust_code').val(rowData.RENT_CUST_CODE);
				$('#tel').val(rowData.TEL);
				$('#addr').val(rowData.ADDRESS);
				$('#contact').val(rowData.CONTACT);
				$('#rent_type'+rowData.RENT_TYPE).attr("checked","checked");
	    	}
		});
		
	});
	
	/**
	 * 根据选择的门市，调出楼层信息表
	 **/
	function setListByProduct(id){
		parent.$.messager.progress({
			text : '数据加载中....'
		});
		for (i=0;i<count;i++){
		  $('#tr_'+i).remove();
		}
		count = 0;
		$.post(sy.contextPath + '/base/contract-info-main!doNotNeedSecurity_findListById.sy', {
			doors_id : id
		}, function(result) {
		   for(var j=0;j<result.list.length;j++){
			    addrow();
				$("#contractinfodetail_"+j + "_doors_line_id").val(result.list[j].DOORS_LINE_ID);
				$("#contractinfodetail_"+j + "_doors_id").val(result.list[j].DOORS_ID);
				$("#contractinfodetail_"+j + "_doors_floor").val(result.list[j].DOORS_FLOOR);
				$("#contractinfodetail_"+j + "_doors_size").val(result.list[j].DOORS_SIZE);
				$("#contractinfodetail_"+j + "_doors_rent").val(result.list[j].DOORS_RENT);
			}
			parent.$.messager.progress('close');
		}, 'json');
	}
	
	var addrow=function()
	{
		var tablestr='<tr id=tr_'+count+'>'+
		'<td style="display:none"><input style="width:80px" id="contractinfodetail_'+count+'_contract_id" name="contractinfodetail['+count+'].contract_id" /></td>' +
	    '<td style="display:none"><input style="width:80px" id="contractinfodetail_'+count+'_contract_line_id" name="contractinfodetail['+count+'].contract_line_id" /></td>' +
	    '<td style="display:none"><input style="width:80px" id="contractinfodetail_'+count+'_doors_line_id" name="contractinfodetail['+count+'].doors_line_id" /></td>' +
	    '<td style="display:none"><input style="width:80px" id="contractinfodetail_'+count+'_doors_id" name="contractinfodetail['+count+'].doors_id" /></td>' +
	    '<td style="display:none"><input style="width:80px" id="contractinfodetail_'+count+'_is_step_charge" name="contractinfodetail['+count+'].is_step_charge" /></td>' +
		'<td><select style="width:98%" id="contractinfodetail_'+count+'_doors_floor" name="contractinfodetail['+count+'].doors_floor"   data-options="required:true" >' +
		' <option value="1">一楼</option> ' +
		' <option value="2">二楼</option> ' + 
		' <option value="3">三楼</option> ' + 
		' <option value="4">四楼</option> ' + 
		' <option value="5">五楼</option> ' + 
		' <option value="6">六楼</option> ' + 
		' <option value="all">整栋</option> ' + 
        ' </select></td>' + 
		'<td><input style="width:98%" id="contractinfodetail_'+count+'_doors_size" onchange="cgrow('+count+')"  name="contractinfodetail['+count+'].doors_size" /></td>' +
		'<td><input style="width:98%" id="contractinfodetail_'+count+'_doors_rent" onchange="cgrow('+count+')"  name="contractinfodetail['+count+'].doors_rent"   data-options="required:true" /></td>' +
		'<td><input style="width:98%" id="contractinfodetail_'+count+'_doors_rent_sum" readonly name="contractinfodetail['+count+'].doors_rent_sum" data-options="required:true" /></td>' +
		'<td><select id="contractinfodetail_'+count+'_rent_rise_type"  name="contractinfodetail['+count+'].rent_rise_type" style="width:70px">' + 
		'<option value="0">不上浮</option> ' +
		'<option value="1">按单价</option> ' + 
		'<option value="2">按总价</option> ' + 
        '</select> </td>' + 
        '<td><input style="width:98%" id="contractinfodetail_'+count+'_rent_rise_rote" name="contractinfodetail['+count+'].rent_rise_rote" data-options="required:true" /></td>' +
		'<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:\'ext-icon-note_add\',plain:true" title="增行" onclick="addrow()"></a> ' +
		'<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:\'ext-icon-note_delete\',plain:true" title="删行" onclick="delrow('+count+')"></a></td>' +
		'</tr>';
		var targetObj=$(tablestr).appendTo("#gr2");
		$.parser.parse(targetObj);
		count++;
	};
	
	//控制行信息
	function cgrow(rowid) {
		var total=0;
		var a1 = $("#contractinfodetail_" + rowid + "_doors_size").val();
		 var a2 = $("#contractinfodetail_" + rowid + "_doors_rent").val();
		 if(a1 != undefined && a2 != undefined){
			 total = a1 * a2;
		 }
		
		$("#contractinfodetail_"+rowid +"_doors_rent_sum").val(total.toFixed(2));
		_changePlusAmout();
	}	//控制行信息
	
	function _changePlusAmout(){
		var total = 0;
		var stotal = 0;
		for (i=0;i<count;i++){
			 var a1 = $("#contractinfodetail_" + i + "_doors_size").val();
			 var a2 = $("#contractinfodetail_" + i + "_doors_rent").val();
			 var a3 = $("#contractinfodetail_" + i + "_doors_rent_sum").val(); 
			 if(a1 != undefined && a2 != undefined){
				 total = total +  a1 * a2;
				 if(a3 != a1 * a2){
					 stotal = 0;
					 stotal = a1 * a2;
					 $("#contractinfodetail_" + i + "_doors_rent_sum").val(stotal.toFixed(2));
				 }
			 } else {
		    }
		}
		$("#contract_sum").val(total.toFixed(2));
	}
	function delrow(rowid){
		var tb = $("#gr2");
		var tl = tb.find("tr").length;
        if (tl < 3) {
        	alert("最后一行不允许删除！");
        	return;
        }
        var tline_id= $('#contractinfodetail_'+rowid+'_contract_line_id').val();
        if(tline_id == ''){
        	$('#tr_'+rowid).remove();
        } else {
        	parent.$.messager.confirm('询问', '您确定要删除这行记录？', function(r) {
      	    if (r) {
	      		$.post(sy.contextPath + '/base/contract-info-main!doNotNeedSecurity_deleteListById.sy', {
	      			contract_line_id : tline_id
	      		}, function(result) {
	      			if (result.success) {
		      			$('#tr_'+rowid).remove();
					} else {
						parent.$.messager.alert('提示', result.msg, 'error');
					}
	      		
	      			}, 'json');
	      		}
      		});
        	
        	
        }		
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
		<input type="hidden" id="contract_id" name="contractinfomain.contract_id" class="transinput" value="<%=id%>" readonly="readonly" />
		<input type="hidden" id="rent_cust_id" name="contractinfomain.rent_cust_id" class="transinput"  readonly="readonly" />
		<input type="hidden" id="doors_id" name="contractinfomain.doors_id" class="transinput"  readonly="readonly" />
		<input type="hidden" id="is_invoice_mul" name="contractinfomain.is_invoice_mul" class="transinput"  readonly="readonly" />
		<input type="hidden" id="create_by" name="contractinfomain.create_by" class="transinput"  readonly="readonly" />
		<input type="hidden" id="create_date" name="contractinfomain.create_date" class="transinput"  readonly="readonly" />
		
		<legend>新税票填开</legend>
		<div style="font-size:14px;color:#333;">
			<table class="table" style="width: 100%;font-size:14px;color:#333;">
				<tr>
					<th style="width:80px;">租户名称</th>
					<td><input id="rent_cust_name" name="contractinfomain.rent_cust_name" class="easyui-combobox" style="width:100%;" data-options="required:true" /></td>					
					<th style="width:80px;">租户编码</th>
					<td><input id="rent_cust_code" name="contractinfomain.rent_cust_code" style="width:100%;" class="easyui-validatebox" /></td>
					<th style="width:80px;">合同编码</th>
					<td><input id="contract_number" name="contractinfomain.contract_number" style="width:100%;" class="easyui-validatebox" /></td>
				</tr>
				<tr>
					<th>联系电话</th>
					<td><input id="tel" name="contractinfomain.tel" class="easyui-validatebox" style="width:100%;"  /></td>					
					<th>联系人</th>
					<td><input id="contact" name="contractinfomain.contact"  style="width:100%;" class="easyui-validatebox" /></td>
					<th>门市名称</th>
					<td><input id="doors_id_name" name="doors_id_name"  style="width:98%;" class="easyui-combobox" data-options="required:true"/></td>
				</tr>
				<tr>
					<th>门市地址</th>
					<td colspan="3"><input id ="address" name="contractinfomain.address" style="width:100%;" class="easyui-validatebox"   /></td>
					<th>租户类型</th>
					<td> <input id="rent_type1" name="contractinfomain.rent_type" type="radio" value="1" checked="true">临租
                         <input id="rent_type2" name="contractinfomain.rent_type" type="radio" value="2" >长租
					</td>
				</tr>
				<tr>
					<th>开始时间</th>
					<td><input id="start_date" name="contractinfomain.start_date" style="width:170px;" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser,required:true"  /></td>					
					<th>结束时间</th>
					<td><input id="end_date" name="contractinfomain.end_date" style="width:160px;" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser,required:true" /></td>
					<th>缴费方式</th>
					<td><select  id="payment_method" name="contractinfomain.payment_method"  style="width:170px;">
                          <option value="1" selected>按月缴费</option>
                          <option value="2">按季缴费</option>
                          <option value="3">按半年缴费</option>
                          <option value="4">按年缴费</option>
                        </select>
                    </td>
				</tr>
				<tr>
					<th>上浮比列</th>
					<td><input id="rent_rise_rote" name="contractinfomain.rent_rise_rote" style="width:100%;"  data-options="required:true" /></td>					
					<th>合同金额</th>
					<td><input id="contract_sum" name="contractinfomain.contract_sum"  style="width:100%;" readonly class="easyui-validatebox" /></td>
					<th>预收款</th>
					<td><input id="pre_sum" name="contractinfomain.pre_sum"  style="width:100%;" /></td>
				</tr>
			</table>
			<table id="gr2" class="table" style="width: 100%;font-size:14px;color:#333">
				<tr style="height:25px">
					<th style="width:150px;text-align:center">楼层</th>	
					<th style="width:80px;text-align:center">面积</th>			
					<th style="width:80px;text-align:center">租金</th>
					<th style="width:80px;text-align:center">总金额</th>
					<th style="width:60px;text-align:center">上浮方式</th>
					<th style="width:60px;text-align:center">上浮标准</th>
					<th style="width:80px;text-align:center">操作</th>
				</tr>
			</table>
		</div>
		</fieldset>
	</form>
	
  </body>
</html>
