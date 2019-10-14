<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var = "admin" scope = "page" value = "#{loggedInUser != null and loggedInUser.authorityStrings.contains('ROLE_ADMIN')}"/>

<html>
	<head>
		<title>Resource Page</title>
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
	</head>
	
	<body>
		<jsp:include page="/WEB-INF/views/includes/nav.jsp" />
		
		<div class="formContent">
			<table cellpadding="0" cellspacing="0" class="tbl">
				<tr>
					<td>
						<c:if test="${!empty resource.name}">
							<h1>Edit Resource</h1>
						</c:if>
						<c:if test="${empty resource.name}">
							<h1>Create Resource</h1>
						</c:if>
					</td>
				</tr>
				<tr>
					<c:url var="addAction" value="/resources/save" ></c:url>
					<form:form action="${addAction}" modelAttribute="resource" method="POST">
					
					<table cellpadding="0" cellspacing="0" class="tbl">
						<tr>
							<td>
								<table cellpadding="0" cellspacing="0" style="width: 350px;">
									<c:if test="${!empty resource.name}">
										<tr>
											<td style="width: 65px;">
												<form:label path="id" style="font-weight: bold; width: 5px;">
													<spring:message text="ID"/>
												</form:label>
											</td>
											<td style="width: 500px;">
												<form:input path="id" readonly="true" size="8" disabled="true"/>
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
										<td style="width: 65px;">
											<form:label path="hours" style="font-weight: bold;">
												<spring:message text="Hours"/>
											</form:label>
										</td>
										<td style="width: 500px;">
											<form:input path="hours" type="number" min="1" />
										</td> 
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table cellpadding="0" cellspacing="0" style="width: 350px;">
									<tr>
										<td>
											<label style="padding-top: 16px;">Resource Skills</label>
										</td>	
										<td>
											<label style="padding-top: 16px;">All Skills</label>
										</td>	
									</tr>
									<tr>
										<td>
				                  			<form:select style="min-width: 100px; min-height: 200px;" path="skills" id="resourceSkills" items="${resource.skills}" onchange="removeSkill(this)" itemValue="id" itemLabel="name" multiple="true"/>
				               			</td>
				               			<td>
				               				<select style="min-width: 100px; min-height: 200px;" onchange="addSkill(this)" id="allSkills" multiple="multiple">
												<c:forEach items="${listSkills}" var="skill">
													<option value="${skill.id}">${skill.name}</option>
						                  		</c:forEach>
					               			</select>
				               			</td>	
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="2" style="padding-top: 20px;">
								<button type="submit" class="btn btn-success"><i class="far fa-save"></i> Save</button>
							</td>
						</tr>
					</table>
					</form:form>
				</tr>
			</table>
		</div>

      <div style="padding:10px;">
         <button type="button" onclick="history.back()" class="btn btn-danger"><i class="fas fa-ban"></i> Cancel</button>
      </div>
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
