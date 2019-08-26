<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<html>
	<head>
		<title>Error</title>
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
		
		<%
			Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
	    	Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
		%>
	</head>
	
	<body>
		<h1>Error! </h1>
		<h2><div>Status code: <%=statusCode%></div></h2>
		<div>Something went wrong. Please submit a bug report detailing how you got to this screen so we can reproduce and fix the error. %></div>
		<a href="/">Go Home</a>
	</body>
</html>