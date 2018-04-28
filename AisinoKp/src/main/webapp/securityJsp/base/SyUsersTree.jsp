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
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var grid;
	var submitNow = function($dialog, $grid, $pjq) {
		var rows = grid.datagrid('getChecked');//得到所有的勾选
		var id="";
		var email="";
		for ( var i = 0; i < rows.length; i++) {
				id+=rows[i].id+"+";
				email+=rows[i].email+"+";		
		}
		
		var pwds=$("#pwds").val();
		var content=$("#content").val();
		var subject=$("#subject").val();
		var config={
				id:id,
				email:email,
				subject:subject,
				content:content,
				pwds:pwds
				
		};
		if ($('form').form('validate')) {
			var url = sy.contextPath + '/base/syuser!sendEmail.sy';
			
			$.post(url, config, function(result) {
				if (result.success) {
					$dialog.dialog('destroy');
					$mainMenu.tree('reload');
				} else {
					$pjq.messager.alert('提示', result.msg, 'error');
				}
			}, 'json');
		}
	};
	
	
	var submitForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {
			
				submitNow($dialog, $grid, $pjq);
			
		}
	};
	
	
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : sy.contextPath + '/base/syuser!grid.sy',
			striped : true,
			rownumbers : true,
			pagination : true,
			//singleSelect : true,
			checkOnSelect : false,
			selectOnCheck : false,
			idField : 'id',
			sortName : 'createdatetime',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			frozenColumns : [ [ {
				width : '100',
				title : 'id',
				field : 'id',
				sortable : true,
				checkbox : true
			}, {
				width : '100',
				title : '姓名',
				field : 'name',
				sortable : true
			} ] ],
			columns : [ [ {
				width : '250',
				title : '邮件地址',
				field : 'email',
				sortable : true	
			}, {
				width : '50',
				title : '性别',
				field : 'sex',
				sortable : true,
				formatter : function(value, row, index) {
					switch (value) {
					case '0':
						return '女';
					case '1':
						return '男';
					}
				}
			}, {
				width : '50',
				title : '年龄',
				field : 'age'
			}, {
				width : '250',
				title : '照片',
				field : 'photo',
				formatter : function(value, row) {
					if(value){
						return sy.formatString('<span title="{0}">{1}</span>', value, value);
					}
				}
			} ] ],
			toolbar : '#toolbar',
			onBeforeLoad : function(param) {
				parent.$.messager.progress({
					text : '数据加载中....'
				});
			},
			onLoadSuccess : function(data) {
				$('.iconImg').attr('src', sy.pixel_0);
				parent.$.messager.progress('close');
			}
		});
	});
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div id="toolbar" style="display: none;">
		<table>
			<tr>
				<td>
					<form id="searchForm">
						<table>
							<tr>
								<td>姓名</td>
								<td><input name="QUERY_t#name_S_LK" style="width: 80px;" /></td>
								<td>性别</td>
								<td><select name="QUERY_t#sex_S_EQ" class="easyui-combobox" data-options="panelHeight:'auto',editable:false"><option value="">请选择</option>
										<option value="1">男</option>
										<option value="0">女</option></select></td>
								
								<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
							</tr>
							<tr>
							
								
								<td>标题</td>
								<td><input type="text" id="subject" style="width: 80px;"/></td>
							
								<td>简介</td>
								<td colspan="3"><input type="text" id="content" style="width:200px;"/></td>
								<td>邮箱密码</td>
								<td><input type="password" id="pwds" style="width: 80px;"/></td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			
		</table>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false">
		</table>
	</div>
</body>
</html>