<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String id = request.getParameter("id");
	if (id == null) {
		id = "";
	}
	
	String ksrq= request.getParameter("ksrq");
	if (ksrq != null) {
		ksrq = ksrq.substring(0, 10);
	}
	String jsrq= request.getParameter("jsrq");
	if (jsrq != null) {
		jsrq = jsrq.substring(0, 10);
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <jsp:include page="../../inc.jsp"></jsp:include>
    
    <title>My JSP 'TicketJbForm.jsp' starting page</title>
    <script>
    
    $(function(){
		var jbid='<%=id%>';
		var ksrq='<%=ksrq%>';
		var jsrq='<%=jsrq%>';
		if(jbid!=''){
			$("#newtr").hide();
			loadOldStat(jbid,ksrq,jsrq);
		}
    });
    
    var loadOldStat=function(id,ksrq,jsrq)
    {
    	var url;
		url = sy.contextPath + '/base/ticket-jb!doNotNeedSessionAndSecurity_loadOldStat.sy';
		$.post(url, {id:id}, function(result) {
			parent.sy.progressBar('close');//关闭上传进度条
			if(result!=undefined){
				createTable(result,ksrq,jsrq);
			}
		}, 'json');
    };

	var addFun=function()
	{
		if ($('form').form('validate')) {
			var url;
			url = sy.contextPath + '/base/ticket-jb!doNotNeedSessionAndSecurity_doJb.sy';
			$.post(url, sy.serializeObject($('form')), function(result) {
				parent.sy.progressBar('close');//关闭上传进度条
				if(result!=undefined){
					var kssj=$("#ksrq").datebox('getValue');
					var jssj=$("#jsrq").datebox('getValue');
					createTable(result,kssj,jssj);
				}
			}, 'json');
		}
	};
	
	var createTable=function(result,kssj,jssj){
		var myDate = new Date();
		var monthstr=myDate.getMonth()+1;
		var datestr=myDate.getFullYear()+"年"+monthstr+"月"+myDate.getDate()+"日";
		var sno=""+myDate.getFullYear()+monthstr+myDate.getDate()+myDate.getHours()+myDate.getMinutes()+myDate.getSeconds()+myDate.getMilliseconds();
		var str="<div style='width:100%;text-align:center;'><h1>税收票款结报缴销单</h1></div><br />";
		str+="<div style='width:100%;font-size:14px;'>";
		str+="<div style='width:100%;text-align:right'>编号："+sno+"&nbsp;</div>";
		str+="<table style='width:100%;font-size:14px;'><tr><td style='width:50%'>结报缴销时间："+kssj+"至"+jssj+"</td>"
		+"<td align='right' style='width:50%'>单位&nbsp;&nbsp;份&nbsp;&nbsp; (枚)</td></tr></table>";
		str+="<table border='1' bordercolor='#333' style='width:100%;border-collapse:collapse;font-size:14px;text-align: center;'>";
		str+="<tr><td colspan='7' align='left'>税收票款结报缴销单-结报明细</td></tr>";
		str+="<tr>"
		+"<td>税收票证名称</td>"
		+"<td>字轨</td>"
		+"<td>起始号码</td>"
		+"<td>终止号码</td>"
		+"<td>开具数</td>"
		+"<td>实征税款（元）</td>"
		+"<td>开具作废数</td>"
		+"</tr>";
		for(var o=0;o<result.length-1;o++){
			str+="<tr>"
				+"<td>"+result[o].NAME+"</td>"
				+"<td>"+result[o].FPDM+"</td>"
				+"<td>"+result[o].KSHM+"</td>"
				+"<td>"+result[o].JSHM+"</td>"
				+"<td>"+result[o].KJS+"</td>"
				+"<td>"+result[o].HJJE+"</td>"
				+"<td>"+result[o].ZFS+"</td>"
				+"</tr>";
		}
		str+="<tr><td colspan='7' align='left'>税收票款结报缴销单-结报统计</td></tr>";
		str+="<tr>"
		+"<td>税收票证名称</td>"
		+"<td>字轨</td>"
		+"<td>起始号码</td>"
		+"<td>终止号码</td>"
		+"<td>开具数</td>"
		+"<td>实征税款（元）</td>"
		+"<td>开具作废数</td>"
		+"</tr>";
		var f=result.length-1;
		str+="<tr>"
			+"<td>"+result[0].NAME+"</td>"
			+"<td>"+result[0].FPDM+"</td>"
			+"<td>"+result[f].KSHM+"</td>"
			+"<td>"+result[f].JSHM+"</td>"
			+"<td>"+result[f].KJS+"</td>"
			+"<td>"+result[f].HJJE+"</td>"
			+"<td>"+result[f].ZFS+"</td>"
			+"</tr>";
		str+="</table><table style='width:100%;font-size:14px'><tr>"
		+"<td style='width:30%'>税收票证管理人员(签章):</td>"
		+"<td style='width:30%'>结报缴销人(签章):</td>"
		+"<td style='text-align:right'>结报缴销日期:"+datestr+"</td>"
		+"</tr></table>"
		+"</div>";
		$("#PrintDiv").html(str);
	};
	
	var printFun=function()
	{
		$("#PrintDiv").jqprint();
	}
	
	
	</script>
  </head>
  
  <body class="easyui-layout" >
  	<div id="toolbar" >
		<form id="form">
			<table style="font-size:12px;">
				<tr id="newtr">
					<td>请选择税票种类</td>
					<td><input id="fplx" name="name" class="easyui-combobox" data-options="required:true,missingMessage:'请选择税票种类', 
    	valueField:'VALUE',
    	textField:'NAME',
    	editable:false,
    	url:'<%=contextPath%>/base/ticket-store!doNotNeedSessionAndSecurity_getFpName.sy',
    	onLoadSuccess: function () {
    		
    	},
        onSelect: function(data){    
			
        }" ></input></td>
					<td>请选择结报期</td>
					<td><input id="ksrq" name="ksrq" class="easyui-datebox" data-options="required:true,missingMessage:'请选择开始日期'"></td>
					<td><input id="jsrq" name="jsrq" class="easyui-datebox" data-options="required:true,missingMessage:'请选择结束日期'"></td>
					<td><a href="javascript:void(0);"class="easyui-linkbutton"
						data-options="iconCls:'ext-icon-brick_go'" onclick="addFun()">确定</a>
					</td>
				</tr>
				<tr>
				<td><a href="javascript:void(0);"class="easyui-linkbutton"
						data-options="iconCls:'ext-icon-brick_go'" onclick="printFun()">打印结报单</a></td>
				</tr>
			</table>
		</form>
	</div>
    <div id="PrintDiv" style="width:100%;">
    </div>
  </body>
</html>
