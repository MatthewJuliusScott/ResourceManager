<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
	<head>
		<title>Index</title>
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
	</head>
	
	<body>
	
		<jsp:include page="/WEB-INF/views/includes/nav.jsp" />
		
		<div class="formContent">
			<h1>Resource Manager</h1>
			<a class="tblButton" href="/projects" role="button">Projects</a>
			<a class="tblButton" href="/resources" role="button">Resources</a>
			<a class="tblButton" href="/skills" role="button">Skills</a>
		</div>
	</body>	
</html>
