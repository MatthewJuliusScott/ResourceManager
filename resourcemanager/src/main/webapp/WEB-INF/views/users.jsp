<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script>
	function ConfirmMessage(id) {
		var result = confirm("Do you want to remove this User?");
		if (result == true){
			window.location.href = "/users/delete/"+id;	
		}
	}
</script>
<html>
	<head>
		<title>Manage System Users</title>
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
	</head>
	
	<body>
		<jsp:include page="/WEB-INF/views/includes/nav.jsp" />
		
		<div class="formContent">
			<table cellpadding="0" cellspacing="0" class="tbl">
				<tr>
					<td>
						<h1>Users</h1>
					</td>	
				</tr>
				
				<tr>
					<td>
						<a class="tblButton" href="/users/add" role="button">Create User</a>
					</td>	
				</tr>
			</table>
		
			<div style="padding-top: 30px;">
				<c:if test="${!empty listUsers}">
					<table cellpadding="0" cellspacing="0" class="tblHeaderScroll">
						<tr>
							<th style="width: 300px;" class="tblHeaderCenter" scope="col">User ID</th>
							<th style="width: 65%;" class="tblHeader" scope="col">Name</th>
							<th style="width: 10%;" class="tblHeaderCenter" scope="col">Edit</th>
							<th style="width: 10%;" class="tblHeaderCenter" scope="col">Delete</th>
						</tr>
						<table cellpadding="0" cellspacing="0" class="tblList">
							<c:forEach items="${listUsers}" var="User">
									<tr>
										<td style="width: 300px;" class="tblDefCenter">${User.id}</td>
										<td style="width: 65%;" class="tblDef">${User.name}</td>
										<td style="width: 10%;" class="tblDefCenter"><a style="background: deepskyblue;" class="btn btn-primary btn-sm" href="/users/edit/${User.id}" role="button"><i class="far fa-edit"></i></a></td>
										<td style="width: 10%;" class="tblDefCenter"><a class="btn btn-danger btn-sm" onclick="ConfirmMessage(${User.id});" role="button"><i class="fas fa-trash"></i></a></td>
									</tr>
							</c:forEach>
						</table>
					</table>
				</c:if>		
			</div>	
		</div>
	</body>	
</html>
