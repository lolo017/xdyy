<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="aisino.reportform.util.base.SecurityUtil"%>
	<%@ page import="aisino.reportform.model.base.SessionInfo"%>
<%
String contextPath = request.getContextPath();
SecurityUtil securityUtil = new SecurityUtil(session);
SessionInfo sessionInfo = (SessionInfo) session.getAttribute("sessionInfo");
String name=sessionInfo.getUser().getName();
String userId=sessionInfo.getUser().getId();
String empId=sessionInfo.getUser().getEmpId();
SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
String date= format.format(new Date());
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
<script type="text/javascript" src="<%=contextPath%>/jslib/My97DatePicker4.8Beta3/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">

var grid;


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
				url = sy.contextPath + '/base/performance!update.sy';
			} else {
				url = sy.contextPath + '/base/performance!doNotNeedSecurity_savePerformance.sy';
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

		
	$(function() {
	
		//$("#perDate").datebox("setValue", getCurrentDate());
		$("#perDate").val(getCurrentDate());
		//查询个人信息
		$.post(
				sy.contextPath + '/base/performance!doNotNeedSecurity_getEmpData.sy',
				{
					id : <%=empId%>
				},
				function(result) {
					if (result.empId != undefined) {
						
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
		//查询上月个人指标
		$.post(
				sy.contextPath + '/base/jxzj!grid2.sy',
				{
					id : <%=empId%>,
				    zbcjsj: getCurrentDate().substring(0,7)
				},
				function(result) {
					var targetObj;
					var str="";
					for(var o=0;o<result.sy.length;o++){ 
						var _select;
						if(result.sy[o].jxlb == "CPI"){
							_select = "<select name='jxlb_gr_sy'><option value='KPI'>KPI</option><option value='CPI' selected='selected'>CPI</option></select>";
						}else{
							_select = "<select name='jxlb_gr_sy'><option value='KPI' selected='selected'>KPI</option><option value='CPI'>CPI</option></select>";
						}
						//targetObj = $("<tr><td style='display:none'><input name='grIds' type='text' value='"+result.rows[o].id+"'/></td><td height=20  width='12%' style='text-align:center;'>"+result.rows[o].syempdataByEmpdataid.name+"</td><td height=20 width='12%' style='text-align:center;'>"+result.rows[o].zbcjsj+"</td><td height=20 width='28%' style='text-align:center;''>"+result.rows[o].zbmc+"</td><td height=20 width='12%' style='text-align:center;'>"+result.rows[o].zbqz+"</td><td height=20 width='12%' style='text-align:center;'>"+result.rows[o].zbfs+"</td><td height=20 width='12%' style='text-align:center;'><input name='grfs' style='width:100%' validtype='jx' class='easyui-validatebox' data-options='required:true'/></td><td height=20 width='12%' style='text-align:center;'></td></tr>").appendTo("#gr1");
		                targetObj = $("<tr><th height=20  width='40%' style='text-align:center;'><input name='zbmc_gr_sy' style='width:100%'  class='easyui-validatebox' data-options='required:true' value='"+result.sy[o].zbmc+"'/></th><th height=20  width='12%' style='text-align:center;'><input name='zbqz_gr_sy' style='width:100%' validtype='qz' class='easyui-validatebox' data-options='required:true' value='"+result.sy[o].zbqz+"'/></th><th height=20  width='12%' style='text-align:center;'>"+ _select +"</th><th height=20  width='12%' style='text-align:center;'><input name='zpfz_gr_sy' style='width:100%' validtype='jxVali[0," + result.sy[o].zbqz +"]' class='easyui-validatebox' data-options='required:true'/></th><th height=20  width='12%' style='text-align:center;'><input  style='width:100%' readonly='true'/></th><th height=20  width='12%' style='text-align:center;'></th>").appendTo("#gr1");
						$.parser.parse(targetObj);
						str=str+(Number(o)+1)+"."+result.sy[o].zbmc+"  ";
					}
				$("#mb1").val(str);
				$("#mb2").val(str);
				$("#mb3").val(str);
				}, 'json');
		//查询上月部门指标
		$.post(
				sy.contextPath + '/base/jxbm!grid2.sy',
				{

					id : <%=empId%>,
				    zbcjsj: getCurrentDate().substring(0,7)
				},
				function(result) {
					var targetObj;
					for(var o=0;o<result.sy.length;o++){ 
						var _select;
						if(result.sy[o].jxlb == "CPI"){
							_select = "<select name='jxlb_bm_sy'><option value='KPI'>KPI</option><option value='CPI' selected='selected'>CPI</option></select>";
						}else{
							_select = "<select name='jxlb_bm_sy'><option value='KPI' selected='selected'>KPI</option><option value='CPI'>CPI</option></select>";
						}
						
						
						   targetObj = $("<tr><th height=20  width='40%' style='text-align:center;'><input name='zbmc_bm_sy' style='width:100%'  class='easyui-validatebox' data-options='required:true' value='"+result.sy[o].zbmc+"'/></th><th height=20  width='12%' style='text-align:center;'><input name='zbqz_bm_sy' style='width:100%' validtype='qz' class='easyui-validatebox' data-options='required:true' value='"+result.sy[o].zbqz+"'/></th><th height=20  width='12%' style='text-align:center;'>" + _select + "</th><th height=20  width='12%' style='text-align:center;'><input name='zpfz_bm_sy' style='width:100%' validtype='jxVali[0," + result.sy[o].zbqz +"]' class='easyui-validatebox' data-options='required:true'/></th><th height=20  width='12%' style='text-align:center;'><input style='width:100%' readonly='true'/></th><th height=20  width='12%' style='text-align:center;'></th>").appendTo("#bm1");
						$.parser.parse(targetObj);

					}
				}, 'json');
		
		
	});
	function addGr1(){
		

		var targetObj;
		targetObj = $("<tr><td height=20  width='40%' style='text-align:center;'><input name='zbmc_gr_sy' style='width:100%'  class='easyui-validatebox' data-options='required:true'/></td><td height=20  width='12%' style='text-align:center;'><input name='zbqz_gr_sy' style='width:100%' validtype='qz' class='easyui-validatebox' data-options='required:true'/></td><td height=20  width='12%' style='text-align:center;'><select name='jxlb_gr_sy'><option value='KPI'>KPI</option><option value='CPI'>CPI</option></select></td><td height=20  width='12%' style='text-align:center;'><input name='zpfz_gr_sy' style='width:100%' validtype='jx' class='easyui-validatebox' data-options='required:true'/></td><td height=20  width='12%' style='text-align:center;'><input style='width:100%' readonly='true'/></td><td height=20  width='12%' style='text-align:center;'><a href='javascript:void' onclick='del(this);'>删除</a></td></tr>").appendTo("#gr1");
		$.parser.parse(targetObj);
	}
// 	function addGr1(){
// 		var targetObj;
// 		targetObj = $("<tr><th height=20  width='40%' style='text-align:center;'><input name='zbmc_gr_sy' style='width:100%'  class='easyui-validatebox' data-options='required:true'/></th><th height=20  width='12%' style='text-align:center;'><input name='zbqz_gr_sy' style='width:100%' validtype='qz' class='easyui-validatebox' data-options='required:true'/></th><th height=20  width='12%' style='text-align:center;'><select name='jxlb_gr_sy'><option value='KPI'>KPI</option><option value='CPI'>CPI</option></select></th><th height=20  width='12%' style='text-align:center;'><input name='zpfz_gr_sy' style='width:100%' class='easyui-validatebox' data-options='required:true' readonly='true'/></th><th height=20  width='12%' style='text-align:center;'><input style='width:100%' readonly='true'/></th><th height=20  width='12%' style='text-align:center;'><a href='javascript:void(0)' onclick='del(this);'>删除</a></th>").appendTo("#gr1");
// 		$.parser.parse(targetObj);
// 	}
	function addGr2(){
		var targetObj;
		targetObj = $("<tr><th height=20  width='40%' style='text-align:center;'><input name='zbmc_gr_by' style='width:100%'  class='easyui-validatebox' data-options='required:true'/></th><th height=20  width='12%' style='text-align:center;'><input name='zbqz_gr_by' style='width:100%' validtype='qz' class='easyui-validatebox' data-options='required:true'/></th><th height=20  width='12%' style='text-align:center;'><select name='jxlb_gr_by'><option value='KPI'>KPI</option><option value='CPI'>CPI</option></select></th><th height=20  width='12%' style='text-align:center;'><input  style='width:100%' readonly='true'/></th><th height=20  width='12%' style='text-align:center;'><input  style='width:100%' readonly='true'/></th><th height=20  width='12%' style='text-align:center;'><a href='javascript:void(0)' onclick='del(this);'>删除</a></th>").appendTo("#gr2");
		$.parser.parse(targetObj);
	}
	function addBm1(){
		var targetObj;
		targetObj = $("<tr><th height=20  width='40%' style='text-align:center;'><input name='zbmc_bm_sy' style='width:100%'  class='easyui-validatebox' data-options='required:true'/></th><th height=20  width='12%' style='text-align:center;'><input name='zbqz_bm_sy' style='width:100%' validtype='qz' class='easyui-validatebox' data-options='required:true'/></th><th height=20  width='12%' style='text-align:center;'><select name='jxlb_bm_sy'><option value='KPI'>KPI</option><option value='CPI'>CPI</option></select></th><th height=20  width='12%' style='text-align:center;'><input name='zpfz_bm_sy' style='width:100%' readonly='true' validtype='jx' class='easyui-validatebox' data-options='required:true'/></th><th height=20  width='12%' style='text-align:center;'><input  style='width:100%' readonly='true'/></th><th height=20  width='12%' style='text-align:center;'><a href='javascript:void(0)' onclick='del(this);'>删除</a></th>").appendTo("#bm1");
		$.parser.parse(targetObj);
	}
	function addBm2(){
		var targetObj;
		targetObj = $("<tr><th height=20  width='40%' style='text-align:center;'><input name='zbmc_bm_by' style='width:100%'  class='easyui-validatebox' data-options='required:true'/></th><th height=20  width='12%' style='text-align:center;'><input name='zbqz_bm_by' style='width:100%' validtype='qz' class='easyui-validatebox' data-options='required:true'/></th><th height=20  width='12%' style='text-align:center;'><select name='jxlb_bm_by'><option value='KPI'>KPI</option><option value='CPI'>CPI</option></select></th><th height=20  width='12%' style='text-align:center;'><input  style='width:100%' readonly='true'/></th><th height=20  width='12%' style='text-align:center;'><input  style='width:100%' readonly='true'/></th><th height=20  width='12%' style='text-align:center;'><a href='javascript:void(0)' onclick='del(this);'>删除</a></th>").appendTo("#bm2");
		$.parser.parse(targetObj);
	}
	function del(now){
		$(now).parent().parent().remove(); 
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
					<td><input name="data.syuser.id" value="<%=userId %>" readonly="readonly" style="display:none"/></td>
					<td><input name="empId" value="<%=empId %>" style="display:none"/></td>
					
					</tr>
			     <tr>
					<th >姓名</th>
					<td>
					<div id="name" ><%=name %></div>
					</td>
					<th>评价时间</th>
					<td><input id="perDate" name="data.perDate" class="" style="width: 155px" readonly="readonly" /></td>
					
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
					<td rowspan="1" colspan="4">
			<div id="tt1" class="easyui-tabs" style="width:700px;height:200px">  
            <div title="个人指标"  style="width:100%"> 
		    <table id="gr1" cellspacing="0" cellpadding="0" width="100%">
            <tr>  
            <th height=20  width="40%" style="text-align:center;">指标名称</th>  
            <th height=20  width="10%" style="text-align:center;">指标权重</th>
            <th height=20  width="10%" style="text-align:center;">绩效类别</th>
            <th height=20  width="10%" style="text-align:center;">自我评分</th>
            <th height=20  width="10%" style="text-align:center;">领导评分</th>
            <th height=20  width="10%" style="text-align:center;"><a href="javascript:void" onclick="addGr1();">添加</a></th>
            
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
            <th height=20  width="12%" style="text-align:center;">指标权重</th>
            <th height=20  width="12%" style="text-align:center;">绩效类别</th>
            <th height=20  width="12%" style="text-align:center;">自我评分</th>
            <th height=20  width="12%" style="text-align:center;">领导评分</th>
            <th height=20  width="12%" style="text-align:center;"><a href="javascript:void" onclick="addBm2();">添加</a></th>
            
            </tr>
		    </table>
		  </div>
					</td>
					<tr>
					<th rowspan="3" >考核结果及运用</th>
					<th rowspan="1" >个人绩效</th>
					<td rowspan="1" colspan="1"><textarea readonly="true" style="width: 200px;height: 30px"name="data.isLeaderConfer"></textarea>（总分为1）</td>
				</tr>
				<tr>
					<th rowspan="1" >部门绩效</th>				
					<td rowspan="1" colspan="1"><textarea readonly="true" style="width: 200px;height: 30px"name="data.isLeaderConfer2"></textarea>（总分为1）</td>
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
					</td>
					
				</tr>
				
				<tr>
					<th rowspan="3" >考核结果及运用</th>
					<th rowspan="1" >绩效评分</th>
					<td rowspan="1" colspan="1"><textarea readonly="true" style="width: 200px;height: 30px"name="data.isLeaderConfer"></textarea>（总分为1）</td>
				</tr>
				<tr>
					<th rowspan="1" >部门绩效</th>				
					<td rowspan="1" colspan="1"><textarea readonly="true" style="width: 200px;height: 30px"name="data.isLeaderConfer2"></textarea>（总分为1）</td>
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
            <th height=20  width="12%" style="text-align:center;">指标权重</th>
            <th height=20  width="12%" style="text-align:center;">绩效类别</th>
            <th height=20  width="12%" style="text-align:center;">自我评分</th>
            <th height=20  width="12%" style="text-align:center;">领导评分</th>
            <th height=20  width="12%" style="text-align:center;"><a href="javascript:void" onclick="addBm2();">添加</a></th>
            
            </tr>
		    </table>
		  </div>
					</td>
					<tr>
					<th rowspan="2" >考核结果及运用</th>
					<th rowspan="1" >绩效评分</th>
					<td rowspan="1" colspan="1"><textarea readonly="true" style="width: 200px;height: 30px"name="data.isLeaderConfer"></textarea>（总分为1）</td>
				</tr>
				<tr>
					<th rowspan="1" >部门绩效</th>				
					<td rowspan="1" colspan="1"><textarea readonly="true" style="width: 200px;height: 30px"name="data.isLeaderConfer2"></textarea>（总分为1）</td>
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