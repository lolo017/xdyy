<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="aisino.reportform.util.base.SecurityUtil"%>
<%
	String contextPath = request.getContextPath();
	SecurityUtil securityUtil = new SecurityUtil(session);
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
var db;
var targetObj;
var tStr;
var cStr;
var widthArray=new Array;
var rowArray=new Array;
var rowspanArray=new Array;
var colspanArray=new Array;
var unit;
var highchartsBtn;
var sortOrder;
var sortPara="";
var tableIndex;
function preview(){
	//验证表头
	if(!$('#tForm').form('validate')){
		return false;
	}
	unit=$("#unit").val();
	highchartsBtn=$("#highchartsBtn").combobox("getValue");
	sortOrder=$("#sortOrder").combobox("getValue");
	db=$("#db").combobox('getValue');
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
	
// 	parent.$.messager.progress({
// 		title : '提示',
// 		text : '数据处理中，请稍后....'
// 	});
	
// 	$.post(sy.contextPath +  '/base/report-form!findGridField.sy',{sqlStr:sqlStr,paramStr:paramStr,db:db} , function(result) {
// 		if (result.success) {
// 			var cArray= result.obj;
// 			var cStr="";
//			判断输入表头和SQL返回表头数是否相等
// 			if(tArray.length!=cArray.length){
// 				parent.$.messager.progress('close');
// 				$.messager.alert('error','表头字段数与SQL返回字段数不相等!');
// 				return false;
// 			}
//			根据rowTotal生成tr
// 			for(var t=0;t<rowTotal;t++){
// 				$("#previewGrid").append("<tr id='preTr"+t+"'></tr>");
// 			}
// 			for(var i=0;i<tArray.length;i++){ 
// 				if(cArray[i]=="QUERY_PARA"){
// 					$("#preTr"+rowArray[i]).append("<th rowspan='"+rowspanArray[i]+"' colspan='"+colspanArray[i]+"'   sortable='true' field='"+cArray[i]+"' align='center' hidden='true'>"+"查询参数"+"</th> ");
// 				}else if(cArray[i]=="SORT_PARA"){
// 					$("#preTr"+rowArray[i]).append("<th rowspan='"+rowspanArray[i]+"' colspan='"+colspanArray[i]+"'   sortable='true' field='"+cArray[i]+"' align='center' hidden='true'>"+"排序字段"+"</th> ");
// 					sortPara=cArray[i];
// 				}
// 				else{
// 				$("#preTr"+rowArray[i]).append("<th rowspan='"+rowspanArray[i]+"' colspan='"+colspanArray[i]+"' sortable='true' field='"+cArray[i]+"' align='center' width='"+widthArray[i]+"'>"+tArray[i]+"</th> ");
// 				}
// 				if(i<tArray.length-1){
// 					cStr=cStr+cArray[i]+"+";
// 				}else{
// 					cStr=cStr+cArray[i];
// 				}
			
// 			}
// 			$("#cName").val(cStr);
// 			parent.$.messager.progress('close');
// 			preGrid(sqlStr,paramStr);
// 		} 
// 	}, 'json');
	
	
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
	preGrid(sqlStr,paramStr);
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
				pageSize : 50,
				pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
						400, 500 ],
				onBeforeLoad:function(){
				}
				
			});
}

function save(){
	var arrayEn = new Array();
	var arrayCn = new Array();
	var arrayType = new Array();
	var arraySql=new Array();
	var arraySeq=new Array();
	var arrayUrl=new Array();
	var arrayHc=new Array();
	var arrayDefault=new Array();
	var arraySelf=new Array();
	var arrayAvg=new Array();
	var arraySqlWarn=new Array();
	//跳转路径
	for(var i=1;i<$("#tTable tr").length;i++){
		arrayUrl[i-1]=$("#tTable tr:eq("+i+") td:eq(3) input").val();
	}
	//该pagecode是否显示在highcharts
	for(var i=1;i<$("#tTable tr").length;i++){
		arrayHc[i-1]=$("#tTable tr:eq("+i+") td:eq(2) select").combobox('getValue');
	}
	
	//自定义预警
	for(var i=1;i<$("#selfTable tr").length;i++){
		arraySelf[i-1]=new Array();
		for(var j=0;j<4;j++){
			if(j==3){
				arraySelf[i-1][j]=$("#selfTable tr:eq("+i+") td:eq("+j+") select").combobox('getValue');
			}else{
			arraySelf[i-1][j]=$("#selfTable tr:eq("+i+") td:eq("+j+") input").val();
			}
		}
	}
	//平均值预警
	for(var i=1;i<$("#avgTable tr").length;i++){
		arrayAvg[i-1]=new Array();
		for(var j=0;j<4;j++){
			if(j==3){
				arrayAvg[i-1][j]=$("#avgTable tr:eq("+i+") td:eq("+j+") select").combobox('getValue');
			}else{
				arrayAvg[i-1][j]=$("#avgTable tr:eq("+i+") td:eq("+j+") input").val();
			}
		}
	}
	//SQL预警
	for(var i=1;i<$("#sqlValueTable tr").length;i++){
		arraySqlWarn[i-1]=new Array();
		for(var j=0;j<4;j++){
			if(j==3){
				arraySqlWarn[i-1][j]=$("#sqlValueTable tr:eq("+i+") td:eq("+j+") select").combobox('getValue');
			}else{
				arraySqlWarn[i-1][j]=formatSql($("#sqlValueTable tr:eq("+i+") td:eq("+j+") input").val());
			}
		}
	}
	
	var trLength=$("#conditionTable tr").length;
	for(var i=1;i<trLength;i++){
		if($("#conditionTable tr:eq("+i+") td:eq(1) div").html()!=""&&$("#conditionTable tr:eq("+i+") td:eq(2) div").html()!=""){
			arrayEn[i-1]=$("#conditionTable tr:eq("+i+") td:eq(1) div").html();
			arrayCn[i-1]=$("#conditionTable tr:eq("+i+") td:eq(2) div").html();
			arrayType[i-1]=$("#conditionTable tr:eq("+i+") td:eq(3) div:eq(0) select").combobox('getValue');
			arraySql[i-1]=formatSql($("#conditionTable tr:eq("+i+") td:eq(4) div").text());
			arraySeq[i-1]=$("#conditionTable tr:eq("+i+") td:eq(5) input").val();
			//如果是日期，取值
			if(arrayType[i-1]==1){
			arrayDefault[i-1]=$("#conditionTable tr:eq("+i+") td:eq(6) input").datebox("getValue");
			//如果是文本框,取值
			}else if(arrayType[i-1]==4){
				arrayDefault[i-1]=$("#conditionTable tr:eq("+i+") td:eq(6) input").val();
				
			}else{
			arrayDefault[i-1]="";
			}
		}
	}

	
	var sqlStr=formatSql($("#sql").val());
	
	var dialog = parent.sy.modalDialog({
		title : '保存报表',
		url : sy.contextPath + '/securityJsp/base/SaveReportForm.jsp',
		buttons : [ {
			text : '添加',
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.sendParam(db,unit,sortOrder,highchartsBtn,tStr,cStr,sqlStr,arrayEn,arrayCn,arrayType,arraySql,arraySeq,arrayUrl,arrayHc,arrayDefault,widthArray,arraySelf,arrayAvg,arraySqlWarn,tableIndex);
				dialog.find('iframe').get(0).contentWindow.submitForm(dialog, parent.$, parent.mainMenu);
				
			}
		} ]
		
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
	 targetObj=$("<tr><td  width='20%' height=20><input  class='easyui-validatebox' style='width: 95%' data-options='required:true' /></td><td  width='20%' height=20><input style='width: 95%'  value=''></input></td><td  width='20%' height=20><select  id=\"\" style=\"width:65%;\" panelHeight=\"110\"  class=\"easyui-combobox\" data-options=\"valueField: 'id',  textField: 'text' \" ><option value=\"0\">否</option><option value=\"1\">是</option></select></td><td  width='25%' height=20><input  maxlength='1000' style='width: 65%' />&nbsp;<img  class='iconImg ext-icon-zoom' title='选择' onclick='urlTree(this);'/></td><td style='display:none'><input  value='0' /></td><td style='display:none'><input  value='1' /></td><td style='display:none'><input  value='1' /></td><td style='display:none'><input  value='120' /></td><td  width='20%' height=20><a href='javascript:void(0);' onclick='delTname(this);'>删除</a>&nbsp;<a href='javascript:void(0);' onclick='more(this);'>更多</a></td></tr> ").appendTo("#tTable");
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
	targetObj=$("<tr><td  width='20%' height=20><input  class='easyui-validatebox' style='width: 95%' validtype='english' data-options='required:true' /></td><td  width='20%' height=20><input  maxlength='1000' style='width: 95%' /></td><td  width='20%' height=20><input  maxlength='1000' style='width: 95%'  value=''></input></td><td><select  id=\"\" style=\"width:80%;\" panelHeight=\"110\"  class=\"easyui-combobox\" data-options=\"valueField: 'id',  textField: 'text' \" ><option value=\"#66ff66\">绿</option><option value=\"#77FFEE\">蓝</option><option value=\"#FF3333\">红</option><option value=\"##FFFF00\">黄</option></select></td><td  width='15%' height=20><a href='javascript:void(0);' onclick='delTname(this);'>删除</a></td></tr> ").appendTo("#avgTable");
	 $.parser.parse(targetObj);
}
function addSqlValue(){
	
	targetObj=$("<tr><td  width='20%' height=20><input  class='easyui-validatebox' style='width: 95%' validtype='english' data-options='required:true' /></td><td  width='20%' height=20><input  maxlength='1000' style='width: 95%' /></td><td  width='20%' height=20><input  maxlength='1000' style='width: 95%'  value=''></input></td><td><select  id=\"\" style=\"width:80%;\" panelHeight=\"110\"  class=\"easyui-combobox\" data-options=\"valueField: 'id',  textField: 'text' \" ><option value=\"#66ff66\">绿</option><option value=\"#77FFEE\">蓝</option><option value=\"#FF3333\">红</option><option value=\"##FFFF00\">黄</option></select></td><td  width='15%' height=20><a href='javascript:void(0);' onclick='delTname(this);'>删除</a></td></tr> ").appendTo("#sqlValueTable");
	$.parser.parse(targetObj);
}
//根据下拉框选中项，判断是否显示默认值
function clearDefaultValue(now){
// 	if($("#conditionTable tr:eq("+$(now).parent().parent().parent().index()+") td:eq(3) div:eq(0) select").combobox("getValue")!=1){
// 	$("#conditionTable tr:eq("+$(now).parent().parent().parent().index()+") td:eq(6)").html("");
// 	}else if(){
		
// 	}else{
// 		$("#conditionTable tr:eq("+$(now).parent().parent().parent().index()+") td:eq(6)").html("<input class='easyui-datebox' data-options='editable:true'  style='width: 95px;' />");
// 		$.parser.parse("#conditionTable tr:eq("+$(now).parent().parent().parent().index()+") td:eq(6)");
// 	}
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
//当不选择自定义SQL下拉框时，清空自定义SQL
function clearSelectSql(now){
	$("#conditionTable tr:eq("+$(now).parent().parent().parent().index()+") td:eq(4) div").html("");
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
<div style="width:37%;height:100%;float:left;font-size:10px;">
<div >
<span>选择数据库:</span>
<span>
<select id="db" class="easyui-combobox" data-options="editable:false"  style="width: 90px;"panelHeight="110" >
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

<div id="tt" class="easyui-tabs" >  
<div title="表头信息"  style="height: 120px;"> 
<form id="tForm" method="post" class="form">
<table id="tTable" border="1" style="table-layout:fixed;BORDER-COLLAPSE: collapse;border: solid 1px #AAAAAA;text-align:center;" width="100%">
<tr>
<td  width="19%" height=20>中文字段</td>
<td  width="19%" height=20>英文字段</td>
<td  width="20%" height=20>highcharts</td>
<td  width="25%" height=20>跳转url</td>
<td  width="17%" height=20><a href="javascript:void(0);" onclick="addTname();">添加</a></td>
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
<div style="width:62%;height:100%;float:right;">	
<textarea id="cName" style="display: none">
</textarea>


<div style="height:30%;width:100%;" class="easyui-layout" data-options="fit:false,border:false">

<div data-options="region:'center',fit:true,border:false,title:'SQL语句'">

<textarea id="sql" style="width:80%; height:80%; ">
</textarea>

<a href="javascript:void(0);" class="easyui-linkbutton"
data-options="plain:true,iconCls:'ext-icon-resultset_next'" onclick="preview()">预览</a>
<a href="javascript:void(0);" class="easyui-linkbutton"
data-options="plain:true,iconCls:'ext-icon-disk'" onclick="save()">保存</a>
</div>


</div>
<div style="height:30%;width:100%;float:left;" class="easyui-layout" data-options="fit:false,border:false">


				
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
<td >添加/删除</td>
</tr> 
<tr>
<td width=30 height=25>1</td>
<td width=100 height=25><div onclick="changeToInput(this)" style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;width:100%;height:100%"></div></td>
<td width=100 height=25><div onclick="changeToInput(this)" style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;width:100%;height:100%"></div></td>
<td width=100 height=25 ><div  style="width:100%;height:100%">
<select data-options="valueField: 'id',  textField: 'text',onSelect:function(rec){ clearDefaultValue(this);if(rec.id!=2){clearSelectSql(this);}  }" class="easyui-combobox" style="width: 100%;">
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