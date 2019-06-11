<nav class="navbar navbar-expand-sm bg-light">

  <!-- Links -->
  <ul class="navbar-nav" style="width: 100%">
    <li class="nav-item">
      <a class="nav-link" href="/">Index</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="/projects">Projects</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="/resources">Resources</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="/skills">Skills</a>
    </li>
    <li class="nav-item" style="margin-left: auto;">
    	<form action="/logout" method="post">
			<button type="submit" class="btn btn-danger">Log Out</button>
			<input type="hidden" name="${_csrf.parameterName}"
				   value="${_csrf.token}"/>
		</form>
    </li>
  </ul>

</nav>