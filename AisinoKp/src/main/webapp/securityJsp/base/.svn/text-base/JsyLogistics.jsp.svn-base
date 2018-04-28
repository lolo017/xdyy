<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String contextPath = request.getContextPath();
// SessionInfo sessionInfo = (SessionInfo) session.getAttribute("sessionInfo");
//   String userId=sessionInfo.getUser().getId();

%>
<%
	String id = request.getParameter("did");
	if (id == null) {
		id = "";
	}
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
var id='${param.did}';
var submitForm = function($dialog, $grid, $pjq,dpid) {
	if ($('form').form('validate')) {
		var url;
		if (id!="") {
			url = sy.contextPath + '/base/jsy!doNotNeedSecurity_saveLogistics.sy';
		} 
		$.post(url, sy.serializeObject($('form')), function(result) {
			if (result.success) {
				$dialog.dialog('destroy');
			} else {
				$pjq.messager.alert('提示', result.msg, 'error');
			}
		}, 'json');
	}
};

$(function() {
	$("#status").combobox('setValue', '');

	var grid = $('#grid').datagrid({
		title : '',
		url : sy.contextPath + '/base/jsy!doNotNeedSecurity_getSpmx.sy',
		queryParams: {
			id: id
		},
		striped : false,
		rownumbers : true,
		pagination : true,
		singleSelect : true,
		idField : "ID",
		sortName : "ID",
		//sortOrder : sort_order,
		//sortable:false,
		checkOnSelect : false,
		selectOnCheck : false,
		nowrap :false,
		//fitColumns : true,
		remoteSort: false,
		pageSize : 50,
		toolbar : '#toolbar',
		pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
				400, 500 ],
		onLoadSuccess:function(data) {
		}
	});
});


</script>
</head>
<body style="">
<div style="height:420px;width:100%;">
	<form method="post" class="form">
		<fieldset>
			<legend>录入物流信息</legend>
			<table class="table" style="width: 100%;">
			
			        <tr >
					<th>订单编号</th>
					<td><input name="id"  readonly="true" value="<%=id%>" style="width: 200px" /></td>
					<th>物流公司</th>
					<td><input id="dm" class="easyui-combobox" name="dm"   style="width: 200px"  data-options="editable:false,required:true,valueField:'VALUE',textField:'TEXT',url:'<%=contextPath%>/base/jsy!doNotNeedSecurity_getDm.sy'" /> 
					</tr>
				
				<tr>
					<th>物流单号</th>
					<td>
					<input name="no" class="easyui-validatebox" style="width: 200px" data-options="required:true" /></td>
					</td>
					<th>物流状态</th>
					<td>
					<select id="status" name="status" class="easyui-combobox" style="width: 200px" panelHeight="100" data-options="required:true,editable: false"  value=""/>
					<option value="0">邮寄中</option>   
                    <option value="1">投递成功</option>
					</select>
					</td>
				</tr>
				<tr>
				<th>明细</th>
				<td colspan="3" >
				<textarea name="mx" style="width: 200px;height: 30px"></textarea>
				</td>
				</tr>
				

			</table>
		</fieldset>

		
	</form>
	<div style="width:100%;height:300px" class="easyui-layout" data-options="fit:true,border:false">
	
	<div id="preDiv" data-options="region:'center',fit:true,border:false,title:'商品列表'"  >
<table id="grid" data-options="fit:false,border:false"  style="height:260px">
<thead id="pre">  
        <tr>
        <th  width="100" sortable='true' align='center' field="ID">订单编号</th>
        <th  width="100" sortable='true' align='center' field="SPDM">商品代号</th>
        <th  width="100" sortable='true' align='center' field="MC">名称</th>
        <th  width="100" sortable='true' align='center' field="PRICE">单价</th>
        <th  width="100" sortable='true' align='center' field="AMOUNT">数量</th>
        <th  width="100" sortable='true' align='center' field="TOTAL">总价</th>
        </tr>
 </thead>
</table>

</div>
</div>
</div>
</body>
</html>