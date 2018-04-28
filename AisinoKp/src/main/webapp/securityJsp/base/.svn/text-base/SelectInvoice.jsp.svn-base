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
var is_open = 0;
var a;
var showAlertWindow = function(message) {
	parent.$.messager.alert('提示', message, 'error');
};
//打开金穗卡
function opencar(){
  if(is_open==0){
      a=new ActiveXObject("TaxCardX.GoldTax");	      
	  a.OpenCard();
	  if(a.RetCode=="1011" || a.RetCode=="3001"){
		 is_open=1;
         var mess="金税盘开启成功！";
		 alert(mess);
		}else{
		 alert("金税盘开启失败，错误信息："+a.RetMsg);
		}
      }else{
          alert('金税盘已打开，无需再打开！');
      }
}
//发票关闭
function closecar(){
	is_open=0;
	a.CloseCard();
	alert('金穗盘已关闭');
}
//发票作废
function invoicezf(){
		// grid.datagrid('uncheckAll');	
	    	  var INVOICE_TYPE="";
	          var INVOICE_NUMBER="";
	 	     var selectGird= $('#grid').datagrid('getSelections');	 	     
	 	     if(selectGird.length ==0){
	 	    	 alert("没有选中，无法作废！");
	 	    	 return;  	 
	 	     } 	 		
	 	    a.InvListInit();
	 	    for(var i=0;i<selectGird.length;i++){	
	 	    	a.InfoKind='0';
	 	    	a.InfoTypeCode=selectGird[i].INVOICE_TYPE;
	 	    	a.InfoNumber=selectGird[i].INVOICE_NUMBER;	 	    	
	 	    	a.CancelInv();	 	    	
	 	    	if(a.RetCode==6001){
	 	    		INVOICE_TYPE = INVOICE_TYPE+selectGird[i].INVOICE_TYPE+",";
	 		    	INVOICE_NUMBER = INVOICE_NUMBER+selectGird[i].INVOICE_NUMBER+",";
	 	    	}else{
	 	    		alert('发票代码：'+selectGird[i].INVOICE_TYPE+'发票号码：'+selectGird[i].INVOICE_NUMBER+'作废失败,失败代码：'+a.RetCode+',失败原因:'+a.RetMsg);	   		 
	 	   		 return;	 	   		 
	 	    	}	 	    	
	 	    }	
	 	    $.post(sy.contextPath + '/base/korder-list!doNotNeedSecurity_doInvoiceZF.sy',
	 					{bill_Id: INVOICE_TYPE,line_id:INVOICE_NUMBER},function(result) {
	 				  if(result.success){
	 					  $.messager.alert('提示',result.msg,'success');
	 				  }else{
	 					  $.messager.alert('提示',result.msg,'error');
	 				  }
	 				},'json');	        
}
$(function() {
	grid = $('#grid').datagrid({
				title : '',
				url : sy.contextPath + '/base/korder-list!doNotNeedSessionAndSecurity_selectgrid.sy',
				striped : true,
				rownumbers : true,
				pagination : true,
				singleSelect : false,
				sortOrder : 'desc',
				pageSize : 20,
				pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,400, 500 ],
				frozenColumns : [ [ {
							width : '80',
							title : '单据',
							field : 'FInterID',
							sortable : true,
							hidden : true
						},
						{
							width : '80',
							title : '单据',
							field : 'FEntryID',
							sortable : true,
							hidden : true
						},
						{
							width : '80',
							title : '客户ID',
							field : 'FCustID',
							sortable : true,
							hidden : true
						},
						{
							width : '120',
							title : '单据编号',
							field : 'FBillNo',
							sortable : true
						},
						
						{
							width : '120',
							title : '发票代码',
							field : 'INVOICE_TYPE',
							sortable : true
						},
						{
							width : '120',
							title : '发票号码',
							field : 'INVOICE_NUMBER',
							sortable : true
						}
						,
						{
							width : '120',
							title : '客户名称',
							field : 'CUST_NAME',
							sortable : true
						}] ],
						columns : [ [
						{
							width : '80',
							title : '客户编码',
							field : 'CUST_CODE',
							sortable : true,
							hidden : true
						},
						{
							width : '80',
							title : '地址电话',
							field : 'FADDRESS',
							sortable : true,
							hidden : true
						},
						{
							width : '80',
							title : '银行帐号',
							field : 'FBanK',
							sortable : true,
							hidden : true
						},
						{
							width : '80',
							title : '开票时间',
							field : 'CDATE',
							sortable : true
						},
						{
							width : '100',
							title : '产品编码',
							field : 'FNumber',
							sortable : true
						},
						{
							width : '120',
							title : '产品名称',
							field : 'FName',
							sortable : true
						},
						{
							width : '90',
							title : '规格型号',
							field : 'FModel',
							sortable : true
						},
						{
							width : '70',
							title : '单位',
							field : 'UNITNAME',
							sortable : true
						},
						{
							width : '70',
							title : '数量',
							field : 'FQty',
							sortable : true
						},
						{
							width : '70',
							title : '单价',
							field : 'FPrice',
							sortable : true
						},
						{
							width : '70',
							title : '税率',
							field : 'FCESS',
							sortable : true
						},
						{
							width : '70',
							title : '税额',
							field : 'FSE',
							sortable : true
						},
						{
							width : '80',
							title : '建单人',
							field : 'BILLNAME',
							sortable : true,
							hidden : true
						}
						,
						{
							width : '80',
							title : '复核人',
							field : 'CHECKNAME',
							sortable : true,
							hidden : true
						}
						]],
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
		<form id="searchForm">
			<table style="font-size:12px;">
				<tr>
				    <td>订单号</td>
					<td><input name="bill_Id" style="width: 100px;" /></td>
					<td>发票号码</td>
					<td><input name="bill_No" style="width: 100px;" /></td>
					<td>客户名称</td>
					<td><input name="cust_name" style="width: 100px;" /></td>
				
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'ext-icon-zoom',plain:true"
						onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">查询</a>
						<a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'ext-icon-zoom_out',plain:true"
						onclick="$('#searchForm input').val('');$('#statusselector').val('999');grid.datagrid('load',{});">重置</a>	
					</td>
				</tr>
				<tr>
					<td colspan="7">
					    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-brick_go'" onclick="opencar()">打开金税卡</a>&nbsp;&nbsp; 
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-brick_go'" onclick="invoicezf()">发票作废</a>&nbsp;&nbsp;
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-brick_go'" onclick="closecar()">关闭金税卡</a>&nbsp;&nbsp;
					</td>
				</tr>				
			</table>
		</form>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>