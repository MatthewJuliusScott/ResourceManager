<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
	<head>
		<title>Resource Page</title>
		<style type="text/css">
			.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
			.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
			.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
			.tg .tg-4eph{background-color:#f9f9f9}
			td {vertical-align:top;}
			.left-arrow {float:left;}
			.right-arrow {float:right;}
		</style>
		<script src="<c:url value="/resources/js/jquery-3.3.1.min.js" />"></script>
	</head>
	
	<body>
	<c:if test="${!empty resource.name}">
		<h1>
			Edit a Resource
		</h1>
		<div><a href="/resources">Back to Add</a></div>
	</c:if>
	<c:if test="${empty resource.name}">
		<h1>
			Add a Resource
		</h1>
	</c:if>
	
	
	<c:url var="addAction" value="/resource/add" ></c:url>
	
	<form:form action="${addAction}" modelAttribute="resource" method="POST">
	<table>
		<tr>
			<td>
				<table>
					<c:if test="${!empty resource.name}">
					<tr>
						<td>
							<form:label path="id">
								<spring:message text="ID"/>
							</form:label>
						</td>
						<td>
							<form:input path="id" readonly="true" size="8"  disabled="true" />
							<form:hidden path="id" />
						</td> 
					</tr>
					</c:if>
					<tr>
						<td>
							<form:label path="name">
								<spring:message text="Name"/>
							</form:label>
						</td>
						<td>
							<form:input path="name" />
						</td> 
					</tr>
					<tr>
						<td colspan="2">
							<c:if test="${!empty resource.name}">
								<input type="submit"
									value="<spring:message text="Edit Resource"/>" />
							</c:if>
							<c:if test="${empty resource.name}">
								<input type="submit"
									value="<spring:message text="Add Resource"/>" />
							</c:if>
						</td>
					</tr>
				</table>
			</td>
			<td>
				<table>
					<tr>
						<td>
							<table>
								<tr>
									<th>Resource Skills</th>
								</tr>
								<tr>
									<td>
			                  			<form:select path="skills" id="resourceSkills" items="${resource.skills}" onchange="removeSkill(this)" itemValue="id" itemLabel="name" multiple = "true" />
			               			</td>
			               		</tr>
							</table>
						</td>
						<td>
							<table>
								<tr>
									<th>All Skills</th>
								</tr>
								<tr>
									<td>
										<select onchange="addSkill(this)" id="allSkills" multiple = "multiple">
											<c:forEach items="${listSkills}" var="skill">
												<option value="${skill.id}">${skill.name}</option>
					                  		</c:forEach>
				               			</select>
			               			</td>
			               		</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	</form:form>
	<br>
	<h3>Resources List</h3>
	<c:if test="${!empty listResources}">
		<table class="tg">
		<tr>
			<th width="80">Resource ID</th>
			<th width="120">First Name</th>
			<th width="120">Last Name</th>
			<th width="300">Skills</th>
			<th width="60">Edit</th>
			<th width="60">Delete</th>
		</tr>
		<c:forEach items="${listResources}" var="resource">
			<tr>
				<td>${resource.id}</td>
				<td>${resource.firstName}</td>
				<td>${resource.lastName}</td>
				<td><c:forEach items="${resource.skills}" var="skill">${skill.name} </c:forEach></td>
				<td><a href="<c:url value='/edit/${resource.id}' />" >Edit</a></td>
				<td><a href="<c:url value='/remove/${resource.id}' />" >Delete</a></td>
			</tr>
		</c:forEach>
		</table>
	</c:if>
	
	<h1>
		Bookings
	</h1>
	
	<c:if test="${!empty resource.firstName}">
		<h2>Create a Booking</h2>
		<p>Start Date: <input name="startDate" id="startDate" type="text" class="datepicker"></p>
		<p>End Date: <input name="endDate" id="endDate" type="text" class="datepicker"></p>
	</c:if>
	
	</body>
	
	<footer>
		<script>
			function addSkill(selectObject) {
				var optionObject = selectObject.options[selectObject.selectedIndex];
				var resourceSkills = document.getElementById("resourceSkills");
				resourceSkills.add(optionObject);
				for (i = 0; i < resourceSkills.options.length; i++) {
					resourceSkills.options[i].selected = true;
				}
			}
			
			function removeSkill(selectObject) {
				var optionObject = selectObject.options[selectObject.selectedIndex];
				var allSkills = document.getElementById("allSkills");
				allSkills.add(optionObject);
				for (i = 0; i < allSkills.options.length; i++) {
					resourceSkills.options[i].selected = true;
				}
			}
			
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
