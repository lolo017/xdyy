<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="aisino.reportform.model.base.SessionInfo"%>

<%
	String contextPath = request.getContextPath();
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute("sessionInfo");
	int isPwd=sessionInfo.getUser().getIsPwd();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<jsp:include page="inc.jsp"></jsp:include>
    <title>电子发票平台</title>
    <script>
    $(function(){
//     	parent.$.messager.show({
// 			title : '请及时打印',
// 			height:'200px',
// 			msg : '《每日票证使用及征收数据明细表》',
// 			showType : 'show',
// 			timeout:10000
// 		});
    });
    </script>
  </head>
  
  <body class="easyui-layout" >
  <div style="margin:0 50px auto 50px">
  <br/>
    <a>欢迎使用电子发票平台</a> <hr />
    <div style="width:100%;font-size:14px">
    <%
		if (sessionInfo != null) {
			out.print(aisino.reportform.util.base.StringUtil.formateString("欢迎：{0}！",sessionInfo.getUser().getName()));
		}
	%>
	<br>
	<div style="text-align:center"><a style="color:#aa0000">请勿测试！</a></div>
    </div>
    
    <div style="text-align:center">
   	<img src="./img/guide.png" style="cursor:default"></img>
   	 <div>
    	如果无法打印发票，请下载&nbsp;<a style="color:#aa0000;cursor:pointer">Adobe Reader</a>
    </div>
    </div>
    </div>
    
    <div data-options="region:'south',href:'<%=contextPath%>/securityJsp/south.jsp',border:false"
		style="height: 40px;overflow: hidden;"></div>
  </body>
</html>
