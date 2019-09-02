<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var = "admin" scope = "page" value = "#{loggedInUser != null and loggedInUser.authorityStrings.contains('ROLE_ADMIN')}"/>

<script>
   function confirmDelete(resourceId){
      BAMJConfirm("Are you sure you want to delete resource " + resourceId + "?", "/resources/delete/" + resourceId);
   }
</script>

<html>
	<head>
		<title>Resources</title>
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
	</head>
	
	<body>
		<jsp:include page="/WEB-INF/views/includes/nav.jsp" />
		
		<div class="formContent">
			<table cellpadding="0" cellspacing="0" class="tbl">
				<tr>
					<td>
						<h1>Resources</h1>
					</td>	
				</tr>
				
				<tr>
					<td>
						<a class="tblButton" href="/resources/add" role="button">Create Resource</a>
					</td>	
				</tr>
			</table>
		
			<div style="padding-top: 30px;">
				<c:if test="${!empty listResources}">
					<table cellpadding="0" cellspacing="0" class="tblHeaderScroll">
						<tr>
							<th style="width: 300px;" class="tblHeaderCenter" scope="col">Resource ID</th>
							<th style="width: 65%;" class="tblHeader" scope="col">Name</th>
							<th style="width: 10%;" class="tblHeaderCenter" scope="col">Edit</th>
							<th style="width: 10%;" class="tblHeaderCenter" scope="col">Delete</th>
						</tr>
						<table cellpadding="0" cellspacing="0" class="tblList">
							<c:forEach items="${listResources}" var="resource">
									<tr>
										<td style="width: 300px;" class="tblDefCenter">${resource.id}</td>
										<td style="width: 65%;" class="tblDef">${resource.name}</td>
										<td style="width: 10%;" class="tblDefCenter"><a style="background: deepskyblue;" class="btn btn-primary btn-sm" href="/resources/edit/${resource.id}" role="button"><i class="far fa-edit"></i></a></td>
										<td style="width: 10%;" class="tblDefCenter"><a onclick="confirmDelete(${resource.id})" class="btn btn-danger btn-sm" role="button"><i class="fas fa-trash"></i></a></td>
									</tr>
							</c:forEach>
						</table>
					</table>
				</c:if>		
			</div>	
		</div>
		
		<div style="padding:10px;">
         <button type="button" onclick="history.back()" class="btn btn-danger"><i class="fas fa-ban"></i> Cancel</button>
      </div>
	</body>	
</html>
