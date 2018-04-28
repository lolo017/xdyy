<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="aisino.reportform.util.base.SecurityUtil"%>
<%@ page import="aisino.reportform.model.base.SessionInfo"%>
<%@ page import="aisino.reportform.util.base.ConfigUtil"%>

<%
	String contextPath = request.getContextPath();
	SecurityUtil securityUtil = new SecurityUtil(session);
	//UserInfo userInfo = (UserInfo) application.getAttribute("userInfo");
	SessionInfo sessionInfo = (SessionInfo) session
			.getAttribute("sessionInfo");
	String ssflbmbbh = ConfigUtil.get("ssflbmbbh");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="../../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var grid;
	var is_open = 0;
	var activex;
	var ssContent;
	var dialog;
	
	$(function() {
		//grid加载数据
		grid = $('#grid').datagrid({
			title : '',
			url : sy.contextPath
					+ '/base/fpmng/ssflbm!getSsflbmGrid.sy',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : false,
			idField : 'ID',
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
							return "<input type=\"checkbox\"  name=\"check\" value=\"" + row.ID + "\" >";
						}
					},
					{
						width : '60',
						title : '操作',
						field : 'action',
						align : 'center',
						formatter : function(value, row) {
							var str = '';
							<%if (securityUtil.havePermission("/base/fpmng/ssflbm!updateSsflbm")) {%>
								str += sy
									.formatString(
											'<img class="iconImg ext-icon-vcard_edit" title="修改" onclick="updateSsflbm(\'{0}\');"/>',
											row.ID);
 							<%}%>
							<%if (securityUtil.havePermission("/base/fpmng/ssflbm!deleteSsflbm")) {%>
								str += sy
									.formatString(
											'<img class="iconImg ext-icon-vcard_delete" title="删除" onclick="deleteSsflbm(\'{0}\');"/>',
											row.ID);
 							<%}%>
							return str;
						}
					} ] ],
			columns : [ [  {
				width : '160',
				title : '税收分类编码编号',
				field : 'ID',
				halign : 'center',
				//sortable : true,
				hidden : true
			}, {
				width : '180',
				title : '商品号码',
				field : 'SPHM',
				halign : 'center',
			}, {
				width : '200',
				title : '商品名称',
				field : 'SPMC',
				halign : 'center',
				//sortable : true
			}, {
				width : '140',
				title : '规格型号',
				field : 'GGXH',
				halign : 'center',
				//sortable : true
			}, {
				width : '160',
				title : '单位',
				field : 'DW',
				halign : 'center'
			}, {
				width : '60',
				title : '税率',
				field : 'SLV',
				align : 'center',
				//sortable : true
			}, {
				width : '100',
				title : '税收分类编码',
				field : 'SSFLBM',
				halign : 'center',
				//sortable : true
			} ] ],
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
				
				//提示: 本页显示记录存在的负数订单数量, 存在则提示
				var has_f=0;
				for (var rNo=0; rNo < data.rows.length; rNo++) {
					if(data.rows[rNo].TOTAL < 0) {
						has_f++;
					}
				}
				if (has_f > 0 && has_f < data.rows.length ) {
					alert("本页存在"+has_f+"张单据总金额为负!");
				}
				
				//checkbox全选
				$("#checkAll").on("click",function () {
					//console.log($(this).is(':checked'));
			        if ($(this).is(':checked')) {
			            $("input[name='check']").prop("checked", true);
			        } else {
			            $("input[name='check']").prop("checked", false);
			        }
				});
			}, //end onLoadSuccess;
			
		});
			
	});
	
	//新增
	function save() {
		dialog = parent.sy.modalDialog({
			title : '添加税收分类编码',
			url : sy.contextPath + '/securityJsp/base/aisinoKp/SsflbmForm.jsp',
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
	
	//修改
	function updateSsflbm(id){
		//alert(id);
		dialog = parent.sy.modalDialog({
			title : '编辑',
			url : sy.contextPath + '/securityJsp/base/aisinoKp/SsflbmForm.jsp?id=' + id,
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
	
	//删除
	function deleteSsflbm(id){
		parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/base/fpmng/ssflbm!deleteSsflbm.sy', {id:id},function(result){
					if(result.success){
						//alert(result.msg);
						$.messager.alert('提示',result.msg,'info');
						grid.datagrid('reload');
					}
				},'json');
				
			}
		});
		
	}
	
</script>
<title>单据管理</title>
</head>

<body class="easyui-layout" data-options="fit:true,border:false">
	<div id="toolbar" style="display: none;">
		<!-- 查询条件筛选 -->
		<form id="searchForm" method="post">
			<table>
				<tr>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="save();">添加</a></td>
				
					<!-- <td><label>商品号码:</label></td> -->
					<td><input id="sphm" name="sphm" style="width: 120px;" placeholder="请输入商品号码" /></td>
					<td><div class="datagrid-btn-separator"></div></td>
					<!-- <td><label>商品名称:</label></td> -->
					<td><input id="spmc" name="spmc" style="width: 120px;" placeholder="请输入商品名称" /></td>
					<td><div class="datagrid-btn-separator"></div></td>
					
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'ext-icon-zoom',plain:true"
						onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">查询</a>
						<!-- <a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'ext-icon-zoom_out',plain:true"
						onclick="$('#searchForm input').val('');$('#statusselector').val('999');grid.datagrid('load',{});">重置查询</a> -->
					</td>
				</tr>
			</table>
		</form>
		

	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>
