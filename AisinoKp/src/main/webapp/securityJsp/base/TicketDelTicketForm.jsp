<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String id = request.getParameter("id");
	if (id == null) {
		id = "";
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<jsp:include page="../../inc.jsp"></jsp:include>
    <title>My JSP 'TicketDelTicketForm.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script>

var delTicket=function(){
	parent.$.messager.confirm('询问', '您确定要作废当前发票？', function(r) {
		if (r) {
			$.post(sy.contextPath + '/base/ticket-store!doNotNeedSessionAndSecurity_disableInvoice.sy', {
				id : id
			}, function(result) {
				if(result.success){
					messager.show({
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
				}
				else{
					messager.alert('提示', result.msg, 'error');
				}
			}, 'json');
		}
	});
};
</script>
  </head>
  
  <body class="easyui-layout" data-options="fit:true,border:false">
    <form method="post" class="form">
    	<input name="data.id" value="<%=id%>" readonly="readonly" />
    	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-cancel'" onclick="delTicket()">发票作废</a>
    </form>
  </body>
</html>
