<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String OPEN_ID = request.getParameter("open_id");
	if (OPEN_ID == null) {
		OPEN_ID = "";
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<jsp:include page="../../inc.jsp"></jsp:include>
    <title>My JSP 'TicketAddOrEditCustomer.jsp' starting page</title>
    <script type="text/javascript">
// 	var submitNow = function($dialog, $grid, $pjq) {
// 		var url;
// 		url = sy.contextPath + '/base/opencustomer!doNotNeedSecurity_SaveOrUpdate.sy';
// 		$.post(url, sy.serializeObject($('form')), function(result) {
// 			parent.sy.progressBar('close');//关闭上传进度条

// 			if (result.success) {
// 				$pjq.messager.alert('提示', result.msg, 'info');
// 				$grid.datagrid('load');
// 				$dialog.dialog('destroy');
// 			} else {
// 				$pjq.messager.alert('提示', result.msg, 'error');
// 			}
// 		}, 'json');
// 	};
// 	var submitForm = function($dialog, $grid, $pjq) {
// 		if ($('form').form('validate')) {
// 			submitNow($dialog, $grid, $pjq);
// 		}
// 	};
	
	
 	$(function() {
		
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


// 		if ($(':input[name="data.open_id"]').val().length > 0) {
// 			parent.$.messager.progress({
// 				text : '数据加载中....'
// 			});
// 			$.post(sy.contextPath + '/base/opencustomer!doNotNeedSecurity_getCusInfoById.sy', {
// 				open_id : $(':input[name="data.open_id"]').val()
// 			}, function(result) {
// 				if (result.OPEN_ID != undefined) {
// 					$('form').form('load', {
// 						'data.open_id' : result.OPEN_ID,
// 						'data.cust_code' : result.CUST_CODE,
// 						'data.cust_name' : result.CUST_NAME,
// 						'data.limit_amount' : result.LIMIT_AMOUNT,
// 						'data.start_date' : result.START_DATE,
// 						'data.end_date' : result.END_DATE,
// 						'data.is_valid' : result.IS_VALID,
// 						'data.valid_date' : result.VALID_DATE,
// 						'data.create_date' : result.CREATE_DATE,
// 						'data.create_by' : result.CREATE_BY,
// 						'data.update_date' : result.UPDATE_DATE,
// 						'data.update_by' : result.UPDATE_BY
						
// 					});
// 				}
// 				parent.$.messager.progress('close');
// 			}, 'json');
// 		}
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

	 var mLingyong = function(){
	    	
	    	var fpdm = $('#fpdm').val();
	    	if(fpdm==''){
	    		parent.$.messager.alert('提示', '批量分配，需要输入发票代码！', 'error');
	    		return;
		    }
	    	var fphm1 = $('#fphm1').val();
	    	var fphm2 = $('#fphm2').val();
	    	if(fphm1 == '' || fphm2 == ''){
	    		parent.$.messager.alert('提示', '批量分配，需要输入发票号码段！', 'error');
	    		return;
	    	}
	    	else{
	    		parent.$.messager.progress({
	    			text : '请稍后....'
	    		});
	    		var url;
	    		url = sy.contextPath + '/base/ticket-store!doNotNeedSessionAndSecurity_mlingyong.sy';
	    		$.post(url,sy.serializeObject($("#form").form()), function(result) {
	    			parent.$.messager.progress('close');
	    			if (result.success) {
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
							//grid.datagrid('clearChecked');
	    			}
	    			else {
	    				parent.$.messager.alert('提示', result.msg, 'error');
	    			}
	    		}, 'json');
	    	}
	    };
	    
	 var notmLingyong = function(){
	    	
	    	var fpdm = $('#fpdm').val();
	    	if(fpdm==''){
	    		parent.$.messager.alert('提示', '批量分配，需要输入发票代码！', 'error');
	    		return;
		    }
	    	var fphm1 = $('#fphm1').val();
	    	var fphm2 = $('#fphm2').val();
	    	if(fphm1 == '' || fphm2 == ''){
	    		parent.$.messager.alert('提示', '批量分配，需要输入发票号码段！', 'error');
	    		return;
	    	}
	    	else{
	    		parent.$.messager.progress({
	    			text : '请稍后....'
	    		});
	    		var url;
	    		url = sy.contextPath + '/base/ticket-store!doNotNeedSessionAndSecurity_notmlingyong.sy';
	    		$.post(url,sy.serializeObject($("#form").form()), function(result) {
	    			parent.$.messager.progress('close');
	    			if (result.success) {
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
							//grid.datagrid('clearChecked');
	    			}
	    			else {
	    				parent.$.messager.alert('提示', result.msg, 'error');
	    			}
	    		}, 'json');
	    	}
	    };
	    
	    function cgrow(){
	    	var amount = $("#amount").val();
	    	var fphm1 = $("#fphm1").val();
	    	$("#fphm2").val(parseInt(amount)- parseInt(1)+ parseInt(fphm1));
	    }
	    
    </script>
  </head>
  
  <body>
    <form method="post" class="form" id="form">
	<div class="easyui-panel"  data-options="fit:true,border:false" style="width:300px;padding:20px 60px">
      <div style="margin-bottom:10px">
        
               <input  id="org_id" type="hidden" name="org_id" class="transinput" readonly="readonly" />
<!--           <input type="hidden" name="data.is_valid" class="transinput"  readonly="readonly" /> -->
<!--           <input type="hidden" name="data.valid_date" class="transinput"  readonly="readonly" /> -->
<!--           <input type="hidden" name="data.create_date" class="transinput"  readonly="readonly" /> -->
<!--           <input type="hidden" name="data.create_by" class="transinput"  readonly="readonly" /> -->
<!--           <input type="hidden" name="data.update_date" class="transinput"  readonly="readonly" /> -->
<!--           <input type="hidden" name="data.update_by" class="transinput"  readonly="readonly" /> -->
<!--           <input name="data.cust_code" class="easyui-validatebox textbox"  data-options="required:true"  style="width:100%;height:20px"> -->
       </div>
       <div style="margin-bottom:10px">
        <div>发票代码</div>
        <input id="fpdm" name="fpdm" class="easyui-validatebox textbox"  data-options="required:true"  style="width:300px;height:20px">
       	</div>
       	<div style="margin-bottom:10px">
       	  <div>发票号码开始段</div>
       	    <input  id="fphm1" name="fphm1"  class="easyui-validatebox textbox" data-options="required:true"  style="width:300px;height:20px">
       	</div>
       	<div style="margin-bottom:10px">
       	  <div>分配数量</div>
       	    <input id="amount" name="amount"  onchange="cgrow()" class="easyui-validatebox textbox"  style="width:300px;height:20px">
       	</div>
       	<div style="margin-bottom:10px">
       	  <div>发票号码结束段</div>
       	    <input  id="fphm2" name="fphm2"  class="easyui-validatebox textbox" data-options="required:true"  style="width:300px;height:20px">
       	</div>
       	<div style="margin-bottom:10px">
       	  <div>分配人</div>
       	    <input id="lyr" name="lyr" class="easyui-combobox" data-options="required:true"  style="width:300px;height:25px">
       	</div>
       	<div style="margin-botton:20px">
       	   <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-brick_go'" onclick="mLingyong()">批量分配</a>&nbsp;&nbsp;&nbsp; 
       	   <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-brick_go'" onclick="notmLingyong()">批量撤销分配</a>
		  </div>
     
	</form>
  </body>
</html>
