<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="aisino.reportform.util.base.SecurityUtil"%>
<%
	String contextPath = request.getContextPath();
	SecurityUtil securityUtil = new SecurityUtil(session);
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
var grid;

var editemp=function(id){
	
	var dialog = parent.sy.modalDialog({
		title : '新增用户信息',
		url : sy.contextPath + '/securityJsp/base/EmpForm.jsp?id=' + id,
		width:800,
		height:600,
		buttons : [ {
			text : '保存',
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
			}
		} ]
	});
	
};


$(function() {
	grid = $('#treeGrid').treegrid({
		title : '',
		url : sy.contextPath + '/base/performance!doNotNeedSecurity_treeGrid.sy',
		idField : 'empId',
		treeField : 'empName',
		parentField : 'pid',
		rownumbers : true,
		pagination : false,
		//sortName : 'seq',
		sortOrder : 'asc',
		frozenColumns : [ [ {
			width : '200',
			title : '名称',
			field : 'empName'
		} ] ],
		columns : [ [ {
			width : '200',
			title : '部门',
			field : 'depName'
		},
		{
			width : '200',
			title : '操作',
			field : 'ac',
			formatter : function(value, row) {
				var str = '';
				<%if (securityUtil.havePermission("/base/emp!update")) {%>
					str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="编辑" onclick="editemp(\'{0}\');"/>', row.empId);
				<%}%>
				return str;
			}
		}
		
		]],
		toolbar : '#toolbar',
        onLoadSuccess: function () {
        	$('.iconImg').attr('src', sy.pixel_0);
			parent.$.messager.progress('close');
        	$('#treeGrid').treegrid('expand', $('#treeGrid').treegrid("getRoot").empId);
        	
      	  },
      	onBeforeLoad : function(row, param) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
		},
      	onClickRow:function(){
      		
      	},
      	onBeforeExpand:function(row){  
            //动态设置展开查询的url  
           var url = sy.contextPath + '/base/performance!doNotNeedSecurity_treeGridExpand.sy?empId='+row.empId;  
           $("#treeGrid").treegrid("options").url = url;  
           return true;      
       }  
	
	});
});
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div id="toolbar" style="display: none;">
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="treeGrid" data-options="fit:true,border:false"></table>
	</div>
</body>