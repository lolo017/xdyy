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
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : sy.contextPath + '/base/business!doNotNeedSecurity_businessList.sy',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'id',
			sortName : 'SJ',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			frozenColumns : [ [ {
				width : '100',
				title : '序   号',
				field : 'XH',
				sortable : true
			}, {
				width : '80',
				title : '客户端ID',
				field : 'KHDID',
				sortable : true
			}, 
			 {
				width : '150',
				title : '公司名称',
				field : 'companyname',
				sortable : true
			}, 
			{
				width : '150',
				title : '联系人',
				field : 'contacts',
				sortable : true
			},
			{
				width : '150',
				title : '联系电话',
				field : 'tel',
				sortable : true
			},
			{
				width : '150',
				title : '文件名字',
				field : 'filename',
				sortable : true
			},
			{
				width : '150',
				title : '上传文件时间',
				field : 'SJ',
				sortable : true
			},
			{
				title : '操作',
				field : 'action',
				width : '120',
				formatter : function(value, row) {
					var	str = sy.formatString("<img class=\"iconImg ext-icon-book_link\" title=\"下  载\" onclick=\"showFun('" + row.XH +"','" + row.KHDID +"');\"/>");
					return str;
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
	var showFun = function(xh,khdid) {
		var	url = sy.contextPath + '/base/business!doNotNeedSecurity_downFile.sy?xh=' + xh + '&khdid=' + khdid;
		downloadFile(url);
	};
	function downloadFile(url) {   
        try{ 
            var elemIF = document.createElement("iframe");   
            elemIF.src = url;   
            elemIF.style.display = "none";   
            document.body.appendChild(elemIF);   
        }catch(e){ 
 
        } 
    }
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div id="toolbar" style="display: none;">
		<table>
			<tr>
				<td>
				</td>
			</tr>
			
		</table>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>