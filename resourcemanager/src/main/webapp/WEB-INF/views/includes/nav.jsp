<%@page import="com.resourcemanager.model.User"%>
<% User user = (User)session.getAttribute("loggedInUser") != null ? (User)session.getAttribute("loggedInUser") : new User();%>
<!-- nav bar -->
<div class="topnav">
	<a class="navMultiLine" href="/">Resource<br />Manager</a>
    <a class="navSingleLine" href="/users/myprofile">My Profile</a>
    <a class="navSingleLine" href="/users/notifications">Notifications</a>
    <a class="navSingleLine" href="/projects">Projects</a>
    <% if (user.getAuthorityStrings().contains("ROLE_ADMIN")) { %><a class="navSingleLine" href="/resources">Resources</a><% } %>
    <a class="navSingleLine" href="/skills">Skills</a>
    <% if (user.getAuthorityStrings().contains("ROLE_ADMIN")) { %><a class="navSingleLine" href="/users">Manage Users</a><% } %>
    <% if (user.getAuthorityStrings().contains("ROLE_ADMIN")) { %><a class="navSingleLine" href="/allocations/listrequired">Organisational Requirements</a><% } %>
    <div class="topnav-right">
    	<form action="/logout" method="post">
			<button type="submit" class="btn btn-danger">Log Out</button>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>
    </div>
</div>