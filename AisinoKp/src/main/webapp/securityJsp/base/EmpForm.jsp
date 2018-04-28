<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String contextPath = request.getContextPath();
// SessionInfo sessionInfo = (SessionInfo) session.getAttribute("sessionInfo");
//   String userId=sessionInfo.getUser().getId();

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
var id='${param.id}';
var submitForm = function($dialog, $grid, $pjq,dpid) {
	if ($('form').form('validate')) {
		setList();
		var url;
		if (id!="") {
			url = sy.contextPath + '/base/emp-data!update.sy';
		} else {
			url = sy.contextPath + '/base/emp-data!save.sy';
		}
		$.post(url, sy.serializeObject($('form')), function(result) {
			if (result.success) {
				
				$grid.datagrid('reload');
				$dialog.dialog('destroy');
				//$mainMenu.tree('reload');
				
			} else {
				$pjq.messager.alert('提示', result.msg, 'error');
			}
		}, 'json');
	}
};

$(function() {
	if ($(':input[name="data.empId"]').val().length > 0) {
		$(':input[name="data.empId"]').attr("readonly","readonly");
		parent.$.messager.progress({
			text : '数据加载中....'
		});
		$
				.post(
						sy.contextPath + '/base/emp-data!getById.sy',
						{
							id : $(':input[name="data.empId"]').val(),
						},
						function(result) {
							if (result.data.empId != undefined) {
								$('form')
										.form(
												'load',
												{
													//'data.id' : result.data.id,
													'data.empId' : result.data.empId,
													'data.name' : result.data.name,
													'data.sex' : result.data.sex,
													'data.hight' : result.data.hight,
													'data.blood' : result.data.blood,
													'data.home' : result.data.home,
													'data.mobile' : result.data.mobile,
													'data.address' : result.data.address,
													'data.phone' : result.data.phone,
													'data.political' : result.data.political,
													'data.orgOrganization.id' : result.data.orgOrganization.id,
													'data.depOrganization.id':result.data.depOrganization.id,
													'data.posOrganization.id':result.data.posOrganization.id
													//'data.empData.empId':result.data.empData.empId
												});
								//设置上级
								if(typeof(result.data.empData) !="undefined"){
									$("input[name='data.empData.empId']").val(result.data.empData.empId);
									$("#leaderName").val(result.data.empData.name);
									}
								//显示入职日期、生日
								if(typeof(result.data.indate) !="undefined"){
								$("#indate").datebox("setValue", result.data.indate.substr(0,10));
								}
								if(typeof(result.data.birthday) !="undefined"){
								$("#birthday").datebox("setValue", result.data.birthday.substr(0,10));
								}
								//显示学历、职称、家庭信息
								var targetObj;
								for(var o=0;o<result.degree.length;o++){
								//for(var o in result.degree){
									targetObj=$("<tr><td width='30%'  ><input  id='xlSelect"+o+"' panelHeight=\"110\" class=\"easyui-combobox\"editable=\"false\"  data-options=\"required:true,valueField: 'value',textField: 'text',data: [{text: '专科',value: '1'},{text: '本科',value: '2'},{text: '硕士',value: '3'},{text: '博士',value: '4'},{text: '博士后',value: '5'}]\" /></td width='30%' ><td><input  class='easyui-validatebox' style='width: 160px' value='"+result.degree[o].school+"' data-options='required:true' /></td><td width='30%' ><input id='degreeDate"+o+"' class='easyui-datebox' editable='false' style='width: 160px' data-options='required:true'/></td><td width='10%' style='text-align:center;'><a  href='javascript:void(0);' onclick='delTr(this)'>删除</a></td></tr>").appendTo("#xlList");
									$.parser.parse(targetObj);
									$("#xlSelect"+o).combobox('setValue',result.degree[o].degree);
									$("#degreeDate"+o).datebox("setValue",result.degree[o].graduationDate.substr(0,10));
								}
								for(var o=0;o<result.title.length;o++){
								//for(var o in result.title){
									targetObj=$("<tr><td width='30%'  ><input  class='easyui-validatebox' style='width: 160px' value='"+result.title[o].title+"'data-options='required:true' /></td width='30%' ><td><input  id='zcSelect"+o+"' panelHeight=\"110\" class=\"easyui-combobox\"editable=\"false\"  data-options=\"required:true,valueField: 'value',textField: 'text',data: [{text: '初级',value: '1'},{text: '中级',value: '2'},{text: '高级',value: '3'}]\" /></td><td width='30%' ><input id='titleDate"+o+"' class='easyui-datebox' editable='false' style='width: 160px' data-options='required:true'/></td><td width='10%' style='text-align:center;'><a  href='javascript:void(0);' onclick='delTr(this)'>删除</a></td></tr>").appendTo("#zcList");
									$.parser.parse(targetObj);
									$("#zcSelect"+o).combobox('setValue',result.title[o].grade);
									$("#titleDate"+o).datebox("setValue",result.title[o].getDate.substr(0,10));
								}
								for(var o=0;o<result.family.length;o++){
								//for(var o in result.family){
									targetObj=$("<tr><td width='22%' ><input  class='easyui-validatebox' style='width: 120px' value='"+result.family[o].name+"'data-options='required:true' /></td><td width='22%'  ><input  class='easyui-validatebox' style='width: 120px' value='"+result.family[o].relationship+"'data-options='required:true' /></td width='22%' ><td><input  class='easyui-validatebox' style='width: 120px' value='"+result.family[o].company+"' data-options='required:true' /></td><td width='22%' ><input  class='easyui-validatebox' value='"+result.family[o].phone+"' style='width: 120px' data-options='required:true'/></td><td width='12%' style='text-align:center;'><a  href='javascript:void(0);' onclick='delTr(this)'>删除</a></td></tr>").appendTo("#jtcyList");
									$.parser.parse(targetObj);
								}
								$("#orgName").val(result.data.orgOrganization.name);
								$("#orgName").validatebox('validate');
								$("#depName").val(result.data.depOrganization.name);
								$("#depName").validatebox('validate');
								$("#posName").val(result.data.posOrganization.name);
								$("#posName").validatebox('validate');
								
								//设置部门ID
								$("#dp").combotree("setValue",result.data.depOrganization.id);
								//设置上级ID
								$("#leader").combotree("setValue",result.data.leaderid);
							}
							parent.$.messager.progress('close');
						}, 'json');
	}else{
		//设置部门ID
		$("#dp").combotree("setValue","${param.dpid}");
		//设置部门ID
		$("#leader").combotree("setValue","51010001");
	}
});

//添加学历、职称、家庭 信息输入框
function  addTr(tableId){
	if(tableId=="xlList"){
	var targetObj=$("<tr><td width='30%'  ><input panelHeight=\"110\" class=\"easyui-combobox\"editable=\"false\"  data-options=\"required:true,valueField: 'value',textField: 'text',data: [{text: '专科',value: '1'},{text: '本科',value: '2'},{text: '硕士',value: '3'},{text: '博士',value: '4'},{text: '博士后',value: '5'}]\" /></td ><td width='30%' ><input  class='easyui-validatebox' style='width: 160px' data-options='required:true' /></td><td width='30%' ><input  class='easyui-datebox' editable='false' style='width: 160px' data-options='required:true'/></td><td width='10%' style='text-align:center;'><a  href='javascript:void(0);' onclick='delTr(this)'>删除</a></td></tr>").appendTo("#"+tableId);
	}
	if(tableId=="zcList"){
	var targetObj=$("<tr><td width='30%'  ><input  class='easyui-validatebox' style='width: 160px' data-options='required:true' /></td width='30%' ><td><input panelHeight=\"110\" class=\"easyui-combobox\"editable=\"false\"  data-options=\"required:true,valueField: 'value',textField: 'text',data: [{text: '初级',value: '1'},{text: '中级',value: '2'},{text: '高级',value: '3'}]\" /></td><td width='30%' ><input  class='easyui-datebox' editable='false' style='width: 160px' data-options='required:true' /></td><td width='10%' style='text-align:center;'><a  href='javascript:void(0);' onclick='delTr(this)'>删除</a></td></tr>").appendTo("#"+tableId);	
	}
	if(tableId=="jtcyList"){
	var targetObj=$("<tr><td width='22%'  ><input  class='easyui-validatebox' style='width: 120px' data-options='required:true' /></td><td width='22%'  ><input  class='easyui-validatebox' style='width: 120px' data-options='required:true' /></td width='22%' ><td><input  class='easyui-validatebox' style='width: 120px' data-options='required:true' /></td><td width='22%' ><input  class='easyui-validatebox'  style='width: 120px' data-options='required:true' /></td><td width='12%' style='text-align:center;'><a  href='javascript:void(0);' onclick='delTr(this)'>删除</a></td></tr>").appendTo("#"+tableId);
	}
	$.parser.parse(targetObj);
}
//删除
function delTr(now){
	$(now).parent().parent().remove(); 
}
//将学历、职称、家庭信息按list方式传参
function setList(){
	var xlLength=$("#xlList tr").length;
	var zcLength=$("#zcList tr").length;
	var jtLength=$("#jtcyList tr").length;
	var i;
	var j;
    	for( i=1;i<xlLength;i++){
    		 j=i-1;
    		//$("#xlSelect"+j).attr("name","empDegreeList["+j+"].degree");
    		$("#xlList tr:eq("+i+") td:eq(0) span:eq(0) input:eq(1)").attr("name","empDegreeList["+j+"].degree");
    		$("#xlList tr:eq("+i+") td:eq(1) input").attr("name","empDegreeList["+j+"].school");
    		$("#xlList tr:eq("+i+") td:eq(2) span:eq(0) input:eq(1)").attr("name","empDegreeList["+j+"].graduationDate");
    		//$("#hiddenDate").append("<input  name='empDegreeList["+j+"].graduationDate' value='"+$("#xlList tr:eq("+i+") td:eq(2) input").datebox("getValue")+"'/>");

    	}
	
    	for( i=1;i<zcLength;i++){
    		 j=i-1;
    		$("#zcList tr:eq("+i+") td:eq(0) input").attr("name","empTitleList["+j+"].title");
//     		$("#zcList tr:eq("+i+") td:eq(1) input").attr("name","empTitleList["+j+"].grade");
            $("#zcList tr:eq("+i+") td:eq(1) span:eq(0) input:eq(1)").attr("name","empTitleList["+j+"].grade");
    		//$("#hiddenDate").append("<input  name='empTitleList["+j+"].getDate' value='"+$("#zcList tr:eq("+i+") td:eq(2) input").datebox("getValue")+"'/>");
            $("#zcList tr:eq("+i+") td:eq(2) span:eq(0) input:eq(1)").attr("name","empTitleList["+j+"].getDate");
    	}
    	for( i=1;i<jtLength;i++){
    		 j=i-1;
    		 $("#jtcyList tr:eq("+i+") td:eq(0) input").attr("name","empFamilyList["+j+"].name");
    		$("#jtcyList tr:eq("+i+") td:eq(1) input").attr("name","empFamilyList["+j+"].relationship");
    		$("#jtcyList tr:eq("+i+") td:eq(2) input").attr("name","empFamilyList["+j+"].company");
    		$("#jtcyList tr:eq("+i+") td:eq(3) input").attr("name","empFamilyList["+j+"].phone");

    }
    
}
function orgChange(){
var dialog = parent.sy.modalDialog({
	title : '选择公司',
	url : sy.contextPath + '/securityJsp/base/EmpTree.jsp?method=orgTreeGrid',
	height:540,
	buttons : [ {
		text : '确认',
		handler : function() {
			//dialog.find('iframe').get(0).contentWindow.sendParam(id,pageName,tStr,cStr,sqlStr,arrayEn,arrayCn,arrayType,arraySql);
			//dialog.find('iframe').get(0).contentWindow.submitForm(dialog, parent.$, parent.mainMenu);
		var array=dialog.find('iframe').get(0).contentWindow.saveChange(dialog);
		if(array.length>0){
		$("#orgName").val(array[1]);
		$("input[name='data.orgOrganization.id']").val(array[0]);
		//清空部门，岗位
		$("#depName").val("");
		$("input[name='data.depOrganization.id']").val("");
		$("#posName").val("");
		$("input[name='data.posOrganization.id']").val("");
		}
		}
	} ]
	
});
}
function depChange(){
	var dialog = parent.sy.modalDialog({
		title : '选择部门',
		url : sy.contextPath + '/securityJsp/base/EmpTree.jsp?method=depTreeGrid&id='+$("input[name='data.orgOrganization.id']").val(),
		height:540,
		buttons : [ {
			text : '确认',
			handler : function() {
				//dialog.find('iframe').get(0).contentWindow.sendParam(id,pageName,tStr,cStr,sqlStr,arrayEn,arrayCn,arrayType,arraySql);
				//dialog.find('iframe').get(0).contentWindow.submitForm(dialog, parent.$, parent.mainMenu);
			var array=dialog.find('iframe').get(0).contentWindow.saveChange(dialog);
			if(array.length>0){
			$("#depName").val(array[1]);
			$("input[name='data.depOrganization.id']").val(array[0]);
			//清空岗位
			$("#posName").val("");
			$("input[name='data.posOrganization.id']").val("");
			}
			}
		} ]
		
	});
	}
function posChange(){
	var dialog = parent.sy.modalDialog({
		title : '选择岗位',
		url : sy.contextPath + '/securityJsp/base/EmpTree.jsp?method=posTreeGrid&id='+$("input[name='data.depOrganization.id']").val(),
		height:540,
		buttons : [ {
			text : '确认',
			handler : function() {
				//dialog.find('iframe').get(0).contentWindow.sendParam(id,pageName,tStr,cStr,sqlStr,arrayEn,arrayCn,arrayType,arraySql);
				//dialog.find('iframe').get(0).contentWindow.submitForm(dialog, parent.$, parent.mainMenu);
			var array=dialog.find('iframe').get(0).contentWindow.saveChange(dialog);
			if(array.length>0){
			$("#posName").val(array[1]);
			$("input[name='data.posOrganization.id']").val(array[0]);
			}
			}
		} ]
		
	});
	}
function leaderChange(){
	var dialog = parent.sy.modalDialog({
		title : '选择上级',
		url : sy.contextPath + '/securityJsp/base/EmpTree.jsp?method=leaderTreeGrid',
		height:540,
		buttons : [ {
			text : '确认',
			handler : function() {
				//dialog.find('iframe').get(0).contentWindow.sendParam(id,pageName,tStr,cStr,sqlStr,arrayEn,arrayCn,arrayType,arraySql);
				//dialog.find('iframe').get(0).contentWindow.submitForm(dialog, parent.$, parent.mainMenu);
			var array=dialog.find('iframe').get(0).contentWindow.saveChange(dialog);
			if(array.length>0){
			$("#leaderName").val(array[1]);
			$("input[name='data.empData.empId']").val(array[0]);
			}
			}
		} ]
		
	});
	}
</script>
</head>
<body>
	<form method="post" class="form">
		<fieldset>
			<legend>基本信息</legend>
			<table class="table" style="width: 100%;">
			
			        <tr >
					<th>工号</th>
					<td><input name="data.empId"  value="<%=id%>" class="easyui-validatebox" value="" style="width: 200px" data-options="required:true" /></td>
					<th>姓名</th>
					<td><input name="data.name" class="easyui-validatebox" style="width: 200px" data-options="required:true" /></td>
					
					</tr>
				
				<tr>
					<th>性别</th>
					<td>
					<select name="data.sex" class="easyui-combobox" style="width: 204px;" panelHeight="100"/>
					<option value="1">男</option>   
                    <option value="0">女</option>
					</select>
					</td>
					<th>身高</th>
					<td><input name="data.hight" style="width: 200px"/></td>
					
				</tr>
				
				<tr>
					
					<th>血型</th>
					<td><input name="data.blood" style="width: 200px" /></td>
					<th>籍贯</th>
					<td><input name="data.home" style="width: 200px" /></td>
				</tr>
				
				<tr>
				
				
				<tr>
					<th>电话</th>
					<td><input name="data.mobile" style="width: 200px" class="" data-options="required:false" /></td>
					<th>出生日期</th>
					<td><input id="birthday" class="easyui-datebox" name="data.birthday" editable="false" style="width: 204px"data-options="required:false" /></td>
				</tr>
				
				<tr>
					<th>政治面貌</th>
					<td><select name="data.political" editable="false" data-options="required:true"class="easyui-combobox" style="width: 204px;" panelHeight="100"/>
					<option value="1">党员</option>   
                    <option value="2">团员</option>
                    <option value="3">群众</option>
                    <option value="4">其他</option>
					</select></td>
					<th>入职日期</th>
						<td><input id="indate" class="easyui-datebox" name="data.indate"  editable="false" style="width: 204px"data-options="required:true"/></td>
				    
				</tr>
				<tr>
					<th>住址</th>
					<td><input name="data.address" style="width: 200px"class="easyui-validatebox" data-options="required:false"  /></td>
					<th>住址电话</th>
					<td><input name="data.phone" style="width: 200px"/></td>
				</tr>
				
				<tr>
					<th>劳工合同</th>
					<td><input name="" style="width: 200px" /></td>
					<th>体检表</th>
					<td><input name="" style="width: 200px"/></td>
				</tr>
				
				<tr>
				<th>公司</th>
				<td>
				
				<input style="width: 80%" id="orgName" readonly="readonly" class="easyui-validatebox" data-options="required:true" />&nbsp;<img class="iconImg ext-icon-zoom" title="选择" onclick="orgChange();"/>
				<input name="data.orgOrganization.id" type="hidden"/>
				</td>
				<th>部门</th>
				<td>
				<input style="width: 80%"  id="depName" readonly="readonly" class="easyui-validatebox" data-options="required:true" />&nbsp;<img class="iconImg ext-icon-zoom" title="选择" onclick="depChange();"/>
				<input name="data.depOrganization.id" type="hidden"/>
				</td>
<!-- 				<td><select id="dp" name="data.depOrganization.id" class="easyui-combotree" data-options="editable:false,idField:'id',textField:'name',parentField:'pid',url:'<%=contextPath%>/base/syorganization!doNotNeedSecurity_comboTree.sy'" style="width: 200px;"></select></td> -->
<!-- 				<th>直接上级</th> -->
<!-- 				<td><select id="leader" name="data.leaderid" class="easyui-combotree" data-options="editable:false,idField:'empId',textField:'name',parentField:'leaderid',url:'<%=contextPath%>/base/emp-data!doNotNeedSecurity_empComboTree.sy'" style="width: 200px;"></select></td> -->
				</tr>
				<tr>
				<th>岗位</th>
				<td>
				<input style="width: 80%"  id="posName"  readonly="readonly" class="easyui-validatebox" data-options="required:true" />&nbsp;<img class="iconImg ext-icon-zoom" title="选择" onclick="posChange();"/>
				<input name="data.posOrganization.id" type="hidden"/>
				</td>
				<th>上级</th>
				<td>
				<input style="width: 80%"  id="leaderName"  readonly="readonly" class="" data-options="required:true" />&nbsp;<img class="iconImg ext-icon-zoom" title="选择" onclick="leaderChange();"/>
				<input name="data.empData.empId" type="hidden"/>
				</td>
				</tr>

			</table>
		</fieldset>

		<div id="tt" class="easyui-tabs">  
		
		  <div title="学历信息"  style="height: 120px;"> 
		    <table id="xlList" cellspacing="0" cellpadding="0" width="100%" >
            <tr>  
            <th  width="30%" style="text-align:center;">学位</th>
            <th width="30%" style="text-align:center;">学校</th>  
            <th width="30%" style="text-align:center;">毕业时间</th>  
            <td width="10%" style="text-align:center;"><a href="javascript:void(0);" onclick="addTr('xlList')">添加</a>    </td>
            </tr>  
		    </table>
		  </div>
		  <div title="职称信息"  style="height: 120px;">
		    <table id="zcList" cellspacing="0" cellpadding="0" width="100%" > 
            <tr >  
            <th width="30%" style="text-align:center;">职称</th>
            <th width="30%" style="text-align:center;">等级</th>  
            <th width="30%" style="text-align:center;">获得时间</th>  
            <td width="10%" style="text-align:center;"><a href="javascript:void(0);" onclick="addTr('zcList')">添加</a>    </td>
            </tr>  
		    </table>
		  </div>
		  <div title="家庭成员"  style="height: 120px;">
		    <table id="jtcyList" cellspacing="0" cellpadding="0" width="100%" > 
            <tr>  
            <th  width="22%" style="text-align:center;">姓名</th>
            <th  width="22%" style="text-align:center;">关系</th>
            <th  width="22%" style="text-align:center;">工作单位</th>  
            <th width="22%" style="text-align:center;">联系电话</th>  
            <td width="12%" style="text-align:center;"><a href="javascript:void(0);" onclick="addTr('jtcyList')">添加</a>    </td>
            </tr>  
		    </table>
		  </div>
        </div>
	</form>
</body>
</html>