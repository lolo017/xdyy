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
<style>
html,body{ height:98%;} 
</style>
<script type="text/javascript">
	var grid;
	var empGrid;
	var dpid;
	$(function() {
		grid = $('#grid').treegrid({
			title : '',
			url : sy.contextPath + '/base/emp-data!doNotNeedSecurity_treeGrid.sy',
			idField : 'ID',
			treeField : 'NAME',
			parentField : 'SYORGANIZATION_ID',
			rownumbers : true,
			pagination : false,
			sortName : 'seq',
			sortOrder : 'asc',

			columns :  [ [ {
				width : '100',
				title : '编号',
				field : 'ID',
				hidden:true
			},{
				width : '200',
				title : '名称',
				field : 'NAME'
				//checkbox:true
			} 
			] ],
			
			onBeforeLoad : function(row, param) {
				parent.$.messager.progress({
					text : '数据加载中....'
				});
			},
			onLoadSuccess : function(row, data) {
				$('.iconImg').attr('src', sy.pixel_0);
				parent.$.messager.progress('close');
				$('#grid').treegrid('collapseAll');
				$('#grid').treegrid('expand', $('#grid').treegrid("getRoot").ID);
			},
			onClickRow:function(row){
				dpid=row.ID;
				//判断是否已经加载过
				if($("#isGrid").val()==1){
					
					//如果他是子节点，按posid查询,否则按depid查询
					if(row.LEVELID==3){
				        $('#empGrid').datagrid('options').url =sy.contextPath + '/base/emp-data!doNotNeedSecurity_empDataGridByPosId.sy';  
						$('#empGrid').datagrid('load',{id: row.ID});
					}else{
						$('#empGrid').datagrid('options').url =sy.contextPath + '/base/emp-data!doNotNeedSecurity_empDataGrid.sy';  
						$('#empGrid').datagrid('load',{id: row.ID});
					}
				
				}else{
					//如果他是子节点，按posid查询,否则按depid查询
					if(row.LEVELID==3){
						empGrid(row.ID,sy.contextPath + '/base/emp-data!doNotNeedSecurity_empDataGridByPosId.sy');
					}else{
						empGrid(row.ID,sy.contextPath + '/base/emp-data!doNotNeedSecurity_empDataGrid.sy');
					}
					
				}
				$("#queryId").val(row.ID);
			},
			onBeforeExpand:function(row){  
	            //动态设置展开查询的url  
// 	           var url = sy.contextPath + '/base/emp-data!doNotNeedSecurity_treeGridExpand.sy?id='+row.ID;  
// 	           $("#grid").treegrid("options").url = url;  
// 	           return true;      
	       }  
		});
	});
	function empGrid(id,url){
		empGrid = $('#empGrid').datagrid({
			title : '',
			url : url,
			queryParams:{
				id:id
			},
			fit:true,
			striped : false,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'EMPID',
			sortName : 'EMPID',
			//sortOrder : 'desc',
			pageSize : 50,
			toolbar : '#toolbar',
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
					400, 500 ],
			columns :  [ [ {
						width : '100',
						title : '工号',
						field : 'EMPID',
						align : 'center'
					},
					{
						width : '100',
						title : '姓名',
						field : 'NAME',
						align : 'center'
					},
					{
						width : '100',
						title : '性别',
						field : 'SEX',
						align:'center',
						formatter : function(value,row) {
							if(value==1){
								return "男";
							}
							if(value==0){
								return "女";
							}
						}
					},
					{
						width : '100',
						title : '电话',
						field : 'MOBILE',
						align : 'center'
					},
					{
						width : '100',
						title : '入职日期',
						field : 'INDATE',
						align : 'center',
					    formatter : function(value,row) {
					    	if(typeof(value) !="undefined"){
			      	        	 return value.substr(0,10);
			      	        	 }
			      			}
					},
					{
						width : '200',
						title : '居住地址',
						field : 'ADDRESS',
						align : 'center'
					},
					{
						width : '100',
						title : '操作',
						field : 'ac',
						align : 'center',
						formatter : function(value, row) {
	      					var str = '';
	      					<%if (securityUtil.havePermission("/base/emp-data!update")) {%>
	      						str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="编辑" onclick="editEmp(\'{0}\');"/>', row.EMPID);
	      					<%}%>
	      				
	      					return str;
	      				}
					}
					
					
					] ],
			onLoadSuccess : function(row, data) {
				$('.iconImg').attr('src', sy.pixel_0);
				$("#isGrid").val(1);
			},
			onDblClickRow:function(rowIndex,rowData){
				editEmp(rowData.EMPID);

				}
		});
	}
	function addEmp(){
		
		var dialog = parent.sy.modalDialog({
			title : '添加员工信息',
			url : sy.contextPath + '/securityJsp/base/EmpForm.jsp?dpid='+dpid,
			height:540,
			buttons : [ {
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, empGrid, parent.$,dpid);
				}
			} ]
		});
	}
	function  editEmp(id) {
		var dialog = parent.sy.modalDialog({
			title : '编辑员工信息',
			url : sy.contextPath + '/securityJsp/base/EmpForm.jsp?id=' + id+'&dpid='+dpid,
			height:540,
			buttons : [ {
				text : '保存',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(
							dialog, empGrid, parent.$,dpid);
				}
			} ]
		});
	}
	  
	  function delEmp(id){
		  parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
				if (r) {
					$.post(sy.contextPath + '/base/emp-data!delete.sy', {
						id : id
					}, function() {
						empGrid.datagrid('reload');
					}, 'json');
				}
			});
	  }
</script>
</head>
<body >

<input id="isGrid" type="hidden" value="0"/>
<div style="width:25%;height:100%;float:left;" >
<div style="height:100%;width:100%;" class="easyui-layout" data-options="fit:false,border:false">
	
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</div>
</div>
<div style="width:75%;height:100%;float:left;" >
<div style="height:100%;width:100%;border:1px solid #AAAAAA" class="easyui-layout" data-options="fit:false,border:false">
<div id="toolbar" >
<form id="searchForm">
<input id="queryId" name="id" type="hidden"/>
<table>
<tr><td><a href="javascript:void(0);" class="easyui-linkbutton"
data-options="plain:true,iconCls:'ext-icon-user_add'" onclick="addEmp()">添加</a></td>
<td><a onclick="empGrid.datagrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-arrow_refresh'">刷新</a></td>
<td>工号<input name="empId" style="width: 80px;" /></td>
<td>姓名<input name="empName" style="width: 80px;" /></td>
<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="empGrid.datagrid('load',sy.serializeObject($('#searchForm')));">查询</a></td>
</tr>
</table>
</form>
</div>
<div data-options="region:'center',fit:true,border:false,title:'人员列表'">
<table id="empGrid" data-options="fit:true,border:false"></table>
</div>
</div>

</div>
	
</body>
</html>