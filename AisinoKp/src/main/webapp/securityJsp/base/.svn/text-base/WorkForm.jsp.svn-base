<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="aisino.reportform.util.base.SecurityUtil"%>
<%@ page import="aisino.reportform.model.base.SessionInfo"%>
<%
	String contextPath = request.getContextPath();
	SecurityUtil securityUtil = new SecurityUtil(session);
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute("sessionInfo");
	String name=sessionInfo.getUser().getName();
%>
<%    
	String id = request.getParameter("id");
	if (id == null) {
		id = "";
	}
%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	
	var submitNow = function($dialog, $grid, $pjq) {
		var url = sy.contextPath + '/base/work!save.sy';
		
		$.post(url, sy.serializeObject($('form')), function(result) {
			parent.sy.progressBar('close');//关闭上传进度条

			if (result.success) {
				$pjq.messager.alert('提示', result.msg, 'info');
				$grid.datagrid('load');
				$dialog.dialog('destroy');
			} else {
				$pjq.messager.alert('提示', result.msg, 'error');
			}
		}, 'json');
	};
	var submitForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {	
				submitNow($dialog, $grid, $pjq);
		}
	};
	$(function() {

	});
</script>
</head>
<body>
	<form method="post" class="form">
	<input type="hidden"  name="data.name" value="<%=name%>"/>
		<fieldset>
			<legend>沟通</legend>
			<table class="table" style="width: 100%;">
			<%if (securityUtil.havePermission("/base/work!ordinary")&&!securityUtil.havePermission("/base/work!othermanager")) {%>
				<tr>
				
					<th style="text-align:center;">与直接上级沟通</th>
					<td>
					<input type="hidden" id="usersid"  name="data.empId" value="<%=id%>"/>
					<textarea style=" width: 480px; height: 80px;" name="data.sjgt"></textarea>
					</td>
				</tr>
				<%if (securityUtil.havePermission("/base/work!otherleader")) {%>
				<tr>
					<th style="text-align:center;">与其他非分管领导沟通</th>
					<td><textarea style=" width: 480px; height: 80px;" name="data.fgldgt"></textarea></td>
				</tr>
				<%}%>
				<%if (securityUtil.havePermission("/base/work!subordinate")) {%>
				<tr>
					<th style="text-align:center;">与下级沟通</th>
					<td><textarea style=" width: 480px; height: 80px;" name="data.xjgt"></textarea></td>
				</tr>
				<%}%>
				<tr>
					<th style="text-align:center;">与分子公司沟通</th>
					<td><textarea style=" width: 480px; height: 80px;" name="data.gzgsgt"></textarea></td>
				</tr>
				
				<tr>
				<th style="text-align:center;">与其它部门沟通</th>
				<td><textarea style=" width: 480px; height: 80px;" name="data.qtbmgt"></textarea></td>
				</tr>
					<tr>
					<th style="text-align:center;">与外部相关部门、合作伙伴沟通</th>
				<td><textarea style=" width: 480px; height: 80px;" name="data.hzhbgt"></textarea></td>
				</tr>
				<tr>
				
					<th style="text-align:center;">工作中新学会了什么？</th>
					<td>
				
					<textarea style=" width: 480px; height: 80px;" name="data.xhlsm"></textarea></td>
					</tr>
					<tr>
					<th style="text-align:center;">将知识运用到工作中解决了什么问题？</th>
					<td><textarea style=" width: 480px; height: 80px;" name="data.jjwt"></textarea></td>
				</tr>					
				<tr>
				
					<th style="text-align:center;">参与了哪些工作变革</th>
					<td>
				
					<textarea style=" width: 480px; height: 80px;" name="data.cjbg"></textarea></td>
					</tr>
					<tr>
					<th style="text-align:center;">推动了哪些工作变革</th>
					<td><textarea style=" width: 480px; height: 80px;" name="data.tdbg"></textarea></td>
				</tr>
				<tr>
					<th style="text-align:center;">被总经理采纳</th>
					<td><textarea style=" width: 480px; height: 80px;" name="data.jjlcn"></textarea></td>
					</tr>
					<tr>
					<th style="text-align:center;">被分管领导采纳</th>
					<td><textarea style=" width: 480px; height: 80px;" name="data.fgldcn"></textarea></td>
				</tr>
				<tr>
				<th style="text-align:center;">被非分管领导采纳</th>
				<td><textarea style=" width: 480px; height: 80px;" name="data.ffgldcn"></textarea></td>
				</tr>
			   <%}%>
			   <%if (securityUtil.havePermission("/base/work!othermanager")&&!securityUtil.havePermission("/base/work!ordinary")) {%>
			     <tr>
				
					<th style="text-align:center;">与省公司沟通</th>
					<td>
					<input type="hidden" id="usersid"  name="data.empId" value="<%=id%>"/>
					<textarea style=" width: 480px; height: 82px;" name="data.sjgt"></textarea></td>
				</tr>
				<tr>
					<th style="text-align:center;">与省公司对口领导沟通</th>
					<td><textarea style=" width: 480px; height: 80px;" name="data.fgldgt"></textarea></td>
				</tr>
				<tr>
					<th style="text-align:center;">与下级沟通</th>
					<td><textarea style=" width: 480px; height: 80px;" name="data.xjgt"></textarea></td>
				</tr>
				<tr>				
					<th style="text-align:center;">执行力评价</th>
					<td>				
					<textarea style=" width: 480px; height: 80px;" name="data.cjbg"></textarea></td>
				</tr>
				<tr>
					<th style="text-align:center;">推动了哪些工作变革</th>
					<td><textarea style=" width: 480px; height: 80px;" name="data.tdbg"></textarea></td>
				</tr>
			   <%}%>
			</table>
		</fieldset>
	</form>
</body>
</html>