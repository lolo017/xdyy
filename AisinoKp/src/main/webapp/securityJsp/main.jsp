<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="aisino.reportform.model.base.SessionInfo"%>
<%
	String contextPath = request.getContextPath();
	SessionInfo sessionInfo = (SessionInfo) session
			.getAttribute("sessionInfo");
%>
<!DOCTYPE html>
<html>
<head>
<title>电子发票平台</title>
<jsp:include page="../inc.jsp"></jsp:include>
<script type="text/javascript">
	var mainMenu;
	var mainTabs;

	$(function() {
		var loginFun = function() {
			if ($('#loginDialog form').form('validate')) {
				$('#loginBtn').linkbutton('disable');
				$.post(sy.contextPath + '/base/syuser!doNotNeedSessionAndSecurity_login.sy',
						$('#loginDialog form').serialize(), function(result) {
							if (result.success) {
								$('#loginDialog').dialog('close');
							} else {
								$.messager.alert('提示', result.msg, 'error',
										function() {
											$('#loginDialog form :input:eq(1)')
													.focus();
										});
							}
							$('#loginBtn').linkbutton('enable');
						}, 'json');
			}
		};
		$('#loginDialog').show().dialog({
			modal : true,
			closable : false,
			iconCls : 'ext-icon-lock_open',
			width:600,
			height:400,
			buttons : [ {
				id : 'loginBtn',
				text : '登录',
				handler : function() {
					loginFun();
				}
			} ],
			onOpen : function() {
				$('#loginDialog form :input[name="data.pwd"]').val('');
				$('form :input').keyup(function(event) {
					if (event.keyCode == 13) {
						loginFun();
					}
				});
			}
		}).dialog('close');

		$('#passwordDialog')
				.show()
				.dialog(
						{
							modal : true,
							closable : true,
							iconCls : 'ext-icon-lock_edit',
							buttons : [ {
								text : '修改',
								handler : function() {
									if ($('#passwordDialog form').form(
											'validate')) {
										$
												.post(
														sy.contextPath
																+ '/base/syuser!doNotNeedSecurity_updateCurrentPwd.sy',
														{
															'data.pwd' : $(
																	'#pwd')
																	.val()
														},
														function(result) {
															if (result.success) {
																$.messager
																		.alert(
																				'提示',
																				'密码修改成功！',
																				'info');
																$(
																		'#passwordDialog')
																		.dialog(
																				'close');
															}
														}, 'json');
									}
								}
							} ],
							onOpen : function() {
								$('#passwordDialog form :input').val('');
							}
						}).dialog('close');
		
		//强制修改初始密码
		$('#firstDialog').show().dialog({
			modal : true,
			closable : false,
			iconCls : 'ext-icon-lock_open',
			buttons : [ {
					id : 'logoutBtn',
					text : '退出',
					handler : function() {
						$.post(sy.contextPath
								+ '/base/syuser!doNotNeedSessionAndSecurity_logout.sy',
								function(result) {
									location.replace(sy.contextPath + '/index.jsp');
								}, 'json');
					}
			},
			{
				id : 'loginBtn',
				text : '修改',
				handler : function() {
					firstUpdate();
				}}],
			onOpen : function() {

			}
		}).dialog('close');
		//强制完善基本信息empdata
		$('#empDataDialog').show().dialog({
			modal : true,
			closable : false,
			iconCls : 'ext-icon-lock_open',
			buttons : [ {
					id : 'logoutBtn',
					text : '退出',
					handler : function() {
						location.replace(sy.contextPath + '/index.jsp');
					}
			},
			{
				id : 'loginBtn',
				text : '确定',
				handler : function() {
					$.post(sy.contextPath
							+ '/base/emp-data!doNotNeedSecurity_updateComplete.sy',sy.serializeObject($('#empForm')),
							function(result) {
								if(result.success){
									$('#empDataDialog').dialog('close');
									location.replace(sy.contextPath + '/index.jsp');
								}
							}, 'json');
				}}],
			onOpen : function() {

			}
		}).dialog('close');
		
		mainMenu = $('#mainMenu').tree({
							url : sy.contextPath + '/base/syresource!doNotNeedSecurity_getMainMenu.sy',
							parentField : 'pid',
							onClick : function(node) {
								if (node.attributes.url) {
									var src = sy.contextPath
											+ node.attributes.url;
									if (!sy.startWith(node.attributes.url, '/')) {
										src = node.attributes.url;
									}
									if (node.attributes.target
											&& node.attributes.target.length > 0) {
										window
												.open(src,
														node.attributes.target);
									} else {
										var tabs = $('#mainTabs');
										var opts = {
											id:node.id,
											title : node.text,
											closable : true,
											iconCls : node.iconCls,
											content : sy
													.formatString(
															'<iframe src="{0}" allowTransparency="true" style="border:0;width:100%;height:99%;" frameBorder="0"></iframe>',
															src),
											border : false,
											fit : true
										};
										if (tabs.tabs('exists', opts.title)) {
											
											tabs.tabs('select', opts.title);
											//如果点击后存在名称相同但ID不同的资源页面，则跳转并刷新
											if(node.id!=tabs.tabs('getSelected').panel('options').id){
												tabs.tabs('update', {tab: tabs.tabs('getTab', opts.title), options: {id:node.id,iconCls : node.iconCls,content: sy.formatString('<iframe src="{0}" allowTransparency="true" style="border:0;width:100%;height:99%;" frameBorder="0"></iframe>',src), closable: true}}); 
											}
										
										} else {
											tabs.tabs('add', opts);
										}
									}
								}
							}
						});

		$('#mainLayout').layout('panel', 'center').panel(
				{
					onResize : function(width, height) {
						sy.setIframeHeight('centerIframe',
								$('#mainLayout').layout('panel', 'center')
										.panel('options').height - 5);
					}
				});

		mainTabs = $('#mainTabs').tabs({
							tabPosition : 'top',
							fit : true,
							border : false,
							tools : [
// 									{
// 										iconCls : 'ext-icon-arrow_up',
// 										handler : function() {
// 											mainTabs.tabs({
// 												tabPosition : 'top'
// 											});
// 										}
// 									},
// 									{
// 										iconCls : 'ext-icon-arrow_left',
// 										handler : function() {
// 											mainTabs.tabs({
// 												tabPosition : 'left'
// 											});
// 										}
// 									},
// 									{
// 										iconCls : 'ext-icon-arrow_down',
// 										handler : function() {
// 											mainTabs.tabs({
// 												tabPosition : 'bottom'
// 											});
// 										}
// 									},
// 									{
// 										iconCls : 'ext-icon-arrow_right',
// 										handler : function() {
// 											mainTabs.tabs({
// 												tabPosition : 'right'
// 											});
// 										}
// 									},
									{
// 										text : '刷新',
										iconCls : 'ext-icon-arrow_refresh',
										handler : function() {
											var panel = mainTabs.tabs(
													'getSelected').panel(
													'panel');
											var frame = panel.find('iframe');
											try {
												if (frame.length > 0) {
													for (var i = 0; i < frame.length; i++) {
														frame[i].contentWindow.document
																.write('');
														frame[i].contentWindow
																.close();
														frame[i].src = frame[i].src;
													}
													if (navigator.userAgent
															.indexOf("MSIE") > 0) {// IE特有回收内存方法
														try {
															CollectGarbage();
														} catch (e) {
														}
													}
												}
											} catch (e) {
											}
										}
									},
									{
// 										text : '关闭',
										iconCls : 'ext-icon-cross',
										handler : function() {
											var index = mainTabs
													.tabs(
															'getTabIndex',
															mainTabs.tabs('getSelected'));
											var tab = mainTabs.tabs('getTab',
													index);
											if (tab.panel('options').closable) {
												mainTabs.tabs('close', index);
											} else {
// 												$.messager.alert('提示','['+ tab.panel('options').title + ']不可以被关闭！','error');
												$.messager.show({
													title:'提示',
													msg:'主页不可以被关闭！',
													showType : 'slide',
													timeout : 2000,
													style : {
														right : '',
														top : document.body.scrollTop + document.documentElement.scrollTop,
														bottom : ''
													}
												});
											}
										}
									} ]
						});
		$("#menuPanel").css({"background-color":"#3d464d","color":"#efefef","border-color":"#3d464d"});//,"overflow":"hidden"
	});
	
	function loadTree(id){
		mainMenu = $('#mainMenu')
			.tree(
					{
						url : sy.contextPath
								+ '/base/syresource!doNotNeedSecurity_getMainMenu.sy',
						parentField : 'pid',
						onClick : function(node) {
							if (node.attributes.url) {
								var src = sy.contextPath
										+ node.attributes.url;
								if (!sy.startWith(node.attributes.url, '/')) {
									src = node.attributes.url;
								}
								if (node.attributes.target
										&& node.attributes.target.length > 0) {
									window
											.open(src,
													node.attributes.target);
								} else {
									var tabs = $('#mainTabs');
									var opts = {
										id:node.id,
										title : node.text,
										closable : true,
										iconCls : node.iconCls,
										content : sy
												.formatString(
														'<iframe src="{0}" allowTransparency="true" style="border:0;width:100%;height:99%;" frameBorder="0"></iframe>',
														src),
										border : false,
										fit : true
									};
									if (tabs.tabs('exists', opts.title)) {
										tabs.tabs('select', opts.title);
										//如果点击后存在名称相同但ID不同的资源页面，则跳转并刷新
										if(node.id!=tabs.tabs('getSelected').panel('options').id){
											tabs.tabs('update', {tab: tabs.tabs('getTab', opts.title), options: {id:node.id,iconCls : node.iconCls,content: sy.formatString('<iframe src="{0}" allowTransparency="true" style="border:0;width:100%;height:99%;" frameBorder="0"></iframe>',src), closable: true}}); 
										}
									
									} else {
										tabs.tabs('add', opts);
									}
								}
							}
						}
					});
	}
	function reloadTree(){
		$("#mainMenu").tree("options").url=sy.contextPath+ '/base/syresource!doNotNeedSecurity_getMainMenu.sy';
		$("#mainMenu").tree('reload');
	}
	
	function firstUpdate(){
		if ($('#firstDialog form').form('validate')) {
	       $.post(sy.contextPath+ '/base/syuser!doNotNeedSecurity_firstUpdatePwd.sy',
		     {'data.pwd' : $('#newPwd').val()},
					function(result) {if (result.success) {
							$.messager.alert('提示','密码修改成功！','info');
							$('#firstDialog').dialog('close');
						}
					}, 'json');
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
				$("#org").val(array[1]);
				$("input[name='orgId']").val(array[0]);
				$("#org").validatebox('validate');
				//清空部门，岗位
				$("#dep").val("");
				$("input[name='depId']").val("");
				$("#pos").val("");
				$("input[name='posId']").val("");
				}
				}
			} ]
			
		});
		}
		function depChange(){
			var dialog = parent.sy.modalDialog({
				title : '选择部门',
				url : sy.contextPath + '/securityJsp/base/EmpTree.jsp?method=depTreeGrid&id='+$("input[name='orgId']").val(),
				height:540,
				buttons : [ {
					text : '确认',
					handler : function() {
						//dialog.find('iframe').get(0).contentWindow.sendParam(id,pageName,tStr,cStr,sqlStr,arrayEn,arrayCn,arrayType,arraySql);
						//dialog.find('iframe').get(0).contentWindow.submitForm(dialog, parent.$, parent.mainMenu);
					var array=dialog.find('iframe').get(0).contentWindow.saveChange(dialog);
					if(array.length>0){
					$("#dep").val(array[1]);
					$("input[name='depId']").val(array[0]);
					$("#dep").validatebox('validate');
					//清空岗位
					$("#pos").val("");
					$("input[name='posId']").val("");
					}
					}
				} ]
				
			});
			}
		function posChange(){
			var dialog = parent.sy.modalDialog({
				title : '选择岗位',
				url : sy.contextPath + '/securityJsp/base/EmpTree.jsp?method=posTreeGrid&id='+$("input[name='depId']").val(),
				height:540,
				buttons : [ {
					text : '确认',
					handler : function() {
						//dialog.find('iframe').get(0).contentWindow.sendParam(id,pageName,tStr,cStr,sqlStr,arrayEn,arrayCn,arrayType,arraySql);
						//dialog.find('iframe').get(0).contentWindow.submitForm(dialog, parent.$, parent.mainMenu);
					var array=dialog.find('iframe').get(0).contentWindow.saveChange(dialog);
					if(array.length>0){
					$("#pos").val(array[1]);
					$("input[name='posId']").val(array[0]);
					$("#pos").validatebox('validate');
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
					$("#leader").val(array[1]);
					$("input[name='leaderId']").val(array[0]);
					$("#leader").validatebox('validate');
					}
					}
				} ]
				
			});
			}
</script>
<style>
.panel-header
{
background-color:#3186dc;
border-color:#3186dc;
}
</style>
</head>
<body id="mainLayout" class="easyui-layout" >
	<div data-options="region:'north',href:'<%=contextPath%>/securityJsp/north.jsp',border:false"
		style="height: 40px; overflow: hidden;background-color:#fafafa"></div>
	<div id="menuPanel" data-options="region:'west',href:'',split:true,collapsed:false,
	tools:[{text:'关闭aaa',iconCls:'ext-icon-arrow_undo',handler:function(){reloadTree();}}]" 
		title="导航" style="width:170px;padding: 0;">
		<ul id="mainMenu"></ul>
	</div>
	<div data-options="region:'center'" style="overflow: hidden;padding: 0">
		<div id="mainTabs">
			<div title="<%
		if (sessionInfo != null) {
			out.print(aisino.reportform.util.base.StringUtil.formateString("欢迎：{0}！",sessionInfo.getUser().getName()));
		}
	%>" data-options="iconCls:'ext-icon-user_gray'">
				<iframe src="<%=contextPath%>/WelcomeCenter.jsp" allowTransparency="true"
					style="border: 0; width: 100%; height: 99%;" frameBorder="0"></iframe>
			</div>
		</div>
	</div>
	<!-- div
		data-options="region:'south',href:'<%=contextPath%>/securityJsp/south.jsp',border:false"
		style="height: 30px; overflow: hidden;"></div> -->

	<div id="loginDialog" title="解锁登录" style="display: none;">
		<form method="post" class="form" onsubmit="return false;">
			<table class="table">
				<tr>
					<th width="50">登录名</th>
					<td><%=sessionInfo.getUser().getLoginname()%><input
						name="data.loginname" readonly="readonly" type="hidden"
						value="<%=sessionInfo.getUser().getLoginname()%>" /></td>
				</tr>
				<tr>
					<th>密码</th>
					<td><input name="data.pwd" type="password"
						class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
			</table>
		</form>
	</div>

	<div id="passwordDialog" title="修改密码" style="display: none;">
		<form method="post" class="form" onsubmit="return false;">
			<table class="table">
				<tr>
					<th>新密码</th>
					<td><input id="pwd" name="data.pwd" type="password"
						class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>重复密码</th>
					<td><input type="password" class="easyui-validatebox"
						data-options="required:true,validType:'eqPwd[\'#pwd\']'" /></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="firstDialog" title="您还是初始密码，请修改！" style="display: none;">
		<form method="post" class="form" onsubmit="return false;">
			<table class="table">
				<tr>
					<th>新密码</th>
					<td><input id="newPwd" name="data.pwd" type="password"
						class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>重复密码</th>
					<td><input type="password" class="easyui-validatebox"
						data-options="required:true,validType:'eqPwd[\'#newPwd\']'" /></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="empDataDialog" title="您的基本信息不完整，请补充后再进行填写！" style="display: none;">
		<form id="empForm" method="post" class="form" onsubmit="return false;">
			<table class="table">
			<tr>
			<td>
			<input type="hidden" name="empId" id="empId"/>
			</td>
			</tr>
				<tr>
					<th>公司</th>
					<td><input id="org" readonly="readonly" class="easyui-validatebox" data-options="required:true" />
					<input id="orgId" name="orgId" type="hidden"/>
					<img class="iconImg ext-icon-zoom" title="选择" onclick="orgChange();"/>
					</td>
				</tr>
				<tr>
					<th>部门</th>
					<td><input id="dep" readonly="readonly" class="easyui-validatebox" data-options="required:true" />
					<input id="depId"name="depId" type="hidden"/>
					<img class="iconImg ext-icon-zoom" title="选择" onclick="depChange();"/>
					</td>
				</tr>
				<tr>
					<th>岗位</th>
					<td><input id="pos" readonly="readonly"  class="easyui-validatebox" data-options="required:true" />
					<input id="posId"name="posId" type="hidden"/>
					<img class="iconImg ext-icon-zoom" title="选择" onclick="posChange();"/>
					</td>
				</tr>
				<tr>
					<th>上级</th>
					<td><input id="leader" readonly="readonly"  class="easyui-validatebox" data-options="required:true" />
					<input id="leaderId"name="leaderId" type="hidden"/>
					<img class="iconImg ext-icon-zoom" title="选择" onclick="leaderChange();"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>