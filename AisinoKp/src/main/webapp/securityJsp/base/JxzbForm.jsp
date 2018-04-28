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
	if (empId == null) {
		empId = "";
	}
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript" src="<%=contextPath%>/jslib/My97DatePicker4.8Beta3/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
<script type="text/javascript">

	var submitNow = function($dialog, $grid, $pjq) {
		var a=$("#idss").val();
		//alert(a);
		var url;
		if ($(':input[name="data.id"]').val().length > 0 ) {
			url = sy.contextPath + '/base/jxzj!update.sy';
		} else {
			url = sy.contextPath + '/base/jxzj!save.sy';
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
	
		var d = new Date().pattern("yyyy-MM");
		$("#perDate").datebox("setValue", d);
		var empIds='${param.empId}';
		var ids='${param.id}';
		var con={
				zbcjsj:d,
				syempdataByEmpdataid:empIds
			};
		//alert(ids);
		if (ids!=null&&ids!="") {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sy.contextPath + '/base/jxzj!getById.sy', {
				id : ids
			}, function(result) {
				if (result.id != undefined) {
					$('form').form('load', {
						'data.syempdataByEmpdataid.empId' : result.syempdataByEmpdataid.empId,
						'data.zbqz' : result.zbqz,
						'data.zbmc' : result.zbmc,
						'data.zbfs' : result.zbfs,
						'data.id' : result.id
					});
					
				}
				parent.$.messager.progress('close');
			}, 'json');
		}else{
		 $.post(sy.contextPath + '/base/jxzj!find.sy', con, function(result) {
		
			 var b=0;
		 for(var i=0;i<result.total;i++){
				 b+=result.rows[i].zbqz;
				}
		 var a=1;
		 var c=(a-b).toFixed(1);
		 //alert(a+","+b+","+c);
		if(c==1){
			 parent.$.messager.confirm('提示', '您的总权重分值已经大于1，请及时修改权重值', function(r) {
				
			});
			 
		}
		 $("#zbqz").val(c);
			}, 'json');
		}
	});
	  
	
</script>
</head>
<body>
	<form method="post" class="form">
		<fieldset>
		<input type="hidden" id="idss" name="data.idss"/>
		<input type="hidden" name="data.id"/>
			<legend>指标信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>被考核人编号</th>
					<td><input name="data.syempdataByEmpdataid.empId" value="<%=empId%>" readonly="readonly" /></td>
					<th>考核日期</th>
					<td><input name="data.zbcjsj"  value=<%=date %> class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM'})" readonly="readonly" style="width: 158px;" /></td>
				</tr>
				<tr>
					<th>指标权重</th>
					<td><input  validtype="qz" class='easyui-validatebox' data-options='required:true'  name="data.zbqz" id="zbqz" value=""  /></td>
					<th>目标分数</th>
					<td><input readonly="readonly" name="data.zbfs" style="width: 155px" value="1"/></td>
				</tr>
				<tr >
					<th>指标内容</th>
					<td colspan="3"><textarea name="data.zbmc" style=" width: 628px; height: 260px;"></textarea></td>
					
				</tr>
			
			</table>
		</fieldset>
	</form>
</body>
</html>