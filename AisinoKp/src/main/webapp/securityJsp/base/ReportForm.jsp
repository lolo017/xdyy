<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="aisino.reportform.util.base.SecurityUtil"%>
<%@ page import="aisino.reportform.model.base.SessionInfo"%>
<%
	String contextPath = request.getContextPath();
	SecurityUtil securityUtil = new SecurityUtil(session);
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute("sessionInfo");
	String empId=sessionInfo.getUser().getEmpId();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
var grid;
var targetObj;
var pageId='${param.pageId}';
//var dateArray=new  Array();
var db;
var urlMap= new Map();
var urlParas;
var comboboxNum=0;//计算下拉框个数，用于判断是否加载完毕
var comboboxCount=0;//下拉框总数
var sort_para="";//用于判断排序
var sort_order;//asc或desc
var categories=new Array();//图表X轴中文名称
var chartfield=new Array();//图表X轴英文名称（字段名称）
var chartName;//图表名称  // highchartsBtn  1    unit 单位   isHighcharts
var highchartsBtn;//是否显示highcharts图标  1 显示    0 不显示
var unit;//highcharts图标单位 如：千克
var buildJson;
var jq = top.jQuery; 
var session_empid="<%=empId%>";

$(function() {
	//可能存在不同资源对应同一个page，所以excel文件名用资源名称
	$.post(sy.contextPath +  '/base/report-form!build.sy',{id:pageId} , function(result) {
		   db=result.page.db;
		   sort_order=result.page.sortOrder;
		   chartName=result.page.pageName;
		   highchartsBtn=result.page.highchartsBtn;
		   unit=result.page.unit;
		   urlParas=getUrlPara(location.search);
		  // =result.pageCodes[0].cnEnglish;
		   buildJson=result;
		   //判断是否显示highcharts图表按钮
			if(highchartsBtn!=1){
				$('#chartBtn').hide();
			}
		   
		   //计算下拉框总数
		   for(var i = 0 ; i < result.sqlconditions.length ; i ++)
			{
			if(result.sqlconditions[i].conditiontype == 2) {
				comboboxCount ++;
				}
			}
		   //根据maxrow行数创建tr
		   for(var r=0;r<=result.maxrow;r++){ 
			   $("#pre").append("<tr id='preTr"+r+"'></tr>");
		   }
		   for(var o1=0;o1<result.pageCodes.length;o1++){
			//for(var o1 in result.pageCodes){ 
				//需要传递的查询参数隐藏
				if(result.pageCodes[o1].cnEnglish=="QUERY_PARA"){
					$("#preTr"+result.pageCodes[o1].rowNum).append("<th colspan='"+result.pageCodes[o1].colspan+"' rowspan='"+result.pageCodes[o1].rowspan+"' sortable='true' align='center' hidden='true' field='"+undefinedToString(result.pageCodes[o1].cnEnglish)+"'>查询参数</th>");
				}else if(result.pageCodes[o1].cnEnglish=="SORT_PARA"){
					$("#preTr"+result.pageCodes[o1].rowNum).append("<th colspan='"+result.pageCodes[o1].colspan+"' rowspan='"+result.pageCodes[o1].rowspan+"' sortable='true' align='center' hidden='true' field='"+undefinedToString(result.pageCodes[o1].cnEnglish)+"'>排序字段</th>");
					sort_para=result.pageCodes[o1].cnEnglish;
				}else if(result.pageCodes[o1].url){
					$("#preTr"+result.pageCodes[o1].rowNum).append("<th colspan='"+result.pageCodes[o1].colspan+"' rowspan='"+result.pageCodes[o1].rowspan+"' width='"+result.pageCodes[o1].width+"' sortable='true' align='center' field='"+undefinedToString(result.pageCodes[o1].cnEnglish)+"' data-options=\"styler: function(value,rowdata,index){return 'color:blue;text-decoration: underline';}\">"+result.pageCodes[o1].cnName+"</th>");
				//如果该字段对应一个跳转url，则放入map，供双击跳转时使用
					urlMap.put(result.pageCodes[o1].cnEnglish,result.pageCodes[o1].url);
				
					//存入highchart目录
					categories.push(result.pageCodes[o1].cnName);
					chartfield.push(result.pageCodes[o1].cnEnglish);
				}else{
					$("#preTr"+result.pageCodes[o1].rowNum).append("<th  colspan='"+result.pageCodes[o1].colspan+"' rowspan='"+result.pageCodes[o1].rowspan+"' width='"+result.pageCodes[o1].width+"' sortable='true' align='center' field='"+undefinedToString(result.pageCodes[o1].cnEnglish)+"' >"+result.pageCodes[o1].cnName+"</th>");
					
					//存入highchart目录
					if("1"==result.pageCodes[o1].isHighcharts){
						categories.push(result.pageCodes[o1].cnName);
						chartfield.push(result.pageCodes[o1].cnEnglish);
					}
					
				}
			}
			var cNum=0;
			for(var o2=0;o2<result.sqlconditions.length;o2++){
			//for(var o2 in result.sqlconditions){ 
				//1是日期
				if(result.sqlconditions[o2].conditiontype==1){
				targetObj = $("<td>"+result.sqlconditions[o2].cncondition+"</td><td><input id='"+result.sqlconditions[o2].encondition+"' name='"+result.sqlconditions[o2].encondition+"' class='easyui-datebox' data-options='editable:false'  style='width: 120px;' /></td>").appendTo("#conTr");
				$.parser.parse(targetObj);
				//如果有上个页面传过来的日期，就取这个日期
				if(urlParas[result.sqlconditions[o2].encondition]){
					$("#"+result.sqlconditions[o2].encondition).datebox("setValue",urlParas[result.sqlconditions[o2].encondition] );
				}//如果有默认值，就取默认值
				else if(result.sqlconditions[o2].defaultValue){
				$("#"+result.sqlconditions[o2].encondition).datebox("setValue", result.sqlconditions[o2].defaultValue);	
				}else{
				//上面都没有，就取当前日期
				$("#"+result.sqlconditions[o2].encondition).datebox("setValue", getCurrentDate());
				}
				}
				//2是自定义SQL
				if(result.sqlconditions[o2].conditiontype==2){
			   //默认选中第一个选项
			   //console.log("<td>"+result.sqlconditions[o2].cncondition+"</td><td><input id=\""+result.sqlconditions[o2].encondition+"\"style=\"width:130px\"panelHeight:\"100px\" name=\""+result.sqlconditions[o2].encondition+"\" class=\"easyui-combobox\"  data-options=\"valueField:'VALUE',textField:'TEXT',url:'<%=contextPath%>/base/report-form!doNotNeedSecurity_sqlCombobox.sy?id="+result.sqlconditions[o2].id+"&db="+db+"', onLoadSuccess: function (data) {var para=urlParas['"+result.sqlconditions[o2].encondition+"'];if (data) {if(data.length>0){$('#'+'"+result.sqlconditions[o2].encondition+"').combobox('setValue',data[0].VALUE);for(var i=0;i<data.length;i++){if(para!=undefined&&data[i].VALUE!=undefined&&para==data[i].VALUE){$('#'+'"+result.sqlconditions[o2].encondition+"').combobox('setValue',para);}}}comboboxNum=comboboxNum+1;loadFinish('"+sort_para+"');}}\" ></input></td>");
				targetObj = $("<td>"+result.sqlconditions[o2].cncondition+"</td><td><input id=\""+result.sqlconditions[o2].encondition+"\" style=\"width:130px\" panelHeight:\"100px\" name=\""+result.sqlconditions[o2].encondition+"\" class=\"easyui-combobox\"  data-options=\"valueField:'VALUE',textField:'TEXT',url:'<%=contextPath%>/base/report-form!doNotNeedSecurity_sqlCombobox.sy?id="+result.sqlconditions[o2].id+"&db="+db+"', onLoadSuccess: function (data) {var para=urlParas['"+result.sqlconditions[o2].encondition+"'];if (data) {if(data.length>0){$('#'+'"+result.sqlconditions[o2].encondition+"').combobox('setValue',data[0].VALUE);for(var i=0;i<data.length;i++){if(para==data[i].VALUE){$('#'+'"+result.sqlconditions[o2].encondition+"').combobox('setValue',para);}}}comboboxNum=comboboxNum+1;loadFinish('"+sort_para+"');}}\" ></input></td>").appendTo("#conTr");
				$.parser.parse(targetObj);
				}
				//3是隐藏session值,并记录隐藏参数的个数，判断是否添加查询按钮
				if(result.sqlconditions[o2].conditiontype==3){
					targetObj = $("<td style=\"display:none\"><input id=\""+result.sqlconditions[o2].encondition+"\"  name=\""+result.sqlconditions[o2].encondition+"\"></input></td>").appendTo("#conTr");
					//上个页面如果传有同名的参数过来，则取这个值
					if(urlParas[result.sqlconditions[o2].encondition]){
						$("#"+result.sqlconditions[o2].encondition).val(decodeURIComponent(urlParas[result.sqlconditions[o2].encondition]));
					}
					cNum=cNum+1;
				}
				//4是文本框
				if(result.sqlconditions[o2].conditiontype==4){
					if(result.sqlconditions[o2].defaultValue){
						targetObj = $("<td>"+result.sqlconditions[o2].cncondition+"</td><td><input name=\""+result.sqlconditions[o2].encondition+"\"id=\""+result.sqlconditions[o2].encondition+"\"style=\"width:100px\" name=\""+result.sqlconditions[o2].encondition+"\" value=\""+result.sqlconditions[o2].defaultValue+"\" ></input></td>").appendTo("#conTr");
					}//有上个页面传过来的值，就取这个值
					else if(urlParas[result.sqlconditions[o2].encondition]){
						targetObj = $("<td>"+result.sqlconditions[o2].cncondition+"</td><td><input name=\""+result.sqlconditions[o2].encondition+"\"id=\""+result.sqlconditions[o2].encondition+"\"style=\"width:100px\" name=\""+result.sqlconditions[o2].encondition+"\" value=\""+decodeURIComponent(urlParas[result.sqlconditions[o2].encondition])+"\" ></input></td>").appendTo("#conTr");
						}
					else{
						targetObj = $("<td>"+result.sqlconditions[o2].cncondition+"</td><td><input name=\""+result.sqlconditions[o2].encondition+"\"id=\""+result.sqlconditions[o2].encondition+"\"style=\"width:100px\" name=\""+result.sqlconditions[o2].encondition+"\"  ></input></td>").appendTo("#conTr");
					}
	
				}
			}
			//存在条件时，且条件类型不全为3，添加查询按钮
			if(result.sqlconditions.length>cNum){
				
				targetObj = $("<td><a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'ext-icon-search'\" onclick=\"conQuery();\">查询</a></td>").appendTo("#conTr");
			     $.parser.parse(targetObj);
			}
			//添加查询ID
			$("#conTr").append("<td style='display:none'><input name='id' value='"+pageId+"'/></td>");
			//添加db
			$("#conTr").append("<td style='display:none'><input name='db' value='"+db+"'/></td>");
			//如果没有下拉框条件，直接grid
			if(comboboxCount==0){
					rfGrid(sort_para);
			}
		
	}, 'json');
	
	
});


//发送邮件
var sendEmail = function() {
	
	var dialog = parent.sy.modalDialog({
		title : '发送邮件',
		url : sy.contextPath + '/securityJsp/base/SyUsersTree.jsp',
		width:900,
		height:400,
		buttons : [ {
			text : '发送邮件',
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
			}
		} ]
	});
};

//生成excel
var sendrow=function(){
	
	parent.$.messager.progress({
		title : '提示',
		text : '数据处理中，请稍后....'
	});
	$.post(sy.contextPath + '/base/report-form!exportExcel.sy?excelName='+encodeURI(encodeURI(jq('#mainTabs').tabs('getSelected').panel('options').title)), sy.serializeObject($('#searchForm')), function(result) {
		if($("#wod").html()!=""){
			$("#wod").html("");
		}
		$("<a  href="+sy.contextPath + '/base/report-form!download.sy?filenames='+encodeURI(encodeURI(result.obj))+">下载<a/>").appendTo("#wod"); 
		parent.$.messager.progress('close');
		parent.$.messager.alert('提示', result.msg, 'info');
			
		
	}, 'json');
	
};


function rfGrid(sort_para){
	//第一次查询，将参数添加到url
	var newUrl=sy.contextPath +  '/base/report-form!grid.sy'+'?'+$("#searchForm").serialize();
	grid = $('#grid').datagrid({
	title : '',
	url : newUrl,
	striped : false,
	rownumbers : true,
	pagination : true,
	singleSelect : true,
	idField : sort_para,
	sortName : sort_para,
	sortOrder : sort_order,
	//sortable:false,
	checkOnSelect : false,
	selectOnCheck : false,
	nowrap :false,
	//fitColumns : true,
	remoteSort: false,
	pageSize : 50,
	toolbar : '#toolbar',
	pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
			400, 500 ],
	onLoadSuccess:function(data) {
		
		//由于排序图标会影响预警数值显示，所以将排序图标移除
		$(".datagrid-sort-icon").remove();
		//清除下载按钮
    	$("#wod").html("");
    	var panel = $(this).datagrid('getPanel');   
	    var tr = panel.find('div.datagrid-body tr');
	    var warnInfo;
	  //防止预警信息重复显示
	  for(var o=0;o<buildJson.selfWarn.length;o++){
	   // for(var o in buildJson.selfWarn){
	    	$(".datagrid-header-row").children('td[field="'+buildJson.selfWarn[o].CNENGLISH+'"]').find("div").eq(0).children("span").nextAll().remove();
	    
	    }
	    //自定义预警
	    for(var o=0;o<buildJson.selfWarn.length;o++){
	   // for(var o in buildJson.selfWarn){
		    warnInfo="val";
		   if(buildJson.selfWarn[o].GT!=undefined&&buildJson.selfWarn[o].LT!=undefined){
			   warnInfo=buildJson.selfWarn[o].GT+"≤"+warnInfo+"≤"+buildJson.selfWarn[o].LT;
		   }
		 	if(buildJson.selfWarn[o].GT!=undefined&&buildJson.selfWarn[o].LT==undefined){
		 				warnInfo=warnInfo+"≥"+buildJson.selfWarn[o].GT;
		 	}
		 	if(buildJson.selfWarn[o].LT!=undefined&&buildJson.selfWarn[o].GT==undefined){
		 				warnInfo=warnInfo+"≤"+buildJson.selfWarn[o].LT;
		 	}
		 	//处理预警区间信息
		 	warnInfo="<div>["+warnInfo+"<span style='background:"+buildJson.selfWarn[o].COLOR+"'>&nbsp</span>]</div>";
		 	//显示预警区间
			$(".datagrid-header-row").children('td[field="'+buildJson.selfWarn[o].CNENGLISH+'"]').find("div").eq(0).append(warnInfo);
		    
	    	//显示预警颜色
			tr.each(function(){   
            var td = $(this).children('td[field="'+buildJson.selfWarn[o].CNENGLISH+'"]');   
            //大于、小于值同时存在
            if(buildJson.selfWarn[o].GT!=undefined&&buildJson.selfWarn[o].LT!=undefined&&td.children("div").text()!=""&&td.children("div").text()!=undefined&&Number(td.children("div").text())<=Number(buildJson.selfWarn[o].LT)&&Number(td.children("div").text())>=Number(buildJson.selfWarn[o].GT)){
            	td.children("div").css({"background-color": buildJson.selfWarn[o].COLOR});
            }
            //只有大于
            if(buildJson.selfWarn[o].GT!=undefined&&buildJson.selfWarn[o].LT==undefined&&td.children("div").text()!=""&&td.children("div").text()!=undefined&&Number(td.children("div").text())>=Number(buildJson.selfWarn[o].GT)){
            	td.children("div").css({"background-color": buildJson.selfWarn[o].COLOR});
            }
            //只有小于
           if(buildJson.selfWarn[o].GT==undefined&&buildJson.selfWarn[o].LT!=undefined&&td.children("div").text()!=""&&td.children("div").text()!=undefined&&Number(td.children("div").text())<=Number(buildJson.selfWarn[o].LT)){
        	   td.children("div").css({"background-color": buildJson.selfWarn[o].COLOR});
            }
			 });   
	    }
	  //防止预警信息重复显示
	  for(var o=0;o<buildJson.avgWarn.length;o++){
	   // for(var o in buildJson.avgWarn){
				$(".datagrid-header-row").children('td[field="'+buildJson.avgWarn[o].CNENGLISH+'"]').find("div").eq(0).children("div:last-child").remove();

	    }
	    //平均值预警
	     for(var o=0;o<buildJson.avgWarn.length;o++){
           // for(var o in buildJson.avgWarn){
	    	warnInfo="平均值:";
			var avgMap=grid.datagrid('getData').avgMap;
			//显示预警值
		    $(".datagrid-header-row").children('td[field="'+buildJson.avgWarn[o].CNENGLISH+'"]').find("div").eq(0).attr("title",avgMap[buildJson.avgWarn[o].CNENGLISH+"INFO"]);
		    $(".datagrid-header-row").children('td[field="'+buildJson.avgWarn[o].CNENGLISH+'"]').find("div").eq(0).append("<div>["+avgMap[buildJson.avgWarn[o].CNENGLISH]+"<span style='background:"+buildJson.avgWarn[o].COLOR+"'>&nbsp</span>]</div>");
			//大于等于平均值，就预警变色
			tr.each(function(){   
	            var td = $(this).children('td[field="'+buildJson.avgWarn[o].CNENGLISH+'"]');   
	            if(Number(td.children("div").text())<=Number(avgMap[buildJson.avgWarn[o].CNENGLISH])){
	            	td.children("div").css({"background-color": buildJson.avgWarn[o].COLOR});
	            }
	        });   

            }
	    //防止预警信息重复显示
	    for(var o=0;o<buildJson.sqlWarn.length;o++){
            //for(var o in buildJson.sqlWarn){
    	    	$(".datagrid-header-row").children('td[field="'+buildJson.sqlWarn[o].CNENGLISH+'"]').find("div").eq(0).children("span").nextAll().remove();
    	    
    	    }
	    //SQL预警
	    for(var o=0;o<buildJson.sqlWarn.length;o++){
           // for(var o in buildJson.sqlWarn){
            	warnInfo="val";
       		   if(buildJson.sqlWarn[o].gtValue!=undefined&&buildJson.sqlWarn[o].ltValue!=undefined){
       			   warnInfo=buildJson.sqlWarn[o].gtValue+"≤"+warnInfo+"≤"+buildJson.sqlWarn[o].ltValue;
       		   }
       		 	if(buildJson.sqlWarn[o].gtValue!=undefined&&buildJson.sqlWarn[o].ltValue==undefined){
       		 				warnInfo=warnInfo+"≥"+buildJson.sqlWarn[o].gtValue;
       		 	}
       		 	if(buildJson.sqlWarn[o].ltValue!=undefined&&buildJson.sqlWarn[o].gtValue==undefined){
       		 				warnInfo=warnInfo+"≤"+buildJson.sqlWarn[o].ltValue;
       		 	}
       		 	//处理预警区间信息
       		 	warnInfo="<div>["+warnInfo+"<span style='background:"+buildJson.sqlWarn[o].COLOR+"'>&nbsp</span>]</div>";
       		 	//显示预警区间
       			$(".datagrid-header-row").children('td[field="'+buildJson.sqlWarn[o].CNENGLISH+'"]').find("div").eq(0).append(warnInfo);
       		//显示预警颜色
    			tr.each(function(){   
                var td = $(this).children('td[field="'+buildJson.sqlWarn[o].CNENGLISH+'"]');   
                //大于、小于值同时存在
                if(buildJson.sqlWarn[o].gtValue!=undefined&&buildJson.sqlWarn[o].ltValue!=undefined&&td.children("div").text()!=""&&td.children("div").text()!=undefined&&Number(td.children("div").text().replace("%",""))<=Number(buildJson.sqlWarn[o].ltValue.replace("%",""))&&Number(td.children("div").text().replace("%",""))>=Number(buildJson.sqlWarn[o].gtValue.replace("%",""))){
                	
                	td.children("div").css({"background-color": buildJson.sqlWarn[o].COLOR});
                }
                //只有大于
                if(buildJson.sqlWarn[o].gtValue!=undefined&&buildJson.sqlWarn[o].ltValue==undefined&&td.children("div").text()!=""&&td.children("div").text()!=undefined&&Number(td.children("div").text().replace("%",""))>=Number(buildJson.sqlWarn[o].gtValue.replace("%",""))){
                	td.children("div").css({"background-color": buildJson.sqlWarn[o].COLOR});
                }
                //只有小于
               if(buildJson.sqlWarn[o].gtValue==undefined&&buildJson.sqlWarn[o].ltValue!=undefined&&td.children("div").text()!=""&&td.children("div").text()!=undefined&&Number(td.children("div").text().replace("%",""))<=Number(buildJson.sqlWarn[o].ltValue.replace("%",""))){
            	   td.children("div").css({"background-color": buildJson.sqlWarn[o].COLOR});
                }
    			 });   
            }
 
    },
    onDblClickCell:function(rowIndex, field,value){
    	//url不为空且单元格值不为空时，则跳转
    	//alert(JSON.stringify($('#grid').datagrid('getSelected',rowIndex)));
    	//alert(urlMap.get(field) + '=== ' + value + "----" + $('#grid').datagrid('getSelected',rowIndex).QUERY_PARA);
    	if(urlMap.get(field)&&value!=undefined){
			jumpUrl(urlMap.get(field),$('#grid').datagrid('getSelected',rowIndex).QUERY_PARA);
    	}
    	/**
    	else{
    		var dialog = parent.sy.modalDialog({
    			title : '推送失败单',
    			url : sy.contextPath + '/securityJsp/base/TelPushFail.jsp',
    			width:1000,
    			height:600,
    			buttons : [ {
    				text : '关 闭',
    				handler : function() {
    					dialog.find('iframe').get(0).contentWindow.submitForm(
    							dialog, grid, parent.$);
    				}
    			} ]
    		});
    	}
    	*/
    	
	},
	rowStyler:function(rowIndex,rowData){
		
	}

});
}

function conQuery(url){
	grid.datagrid('options').url=sy.contextPath +  '/base/report-form!grid.sy';
	grid.datagrid('load',sy.serializeObject($('#searchForm')));
 
}
//跳转页面
function jumpUrl(url,queryParas) {
	queryParas=queryParas.toString();
	var paraArray= new Array(); 
	if(queryParas.indexOf(",")!=-1){
		paraArray=queryParas.split(",");
	}
	if(paraArray.length>1){
		for(var i=0;url.indexOf("=&")!=-1;i++){
			url=url.replace("=&","="+paraArray[i]+"&");
		}
		url=url+paraArray[paraArray.length-1];
		url=removeRepeat(url)
	}else{
		url=url+queryParas;
		url=removeRepeat(url);
	}
	if(url.indexOf("dialog")==0){
		url=replaceAllstr(url,"dialog","");
		var dialog = parent.sy.modalDialog({
			title : '窗口',
			url : sy.contextPath + url,
			height:540,
			width:800,
			buttons : [ {
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	}else if(url.indexOf("/")!=0){
		//向外部IP传递session值
		url=replaceAllstr(url,"session_empid",session_empid);
		window.open (url, 'newwindow', 'height=400, width=900, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no');
	}
//     else if(url.indexOf("crm_ip")==0){
		
// 		向外部IP传递session值
// 		url=url.replace("crm_ip",crm_ip);
// 		url=replaceAllstr(url,"session_empid",session_empid);
// 		window.open (url, 'newwindow', 'height=400, width=900, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no');
// 	}
    else{
	var content = '<iframe scrolling="auto" frameborder="0"  src="<%=contextPath%>' + url+'" style="border: 0; width: 100%; height: 99%;"></iframe>';     
	//获取跳转页面名称，图标
	$.post(sy.contextPath +  '/base/report-form!doNotNeedSecurity_getJumpResource.sy',{id:getJumpId(url)} , function(result) {
	   
    if (jq("#mainTabs").tabs('exists', result[0].NAME)){    
        jq("#mainTabs").tabs('select', result[0].NAME);
        var currTab = jq("#mainTabs").tabs('getTab',result[0].NAME);  
        jq("#mainTabs").tabs('update', {tab: currTab, options: {content: content, closable: true}});  
    } else {  
           jq("#mainTabs").tabs('add',{    
                              title:result[0].NAME,
                              iconCls :result[0].ICONCLS,   
                              content:content,    
                              closable:true
                            });    
     }    
	}, 'json');
	}
}

//获取当前页面的跳转参数
// function getFormPara(paraStr) {
// 	     var strs = paraStr.split("&");
// 	     var formPara="";
// 	      for(var i = 0; i < strs.length; i ++) {
//	    	id，db不需要传递,且值为空的参数也不用传递
// 	       	  if(strs[i].split("=")[0]!="id"&&strs[i].split("=")[0]!="db"&&strs[i].split("=")[1]!=undefined&&strs[i].split("=")[1]!=""){
// 	       		  formPara=formPara+"&"+strs[i];
// 	       	  }
// 	      }
// 	   return formPara;
// 	}
//参数去重
function removeRepeat(url){
	var formParas=$("#searchForm").serialize();
	 var strs = formParas.split("&");
      for(var i = 0; i < strs.length; i ++) {
    	//id，db不需要传递,且值为空的参数也不用传递
    	//url,formParas里都有的参数，就取url中的
       	  if(url.indexOf("&"+strs[i].split("=")[0]+"=")==-1&&url.indexOf("?"+strs[i].split("=")[0]+"=")==-1&&strs[i].split("=")[0]!="id"&&strs[i].split("=")[0]!="db"&&strs[i].split("=")[1]!=undefined&&strs[i].split("=")[1]!=""){
       		  url=url+"&"+strs[i];
       	  }
      }
      return url;
}

//获取上个页面传递过来的参数
function getUrlPara(paraStr) {
	   var urlPara = new Object();
	   if (paraStr.indexOf("?") != -1) {
	      var str = paraStr.substr(1);
	      strs = str.split("&");
	      for(var i = 0; i < strs.length; i ++) {
	    	  urlPara[strs[i].split("=")[0]]=(strs[i].split("=")[1]);
	    	  
	      }
	   }
	   return urlPara;
	}
//获取跳转到的页面ID	
function getJumpId(url){
	url=url.substring(url.indexOf("?"));
	var para=getUrlPara(url);
	return para['pageId'];
}
//判断下拉框是否加载完毕
function loadFinish(sort_para){
	if(comboboxNum==comboboxCount){
		//传递一个field作为sortName，用于排序
		rfGrid(sort_para);
	}
	
}
//关闭loading图
function closes(){
	    $("#loading").fadeOut("normal",function(){
	        $(this).remove();
	    });
	   
	}
var pc;
//判断easyui是否加载完毕
$.parser.onComplete = function(){
	    if(pc) {
	    	clearTimeout(pc);
	    	}
	    pc = setTimeout(closes, 1200);
	}

//生成图表
function createChart(type){
	var r=grid.datagrid('getData').rows;
	//console.debug(chartfield);
	if(!r.length){
		$.messager.alert('错误提示','展示的数据不能为空！','error');
		return;
	}
	var highCharData='[';
	for ( var i = 0; i < r.length; i++) {
		if(eval('r[i]'+'.'+chartfield[0])){
			highCharData+='{'+"name:'"+eval('r[i]'+'.'+chartfield[0])+"',data:[";
		}else{
			continue;
		}
		for ( var j = 1; j < chartfield.length; j++) {
			if(eval('r[i]'+'.'+chartfield[j])){
				highCharData+=eval('r[i]'+'.'+chartfield[j])+',';
			}else{
				highCharData+=0+',';
			}
		}
		highCharData=highCharData.substr(0,highCharData.length-1)+']},';
		
	}
	highCharData=highCharData.substr(0,highCharData.length-1)+']';
	var firstCate=categories.shift();
	// highchartsBtn  1    unit 单位   isHighcharts
	var dialog=parent.$('<div>').dialog({ 
		  width: 1000,    
	      height:600, 
		  region:'center',
		  title: chartName,
		  maximizable:true,
		  modal:true,
		  onClose:function(){
			  categories.unshift(firstCate);
		  }
		}); 
	dialog.highcharts({ chart: { type: type}, 
		title: { text: chartName },
		//subtitle: { text: 'Source: WorldClimate.com' }, 
		xAxis: { categories: categories }, 
		yAxis: {title: { text: '单位 ( '+unit+' )' } }, 
		plotOptions: { column: { pointPadding: 0.2, borderWidth: 0, dataLabels: {enabled: true} } }, 
		series:eval(highCharData)
			});
	
}



</script>
</head>
<body>
<div id='loading' style="position:absolute;z-index:1000;top:0px;left:0px;width:100%;height:100%;background:white ;text-align:center;padding-top: 20%;">
<div style="margin:-150px 100px 0px 100px">
<img width="400" src="<%=contextPath %>/style/images/google.gif" />
</div>
</div>
<div style="height:100%;width:100%;" class="easyui-layout" data-options="fit:true,border:false">
<div id="toolbar" >
<div><a href="javascript:void(0);" class="easyui-linkbutton"
data-options="plain:true,iconCls:'ext-icon-email'" onclick="sendEmail()">发送邮件</a>

<a href="javascript:void(0);" class="easyui-linkbutton"
data-options="plain:true,iconCls:'ext-icon-page_white_excel'" onclick="sendrow()">生成excel</a>

<a href="javascript:void(0)" id="chartBtn" class="easyui-splitbutton"   
        data-options="menu:'#mm',iconCls:'ext-icon-chart_bar',hidden:true" >图表展示</a>   
<div id="mm" style="width:100px;">   
    <div data-options="iconCls:'ext-icon-chart_bar'" onclick="javascript:createChart('column')">柱状图</div>   
    <div data-options="iconCls:'ext-icon-chart_curve'" onclick="javascript:createChart('area')">区域图</div>  
    <div data-options="iconCls:'ext-icon-chart_line'" onclick="javascript:createChart('line')">线性图</div>  
    <div data-options="iconCls:'ext-icon-chart_pie'" onclick="javascript:createChart('pie')">饼状图</div>  
</div>  

<a id="wod"></a>
</div>
<form id="searchForm">
<table>
			<tr  id="conTr">
			
			</tr>
</table>
</form>
</div>
<div id="preDiv" data-options="region:'center',fit:true,border:false,title:'报表查询'" style="width:500px;height:250px" >
<table id="grid" data-options="fit:true,border:false">
<thead id="pre">  
         
 </thead>
</table>

</div>

</div>
</body>
</html>