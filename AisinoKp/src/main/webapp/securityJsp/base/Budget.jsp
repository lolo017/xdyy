<%@page import="aisino.reportform.model.base.SessionInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="aisino.reportform.util.base.SecurityUtil"%>
<%
	String contextPath = request.getContextPath();
	SecurityUtil securityUtil = new SecurityUtil(session);
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute("sessionInfo");
	String orgid=sessionInfo.getUser().getUserorgid();
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<script type="text/javascript" src="<%=contextPath%>/jslib/My97DatePicker4.8Beta3/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var grid;
	
	$(function() {
		$("#year").val(getCurrentDate().substring(0,4));
		grid = $('#grid').datagrid({
			title : '',
			url : sy.contextPath + '/base/specific!doNotNeedSecurity_getSname.sy',
			queryParams: {
				year: getCurrentDate().substring(0,4),
			},
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'VALUE',
			//sortName : 'budgetid',
			sortOrder : 'asc',
			pageSize : 800,
			pageList : [ 800,1600],
			remoteSort: false,
			columns : [ [ {
				width : '200',
				title : 'ID',
				field : 'VALUE',
				hidden:true
			}, {
				width : '200',
				title : '名称',
				field : 'TEXT'
			},
			{
				width : '200',
				title : '状态',
				field : 'CONFIRM',
				 formatter : function(value,row) {
				       if(value=="1"){
				    	   return "已审核(无法修改)";
				       }else{
				    	   return "未审核(可以修改)";
				       }
			}
			},
			{
				title : '操作',
				field : 'action',
				width : '200',
				formatter : function(value, row) {
					var str = '';
						str += sy.formatString('<img class="iconImg ext-icon-note" title="查看" onclick="lookSpecific(\'{0}\');"/>', row.VALUE);
						str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="编辑" onclick="editSpecific(\'{0}\');"/>', row.VALUE);
					<%if (securityUtil.havePermission("/base/specific!delSpecific")) {%>
						str += sy.formatString('<img class="iconImg ext-icon-note_delete" title="删除" onclick="delSpecific(\'{0}\',\'{1}\');"/>', row.VALUE,row.TEXT);
					<%}%>
					<%if (securityUtil.havePermission("/base/specific!conSpecific")) {%>
					str += sy.formatString('<img class="iconImg ext-icon-tick" title="审核" onclick="conSpecific(\'{0}\',\'{1}\');"/>', row.VALUE,row.TEXT);
				<%}%>
				<%if (securityUtil.havePermission("/base/specific!backSpecific")) {%>
				str += sy.formatString('<img class="iconImg ext-icon-arrow_undo" title="反审核" onclick="backSpecific(\'{0}\',\'{1}\');"/>', row.VALUE,row.TEXT);
			    <%}%>
					return str;
				}
			}
			]],
			toolbar : '#toolbar',
			onLoadSuccess : function(data) {
				
				$('.iconImg').attr('src', sy.pixel_0);
				parent.$.messager.progress('close');
				//将表头设置为当前查询对应的年份
// 				$(".datagrid-header-row").children('td[field="directives"]').next().find("div").html($('#year').combobox('getValue'));
// 				$(".datagrid-header-row").children('td[field="directives"]').next().next().find("div").html(Number($('#year').combobox('getValue'))+1);

			}
		});
	});
	
	function lookSpecific(sid){
		var dialog = parent.sy.modalDialog({
			title : '查看预算汇总',
			url : sy.contextPath + '/securityJsp/base/BudgetForm.jsp?sid='+sid,
			width:1300,
			height:600,
			onDestroy: function(){

			}
		});
	}

	function addSpecific(){
		var dialog = parent.sy.modalDialog({
			title : '添加预算汇总',
			url : sy.contextPath + '/securityJsp/base/BudgetForm.jsp?',
			width:1300,
			height:600,
			buttons : [ {
				text : '保存',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ],
			onDestroy: function(){

			}
		});
	}
	function editSpecific(sid){
	
			$.post(sy.contextPath + '/base/specific!doNotNeedSecurity_isEdit.sy',{sid:sid},
					function(result) {
				if(result.success){
					var dialog = parent.sy.modalDialog({
						title : '编辑预算汇总',
						url : sy.contextPath + '/securityJsp/base/BudgetForm.jsp?sid='+sid,
						width:1300,
						height:600,
						buttons : [ {
							text : '保存',
							handler : function() {
								dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
								
							}
						} ]
					});
				}else{
					parent.$.messager.alert('提示', result.msg, 'info');
				}
				
		}, 'json');
		
		
	}
	function delSpecific(sid,name){
		parent.$.messager.confirm('询问', '您确定要删除<b style="font-size:17px">'+name+'</b>吗？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/base/specific!delSpecific.sy', {
					sid : sid
				}, function(result) {
					querySpecific();
					if(result.success){
						parent.$.messager.alert('提示', result.msg, 'info');
					}
				}, 'json');
			}
		});
	}
	function conSpecific(sid,name){
		parent.$.messager.confirm('询问', '您确定要将 <b style="font-size:17px">'+name+'</b> 通过审核吗？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/base/specific!conSpecific.sy', {
					sid : sid
				}, function(result) {
					if(result.success){
						parent.$.messager.alert('提示', result.msg, 'info');
						grid.datagrid('load',sy.serializeObject($('#searchForm')));
					}
				}, 'json');
			}
		});
	}
	function backSpecific(sid,name){
		parent.$.messager.confirm('询问', '您确定要将 <b style="font-size:17px">'+name+'</b> 反审核吗？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/base/specific!backSpecific.sy', {
					sid : sid
				}, function(result) {
					if(result.success){
						parent.$.messager.alert('提示', result.msg, 'info');
						grid.datagrid('load',sy.serializeObject($('#searchForm')));
					}
				}, 'json');
			}
		});
	}
	
	function querySpecific(){
		grid.datagrid('load',sy.serializeObject($('#searchForm')));
	}
	</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">

	
	<div id="toolbar" style="display: none;" >
	<form id="searchForm">
<table>
<tr>
<td>
<a href="javascript:void(0);" class="easyui-linkbutton"data-options="plain:true,iconCls:'ext-icon-disk'" onclick="addSpecific()">添加</a>

</td>
<td>
年份:
<input name="year" id="year"  value="" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy'})" style="width: 120px;" />
</td>
<td>
<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="querySpecific();">查询</a>
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