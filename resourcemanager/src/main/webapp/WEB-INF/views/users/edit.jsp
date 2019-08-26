<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page import="com.resourcemanager.model.User"%>
<% User user = (User)session.getAttribute("loggedInUser") != null ? (User)session.getAttribute("loggedInUser") : new User();%>

<html>
	<head>
		<title>User Page</title>
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
	</head>
	
	<body>
		<jsp:include page="/WEB-INF/views/includes/nav.jsp" />
		
		<div class="formContent">
			<table cellpadding="0" cellspacing="0" class="tbl">
				<tr>
					<td>
						<c:if test="${!empty user.name}">
							<h1>Edit User</h1>
						</c:if>
						<c:if test="${empty user.name}">
							<h1>Create User</h1>
						</c:if>
					</td>
				</tr>
				<tr>
					<c:url var="addAction" value="/users/save" ></c:url>
					<form:form action="${addAction}" modelAttribute="user" method="POST">
					<form:hidden path="id" />
					<table cellpadding="0" cellspacing="0" class="tbl">
						<tr>
							<td>
								<table cellpadding="0" cellspacing="0" style="width: 350px;">
									<c:if test="${user.id == 0}">
										<tr>
											<td style="width: 150px;">
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
										<td style="width: 150px;">
											<form:label path="name" style="font-weight: bold;">
												<spring:message text="Name"/>
											</form:label>
										</td>
										<td style="width: 500px;">
											<form:input path="name" />
										</td> 
									</tr>
									<tr>
										<td style="width: 150px;">
											<form:label path="Email" style="font-weight: bold;">
												<spring:message text="Email"/>
											</form:label>
										</td>
										<td style="width: 500px;">
											<form:input path="email" readonly="true" />
										</td> 
									</tr>
									<c:if test="${user.id == 0}">
										<tr>
											<td style="width: 150px;">
												<label style="font-weight: bold;">
													<spring:message text="Password"/>
												</label>
											</td>
											<td style="width: 500px;">
												<input type="password" name="password" />
											</td> 
										</tr>
										<tr>
											<td style="width: 150px;">
												<label style="font-weight: bold;">
													<spring:message text="Confirm Password"/>
												</label>
											</td>
											<td style="width: 500px;">
												<input type="password" name="confirmPassword" />
											</td> 
										</tr>
									</c:if>
									<c:if test="${user.id != 0}">
										<tr>
											<td style="width: 150px;">
												<label style="font-weight: bold;">
													<spring:message text="Old Password"/>
												</label>
											</td>
											<td style="width: 500px;">
												<input type="password" name="oldPassword" />
											</td> 
										</tr>
										<tr>
											<td style="width: 150px;">
												<label style="font-weight: bold;">
													<spring:message text="New Password"/>
												</label>
											</td>
											<td style="width: 500px;">
												<input type="password" name="password" />
											</td> 
										</tr>
										<tr>
											<td style="width: 150px;">
												<label style="font-weight: bold;">
													<spring:message text="Confirm New Password"/>
												</label>
											</td>
											<td style="width: 500px;">
												<input type="password" name="confirmPassword" />
											</td> 
										</tr>
									</c:if>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="2" style="padding-top: 20px;">
								<button type="button" onclick="history.back()" class="btn btn-danger"><i class="fas fa-ban"></i> Cancel</button>
								<button type="submit" class="btn btn-success"><i class="far fa-save"></i> Save</button>
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
			
		</script>
	</footer>
	
</html>
