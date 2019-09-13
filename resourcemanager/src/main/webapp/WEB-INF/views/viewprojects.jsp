<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var = "admin" scope = "page" value = "#{loggedInUser != null and loggedInUser.authorityStrings.contains('ROLE_ADMIN')}"/>

<html>
	<head>
		<title>View Project</title>
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
	</head>
	
	<body>
		<jsp:include page="/WEB-INF/views/includes/nav.jsp" />
		
		<div class="formContent">
			<table cellpadding="0" cellspacing="0" class="tbl">
				<tr>
					<td>
						<h1>View Projects</h1>
					</td>	
				</tr>
			</table>
			
			<div style="padding-top: 30px;">
				<c:if test="${!empty listProjects}">
					<table cellpadding="0" cellspacing="0" class="tblHeaderScroll">
						<tr>
							<th style="width: 300px;" class="tblHeaderCenter">Project ID</th>
							<th style="width: 65%;" class="tblHeader">Name</th>
							<th style="width: 10%;" class="tblHeaderCenter">Join</th>
						</tr>
						<table cellpadding="0" cellspacing="0" class="tblList">
							<c:forEach items="${listProjects}" var="project">
								<tr>
									<td style="width: 300px;" class="tblDefCenter">${project.id}</td>
									<td style="width: 65%;" class="tblDef">${project.name}</td>
									<td style="width: 10%;" class="tblDefCenter"><a style="background: deepskyblue;" class="btn btn-primary btn-sm" href="/projects/join/${project.id}" role="button"><i class="far fa-edit"></i></a></td>									
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
