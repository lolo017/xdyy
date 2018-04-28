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
	var uploader;//上传对象
	var submitNow = function($dialog, $grid, $pjq) {
		var url;
		if ($(':input[name="data.id"]').val().length > 0) {
			url = sy.contextPath + '/base/cuspos!update.sy';
		} else {
			url = sy.contextPath + '/base/cuspos!save.sy';
		}
		$.post(url, sy.serializeObject($('form')), function(result) {
			parent.sy.progressBar('close');//关闭上传进度条

			if (result.success) {
				$pjq.messager.alert('提示', result.msg, 'info');
				$grid.datagrid('load');
				$dialog.dialog('destroy');
			} else {
				$pjq.messager.alert('提示', result.msg, 'error');
			}
		}, 'json');
	};
	var submitForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {
			
				submitNow($dialog, $grid, $pjq);
			
		}
	};
	$(function() {

		if ($(':input[name="data.id"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sy.contextPath + '/base/cuspos!getById.sy', {
				id : $(':input[name="data.id"]').val()
			}, function(result) {
				if (result.id != undefined) {
					$('form').form('load', {
						'data.id' : result.id,
						'data.customeroutname' : result.customeroutname,
						'data.custposcode' : result.custposcode,
						'data.buscode' : result.buscode,
						'data.bustel' : result.bustel,
						'data.poslegalperson' : result.poslegalperson,
						'data.legalcode' : result.legalcode,
						'data.busname' : result.busname,
						'data.outnumer' : result.outnumer,
						'data.personnumber' : result.personnumber,
						'data.mccnumber' : result.mccnumber,
						'data.org' : result.org,
						'data.temp' : result.temp,
						'data.poscount' : result.poscount,
						'data.ser' : result.ser,
						'data.posbranch' : result.posbranch,
						'data.posoperator' : result.posoperator,
						'data.notes' : result.notes,
						'data.isic' : result.isic,
						'data.posproductid' : result.posproductid,
						'data.isjoint' : result.isjoint
						
						
					});
				}
				parent.$.messager.progress('close');
			}, 'json');
		}

	});
</script>
</head>
<body >
	<form method="post" class="form">
		<fieldset>
			<legend>商户基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>编号</th>
					<td><input name="data.id"  placeholder="不需要填写，系统默认" value="<%=id%>" readonly="readonly" /></td>
					<th>商户对外名称</th>
					<td><input name="data.customeroutname" class="easyui-validatebox"
						data-options="required:true" /></td>
				</tr>
				<tr>
					<th>商户编号</th>
					<td><input name="data.custposcode"  class="easyui-validatebox"  validType="number" data-options="required:true"/></td>
					<th>营业执照编号</th>
					<td><input name="data.buscode"  class="easyui-validatebox" data-options="required:true"/></td>
				</tr>

				<tr>
					<th>交易电话</th>
					<td><input name="data.bustel"  class="easyui-validatebox" data-options="required:true"/></td>
					<th>法人</th>
					<td><input name="data.poslegalperson"  class="easyui-validatebox" validType="CHS" data-options="required:true"/></td>
				</tr>
				<tr>
					<th>法人身份证</th>
					<td><input name="data.legalcode"  class="easyui-validatebox"  validType="idCode" data-options="required:true"/></td>
					<th>户名（公/私）</th>
					<td><input name="data.busname"  class="easyui-validatebox" data-options="required:true"/></td>
				</tr>

				<tr>
					<th>对公账号</th>
					<td><input name="data.outnumer"  class="easyui-validatebox" data-options="required:true"/></td>
					<th>个人卡号</th>
					<td><input name="data.personnumber"  class="easyui-validatebox" data-options="required:true"/></td>
				</tr>


				<tr>
					<th>MCC（行业编码）</th>
					<td><input name="data.mccnumber"   class="easyui-validatebox" data-options="required:true"/></td>
					<th>商户拓展归属/网点</th>
					<!--  <td><input name="data.org"  class="easyui-validatebox" data-options="required:true"/></td>-->
					<td><select name="data.org"
						class="easyui-combobox"
						data-options="required:true,editable:false,valueField:'orgId',textField:'orgShortName',url:'<%=contextPath%>/base/sysorg!doNotNeedSecurity_combobox.sy',panelHeight:'auto'"
						style="width: 155px;"></select></td>
				
				</tr>

				<tr>
					<th>拓展人员</th>
					<td><input name="data.temp"   class="easyui-validatebox" data-options="required:true"/></td>
					<th>机具数量</th>
					<td><input name="data.poscount"   class="easyui-validatebox" validType="number" data-options="required:true"/></td>
				</tr>

				<tr>
					<th>服务人员</th>
					<td><input name="data.ser"   class="easyui-validatebox" data-options="required:true"/></td>
					<th>支行/中心支行</th>
					<td><input name="data.posbranch"   class="easyui-validatebox" data-options="required:true"/></td>
				</tr>

				<tr>
					<th>经办人</th>
					<td><input name="data.posoperator"   class="easyui-validatebox" data-options="required:true"/></td>
					<th>备注</th>
					<td><input name="data.notes"  class="easyui-validatebox" data-options="required:true"/></td>
				</tr>

				<tr>
					<th>是否受理IC卡</th>
					<td><select class="easyui-combobox" name="data.isic"  data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value="1">是</option>
							<option value="0">否</option>
					</select></td>
					<th>出机方/进件产品</th>
					<td><input name="data.posproductid"  class="easyui-validatebox"  data-options="required:true"/></td>
				</tr>
				
				<tr>
					<th>是否联营  </th>
					<td><select class="easyui-combobox" name="data.isjoint" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value="1">是</option>
							<option value="0">否</option>
					</select></td>
					<th></th>
					<td></td>
				</tr>

			
			</table>
		</fieldset>
	</form>
</body>
</html>