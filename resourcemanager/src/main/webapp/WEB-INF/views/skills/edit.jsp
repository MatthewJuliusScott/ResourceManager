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
		
		<div class="formContent">
			<table cellpadding="0" cellspacing="0" class="tbl">
				<tr>
					<td>
						<c:if test="${!empty skill.name}">
							<h1>
								Update Skill
							</h1>
						</c:if>
						<c:if test="${empty skill.name}">
							<h1>
								Create Skill
							</h1>
						</c:if>
					</td>
				</tr>
				<tr>
					<c:url var="addAction" value="/skills/save" ></c:url>
					<form:form action="${addAction}" modelAttribute="skill" method="POST">
					
						<table cellpadding="0" cellspacing="0" class="tbl">
							<tr>
								<td>
									<table cellpadding="0" cellspacing="0" style="width: 350px;">
										<c:if test="${!empty skill.name}">
											<tr>
												<td style="width: 65px;">
													<form:label path="id" style="font-weight: bold; width: 5px;">
														<spring:message text="ID"/>
													</form:label>
												</td>
												<td style="width: 500px;">
													<form:input path="id" readonly="true" size="8"  disabled="true" />
													<form:hidden path="id" />
												</td> 
											</tr>
										</c:if>
										<tr>
											<td style="width: 65px;">
												<form:label path="name" style="font-weight: bold;">
													<spring:message text="Name"/>
												</form:label>
											</td>
											<td style="width: 500px;">
												<form:input path="name" />
											</td> 
										</tr>
										<tr>
											<td colspan="2" style="padding-top: 20px;">
												<button type="submit" class="btn btn-success"><i class="far fa-save"></i> Save</button>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</form:form>
				</tr>
			</table>									
		</div>		
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
