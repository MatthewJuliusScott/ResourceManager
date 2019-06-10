<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
	<head>
		<title>Skills</title>
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
	</head>
	
	<body>
	
		<jsp:include page="/WEB-INF/views/includes/nav.jsp" />
		
		<div class="container-fluid">
			<h1>Skills</h1>
			
			<div class="container-fluid">
				<h2>Create Skill</h2>
				<a class="btn btn-primary" href="/skills/add" role="button">Create Skill</a>
			</div>
			
			<div class="container-fluid">
				<h2>Skills List</h2>
				<c:if test="${!empty listSkills}">
					<table class=".table">
					<tr>s
						<th scope="col">Skill ID</th>
						<th scope="col">Name</th>
						<th scope="col">Edit</th>
						<th scope="col">Delete</th>
					</tr>
					<c:forEach items="${listSkills}" var="skill">
						<tr>
							<td>${skill.id}</td>
							<td>${skill.name}</td>
							<td><a class="btn btn-primary btn-sm" href="/skills/edit/${skill.id}" role="button"><i class="far fa-edit"></i></a></td>
							<td><a class="btn btn-danger btn-sm" href="/skills/delete/${skill.id}" role="button"><i class="fas fa-trash"></i></a></td>
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
