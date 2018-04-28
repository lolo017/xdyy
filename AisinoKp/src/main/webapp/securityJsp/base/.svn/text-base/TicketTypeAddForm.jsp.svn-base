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
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<jsp:include page="../../inc.jsp"></jsp:include>
    <title></title>
    <script type="text/javascript">
	var submitNow = function($dialog, $grid, $pjq) {
		parent.$.messager.progress({
			text : '数据加载中....'
		});
		var url;
		url = sy.contextPath + '/base/ticket-store!doNotNeedSecurity_TicketTypeSave.sy';
		$.post(url, sy.serializeObject($('form')), function(result) {
			if (result.success) {
				$pjq.messager.show({
					title:'提示',
					msg:result.msg,
					showType:'slide',
					timeout:2000,
					style:{
						right:'',
						top:document.body.scrollTop+document.documentElement.scrollTop,
						bottom:''
					}
				});
				$grid.datagrid('load');
				$dialog.dialog('destroy');
			} else {
				$pjq.messager.alert('提示', result.msg, 'error');
			}
		}, 'json');
		parent.$.messager.progress('close');
	};
	var submitForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {
			submitNow($dialog, $grid, $pjq);
		}
	};
	
	$(function() {
		

		if ($(':input[name="ticketType.id"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sy.contextPath + '/base/ticket-store!doNotNeedSecurity_GetEntitleByid.sy', {
				type_id : $(':input[name="ticketType.id"]').val()
			}, function(result) {
				if (result.ID != undefined) {
					$('form').form('load', {
						'ticketType.id' : result.ID,
						'ticketType.flag' : result.FLAG,
						'ticketType.code' : result.CODE,
						'ticketType.name' : result.NAME,
						'type_id': result.ID
						
					});
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
	});
    </script>
  </head>
  
  <body>
    <form method="post" class="form">
	<div class="easyui-panel"  data-options="fit:true,border:false" style="width:200px;padding:20px 60px">
      <div style="margin-bottom:10px">
          <input type="hidden" name="ticketType.id" class="transinput" value="<%=id%>" readonly="readonly" />
          <input type="hidden" name="type_id" class="transinput" readonly="readonly" />
          <input type="hidden" name="ticketType.flag" class="transinput"  readonly="readonly" />
       </div>
       <div style="margin-bottom:10px">
        <div>类型编码</div>
        <input name="ticketType.code" class="easyui-validatebox textbox"  data-options="required:true"  style="width:90%;height:20px">
       </div>
       	<div style="margin-bottom:10px">
       	  <div>类型名称</div>
       	    <input  name="ticketType.name"  class="easyui-validatebox textbox" data-options="required:true"  style="width:90%;height:20px">
       	</div>
     </div>
	</form>
  </body>
</html>
