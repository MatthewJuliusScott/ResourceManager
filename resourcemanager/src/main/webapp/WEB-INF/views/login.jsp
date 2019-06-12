<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>ResourceManager Login</title>
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
	</head>
<body>
	<div class="container" style="margin:50px">
		<h3>ResourceManager Login</h3>
		
		<div class="container"><img src="resources/images/logo.png" alt="Logo"></div>
		
		<c:if test="${param.error ne null}">
			<div style="color: red">Invalid credentials.</div>
		</c:if>
		<form action="/ResourceManager/login" method="post">
			<div class="form-group">
				<label for="username">Username: <input type="text"
					class="form-control" id="username" name="username">
			</div>
			<div class="form-group">
				<label for="pwd">Password:</label> <input type="password"
					class="form-control" id="pwd" name="password">
			</div>

			<button type="submit" class="btn btn-success">Submit</button>

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
	</div>
</body>
</html>