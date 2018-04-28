<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
	String id = request.getParameter("id");
	if (id == null) {
		id = "";
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="../../../inc.jsp"></jsp:include>
<title>明细页面</title>
<script type="text/javascript">
var grid;
	
//页面默认加载行为
$(function() {
	var id = $('#id').val();
	if (id.length > 0) {
		$.post(sy.contextPath + '/base/fpmng/order!checkOrder.sy', {id : id} , function(result) {
			if(result.success){
				var data = result.obj[0];
				$("#ohid").val(data.OHID);
				$("#createtime").val(data.CREATETIME);
				$("#bz").val(data.BZ);
				$("#fpdm").val(data.FPDM);
				$("#fphm").val(data.FPHM);
				
				$("#fpzl").combobox("setValue",data.FPZL);
				$("#skr").val(data.SKR);
				$("#fhr").val(data.FHR);
				$("#kpr").val(data.KPR);
				
				$("#gfmc").val(data.GFMC);
				$("#gfdzdh").val(data.GFDZDH);
				$("#gfsh").val(data.GFSH);
				$("#gfyhzh").val(data.GFYHZH);
				
				$("#xfmc").val(data.XFMC);
				$("#xfdzdh").val(data.XFDZDH);
				$("#xfsh").val(data.XFSH);
				$("#xfyhzh").val(data.XFYHZH);
				
				
				$("#has_qd").val(data.HAS_QD);
				$("#amount").val(data.AMOUNT);
				$("#tax_amount").val(data.TAX_AMOUNT);
				$("#total").val(data.TOTAL);
				
				$("#mobile").val(data.MOBILE);
				$("#email").val(data.EMAIL);
			}
		}, 'json');
	}
	//加载明细
	loadItems(id);
});		//页面默认加载行为 - end
	
var loadItems = function(id) {
	grid = $("#grid").datagrid({
		title : '',
		url : sy.contextPath + '/base/fpmng/order!doNotNeedSecurity_checkOrderDetail.sy?id='+id,
		striped : true,
		rownumbers : true,
		pagination : true,
		singleSelect : true,
		//fitColumns:true,
		idField : 'OLID',
		sortOrder : 'desc',
		pageSize : 100,
		pageList : [10, 20, 50, 100, 500 ],
		frozenColumns : [ [
			{width : '20', title : 'OLID', field : 'OLID', hidden : true},
			{width : '30', title : '序号', field : 'SPXH', align : 'center', hidden : true}
		] ],
		columns : [ [
		 	{width : '80', title : '商品号码', field : 'SPHM', halign : 'center', sortable : true},
			{width : '260', title : '商品名称', field : 'SPMC', halign : 'center', sortable : true},
			{width : '130', title : '规格型号', field : 'GGXH', halign : 'center', sortable : true},
			{width : '30', title : '单位', field : 'DW', align : 'center', sortable : true},
			{width : '40', title : '数量', field : 'SL', halign : 'center', sortable : true},
			{width : '60', title : '单价', field : 'DJ', halign : 'center', sortable : true},
			{width : '30', title : '税率', field : 'SLV', halign : 'center', sortable : true},
			{width : '80', title : '金额', field : 'JE', halign : 'center', sortable : true},
			{width : '80', title : '税收分类编码', field : 'SSFLBM', halign : 'center', sortable : true},
		] ]
    });
};
</script>

</head>
<body  class="easyui-layout" data-options="fit:true,border:false">
    <form method="post" class="form">
		<fieldset>
			<legend>单据信息</legend>
			<input type="hidden" id="id" name="id" class="transinput" value="<%=id%>" readonly="readonly" />
			<table class="table" style="width: 100%;font-size:14px">
				<!-- <tr style="display:none"> -->
				<tr>
					<td>单据编号</td>
					<td><input id="ohid" type="text" class="easyui-validatebox" readonly="readonly" /></td>
					<td>单据日期</td>
					<td><input id="createtime" type="text" class="easyui-validatebox" readonly="readonly"/></td>
					<td>发票代码</td>
					<td><input id="fpdm" type="text" class="easyui-validatebox" readonly="readonly"/></td>
					<td>发票号码</td>
					<td><input id="fphm" type="text" class="easyui-validatebox" readonly="readonly"/></td>
				</tr>
				<tr>
					<td style="width:40px">开票种类</td>
					<td><select id="fpzl" class="easyui-combobox" data-options="panelHeight:'auto' ">
							<option value="0">专票</option>
							<option value="2">普票</option>
							<option value="41">卷票</option>
							<option value="51">电子发票</option>
						</select>
					</td>
					<td>收款人</td>
					<td><input id="skr" type="text" readonly="readonly"/></td>
					<td>复核人</td>
					<td><input id="fhr" type="text" readonly="readonly"/></td>
					<td>开票人</td>
					<td><input id="kpr" type="text" readonly="readonly"/></td>
				</tr>
				<tr>
					<td>购方名称</td>
					<td colspan="3"><input id="gfmc" type="text" style="width: 99%;" readonly="readonly" /></td>
					<td>购方税号</td>
					<td><input id="gfsh" type="text" style="width: 99%;" readonly="readonly"/></td>
					<td>合计金额</td>
					<td><input id="amount" type="text" style="width: 99%;" readonly="readonly"/></td>
				</tr>
				<tr>
					<td>银行账号</td>
					<td colspan="3"><input id="gfyhzh" type="text" style="width: 99%;" readonly="readonly"/></td>
					<td>地址电话</td>
					<td colspan="3"><input id="gfdzdh" type="text" style="width: 99%;" readonly="readonly"/></td>
				</tr>	
				<tr>
					<td>客户手机</td>
					<td><input id="mobile" type="text" style="width: 99%;" readonly="readonly"/></td>
					<td>客户邮箱</td>
					<td><input id="email" type="text" style="width: 99%;" readonly="readonly"/></td>
				</tr>		
				<tr>
					<td>销方名称</td>
					<td colspan="3"><input id="xfmc" type="text" style="width: 99%;" readonly="readonly" /></td>
					<td>销方税号</td>
					<td><input id="xfsh" type="text" style="width: 99%;" readonly="readonly"/></td>
					<td>合计税额</td>
					<td><input id="tax_amount" type="text" style="width: 99%;" readonly="readonly"/></td>
				</tr>
				<tr>
					<td>银行账号</td>
					<td colspan="3"><input id="xfyhzh" type="text" style="width: 99%;" readonly="readonly"/></td>
					<td>地址电话</td>
					<td colspan="3"><input id="xfdzdh" type="text" style="width: 99%;" readonly="readonly"/></td>
				</tr>			
 				<tr>
					<td>备注</td>
					<td colspan="5"><input id="bz" type="text" style="width: 99%;" readonly="readonly"/></td>
					<td>税价合计</td>
					<td><input id="total" type="text" style="width: 99%;" readonly="readonly"/></td>
				</tr>
				
			</table>
		</fieldset>
		
 		<fieldset>
			<legend>单据明细</legend>
			<div data-options="region:'center',fit:true,border:false" style="height: 240px;">
				<table id="grid" data-options="fit:true,border:false"></table>
			</div>
		</fieldset>
	</form>
</body>
</html>
