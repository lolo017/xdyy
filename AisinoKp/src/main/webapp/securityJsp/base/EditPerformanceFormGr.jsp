<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="aisino.reportform.util.base.SecurityUtil"%>
<%@page import="java.text.SimpleDateFormat"%>
	<%@ page import="aisino.reportform.model.base.SessionInfo"%>
<%
SecurityUtil securityUtil = new SecurityUtil(session);
SessionInfo sessionInfo = (SessionInfo) session.getAttribute("sessionInfo");
String empId=sessionInfo.getUser().getEmpId();
SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
String date= format.format(new Date());
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
<script type="text/javascript" src="<%=contextPath%>/jslib/My97DatePicker4.8Beta3/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
<script type="text/javascript">
var empId='${param.empId}';

	var submitForm = function($dialog, $grid, $pjq) {
		//提交前将小写逗号改为大写，防止后台拆分报错
		 $("input[name='zbmc_gr_sy']").each(function(){
			    $(this).val(replaceAllstr($(this).val(),",","，"));
		  });
		 $("input[name='zbmc_gr_by']").each(function(){
			    $(this).val(replaceAllstr($(this).val(),",","，"));
		  });
		 $("input[name='zbmc_bm_sy']").each(function(){
			    $(this).val(replaceAllstr($(this).val(),",","，"));
		  });
		 $("input[name='zbmc_bm_by']").each(function(){
			    $(this).val(replaceAllstr($(this).val(),",","，"));
		  });
		if ($('form').form('validate')) {
			var url;
			if ($(':input[name="data.id"]').val().length > 0) {
				url = sy.contextPath + '/base/performance!doNotNeedSecurity_updatePerformance.sy';
			} else {
				url = sy.contextPath + '/base/performance!save.sy';
			}
			$.post(url, sy.serializeObject($('form')), function(result) {
					if (result.success) {
						
								  $grid.datagrid('reload');
									$dialog.dialog('destroy');
					} else {
						$pjq.messager.alert('提示', result.msg, 'error');
					}
			}, 'json');
		}
	
	};
	var showIcons = function() {
		var dialog = parent.sy.modalDialog({
			title : '浏览小图标',
			url : sy.contextPath + '/style/icons.jsp',
			buttons : [ {
				text : '确定',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.selectIcon(
							dialog, $('#iconCls'));
				}
			} ]
		});
	};

	

	$(function() {
		
            $("#empId").val(empId);
		
		if ($(':input[name="data.id"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sy.contextPath + '/base/performance!getById.sy',
							{
								id : $(':input[name="data.id"]').val()
							},
							function(result) {
								if (result.id != undefined) {
									$('form')
											.form(
													'load',
													{
														'data.id' : result.id,
														'data.syuser.id' : result.syuser.id,
														'data.empId' : result.empId,
														'data.perPlace' : result.perPlace,
														'data.summary':decodeHtml(result.summary),
														'data.target':decodeHtml(result.target),
														'data.workImprove':decodeHtml(result.workImprove),
														'data.workTarget':decodeHtml(result.workTarget),
														'data.improve':decodeHtml(result.improve),
														'data.result':decodeHtml(result.result),
														'data.isConfer':decodeHtml(result.isConfer),
														'data.isConfergrzb':decodeHtml(result.isConfergrzb),
														'data.isLeaderConferGrzb':decodeHtml(result.isLeaderConferGrzb),
														
														'data.isLeaderConfer':decodeHtml(result.isLeaderConfer),
														'data.isLeaderConfer2':decodeHtml(result.isLeaderConfer2),
														'data.isPersonalConfer':decodeHtml(result.isPersonalConfer),
														'data.dep':decodeHtml(result.dep),
														'data.position':decodeHtml(result.position),
														'data.leader':decodeHtml(result.leader)
													});
									$("#name").html(result.syuser.name);
									//$("#perDate").datebox("setValue", result.perDate.substr(0,10));
									$("#perDate").val(result.perDate.substr(0,10));
									if(result.type=="1"){
										o=document.getElementById('t2');
										o.parentNode.removeChild(o);
									}
									if(result.type=="2"){
										o=document.getElementById('t1');
										o.parentNode.removeChild(o);
									}
									//查询指标
									queryZb();
									if ($(':input[name="data.isConfergrzb"]').is(':checked') && empId == '<%=empId%>') {
										readOnly();
									}
								}
								parent.$.messager.progress('close');
							}, 'json');
			
		}
		
		
	});
	function queryZb(){
	
	
		

		$.ajaxSetup({
			  async: false
		});
		//查询上月,本月个人指标
		$.post(
				sy.contextPath + '/base/jxzj!grid2.sy',
				{
					id : empId,
				    zbcjsj: $("#perDate").val().substring(0,7)
				},
				function(result) {
					var targetObj;
					//上月
				for(var o1=0;o1<result.sy.length;o1++){
					//for(var o1 in result.sy){ 
						var _select;
						if(result.sy[o1].jxlb == "CPI"){
							_select = "<select name='jxlb_gr_sy'><option value='KPI'>KPI</option><option value='CPI' selected='selected'>CPI</option></select>";
						}else{
							_select = "<select name='jxlb_gr_sy'><option value='KPI' selected='selected'>KPI</option><option value='CPI'>CPI</option></select>";
						}
						//看的是自己绩效，不能填领导评分
						if(empId=='<%=empId%>'){
							//填写自评分值后，不允许修改指标名称和权重或删除
							if(result.sy[o1].zpfz==undefined||result.sy[o1].zpfz==""){
								  targetObj = $("<tr><td height=20  width='40%' style='text-align:center;'><input name='zbmc_gr_sy' style='width:100%'  class='easyui-validatebox' data-options='required:true' value='"+result.sy[o1].zbmc+"'/></td><td height=20  width='12%' style='text-align:center;'><input name='zbqz_gr_sy' style='width:100%' validtype='qz' class='easyui-validatebox' data-options='required:true' value='"+result.sy[o1].zbqz+"'/></td><td height=20  width='12%' style='text-align:center;'>" + _select + "</td><td height=20  width='12%' style='text-align:center;'><input name='zpfz_gr_sy' style='width:100%' validtype='jxVali[0,"+ result.sy[o1].zbqz +"]' class='easyui-validatebox' data-options='required:true' value='"+result.sy[o1].zpfz+"'/></td><td height=20  width='12%' style='text-align:center;'><input name='ldpf_gr_sy'  style='width:100%' readonly='true' value='"+isUndefined(result.sy[o1].ldpf)+"'/></td><td height=20  width='12%' style='text-align:center;'><a href='javascript:void' onclick='del(this);'>删除</a></td></tr>").appendTo("#gr1");
							}else{
			                      targetObj = $("<tr><td height=20  width='40%' style='text-align:center;'><input name='zbmc_gr_sy' style='width:100%'  class='easyui-validatebox' data-options='required:true' value='"+result.sy[o1].zbmc+"'/></td><td height=20  width='12%' style='text-align:center;'><input name='zbqz_gr_sy' style='width:100%' validtype='qz' class='easyui-validatebox' data-options='required:true' value='"+result.sy[o1].zbqz+"'/></td><td height=20  width='12%' style='text-align:center;'>" + _select + "</td><td height=20  width='12%' style='text-align:center;'><input name='zpfz_gr_sy' style='width:100%' validtype='jxVali[0,"+ result.sy[o1].zbqz +"]' class='easyui-validatebox' data-options='required:true' value='"+result.sy[o1].zpfz+"'/></td><td height=20  width='12%' style='text-align:center;'><input name='ldpf_gr_sy' class='easyui-validatebox' validtype='jxVali[0,"+ result.sy[o1].zbqz +"]' style='width:100%' value='"+isUndefined(result.sy[o1].ldpf)+"'/></td><td height=20  width='12%' style='text-align:center;'></td></tr>").appendTo("#gr1");
							}
						}else{
			                targetObj = $("<tr><td height=20  width='40%' style='text-align:center;'><input name='zbmc_gr_sy' style='width:100%'  class='easyui-validatebox' data-options='required:true' value='"+result.sy[o1].zbmc+"'/></td><td height=20  width='12%' style='text-align:center;'><input name='zbqz_gr_sy' style='width:100%' validtype='qz' class='easyui-validatebox' data-options='required:true' value='"+result.sy[o1].zbqz+"'/></td><td height=20  width='12%' style='text-align:center;'>" + _select + "</td><td height=20  width='12%' style='text-align:center;'><input name='zpfz_gr_sy' style='width:100%' validtype='jxVali[0,"+ result.sy[o1].zbqz +"]' class='easyui-validatebox' data-options='required:true' value='"+result.sy[o1].zpfz+"'/></td><td height=20  width='12%' style='text-align:center;'><input data-options='required:true' name='ldpf_gr_sy' class='easyui-validatebox' validtype='jxVali[0,"+ result.sy[o1].zbqz +"]' onkeyup='countGr(this);' class='easyui-validatebox' style='width:100%' value='"+isUndefined(result.sy[o1].ldpf)+"'/></td><td height=20  width='12%' style='text-align:center;'><a href='javascript:void' onclick='del(this);'>删除</a></td></tr>").appendTo("#gr1");
						}
						$.parser.parse(targetObj);
					}
					if(result.sy.length > 0)
						$("#gr1_btn").hide();
					//本月
					for(var o=0;o<result.by.length;o++){
						var _select;
						if(result.by[o].jxlb == "CPI"){
							_select = "<select name='jxlb_gr_by'><option value='KPI'>KPI</option><option value='CPI' selected='selected'>CPI</option></select>";
						}else{
							_select = "<select name='jxlb_gr_by'><option value='KPI' selected='selected'>KPI</option><option value='CPI'>CPI</option></select>";
						}
						
						if(result.by[o].zpfz==undefined||result.by[o].zpfz==""){
							targetObj = $("<tr><td height=20  width='40%' style='text-align:center;'><input name='zbmc_gr_by' style='width:100%'  class='easyui-validatebox' data-options='required:true' value='"+result.by[o].zbmc+"'/></td><td height=20  width='12%' style='text-align:center;'><input name='zbqz_gr_by' style='width:100%' validtype='qz' class='easyui-validatebox' data-options='required:true' value='"+result.by[o].zbqz+"'/></td><td height=20  width='12%' style='text-align:center;'>" + _select + "</td><td height=20  width='12%' style='text-align:center;'><input  style='width:100%' validtype='jxVali[0,"+ result.by[o].zbqz +"]'  data-options='required:true' readonly='true'/></td><td height=20  width='12%' style='text-align:center;'><input name='ldpf_gr_by' style='width:100%' readonly='true'/></td><td height=20  width='12%' style='text-align:center;'><a href='javascript:void' onclick='del(this);'>删除</a></td></tr>").appendTo("#gr2");
							
						}else{
			                targetObj = $("<tr><td height=20  width='40%' style='text-align:center;'><input name='zbmc_gr_by' readonly='true' style='width:100%'  class='easyui-validatebox' data-options='required:true' value='"+result.by[o].zbmc+"'/></td><td height=20  width='12%' style='text-align:center;'><input name='zbqz_gr_by' readonly='true'  style='width:100%' validtype='qz' class='easyui-validatebox' data-options='required:true' value='"+result.by[o].zbqz+"'/></td><td height=20  width='12%' style='text-align:center;'>" + _select + "</td><td height=20  width='12%' style='text-align:center;'><input  style='width:100%' validtype='jxVali[0,"+ result.by[o].zbqz +"]'  data-options='required:true' readonly='true'/></td><td height=20  width='12%' style='text-align:center;'><input name='ldpf_gr_by' style='width:100%' readonly='true'/></td><td height=20  width='12%' style='text-align:center;'><a href='javascript:void' onclick='del(this);'>删除</a></td></tr>").appendTo("#gr2");
						}
						$.parser.parse(targetObj);
					}
				}, 'json');
		$.ajaxSetup({
			  async: false
		});
		//查询上月，本月部门指标
		$.post(
				sy.contextPath + '/base/jxbm!grid2.sy',
				{
					id : empId,
				    zbcjsj: $("#perDate").val().substring(0,7)
				},
				function(result){
					var targetObj;
					//上月
					for(var o=0;o<result.sy.length;o++){
						var _select;
						if(result.sy[o].jxlb == "CPI"){
							_select = "<select name='jxlb_bm_sy'><option value='KPI'>KPI</option><option value='CPI' selected='selected'>CPI</option></select>";
						}else{
							_select = "<select name='jxlb_bm_sy'><option value='KPI' selected='selected'>KPI</option><option value='CPI'>CPI</option></select>";
						}
						
						if(empId=='<%=empId%>'){
							   targetObj = $("<tr><td height=20  width='40%' style='text-align:center;'><input name='zbmc_bm_sy' style='width:100%'  class='easyui-validatebox' data-options='required:true' value='"+result.sy[o].zbmc+"'/></td><td height=20  width='12%' style='text-align:center;'><input name='zbqz_bm_sy' style='width:100%' validtype='qz' class='easyui-validatebox' data-options='required:true' value='"+result.sy[o].zbqz+"'/></td><td height=20  width='12%' style='text-align:center;'>" + _select + "</td><td height=20  width='12%' style='text-align:center;'><input name='zpfz_bm_sy' style='width:100%' validtype='jxVali[0,"+ result.sy[o].zbqz +"]' class='easyui-validatebox' data-options='required:true' value='"+result.sy[o].zpfz+"'/></td><td height=20  width='12%' style='text-align:center;'><input  name='ldpf_bm_sy'  style='width:100%' validtype='jxVali[0,"+ result.sy[o].zbqz +"]' class='easyui-validatebox' value='"+isUndefined(result.sy[o].ldpf)+"'/></td><td height=20  width='12%' style='text-align:center;'><a href='javascript:void' onclick='del(this);'>删除</a></td></tr>").appendTo("#bm1");
						}else{
							   targetObj = $("<tr><td height=20  width='40%' style='text-align:center;'><input name='zbmc_bm_sy' style='width:100%'  class='easyui-validatebox' data-options='required:true' value='"+result.sy[o].zbmc+"'/></td><td height=20  width='12%' style='text-align:center;'><input name='zbqz_bm_sy' style='width:100%' validtype='qz' class='easyui-validatebox' data-options='required:true' value='"+result.sy[o].zbqz+"'/></td><td height=20  width='12%' style='text-align:center;'>"+ _select +"</td><td height=20  width='12%' style='text-align:center;'><input name='zpfz_bm_sy' style='width:100%' validtype='jxVali[0,"+ result.sy[o].zbqz +"]' class='easyui-validatebox' data-options='required:true' value='"+result.sy[o].zpfz+"'/></td><td height=20  width='12%' style='text-align:center;'><input data-options='required:true' name='ldpf_bm_sy' validtype='jxVali[0,"+ result.sy[o].zbqz +"]' class='easyui-validatebox' onkeyup='countBm();' style='width:100%' value='"+isUndefined(result.sy[o].ldpf)+"'/></td><td height=20  width='12%' style='text-align:center;'><a href='javascript:void' onclick='del(this);'>删除</a></td></tr>").appendTo("#bm1");
						}
						$.parser.parse(targetObj);
					}
					if(result.sy.length > 0)
						$("#bm1_btn").hide();
					//本月
					for(var o=0;o<result.by.length;o++){
						var _select;
						if(result.by[o].jxlb == "CPI"){
							_select = "<select name='jxlb_bm_by'><option value='KPI'>KPI</option><option value='CPI' selected='selected'>CPI</option></select>";
						}else{
							_select = "<select name='jxlb_bm_by'><option value='KPI' selected='selected'>KPI</option><option value='CPI'>CPI</option></select>";
						}
						
						targetObj = $("<tr><td height=20  width='40%' style='text-align:center;'><input name='zbmc_bm_by' style='width:100%'  class='easyui-validatebox' data-options='required:true' value='"+result.by[o].zbmc + "'/></td><td height=20  width='12%' style='text-align:center;'><input name='zbqz_bm_by' style='width:100%' validtype='qz' class='easyui-validatebox' data-options='required:true' value='"+result.by[o].zbqz+"'/></td><td height=20  width='12%' style='text-align:center;'>" + _select + "</td><td height=20  width='12%' style='text-align:center;'><input style='width:100%' validtype='jxVali[0,"+ result.by[o].zbqz +"]' data-options='required:true' readonly='true'/></td><td height=20  width='12%' style='text-align:center;'><input name='ldpf_bm_by' style='width:100%' readonly='true'/></td><td height=20  width='12%' style='text-align:center;'><a href='javascript:void' onclick='del(this);'>删除</a></td></tr>").appendTo("#bm2");
						
						$.parser.parse(targetObj);
					}
				}, 'json');
	}
	
	
function decodeHtml(str){
	
	if(str){
	str=str.replace(/&lt;/g, "<");
	str=str.replace(/&gt;/g, ">");
	str=str.replace(/&rsquo;/g, "'");
	str=str.replace(/&lsquo;/g, "'");
	str=str.replace(/&ldquo;/g, '"');
	str=str.replace(/&rdquo;/g, '"');
	str=str.replace(/&quot;/g, '"');
	str=str.replace(/&#39;/g,"'");
	return str;
	}else{
		return "";
	}
}

function isUndefined(str){
	
	if(str==undefined){
		return "";
	}else{
		return str;
	}
}

function escape2Html(str) {
	
	 var arrEntities={'lt':'<','gt':'>','nbsp':' ','amp':'&','quot':'"'};
	 return str.replace(/&(lt|gt|nbsp|amp|quot);/ig,function(all,t){return arrEntities[t];});
	}	
//计算个人绩效
function countGr(now){
	

	var trLength=$("#gr1 tr").length;
	var count=0;
	if(trLength>1){
	for(var i=1;i<trLength;i++){
		if($("#gr1 tr:eq("+i+") td:eq(2) select").val() == "KPI"){
			//count=accAdd(count,accMul($("#gr1 tr:eq("+i+") td:eq(1) input").val(),$("#gr1 tr:eq("+i+") td:eq(4) input").val()));
			count=accAdd(count,$("#gr1 tr:eq("+i+") td:eq(4) input").val());
		}else if($("#gr1 tr:eq("+i+") td:eq(2) select").val() == "CPI"){
			//count=accAdd(count,accSub($("#gr1 tr:eq("+i+") td:eq(4) input").val(),$("#gr1 tr:eq("+i+") td:eq(1) input").val()));
			count=accSub(count,$("#gr1 tr:eq("+i+") td:eq(4) input").val());
		}
	}
	}
	$("#grv1").val(count);
}
//计算部门绩效
function countBm(){
	

	var trLength=$("#bm1 tr").length;
	var count=0;
	if(trLength>1){
	for(var i=1;i<trLength;i++){
		if($("#bm1 tr:eq("+i+") td:eq(2) select").val() == "KPI"){
			//count=accAdd(count,accMul($("#bm1 tr:eq("+i+") td:eq(1) input").val(),$("#bm1 tr:eq("+i+") td:eq(4) input").val()));
			count=accAdd(count,$("#bm1 tr:eq("+i+") td:eq(4) input").val());
		}else if($("#bm1 tr:eq("+i+") td:eq(2) select").val() == "CPI"){
			//count=accAdd(count,accSub($("#bm1 tr:eq("+i+") td:eq(4) input").val(),$("#bm1 tr:eq("+i+") td:eq(1) input").val()));
			count=accSub(count,$("#bm1 tr:eq("+i+") td:eq(4) input").val());
		}
	}
	}
	$("#bmv1").val(count);
}

function validateJx(){


	var count1=0;
	var count2=0;
	var count3=0;
	var count4=0;
	if($("#gr1 tr").length>1){
	for(var i=1;i<$("#gr1 tr").length;i++){
		count1=accAdd(count1,$("#gr1 tr:eq("+i+") td:eq(1) input").val());
	}
//	if(count1!=1){
//		return "上月个人指标权重合计应等于1!";
//	}
	}
	if($("#gr2 tr").length>1){
		for(var i=1;i<$("#gr2 tr").length;i++){
			count2=accAdd(count2,$("#gr2 tr:eq("+i+") td:eq(1) input").val());
		}

		}
	if($("#bm1 tr").length>1){
		for(var i=1;i<$("#bm1 tr").length;i++){
			count3=accAdd(count3,$("#bm1 tr:eq("+i+") td:eq(1) input").val());
		}
	
		}
	if($("#bm2 tr").length>1){
		for(var i=1;i<$("#bm2 tr").length;i++){
			count4=accAdd(count4,$("#bm2 tr:eq("+i+") td:eq(1) input").val());
		}
	
		}
  return "1";
	
}
function addGr1(){
	

	var targetObj;
	targetObj = $("<tr><td height=20  width='40%' style='text-align:center;'><input name='zbmc_gr_sy' style='width:100%'  class='easyui-validatebox' data-options='required:true'/></td><td height=20  width='12%' style='text-align:center;'><input name='zbqz_gr_sy' style='width:100%' validtype='qz' class='easyui-validatebox' data-options='required:true'/></td><td height=20  width='12%' style='text-align:center;'><select name='jxlb_gr_sy'><option value='KPI'>KPI</option><option value='CPI'>CPI</option></select></td><td height=20  width='12%' style='text-align:center;'><input name='zpfz_gr_sy' style='width:100%' validtype='jx' class='easyui-validatebox' data-options='required:true'/></td><td height=20  width='12%' style='text-align:center;'><input style='width:100%' readonly='true'/></td><td height=20  width='12%' style='text-align:center;'><a href='javascript:void' onclick='del(this);'>删除</a></td></tr>").appendTo("#gr1");
	$.parser.parse(targetObj);
}
function addGr2(){
	alert(1);
	var targetObj;
	targetObj = $("<tr><td height=20  width='40%' style='text-align:center;'><input name='zbmc_gr_by' style='width:100%'  class='easyui-validatebox' data-options='required:true'/></td><td height=20  width='12%' style='text-align:center;'><input name='zbqz_gr_by' style='width:100%' validtype='qz' class='easyui-validatebox' data-options='required:true'/></td><td height=20  width='12%' style='text-align:center;'><select name='jxlb_gr_by'><option value='KPI'>KPI</option><option value='CPI'>CPI</option></select></td><td height=20  width='12%' style='text-align:center;'><input  style='width:100%' readonly='true'/></td><td height=20  width='12%' style='text-align:center;'><input  style='width:100%' readonly='true'/></td><td height=20  width='12%' style='text-align:center;'><a href='javascript:void' onclick='del(this);'>删除</a></td></tr>").appendTo("#gr2");
	$.parser.parse(targetObj);
}
function addBm1(){
	var targetObj;
	targetObj = $("<tr><td height=20  width='40%' style='text-align:center;'><input name='zbmc_bm_sy' style='width:100%'  class='easyui-validatebox' data-options='required:true'/></td><td height=20  width='12%' style='text-align:center;'><input name='zbqz_bm_sy' style='width:100%' validtype='qz' class='easyui-validatebox' data-options='required:true'/></td><td height=20  width='12%' style='text-align:center;'><select name='jxlb_bm_sy'><option value='KPI'>KPI</option><option value='CPI'>CPI</option></select></td><td height=20  width='12%' style='text-align:center;'><input name='zpfz_bm_sy' style='width:100%' validtype='jx' class='easyui-validatebox' data-options='required:true'/></td><td height=20  width='12%' style='text-align:center;'><input  style='width:100%' readonly='true'/></td><td height=20  width='12%' style='text-align:center;'><a href='javascript:void' onclick='del(this);'>删除</a></td></tr>").appendTo("#bm1");
	$.parser.parse(targetObj);
}
function addBm2(){
	var targetObj;
	targetObj = $("<tr><td height=20  width='40%' style='text-align:center;'><input name='zbmc_bm_by' style='width:100%'  class='easyui-validatebox' data-options='required:true'/></td><td height=20  width='12%' style='text-align:center;'><input name='zbqz_bm_by' style='width:100%' validtype='qz' class='easyui-validatebox' data-options='required:true'/></td><td height=20  width='12%' style='text-align:center;'><select name='jxlb_bm_by'><option value='KPI'>KPI</option><option value='CPI'>CPI</option></select></td><td height=20  width='12%' style='text-align:center;'><input  style='width:100%' readonly='true'/></td><td height=20  width='12%' style='text-align:center;'><input  style='width:100%' readonly='true'/></td><td height=20  width='12%' style='text-align:center;'><a href='javascript:void' onclick='del(this);'>删除</a></td></tr>").appendTo("#bm2");
	$.parser.parse(targetObj);
}
function del(now){
	$(now).parent().parent().remove(); 
}

$.fn.selectReadOnly=function(){ 
	var tem=$(this).children('option').index($("option:selected")); $(this).change(function(){ $(this).children('option').eq(tem).attr("selected",true);  }); 
} 
function confirI(e){
	if(e.checked){
		if(empId != '<%=empId%>'){  
			alert('请让员工本人确认！');
			e.checked = false;
		}else if(empId == '<%=empId%>'){
			readOnly();
		}
	}else{
		if(empId == '<%=empId%>'){  
			alert('你已经确认了，不能再做修改！');
			e.checked = true;
		}
	}
}

function leaderConfire(e){
	if(e.checked){
		if(empId == '<%=empId%>'){  
			alert('请不要越权操作！');
			e.checked = false;
		}
	}
}

function readOnly(){
	$("input").attr("readonly","readonly");
	$("textarea").attr("readonly","readonly");
	$("a").attr("onclick","");
	$("select").attr("onfocus","this.defaultIndex=this.selectedIndex;");
	$("select").attr("onchange","this.selectedIndex=this.defaultIndex;");
}

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
					<td><input name="data.syuser.id" value="" readonly="readonly" style="display:none"/></td>
					<td><input id="empId" name="empId" style="display:none"/></td>
					</tr>
			     <tr>
					<th  >姓名</th>
					<td>
					<div id="name" ></div>
					</td>
					<th>评价时间</th>
					<td><input readonly="true" id="perDate" name="data.perDate" class="" style="width: 155px" /></td>
					
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
			
			<table id="t1" class="table" style="width: 100%;">
			<tr>
					<th rowspan="3"  >上月工作回顾</th>
					<th rowspan="1">工作目标及改进计划</th>
					<td rowspan="1" colspan="4">
					<div id="tt1" class="easyui-tabs" style="width:700px;height:200px">  
            <div title="个人指标"  style="width:100%"> 
		    <table id="gr1" cellspacing="0" cellpadding="0" width="100%">
            <tr>  
            <th height=20  width="40%" style="text-align:center;">指标名称</th>  
            <th height=20  width="12%" style="text-align:center;">指标权重</th>
            <th height=20  width="12%" style="text-align:center;">绩效类别</th>
            <th height=20  width="12%" style="text-align:center;">自我评分</th>
            <th height=20  width="12%" style="text-align:center;">领导评分</th>
            <th height=20  width="12%" style="text-align:center;"><a href="javascript:void" id="gr1_btn" onclick="addGr1();">添加</a></th>
            
            </tr>
		    </table>
		  </div>
		  <div title="部门指标"  style="width:100%"> 
		    <table id="bm1" cellspacing="0" cellpadding="0" width="100%">
            <tr>  
            <th height=20  width="40%" style="text-align:center;">指标名称</th>  
            <th height=20  width="12%" style="text-align:center;">
			指标权重
				<!-- select name="jxlb_bm_sy">
					<option value="1">CPI</option>
					<option value="2">KPI</option>
				</select-->
			</th>
            <th height=20  width="12%" style="text-align:center;">绩效类别</th>
            <th height=20  width="12%" style="text-align:center;">自我评分</th>
            <th height=20  width="12%" style="text-align:center;">领导评分</th>
            <th height=20  width="12%" style="text-align:center;"><a href="javascript:void" id="bm1_btn" onclick="addBm1();">添加</a></th>
            
            </tr>
		    </table>
		  </div>
		
</div>
					
					</td>
					
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
					<td rowspan="1" colspan="4">
					<div id="tt2" class="easyui-tabs" style="width:700px;height:200px">  
            <div title="个人指标"  style="width:100%"> 
		    <table id="gr2" cellspacing="0" cellpadding="0" width="100%">
            <tr>  
            <th height=20  width="40%" style="text-align:center;">指标名称</th>  
            <th height=20  width="12%" style="text-align:center;">指标权重</th>
            <th height=20  width="12%" style="text-align:center;">绩效类别</th>
            <th height=20  width="12%" style="text-align:center;">自我评分</th>
            <th height=20  width="12%" style="text-align:center;">领导评分</th>
            <th height=20  width="12%" style="text-align:center;"><a href="javascript:void" onclick="addGr2();">添加</a></th>
            </tr>
		    </table>
		  </div>
		  <div title="部门指标"  style="width:100%"> 
		    <table id="bm2" cellspacing="0" cellpadding="0" width="100%">
            <tr>  
            <th height=20  width="40%" style="text-align:center;">指标名称</th>  
            <th height=20  width="12%" style="text-align:center;">
            	考核权重
				<!-- select name="jxlb_bm_by">
					<option value="1">CPI</option>
					<option value="2">KPI</option>
				</select-->
			</th>
            <th height=20  width="12%" style="text-align:center;">绩效类别</th>
            <th height=20  width="12%" style="text-align:center;">自我评分</th>
            <th height=20  width="12%" style="text-align:center;">领导评分</th>
            <th height=20  width="12%" style="text-align:center;"><a href="javascript:void" onclick="addBm2();">添加</a></th>
            
            </tr>
		    </table>
		  </div>
					</td>
					</tr>
					<tr>
					<th rowspan="3" >考核结果及运用</th>
					<th rowspan="1" >个人绩效</th>
					<td rowspan="1" colspan="1"><textarea style="width: 200px;height: 30px"name="data.isLeaderConfer" id="grv1"></textarea>（总分为1）</td>
				</tr>
				<tr>
					<th rowspan="1" >部门绩效</th>				
					<td rowspan="1" colspan="1"><textarea style="width: 200px;height: 30px"name="data.isLeaderConfer2" id="bmv1"></textarea>（总分为1）</td>
				</tr>
				<tr>
					<th rowspan="1" >专项奖惩</th>				
					<td rowspan="1" colspan="4"><textarea style="width: 700px;height: 30px"name="data.isPersonalConfer"></textarea></td>
				</tr>
				<tr>
					<th rowspan="2" style="text-align:left;">员工确认</th>				
					<td rowspan="1" colspan="4" style="text-align:left;">
					员工本人是否已经确认本月'个人指标'?(若确认，请勾选；否则不勾选)  确认：
					<input type="checkbox" name="data.isConfergrzb" value="1" onchange="confirI(this);"/>
					</td>
				</tr>
				<tr>
					<td rowspan="1" colspan="4" style="text-align:left;">
					员工本人是否已经知晓本次考核结果？(若知晓，请勾选；否则不勾选)  确认：
					<input type="checkbox" name="data.isConfer" value="1" onchange="confirI(this);"/>
					</td>
				</tr>
				<tr>
					<th rowspan="1" style="text-align:left;">上级确认</th>				
					<td rowspan="1" colspan="4" style="text-align:left;">
					上级是否确认该员工本月的'个人指标'?(若确认，请勾选；否则不勾选) 确认：
					<input type="checkbox" name="data.isLeaderConferGrzb" value="1" onchange="leaderConfire(this);" />
					</td>
				</tr>
			</table>
			<table id="t2" class="table" style="width: 100%;">
				
				<tr>
					<th rowspan="3"  >上月工作</th>
					<th rowspan="1">利润目标与进度差距</th>
					<td rowspan="1" colspan="4">
					<div id="tt1" class="easyui-tabs" style="width:700px;height:200px"> 
					<div title="个人指标"  style="width:100%"> 
		    <table id="gr1" cellspacing="0" cellpadding="0" width="100%">
            <tr>  
            <th height=20  width="40%" style="text-align:center;">指标名称</th>  
            <th height=20  width="12%" style="text-align:center;">指标权重</th>
            <th height=20  width="12%" style="text-align:center;">绩效类别</th>
            <th height=20  width="12%" style="text-align:center;">自我评分</th>
            <th height=20  width="12%" style="text-align:center;">领导评分</th>
            <th height=20  width="12%" style="text-align:center;"><a href="javascript:void" onclick="addGr1();">添加</a></th>
            
            </tr>
		    </table>
		  </div>
		  <div title="部门指标"  style="width:100%"> 
		    <table id="bm1" cellspacing="0" cellpadding="0" width="100%">
            <tr>  
            <th height=20  width="40%" style="text-align:center;">指标名称</th>  
            <th height=20  width="12%" style="text-align:center;">指标权重</th>
            <th height=20  width="12%" style="text-align:center;">绩效类别</th>
            <th height=20  width="12%" style="text-align:center;">自我评分</th>
            <th height=20  width="12%" style="text-align:center;">领导评分</th>
            <th height=20  width="12%" style="text-align:center;"><a href="javascript:void" onclick="addBm1();">添加</a></th>
            
            </tr>
		    </table>
		  </div>
		  </div>
					</td>
					
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
					<td rowspan="1" colspan="4">
					<div id="tt2" class="easyui-tabs" style="width:700px;height:200px">  
            <div title="个人指标"  style="width:100%"> 
		    <table id="gr2" cellspacing="0" cellpadding="0" width="100%">
            <tr>  
            <th height=20  width="40%" style="text-align:center;">指标名称</th>  
            <th height=20  width="12%" style="text-align:center;">指标权重</th>
            <th height=20  width="12%" style="text-align:center;">绩效类别</th>
            <th height=20  width="12%" style="text-align:center;">自我评分</th>
            <th height=20  width="12%" style="text-align:center;">领导评分</th>
            <th height=20  width="12%" style="text-align:center;"><a href="javascript:void" onclick="addGr2();">添加</a></th>
            </tr>
		    </table>
		  </div>
		  <div title="部门指标"  style="width:100%"> 
		    <table id="bm2" cellspacing="0" cellpadding="0" width="100%">
            <tr>  
            <th height=20  width="40%" style="text-align:center;">指标名称</th>  
            <th height=20  width="12%" style="text-align:center;">指标权重</th>
            <th height=20  width="12%" style="text-align:center;">绩效类别</th>
            <th height=20  width="12%" style="text-align:center;">自我评分</th>
            <th height=20  width="12%" style="text-align:center;">领导评分</th>
            <th height=20  width="12%" style="text-align:center;"><a href="javascript:void" onclick="addBm2();">添加</a></th>
            
            </tr>
		    </table>
		  </div>
		  </div>
					</td>
					
				</tr>
				
				<tr>
					<th rowspan="4" >考核结果及运用</th>
					<th rowspan="1" >个人绩效</th>
					<td rowspan="1" colspan="1">
					<textarea readonly="true" style="width: 200px;height: 30px"name="data.isLeaderConfer" id="grv1">
					</textarea>（总分为1）
					</td>
				</tr>
				<tr>
					<th rowspan="1" >部门绩效</th>				
					<td rowspan="1" colspan="1">
					<textarea readonly="true" style="width: 200px;height: 30px"name="data.isLeaderConfer2" id="bmv2"></textarea>（总分为1）</td>
				</tr>
				<tr>
					<th rowspan="1" >专项奖惩</th>				
					<td rowspan="1" colspan="4"><textarea  style="width: 700px;height: 30px"name="data.isPersonalConfer"></textarea></td>
				</tr>
				<tr>
					<th rowspan="1" >员工确认</th>				
					<td rowspan="1" colspan="1">
					我已知晓本次考核结果并确认(请勾选确认框)。
					<input style="width: 20px; height: 20px;" type="checkbox" name="data.isConfer" value="1"   />
					</td>
				</tr>
			</table>
		</fieldset>
		
		
		

	</form>
	<input type="hidden" name="data.zbcjsj" id="pic" value=<%=date %> class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM'})" readonly="readonly" style="width: 158px;" />
</body>
</html>