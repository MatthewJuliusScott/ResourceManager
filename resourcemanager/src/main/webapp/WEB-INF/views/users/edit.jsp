<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page import="com.resourcemanager.model.User"%>
<c:set var = "admin" scope = "page" value = "#{loggedInUser != null and loggedInUser.authorityStrings.contains('ROLE_ADMIN')}"/>

<html>
	<head>
		<title>User Page</title>
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
	</head>
	
	<body>
		<jsp:include page="/WEB-INF/views/includes/nav.jsp" />
		
		<div class="formContent">
			<c:url var="addAction" value="/users/save" ></c:url>
			<form:form action="${addAction}" modelAttribute="user" method="POST">
			<form:hidden path="id" />
				<table cellpadding="0" cellspacing="0" class="tbl">
					<tr>
						<td>
							<c:if test="${user.id != 0 && loggedInUser.id != user.id}">
								<h1>Edit User</h1>
							</c:if>
							<c:if test="${loggedInUser.id == user.id}">
								<h1>Edit My Profile</h1>
							</c:if>
							<c:if test="${user.id == 0}">
								<h1>Create User</h1>
							</c:if>
						</td>
					</tr>
					<tr>
						<table cellpadding="0" cellspacing="0" class="tbl">
							<tr>
								<td>
									<table cellpadding="0" cellspacing="0">
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
													<c:if test="${user.id == 0}">
														<form:input path="email" class="form-control" />
													</c:if>
													<c:if test="${user.id != 0}">
														<form:input path="email" readonly="true" class="form-control" />
													</c:if>
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
													<input type="password" name="password" class="form-control" />
												</td> 
											</tr>
											<tr>
												<td style="width: 150px;">
													<label style="font-weight: bold;">
														<spring:message text="Confirm Password"/>
													</label>
												</td>
												<td style="width: 500px;">
													<input type="password" name="confirmPassword" class="form-control" />
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
													<input type="password" name="oldPassword" class="form-control" />
												</td> 
											</tr>
											<tr>
												<td style="width: 150px;">
													<label style="font-weight: bold;">
														<spring:message text="New Password"/>
													</label>
												</td>
												<td style="width: 500px;">
													<input type="password" name="password" class="form-control" />
												</td> 
											</tr>
											<tr>
												<td style="width: 150px;">
													<label style="font-weight: bold;">
														<spring:message text="Confirm New Password"/>
													</label>
												</td>
												<td style="width: 500px;">
													<input type="password" name="confirmPassword" class="form-control" />
												</td> 
											</tr>
										</c:if>
										<c:if test="${admin}">
											<tr>
												<td style="width: 150px;">
														<label style="font-weight: bold;">
															<spring:message text="Link to Resource"/>
														</label>
													</td>
												<td>
													<select name="resourceId" id="resourceId" class="form-control" style="width: 500px;">
														<c:if test = "${not empty user.resource}">
															<option value="${user.resource.id}" selected>${user.resource.name}</option>
															<option value="">-- none --</option>
														</c:if>
														<c:if test = "${empty user.resource}">
															<option value="" selected>-- none --</option>
														</c:if>
														<c:forEach items="${listResources}" var="resource">
															<c:if test = "${empty resource.user && (empty user.resource || user.resource.id != resource.id)}">
																<option value="${resource.id}">${resource.name}</option>
															</c:if>
								                  		</c:forEach>
							               			</select>
												</td>
											</tr>
											<tr>
												<td style="width: 150px;">
														<label style="font-weight: bold;">
															<spring:message text="Authority"/>
														</label>
												</td>
												<td>
													<select name="authority" id="authority" class="form-control" style="width: 500px;">
														<c:if test = "${user.authorityStrings.contains('ROLE_ADMIN')}">
															<option value="ROLE_USER">User</option>
															<option value="ROLE_ADMIN" selected>Admin</option>
														</c:if>
														<c:if test = "${!user.authorityStrings.contains('ROLE_ADMIN')}">
															<option value="ROLE_USER" selected>User</option>
															<option value="ROLE_ADMIN">Admin</option>
														</c:if>
						               				</select>
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
					</tr>
				</table>
			</form:form>
		</div>		
	</body>
	
	<footer>
		<script>
			
		</script>
	</footer>
	
</html>
