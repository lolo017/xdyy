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
var leaderTreeGrid;
var orgTreeGrid;
$(function() {
	orgTreeGrid = $('#orgTreeGrid').treegrid({
		title : '',
		url : sy.contextPath + '/base/performance!doNotNeedSecurity_orgTreeGrid.sy',
		idField : 'ID',
		treeField : 'NAME',
		parentField : 'SYORGANIZATION_ID',
		rownumbers : true,
		pagination : false,
		//sortName : 'seq',
		sortOrder : 'asc',
		columns : [ [ {
			width : '200',
			title : '公司',
			field : 'NAME'
		}
		]],
		  onLoadSuccess: function () {
			  $('#orgTreeGrid').treegrid('collapseAll');
			  $('#orgTreeGrid').treegrid('expand', $('#orgTreeGrid').treegrid("getRoot").ID);
		  },
		  onClickRow:function(row){
			  $("#queryId").val(row.ID);
			  leaderGrid(row.ID);
		  }
	});      	  
});
function leaderGrid(orgId){
	leaderTreeGrid = $('#leaderTreeGrid').treegrid({
		title : '',
		url : sy.contextPath + '/base/performance!doNotNeedSecurity_leaderTreeGrid.sy',
		queryParams:{
			id:orgId
		},
		idField : 'EMPID',
		treeField : 'NAME',
		parentField : 'LEADERID',
		rownumbers : true,
		pagination : false,
		//sortName : 'seq',
		sortOrder : 'asc',
		toolbar : '#toolbar',
		columns : [ [ 
		              {
			width : '200',
			title : '部门',
			field : 'DEP'
			
		},{
			width : '200',
			title : '名称',
			field : 'NAME'
		},
		
		{
			width : '100',
			title : '业绩管理',
			field : 'action',
			formatter : function(value, row) {
				var str = '';
					str += sy.formatString('<img class="iconImg ext-icon-note" title="查看" onclick="performance(\'{0}\');"/>', row.EMPID);
				return str;
			}
		}, {
			title : '工作成长',
			field : 'communicate',
			width : '100',
			formatter : function(value, row) {
				var str = '';
				str += sy.formatString('<img class="iconImg ext-icon-note" title="查看" onclick="workSelect(\'{0}\');"/>', row.EMPID);
				return str;
			}
		}, {
			title : '专业知识',
			field : 'study',
			width : '100',
			formatter : function(value, row) {
				var str = '';
					str += sy.formatString('<img class="iconImg ext-icon-note" title="查看" onclick="editFun(\'{0}\');"/>', row.EMPID);
				return str;
			}
		} 
		
		]],
		toolbar : '#toolbar',
        onLoadSuccess: function () {
         	$('.iconImg').attr('src', sy.pixel_0);
// 			parent.$.messager.progress('close');
//         	$('#leaderTreeGrid').treegrid('expand', $('#leaderTreeGrid').treegrid("getRoot").EMPID);
        	 $('#leaderTreeGrid').treegrid('collapseAll');
			  $('#leaderTreeGrid').treegrid('expand', $('#leaderTreeGrid').treegrid("getRoot").EMPID);
      	  },
      	
      	onBeforeExpand:function(row){  
            //动态设置展开查询的url  
          // var url = sy.contextPath + '/base/performance!doNotNeedSecurity_treeGridExpand.sy?empId='+row.EMPID;  
          // $("#treeGrid").treegrid("options").url = url;  
           //return true;      
       }  
	
	});
}
function performance(empId){
	var content = '<iframe scrolling="auto" frameborder="0"  src="<%=contextPath%>/securityJsp/base/Performance.jsp?empId='+empId+'"style="border: 0; width: 100%; height: 99%;"></iframe>';     
    var jq = top.jQuery;    
    if (jq("#mainTabs").tabs('exists', "业绩管理")){    
        jq("#mainTabs").tabs('select', "业绩管理");
        var currTab = jq("#mainTabs").tabs('getTab',"业绩管理");  
        jq("#mainTabs").tabs('update', {tab: currTab, options: {content: content, closable: true}});  
    } else {  
          
           jq("#mainTabs").tabs('add',{    
                              title:'业绩管理',
                              iconCls :'ext-icon-page_red' ,   
                              content:content,    
                              closable:true
                            });    
     }    
}

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
    if (jq("#mainTabs").tabs('exists', "专业知识")){    
        jq("#mainTabs").tabs('select', "专业知识");
        var currTab = jq("#mainTabs").tabs('getTab',"专业知识");  
        jq("#mainTabs").tabs('update', {tab: currTab, options: {content: content, closable: true}});  
    } else {  
          
           jq("#mainTabs").tabs('add',{    
                              title:'专业知识',
                              iconCls :'ext-icon-page_red' ,   
                              content:content,    
                              closable:true
                            });    
     }    
};

function leaderQuery(){
	$.post(sy.contextPath + '/base/performance!doNotNeedSecurity_leaderTreeGrid.sy',sy.serializeObject($('#searchForm')),function(data){
		  $('#leaderTreeGrid').treegrid('loadData',data);
		},'json');
}
</script>
</head>
<body >
	
<div style="width:22%;height:100%;float:left;" >
<div style="height:100%;width:100%;" class="easyui-layout" data-options="fit:false,border:false">
	
	<div data-options="region:'center',fit:true,border:false">
		<table id="orgTreeGrid" data-options="fit:true,border:false"></table>
	</div>
</div>
</div>
<div style="width:75%;height:100%;float:left;" >
<div style="height:100%;width:100%;border:1px solid #AAAAAA" class="easyui-layout" data-options="fit:false,border:false">
<div id="toolbar" >
<form id="searchForm">
<input id="queryId" name="id" type="hidden"/>
<table>
<tr>
<td>工号<input name="empId" style="width: 80px;" /></td>
<td>姓名<input name="empName" style="width: 80px;" /></td>
<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="leaderQuery();">查询</a></td>
</tr>
</table>
</form>
</div>
<div data-options="region:'center',fit:true,border:false,title:'人员列表'">
<table id="leaderTreeGrid" data-options="fit:true,border:false"></table>
</div>
</div>

</div>
	
</body>