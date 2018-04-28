<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="aisino.reportform.util.base.TicketTaoDa"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String id = request.getParameter("id");
	if (id == null) {
		id = "";
	}
	
	List<Map>iList=new ArrayList();
	iList=TicketTaoDa.LoadTaoDaGS(id);
	String fpmc=iList.get(0).get("FPMC").toString();
	String kpy=iList.get(0).get("KPY").toString();
	String fpdm=iList.get(0).get("FPDM").toString();
	String fphm=iList.get(0).get("FPHM").toString();
	String kprq=iList.get(0).get("KPRQ").toString();
	String zfqk=iList.get(0).get("ZFQK").toString();
	String cssStyle="font-family:simsun";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <jsp:include page="../../inc.jsp"></jsp:include>
    
    <title>My JSP 'TicketZfqkbPrintForm.jsp' starting page</title>
<script>
    var submitNow = function($dialog, $grid, $pjq) {
		$("#DivToPrint").jqprint();
	};
	var submitForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {
			submitNow($dialog, $grid, $pjq);
		}
	};
	var printFun=function()
	{
		$("#DivToPrint").jqprint();
	}
// 	$(function() {
// 		parent.$.messager.progress({
// 			text : '数据加载中....'
// 		});
// 		$.post(sy.contextPath + '/base/ticket-store!doNotNeedSessionAndSecurity_getZfqkDetailbyId.sy?fpid=<%=id%>', {}, function(result) {
// 			if (result.FPHM != undefined) {
// 				$('form').form('load', {
// 					'data.fpmc' : result.FPMC,
// 					'data.kpy' : result.KPY,
// 					'data.fpdm':result.FPDM,
// 					'data.fphm':result.FPHM,
// 					'data.kprq':result.KPRQ,
// 					'data.zfqk':result.ZFQK
// 				});
// 			}
// 			parent.$.messager.progress('close');
// 		}, 'json');
// 	});
    </script>
  </head>
  
  <body class="easyui-layout">
    <div id="toolbar" >
  		<table style="font-size:12px;"><tr><td>
	  	<a href="javascript:void(0);"class="easyui-linkbutton" data-options="iconCls:'ext-icon-brick_go'" onclick="printFun()">打印作废情况单</a>
  		</td></tr></table>
  </div>
    <div id="DivToPrint" style="<%=cssStyle%>">
  <form id="form">
  <br><br>
    <div style="text-align:center;text-decoration: underline;width:700px"><h1>税收票证开具作废审批单</h1></div><br>
  <br>
    <div style="text-align:right;text-decoration: underline;width:700px;font-size:12px;">附原始证明依据&nbsp;&nbsp;&nbsp;&nbsp;张</div>
    	<table border="1" bordercolor="#333" style="border-collapse:collapse;font-size:12px;text-align: center;">
    <tr>
        <td style=" width: 100px;text-decoration: underline;">开具人姓名</td>
        <td style=" width: 100px;text-decoration: underline;" rowspan="4">作废票</td>
        <td style=" width: 100px;text-decoration: underline;">票证种类</td>
        <td style=" width: 100px;">
        <textarea style="resize:none;border:0;" readonly="readonly" name="data.fpmc" ><%=fpmc %></textarea>
        </td>
        <td style=" width: 100px;text-decoration: underline;" rowspan="4">新开票</td>
        <td style=" width: 100px;text-decoration: underline;">票证种类</td>
        <td style=" width: 100px;"></td>
    </tr>
    <tr>
        <td style=" width: 100px;" rowspan="3">
        <textarea style="width:100%;height:100%;resize:none;border:0;" readonly="readonly" name="data.kpy"><%=kpy %></textarea>
        </td>
        <td style=" width: 100px;  text-decoration: underline;">字轨</td>
        <td style=" width: 100px;">
        <textarea style="resize:none;border:0;" readonly="readonly" name="data.fpdm" ><%=fpdm %></textarea>
        </td>
        <td style=" width: 100px;  text-decoration: underline;">字轨</td>
        <td style=" width: 100px;">&nbsp;</td>
    </tr>
    <tr>
        <td style=" width: 100px;  text-decoration: underline;">号码</td>
        <td style=" width: 100px;">
        <textarea style="resize:none;border:0;" readonly="readonly" name="data.fphm" ><%=fphm %></textarea>
        </td>
        <td style=" width: 100px;  text-decoration: underline;">号码</td>
        <td style=" width: 100px;">&nbsp;</td>
    </tr>
    <tr>
        <td style=" width: 100px;  text-decoration: underline;">日期</td>
        <td style=" width: 100px;">
        <textarea style="resize:none;border:0;" readonly="readonly" name="data.kprq" ><%=kprq %></textarea>
        </td>
        <td style=" width: 100px;">&nbsp;</td>
        <td style=" width: 100px;">&nbsp;</td>
    </tr>
    <tr>
        <td style="height: 200px;width: 100px;text-decoration: underline;">作废原因</td>
        <td style="height: 200px;" colspan="2">
        <textarea style="width:100%;height:100%;resize:none;border:0;" readonly="readonly" name="data.zfqk" ><%=zfqk %></textarea>
        </td>
        <td style="height: 200px;width: 100px;text-decoration: underline;">票证会计审核意见</td>
        <td style="height: 200px;width: 100px;"></td>
        <td style="height: 200px;width: 100px;text-decoration: underline;">主管领导审查意见</td>
        <td style="width: 100px;height: 200px;"></td>
    </tr>
</table>
<table style="font-size:12px;width:700px">
	<tr>
	<td style="width:50%;text-align:left;">票证开具人（签字）</td>
	<td style="width:50%;text-align:right;">
	日期：&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日</td>
	</tr>
</table>
<hr />
    </form>
    </div>
  </body>
</html>
