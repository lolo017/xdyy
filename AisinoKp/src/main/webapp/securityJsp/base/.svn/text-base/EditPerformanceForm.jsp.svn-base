<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="aisino.reportform.util.base.SecurityUtil"%>
<%
SecurityUtil securityUtil = new SecurityUtil(session);
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
		if ($('form').form('validate')) {
			var url;
			if ($(':input[name="data.id"]').val().length > 0) {
				url = sy.contextPath + '/base/performance!update.sy';
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
	
	Date.prototype.pattern=function(fmt) {     
	    var o = {     
	    "M+" : this.getMonth()+1, //月份     
	    "d+" : this.getDate(), //日     
	    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时     
	    "H+" : this.getHours(), //小时     
	    "m+" : this.getMinutes(), //分     
	    "s+" : this.getSeconds(), //秒     
	    "q+" : Math.floor((this.getMonth()+3)/3), //季度     
	    "S" : this.getMilliseconds() //毫秒     
	    };     
	    var week = {     
	    "0" : "/u65e5",     
	    "1" : "/u4e00",     
	    "2" : "/u4e8c",     
	    "3" : "/u4e09",     
	    "4" : "/u56db",     
	    "5" : "/u4e94",     
	    "6" : "/u516d"    
	    };     
	    if(/(y+)/.test(fmt)){     
	        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));     
	    }     
	    if(/(E+)/.test(fmt)){     
	        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);     
	    }     
	    for(var k in o){     
	        if(new RegExp("("+ k +")").test(fmt)){     
	            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));     
	        }     
	    }     
	    return fmt;     
	}   
	  
	var d = new Date().pattern("yyyy-MM-dd");
	function escape2Html(str) {
		 var arrEntities={'lt':'<','gt':'>','nbsp':' ','amp':'&','quot':'"'};
		 return str.replace(/&(lt|gt|nbsp|amp|quot);/ig,function(all,t){return arrEntities[t];});
		}	
	$(function() {
		$("#perDate").datebox("setValue", d);
		if ($(':input[name="data.id"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(
							sy.contextPath + '/base/performance!getById.sy',
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
														'data.isLeaderConfer':decodeHtml(result.isLeaderConfer),
														'data.isPersonalConfer':decodeHtml(result.isPersonalConfer),
														'data.dep':decodeHtml(result.dep),
														'data.position':decodeHtml(result.position),
														'data.leader':decodeHtml(result.leader)
													});
// 									alert(decodeHtml(result.summary));
									//$('input[name="data.summary"]').val(escape2Html(result.summary));
									$("#name").html(result.syuser.name);
									$("#perDate").datebox("setValue", result.perDate.substr(0,10));
									if(result.type=="1"){
										o=document.getElementById('t2');
										o.parentNode.removeChild(o);
									}
									if(result.type=="2"){
										o=document.getElementById('t1');
										o.parentNode.removeChild(o);
									}
									
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
			<legend>绩效信息</legend>
			<table  class="table" style="width: 100%;">
			<tr style="display:none">
			        <th>编号</th>
					<td><input name="data.id" value="<%=id %>" readonly="readonly" style="display:none"/></td>
					<th>用户ID</th>
					<td><input name="data.syuser.id" value="" readonly="readonly" style="display:none"/></td>
					</tr>
			     <tr>
					<th  >姓名</th>
					<td>
					<div id="name" ></div>
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
		</fieldset>
	</form>
</body>
</html>