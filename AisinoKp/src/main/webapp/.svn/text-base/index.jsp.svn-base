<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="aisino.reportform.model.base.SessionInfo"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<title>电子发票平台</title>
<jsp:include page="inc.jsp"></jsp:include>
<%
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute("sessionInfo");
	if (sessionInfo != null) {
		request.getRequestDispatcher("/securityJsp/main.jsp").forward(request, response);
	} else {
		request.getRequestDispatcher("/login2.jsp").forward(request, response);
	}
%>
</head>
<body>
</body>
</html>