<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="aisino.reportform.util.base.SecurityUtil"%>
<%@ page import="aisino.reportform.model.base.SessionInfo"%>
<%
	String contextPath = request.getContextPath();
	SecurityUtil securityUtil = new SecurityUtil(session);
	//UserInfo userInfo = (UserInfo) application.getAttribute("userInfo");
	SessionInfo sessionInfo = (SessionInfo) session
			.getAttribute("sessionInfo");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="../../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var grid;	//显示列表
	var dialog;	//对话框
	 function edit(id){
		 var dialog = parent.sy.modalDialog({
				title : '编辑购方信息',
				url : sy.contextPath + '/securityJsp/base/aisinoKp/BuyerInfoForm.jsp?id=' + id,
				buttons : [ {
					text : '保存',
					handler : function() {
						dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
					}
				} ],
				height : 460,
				width : 760,
			});
	}
	 function addFun() {
		var dialog = parent.sy.modalDialog({
			title : '添加购方信息',
			url : sy.contextPath + '/securityJsp/base/aisinoKp/BuyerInfoForm.jsp',
			buttons : [ {
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ],
			height : 460,
			width : 760,
		});
	};
	function remove_buyer(id){
		parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/base/fpmng/buyerinfo!delete.sy', {id:id},function(result){
					if(result.success){
						alert(result.msg);
						grid.datagrid('reload');
					}
				},'json');
			}
		});
		
	}
	$(function() {
		
		//grid加载数据
		grid = $('#grid').datagrid({
			title : '',
			url : sy.contextPath
					+ '/base/fpmng/buyerinfo!infoGrid.sy',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : false,
			idField : 'id',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 5, 10, 15, 20, 30, 50, 100, 200, 300,
					500, 1000 ],
			frozenColumns : [ [
					{
						field : 'checkop',
						title : '<input id=\"checkAll\" type=\"checkbox\"  >',
						width : 30,
						formatter : function(value, row) {
							return "<input type=\"checkbox\"  name=\"check\" value=\"" + row.id + "\" >";
						}
					},{
						width : '40',
						title : '操作',
						field : 'action',
						align : 'center',
						formatter : function(value, row) {
							var str = '';
								str += sy
									.formatString(
											'<img class="iconImg ext-icon-report_edit" title="编辑" onclick="edit(\'{0}\');"/>',
											row.id);
								str += sy.formatString('<img class="iconImg ext-icon-delete" title="删除" onclick="remove_buyer(\'{0}\');"/>',
										row.id)
							return str;
						}
					}
					] ],
					
			columns : [ [  {
				width : '140',
				title : '购方号码',
				field : 'gfhm',
				halign : 'center',
				sortable : true
			}
			,{
				width : '160',
				title : '购方名称',
				field : 'gfmc',
				halign : 'center',
				
			}, {
				width : '180',
				title : '购方税号',
				field : 'gfsh',
				halign : 'center'
			}, {
				width : '240',
				title : '地址电话',
				field : 'dzdh',
				halign : 'center',
				//sortable : true
			}, {
				width : '240',
				title : '银行账户',
				field : 'yhzh',
				halign : 'center',
				//sortable : true
			}, {
				width : '100',
				title : '手机号',
				field : 'mobile',
				halign : 'center',
				//sortable : true
			}, {
				width : '150',
				title : '邮箱',
				field : 'email',
				halign : 'center',
				//sortable : true
			}] ],
			toolbar : '#toolbar',
			onBeforeLoad : function(data) {
				parent.$.messager.progress({
					text : '数据加载中....'
				});
				
			},
			onClickRow:function(rowIndex,rowData){
				$(this).datagrid('unselectRow',rowIndex);
			},
			onLoadSuccess : function(data) {
				//console.log(data);
				$('.iconImg').attr('src', sy.pixel_0);
				parent.$.messager.progress('close');
				if(data.total==0){
					$.messager.alert('提示',
							"没有数据",
							'info');
				}
				
				//checkbox全选
				$("#checkAll").on("click",function () {
					console.log($(this).is(':checked'));
			        if ($(this).is(':checked')) {	
			            $("input[name='check']").prop("checked", true);
			        } else {
			            $("input[name='check']").prop("checked", false);
			        }
				});
			}, //end onLoadSuccess;
			
		});
			
	});
	
	
	
	
	
</script>
<title>购方信息管理</title>
</head>

<body class="easyui-layout" data-options="fit:true,border:false" style="overflow:auto;">
	<div id="toolbar" style="display: none;">
		<form id="searchForm" method="post">
			<table>
				<tr>
					<%-- <%if (securityUtil.havePermission("/base/syrole!save")) {%><%}%> --%>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
				
					<td><div class="datagrid-btn-separator"></div></td>
					<!-- <td><label>购方名称:</label></td> -->
					<td><input id="gfmc" name="gfmc" style="width: 120px;" placeholder="请输入购方名称" /></td>
					<td><div class="datagrid-btn-separator"></div></td>
					
					<!-- <td><label>购方税号:</label></td> -->
					<td><input id="gfsh" name="gfsh" style="width: 120px;" placeholder="请输入购方税号" /></td>
					<td><div class="datagrid-btn-separator"></div></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-magnifier',plain:true" onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')))">查询</a></td>
				</tr>
				
			</table>

		</form>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>
