<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ page import="aisino.reportform.util.base.SecurityUtil"%>
	<%@ page import="aisino.reportform.model.base.SessionInfo"%>
<%
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
<script type="text/javascript" src="<%=contextPath%>/jslib/My97DatePicker4.8Beta3/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
var sid='${param.sid}';
var targetObj;
	var submitForm = function($dialog, $grid, $pjq) {
		parent.$.messager.progress({
			text : '数据加载中....'
		});
		if ($('form').form('validate')) {
			var url;
			if (sid.length > 0) {
				url = sy.contextPath + '/base/specific!update.sy';
			} else {
				url = sy.contextPath + '/base/specific!save.sy';
			}
			$.post(url, sy.serializeObject($('form')), function(result) {
				if (result.success) {
					parent.$.messager.progress('close');
					$grid.datagrid('reload');
// 					$dialog.dialog('destroy');
					$pjq.messager.alert('提示', '保存成功!', 'info');
				} else {
					parent.$.messager.progress('close');
					$pjq.messager.alert('提示', result.msg, 'error');
				}
			}, 'json');
		}
	};

 
 
   function sum(o){
	   //获取当前对象的单价、数量、总价
	   var beforeTotal=$("#to"+o.id.substring(2)).val();
	   var price=$("#pr"+o.id.substring(2)).val();
	   var number=$("#nu"+o.id.substring(2)).val();
	   var afterTotal=accMul(price,number);
	   if(isNaN(afterTotal)||afterTotal<0){
		   return false;
	   }
	   $("#to"+o.id.substring(2)).val(afterTotal).change();
	   
	   //计算毛利
	   if(o.id.substring(2)<269)
	   {   
	       $("#to"+(parseInt(o.id.substring(2))+502)).val(accSub($("#to"+o.id.substring(2)).val(),$("#to"+(parseInt(o.id.substring(2))+251)).val())).change();
	   }
	   else if(o.id.substring(2)>268&& o.id.substring(2)<520)
	   {
		   $("#to"+(parseInt(o.id.substring(2))+251)).val(accSub($("#to"+(parseInt(o.id.substring(2))-251)).val(),$("#to"+o.id.substring(2)).val())).change();
	   }
		    
	   
	   //当前对象的父对象total同时也改变
	   parentSum($("#pid"+o.id.substring(2)).val(),beforeTotal,afterTotal);
   }
   
   function parentSum(id,beforeTotal,afterTotal){
	   var pBeforeTotal=$("#to"+id).val();
	   var pAfterTotal=accSub(accAdd(Number($("#to"+id).val()),Number(afterTotal)),Number(beforeTotal))
	   $("#to"+id).val(pAfterTotal).change();
	   
	   //计算毛利
	   if(id<269)
	   {   
	       $("#to"+(parseInt(id)+502)).val(accSub($("#to"+id).val(),$("#to"+(parseInt(id)+251)).val())).change();
	   }
	   else if(id>268&& id<520)
	   {
		   $("#to"+(parseInt(id)+251)).val(accSub($("#to"+(parseInt(id)-251)).val(),$("#to"+id).val())).change();
	   }
	   
	   //如果有上级对象才改变上级total
	   if($("#pid"+id).val()!="0"&&$("#pid"+id).val()!=undefined){
		   parentSum($("#pid"+id).val(),pBeforeTotal,pAfterTotal);
	   }
   }
   
   function sum2(o){
	   //获取当前对象的单价、数量、总价
	   var beforeTotal=$("#tot"+o.id.substring(3)).val();
	   var price=$("#pri"+o.id.substring(3)).val();
	   var number=$("#num"+o.id.substring(3)).val();
	   var afterTotal=accMul(price,number);
	   if(isNaN(afterTotal)||afterTotal<0){
		   return false;
	   }
	   $("#tot"+o.id.substring(3)).val(afterTotal);
	   
	   //计算毛利
	   if(o.id.substring(3)<269)
	   {   
	       $("#tot"+(parseInt(o.id.substring(3))+502)).val(accSub($("#tot"+o.id.substring(3)).val(),$("#tot"+(parseInt(o.id.substring(3))+251)).val())).change();
	   }
	   else if(o.id.substring(3)>268&& o.id.substring(3)<520)
	   {
		   $("#tot"+(parseInt(o.id.substring(3))+251)).val(accSub($("#tot"+(parseInt(o.id.substring(3))-251)).val(),$("#tot"+o.id.substring(3)).val())).change();
	   }
	 
	 
	   //当前对象的父对象total同时也改变
	   parentSum2($("#pid"+o.id.substring(3)).val(),beforeTotal,afterTotal);

   }
   
   function parentSum2(id,beforeTotal,afterTotal){
	   var pBeforeTotal=$("#tot"+id).val();
	   var pAfterTotal=accSub(accAdd(Number($("#tot"+id).val()),Number(afterTotal)),Number(beforeTotal))
   $("#tot"+id).val(pAfterTotal);
	   
	   //计算毛利
	   if(id<269)
	   {   
	       $("#tot"+(parseInt(id)+502)).val(accSub($("#tot"+id).val(),$("#tot"+(parseInt(id)+251)).val())).change();
	   }
	   else if(id>268&& id<520)
	   {
		   $("#tot"+(parseInt(id)+251)).val(accSub($("#tot"+(parseInt(id)-251)).val(),$("#tot"+id).val())).change();
	   }
	 
	 
	   //如果有上级对象才改变上级total
	   if($("#pid"+id).val()!="0"&&$("#pid"+id).val()!=undefined){
		   parentSum2($("#pid"+id).val(),pBeforeTotal,pAfterTotal);
	   }
   }
 
 
	$(function() {

		$.post(sy.contextPath + '/base/budget!grid2.sy',{sort:"uu",order:"asc"},
				
				function(result) {
			$("#org").val(result.avgMap.orgName);
			$("#year").val(getCurrentDate().substring(0,4));
			$("#time1").html(getCurrentDate().substring(0,4));
			$("#time2").html(Number(getCurrentDate().substring(0,4))+1);
			for(var i=0;i<result.total;i++){
				if(result.rows[i].iscu==1){
					if(result.rows[i].type==1){
						targetObj = $('<tr>'+
								'<td  style="font-weight:bold" >'+result.rows[i].directives+'</td>'+
								'<td > <input readonly="readonly"  style="width:115px" type="hidden" name="data.budget.indexs" value='+result.rows[i].indexs+'> <input readonly="readonly" style="width:115px" validtype="" class="easyui-validatebox"  data-options="required:true" name="data.numbers" id="nu'+result.rows[i].indexs+'"   value="0" ><input id="pid'+result.rows[i].indexs+'" type="hidden"  value='+result.rows[i].pid+'></td>'+
								'<td ><input readonly="readonly"   style="width:115px" validtype="" class="easyui-validatebox"data-options="required:true" name="data.price"  id="pr'+result.rows[i].indexs+'"   value="0" ></td>'+	
								'<td ><input readonly="readonly"  style="width:115px" validtype="" class="easyui-validatebox"data-options="required:true"   name="data.total"  id="to'+result.rows[i].indexs+'"   value="0"  onchange="setValue_1(this.id)"></td>'+
								
								'<td > <input readonly="readonly" style="width:115px" validtype="" class="easyui-validatebox"  data-options="required:true" name="data.numbers2" id="num'+result.rows[i].indexs+'"   value="0" onkeyup=""></td>'+
								'<td ><input readonly="readonly" style="width:115px" validtype="" class="easyui-validatebox"data-options="required:true" name="data.price2"  id="pri'+result.rows[i].indexs+'"   value="0" onkeyup=""></td>'+	
								'<td ><input readonly="readonly" style="width:115px" validtype="" class="easyui-validatebox"data-options="required:true"   name="data.total2"  id="tot'+result.rows[i].indexs+'"  value="0"  onchange="setValue_2(this.id)" "></td>'+
								'</tr>').appendTo("#tab");
					}else{
						targetObj = $('<tr>'+
								'<td  style="font-weight:bold" >'+result.rows[i].directives+'</td>'+
								'<td > <input readonly="readonly"  style="width:115px" type="hidden" name="data.budget.indexs" value='+result.rows[i].indexs+'> <input type="hidden"  style="width:115px"    name="data.numbers" id="nu'+result.rows[i].indexs+'"   value="null" ></td>'+
								'<td ><input type="hidden"  style="width:115px" name="data.price"  id="pr'+result.rows[i].indexs+'"  value="null" ></td>'+	
								'<td ><input style="width:115px" validtype="" class="easyui-validatebox"data-options="required:true"   name="data.total" id="to'+result.rows[i].indexs+'"   value="0"  onkeyup="setValue_1(this.id)" onchange="setValue_1(this.id)"></td>'+
								
								'<td > <input type="hidden" style="width:115px" name="data.numbers2"   value="null" ></td>'+
								'<td ><input type="hidden" style="width:115px"  name="data.price2"    value="null" ></td>'+	
								'<td ><input  style="width:115px" validtype="" class="easyui-validatebox"data-options="required:true" id="tot'+result.rows[i].indexs+'"  name="data.total2"   value="0"  onkeyup="setValue_2(this.id)" onchange="setValue_2(this.id)"></td>'+
								'</tr>').appendTo("#tab");
					}

					
				}else{
					if(result.rows[i].type==1){
                            targetObj = $('<tr>'+
        							'<td style="">'+result.rows[i].directives+'</td>'+
        							'<td ><input style="width:115px" type="hidden" name="data.budget.indexs" value='+result.rows[i].indexs+'><input style="width:115px"  data-options="required:true" class="easyui-validatebox" name="data.numbers"  id="nu'+result.rows[i].indexs+'"   value="0" onkeyup="sum(this)"><input id="pid'+result.rows[i].indexs+'" type="hidden"  value='+result.rows[i].pid+'></td>'+
        							'<td ><input style="width:115px" validtype=""  class="easyui-validatebox"data-options="required:true"  name="data.price" id="pr'+result.rows[i].indexs+'"   value="0"  onkeyup="sum(this)"></td>'+
        							'<td ><input readonly="readonly" style="width:115px" validtype="" class="easyui-validatebox"data-options="required:true"     name="data.total"  id="to'+result.rows[i].indexs+'"    value="0" onchange="setValue_1(this.id)"></td>'+
        							
        							'<td ><input style="width:115px" validtype="" class="easyui-validatebox"  data-options="required:true" name="data.numbers2" id="num'+result.rows[i].indexs+'"   value="0" onkeyup="sum2(this)"></td>'+
        							'<td ><input style="width:115px" validtype="" class="easyui-validatebox"data-options="required:true" name="data.price2"  id="pri'+result.rows[i].indexs+'"   value="0" onkeyup="sum2(this)"></td>'+	
        							'<td ><input readonly="readonly" style="width:115px" validtype="" class="easyui-validatebox"data-options="required:true"   name="data.total2"  id="tot'+result.rows[i].indexs+'"  value="0"  onchange="setValue_1(this.id)" "></td>'+
        							'</tr>').appendTo("#tab");
					}else{
						targetObj = $('<tr>'+
    							'<td style="">'+result.rows[i].directives+'</td>'+
    							'<td ><input style="width:115px" type="hidden" name="data.budget.indexs" value='+result.rows[i].indexs+'><input type="hidden" style="width:115px"   name="data.numbers"    value="null" ></td>'+
    							'<td ><input type="hidden" style="width:115px"  name="data.price"  value="null"  ></td>'+
    							'<td ><input  style="width:115px" validtype="" class="easyui-validatebox"data-options="required:true" id="to'+result.rows[i].indexs+'"    name="data.total"     value="0" onkeyup="setValue_1(this.id)" onchange="setValue_1(this.id)"></td>'+
    							
    							'<td ><input type="hidden" style="width:115px"    name="data.numbers2"   value="null" ></td>'+
    							'<td ><input type="hidden" style="width:115px"  name="data.price2"    value="null" ></td>'+	
    							'<td ><input style="width:115px" validtype="" class="easyui-validatebox"data-options="required:true" id="tot'+result.rows[i].indexs+'"  name="data.total2"   value="0"  onkeyup="setValue_2(this.id)" onchange="setValue_2(this.id)"></td>'+
    							'</tr>').appendTo("#tab");
					}
				}
				$.parser.parse(targetObj);
			}
			//如果sid不为空，表示打开编辑页面，后台取数据
			if(sid!=""){
				parent.$.messager.progress({
					text : '数据加载中....'
				});
				$("#yspan").html("编制年份:<input style='width:120px' name='year' id='year' readonly='readonly' />");
				$.post(sy.contextPath + '/base/specific!grid.sy',{sid:sid},
						function(result) {
					//设置编辑页面的年份和公司
					$("#year").val(result.rows[0].sname.substring(0,4));
					$("#time1").html(result.rows[0].sname.substring(0,4));
					$("#time2").html(Number(result.rows[0].sname.substring(0,4))+1);
					$("#org").val(result.rows[0].sname.substring(4));
					for(var i=0;i<result.rows.length;i++){
						$("#tab tr:eq("+(i+2)+") td:eq(1) input:eq(1)").val(changeVal(result.rows[i].numbers));
						$("#tab tr:eq("+(i+2)+") td:eq(2) input:eq(0)").val(changeVal(result.rows[i].price));
						$("#tab tr:eq("+(i+2)+") td:eq(3) input:eq(0)").val(changeVal(result.rows[i].total));
						$("#tab tr:eq("+(i+2)+") td:eq(4) input:eq(0)").val(changeVal(result.rows[i].numbers2));
						$("#tab tr:eq("+(i+2)+") td:eq(5) input:eq(0)").val(changeVal(result.rows[i].price2));
						$("#tab tr:eq("+(i+2)+") td:eq(6) input:eq(0)").val(changeVal(result.rows[i].total2));
					}
					parent.$.messager.progress('close');
			}, 'json');
			}
		}, 'json');

	});
	//如果是undefined，设置为字符串"null"
	function changeVal(s){
		if(s==undefined){
			return "null";
		}else{
			return s;
		}
	}
	//改变第一列的total值
	function change1_1(){
		$("#to1").val(accAdd($("#to2").val(),$("#to6").val())).change();
	}
	function change2_1(){
		$("#to2").val(accSub(accAdd($("#to3").val(),$("#to4").val()),$("#to5").val())).change();
	}
	function change6_1(){
		$("#to6").val(accAdd($("#to7").val(),$("#to8").val())).change();
	}
	function change7_1(){
		$("#to7").val(accAdd(accMul($("#to10").val(),$("#to16").val()),$("#to13").val())).change();
	}
	function change8_1(){
		$("#to8").val(accAdd(accMul($("#to11").val(),$("#to17").val()),$("#to14").val())).change();
	}
	function change9_1(){
		$("#to9").val(accAdd($("#to10").val(),$("#to11").val())).change();
	}
	function change12_1(){
		$("#to12").val(accAdd($("#to13").val(),$("#to14").val())).change();
	}
	function change802_1() {
		$("#to802").val(accAdd(accSub(accSub(accSub($("#to520").val(),$("#to771").val()),$("#to799").val()),$("#to800").val()),$("#to801").val())).change();
	}
	function change804_1(){
		$("#to804").val(accSub($("#to802").val(),$("#to803").val())).change();
	}
	function change808_1(){
		$("#to808").val(accDiv(accSub(accSub(accSub(accSub(accSub(accSub(accSub(accSub($("#to520").val(),$("#to539").val()),$("#to540").val()),$("#to541").val()),$("#to543").val()),$("#to544").val()),$("#to524").val()),$("#to548").val()),$("#to613").val()),$("#to520").val())).change();
	}
	function change809_1(){
		$("#to809").val(accDiv($("#to802").val(),$("#to778").val())).change();
	}
	function change810_1(){
		$("#to810").val(accDiv($("#to802").val(),$("#to805").val())).change();
	}
	function change811_1(){
		$("#to811").val(accDiv($("#to778").val(),$("#to805").val())).change();
	}
	function change812_1(){
		$("#to812").val(accDiv($("#to802").val(),$("#to771").val())).change();
	}
	
	function setValue_1(id){
		id=id.replace("to","");
		if(id=="2"||id=="6"){
			change1_1();
		}
		if(id=="3"||id=="4"||id=="5"){
			change2_1();
		}
		if(id=="7"||id=="8"){
			change6_1();
		}
		if(id=="10"||id=="16"||id=="13"){
			change7_1();
		}
		if(id=="11"||id=="17"||id=="14"){
			change8_1();
		}
		if(id=="10"||id=="11"){
			change9_1();
		}
		if(id=="13"||id=="14"){
			change12_1();
		}
		if(id=="520"||id=="771"||id=="799"||id=="800"||id=="801"){
			change802_1();
		}
		if(id=="802"||id=="803"){
			change804_1();
		}
		if(id=="520"||id=="539"||id=="540"||id=="541"||id=="543"||id=="544"||id=="524"||id=="548"||id=="613"||id=="520"){
			change808_1();
		}
		if(id=="802"||id=="778"){
			change809_1();
		}
		if(id=="802"||id=="805"){
			change810_1();
		}
		if(id=="778"||id=="805"){
			change811_1();
		}
		if(id=="802"||id=="771"){
			change812_1();
		}

	}
	//改变第二列的total值
	function change1_2(){
		$("#tot1").val(accAdd($("#tot2").val(),$("#tot6").val())).change();
	}
	function change2_2(){
		$("#tot2").val(accSub(accAdd($("#tot3").val(),$("#tot4").val()),$("#tot5").val())).change();
	}
	function change6_2(){
		$("#tot6").val(accAdd($("#tot7").val(),$("#tot8").val())).change();
	}
	function change7_2(){
		$("#tot7").val(accAdd(accMul($("#tot10").val(),$("#tot16").val()),$("#tot13").val())).change();
	}
	function change8_2(){
		$("#tot8").val(accAdd(accMul($("#tot11").val(),$("#tot17").val()),$("#tot14").val())).change();
	}
	function change9_2(){
		$("#tot9").val(accAdd($("#tot10").val(),$("#tot11").val())).change();
	}
	function change12_2(){
		$("#tot12").val(accAdd($("#tot13").val(),$("#tot14").val())).change();
	}
	function change802_2() {
		$("#tot802").val(accAdd(accSub(accSub(accSub($("#tot520").val(),$("#tot771").val()),$("#tot799").val()),$("#tot800").val()),$("#tot801").val())).change();
	}
	function change804_2(){
		$("#tot804").val(accSub($("#tot802").val(),$("#tot803").val())).change();
	}
	function change808_2(){
		$("#tot808").val(accDiv(accSub(accSub(accSub(accSub(accSub(accSub(accSub(accSub($("#tot520").val(),$("#tot539").val()),$("#tot540").val()),$("#tot541").val()),$("#tot543").val()),$("#tot544").val()),$("#tot524").val()),$("#tot548").val()),$("#tot613").val()),$("#tot520").val())).change();
	}
	function change809_2(){
		$("#tot809").val(accDiv($("#tot802").val(),$("#tot778").val())).change();
	}
	function change810_2(){
		$("#tot810").val(accDiv($("#tot802").val(),$("#tot806").val())).change();
	}
	function change811_2(){
		$("#tot811").val(accDiv($("#tot778").val(),$("#tot806").val())).change();
	}
	function change812_2(){
		$("#tot812").val(accDiv($("#tot802").val(),$("#tot771").val())).change();
	}
	
	function setValue_2(id){
		id=id.replace("tot","");
		if(id=="2"||id=="6"){
			change1_2();
		}
		if(id=="3"||id=="4"||id=="5"){
			change2_2();
		}
		if(id=="7"||id=="8"){
			change6_2();
		}
		if(id=="10"||id=="16"||id=="13"){
			change7_2();
		}
		if(id=="11"||id=="17"||id=="14"){
			change8_2();
		}
		if(id=="10"||id=="11"){
			change9_2();
		}
		if(id=="13"||id=="14"){
			change12_2();
		}
		if(id=="520"||id=="771"||id=="799"||id=="800"||id=="801"){
			change802_2();
		}
		if(id=="802"||id=="803"){
			change804_2();
		}
		if(id=="520"||id=="539"||id=="540"||id=="541"||id=="543"||id=="544"||id=="524"||id=="548"||id=="613"||id=="520"){
			change808_2();
		}
		if(id=="802"||id=="778"){
			change809_2();
		}
		if(id=="802"||id=="806"){
			change810_2();
		}
		if(id=="778"||id=="806"){
			change811_2();
		}
		if(id=="802"||id=="771"){
			change812_2();
		}

	}
</script>
</head>
<body>

	<form method="post" class="form">
	<span id="yspan">编制年份:
<input name="year" id="year"  value="" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy'})" style="width: 120px;" />
	</span>
	<span>编制单位:
<input id="org"  name="orgName" readonly="readonly"  style="width: 120px;" />
   </span>
		<fieldset>
			
			<table id="tab" class="table" style="width: 100%;">
				<tr >
				<th colspan="1"  rowspan="2" style="text-align: center">分类/指标名称</th>
					<th id="time1" colspan="3"  rowspan="1" style="text-align: center"></th>
					<th id="time2" colspan="3"  rowspan="1" style="text-align: center"></th>
							
				</tr>
				<tr>
				<th style="text-align: center;width:120px">数量</th>
						<th style="text-align: center;width:120px">单价</th>
							<th style="text-align: center;width:120px">合计</th>
							<th style="text-align: center;width:120px">数量</th>
						<th style="text-align: center;width:120px">单价</th>
							<th style="text-align: center;width:120px">合计</th>
				</tr>
				
			</table>
			
		<input id="sid"  name="sid" type="hidden" value="${param.sid}" />
		</fieldset>
	</form>
	
</body>
</html>