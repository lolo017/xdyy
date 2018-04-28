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
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	
	var submitNow = function($dialog, $grid, $pjq) {
		var url = sy.contextPath + '/base/study!save.sy';
		
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
	
		<fieldset>
			<legend>沟通</legend>
			
			<table  class="table" style="width: 100%; height: 320px">
			
  <tr>
  
    <th style="text-align:center; width: 90px;" rowspan="3">学历</th>
    <td style="text-align:center;" rowspan="2">已取得学历</td>
    <td  style="text-align:center;" colspan="2">在读学历</td>
    <td colspan="3">拟考取学历</td>
  </tr>
  <tr>
    <td style="text-align:center;">学历性质（本科/研究生）</td>
    <td style="text-align:center;">本学期学习科目</td>
    <td style="text-align:center;">院校名称</td>
    <td style="text-align:center;">学历性质（本科/研究生）</td>
    <td style="text-align:center;">考试时间</td>
  </tr>
  <tr>
    <td><input name="data.yqdxl" /></td>
    <td><input name="data.zdxlxz" /></td>
    <td><input name="data.zdxlkm" /></td>
    <td><input name="data.nkqxlyxmc" /></td>
    <td><input name="data.nkqxlxlxz" /></td>
    <td>
    	<input type="hidden"  value=<%=id%> name="data.empId"/>
    	<input type="hidden"  value=<%=name%> name="data.name"/>
    <input name="data.nkqxlkssj" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width: 120px;" />
    </td>
  </tr>
 
  <tr>
    <th style="text-align:center;" rowspan="2">职称</th>
 
    <td style="text-align:center;">已取得职称</td>
    <td style="text-align:center;">拟考取职称</td>
    <td style="text-align:center;">报名时间</td>
    <td style="text-align:center;">考试科目</td>
    <td style="text-align:center;">考试时间</td>
  </tr>
  <tr>
    <td><input name="data.zcyqdzc" /></td>
    <td><input name="data.zcnkqzc" /></td>
    <td>
    <input name="data.zcbmsj" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width: 120px;" />
    </td>
    <td><input name="data.zckskm" /></td>
    <td>
    <input name="data.nkqxlzcsj" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width: 120px;" />
    </td>
  </tr>
 
 
</table>

		</fieldset>
	</form>
</body>
</html>