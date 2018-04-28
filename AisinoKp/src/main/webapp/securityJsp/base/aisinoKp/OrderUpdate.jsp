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
<title>单据明细页面</title>
<script type="text/javascript">
var grid;

var effectRow = new Object();
var editIndex = undefined;
function endEditing(){
    if (editIndex == undefined){return true;}
    if ($('#grid').datagrid('validateRow', editIndex)){

        $('#grid').datagrid('endEdit', editIndex);
        editIndex = undefined;
        return true;
    } else {
        return false;
    }
}
function onClickRow(index){
    if (editIndex != index){
        if (endEditing()){
            $('#grid').datagrid('selectRow', index)
                    .datagrid('beginEdit', index);
            editIndex = index;
        } else {
            $('#grid').datagrid('selectRow', editIndex);
        }
    }
}
function submitform(){
	
    if (endEditing()){
    	//var rows = $('#grid').datagrid('getChanges');
    	var olidArray=new Array();
    	var ssflbmArray=new Array();
    	var sphmArray=new Array();
    	var spmcArray=new Array();
    	var ggxhArray=new Array();
    	var dwArray=new Array();
    	var slArray=new Array();
    	var djArray=new Array();
    	var slvArray=new Array();
    	var jeArray=new Array();
    	var updated = $('#grid').datagrid('getChanges', 'updated');
    	if (updated.length) { 
    		$.each(updated,function(index,element){
    			olidArray.push(element.OLID);
    			//ssflbmArray.push(element.SSFLBM);
    			//sphmArray.push(element.SPHM);
    			//spmcArray.push(element.SPMC);
    			//ggxhArray.push(element.GGXH);
    			//dwArray.push(element.DW);
    			//slArray.push(element.SL);
    			//slvArray.push(element.SLV);
    			//djArray.push(element.DJ);
    			//jeArray.push(element.JE);
    			
    			if(element.JE==''){
    				jeArray.push(" ");
    			}else{
    				jeArray.push(element.JE);
    			}
    			
    			if(element.DJ==''){
    				djArray.push(" ");
    			}else{
    				djArray.push(element.DJ);
    			}
    			
    			if(element.SLV==''){
    				slvArray.push(" ");
    			}else{
    				slvArray.push(element.SLV);
    			}
    			
    			if(element.SL==''){
    				slArray.push(" ");
    			}else{
    				slArray.push(element.SL);
    			}
    			
    			if(element.DW==''){
    				dwArray.push(" ");
    			}else{
    				dwArray.push(element.DW);
    			}
    			
    			if(element.GGXH==''){
    				ggxhArray.push(" ");
    			}else{
    				ggxhArray.push(element.GGXH);
    			}
    			
    			if(element.SPMC==''){
    				spmcArray.push(" ");
    			}else{
    				spmcArray.push(element.SPMC);
    			}
    			
    			if(element.SPHM==''){
    				sphmArray.push(" ");
    			}else{
    				sphmArray.push(element.SPHM);
    			}
    			
    			if(element.SSFLBM==''){
    				ssflbmArray.push(" ");
    			}else{
    				ssflbmArray.push(element.SSFLBM);
    			}
    			
    		});
    	}
    	
    	$.post(sy.contextPath + '/base/fpmng/order!editOrderDetail.sy', 
    		{	ohid:$("#ohid").val(),fpzl:$("#fpzl").combobox('getValue'),kpr:$("#kpr").val(),
    			gfmc:$("#gfmc").val(),gfdzdh:$("#gfdzdh").val(),gfsh:$("#gfsh").val(),gfyhzh:$("#gfyhzh").val(),
    			skr:$("#skr").val(),fhr:$("#fhr").val(),mobile:$("#mobile").val(),email:$("#email").val(),
    			olids:olidArray.join(','),ssflbms:ssflbmArray.join(','),sphms:sphmArray.join(','),spmcs:spmcArray.join(','),
    			ggxhs:ggxhArray.join(','),dws:dwArray.join(','),sls:slArray.join(','),slvs:slvArray.join(','),
    			djs:djArray.join(','),jes:jeArray.join(','),bz:$("#bz").val()}, 
    			function(data) { 
    				if(data.success){ 
    					parent.$.messager.alert('成功', data.msg);
    					window.location.reload();
    				}
    			}, 'json'); 
        //$('#grid').datagrid('acceptChanges');
        //$('#grid').datagrid('reload');
        
    }
}
function reject(){
    $('#grid').datagrid('rejectChanges');
    editIndex = undefined;
}
/* function getChanges(){
    var rows = $('#grid').datagrid('getChanges');
    alert(rows.length+' rows are changed!');
} */

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
		onClickRow : onClickRow,
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
		 	{width : '80', title : '商品号码', field : 'SPHM', halign : 'center', sortable : true,editor:{type:'text',options: { required: true}}},
			{width : '260', title : '商品名称', field : 'SPMC', halign : 'center', sortable : true,editor:{type:'validatebox',options: { required: true}}},
			{width : '130', title : '规格型号', field : 'GGXH', halign : 'center', sortable : true,editor:{type:'text',options: { required: true}}},
			{width : '30', title : '单位', field : 'DW', align : 'center', sortable : true,editor:{type:'text',options: { required: true}}},
			{width : '40', title : '数量', field : 'SL', halign : 'center', sortable : true,editor:{type:'validatebox',options: { required: true,number:true}}},
			{width : '60', title : '单价', field : 'DJ', halign : 'center', sortable : true,editor:{type:'validatebox',options: { required: true}}},
			{width : '30', title : '税率', field : 'SLV', halign : 'center', sortable : true,editor:{type:'validatebox',options: { required: true}}},
			{width : '80', title : '金额', field : 'JE', halign : 'center', sortable : true,editor:{type:'validatebox',options: { required: true}}},
			{width : '80', title : '税收分类编码', field : 'SSFLBM', halign : 'center', sortable : true,
					editor:{type:'text',options: { required: true} }
			},
		] ]
    });
};
</script>

</head>
<body  class="easyui-layout" data-options="fit:true,border:false" style="overflow:auto;">
	
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
					<td><input id="skr" type="text" /></td>
					<td>复核人</td>
					<td><input id="fhr" type="text" /></td>
					<td>开票人</td>
					<td><input id="kpr" type="text" /></td>
				</tr>
				<tr>
					<td>购方名称</td>
					<td colspan="3"><input id="gfmc" type="text" style="width: 99%;" /></td>
					<td>购方税号</td>
					<td><input id="gfsh" type="text" style="width: 99%;" /></td>
					<td>总金额</td>
					<td><input id="amount" type="text" style="width: 99%;" readonly="readonly"/></td>
				</tr>
				<tr>
					<td>银行账号</td>
					<td colspan="3"><input id="gfyhzh" type="text" style="width: 99%;" /></td>
					<td>地址电话</td>
					<td colspan="3"><input id="gfdzdh" type="text" style="width: 99%;" /></td>
				</tr>
				<tr>
					<td>客户手机</td>
					<td><input id="mobile" type="text" style="width: 99%;" /></td>
					<td>客户邮箱</td>
					<td><input id="email" type="text" style="width: 99%;" /></td>
				</tr>				
				<tr>
					<td>销方名称</td>
					<td colspan="3"><input id="xfmc" type="text" style="width: 99%;" readonly="readonly" /></td>
					<td>销方税号</td>
					<td><input id="xfsh" type="text" style="width: 99%;" readonly="readonly"/></td>
					<td>税额</td>
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
					<td colspan="5"><input id="bz" type="text" style="width: 99%;"/></td>
					<td>税价合计</td>
					<td><input id="total" type="text" style="width: 99%;" readonly="readonly"/></td>
				</tr>
			</table>
		</fieldset>
		
 		<fieldset>
			<legend>单据明细</legend>
			<div data-options="region:'center',fit:true,border:false,onDblClickCell:onClickCell" style="height: 240px;">
				<table id="grid" data-options="fit:true,border:false"></table>
			</div>
		</fieldset>
	</form>
	<div align="right" style="margin-top: -10px;margin-right: 10px">
		<a class="easyui-linkbutton"
						data-options="iconCls:'ext-icon-disk'"
						onclick="submitform();"  >提交修改</a>
	</div>
</body>
</html>