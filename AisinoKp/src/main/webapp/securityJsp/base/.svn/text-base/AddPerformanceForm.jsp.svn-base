<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="aisino.reportform.util.base.SecurityUtil"%>
	<%@ page import="aisino.reportform.model.base.SessionInfo"%>
<%
SecurityUtil securityUtil = new SecurityUtil(session);
SessionInfo sessionInfo = (SessionInfo) session.getAttribute("sessionInfo");
String name=sessionInfo.getUser().getName();
String userId=sessionInfo.getUser().getId();
String empId=sessionInfo.getUser().getEmpId();
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
	var submitForm = function($dialog, $grid, $pjq) {
		parent.$.messager.progress({
			text : '数据加载中....'
		});
		if ($('form').form('validate')) {
			var url;
			if ($(':input[name="data.id"]').val().length > 0) {
				url = sy.contextPath + '/base/performance!update.sy';
			} else {
				url = sy.contextPath + '/base/performance!save.sy';
			}
			$.post(url, sy.serializeObject($('form')), function(result) {
				if (result.success) {
					parent.$.messager.progress('close');
					$grid.datagrid('reload');
					$dialog.dialog('destroy');
					
				} else {
					$pjq.messager.alert('提示', result.msg, 'error');
				}
			}, 'json');
		}
	};
	

		
	$(function() {
		$("#perDate").datebox("setValue", getCurrentDate());
		$.post(
				sy.contextPath + '/base/performance!doNotNeedSecurity_getEmpData.sy',
				{
					id : <%=empId%>
				},
				function(result) {
		
					if(result.orgOrganization==undefined||result.empData==undefined||result.depOrganization==undefined||result.posOrganization==undefined)
						{
						var jq = top.jQuery; 
						jq('#empDataDialog').dialog('open');
						jq('#empId').val(result.empId);
						if(result.orgOrganization!=undefined){
							jq('#org').val(result.orgOrganization.name);
							jq('#orgId').val(result.orgOrganization.id);
							jq("#org").validatebox('validate');
						}
						if(result.depOrganization!=undefined){
							jq('#dep').val(result.depOrganization.name);
							jq('#depId').val(result.depOrganization.id);
							jq("#dep").validatebox('validate');
						}
						if(result.posOrganization!=undefined){
							jq('#pos').val(result.posOrganization.name);
							jq('#posId').val(result.posOrganization.id);
							jq("#pos").validatebox('validate');
						}
						if(result.empData!=undefined){
							jq('#leader').val(result.empData.name);
							jq('#leaderId').val(result.empData.id);
							jq("#leader").validatebox('validate');
						}
						
						
						}else if(result.empId != undefined) {
						
						$('form')
								.form(
										'load',
										{
											'data.leader' : result.empData.name,
											'data.dep' : result.depOrganization.name,
											'data.position' : result.posOrganization.name
				
										});
						
					}
				}, 'json');

	});
	

</script>
</head>
<body>
	<form method="post" class="form">
		<fieldset>
			<legend>绩效信息</legend>
			<table  class="table" style="width: 100%;">
			<tr style="display:none">
			        <th>编号</th>
					<td><input name="data.id" value="<%=id %>" readonly="readonly" style="display:none"/></td>
					<th>用户ID</th>
					<td><input name="data.syuser.id" value="<%=userId %>" readonly="readonly" style="display:none"/></td>
					</tr>
			     <tr>
					<th >姓名</th>
					<td>
					<div id="name" ><%=name %></div>
					</td>
					<th>评价时间</th>
					<td><input id="perDate" name="data.perDate" class="easyui-datebox" style="width: 155px" /></td>
					
				</tr>
				<tr>
					<th>部门</th>
					<td><input name="data.dep" readonly="readonly"/></td>
					<th>岗位</th>
					<td><input name="data.position"readonly="readonly"/></td>
					<th>上级</th>
					<td><input name="data.leader"readonly="readonly"/></td>
				</tr>
				</table>
			<%if (securityUtil.havePermission("/base/work!ordinary")&&!securityUtil.havePermission("/base/work!othermanager")) {%>
			<input name="data.type" value="1" type="hidden"/>
			<table id="t1" class="table" style="width: 100%;">
			<tr>
					<th rowspan="3"  >上月工作回顾</th>
					<th rowspan="1">工作目标及改进计划</th>
					<td rowspan="1" colspan="4"><textarea style="width: 700px;height: 60px"name="data.summary"></textarea></td>
					
				</tr>
				<tr>
					<th rowspan="1">目标及改进完成情况报告</th>
					<td rowspan="1" colspan="4"><textarea style="width: 700px;height: 60px"name="data.target"></textarea></td>
					
				</tr>
				<tr>
					<th rowspan="1">上级指导及评价</th>
					<td rowspan="1" colspan="4"><textarea style="width: 700px;height: 60px"name="data.workImprove"></textarea></td>
					
				</tr>
				<tr>
					<th rowspan="1" >本月工作计划</th>
					<th rowspan="1">本月工作目标及改进计划</th>
					<td rowspan="1" colspan="4"><textarea style="width: 700px;height: 100px"name="data.workTarget"></textarea>
					</td>
					<tr>
					<th rowspan="2" >考核结果及运用</th>
					<th rowspan="1" >绩效评分</th>
					<td rowspan="1" colspan="1"><textarea style="width: 200px;height: 30px"name="data.isLeaderConfer"></textarea>（总分为1）</td>
				</tr>
				<tr>
					<th rowspan="1" >专项奖惩</th>				
					<td rowspan="1" colspan="4"><textarea style="width: 700px;height: 30px"name="data.isPersonalConfer"></textarea></td>
				</tr>
				</tr>
			</table>
			<%} %>
			<%if (securityUtil.havePermission("/base/work!othermanager")&&!securityUtil.havePermission("/base/work!ordinary")) {%>
			<input name="data.type" value="2" type="hidden"/>
			<table id="t2" class="table" style="width: 100%;">
				
				<tr>
					<th rowspan="3"  >上月工作</th>
					<th rowspan="1">利润目标与进度差距</th>
					<td rowspan="1" colspan="4"><textarea style="width: 700px;height: 60px"name="data.summary"></textarea></td>
					
				</tr>
				<tr>
					<th rowspan="1">指标完成短板和改进计划</th>
					<td rowspan="1" colspan="4"><textarea style="width: 700px;height: 60px"name="data.target"></textarea></td>
					
				</tr>
				<tr>
					<th rowspan="1">工作短板和改进计划</th>
					<td rowspan="1" colspan="4"><textarea style="width: 700px;height: 60px"name="data.improve"></textarea></td>
					
				</tr>
				<tr>
					<th rowspan="1" >本月工作</th>
					<th rowspan="1">利润目标与进度计划</th>
					<td rowspan="1" colspan="4"><textarea style="width: 700px;height: 100px"name="data.workTarget"></textarea>
					</td>
					
				</tr>
				
				<tr>
					<th rowspan="2" >考核结果及运用</th>
					<th rowspan="1" >绩效评分</th>
					<td rowspan="1" colspan="1"><textarea style="width: 200px;height: 30px"name="data.isLeaderConfer"></textarea>（总分为1）</td>
				</tr>
				<tr>
					<th rowspan="1" >专项奖惩</th>				
					<td rowspan="1" colspan="4"><textarea style="width: 700px;height: 30px"name="data.isPersonalConfer"></textarea></td>
				</tr>
			</table>
			<%} %>
			<%if (securityUtil.havePermission("/base/work!othermanager")&&securityUtil.havePermission("/base/work!ordinary")) {%>
			<input name="data.type" value="1" type="hidden"/>
			<table id="t1" class="table" style="width: 100%;">
			<tr>
					<th rowspan="3"  >上月工作回顾</th>
					<th rowspan="1">工作目标及改进计划</th>
					<td rowspan="1" colspan="4"><textarea style="width: 700px;height: 60px"name="data.summary"></textarea></td>
					
				</tr>
				<tr>
					<th rowspan="1">目标及改进完成情况报告</th>
					<td rowspan="1" colspan="4"><textarea style="width: 700px;height: 60px"name="data.target"></textarea></td>
					
				</tr>
				<tr>
					<th rowspan="1">上级指导及评价</th>
					<td rowspan="1" colspan="4"><textarea style="width: 700px;height: 60px"name="data.workImprove"></textarea></td>
					
				</tr>
				<tr>
					<th rowspan="1" >本月工作计划</th>
					<th rowspan="1">本月工作目标及改进计划</th>
					<td rowspan="1" colspan="4"><textarea style="width: 700px;height: 100px"name="data.workTarget"></textarea>
					</td>
					<tr>
					<th rowspan="2" >考核结果及运用</th>
					<th rowspan="1" >绩效评分</th>
					<td rowspan="1" colspan="1"><textarea style="width: 200px;height: 30px"name="data.isLeaderConfer"></textarea>（总分为1）</td>
				</tr>
				<tr>
					<th rowspan="1" >专项奖惩</th>				
					<td rowspan="1" colspan="4"><textarea style="width: 700px;height: 30px"name="data.isPersonalConfer"></textarea></td>
				</tr>
				</tr>
			</table>
		<%} %>
		</fieldset>
	</form>
</body>
</html>