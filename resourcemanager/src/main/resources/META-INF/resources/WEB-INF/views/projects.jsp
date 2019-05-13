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
	
		<h1>Projects</h1>
		
		<h2>Create Project</h2>
		<a class="btn btn-primary" href="/projects/add" role="button">Create Project</a>
		
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
					<td>${resource.id}</td>
					<td>${resource.name}</td>
					<td><a class="btn btn-primary btn-sm" href="/resources/edit/${resource.id}" role="button">Link</a></td>
					<td><a class="btn btn-primary btn-sm" href="/resources/delete/${resource.id}" role="button">Link</a></td>
				</tr>
			</c:forEach>
			</table>
		</c:if>
	
	</body>
	
	<footer>
		<script>			
			$(function() {   
			    $( "#startDate" ).datepicker({   
			      defaultDate: "+1w",  
			      changeMonth: true,   
			      numberOfMonths: 1,  
			      onClose: function( selectedDate ) {  
			        $( "#endDate" ).datepicker( "option", "minDate", selectedDate );  
			      }
			    });  
			    $( "#startDate" ).datepicker({
			      defaultDate: "+1w",
			      changeMonth: true,
			      numberOfMonths: 1,
			      onClose: function( selectedDate ) {
			        $( "#endDate" ).datepicker( "option", "maxDate", selectedDate );
			      }
			    });  
			  });  
		</script>
	</footer>
	
</html>
