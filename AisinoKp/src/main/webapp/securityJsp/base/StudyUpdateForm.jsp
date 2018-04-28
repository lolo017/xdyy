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
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	
	var submitNow = function($dialog, $grid, $pjq) {
		var url = sy.contextPath + '/base/study!update.sy';
		
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
		$.post(sy.contextPath + '/base/study!getById.sy', {
			id : $(':input[name="data.id"]').val()
		}, function(result) {
			$('form').form('load', {
					'data.yqdxl' : result.yqdxl,
					'data.zdxlxz' : result.zdxlxz,
					'data.empId' : result.empId,
					'data.zdxlkm' : result.zdxlkm,
					'data.nkqxlyxmc' : result.nkqxlyxmc,
					
					'data.nkqxlxlxz' : result.nkqxlxlxz,
					'data.hzhbgt' : result.nkqxlkssj,
					
					'data.zcyqdzc' : result.zcyqdzc,
					'data.zcnkqzc' : result.zcnkqzc,
					
					'data.zcbmsj' : result.zcbmsj,
					'data.zckskm' : result.zckskm
					
				});
			
			parent.$.messager.progress('close');
		}, 'json');
	

	});
</script>
</head>
<body>
	<form method="post" class="form">
	
		<fieldset>
			<legend>沟通</legend>
			
			
			<table  class="table" style="width: 100%; height: 320px">
			<input type="hidden"  name="data.empId" value=""/>
  <tr>
  
    <th style="text-align:center; width: 90px;" rowspan="3">学历</th>
    <td style="text-align:center;" rowspan="2">已取得学历</td>
    <td  style="text-align:center;" colspan="2">在读学历</td>
    <td colspan="3">拟考取学历</td>
  </tr>
  <tr>
    <td style="text-align:center;">学历性质（本科/研究生）</td>
    <td style="text-align:center;">本年学习科目</td>
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
    	<input type="hidden" id="ids" name="data.id" value=<%=id%> />
    <input name="data.nkqxlkssj" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width: 120px;" />
    </td>
  </tr>
 
  <tr>
    <th style="text-align:center;" rowspan="2">职称</th>
 
    <td style="text-align:center;">已取得职称</td>
    <td style="text-align:center;">拟考取职称</td>
    <td style="text-align:center;">报名时间</td>
    <td style="text-align:center;">考试科目</td>
   
  </tr>
  <tr>
    <td><input name="data.zcyqdzc" /></td>
    <td><input name="data.zcnkqzc" /></td>
    <td>
    <input name="data.zcbmsj" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width: 120px;" />
    </td>
    <td><input name="data.zckskm" /></td>
   
  </tr>
 
 
</table>

		</fieldset>
	</form>
</body>
</html>