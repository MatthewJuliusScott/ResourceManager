<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
	<head>
		<title>Index</title>
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
	</head>
	
	<body>
	
		<jsp:include page="/WEB-INF/views/includes/nav.jsp" />
		
		<div class="container-fluid">
			<h1>Resource Manager</h1>
			<a class="btn btn-primary" href="/ResourceManager/projects" role="button">Projects</a>
			<a class="btn btn-primary" href="/ResourceManager/resources" role="button">Resources</a>
			<a class="btn btn-primary" href="/ResourceManager/skills" role="button">Skills</a>
		</div>
	</body>
	
	<footer>
	
	</footer>
	
</html>
