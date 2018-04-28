<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="aisino.reportform.model.base.SessionInfo"%>

<%
	String contextPath = request.getContextPath();
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute("sessionInfo");
	int isPwd=sessionInfo.getUser().getIsPwd();
%>
<!DOCTYPE html>
<html>
<head>
<title>欢迎页面</title>
<jsp:include page="inc.jsp"></jsp:include>
<style type="text/css">
.projectText{position:relative; width:1000px;margin-top:-183px;color:#FFFFFF;
	background:url(style/images/bg_div.png);
	-moz-border-radius: 10px;      /* Gecko browsers */
	-webkit-border-radius: 10px;   /* Webkit browsers */
	border-radius:10px;            /* W3C syntax */
	border:1px solid #CDCDCD;
}
td {
overflow: hidden; text-overflow: ellipsis;word-break: keep-all;white-space:nowrap;
}
#treeTable td{text-align:center;}
#treeTable {

}
</style>
<script>
var jq = top.jQuery;
var trs = 0;
$(function(){
	changeType();
	});
function loadRoles(type){
	$.post(sy.contextPath +  '/base/syresource!doNotNeedSecurity_getRolesByUserid.sy?type='+type ,{} , function(result) {
		var trIndex;
		$('treeTable > tbody').remove();
		$("#treeTable").html("<tr></tr>");
		for(var o=0;o<result.length;o++){
		//for(var o in result){ 

				trIndex=$("#treeTable tr").length-1;
				if($("#treeTable tr:eq("+trIndex+") td").length<3){
					if("主舱" == result[o].NAME)
						$("#treeTable tr:eq("+trIndex+")").append("<td width=\"\" height=\"120\"align=\"center\"  onclick=\"changeTreeTable('" + result[o].ID +"','"+type+"')\" style=\"cursor:pointer;font-size:14px;font-weight:bold;\" onmouseover=\"this.childNodes[0].style.marginTop ='20px'\" onmouseout=\"this.childNodes[0].style.marginTop ='0px'\">"
							+"<img src=\"style/images/bg/" + result[o].PIC +".png\" /><br/>"+result[o].NAME+"</td>");
					else 
						$("#treeTable tr:eq("+trIndex+")").append("<td width=\"\" height=\"120\"align=\"center\"  onclick=\"changeRoleType('" + result[o].ID +"','"+type+"')\" style=\"cursor:pointer;font-size:14px;font-weight:bold;\" onmouseover=\"this.childNodes[0].style.marginTop ='20px'\" onmouseout=\"this.childNodes[0].style.marginTop ='0px'\">"
								+"<img src=\"style/images/bg/" + result[o].PIC +".png\" /><br/>"+result[o].NAME+"</td>");
					
					trs++;
				}else{
					$("#treeTable").append("<tr></tr>");
				    trIndex=$("#treeTable tr").length-1;
				    if("主舱" == result[o].NAME)
				    	$("#treeTable tr:eq("+trIndex+")").append("<td width=\"\" height=\"120\" align=\"center\" onclick=\"changeTreeTable('" + result[o].ID +"','"+type+"')\" style=\"cursor:pointer;font-size:14px;font-weight:bold;\" onmouseover=\"this.childNodes[0].style.marginTop ='20px'\" onmouseout=\"this.childNodes[0].style.marginTop ='0px'\">"
				    		+"<img src=\"style/images/bg/" + result[o].PIC +".png\" /><br/>" + result[o].NAME+"</td>");
				    else
				    	$("#treeTable tr:eq("+trIndex+")").append("<td width=\"\" height=\"120\" align=\"center\" onclick=\"changeRoleType('" + result[o].ID +"','"+type+"')\" style=\"cursor:pointer;font-size:14px;font-weight:bold;\" onmouseover=\"this.childNodes[0].style.marginTop ='20px'\" onmouseout=\"this.childNodes[0].style.marginTop ='0px'\">"
					    		+"<img src=\"style/images/bg/" + result[o].PIC +".png\" /><br/>" + result[o].NAME+"</td>");
				}
		}
		//修改返回键
		$('#back').attr('onclick','changeType();');
	}, 'json');
	
}
function loadResources(e){
	if("undefined" ==  typeof e)
		e = "";
	$.post(sy.contextPath +  '/base/syresource!doNotNeedSecurity_getResourcesByUserid.sy?type='+e,{} , function(result) {
		var trIndex;
		$('treeTable > tbody').remove();
		$("#treeTable").html("<tr></tr>");
		for(var o=0;o<result.length;o++){
		//for(var o in result){ 
			
// 				trIndex=$("#treeTable tr").length-1;
// 				if($("#treeTable tr:eq("+trIndex+") td").length<3){
// 					$("#treeTable tr:eq("+trIndex+")").append("<td width=\"\" height=\"120\"align=\"center\"  onclick=\"changeTree('"+result[o].ID+"','"+e+"')\" style=\"cursor:pointer;font-size:14px;font-weight:bold;\" onmouseover=\"this.childNodes[0].style.marginTop ='20px'\" onmouseout=\"this.childNodes[0].style.marginTop ='0px'\">"
// 							+"<img src=\"style/images/bg/" + result[o].ICONCLS +".png\" /><br/>"+result[o].NAME+"</td>");
// 					trs++;
// 				}else{
// 					$("#treeTable").append("<tr></tr>");
// 				    trIndex=$("#treeTable tr").length-1;
// 				    $("#treeTable tr:eq("+trIndex+")").append("<td width=\"\" height=\"120\" align=\"center\" onclick=\"changeTree('"+result[o].ID+"','"+e+"')\" style=\"cursor:pointer;font-size:14px;font-weight:bold;\" onmouseover=\"this.childNodes[0].style.marginTop ='20px'\" onmouseout=\"this.childNodes[0].style.marginTop ='0px'\">"
// 				    		+"<img src=\"style/images/bg/" + result[o].ICONCLS +".png\" /><br/>" + result[o].NAME+"</td>");
// 				}
				trIndex=$("#treeTable tr").length-1;
				if($("#treeTable tr:eq("+trIndex+") td").length<3){
					if(result[o].URL){
					$("#treeTable tr:eq("+trIndex+")").append("<td width=\"\" height=\"120\" align=\"center\" onclick=\"changeTree('"+result[o].ID+"','"+e+"','"+"','"+result[o].URL+"','"+result[o].NAME+"','"+result[o].ICONCLS+"')\" style=\"cursor:pointer;font-size:14px;font-weight:bold;\" onmouseover=\"this.childNodes[0].style.marginTop ='20px'\" onmouseout=\"this.childNodes[0].style.marginTop ='0px'\">"
							+"<img src=\"style/images/bg/" + result[o].ICONCLS +".png\" /><br/>"+result[o].NAME+"</td>");
					}else{
						$("#treeTable tr:eq("+trIndex+")").append("<td width=\"\" height=\"120\" align=\"center\" onclick=\"changeTree('"+result[o].ID+"','"+e+"','"+"')\" style=\"cursor:pointer;font-size:14px;font-weight:bold;\" onmouseover=\"this.childNodes[0].style.marginTop ='20px'\" onmouseout=\"this.childNodes[0].style.marginTop ='0px'\">"
								+"<img src=\"style/images/bg/" + result[o].ICONCLS +".png\" /><br/>"+result[o].NAME+"</td>");
					}
					trs++;
				}else{
					$("#treeTable").append("<tr></tr>");
				    trIndex=$("#treeTable tr").length-1;
				    if(result[o].URL){
						$("#treeTable tr:eq("+trIndex+")").append("<td width=\"\" height=\"120\" align=\"center\" onclick=\"changeTree('"+result[o].ID+"','"+e+"','"+"','"+result[o].URL+"','"+result[o].NAME+"','"+result[o].ICONCLS+"')\" style=\"cursor:pointer;font-size:14px;font-weight:bold;\" onmouseover=\"this.childNodes[0].style.marginTop ='20px'\" onmouseout=\"this.childNodes[0].style.marginTop ='0px'\">"
								+"<img src=\"style/images/bg/" + result[o].ICONCLS +".png\" /><br/>"+result[o].NAME+"</td>");
						}else{
							$("#treeTable tr:eq("+trIndex+")").append("<td width=\"\" height=\"120\" align=\"center\" onclick=\"changeTree('"+result[o].ID+"','"+e+"','"+"')\" style=\"cursor:pointer;font-size:14px;font-weight:bold;\" onmouseover=\"this.childNodes[0].style.marginTop ='20px'\" onmouseout=\"this.childNodes[0].style.marginTop ='0px'\">"
									+"<img src=\"style/images/bg/" + result[o].ICONCLS +".png\" /><br/>"+result[o].NAME+"</td>");
						}
				}
				
			
		}
		//修改返回键
		$('#back').attr('onclick','changeType();');
	}, 'json');
}
function changeTree(id,type,roleid,url,name,iconcls){
	//jq("#mainLayout").layout('expand','west');
	//jq("#mainMenu").tree("options").url=sy.contextPath+ '/base/syresource!doNotNeedSecurity_getMainMenuById.sy?id='+id;
	//jq("#mainMenu").tree('reload');
	var content = '<iframe scrolling="auto" frameborder="0"  src="<%=contextPath%>' + url+'" style="border: 0; width: 100%; height: 99%;"></iframe>';     
	
if(url){
    if (jq("#mainTabs").tabs('exists', name)){    
        jq("#mainTabs").tabs('select', name);
        var currTab = jq("#mainTabs").tabs('getTab',name);  
        jq("#mainTabs").tabs('update', {tab: currTab, options: {content: content, closable: true}});  
    } else {  
           jq("#mainTabs").tabs('add',{title:name,iconCls :iconcls,content:content,closable:true});   
     }    

}else{
	$.post(sy.contextPath +  '/base/syresource!doNotNeedSecurity_getResourcesByPid.sy?type='+type+'&id='+id+'&roleid='+roleid,{} , function(result) {
		if(result.length>0){
		var trIndex;
		$('treeTable > tbody').remove();
		$("#treeTable").html("<tr></tr>");
		for(var o=0;o<result.length;o++){
		//for(var o in result){ 
			
			trIndex=$("#treeTable tr").length-1;
			if($("#treeTable tr:eq("+trIndex+") td").length<3){
				if(result[o].URL){
				$("#treeTable tr:eq("+trIndex+")").append("<td width=\"\" height=\"120\" align=\"center\" onclick=\"changeTree('"+result[o].ID+"','"+type+"','"+roleid+"','"+result[o].URL+"','"+result[o].NAME+"','"+result[o].ICONCLS+"')\" style=\"cursor:pointer;font-size:14px;font-weight:bold;\" onmouseover=\"this.childNodes[0].style.marginTop ='20px'\" onmouseout=\"this.childNodes[0].style.marginTop ='0px'\">"
						+"<img src=\"style/images/bg/" + result[o].ICONCLS +".png\" /><br/>"+result[o].NAME+"</td>");
				}else{
					$("#treeTable tr:eq("+trIndex+")").append("<td width=\"\" height=\"120\" align=\"center\" onclick=\"changeTree('"+result[o].ID+"','"+type+"','"+roleid+"')\" style=\"cursor:pointer;font-size:14px;font-weight:bold;\" onmouseover=\"this.childNodes[0].style.marginTop ='20px'\" onmouseout=\"this.childNodes[0].style.marginTop ='0px'\">"
							+"<img src=\"style/images/bg/" + result[o].ICONCLS +".png\" /><br/>"+result[o].NAME+"</td>");
				}
				trs++;
			}else{
				$("#treeTable").append("<tr></tr>");
			    trIndex=$("#treeTable tr").length-1;
			    if(result[o].URL){
					$("#treeTable tr:eq("+trIndex+")").append("<td width=\"\" height=\"120\" align=\"center\" onclick=\"changeTree('"+result[o].ID+"','"+type+"','"+roleid+"','"+result[o].URL+"','"+result[o].NAME+"','"+result[o].ICONCLS+"')\" style=\"cursor:pointer;font-size:14px;font-weight:bold;\" onmouseover=\"this.childNodes[0].style.marginTop ='20px'\" onmouseout=\"this.childNodes[0].style.marginTop ='0px'\">"
							+"<img src=\"style/images/bg/" + result[o].ICONCLS +".png\" /><br/>"+result[o].NAME+"</td>");
					}else{
						$("#treeTable tr:eq("+trIndex+")").append("<td width=\"\" height=\"120\" align=\"center\" onclick=\"changeTree('"+result[o].ID+"','"+type+"','"+roleid+"')\" style=\"cursor:pointer;font-size:14px;font-weight:bold;\" onmouseover=\"this.childNodes[0].style.marginTop ='20px'\" onmouseout=\"this.childNodes[0].style.marginTop ='0px'\">"
								+"<img src=\"style/images/bg/" + result[o].ICONCLS +".png\" /><br/>"+result[o].NAME+"</td>");
					}
			}
				
			
		}
		//修改返回键
		$("#back").attr("onclick","changeLast('"+result[0].ID+"','"+type+"','"+roleid+"');");
		}
	}, 'json');
}
}
function changeLast(id,type,roleid){
	$.post(sy.contextPath +  '/base/syresource!doNotNeedSecurity_getResourcesLast.sy?type='+type+'&id='+id+'&roleid='+roleid,{} , function(result) {
		
		var trIndex;
		$('treeTable > tbody').remove();
		$("#treeTable").html("<tr></tr>");
		for(var o=0;o<result.length;o++){
		//for(var o in result){ 
			
			trIndex=$("#treeTable tr").length-1;
			if($("#treeTable tr:eq("+trIndex+") td").length<3){
				if(result[o].URL){
				$("#treeTable tr:eq("+trIndex+")").append("<td width=\"\" height=\"120\" align=\"center\" onclick=\"changeTree('"+result[o].ID+"','"+type+"','"+roleid+"','"+result[o].URL+"','"+result[o].NAME+"','"+result[o].ICONCLS+"')\" style=\"cursor:pointer;font-size:14px;font-weight:bold;\" onmouseover=\"this.childNodes[0].style.marginTop ='20px'\" onmouseout=\"this.childNodes[0].style.marginTop ='0px'\">"
						+"<img src=\"style/images/bg/" + result[o].ICONCLS +".png\" /><br/>"+result[o].NAME+"</td>");
				}else{
					$("#treeTable tr:eq("+trIndex+")").append("<td width=\"\" height=\"120\" align=\"center\" onclick=\"changeTree('"+result[o].ID+"','"+type+"','"+roleid+"')\" style=\"cursor:pointer;font-size:14px;font-weight:bold;\" onmouseover=\"this.childNodes[0].style.marginTop ='20px'\" onmouseout=\"this.childNodes[0].style.marginTop ='0px'\">"
							+"<img src=\"style/images/bg/" + result[o].ICONCLS +".png\" /><br/>"+result[o].NAME+"</td>");
				}
				trs++;
			}else{
				$("#treeTable").append("<tr></tr>");
			    trIndex=$("#treeTable tr").length-1;
			    if(result[o].URL){
					$("#treeTable tr:eq("+trIndex+")").append("<td width=\"\" height=\"120\" align=\"center\" onclick=\"changeTree('"+result[o].ID+"','"+type+"','"+roleid+"','"+result[o].URL+"','"+result[o].NAME+"','"+result[o].ICONCLS+"')\" style=\"cursor:pointer;font-size:14px;font-weight:bold;\" onmouseover=\"this.childNodes[0].style.marginTop ='20px'\" onmouseout=\"this.childNodes[0].style.marginTop ='0px'\">"
							+"<img src=\"style/images/bg/" + result[o].ICONCLS +".png\" /><br/>"+result[o].NAME+"</td>");
					}else{
						$("#treeTable tr:eq("+trIndex+")").append("<td width=\"\" height=\"120\" align=\"center\" onclick=\"changeTree('"+result[o].ID+"','"+type+"','"+roleid+"')\" style=\"cursor:pointer;font-size:14px;font-weight:bold;\" onmouseover=\"this.childNodes[0].style.marginTop ='20px'\" onmouseout=\"this.childNodes[0].style.marginTop ='0px'\">"
								+"<img src=\"style/images/bg/" + result[o].ICONCLS +".png\" /><br/>"+result[o].NAME+"</td>");
					}
			}
				
			if(result[0].PID){
				//如果有pid，继续返回上一层
				$("#back").attr("onclick","changeLast('"+result[0].ID+"','"+type+"','"+roleid+"');");
			}else{
				if(type == '1')
					$('#back').attr('onclick','loadRoles("'+type+'")');
				if(type == '2')
					$('#back').attr('onclick','changeType()');
			}
		
		}
	}, 'json');
}

function changeType(){
	//var myRoleId = -1;
	$('treeTable > tbody').remove();
	//$.post(sy.contextPath +  '/base/syresource!doNotNeedSecurity_getRolesByUserid.sy',{} , function(result) {
		//myRoleId = result[0].ID;
	//	for(var o in result){
	//		if("主舱" == result[o].NAME){
	//			myRoleId = result[o].ID ;
	//			break;
	//		}
	//	}	
		
	//},'json');	
		$("#treeTable").html("<tr></tr>");
		$("#treeTable tr:eq(0)").append("<td width=\"50%\" height=\"120\" align=\"center\" onclick=\"loadRoles('1');\" style=\"cursor:pointer;font-size:14px;font-weight:bold;\" onmouseover=\"this.childNodes[0].style.marginTop ='20px'\" onmouseout=\"this.childNodes[0].style.marginTop ='0px'\">"
				+"<img src=\"style/images/bg/6b286604-95ef-4b2e-9d02-4728a5882333.png\" /><br/>我的驾驶仓</td>");
		$("#treeTable tr:eq(0)").append("<td width=\"50%\" height=\"120\" align=\"center\" onclick=\"loadResources('2');\" style=\"cursor:pointer;font-size:14px;font-weight:bold;\" onmouseover=\"this.childNodes[0].style.marginTop ='20px'\" onmouseout=\"this.childNodes[0].style.marginTop ='0px'\">"
				+"<img src=\"style/images/bg/256e8303-4f48-479f-a5fd-a55b76a527a0.png\" /><br/>我的操作仓</td>");

	//修改返回键
	$('#back').attr('onclick','');
	//$("#project").css("height",120*trs + "px");
	//判断是否是初始密码
	<%if(isPwd==0){%>
	jq('#firstDialog').dialog('open');
	<%}%>
}

function changeTreeTable(roleid,_type,back){
	
	$.post(sy.contextPath +  '/base/syresource!doNotNeedSecurity_getResourcesByRoleid.sy',{roleid:roleid,type:_type} , function(result) {
		var trIndex;
		$('treeTable > tbody').remove();
		$("#treeTable").html("<tr></tr>");
		for(var o=0;o<result.length;o++){
		//for(var o in result){ 
			
				trIndex=$("#treeTable tr").length-1;
				if($("#treeTable tr:eq("+trIndex+") td").length<3){
					if(result[o].URL){
					$("#treeTable tr:eq("+trIndex+")").append("<td width=\"\" height=\"120\" align=\"center\" onclick=\"changeTree('"+result[o].ID+"','"+_type+"','"+roleid+"','"+result[o].URL+"','"+result[o].NAME+"','"+result[o].ICONCLS+"')\" style=\"cursor:pointer;font-size:14px;font-weight:bold;\" onmouseover=\"this.childNodes[0].style.marginTop ='20px'\" onmouseout=\"this.childNodes[0].style.marginTop ='0px'\">"
							+"<img src=\"style/images/bg/" + result[o].ICONCLS +".png\" /><br/>"+result[o].NAME+"</td>");
					}else{
						$("#treeTable tr:eq("+trIndex+")").append("<td width=\"\" height=\"120\" align=\"center\" onclick=\"changeTree('"+result[o].ID+"','"+_type+"','"+roleid+"')\" style=\"cursor:pointer;font-size:14px;font-weight:bold;\" onmouseover=\"this.childNodes[0].style.marginTop ='20px'\" onmouseout=\"this.childNodes[0].style.marginTop ='0px'\">"
								+"<img src=\"style/images/bg/" + result[o].ICONCLS +".png\" /><br/>"+result[o].NAME+"</td>");
					}
					trs++;
				}else{
					$("#treeTable").append("<tr></tr>");
				    trIndex=$("#treeTable tr").length-1;
				    if(result[o].URL){
						$("#treeTable tr:eq("+trIndex+")").append("<td width=\"\" height=\"120\" align=\"center\" onclick=\"changeTree('"+result[o].ID+"','"+_type+"','"+roleid+"','"+result[o].URL+"','"+result[o].NAME+"','"+result[o].ICONCLS+"')\" style=\"cursor:pointer;font-size:14px;font-weight:bold;\" onmouseover=\"this.childNodes[0].style.marginTop ='20px'\" onmouseout=\"this.childNodes[0].style.marginTop ='0px'\">"
								+"<img src=\"style/images/bg/" + result[o].ICONCLS +".png\" /><br/>"+result[o].NAME+"</td>");
						}else{
							$("#treeTable tr:eq("+trIndex+")").append("<td width=\"\" height=\"120\" align=\"center\" onclick=\"changeTree('"+result[o].ID+"','"+_type+"','"+roleid+"')\" style=\"cursor:pointer;font-size:14px;font-weight:bold;\" onmouseover=\"this.childNodes[0].style.marginTop ='20px'\" onmouseout=\"this.childNodes[0].style.marginTop ='0px'\">"
									+"<img src=\"style/images/bg/" + result[o].ICONCLS +".png\" /><br/>"+result[o].NAME+"</td>");
						}
// 				    $("#treeTable tr:eq("+trIndex+")").append("<td width=\"\" height=\"120\" align=\"center\" onclick=\"changeTree('"+result[o].ID+"')\" style=\"cursor:pointer;font-size:14px;font-weight:bold;\" onmouseover=\"this.childNodes[0].style.marginTop ='20px'\" onmouseout=\"this.childNodes[0].style.marginTop ='0px'\">"
// 				    		+"<img src=\"style/images/bg/" + result[o].ICONCLS +".png\" /><br/>" + result[o].NAME+"</td>");
				}
		}
		if(_type == '1')
			$('#back').attr('onclick','loadRoles("'+_type+'")');
		if(_type == '2')
			$('#back').attr('onclick','changeType()');
		if("undefine" !=  typeof back)
			$('#back').attr('onclick','loadRoles("'+_type+'")');
	}, 'json');
}



function changeRoleType(roleid,type){
	$("#treeTable").html("<tr></tr>");
	$("#treeTable tr:eq(0)").append("<td width=\"50%\" height=\"120\" align=\"center\" onclick=\"changeTreeTable('"+ roleid +"','1','');\" style=\"cursor:pointer;font-size:14px;font-weight:bold;\" onmouseover=\"this.childNodes[0].style.marginTop ='20px'\" onmouseout=\"this.childNodes[0].style.marginTop ='0px'\">"
			+"<img src=\"style/images/bg/6b286604-95ef-4b2e-9d02-4728a5882333.png\" /><br/>驾驶仓</td>");
	$("#treeTable tr:eq(0)").append("<td width=\"50%\" height=\"120\" align=\"center\" onclick=\"changeTreeTable('"+ roleid +"','2','');\" style=\"cursor:pointer;font-size:14px;font-weight:bold;\" onmouseover=\"this.childNodes[0].style.marginTop ='20px'\" onmouseout=\"this.childNodes[0].style.marginTop ='0px'\">"
			+"<img src=\"style/images/bg/256e8303-4f48-479f-a5fd-a55b76a527a0.png\" /><br/>操作仓</td>");
	$('#back').attr('onclick','loadRoles("'+type+'")');
}

</script>
</head>
<body style="background:#FFFFFF url('<%=contextPath%>/style/images/bg/19.jpg') no-repeat scroll 0 0 ;">
<div style="margin-left:48%">
<a  id="all" href="javascript:void(0);" style="color:white" onclick="changeType();">主页</a>   <a id="back" style="color:white" href="javascript:void(0);" onclick="loadRoles();">返回</a></div>
<div class="projectText" style="margin:30px auto 0;">
<table id="treeTable" width="100%" style="table-layout:fixed;align:center;text-align:center;font-size: 17px;">
<tr>

</tr>
</table>

</div>
</body>
</html>