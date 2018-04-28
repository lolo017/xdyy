<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="aisino.reportform.util.base.SecurityUtil"%>
<%@ page import="aisino.reportform.model.base.SessionInfo"%>
<%
	String contextPath = request.getContextPath();
	SecurityUtil securityUtil = new SecurityUtil(session);
	SessionInfo sessionInfo = (SessionInfo) session
			.getAttribute("sessionInfo");
	String empId = sessionInfo.getUser().getEmpId();
%>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	
</script>
</head>
<body>
	<div style="height:100%;width:100%;" class="easyui-layout"
		data-options="fit:true,border:false,title:'基本信息'">

		<div id="preDiv"
			data-options="region:'center',fit:true,border:false,title:'关怀信息'">
			<table style="width:100%;">
				<tr>
					<td>服务单位名称:</td><td><input name='zbqz_gr_sy' class='easyui-validatebox' data-options='required:true'/></td>
					<td></td><td></td>
					<td>客户名称:</td><td><input name='zbqz_gr_sy' class='easyui-validatebox' data-options='required:true'/></td>
				</tr>
				<tr>
					<td>客户联系人:</td><td><input name='zbqz_gr_sy' class='easyui-validatebox' data-options='required:true'/></td>
					<td>固定电话:</td><td><input name='zbqz_gr_sy' class='easyui-validatebox' data-options='required:true'/></td>
					<td>手机号码:</td><td><input name='zbqz_gr_sy' class='easyui-validatebox' data-options='required:true'/></td>
				</tr>
				<tr>
					<td colspan="6">
						<a href="#" class="easyui-linkbutton" data-options="toggle:true"> 修  改 </a>
					</td>
				</tr>
			</table>
			
			<hr style="border:1px solid #CDCDCD;width:100%;"/>
			
			<table id="grid" data-options="fit:true,border:false">
				<tr>
					<td>指导操作：</td>
				</tr>
				<tr>
					<td><input type="radio" class='easyui-validatebox' name=""/>云平台使用、发票开具、申报、认证及其他</td>
				</tr>
				<tr>
					<td><a href="#" class="easyui-linkbutton" data-options="toggle:true">  保   存   </a></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>