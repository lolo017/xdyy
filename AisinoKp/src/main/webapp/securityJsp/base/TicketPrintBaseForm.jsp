<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="aisino.reportform.util.base.TicketTaoDa"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%
	String contextPath = request.getContextPath();
	String id = request.getParameter("id");
	if (id == null) {
		id = "";
	}
	
	String method = request.getParameter("method");
	if (method == null) {
		method = "";
	}
	
	List<Map>iList=new ArrayList();
	iList=TicketTaoDa.LoadTaoDaGS(method);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <jsp:include page="../../inc.jsp"></jsp:include>
    <title>My JSP 'TicketPrintBaseForm' starting page</title>
    <script type="text/javascript">
	var submitForm = function($dialog, $grid, $pjq) {
		$("#page1").jqprint();
	};

	$(function() {
		var method='<%=method%>';
		var url=sy.contextPath + '/base/ticket-store!doNotNeedSessionAndSecurity_getInvoiceDetailbyId.sy?fpid=<%=id%>';
		parent.$.messager.progress({
			text : '数据加载中....'
		});
		$.post(url, {}, function(result) {
			if (result != undefined) {
				var htmlstr='';
				<%
				for(int i=0;i<iList.size();i++)
				{
					if(iList.get(i).get("DAODAID").equals("head"))
					{
						%>
						htmlstr+="<div style='<%=iList.get(i).get("STYLE") %>'>"+result[0].<%=iList.get(i).get("RESULTNAME") %>+"</div>"
						<%
					}
				}
				%>
				
				for(var i=0;i<result.length;i++)
				{
					<%
					for(int i=0;i<iList.size();i++)
					{
						if(iList.get(i).get("DAODAID").equals("body"))
						{
							%>
							htmlstr+="<div style='<%=iList.get(i).get("STYLE") %>'>"+result[i].<%=iList.get(i).get("RESULTNAME") %>+"</div>"
							<%
						}
					}
					%>
				}
				
				// "<div style='position:absolute;font-size:14px;font-family:simsun;top:115px;left:90px'>"+result.FPDM+"</div>"
				$("#page1").html(htmlstr);
				}
			parent.$.messager.progress('close');
		}, 'json');
	});
    </script>
  </head>
  
  <body>
  <img src="../../img/demo.jpg" style='position:absolute;'/>
    <div id="page1">
		
	</div>
  </body>
</html>
