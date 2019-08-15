<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
	<head>
		<title>Allocations</title>
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
	</head>
	
	<body>
		<jsp:include page="/WEB-INF/views/includes/nav.jsp" />
	
		<h1>
			Allocations
		</h1>
	
		<div style="padding-top: 30px;">
			<table cellpadding="0" cellspacing="0" class="tblHeaderScroll">
				<tr>
					<th style="width: 300px;" class="tblHeaderCenter" scope="col">Allocation ID</th>
					<th style="width: 65%;" class="tblHeader" scope="col">Project</th>
					<th style="width: 10%;" class="tblHeaderCenter" scope="col">Edit</th>
					<th style="width: 10%;" class="tblHeaderCenter" scope="col">Delete</th>
				</tr>
				<table cellpadding="0" cellspacing="0" class="tblList">
					<c:forEach items="${listAllocations}" var="allocation">
							<tr>
								<td style="width: 300px;" class="tblDefCenter">${allocation.id}</td>
								<td style="width: 65%;" class="tblDef">${allocation.project.name}</td>
								<td style="width: 10%;" class="tblDefCenter"><a style="background: deepskyblue;" class="btn btn-primary btn-sm" href="allocations/edit/${allocation.id}" role="button"><i class="far fa-edit"></i></a></td>
								<td style="width: 10%;" class="tblDefCenter"><a class="btn btn-danger btn-sm" href="/allocations/delete/${allocation.id}" role="button"><i class="fas fa-trash"></i></a></td>
							</tr>
					</c:forEach>
				</table>
			</table>	
		</div>
	
	</body>
	
	<footer>
		<script>
		</script>
	</footer>
	
</html>
