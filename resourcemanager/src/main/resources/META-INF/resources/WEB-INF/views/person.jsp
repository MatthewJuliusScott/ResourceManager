<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
	<head>
		<title>Person Page</title>
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
	<c:if test="${!empty person.firstName}">
		<h1>
			Edit a Person
		</h1>
		<div><a href="/persons">Back to Add</a></div>
	</c:if>
	<c:if test="${empty person.firstName}">
		<h1>
			Add a Person
		</h1>
	</c:if>
	
	
	<c:url var="addAction" value="/person/add" ></c:url>
	
	<form:form action="${addAction}" modelAttribute="person" method="POST">
	<table>
		<tr>
			<td>
				<table>
					<c:if test="${!empty person.firstName}">
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
							<form:label path="firstName">
								<spring:message text="First Name"/>
							</form:label>
						</td>
						<td>
							<form:input path="firstName" />
						</td> 
					</tr>
					<tr>
						<td>
							<form:label path="lastName">
								<spring:message text="Last Name"/>
							</form:label>
						</td>
						<td>
							<form:input path="lastName" />
						</td> 
					</tr>
					<tr>
						<td colspan="2">
							<c:if test="${!empty person.firstName}">
								<input type="submit"
									value="<spring:message text="Edit Person"/>" />
							</c:if>
							<c:if test="${empty person.firstName}">
								<input type="submit"
									value="<spring:message text="Add Person"/>" />
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
									<th>Person Skills</th>
								</tr>
								<tr>
									<td>
			                  			<form:select path="skills" id="personSkills" items="${person.skills}" onchange="removeSkill(this)" itemValue="id" itemLabel="name" multiple = "true" />
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
	<h3>Persons List</h3>
	<c:if test="${!empty listPersons}">
		<table class="tg">
		<tr>
			<th width="80">Person ID</th>
			<th width="120">First Name</th>
			<th width="120">Last Name</th>
			<th width="300">Skills</th>
			<th width="60">Edit</th>
			<th width="60">Delete</th>
		</tr>
		<c:forEach items="${listPersons}" var="person">
			<tr>
				<td>${person.id}</td>
				<td>${person.firstName}</td>
				<td>${person.lastName}</td>
				<td><c:forEach items="${person.skills}" var="skill">${skill.name} </c:forEach></td>
				<td><a href="<c:url value='/edit/${person.id}' />" >Edit</a></td>
				<td><a href="<c:url value='/remove/${person.id}' />" >Delete</a></td>
			</tr>
		</c:forEach>
		</table>
	</c:if>
	</body>
	
	<footer>
		<script>
			function addSkill(selectObject) {
				var optionObject = selectObject.options[selectObject.selectedIndex];
				var personSkills = document.getElementById("personSkills");
				personSkills.add(optionObject);
				for (i = 0; i < personSkills.options.length; i++) {
					personSkills.options[i].selected = true;
				}
			}
			
			function removeSkill(selectObject) {
				var optionObject = selectObject.options[selectObject.selectedIndex];
				var allSkills = document.getElementById("allSkills");
				allSkills.add(optionObject);
				for (i = 0; i < allSkills.options.length; i++) {
					personSkills.options[i].selected = true;
				}
			}
		</script>
	</footer>
	
</html>
