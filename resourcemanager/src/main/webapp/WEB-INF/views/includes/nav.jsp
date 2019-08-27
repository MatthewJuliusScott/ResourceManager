<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page import="com.resourcemanager.model.User"%>

<c:set var="loggedInUser" value="${loggedInUser}"/>
<spring:eval var="admin" expression="loggedInUser.authorityStrings.contains('ROLE_ADMIN')" />

<!-- nav bar -->
<div class="topnav">
	<a class="navMultiLine" href="/">Resource<br />Manager</a>
    <a class="navSingleLine" href="/users/myprofile">My Profile</a>
    <a class="navSingleLine" href="/users/notifications">Notifications</a>
    <a class="navSingleLine" href="/projects">Projects</a>
    <c:if test="${admin}"><a class="navSingleLine" href="/resources">Resources</a></c:if>
    <a class="navSingleLine" href="/skills">Skills</a>
    <c:if test="${admin}"><a class="navSingleLine" href="/users">Manage Users</a></c:if>
    <c:if test="${admin}"><a class="navSingleLine" href="/allocations/listrequired">Organisational Requirements</a></c:if>
    <div class="topnav-right">
    	<form action="/logout" method="post">
			<button type="submit" class="btn btn-danger">Log Out</button>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>
    </div>
</div>