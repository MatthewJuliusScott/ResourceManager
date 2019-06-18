<!-- nav bar -->
<div class="topnav">
	<a class="navMultiLine" href="/">Resource<br />Manager</a>
    <!-- <a class="navSingleLine" href="/person">My Profile</a> -->
    <a class="navSingleLine" href="/projects">Projects</a>
    <a class="navSingleLine" href="/resources">Resources</a>
    <a class="navSingleLine" href="/skills">Skills</a>
    <div class="topnav-right">
    	<form action="/logout" method="post">
			<button type="submit" class="btn btn-danger">Log Out</button>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>
    </div>
</div>