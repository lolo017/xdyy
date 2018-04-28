<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ page import="aisino.reportform.util.base.SecurityUtil"%>
<%
	String contextPath = request.getContextPath();
SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
String date= format.format(new Date());
%>
<%
	String empId = request.getParameter("empId");
	String dpid = request.getParameter("dpid");
	String id = request.getParameter("id");
	if (empId == null) {
		empId = "";
	}
	if (dpid == null) {
		dpid = "";
	}
	if(id==null){
		id="";
	}
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript" src="<%=contextPath%>/jslib/My97DatePicker4.8Beta3/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
<script type="text/javascript">
var id='${param.id}';
	var submitNow = function($dialog, $grid, $pjq) {
		var url;
		if ($(':input[name="data.id"]').val().length > 0) {
			url = sy.contextPath + '/base/jxbm!update.sy';
		} else {
			url = sy.contextPath + '/base/jxbm!save.sy';
		}
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
		
		
		function myformatter(date){
            var y = date.getFullYear();
            var m = date.getMonth()+1;
            var d = date.getDate();
            return y+'-'+(m<10?('0'+m):m);
        }


  		function myparser(s){
            if (!s) return new Date();
            var ss = (s.split('-'));
            var y = parseInt(ss[0],10);
            var m = parseInt(ss[1],10);
            var d = parseInt(ss[2],10);
            if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
                return new Date(y,m-1,d);
            } else {
                return new Date();
            }
        }
  
  
	$(function() {
		//var d = new Date().pattern("yyyy-MM");
		//$("#perDate").datebox("setValue", d);
		if ($(':input[name="data.id"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sy.contextPath + '/base/jxbm!getById.sy', {
				id : $(':input[name="data.id"]').val()
			}, function(result) {
				if (result.id != undefined) {
					$('form').form('load', {
						'data.id' : result.id,
						'data.syorganization.id' : result.syorganization.id,
						'data.zbcjsj' : result.zbcjsj,
						'data.zbqz' : result.zbqz,
						'data.zbfs' : result.zbfs,
						'data.zbmc' : result.zbmc,
						'data.syempdata.empId':result.syempdata.empId
						
					});
					
				}
				parent.$.messager.progress('close');
			}, 'json');
			}
		
	});
	  
	
</script>
</head>
<body>
	<form method="post" class="form">
		<fieldset>
		<input type="hidden" name="data.id" value="<%=id %>"/>
			<legend>指标信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>被考核部门编号</th>
					<td><input name="data.syorganization.id" value="<%=dpid%>" readonly="readonly" /></td>
					<th>考核日期</th>
					<td><input name="data.zbcjsj"  value="<%=date %>" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM'})" readonly="readonly" style="width: 158px;" /></td>
				</tr>
				<tr>
					<th>指标权重</th>
					<td><input  validtype="qz" class='easyui-validatebox' data-options='required:true'  name="data.zbqz" value=""  /></td>
					<th>目标分数</th>
					<td><input readonly="readonly" id="" name="data.zbfs" style="width: 155px" value="1"/></td>
				</tr>
				<tr >
					<th>指标内容</th>
					<td colspan="3"><textarea name="data.zbmc" style=" width: 628px; height: 260px;"></textarea></td>
					
				</tr>
			<input type="hidden" name="data.syempdata.empId" value=<%=empId%> />
			</table>
		</fieldset>
		
	</form>
</body>
</html>