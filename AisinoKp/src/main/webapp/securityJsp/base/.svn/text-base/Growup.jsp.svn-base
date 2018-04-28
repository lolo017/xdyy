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

	//沟通评价
	var workSelect = function(id) {
		var content = '<iframe scrolling="auto" frameborder="0"  src="<%=contextPath%>/securityJsp/base/WorkSelect.jsp?id=' + id+'"style="border: 0; width: 100%; height: 99%;"></iframe>';     
	    var jq = top.jQuery;    
	    if (jq("#mainTabs").tabs('exists', "工作成长")){    
	        jq("#mainTabs").tabs('select', "工作成长");
	        var currTab = jq("#mainTabs").tabs('getTab',"工作成长");  
	        jq("#mainTabs").tabs('update', {tab: currTab, options: {content: content, closable: true}});  
	    } else {  
	          
	           jq("#mainTabs").tabs('add',{    
	                              title:'工作成长',
	                              iconCls :'ext-icon-page_red' ,   
	                              content:content,    
	                              closable:true
	                            });    
	     }    
	};
	
	//系统学习		
	var editFun = function(id) {
		var content = '<iframe scrolling="auto" frameborder="0"  src="<%=contextPath%>/securityJsp/base/StudySelect.jsp?id=' + id+'"style="border: 0; width: 100%; height: 99%;"></iframe>';     
	    var jq = top.jQuery;    
	    if (jq("#mainTabs").tabs('exists', "系统学习")){    
	        jq("#mainTabs").tabs('select', "系统学习");
	        var currTab = jq("#mainTabs").tabs('getTab',"系统学习");  
	        jq("#mainTabs").tabs('update', {tab: currTab, options: {content: content, closable: true}});  
	    } else {  
	          
	           jq("#mainTabs").tabs('add',{    
	                              title:'系统学习',
	                              iconCls :'ext-icon-page_red' ,   
	                              content:content,    
	                              closable:true
	                            });    
	     }    
	};
	
	
	$(function() {
		grid = $('#grid').treegrid({
			title : '',
			url : sy.contextPath + '/base/emplead!treeGrid.sy',
			idField : 'empId',
			treeField : 'empName',
			parentField : 'pid',
			rownumbers : true,
			pagination : false,
			//sortName : 'seq',
			sortOrder : 'asc',
			frozenColumns : [ [ 
			                    {
				width : '200',
				title : 'id',
				field : 'empId',
			    hidden:true
			} ,{
				width : '200',
				title : '名称',
				field : 'empName'				
			}] ],
			columns : [ [ {
				width : '200',
				title : '部门',
				field : 'depName'
			}, {
				title : '系统学习',
				field : 'study',
				width : '60',
				formatter : function(value, row) {
					var str = '';
					<%if (securityUtil.havePermission("/base/study!grids")) {%>
						str += sy.formatString('<img class="iconImg ext-icon-note" title="查看" onclick="editFun(\'{0}\');"/>', row.empId);
					<%}%>
					return str;
				}
			} , {
				title : '工作成长',
				field : 'communicate',
				width : '60',
				formatter : function(value, row) {
					var str = '';
					<%if (securityUtil.havePermission("/base/work!grids")) {%>
					str += sy.formatString('<img class="iconImg ext-icon-note" title="查看" onclick="workSelect(\'{0}\');"/>', row.empId);
				    <%}%>
					return str;
				}
			}
			
			]],
			toolbar : '#toolbar',
			onLoadSuccess : function(row, data) {
				$('.iconImg').attr('src', sy.pixel_0);
				parent.$.messager.progress('close');
				$('#grid').treegrid('expand', $('#grid').treegrid("getRoot").empId);
			},
			onBeforeExpand:function(row){  
	            //动态设置展开查询的url  
	           var url = sy.contextPath + '/base/emplead!treeGridExpand.sy?empId='+row.empId;  
	           $("#grid").treegrid("options").url = url;  
	           return true;      
	       },
			onBeforeLoad : function(row, param) {
				parent.$.messager.progress({
					text : '数据加载中....'
				});
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
					<form id="searchForm">
						<table>
							
						</table>
					</form>
				</td>
			</tr>
		</table>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>