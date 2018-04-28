<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();%>
<%String contextPath = request.getContextPath();%>
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

function addPlsc(){
	$.messager.progress({ 
		title:'请稍后', 
		msg:'数据提交中...'
		});
	$("#plscAdd_form").form("submit",{
		 onSubmit: function () {
			 var flag = $("#plscAdd_form").form('validate');
			 var fileN = $("#plsc_file").val();
			 	if(fileN.indexOf(".xls") == -1){
			 		alert("请正确选择EXCEL文件！");
				 	flag = false;
			 	}
			 	if(!flag)
			 		$.messager.progress('close');
                return flag;
             },
		url:sy.contextPath + '/base/order!save.sy',
	    success:function(data){ 
	    	 $.messager.progress('close');
	    	var data = eval('(' + data + ')'); 
	    	if(data.success){
	    		 $.messager.alert('系统提示', '导入成功！', 'info');
	    		 $("#plsc_file").val(null);
	    	}else{
	    		$.messager.alert('系统提示', '导入失败...', 'error');
	    	}
	    }
	});
}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" >
 
	<div data-options="region:'center'">
	<form id="plscAdd_form" method="post" enctype="multipart/form-data">
    <table width="60%" border="0" cellpadding="10" cellspacing="0" style="margin:0 auto;" class="listtable">
	    <tr>
	        <td align="center" width="30%" class="pagedistd">上传文件</td>
	        <td class="pagetd">
	        	<input type="file" id="plsc_file" class="easyui-filebox" name="file" data-options="prompt:'点击选择文件'" data-options="required:true"/>
	        </td>
	    </tr>
	    <tr>
	    	<td></td>
	    	<td>
	    		<a href="#" class="easyui-linkbutton" style="align:center;" iconCls="icon-save" onclick="addPlsc();">上   传</a> &nbsp;&nbsp;&nbsp;&nbsp;
	    	</td>
	    </tr>
	    <tr>
	    	<td colspan="2" align="left" style="padding-left:20%;font-size:14px;">
	    	
	    	</td>
	    </tr>
	   </table>
	   </form>
</div>
</div>
</body>
</html>