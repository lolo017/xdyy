<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="aisino.reportform.util.base.SecurityUtil"%>
<%
	String contextPath = request.getContextPath();
	SecurityUtil securityUtil = new SecurityUtil(session);
%>
<%
	String id = request.getParameter("id");
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
	var grid;
	//沟通评价
	
	var addPer=function(id){
		var id='<%=id%>';
		var dialog = parent.sy.modalDialog({
			title : '编辑用户信息',
			url : sy.contextPath + '/securityJsp/base/WorkForm.jsp?id=' + id,
			width:1050,
			height:600,
			buttons : [ {
				text : '保存',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	
	var removeFun = function(id) {
		parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/base/work!delete.sy', {
					id : id
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	
	var Evaluation = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '编辑用户信息',
			url : sy.contextPath + '/securityJsp/base/WorkFormUpdate.jsp?id=' + id,
			width:1050,
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
		var id='<%=id%>';
		grid = $('#grid').datagrid({
			title : '',
			url : sy.contextPath + '/base/work!grids.sy?id='+id,
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'id',
			sortName : 'pjsj',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			frozenColumns : [ [ 
			                    
			                    {
				width : '80',
				title : '编号',
				field : 'id',
				hidden: true,
				sortable : true
			},{
				width : '100',
				title : '姓名',
				field : 'name',
				sortable : true
			
			}, {
				width : '100',
				title : '与直接上级沟通',
				field : 'sjgt',
				sortable : true
			} ] ],
			columns : [ [ {
				width : '100',
				title : '与下级沟通',
				field : 'xjgt',
				sortable : true
			}, 
			{
				width : '120',
				title : '与分子公司沟通',
				field : 'gzgsgt',
				sortable : true
			}, 
			{
				width : '120',
				title : '与其它部门沟通',
				field : 'qtbmgt',
				sortable : true
			}, 
			{
				width : '100',
				title : '工作中新学会了什么',
				field : 'xhlsm',
				sortable : true
			}, 
			{
				width : '120',
				title : '参与了哪些工作变革',
				field : 'cjbg',
				sortable : true
			},
			{
				width : '120',
				title : '推动了哪些工作变革',
				field : 'tdbg',
				sortable : true
			},
			{
     	         width : '160',
     	         title : '时间',
     	         field : 'datadetails',
     	         align : 'center',
     	         formatter : function(value,row) {
 	        	    return value.substr(0,10);
 			        }
                },
			{
				title : '操作',
				field : 'action',
				width : '90',
				formatter : function(value, row) {
					var str = '';
				
						str += sy.formatString('<img class="iconImg ext-icon-note" title="查看" onclick="Evaluation(\'{0}\');"/>', row.id);
						<%if (securityUtil.havePermission("/base/work!delete")) {%>
					str += sy.formatString('<img class="iconImg ext-icon-delete" title="删除" onclick="removeFun(\'{0}\');"/>', row.id);
			
					<%}%>
					return str;
				}
			} ] ],
			toolbar : '#toolbar',
			onBeforeLoad : function(param) {
				parent.$.messager.progress({
					text : '数据加载中....'
				});
			},
			onLoadSuccess : function(data) {
				$('.iconImg').attr('src', sy.pixel_0);
				parent.$.messager.progress('close');
			},
			onDblClickRow:function(rowIndex,rowData){
				Evaluation(rowData.id);

				}
		});
	});
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div id="toolbar" style="display: none;">
		<table>
			<tr>
				<td>
					<a href="javascript:void(0);" class="easyui-linkbutton"
  data-options="plain:true,iconCls:'ext-icon-disk'" onclick="addPer()">添加</a>
				</td>
			</tr>
			<tr>
				<td>
					
				</td>
			</tr>
		</table>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>