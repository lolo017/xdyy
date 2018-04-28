<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="aisino.reportform.model.base.SessionInfo"%>
<%
	String contextPath = request.getContextPath();
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute("sessionInfo");
%>
<script type="text/javascript" charset="utf-8">

	var lockWindowFun = function() {
		$.post(sy.contextPath
				+ '/base/syuser!doNotNeedSessionAndSecurity_logout.sy',
				function(result) {
					$('#loginDialog').dialog('open');
				}, 'json');
	};
	var logoutFun = function() {
		$.post(sy.contextPath
				+ '/base/syuser!doNotNeedSessionAndSecurity_logout.sy',
				function(result) {
					location.replace(sy.contextPath + '/index.jsp');
				}, 'json');
	};
	var showMyInfoFun = function() {
		var dialog = parent.sy.modalDialog({
			title : '我的信息',
			url : sy.contextPath + '/securityJsp/userInfo.jsp'
		});
	};
</script>
<div style="background-color:#399bff;overflow:hidden;border-width: 0" data-options="fit:true,border:false">
<div style="background-color:#399bff;height:40px;width:100%;;border-width: 0" >
	<!-- img alt="金穗数据仓库平台" style="cursor: default;" src="style/images/bg/logojs.png"> -->
	<a style="cursor: default;color:#ffffff;line-height:40px;font-size:14px;margin-left:50px;">电子发票平台</a>
</div>

<div style="position: absolute; right: 50px; bottom: 5px;font-size:12px">
<!-- 	<a href="javascript:void(0);" class="easyui-menubutton" style="color:#333" -->
<!-- 		data-options="menu:'#layout_north_kzmbMenu',iconCls:'ext-icon-wrench'">控制面板</a> -->
	<a href="javascript:void(0);" class="easyui-menubutton" style="color:#fafafa" data-options="menu:'#layout_north_zxMenu',iconCls:'ext-icon-user'"><%
		if (sessionInfo != null) {
			out.print(aisino.reportform.util.base.StringUtil.formateString("{0}",sessionInfo.getUser().getName()));
		}
	%></a>
</div>
<!-- <div id="layout_north_kzmbMenu" style="width: 100px; display: none;"> -->
<!-- 	<div data-options="iconCls:'ext-icon-user_edit'" -->
<!-- 		onclick="$('#passwordDialog').dialog('open');">修改密码</div> -->
<!-- 	<div class="menu-sep"></div> -->
<!-- 	<div style="color:#333" data-options="iconCls:'ext-icon-user'" onclick="showMyInfoFun();">我的信息</div> -->
<!-- </div> -->
<div id="layout_north_zxMenu" style="width:100px; display: none;">
	<div style="color:#333;" onclick="$('#passwordDialog').dialog('open');">修改密码</div>
	<div style="color:#333;" onclick="lockWindowFun();">锁定窗口</div>
	<div style="color:#333;" onclick="logoutFun();">退出系统</div>
</div>
</div>