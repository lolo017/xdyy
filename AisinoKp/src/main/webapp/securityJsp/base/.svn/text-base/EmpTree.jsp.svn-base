<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String contextPath = request.getContextPath();

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
var changeGrid;
var method="${param.method}";
var id="${param.id}";
var url;
$(function() {
	if(method=="orgTreeGrid"){
		url=sy.contextPath + '/base/emp-data!doNotNeedSecurity_orgTreeGrid.sy';
		tree1();
	}
	if(method=="depTreeGrid"){
		url=sy.contextPath + '/base/emp-data!doNotNeedSecurity_depTreeGrid.sy?id='+id;
		tree1();
	}
	if(method=="posTreeGrid"){
		url=sy.contextPath + '/base/emp-data!doNotNeedSecurity_posTreeGrid.sy?id='+id;
		tree1();
	}
	if(method=="leaderTreeGrid"){
		url=sy.contextPath + '/base/emp-data!doNotNeedSecurity_leaderTreeGrid.sy';
		tree2();
	}

});
function tree1(){
	changeGrid = $('#changeGrid').treegrid({
		title : '',
		url : url,
		idField : 'ID',
		treeField : 'NAME',
		parentField : 'SYORGANIZATION_ID',
		rownumbers : true,
		pagination : false,
		sortName : 'SEQ',
		//sortOrder : 'asc',
		columns :  [ [ {
			width : '100',
			title : '编号',
			field : 'ID',
			checkbox:true
			//hidden:true
		},{
			width : '100',
			title : 'level',
			field : 'LEVELID',
			hidden:true
		},
		{
			width : '200',
			title : '名称',
			field : 'NAME'
		} 
		] ],
		onLoadSuccess : function(row, data) {
			$('#changeGrid').treegrid('collapseAll');
			$('#changeGrid').treegrid('expand', $('#changeGrid').treegrid("getRoot").ID);
		}
	});

}

function tree2(){
	changeGrid = $('#changeGrid').treegrid({
		title : '',
		url : url,
		idField : 'EMPID',
		treeField : 'NAME',
		parentField : 'LEADERID',
		rownumbers : true,
		pagination : false,
		//sortName : 'SEQ',
		sortOrder : 'asc',
		toolbar : '#toolbar',
		columns :  [ [ {
			width : '100',
			title : '编号',
			field : 'EMPID',
			checkbox:true
		},
		{
			width : '200',
			title : '名称',
			field : 'NAME'
		},
		{
			width : '200',
			title : '工号',
			field : 'ID'
		} 
		
		] ],
		onLoadSuccess : function() {
			//$('#changeGrid').treegrid('collapseAll');
			//$('#changeGrid').treegrid('expand', $('#changeGrid').treegrid("getRoot").EMPID);
		},
		onBeforeExpand:function(row){  
            //动态设置展开查询的url  
           var url = sy.contextPath + '/base/emp-data!doNotNeedSecurity_leaderTreeGridExpand.sy?id='+row.EMPID;  
           $("#changeGrid").treegrid("options").url = url;  
           return true;      
       }  
	});

}
function saveChange($dialog){
	var array= new Array();
	array[0]=$('#changeGrid').treegrid('getSelected').ID;
	array[1]=$('#changeGrid').treegrid('getSelected').NAME;
	if(method=="leaderTreeGrid"){
		
		array[0]=$('#changeGrid').treegrid('getSelected').EMPID;
		array[1]=$('#changeGrid').treegrid('getSelected').NAME;
	}else{
		if($('#changeGrid').treegrid('getSelected').LEVELID!="2"&&method=="depTreeGrid"){
			return false;
		}
		if($('#changeGrid').treegrid('getSelected').LEVELID!="3"&&method=="posTreeGrid"){
			return false;
		}
	
	}
	$dialog.dialog('destroy');
	return array;
}

function conQuery(){
	var url = sy.contextPath + '/base/emp-data!doNotNeedSecurity_leaderTreeGrid.sy';
    $("#changeGrid").treegrid("options").url = url;  
	changeGrid.treegrid('load',sy.serializeObject($('#searchForm')));
}
</script>
</head>
<body>
<div style="height:100%;width:100%;" class="easyui-layout" data-options="fit:true,border:false">
<div data-options="region:'center',fit:true,border:false,title:'列表'">
<table id="changeGrid" data-options="fit:true,border:false">
<div id="toolbar" style="display: none;">
<form id="searchForm">
工号:<input type="text" style="width:80px" name="qid" />
姓名:<input type="text" style="width:80px" name="qname"/>
</form>
<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="conQuery();">查询</a>
	
</div>
</table>
</div>
</div>
</body>
</html>
