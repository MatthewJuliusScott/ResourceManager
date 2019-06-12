<nav class="navbar navbar-expand-sm bg-light">

  <!-- Links -->
  <ul class="navbar-nav" style="width: 100%">
    <li class="nav-item">
      <a class="nav-link" href="/ResourceManager/">Index</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="/ResourceManager/projects">Projects</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="/ResourceManager/resources">Resources</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="/ResourceManager/skills">Skills</a>
    </li>
    <li class="nav-item" style="margin-left: auto;">
    	<form action="/ResourceManager/logout" method="post">
			<button type="submit" class="btn btn-danger">Log Out</button>
			<input type="hidden" name="${_csrf.parameterName}"
				   value="${_csrf.token}"/>
		</form>
    </li>
  </ul>

</nav>