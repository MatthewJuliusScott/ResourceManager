<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
	<head>
		<title>Project</title>
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
	</head>
	
	<body>
	
		<jsp:include page="/WEB-INF/views/includes/nav.jsp" />
		
		<div class="container-fluid">
			<h1>Projects</h1>
			
			<div class="container-fluid">
				<h2>Create Project</h2>
				<a class="btn btn-primary" href="/projects/add" role="button">Create Project</a>
			</div>
			
			<div class="container-fluid">
				<h2>Project List</h2>
				<c:if test="${!empty listProjects}">
					<table class="tg">
					<tr>
						<th width="80">Project ID</th>
						<th width="120">Name</th>
						<th width="60">Edit</th>
						<th width="60">Delete</th>
					</tr>
					<c:forEach items="${listProjects}" var="project">
						<tr>
							<td>${project.id}</td>
							<td>${project.name}</td>
							<td><a class="btn btn-primary btn-sm" href="/projects/edit/${project.id}" role="button"><i class="far fa-edit"></i></a></td>
							<td><a class="btn btn-danger btn-sm" href="/projects/delete/${project.id}" role="button"><i class="fas fa-trash"></i></a></td>
						</tr>
					</c:forEach>
					</table>
				</c:if>
			</div>
		</div>
	</body>
	
	<footer>
	</footer>
	
</html>
