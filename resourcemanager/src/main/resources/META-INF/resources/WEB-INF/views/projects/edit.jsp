<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
	<head>
		<title>Manage Project</title>
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
	</head>
	
	<body>
	
		<jsp:include page="/WEB-INF/views/includes/nav.jsp" />
		
		<div class="container-fluid">
			<h1>Manage Project</h1>
						
			<div class="container-fluid">
				<c:url var="addAction" value="/projects/save" ></c:url>
				<form:form action="${addAction}" modelAttribute="project" method="POST">
				
					<form:hidden path="id" />
								
					<div class="form-group">
						<form:label path="name">
							<spring:message text="Name"/>
						</form:label>
						<form:input path="name" class="form-control"/>
					</div>
					<h2>Requirements</h2>
					<table class="table">
						<tr>
							<th>Id</th>
							<th>Skill</th>
							<th>Allocated To</th>
							<th>Edit</th>
							<th>Delete</th>
						</tr>
						<c:forEach items="${project.allocations}" var="allocation">
							<tr>
								<td>${allocation.id}</td>
								<td>${allocation.skill.name}</td>
								<td>${allocation.resource.name}</td>
								<td><a class="btn btn-primary btn-sm" href="/allocation/edit/${allocation.id}" role="button"><i class="far fa-edit"></i></a></td>
								<td><a class="btn btn-primary btn-sm" href="/allocation/delete/${allocation.id}" role="button"><i class="fas fa-trash"></i></a></
							</tr>
	                	</c:forEach>
                	</table>
					
					<h3>Add Requirement</h3>
					
					<div class="form-group">
						<label for="startDate">Start Date</label>
						<input type="text" class="form-control datepicker" name="startDate" id="startDate">
						<label for="endDate">End Date</label>
						<input type="text" class="form-control datepicker" name="endDate" id="endDate">
						
						<label for="allSkills">Skill</label>
						<select id="allSkills" name="skillId" class="form-control">
							<c:forEach items="${listSkills}" var="skill">
								<option value="${skill.id}">${skill.name}</option>
	                		</c:forEach>
	           			</select>
	           			
	           			<label for="hours">Hours</label>
						<input type="number" class="form-control" name="hours" id="hours">
					</div>
           			
           			<button type="submit" class="btn btn-primary"><i class="far fa-save"></i> Save</button>
           		</form:form>
			</div>
		</div>
	</body>
	
	<footer>
		<script>			
			$(function() {   
			    $( "#startDate" ).datepicker({   
			      defaultDate: "+1w",  
			      changeMonth: true,
			      dateFormat: 'dd/mm/yy',
			      numberOfMonths: 1,
			      onClose: function( selectedDate ) {  
			        $( "#endDate" ).datepicker( "option", "minDate", selectedDate );  
			      }
			    });  
			    $( "#endDate" ).datepicker({
			      defaultDate: "+1w",
			      changeMonth: true,
			      dateFormat: 'dd/mm/yy',
			      numberOfMonths: 1,
			      onClose: function( selectedDate ) {
			        $( "#startDate" ).datepicker( "option", "maxDate", selectedDate );
			      }
			    });  
			  });
		</script>
	</footer>
	
</html>
