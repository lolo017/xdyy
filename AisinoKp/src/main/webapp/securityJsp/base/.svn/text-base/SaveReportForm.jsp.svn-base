<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var submitForm = function($dialog,$pjq,$mainMenu) {
		if ($('form').form('validate')) {
			var url;
			if ($(':input[name="data.id"]').val().length > 0) {
				url = sy.contextPath + '/base/report-form!saveSql.sy';
			} else {
				url = sy.contextPath + '/base/report-form!saveSql.sy';
			}
			$.post(url, sy.serializeObject($('form')), function(result) {
				if (result.success) {
					$dialog.dialog('destroy');
					$mainMenu.tree('reload');
				} else {
					$pjq.messager.alert('提示', result.msg, 'error');
				}
			}, 'json');
		}
	};
	
	function sendParam(db,unit,sortOrder,highchartsBtn,tStr,cStr,sqlStr,arrayEn,arrayCn,arrayType,arraySql,arraySeq,arrayUrl,arrayHc,arrayDefault,widthArray,arraySelf,arrayAvg,arraySqlWarn,tableIndex){
		$("#tStr").val(tStr);
		$("#cStr").val(cStr);
		$("#sql").val(sqlStr);
		$("#db").val(db);
		$("#unit").val(unit);
		$("#sortOrder").val(sortOrder);
		$("#highchartsBtn").val(highchartsBtn);
		$("#tableIndex").val(tableIndex);
		//SQL预警
		for(var i=0;i<arraySqlWarn.length;i++){
			for(var j=0;j<arraySqlWarn[i].length;j++){
				if(j==0){
					$("#paramTr").append("<td><textarea  name='sqlList["+i+"].cnEnglish' >"+arraySqlWarn[i][j]+"</textarea></td>");
				}
				if(j==1){
					$("#paramTr").append("<td><textarea  name='sqlList["+i+"].gtSql' >"+arraySqlWarn[i][j]+"</textarea></td>");
				}
				if(j==2){
					$("#paramTr").append("<td><textarea  name='sqlList["+i+"].ltSql' >"+arraySqlWarn[i][j]+"</textarea></td>");
				}
				if(j==3){
					$("#paramTr").append("<td><textarea  name='sqlList["+i+"].color' >"+arraySqlWarn[i][j]+"</textarea></td>");
				}
			}
		}
		//平均值预警
		for(var i=0;i<arrayAvg.length;i++){
			for(var j=0;j<arrayAvg[i].length;j++){
				if(j==0){
					$("#paramTr").append("<td><textarea  name='avgList["+i+"].cnEnglish' >"+arrayAvg[i][j]+"</textarea></td>");
				}
				if(j==1){
					$("#paramTr").append("<td><textarea  name='avgList["+i+"].cnEnglishExc' >"+arrayAvg[i][j]+"</textarea></td>");
				}
				if(j==2){
					$("#paramTr").append("<td><textarea  name='avgList["+i+"].cnEnglishStr' >"+arrayAvg[i][j]+"</textarea></td>");
				}
				if(j==3){
					$("#paramTr").append("<td><textarea  name='avgList["+i+"].color' >"+arrayAvg[i][j]+"</textarea></td>");
				}
			}
		}
		//自定义预警
		for(var i=0;i<arraySelf.length;i++){
			for(var j=0;j<arraySelf[i].length;j++){
				if(j==0){
					$("#paramTr").append("<td><textarea  name='selfList["+i+"].cnEnglish' >"+arraySelf[i][j]+"</textarea></td>");
				}
				if(j==1){
					$("#paramTr").append("<td><textarea  name='selfList["+i+"].gtValue' >"+arraySelf[i][j]+"</textarea></td>");
				}
				if(j==2){
					$("#paramTr").append("<td><textarea  name='selfList["+i+"].ltValue' >"+arraySelf[i][j]+"</textarea></td>");
				}
				if(j==3){
					$("#paramTr").append("<td><textarea  name='selfList["+i+"].color' >"+arraySelf[i][j]+"</textarea></td>");
				}
			}
		}
		
		if($("#paramTr").html()){
		for(var i=0;i<arrayEn.length;i++){
		$("#paramTr").append("<td><textarea  name='conditionList["+i+"].cncondition' >"+arrayCn[i]+"</textarea></td>");
		$("#paramTr").append("<td><textarea  name='conditionList["+i+"].encondition' >"+arrayEn[i]+"</textarea></td>");
		$("#paramTr").append("<td><textarea  name='conditionList["+i+"].conditiontype' >"+arrayType[i]+"</textarea></td>");
		$("#paramTr").append("<td><textarea  name='conditionList["+i+"].conditionsql' >"+arraySql[i]+"</textarea></td>");
		$("#paramTr").append("<td><textarea  name='conditionList["+i+"].seq' >"+arraySeq[i]+"</textarea></td>");
		$("#paramTr").append("<td><textarea  name='conditionList["+i+"].defaultValue' >"+arrayDefault[i]+"</textarea></td>");
		}
		for(var ii=0;ii<arrayUrl.length;ii++){
			if(arrayUrl[ii]==""){
				$("#paramTr").append("<td><textarea  name='urlList["+ii+"]' >"+"empty"+"</textarea></td>");
				
			}else{
			$("#paramTr").append("<td><textarea  name='urlList["+ii+"]' >"+encodeURIComponent(arrayUrl[ii])+"</textarea></td>");
			}
			$("#paramTr").append("<td><textarea  name='widthList["+ii+"]' >"+widthArray[ii]+"</textarea></td>");
			$("#paramTr").append("<td><textarea  name='hcList["+ii+"]' >"+arrayHc[ii]+"</textarea></td>");
			
			}
		}
	}
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
		if ($(':input[name="data.id"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(
							sy.contextPath + '/base/syresource!getById.sy',
							{
								id : $(':input[name="data.id"]').val(),
							},
							function(result) {
								if (result.id != undefined) {
									$('form')
											.form(
													'load',
													{
														'data.id' : result.id,
														'data.name' : result.name,
														'data.url' : result.url,
														'data.syresourcetype.id' : result.syresourcetype.id,
														'data.description' : result.description,
														'data.syresource.id' : result.syresource ? result.syresource.id
																: '',
														'data.iconCls' : result.iconCls,
														'data.seq' : result.seq,
														'data.target' : result.target
													});
									$('#iconCls').attr('class', result.iconCls);//设置背景图标
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
			<legend>资源基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>编号</th>
					<td><input name="data.id" value="<%=id%>" readonly="readonly" /></td>
					<th>资源名称</th>
					<td><input name="data.name" class="easyui-validatebox"
						data-options="required:true" /></td>
				</tr>
				<tr>
					<th>资源路径</th>
					<td><input name="data.url" readonly="readonly"/></td>
					<th>资源类型</th>
					<td><select name="data.syresourcetype.id"
						class="easyui-combobox" readonly="readonly" style="width: 155px;">
						<option value="0">菜单</option>
						</select></td>
				</tr>
				<tr>
					<th>上级资源</th>
					<td><select id="syresource_id" name="data.syresource.id"
						class="easyui-combotree"
						data-options="editable:false,idField:'id',textField:'text',parentField:'pid',url:'<%=contextPath%>/base/syresource!doNotNeedSecurity_getMainMenu.sy'"
						style="width: 155px;"></select><img class="iconImg ext-icon-cross"
						onclick="$('#syresource_id').combotree('clear');" title="清空" /></td>
					<th>资源图标</th>
					<td><input id="iconCls" name="data.iconCls"
						readonly="readonly" style="padding-left: 18px; width: 134px;" /><img
						class="iconImg ext-icon-zoom" onclick="showIcons();" title="浏览图标" />&nbsp;<img
						class="iconImg ext-icon-cross"
						onclick="$('#iconCls').val('');$('#iconCls').attr('class','');"
						title="清空" /></td>
				</tr>
				<tr>
					<th>顺序</th>
					<td><input name="data.seq" class="easyui-numberspinner"
						data-options="required:true,min:0,max:100000,editable:false"
						style="width: 155px;" value="100" /></td>
					<th>目标</th>
					<td><input name="data.target" /></td>
				</tr>
				<tr>
					<th>资源描述</th>
					<td><textarea name="data.description"></textarea></td>
					<th></th>
					<td></td>
				</tr>
				<tr style="display: none">
					<td><textarea id="tStr" name="tNameStr" ></textarea></td>
					<td><textarea id="cStr" name="cNameStr" ></textarea></td>
					<td><textarea id="sql" name="sqlStr" ></textarea></td>
					<td><textarea id="db" name="db" ></textarea></td>
					<td><textarea id="unit" name="unit" ></textarea></td>
					<td><textarea id="highchartsBtn" name="highchartsBtn" ></textarea></td>
					<td><textarea id="sortOrder" name="sortOrder" ></textarea></td>
					<td><textarea id="tableIndex" name="tableIndex" ></textarea></td>
				</tr>
				<tr id="paramTr"style="display: none">
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>