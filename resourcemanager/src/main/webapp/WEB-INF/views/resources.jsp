<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
	<head>
		<title>Resources</title>
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
	</head>
	
	<body>
	
		<jsp:include page="/WEB-INF/views/includes/nav.jsp" />
		
		<div class="container-fluid">
			<h1>Resources</h1>
			
			<div class="container-fluid">
				<h2>Create Resource</h2>
				<a class="btn btn-primary" href="/ResourceManager/resources/add" role="button">Create Resource</a>
			</div>
			
			<div class="container-fluid">
				<h2>Resource List</h2>
				<c:if test="${!empty listResources}">
					<table class=".table">
					<tr>
						<th scope="col">Resource ID</th>
						<th scope="col">Name</th>
						<th scope="col">Edit</th>
						<th scope="col">Delete</th>
					</tr>
					<c:forEach items="${listResources}" var="resource">
						<tr>
							<td>${resource.id}</td>
							<td>${resource.name}</td>
							<td><a class="btn btn-primary btn-sm" href="/ResourceManager/resources/edit/${resource.id}" role="button"><i class="far fa-edit"></i></a></td>
							<td><a class="btn btn-danger btn-sm" href="/ResourceManager/resources/delete/${resource.id}" role="button"><i class="fas fa-trash"></i></a></td>
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
