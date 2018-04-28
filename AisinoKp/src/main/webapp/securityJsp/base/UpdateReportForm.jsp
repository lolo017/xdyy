<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="aisino.reportform.util.base.SecurityUtil"%>
<%
	String contextPath = request.getContextPath();
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
<style>
html,body{ height:96%;} 
td {overflow:hidden; text-overflow:ellipsis;}
</style>
</head>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
var previewGrid;
var id='<%=id%>';
var db;
var targetObj;
var widthArray=new Array();
var rowArray=new Array;
var rowspanArray=new Array;
var colspanArray=new Array;
var tStr;
var cStr;
var unit;
var highchartsBtn;
var sortOrder;
var sortPara="";
var tableIndex;
$(function() {
	if ($(':input[name="id"]').val().length > 0) {
		parent.$.messager.progress({
			text : '数据加载中....'
		});
		$.post(sy.contextPath + '/base/page!getPageInfo.sy', {
			id : $(':input[name="id"]').val()
		}, function(result) {	
			if (result.page.id != undefined) {
				$('form').form('load', {
					'id' : result.page.id,
					//'unit' : result.page.unit,
					'pageName':result.page.pageName
				}
				);
				$("#sql").val(result.page.sqlCode);
				$("#db").combobox("setValue",result.page.db);
				$("#sortOrder").combobox("setValue",result.page.sortOrder);
				$("#highchartsBtn").combobox("setValue",result.page.highchartsBtn);
				if(result.page.highchartsBtn==1){
					$("#unit").val(result.page.unit);
					$('#unit').attr('readonly', false);
				}
				
			}
			
			//遍历表头
			for(var p=0;p<result.pageCodes.length;p++){
				var s="<tr ><td  width='20%' height=20><input  class='easyui-validatebox' style='width: 95%' data-options='required:true' value='"+result.pageCodes[p].cnName+"'/></td><td  width='20%' height=20><input style='width: 95%' value='"+undefinedToString(result.pageCodes[p].cnEnglish)+"'></input></td><td  width='20%' height=20><select  id=\"isHighcharts"+p+"\" style=\"width:70%;\" panelHeight=\"110\"  class=\"easyui-combobox\" data-options=\"valueField: 'id',  textField: 'text' \" ><option value=\"0\">否</option><option value=\"1\">是</option></select></td>";
				if(result.pageCodes[p].url){
					s=s+"<td  width='25%' height=20><input  maxlength='1000' style='width: 60%' value='"+result.pageCodes[p].url+"'/>&nbsp;<img  class='iconImg ext-icon-zoom' title='选择' onclick='urlTree(this);'/></td><td style='display:none'><input  value='"+result.pageCodes[p].rowNum+"' /></td><td style='display:none'><input  value='"+result.pageCodes[p].rowspan+"' /></td><td style='display:none'><input  value='"+result.pageCodes[p].colspan+"' /></td><td style='display:none'><input  value='"+result.pageCodes[p].width+"' /></td><td  width='20%' height=20><a href='javascript:void(0);' onclick='delTname(this);'>删除</a>&nbsp;<a href='javascript:void(0);' onclick='more(this);'>更多</a></td></tr>";
				}else{
					s=s+"<td  width='25%' height=20><input  maxlength='1000' style='width: 60%' />&nbsp;<img  class='iconImg ext-icon-zoom' title='选择' onclick='urlTree(this);'/></td><td style='display:none'><input  value='"+result.pageCodes[p].rowNum+"' /></td><td style='display:none'><input  value='"+result.pageCodes[p].rowspan+"' /></td><td style='display:none'><input  value='"+result.pageCodes[p].colspan+"' /></td><td style='display:none'><input  value='"+result.pageCodes[p].width+"' /></td><td  width='20%' height=20><a href='javascript:void(0);' onclick='delTname(this);'>删除</a>&nbsp;<a href='javascript:void(0);' onclick='more(this);'>更多</a></td></tr>";
				}				
                targetObj=$(s).appendTo("#tTable");
				$.parser.parse(targetObj);
				$("#isHighcharts"+p).combobox("setValue",result.pageCodes[p].isHighcharts);
			}
			$('.iconImg').attr('src', sy.pixel_0);
			//遍历自定义预警
			for(var p=0;p<result.selfWarn.length;p++){
				var s="<tr><td  width='20%' height=20><input  class='easyui-validatebox' style='width: 95%' validtype='english' data-options='required:true' value='"+result.selfWarn[p].CNENGLISH+"'/></td>";
			if(result.selfWarn[p].GT!=undefined){
				s=s+"<td  width='20%' height=20><input type='text' class='easyui-numberbox' style='width: 95%' data-options='required:false' value='"+result.selfWarn[p].GT+"' /></td>";
			}else{
				s=s+"<td  width='20%' height=20><input type='text' class='easyui-numberbox' style='width: 95%' data-options='required:false'  /></td>";
			}
			if(result.selfWarn[p].LT!=undefined){
				s=s+"<td  width='20%' height=20><input type='text'   class='easyui-numberbox' style='width: 95%' data-options='required:false' value='"+result.selfWarn[p].LT+"'/></td>";
			}else{
				s=s+"<td  width='20%' height=20><input type='text'   class='easyui-numberbox' style='width: 95%' data-options='required:false' /></td>";
			}
			s=s+"<td  width='35%' height=20><select  id=\"selfColor"+p+"\" style=\"width:80%;\" panelHeight=\"110\"  class=\"easyui-combobox\" data-options=\"valueField: 'id',  textField: 'text' \" ><option value=\"#66ff66\">绿</option><option value=\"#77FFEE\">蓝</option><option value=\"#FF3333\">红</option><option value=\"#FFFF00\">黄</option></select></td><td  width='25%' height=20><a href='javascript:void(0);' onclick='delTname(this);'>删除</a></td></tr>";
			targetObj=$(s).appendTo("#selfTable");
		    $.parser.parse(targetObj);
		    $("#selfColor"+p).combobox("setValue",result.selfWarn[p].COLOR);
			}
			//遍历平均值预警
			for(var p=0;p<result.avgWarn.length;p++){
				var s="<tr><td  width='20%' height=20><input  class='easyui-validatebox' style='width: 95%' validtype='english' data-options='required:true' value='"+result.avgWarn[p].CNENGLISH+"'/></td>";
				if(result.avgWarn[p].CNENGLISH_EXC){
					s=s+"<td  width='20%' height=20><input  maxlength='1000' style='width: 95%' value='"+result.avgWarn[p].CNENGLISH_EXC+"' /></td>";
				}else{
					s=s+"<td  width='20%' height=20><input  maxlength='1000' style='width: 95%'  /></td>";
				}
				if(result.avgWarn[p].CNENGLISH_STR){
					s=s+"<td  width='20%' height=20><input  maxlength='1000' style='width: 95%'  value='"+result.avgWarn[p].CNENGLISH_STR+"'/></td>";
				}else{
					s=s+"<td  width='20%' height=20><input  maxlength='1000' style='width: 95%'  /></td>";
				}
				s=s+"<td  width='35%' height=20><select  id=\"avgColor"+p+"\" style=\"width:80%;\" panelHeight=\"110\"  class=\"easyui-combobox\" data-options=\"valueField: 'id',  textField: 'text' \" ><option value=\"#66ff66\">绿</option><option value=\"#77FFEE\">蓝</option><option value=\"#FF3333\">红</option><option value=\"#FFFF00\">黄</option></select></td><td  width='25%' height=20><a href='javascript:void(0);' onclick='delTname(this);'>删除</a></td></tr>";
				targetObj=$(s).appendTo("#avgTable");
			    $.parser.parse(targetObj);
			    $("#avgColor"+p).combobox("setValue",result.avgWarn[p].COLOR);
			}
			//遍历SQL预警
			for(var p=0;p<result.sqlWarn.length;p++){
				var s="<tr><td  width='20%' height=20><input  class='easyui-validatebox' style='width: 95%' validtype='english' data-options='required:true' value='"+result.sqlWarn[p].CNENGLISH+"'/></td>";
			if(result.sqlWarn[p].GT_SQL!=undefined){
				s=s+"<td  width='20%' height=20><input id=\"gt_sql"+p+"\" type='text'  maxlength='10000' style='width:95%' value='' /></td>";
			}else{
				s=s+"<td  width='20%' height=20><input type='text'  maxlength='10000' style='width:95%'   /></td>";
			}
			if(result.sqlWarn[p].LT_SQL!=undefined){
				s=s+"<td  width='20%' height=20><input id=\"lt_sql"+p+"\"  type='text'  maxlength='10000' style='width:95%' value=''/></td>";
			}else{
				s=s+"<td  width='20%' height=20><input type='text'  maxlength='10000' style='width:95%'  /></td>";
			}
			s=s+"<td  width='35%' height=20><select  id=\"sqlColor"+p+"\" style=\"width:80%;\" panelHeight=\"110\"  class=\"easyui-combobox\" data-options=\"valueField: 'id',  textField: 'text' \" ><option value=\"#66ff66\">绿</option><option value=\"#77FFEE\">蓝</option><option value=\"#FF3333\">红</option><option value=\"#FFFF00\">黄</option></select></td><td  width='25%' height=20><a href='javascript:void(0);' onclick='delTname(this);'>删除</a></td></tr>";
			targetObj=$(s).appendTo("#sqlValueTable");
		    $.parser.parse(targetObj);
		    $("#sqlColor"+p).combobox("setValue",result.sqlWarn[p].COLOR);
		    $("#gt_sql"+p).val(result.sqlWarn[p].GT_SQL);
		    $("#lt_sql"+p).val(result.sqlWarn[p].LT_SQL);
			}
			//遍历条件
			if(result.sqlcondition.length>0){
				$("#conditionTable tr:eq(1)").remove();
				var j=result.sqlcondition;
				for(var c=0;c<result.sqlcondition.length;c++){
					if(j[c].conditiontype==1){
						targetObj=$("<tr><td width=30 height=25>"+(c+1)+"</td><td width=100 height=25><div onclick='changeToInput(this)' style='overflow:hidden;white-space:nowrap;text-overflow:ellipsis;width:100%;height:100%'>"+j[c].encondition+"</div></td><td width=100 height=25><div onclick='changeToInput(this)' style='overflow:hidden;white-space:nowrap;text-overflow:ellipsis;width:100%;height:100%'>"+j[c].cncondition+"</div></td><td width=100 height=25><div  style='width:100%;height:100%'><select data-options='onSelect:function(rec){ clearDefaultValue(this);   }'  class='easyui-combobox' style='width: 100%;'><option value='1'>日期</option><option value='2'>SQL下拉框</option><option value='3'>隐藏session值</option><option value='4'>文本框</option></select></div></td><td width=150 height=25><div onclick='changeToInput(this)' style='overflow:hidden;white-space:nowrap;text-overflow:ellipsis;width:100%;height:100%'></div></td><td width=100 height=25 ><input class='easyui-numberspinner' data-options='required:true,min:0,max:100000,editable:false' style='width: 80px;' value='1' /></td><td width=100 height=25><input id='"+j[c].encondition+"' class='easyui-datebox' data-options='editable:true'  style='width: 95px;' /></input></td><td><a href='javascript:void(0);' onclick='addTr()'>添加</a>    <a  href='javascript:void(0);' onclick='delTr(this)'>删除</a></td></tr>").appendTo("#conditionTable");
						$.parser.parse(targetObj);
						if(j[c].defaultValue){
							$("#"+j[c].encondition).datebox('setValue',j[c].defaultValue);
						}
					}else if(j[c].conditiontype==4){
						targetObj=$("<tr><td width=30 height=25>"+(c+1)+"</td><td width=100 height=25><div onclick='changeToInput(this)' style='overflow:hidden;white-space:nowrap;text-overflow:ellipsis;width:100%;height:100%'>"+j[c].encondition+"</div></td><td width=100 height=25><div onclick='changeToInput(this)' style='overflow:hidden;white-space:nowrap;text-overflow:ellipsis;width:100%;height:100%'>"+j[c].cncondition+"</div></td><td width=100 height=25><div  style='width:100%;height:100%'><select data-options='onSelect:function(rec){ clearDefaultValue(this);   }'  class='easyui-combobox' style='width: 100%;'><option value='1'>日期</option><option value='2'>SQL下拉框</option><option value='3'>隐藏session值</option><option value='4'>文本框</option></select></div></td><td width=150 height=25><div onclick='changeToInput(this)' style='overflow:hidden;white-space:nowrap;text-overflow:ellipsis;width:100%;height:100%'></div></td><td width=100 height=25 ><input class='easyui-numberspinner' data-options='required:true,min:0,max:100000,editable:false' style='width: 80px;' value='1' /></td><td width=100 height=25><input id='"+j[c].encondition+"'   style='width: 95px;' /></input></td><td><a href='javascript:void(0);' onclick='addTr()'>添加</a>    <a  href='javascript:void(0);' onclick='delTr(this)'>删除</a></td></tr>").appendTo("#conditionTable");
						$.parser.parse(targetObj);
						if(j[c].defaultValue){
							$("#"+j[c].encondition).val(j[c].defaultValue);
						}
					}
					else if(j[c].conditiontype==2){
						targetObj=$("<tr><td width=30 height=25>"+(c+1)+"</td><td width=100 height=25><div onclick='changeToInput(this)' style='overflow:hidden;white-space:nowrap;text-overflow:ellipsis;width:100%;height:100%'>"+j[c].encondition+"</div></td><td width=100 height=25><div onclick='changeToInput(this)' style='overflow:hidden;white-space:nowrap;text-overflow:ellipsis;width:100%;height:100%'>"+j[c].cncondition+"</div></td><td width=100 height=25><div  style='width:100%;height:100%'><select data-options='onSelect:function(rec){ clearDefaultValue(this);   }' class='easyui-combobox' style='width: 100%;'><option value='1'>日期</option><option value='2'>SQL下拉框</option><option value='3'>隐藏session值</option><option value='4'>文本框</option></select></div></td><td width=150 height=25><div onclick='changeToInput(this)' style='overflow:hidden;white-space:nowrap;text-overflow:ellipsis;width:100%;height:100%'>"+j[c].conditionsql+"</div></td><td width=100 height=25 ><input class='easyui-numberspinner' data-options='required:true,min:0,max:100000,editable:false' style='width: 80px;' value='1' /></td><td width=100 height=25></td><td><a href='javascript:void(0);' onclick='addTr()'>添加</a>    <a  href='javascript:void(0);' onclick='delTr(this)'>删除</a></td></tr>").appendTo("#conditionTable");
						$.parser.parse(targetObj);
					}else{
				        targetObj=$("<tr><td width=30 height=25>"+(c+1)+"</td><td width=100 height=25><div onclick='changeToInput(this)' style='overflow:hidden;white-space:nowrap;text-overflow:ellipsis;width:100%;height:100%'>"+j[c].encondition+"</div></td><td width=100 height=25><div onclick='changeToInput(this)' style='overflow:hidden;white-space:nowrap;text-overflow:ellipsis;width:100%;height:100%'>"+j[c].cncondition+"</div></td><td width=100 height=25><div  style='width:100%;height:100%'><select data-options='onSelect:function(rec){ clearDefaultValue(this);   }' class='easyui-combobox' style='width: 100%;'><option value='1'>日期</option><option value='2'>SQL下拉框</option><option value='3'>隐藏session值</option><option value='4'>文本框</option></select></div></td><td width=150 height=25><div onclick='changeToInput(this)' style='overflow:hidden;white-space:nowrap;text-overflow:ellipsis;width:100%;height:100%'></div></td><td width=100 height=25 ><input class='easyui-numberspinner' data-options='required:true,min:0,max:100000,editable:false' style='width: 80px;' value='1' /></td><td width=100 height=25></td><td><a href='javascript:void(0);' onclick='addTr()'>添加</a>    <a  href='javascript:void(0);' onclick='delTr(this)'>删除</a></td></tr>").appendTo("#conditionTable");
				        $.parser.parse(targetObj);
					}
				$("#conditionTable tr:eq("+(c+1)+") td:eq(3) div:eq(0) select").combobox('setValue',j[c].conditiontype);
				$("#conditionTable tr:eq("+(c+1)+") td:eq(5)  input").val(j[c].seq);
				}
				
			}
			parent.$.messager.progress('close');
		}, 'json');	
		
	}

	
});	


function preview(){
	//验证表头
	if(!$('#tForm').form('validate')){
		return false;
	}
	parent.$.messager.progress({
		text : '数据加载中....'
	});
	unit=$("#unit").val();
	highchartsBtn=$("#highchartsBtn").combobox("getValue");
	sortOrder=$("#sortOrder").combobox("getValue");
	db=$("#db").combobox("getValue");
	var sqlStr=formatSql($("#sql").val());
	$("#preDiv").html("<table id='previewGrid' data-options='fit:true,border:false'><thead id='pre'></thead></table>");
	
	var tArray= new Array;
	var cArray=new Array;
	var rowTotal=0;
	if($("#tTable tr").length>1){
	for(var i=1;i<$("#tTable tr").length;i++){
		tArray[i-1]=$("#tTable tr:eq("+i+") td:eq(0) input").val();
		cArray[i-1]=$("#tTable tr:eq("+i+") td:eq(1) input").val().toUpperCase();
		rowArray[i-1]=$("#tTable tr:eq("+i+") td:eq(4) input").val();
		rowspanArray[i-1]=$("#tTable tr:eq("+i+") td:eq(5) input").val();
		colspanArray[i-1]=$("#tTable tr:eq("+i+") td:eq(6) input").val();
		if(i==1){
			tStr=$("#tTable tr:eq("+i+") td:eq(0) input").val();
			cStr=$("#tTable tr:eq("+i+") td:eq(1) input").val().toUpperCase();
		}else{
			tStr=tStr+"+"+$("#tTable tr:eq("+i+") td:eq(0) input").val();
			cStr=cStr+"+"+$("#tTable tr:eq("+i+") td:eq(1) input").val().toUpperCase();
		}
		//计算总行数
		if(rowTotal<$("#tTable tr:eq("+i+") td:eq(4) input").val()){
			rowTotal=$("#tTable tr:eq("+i+") td:eq(4) input").val();
		}
		widthArray[i-1]=$("#tTable tr:eq("+i+") td:eq(7) input").val();
	}
	}else{
		$.messager.alert('error','表头字段不能为空!');
		return false;
	}
	tStr=formatSql(tStr);
	cStr=formatSql(cStr);
	var trLength=$("#conditionTable tr").length;
	var paramStr="";
	for(var i=1;i<trLength;i++){
		if($("#conditionTable tr:eq("+i+") td:eq(1) div").html()!=""){
			if(paramStr==""){
	        paramStr=$("#conditionTable tr:eq("+i+") td:eq(1) div").html();
			}else{
		    paramStr=paramStr+"+"+$("#conditionTable tr:eq("+i+") td:eq(1) div").html();
			}
		}
	}
	//根据rowTotal生成tr
	for(var t=0;t<=rowTotal;t++){
		$("#pre").append("<tr id='preTr"+t+"'></tr>");
	}
	for(var i=0;i<tArray.length;i++){ 
		
		if(cArray[i]=="QUERY_PARA"){
			$("#preTr"+rowArray[i]).append("<th rowspan='"+rowspanArray[i]+"' colspan='"+colspanArray[i]+"'  sortable='true' field='"+cArray[i]+"' align='center' hidden='true'>"+"查询参数"+"</th> ");
		}else if(cArray[i]=="SORT_PARA"){
			$("#preTr"+rowArray[i]).append("<th  rowspan='"+rowspanArray[i]+"' colspan='"+colspanArray[i]+"'  sortable='true' field='"+cArray[i]+"' align='center' hidden='true'>"+"排序字段"+"</th> ");
			sortPara=cArray[i];
		}
		else{
		$("#preTr"+rowArray[i]).append("<th rowspan='"+rowspanArray[i]+"' colspan='"+colspanArray[i]+"'  sortable='true' field='"+cArray[i]+"' align='center' width='"+widthArray[i]+"'>"+tArray[i]+"</th> ");
		}
	}
		tableIndex=PrintTableToExcel("previewGrid");
		$("#tableIndex").val(tableIndex);
		preGrid(sqlStr,paramStr);
// 	$.post(sy.contextPath +  '/base/report-form!findGridField.sy',{db:db,sqlStr:sqlStr,paramStr:paramStr} , function(result) {
// 		if (result.success) {
// 			var cArray= result.obj;
// 			var cStr="";
//			判断输入表头和SQL返回表头数是否相等
// 			if(tArray.length!=cArray.length){
// 				parent.$.messager.progress('close');
// 				$.messager.alert('error','表头字段数与SQL返回字段数不相等!');
// 				return false;
// 			}
// 			for(var i=0;i<tArray.length;i++){ 
// 				if(cArray[i]=="QUERY_PARA"){
// 					$("#preTr").append("<th sortable='true' field='"+cArray[i]+"' align='center' hidden='true'>"+"查询参数"+"</th> ");
// 				}else if(cArray[i]=="SORT_PARA"){
// 					$("#preTr").append("<th sortable='true' field='"+cArray[i]+"' align='center' hidden='true'>"+"排序字段"+"</th> ");
// 					sortPara=cArray[i];
// 				}
// 				else{
// 				$("#preTr").append("<th sortable='true' field='"+cArray[i]+"' align='center' width='"+widthArray[i]+"'>"+tArray[i]+"</th> ");
// 				}
// 				if(i<tArray.length-1){
// 					cStr=cStr+cArray[i]+"+";
// 				}else{
// 					cStr=cStr+cArray[i];
// 				}
			
// 			}
// 			$("#cName").val(cStr);
// 			preGrid(sqlStr,paramStr);
// 		} 
// 	}, 'json');
}

function preGrid(sqlStr,paramStr){
	previewGrid = $('#previewGrid').datagrid({
				title : '',
				url : sy.contextPath + '/base/report-form!previewSql.sy',
				queryParams:{
					sqlStr:sqlStr,
					paramStr:paramStr,
					db:db
				},
				fit:true,
				striped : false,
				rownumbers : true,
				pagination : true,
				singleSelect : true,
				idField : sortPara,
				sortName : sortPara,
				sortOrder : sortOrder,
				remoteSort: false,
				pageSize : 50,
				pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
						400, 500 ],
				onLoadSuccess : function(data) {
					parent.$.messager.progress('close');
				}
				
			});
}

function update($dialog, $grid, $pjq, $mainMenu){
	var trLength=$("#conditionTable tr").length;
	$("#condition").html("");
	$("#warn").html("");
	//隐藏提交condition
	for(var i=1;i<trLength;i++){
		if($("#conditionTable tr:eq("+i+") td:eq(1) div").html()!=""&&$("#conditionTable tr:eq("+i+") td:eq(2) div").html()!=""){
			$("#condition").append("<textarea style='display:none'  name='conditionList["+(i-1)+"].cncondition' >"+$("#conditionTable tr:eq("+i+") td:eq(2) div").html()+"</textarea>");
			$("#condition").append("<textarea  style='display:none'  name='conditionList["+(i-1)+"].encondition' >"+$("#conditionTable tr:eq("+i+") td:eq(1) div").html()+"</textarea>");
			$("#condition").append("<textarea  style='display:none'  name='conditionList["+(i-1)+"].conditiontype' >"+$("#conditionTable tr:eq("+i+") td:eq(3) div:eq(0) select").combobox('getValue')+"</textarea>");
			$("#condition").append("<textarea  style='display:none'  name='conditionList["+(i-1)+"].conditionsql' >"+formatSql($("#conditionTable tr:eq("+i+") td:eq(4) div").text())+"</textarea>");
			$("#condition").append("<textarea  style='display:none'  name='conditionList["+(i-1)+"].seq' >"+$("#conditionTable tr:eq("+i+") td:eq(5) input").val()+"</textarea>");
			if($("#conditionTable tr:eq("+i+") td:eq(3) div:eq(0) select").combobox('getValue')==1){
            $("#condition").append("<textarea  style='display:none'  name='conditionList["+(i-1)+"].defaultValue' >"+$("#conditionTable tr:eq("+i+") td:eq(6) input").datebox("getValue")+"</textarea>");
                
           }
			if($("#conditionTable tr:eq("+i+") td:eq(3) div:eq(0) select").combobox('getValue')==4){
	            $("#condition").append("<textarea  style='display:none'  name='conditionList["+(i-1)+"].defaultValue' >"+$("#conditionTable tr:eq("+i+") td:eq(6) input").val()+"</textarea>");
	                
	           }
		 
		}
	}
	//隐藏提交width,url,isHighcharts
	for(var ii=1;ii<$("#tTable tr").length;ii++){
		if($("#tTable tr:eq("+ii+") td:eq(3) input").val()==""){
			$("#condition").append("<textarea  style='display:none'  name='urlList["+(ii-1)+"]' >"+"empty"+"</textarea>");
		}else{
		$("#condition").append("<textarea  style='display:none'  name='urlList["+(ii-1)+"]' >"+encodeURIComponent($("#tTable tr:eq("+ii+") td:eq(3) input").val())+"</textarea>");
		}
		$("#condition").append("<textarea  style='display:none'  name='widthList["+(ii-1)+"]' >"+$("#tTable tr:eq("+ii+") td:eq(7) input").val()+"</textarea>");
		$("#condition").append("<textarea  style='display:none'  name='hcList["+(ii-1)+"]' >"+$("#tTable tr:eq("+ii+") td:eq(2) select").combobox('getValue')+"</textarea>");
        
	}
	//隐藏提交自定义预警
	for(var j=1;j<$("#selfTable tr").length;j++){
		$("#warn").append("<textarea  style='display:none'  name='selfList["+(j-1)+"].cnEnglish' >"+$("#selfTable tr:eq("+j+") td:eq(0) input").val()+"</textarea>");
		$("#warn").append("<textarea  style='display:none'  name='selfList["+(j-1)+"].gtValue' >"+$("#selfTable tr:eq("+j+") td:eq(1) input").val()+"</textarea>");
		$("#warn").append("<textarea  style='display:none'  name='selfList["+(j-1)+"].ltValue' >"+$("#selfTable tr:eq("+j+") td:eq(2) input").val()+"</textarea>");
		$("#warn").append("<textarea  style='display:none'  name='selfList["+(j-1)+"].color' >"+$("#selfTable tr:eq("+j+") td:eq(3) select").combobox('getValue')+"</textarea>");
	       
	}
	//隐藏提交平均值预警
	for(var j=1;j<$("#avgTable tr").length;j++){
		$("#warn").append("<textarea  style='display:none'  name='avgList["+(j-1)+"].cnEnglish' >"+$("#avgTable tr:eq("+j+") td:eq(0) input").val()+"</textarea>");
		$("#warn").append("<textarea  style='display:none'  name='avgList["+(j-1)+"].cnEnglishExc' >"+$("#avgTable tr:eq("+j+") td:eq(1) input").val()+"</textarea>");
		$("#warn").append("<textarea  style='display:none'  name='avgList["+(j-1)+"].cnEnglishStr' >"+$("#avgTable tr:eq("+j+") td:eq(2) input").val()+"</textarea>");
		$("#warn").append("<textarea  style='display:none'  name='avgList["+(j-1)+"].color' >"+$("#avgTable tr:eq("+j+") td:eq(3) select").combobox('getValue')+"</textarea>");
	       
	}
	//隐藏提交SQL预警
	for(var j=1;j<$("#sqlValueTable tr").length;j++){
		$("#warn").append("<textarea  style='display:none'  name='sqlList["+(j-1)+"].cnEnglish' >"+$("#sqlValueTable tr:eq("+j+") td:eq(0) input").val()+"</textarea>");
		$("#warn").append("<textarea  style='display:none'  name='sqlList["+(j-1)+"].gtSql' >"+formatSql($("#sqlValueTable tr:eq("+j+") td:eq(1) input").val())+"</textarea>");
		$("#warn").append("<textarea  style='display:none'  name='sqlList["+(j-1)+"].ltSql' >"+formatSql($("#sqlValueTable tr:eq("+j+") td:eq(2) input").val())+"</textarea>");
		$("#warn").append("<textarea  style='display:none'  name='sqlList["+(j-1)+"].color' >"+$("#sqlValueTable tr:eq("+j+") td:eq(3) select").combobox('getValue')+"</textarea>");
	       
	}
	$("#tName").val(tStr);
	$("#cName").val(cStr);
	$("#sqlStr").val(formatSql($("#sql").val()));
	$('input[name="db"]').val(db);
	$('input[name="sortOrder"]').val(sortOrder);
	$('input[name="unit"]').val(unit);
	$('input[name="highchartsBtn"]').val(highchartsBtn);
	$.messager.confirm("确认", "确认保存吗？", function (r) {  
        if (r) {  
        	$.post(sy.contextPath + '/base/report-form!updateSql.sy', sy.serializeObject($('#form')), function(result) {
        		if (result.success) {
        			$grid.datagrid('reload');
					$dialog.dialog('destroy');
				} else {
					parent.$.messager.alert('提示', result.msg, 'error');
				}
        			
        	}, 'json');
        }  
    });  
}

function addTr(){
	if($("#conditionTable tr:last td:eq(1) div").html()!=""&& $("#conditionTable tr:last td:eq(2) div").html()!=""){
		var tdNum=Number($("#conditionTable tr:last td:first").html())+1;
		var targetObj=$("<tr><td width=30 height=25>"+tdNum+"</td><td width=100 height=25><div onclick='changeToInput(this)' style='overflow:hidden;white-space:nowrap;text-overflow:ellipsis;width:100%;height:100%'></div></td><td width=100 height=25><div onclick='changeToInput(this)' style='overflow:hidden;white-space:nowrap;text-overflow:ellipsis;width:100%;height:100%'></div></td><td width=100 height=25><div  style='width:100%;height:100%'><select data-options='onSelect:function(rec){ clearDefaultValue(this);   }' class='easyui-combobox' style='width: 100%;'><option value='1'>日期</option><option value='2'>SQL下拉框</option><option value='3'>隐藏session值</option><option value='4'>文本框</option></select></div></td><td width=150 height=25><div onclick='changeToInput(this)' style='overflow:hidden;white-space:nowrap;text-overflow:ellipsis;width:100%;height:100%'></div></td><td width=100 height=25 ><input class='easyui-numberspinner' data-options='required:true,min:0,max:100000,editable:false' style='width: 80px;' value='1' /></td><td width=100 height=25><input class='easyui-datebox' data-options='editable:true'  style='width: 95px;' /></input></td><td><a href='javascript:void(0);' onclick='addTr()'>添加</a>    <a  href='javascript:void(0);' onclick='delTr(this)'>删除</a></td></tr>").appendTo("#conditionTable");
		$.parser.parse(targetObj);
	}
	
}

function delTr(now){
	var index = $(now).parent().parent().index();
	var tlength=Number($("#conditionTable tr").length)-1;
	for(var i=index+1;i<=tlength;i++){
		$("#conditionTable tr:eq("+i+") td:eq(0)").html(Number($("#conditionTable tr:eq("+i+") td:eq(0)").html())-1);
	}
	if(tlength>1){
		$(now).parent().parent().remove(); 
	}
}

var beforeVal;
var afterVal;
function changeToInput(now){
	var index = $(now).parent().parent().index();
	var tdNum=$("#conditionTable tr:eq("+index+") td:first").html();
    if($("#conditionTable tr:eq("+index+") td:eq(3) div:eq(0) select").combobox('getValue')!=2&&$(now).parent().index()==4){
    	return false;
    }
	beforeVal=$(now).text();
	$(now).parent().html("<input id='"+"td"+tdNum+"' maxlength='10000' style='height:100%;width:95%' type='text'  onblur='changeTd(this)'/>");
	$("#td"+tdNum).val(beforeVal);
	$("#td"+tdNum).focus();
}

function changeTd(now){
	afterVal=$(now).val();
    $(now).parent().html("<div style='width:100%;height:100%;overflow:hidden;white-space:nowrap;text-overflow:ellipsis;'  onclick='changeToInput(this)'>"+afterVal+"</div>");
	
}
function countChar(str,ch){
	  var c=0;
	  for(var i=0;i<str.length;i++){
		  if(str.charAt(i)==ch){ 
	      c++;
		  }
	  }
	  return c;
	}
	
function formatSql(sqlStr){
	var sqlStr=encodeURIComponent($.trim(sqlStr.replace(/\s+/g, ' ')));
	sqlStr=sqlStr.replace(/\'/g, "%27");
	sqlStr=sqlStr.replace(/\_/g, "%5F");
	sqlStr=sqlStr.replace(/\!/g, "%21");
	sqlStr=sqlStr.replace(/\*/g, "%2A");
	sqlStr=sqlStr.replace(/\-/g, "%2D");
	sqlStr=sqlStr.replace(/\(/g, "%28");
	sqlStr=sqlStr.replace(/\)/g, "%29");
	return sqlStr;
}
function addTname(){
	 targetObj=$("<tr><td  width='20%' height=20><input  class='easyui-validatebox' style='width: 95%' data-options='required:true' /></td><td  width='20%' height=20><input style='width: 95%'  value=''></input></td><td  width='20%' height=20><select  id=\"\" style=\"width:70%;\" panelHeight=\"110\"  class=\"easyui-combobox\" data-options=\"valueField: 'id',  textField: 'text' \" ><option value=\"0\">否</option><option value=\"1\">是</option></select></td><td  width='25%' height=20><input  maxlength='1000' style='width: 60%' />&nbsp;<img  class='iconImg ext-icon-zoom' title='选择' onclick='urlTree(this);'/></td><td style='display:none'><input  value='0' /></td><td style='display:none'><input  value='1' /></td><td style='display:none'><input  value='1' /></td><td style='display:none'><input  value='120' /></td><td  width='20%' height=20><a href='javascript:void(0);' onclick='delTname(this);'>删除</a>&nbsp;<a href='javascript:void(0);' onclick='more(this);'>更多</a></td></tr> ").appendTo("#tTable");
	 $.parser.parse(targetObj);
	 $('.iconImg').attr('src', sy.pixel_0);
}
function delTname(now){
	$(now).parent().parent().remove(); 
}
function addSelf(){
	targetObj=$("<tr><td  width='20%' height=20><input  class='easyui-validatebox' style='width: 95%' validtype='english' data-options='required:true' /></td><td  width='20%' height=20><input type='text' class='easyui-numberbox' style='width: 95%' data-options='required:false'  /></td><td  width='20%' height=20><input type='text'   class='easyui-numberbox' style='width: 95%' data-options='required:false'  value=''></input></td><td  width='35%' height=20><select  id=\"\" style=\"width:80%;\" panelHeight=\"110\"  class=\"easyui-combobox\" data-options=\"valueField: 'id',  textField: 'text' \" ><option value=\"#66ff66\">绿</option><option value=\"#77FFEE\">蓝</option><option value=\"#FF3333\">红</option><option value=\"#FFFF00\">黄</option></select></td><td  width='25%' height=20><a href='javascript:void(0);' onclick='delTname(this);'>删除</a></td></tr> ").appendTo("#selfTable");
	 $.parser.parse(targetObj);
}

function addAvg(){
	targetObj=$("<tr><td  width='20%' height=20><input  class='easyui-validatebox' style='width: 95%' validtype='english' data-options='required:true' /></td><td  width='20%' height=20><input  maxlength='1000' style='width: 95%' /></td><td  width='20%' height=20><input  maxlength='1000' style='width: 95%'  value=''></input></td><td><select  id=\"\" style=\"width:80%;\" panelHeight=\"110\"  class=\"easyui-combobox\" data-options=\"valueField: 'id',  textField: 'text' \" ><option value=\"#66ff66\">绿</option><option value=\"#77FFEE\">蓝</option><option value=\"#FF3333\">红</option><option value=\"#FFFF00\">黄</option></select></td><td  width='15%' height=20><a href='javascript:void(0);' onclick='delTname(this);'>删除</a></td></tr> ").appendTo("#avgTable");
	 $.parser.parse(targetObj);
}
function addSqlValue(){
	targetObj=$("<tr><td  width='20%' height=20><input  class='easyui-validatebox' style='width: 95%' validtype='english' data-options='required:true' /></td><td  width='20%' height=20><input  maxlength='1000' style='width: 95%' /></td><td  width='20%' height=20><input  maxlength='1000' style='width: 95%'  value=''></input></td><td><select  id=\"\" style=\"width:80%;\" panelHeight=\"110\"  class=\"easyui-combobox\" data-options=\"valueField: 'id',  textField: 'text' \" ><option value=\"#66ff66\">绿</option><option value=\"#77FFEE\">蓝</option><option value=\"#FF3333\">红</option><option value=\"##FFFF00\">黄</option></select></td><td  width='15%' height=20><a href='javascript:void(0);' onclick='delTname(this);'>删除</a></td></tr> ").appendTo("#sqlValueTable");
	$.parser.parse(targetObj);
}
//根据下拉框选中项，判断是否显示默认值
function clearDefaultValue(now){
	//日期
	if($("#conditionTable tr:eq("+$(now).parent().parent().parent().index()+") td:eq(3) div:eq(0) select").combobox("getValue")==1){
		$("#conditionTable tr:eq("+$(now).parent().parent().parent().index()+") td:eq(6)").html("<input class='easyui-datebox' data-options='editable:true'  style='width: 95px;' />");
		$.parser.parse("#conditionTable tr:eq("+$(now).parent().parent().parent().index()+") td:eq(6)");
	//文本框
	}else if($("#conditionTable tr:eq("+$(now).parent().parent().parent().index()+") td:eq(3) div:eq(0) select").combobox("getValue")==4){
		$("#conditionTable tr:eq("+$(now).parent().parent().parent().index()+") td:eq(6)").html("<input    style='width: 95px;' />")
	}else{
		$("#conditionTable tr:eq("+$(now).parent().parent().parent().index()+") td:eq(6)").html("");
	}
}

function urlTree(now){
	var url=encodeURIComponent($(now).parent().find("input").val());
	var dialog = parent.sy.modalDialog({
		title : '选择url',
		url : sy.contextPath + '/securityJsp/base/UrlTree.jsp?url='+url,
		height:540,
		buttons : [ {
			text : '确认',
			handler : function() {
				url=dialog.find('iframe').get(0).contentWindow.setUrl(dialog);
				$(now).parent().find("input").val(url);
				}
		} ]
	});
		
}
function more(now){
	var row=$("#tTable tr:eq("+$(now).parent().parent().index()+") td:eq(4) input").val();
	var rowspan=$("#tTable tr:eq("+$(now).parent().parent().index()+") td:eq(5) input").val();
	var colspan=$("#tTable tr:eq("+$(now).parent().parent().index()+") td:eq(6) input").val();
	var width=$("#tTable tr:eq("+$(now).parent().parent().index()+") td:eq(7) input").val();
	var dialog = parent.sy.modalDialog({
		title : '更多设置',
		url : sy.contextPath + '/securityJsp/base/more.jsp?row='+row+'&rowspan='+rowspan+'&colspan='+colspan+'&width='+width,
		height:540,
		buttons : [ {
			text : '确认',
			handler : function() {
				var array=dialog.find('iframe').get(0).contentWindow.setMore(dialog);
				if(array.length==4){
				$("#tTable tr:eq("+$(now).parent().parent().index()+") td:eq(4) input").val(array[0]);
				$("#tTable tr:eq("+$(now).parent().parent().index()+") td:eq(5) input").val(array[1]);
				$("#tTable tr:eq("+$(now).parent().parent().index()+") td:eq(6) input").val(array[2]);
				$("#tTable tr:eq("+$(now).parent().parent().index()+") td:eq(7) input").val(array[3]);
				}
				}
		} ]
	});
		
}
</script>

</head>
<body>

<div style="height:100%;width:100%;">	
<div style="width:30%;height:100%;float:left;font-size:10px;">
<div >
<span>选择数据库:</span>
<span>
<select id="db" class="easyui-combobox" data-options="editable:false" style="width: 90px;"panelHeight="110" >
<option value="1">pos</option>
<option value="2">crm.scjs</option>
<option value="3">jsy</option>
</select>
</span>
<span>表数据排序:</span>
<span>
<select id="sortOrder" class="easyui-combobox" data-options="editable:false" style="width: 90px;"panelHeight="110" >
<option value="asc">升序</option>
<option value="desc">降序</option>
</select>
</span>
</div>
<div>
显示图表库:
<select id="highchartsBtn" data-options="editable:false,valueField: 'id',  textField: 'text',onSelect:function(rec){ if(rec.id==1){$('#unit').attr('readonly', false);}else{$('#unit').attr('readonly', true);$('#unit').val('');}  }"class="easyui-combobox" style="width: 90px;"panelHeight="110" >
<option value="0">否</option>
<option value="1">是</option>
</select>
图表库单位:
<input id="unit" class='easyui-validatebox' style='width: 85px' data-options='required:false'  readonly="true"/>
</div>

<div id="tt" class="easyui-tabs">  
<div title="表头信息"  style="height: 120px;"> 
<form id="tForm" method="post" class="form">
<table id="tTable" border="1" style="table-layout:fixed;BORDER-COLLAPSE: collapse;border: solid 1px #AAAAAA;text-align:center;" width="100%">
<tr>
<td  width="18%" height=20>中文字段</td>
<td  width="18%" height=20>英文字段</td>
<td  width="20%" height=20>highcharts</td>
<td  width="25%" height=20>跳转url</td>
<td  width="19%" height=20><a href="javascript:void(0);" onclick="addTname();">添加</a></td>
</tr> 
</table>
</form>
</div>
<div title="自定义预警"  style="height: 120px;"> 
<form id="selfForm" method="post" class="form">
<table id="selfTable" border="1" style="table-layout:fixed;BORDER-COLLAPSE: collapse;border: solid 1px #AAAAAA;text-align:center;" width="100%">
<tr>
<td  width="20%" height=20>英文字段</td>
<td  width="20%" height=20>下限</td>
<td  width="20%" height=20>上限</td>
<td  width="25%" height=20>颜色</td>
<td  width="15%" height=20><a href="javascript:void(0);" onclick="addSelf();">添加</a></td>
</tr> 
</table>
</form>
</div>
<div title="平均值预警"  style="height: 120px;"> 
<form id="avgForm" method="post" class="form">
<table id="avgTable" border="1" style="table-layout:fixed;BORDER-COLLAPSE: collapse;border: solid 1px #AAAAAA;text-align:center;" width="100%">
<tr>
<td  width="20%" height=20>英文字段</td>
<td  width="20%" height=20>排除字段</td>
<td  width="20%" height=20>排除值</td>
<td  width="25%" height=20>颜色</td>
<td  width="15%" height=20><a href="javascript:void(0);" onclick="addAvg();">添加</a></td>
</tr> 
</table>
</form>
</div>
<div title="sql值预警"  style="height: 120px;"> 
<form id="sqlValueForm" method="post" class="form">
<table id="sqlValueTable" border="1" style="table-layout:fixed;BORDER-COLLAPSE: collapse;border: solid 1px #AAAAAA;text-align:center;" width="100%">
<tr>
<td  width="20%" height=20>英文字段</td>
<td  width="20%" height=20>下限sql</td>
<td  width="20%" height=20>上限sql</td>
<td  width="25%" height=20>颜色</td>
<td  width="15%" height=20><a href="javascript:void(0);" onclick="addSqlValue();">添加</a></td>
</tr> 
</table>
</form>
</div>
</div>

</div>	
<div style="width:69%;height:100%;float:right;">	
<form id="form" method="post" class="form" >
<textarea id="tName"  name="tNameStr" style="display: none">
</textarea>
<textarea id="cName" name="cNameStr" style="display: none">
</textarea>

<input name="db" type="hidden"/>
<input name="sortOrder" type="hidden"/>
<input name="unit" type="hidden"/>
<input name="highchartsBtn" type="hidden"/>
<input name="id" id="id" type="hidden"  value="<%=id%>" />
<input name="pageName" id="pageName" type="hidden"   />
<textarea id="sqlStr"name="sqlStr" style=" display: none">
</textarea>
<textarea id="tableIndex"name="tableIndex" style="display: none">
</textarea>

<div id="condition"></div>
<div id="warn"></div>
</form>

<div style="height:30%;width:100%;" class="easyui-layout" data-options="fit:false,border:false">
<div data-options="region:'center',fit:true,border:false,title:'SQL语句'">
<textarea id="sql"  style="width:80%; height:95px; ">
</textarea>

<a href="javascript:void(0);" class="easyui-linkbutton"
data-options="plain:true,iconCls:'ext-icon-resultset_next'" onclick="preview()">预览</a>

</div>
</div>




<div style="height:32%;width:100%;float:right;" class="easyui-layout" data-options="fit:false,border:false">
<div data-options="region:'center',fit:true,border:false,title:'条件'">
<table id="conditionTable" border="1" style="table-layout:fixed;BORDER-COLLAPSE: collapse;border: solid 1px #AAAAAA;text-align:center;" width="700px">
<tr>
<td width=30 height=25>序号</td>
<td width=80 height=25>条件英文名称</td>
<td width=80 height=25>条件中文名称</td>
<td width=100 height=25>条件类型</td>
<td width=150 height=25>下拉列表SQL</td>
<td width=80 height=25>顺序</td>
<td width=100 height=25>默认值</td>
<td width=80>添加/删除</td>
</tr> 
<tr>
<td width=30 height=25>1</td>
<td width=100 height=25><div onclick="changeToInput(this)" style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;width:100%;height:100%"></div></td>
<td width=100 height=25><div onclick="changeToInput(this)" style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;width:100%;height:100%"></div></td>
<td width=100 height=25 ><div  style="width:100%;height:100%">
<select data-options="onSelect:function(rec){ clearDefaultValue(this);   }" class="easyui-combobox" style="width: 100%;">
<option value="1">日期</option>
<option value="2">SQL下拉框</option>
<option value="3">隐藏session值</option>
<option value="4">文本框</option>
</select>
</div>
</td>
<td width=150 height=25 ><div onclick="changeToInput(this)" style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;width:100%;height:100%"></div></td>
<td width=100 height=25 ><input class="easyui-numberspinner" data-options="required:true,min:0,max:100000,editable:false" style="width: 80px;" value="1" /></td>
<td width=100 height=25><input class='easyui-datebox' data-options='editable:true'  style='width: 95px;' /></input></td>
<td><a href="javascript:void(0);" onclick="addTr()">添加</a>    <a  href="javascript:void(0);" onclick="delTr(this)">删除</a></td>
</tr>
</table>
</div>	
</div>



<div style="height:40%;width:100%;border:1px solid #AAAAAA" class="easyui-layout" data-options="fit:false,border:false">

<div id="preDiv" data-options="region:'center',fit:true,border:false,title:'报表预览'">
<table id="previewGrid" data-options="fit:true,border:false">
<thead id="pre">  

			



 </thead>
</table>
</div>

</div>
</div>

</div>
</body>