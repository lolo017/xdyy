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
    
    <title>My JSP 'TicketFply.jsp' starting page</title>
    <script type="text/javascript">
    
    var idontwanttheinvoice=function(id){
    	parent.$.messager.confirm('询问','是否退回当前的税票？',function(r) {
			if (r) {
				$.post(sy.contextPath+ '/base/ticket-store!doNotNeedSessionAndSecurity_givebackInvoice.sy',{
					id : id
					},function(result) {
						if (result.success) {
							parent.$.messager.show({
								title : '提示',
								height:'300px',
								msg : result.msg,
								showType : 'slide',
								timeout : 5000,
								style : {
									right : '',
									top : document.body.scrollTop
											+ document.documentElement.scrollTop,
									bottom : ''
								}
							});
							grid.datagrid('reload');
						} else {
							parent.$.messager.alert('提示',result.msg,'error');
						}
					}, 'json');
				}
			});
    };
    
    var grid;
    
    $(function() {
		grid = $('#grid').datagrid(
						{
							title : '',
							url : sy.contextPath + '/base/ticket-store!doNotNeedSessionAndSecurity_gridunlingyong.sy',
							striped : true,
							rownumbers : true,
							pagination : true,
							singleSelect : true,
							idField : 'ID',
							sortName : 'LRRQ',
							sortOrder : 'desc',
							pageSize : 20,
							pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
									400, 500 ],
							frozenColumns : [ [ {
								width : '100',
								title : '票据ID',
								field : 'ID',
								sortable : true,
								hidden:true
							}, 
							{ field:'CK',
								checkbox:true
							},
							{
								width : '180',
								title : '名称',
								field : 'NAME',
								sortable : true
							} ] ],
							columns : [ [
									{
										width : '120',
										title : '字轨',
										field : 'FPDM',
										sortable : true
									},
									{
										width : '80',
										title : '税票号码',
										field : 'FPHM',
										sortable : true
									},
									{
										width : '50',
										title : '数量',
										field : 'COUNT',
										sortable : true,
										hidden:true
									},
									{
										width : '150',
										title : '录入日期',
										field : 'LRRQ',
										sortable : true
									},
									{
										width : '80',
										title : '领用状态',
										field : 'LYR',
										sortable : true,
										formatter : function(value, row, index) {
											switch (value) {
											case '0':
												return '未领用';
											case '1':
												return '已被领用';
											}
										}
									},
									{
										width : '150',
										title : '领用日期',
										field : 'LYSJ',
										sortable : true
									},
									{
										title : '操作',
										field : 'action',
										width : '80',
										formatter : function(value, row) {
											var str = '';
											if (row.LYR == '1') {
												str += sy.formatString('<a title="退回当前领用的空白发票" href="javascript:void(0);" onclick="idontwanttheinvoice(\'{0}\');">退回</a>&nbsp;&nbsp;',row.ID);
											}
											return str;
										}
									}
									] ],
							singleSelect: true,
							selectOnCheck: false,
							checkOnSelect: false,
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
		$("#lyr").combogrid({
			url: sy.contextPath + '/base/ticket-store!doNotNeedSecurity_popUsrInfo.sy',
			panelWidth:200,
			panelHeigh:100,
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
	});
    
    var Lingyong = function(){
    	var checkedItems = $('#grid').datagrid('getChecked');
    	var names = [];
    	$.each(checkedItems, function(index, item){
    	names.push(item.ID);
    	});
    	if(names==''){
    		parent.$.messager.alert('提示', '请勾选税票', 'error');
	    }
    	else{
    		parent.$.messager.progress({
    			text : '请稍后....'
    		});
    		var url;
    		url = sy.contextPath + '/base/ticket-store!doNotNeedSessionAndSecurity_lingyong.sy';
    		$.post(url,{id:names.join(",")}, function(result) {
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
						grid.datagrid('clearChecked');
    			}
    			else {
    				$pjq.messager.alert('提示', result.msg, 'error');
    			}
    		}, 'json');
    	}
    };
    
    var notLingyong = function(){
    	var checkedItems = $('#grid').datagrid('getChecked');
    	var names = [];
    	$.each(checkedItems, function(index, item){
    	names.push(item.ID);
    	});
    	if(names==''){
    		parent.$.messager.alert('提示', '请勾选税票', 'error');
	    }
    	else{
    		parent.$.messager.progress({
    			text : '请稍后....'
    		});
    		var url;
    		url = sy.contextPath + '/base/ticket-store!doNotNeedSessionAndSecurity_notlingyong.sy';
    		$.post(url,{id:names.join(",")}, function(result) {
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
						grid.datagrid('clearChecked');
    			}
    			else {
    				parent.$.messager.alert('提示', result.msg, 'error');
    			}
    		}, 'json');
    	}
    };
    
    var mLingyong = function(){
    	
    	var fpdm = $('#fpdm').val();
    	if(fpdm==''){
    		parent.$.messager.alert('提示', '批量领用，需要输入发票代码！', 'error');
    		return;
	    }
    	var fphm1 = $('#fphm1').val();
    	var fphm2 = $('#fphm2').val();
    	if(fphm1 == '' || fphm2 == ''){
    		parent.$.messager.alert('提示', '批量领用，需要输入发票号码段！', 'error');
    		return;
    	}
    	else{
    		parent.$.messager.progress({
    			text : '请稍后....'
    		});
    		var url;
    		url = sy.contextPath + '/base/ticket-store!doNotNeedSessionAndSecurity_mlingyong.sy';
    		$.post(url,{fpdm:fpdm,fphm1:fphm1,fphm2:fphm2}, function(result) {
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
    		parent.$.messager.alert('提示', '批量领用，需要输入发票代码！', 'error');
    		return;
	    }
    	var fphm1 = $('#fphm1').val();
    	var fphm2 = $('#fphm2').val();
    	if(fphm1 == '' || fphm2 == ''){
    		parent.$.messager.alert('提示', '批量领用，需要输入发票号码段！', 'error');
    		return;
    	}
    	else{
    		parent.$.messager.progress({
    			text : '请稍后....'
    		});
    		var url;
    		url = sy.contextPath + '/base/ticket-store!doNotNeedSessionAndSecurity_notmlingyong.sy';
    		$.post(url,{fpdm:fpdm,fphm1:fphm1,fphm2:fphm2}, function(result) {
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
    
</script>
  </head>
  
  <body class="easyui-layout" data-options="fit:true,border:false">
  <div id="toolbar" style="display: none;">
  <form id="searchForm">
	<table style="font-size:12px;">
		<tr>
		<td>发票代码</td>
		<td><input id="fpdm" name="fpdm" style="width: 100px;" /></td>
		<td>发票号码</td>
		<td><input id="fphm1" name="fphm1" style="width: 100px;" /> - <input id="fphm2" name="fphm2" style="width: 100px;" /></td>
		<td>领用人</td>
		<td><input id="lyr" name="lyr" style="width: 100px;" />
		<input id="org_id" name="org_id" style="width: 100px;display:none;" />
		</td>
		
		<td>筛选领用状态</td>
		<td>
			<select id="statusselector" name="status">
				<option value ="0">未领用</option>
  				<option value ="1">已领用</option>
			</select>
		</td>
		<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">过滤</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');$('#statusselector').val('0');grid.datagrid('load',{});">重置过滤</a>
		</td>
		</tr>
		<tr>
			<td align="center">请勾选税票</td>
			<td colspan="3">
			<a href="javascript:void(0);" class="easyui-linkbutton" 
			data-options="iconCls:'ext-icon-arrow_right'" onclick="Lingyong()">够选领用</a>  
			<a href="javascript:void(0);" class="easyui-linkbutton" 
			data-options="iconCls:'ext-icon-arrow_right'" onclick="notLingyong()">够选取消领用</a>
			</td>
<!-- 			<td align="center" colspan="2">请输入查询条件</td> -->
<!-- 			<td colspan="2"> -->
<!-- 			<a href="javascript:void(0);" class="easyui-linkbutton"  -->
<!-- 			data-options="iconCls:'ext-icon-arrow_right'" onclick="mLingyong()">批量分配</a> -->
<!-- 			<a href="javascript:void(0);" class="easyui-linkbutton"  -->
<!-- 			data-options="iconCls:'ext-icon-arrow_right'" onclick="notmLingyong()">批量取消分配</a> -->
<!-- 			</td> -->
		</tr>
	</table>
	</form>
  </div>
    <div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
  </body>
</html>
