<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
	<head>
		<title>Skill Page</title>
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
	</head>
	
	<body>
	
		<jsp:include page="/WEB-INF/views/includes/nav.jsp" />
		
		<c:if test="${!empty skill.name}">
			<h1>
				Edit a Skill
			</h1>
		</c:if>
		<c:if test="${empty skill.name}">
			<h1>
				Add a Skill
			</h1>
		</c:if>
		
		
		<c:url var="addAction" value="/skill/add" ></c:url>
		
		<form:form action="${addAction}" modelAttribute="skill" method="POST">
		<table>
			<tr>
				<td>
					<table>
						<c:if test="${!empty skill.name}">
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
								<input type="submit" class="btn btn-primary"
									value="<spring:message text="Save"/>" />
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
				var skillSkills = document.getElementById("skillSkills");
				skillSkills.add(optionObject);
				for (i = 0; i < skillSkills.options.length; i++) {
					skillSkills.options[i].selected = true;
				}
			}
			
			function removeSkill(selectObject) {
				var optionObject = selectObject.options[selectObject.selectedIndex];
				var allSkills = document.getElementById("allSkills");
				allSkills.add(optionObject);
				for (i = 0; i < allSkills.options.length; i++) {
					skillSkills.options[i].selected = true;
				}
			}
		</script>
	</footer>
	
</html>
