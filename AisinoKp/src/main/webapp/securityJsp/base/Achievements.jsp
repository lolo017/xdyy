<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="aisino.reportform.util.base.SecurityUtil"%>
	<%@ page import="aisino.reportform.model.base.SessionInfo"%>
<%
	String contextPath = request.getContextPath();
	SecurityUtil securityUtil = new SecurityUtil(session);
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute("sessionInfo");
	String empId=sessionInfo.getUser().getEmpId();
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
	String date= format.format(new Date());
%>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript" src="<%=contextPath%>/jslib/My97DatePicker4.8Beta3/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
<script type="text/javascript">
var grid;
var empId='${param.empId}';
	
$(function() {
	
	var pic=$("#pic").val();
	grid = $('#grid').datagrid({
		title : '',
		url : sy.contextPath +  '/base/jxzj!grid.sy',
		queryParams:{
			syempdataByEmpdataid:empId,
			zbcjsj:pic
		},
		//method:'get',
		fit:true,
		striped : false,
		rownumbers : true,
		pagination : true,
		singleSelect : true,
		idField : 'id',
		sortName : 'id',
		//sortOrder : 'desc',
		pageSize : 50,
		toolbar : '#toolbar',
		pageList : [50, 100,150,200 ],
		columns : [ [
          {
	         width : '160',
	         title : '姓名',
	         field : 'name',
	         align : 'center',
	         formatter : function(value,row) {
				       return row.syempdataByEmpdataid.name;
			}

           },
          {
  	         width : '160',
  	         title : '指标创建时间',
  	       	field : 'zbcjsj',
	         align : 'center'
	        
             },
              
             {
    	         width : '160',
    	         title : '指标名称',
    	         field : 'zbmc',
    	         align : 'center'
    	        
               },
               /*
               {
      	         width : '160',
      	         title : '岗位',
      	         field : 'position',
      	         align : 'center'
                 },
                 {
          	         width : '160',
          	         title : '评价时间',
          	         field : 'perDate',
          	         align : 'center',
          	         formatter : function(value,row) {
      	        	    return value.substr(0,10);
      			        }
                     },*/
                     {  title : '操作',
               	         width : '160',
               	         field : 'ac',
               	      formatter : function(value, row) {
      					var str = '';
      					
      					<%if (securityUtil.havePermission("/base/performance!update")) {%>
      						str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="编辑" onclick="editPer(\'{0}\');"/>', row.id);
      					<%}%>
      					<%if (securityUtil.havePermission("/base/performance!delete")) {%>
  						str += sy.formatString('<img class="iconImg ext-icon-note_delete" title="删除" onclick="delPer(\'{0}\');"/>', row.id);
  					<%}%>
      					return str;
      				}
                       } ]],
                       onBeforeLoad : function(row, param) {
           				parent.$.messager.progress({
           					text : '数据加载中....'
           				});
           			},
                       onLoadSuccess : function(row, data) {
           				$('.iconImg').attr('src', sy.pixel_0);
           				parent.$.messager.progress('close');
           			},
           			onDblClickRow:function(rowIndex,rowData){
           				editPer(rowData.id);

						}


	});
});

function addPer(){
	//alert(empId);
	if(empId!='<%=empId%>'){
		$.messager.alert('提示','请使用个人账号填写！'); 
		return false;
	}
	var pid=empId;
	var dialog = parent.sy.modalDialog({
		title : '添加指标',
		url : sy.contextPath + '/securityJsp/base/JxzbForm.jsp?empId='+pid,
		width:1000,
		height:600,
		buttons : [ {
			text : '保存',
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
			}
		} ]
	});
}

  function  editPer(id) {
	var dialog = parent.sy.modalDialog({
		title : '编辑绩效信息',
		url : sy.contextPath + '/securityJsp/base/JxzbForm.jsp?id=' + id,
		width:1000,
		height:600,
		buttons : [ {
			text : '编辑',
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.submitForm(
						dialog, grid, parent.$);
			}
		} ]
	});
}
  
  function delPer(id){
	  parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/base/jxzj!delete.sy', {
					id : id
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
  }
</script>
</head>
<body>
<div style="height:100%;width:100%;" class="easyui-layout" data-options="fit:true,border:false">
<div id="toolbar" >
	<form id="searchForm">
<table>
			<tr>
			<td><a href="javascript:void(0);" class="easyui-linkbutton"
data-options="plain:true,iconCls:'ext-icon-disk'" onclick="addPer()">添加</a></td>
<td>
<input name="QUERY_t#zbcjsj_S_EQ"   id="Picker" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM'})" readonly="readonly" style="width: 120px;" />
<input type="hidden" name="QUERY_t#syempdataByEmpdataid.empId_S_EQ" id="empids" value='${param.empId}'>
<td>
<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">查询</a>

</td>
			
			</tr>
</table>
</form>
</div>
<input type="hidden" name="data.zbcjsj" id="pic" value=<%=date %> class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM'})" readonly="readonly" style="width: 158px;" />
<div id="preDiv" data-options="region:'center',fit:true,border:false,title:'业绩管理'">
<table id="grid" data-options="fit:true,border:false">

</table>

</div>

</div>
</body>
</html>