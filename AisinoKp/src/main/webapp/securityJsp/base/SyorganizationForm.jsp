<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
var id="<%=id%>";
	var submitForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {
			var url;
			if (id.length > 0) {
				url = sy.contextPath + '/base/syorganization!update.sy';
			} else {
				url = sy.contextPath + '/base/syorganization!save.sy';
			}
			$.post(url, sy.serializeObject($('form')), function(result) {
				if (result.success) {
					$grid.treegrid('reload');
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
					dialog.find('iframe').get(0).contentWindow.selectIcon(dialog, $('#iconCls'));
				}
			} ]
		});
	};
	$(function() {
		if ($(':input[name="data.id"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sy.contextPath + '/base/syorganization!getById.sy', {
				id : $(':input[name="data.id"]').val()
			}, function(result) {
				if (result.id != undefined) {
					$('form').form('load', {
						'data.id' : result.id,
						'data.name' : result.name,
						'data.address' : result.address,
						'data.syorganization.id' : result.syorganization ? result.syorganization.id : '',
						'data.iconCls' : result.iconCls,
						'data.seq' : result.seq,
						'data.code' : result.code,
						'data.levelId' : result.levelId,
						'data.telphone' : result.telphone,
						'data.mobile' : result.mobile,
						'data.tax_code' : result.tax_code,
						'data.bank_account' : result.bank_account,
						'data.org_type' : result.org_type,
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
			<legend>机构基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>编号</th>
					<td><input name="data.id" value="<%=id%>"   readonly="readonly" data-options="required:true" /></td>
					<th>机构名称</th>
					<td><input name="data.name" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>机构编码</th>
					<td><input name="data.code"  readonly="readonly"/></td>
					<th>顺序</th>
					<td><input name="data.seq" class="easyui-numberspinner" data-options="required:true,min:0,max:100000,editable:false" style="width: 155px;" value="100" /></td>
				</tr>
				<tr>
					<th>上级机构</th>
					<td><select id="syorganization_id" name="data.syorganization.id" class="easyui-combotree" data-options="editable:false,idField:'id',textField:'name',parentField:'pid',url:'<%=contextPath%>/base/syorganization!doNotNeedSecurity_comboTree.sy'" style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#syorganization_id').combotree('clear');" title="清空" /></td>
					<th>机构图标</th>
					<td><input id="iconCls" name="data.iconCls" readonly="readonly" style="padding-left: 18px; width: 134px;" /><img class="iconImg ext-icon-zoom" onclick="showIcons();" title="浏览图标" />&nbsp;<img class="iconImg ext-icon-cross" onclick="$('#iconCls').val('');$('#iconCls').attr('class','');" title="清空" /></td>
				</tr>
				<tr>
					<th>机构地址</th>
					<td><input name="data.address" /></td>
					<th>类型</th>
					<td><select name="data.levelId"
						class="easyui-combobox"
						data-options="required:true,editable:false,panelHeight:'10px'"
						style="width: 155px;">
						<option value="1">公司</option>
						<option value="2">部门</option>
						<option value="3">职位</option>
						</select></td>
				</tr>
				<tr>
					<th>电话号码</th>
					<td><input name="data.telphone" class="easyui-validatebox" data-options="validType:['length[0,20]']" /></td>
					<th>手机号码</th>
					<td><input name="data.mobile" class="easyui-validatebox" data-options="required:true, validType:['mobile','length[0,11]']"  /></td>
				</tr>				
				<tr>
					<th>税号</th>
					<td><input name="data.tax_code" class="easyui-validatebox" data-options="required:true, validType:['length[0,40]']" /></td>
					<th>银行账号</th>
					<td><input name="data.bank_account" class="easyui-validatebox" data-options="required:true, validType:['length[0,40]']"  /></td>
				</tr>
				<tr>
					<th>行业分类</th>
					<td><input name="data.org_type" class="easyui-validatebox" data-options="required:true, validType:['length[0,40]']" /></td>
					</tr>
				
			</table>
		</fieldset>
	</form>
</body>
</html>