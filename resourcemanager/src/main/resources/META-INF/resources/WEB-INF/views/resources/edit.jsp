<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
	<head>
		<title>Resource Page</title>
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
	</head>
	
	<body>
	
		<jsp:include page="/WEB-INF/views/includes/nav.jsp" />
		
		<c:if test="${!empty resource.name}">
			<h1>
				Edit a Resource
			</h1>
		</c:if>
		<c:if test="${empty resource.name}">
			<h1>
				Add a Resource
			</h1>
		</c:if>
		
		
		<c:url var="addAction" value="/resources/save" ></c:url>
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
							<td>
								<form:label path="hours">
									<spring:message text="hours"/>
								</form:label>
							</td>
							<td>
								<form:input path="hours" />
							</td> 
						</tr>
						<tr>
							<td colspan="2">
								<input type="submit" class="btn btn-primary"
									value="<spring:message text="Save"/>" />
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
				var resourceSkills = document.getElementById("resourceSkills");
				allSkills.add(optionObject);
				for (i = 0; i < allSkills.options.length; i++) {
					resourceSkills.options[i].selected = true;
				}
			}
		</script>
	</footer>
	
</html>
