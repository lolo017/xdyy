<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<title>测试页面</title>
<jsp:include page="../inc.jsp"></jsp:include>
<script type="text/javascript">
// 	$(function() {
// 		$.post(sy.contextPath + '/init!doNotNeedSessionAndSecurity_initDb.sy', function(result) {
// 			if (result.success) {
// 				window.location.replace(sy.contextPath + '/index.jsp');
// 			}
// 		}, 'json');
// 	});
function cl(){
	alert($("#searchForm").serialize());
	alert(getFormPara($("#searchForm").serialize()));
	//alert(sy.serializeObject($('#searchForm')));
}
function getFormPara(paraStr) {
    var strs = paraStr.split("&");
    var formPara="";
     for(var i = 0; i < strs.length; i ++) {
   	  //id，db不需要传递,且值为空的参数也不用传递
   	  if(strs[i].split("=")[0]!="id"&&strs[i].split("=")[0]!="db"&&strs[i].split("=")[1]!=undefined&&strs[i].split("=")[1]!=""){
   		  formPara=formPara+"&"+strs[i];
   	  }
     }
  return formPara;
}
var Sys = {};  
var ua = navigator.userAgent.toLowerCase();  
if (window.ActiveXObject)  
    Sys.ie = ua.match(/msie ([\d.]+)/)[1];  
else if (document.getBoxObjectFor)  
    Sys.firefox = ua.match(/firefox\/([\d.]+)/)[1];  
      
function containsArray(array, obj) {  
    for (var i = 0; i < array.length; i++) {  
        if (array[i] == obj) {  
            return i;  
            break;  
        }  
    }  
    return -1;  
}  
  
Array.prototype.contains = function(obj) {  
    return containsArray(this, obj);  
}
function PrintTableToExcel(tableId) {  
	  
    var offsetLeftArray = new Array();  
    var cell;// 单元格Dom  
    var col;// 单元格实际所在列  
    var cellStr;// 每个cell以row,col,rowSpan,colSpan,value形式  
    var cellStrArray = [];  
    var objTab = document.getElementById(tableId);  
  
    // 遍历第一次取出offsetLeft集合  
    for (var i = 0; i < objTab.rows.length; i++) {  
        for (var j = 0; j < objTab.rows[i].cells.length; j++) {  
            cell = objTab.rows[i].cells[j];  
            if (offsetLeftArray.contains(cell.offsetLeft) == -1)  
                offsetLeftArray.push(cell.offsetLeft);  
        }  
    }  
  
    offsetLeftArray.sort(function(x, y) { return parseInt(x) - parseInt(y); });  
  
    // 遍历第二次生成cellStrArray  
    for (var i = 0; i < objTab.rows.length; i++) {  
        for (var j = 0; j < objTab.rows[i].cells.length; j++) {  
            cell = objTab.rows[i].cells[j];  
            col = offsetLeftArray.contains(cell.offsetLeft);  
            cellStr = i + ',' + col + ',' + cell.rowSpan + ',' + cell.colSpan ;  
            cellStrArray.push(cellStr);  
        }  
    } 
    var str = "";  
    str += cellStrArray.join('+');  
    alert(str);  
  
} 
</script>
</head>
<body>
<form id="searchForm">
<table id="tt">
			<tr  id="conTr">
			<td><input name="aa" value="1"/></td>
			<td><input name="bb" value=""/></td>
			<td><input name="cc" value=""/></td>
			<td><input name="dd" value="2"/></td>
			</tr>
</table>
</form>
<input type="button" value="按钮" onclick="PrintTableToExcel('tt');"/>


<td>单位</td>
<td><input id="dw"style="width:130px"panelHeight:"100px" name="dw" class="easyui-combobox"  data-options="valueField:'VALUE',textField:'TEXT',url:'/AisinoReportForm/base/report-form!doNotNeedSecurity_sqlCombobox.sy?id=5de01ef7-21fc-4167-83b4-bde4717f6a6f&db=2', onLoadSuccess: function (data) {var para=urlParas['dw'];if (data) {if(data.length>0){$('#'+'dw').combobox('setValue',data[0].VALUE);for(var i=0;i<data.length;i++){if(para!=undefined&&data[i].VALUE!=undefined&&para==data[i].VALUE){$('#'+'dw').combobox('setValue',para);}}}comboboxNum=comboboxNum+1;loadFinish('SORT_PARA');}}" ></input></td>
</body>
</html>