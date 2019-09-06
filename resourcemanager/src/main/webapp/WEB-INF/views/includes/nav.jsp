<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page import="com.resourcemanager.model.User"%>
<c:set var = "admin" scope = "page" value = "#{loggedInUser != null and loggedInUser.authorityStrings.contains('ROLE_ADMIN')}"/>

<!-- nav bar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="/">Resource<br />Manager</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">
        <a class="nav-link" href="/users/myprofile">My Profile</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/users/notifications">Notifications</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/projects">Projects</a>
      </li>
      <c:if test="${admin}">
      <li class="nav-item">
        <a class="nav-link" href="/resources">Resources</a>
      </li>
      </c:if>
      <c:if test="${admin}">
      <li class="nav-item">
        <a class="nav-link" href="/skills">Skills</a>
      </li>
      </c:if>
      <c:if test="${admin}">
      <li class="nav-item">
      	<a class="nav-link" href="/users">Users</a>
      </li>
      </c:if>
      <c:if test="${admin}">
      <li class="nav-item">
      	<a class="nav-link" href="/allocations/listrequired">Organisational<br />Requirements</a>
      </li>
      </c:if>
      </li>
      <c:if test="${admin}">
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Reports
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="/reports/1">Hours per skill</a>
          <a class="dropdown-item" href="/reports/2">Hours per project</a>
        </div>
      </li>
      </c:if>
    </ul>
    <form class="form-inline my-2 my-lg-0" action="/logout" method="post">
		<button type="submit" class="btn btn-danger">Log Out</button>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>
  </div>
</nav>