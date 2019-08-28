<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:set var="loggedInUser" value="${loggedInUser}"/>
<spring:eval var="admin" expression="loggedInUser.authorityStrings.contains('ROLE_ADMIN')" />

<script>
   var projectId; // Used for modal.
</script>

<html>
	<head>
		<title>Project</title>
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
	</head>
	
	<body>
		<jsp:include page="/WEB-INF/views/includes/nav.jsp" />
		
		<div class="formContent">
			<table cellpadding="0" cellspacing="0" class="tbl">
				<tr>
					<td>
						<h1>Projects</h1>
					</td>	
				</tr>
				
				<c:if test="${admin}">
					<tr>
						<td>
							<a class="tblButton" href="/projects/add" role="button">Create Project</a>
						</td>	
					</tr>
				</c:if>
			</table>
			<c:set var="isadmin" value="false"/>
			<c:if test="${!empty user }">
					<c:forEach items="${roles}" var="role">
						<c:if test="${role == 'ROLE_ADMIN' }">
							<c:set var="isadmin" value="true"/>
						</c:if>	
				</c:forEach>
			</c:if>
			<div style="padding-top: 30px;">
				<c:if test="${!empty listProjects}">
				
					<table cellpadding="0" cellspacing="0" class="tblHeaderScroll">
						<tr>
							<th style="width: 300px;" class="tblHeaderCenter">Project ID</th>
							<th style="width: 65%;" class="tblHeader">Name</th>
							<c:if test="${isadmin == 'true' }">
							<th style="width: 10%;" class="tblHeaderCenter">Edit</th>
							<th style="width: 10%;" class="tblHeaderCenter">Delete</th>
							</c:if>
							<c:if test="${isadmin != 'true' }">
							<th style="width: 10%;" class="tblHeaderCenter">Join</th>
							</c:if>
						</tr>
						<table cellpadding="0" cellspacing="0" class="tblList">
							<c:forEach items="${listProjects}" var="project">
								<tr>
									<td style="width: 300px;" class="tblDefCenter">${project.id}</td>
									<td style="width: 65%;" class="tblDef">${project.name}</td>
									<c:if test="${isadmin == 'true' }">
									<td style="width: 10%;" class="tblDefCenter"><a style="background: deepskyblue;" class="btn btn-primary btn-sm" href="/projects/edit/${project.id}" role="button"><i class="far fa-edit"></i></a></td>
									<td style="width: 10%;" class="tblDefCenter"><a data-toggle="modal" data-target="#exampleModalCenter" onclick="projectId = '${project.id}'" class="btn btn-danger btn-sm" role="button"><i class="fas fa-trash"></i></a></td>
									</c:if>
									<c:if test="${isadmin != 'true' }">
									<td style="width: 10%;" class="tblDefCenter"><a style="background: deepskyblue;" class="btn btn-primary btn-sm" href="/projects/join/${project.id}" role="button"><i class="far fa-edit"></i></a></td>
									</c:if>
								</tr>
							</c:forEach>
						</table>
					</table>
				</c:if>
				<div>
               <button type="button" onclick="history.back()" class="btn btn-danger"><i class="fas fa-ban"></i> Cancel</button>
            </div>
            
            <!-- Modal popup -->
            <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
               <div class="modal-dialog modal-dialog-centered" role="document">
                  <div class="modal-content">
                     <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle" style="font-weight:bold;">Warning</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                           <span aria-hidden="true">&times;</span>
                        </button>
                     </div>
                     <div class="modal-body">
                        Are you sure you want to delete this Project?
                     </div>
                     <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-dismiss="modal" style="background:deepskyblue;">Cancel</button>
                        <button type="button" class="btn btn-danger" onclick="window.location.href='/projects/delete/'+projectId">Delete</button>
                     </div>
                  </div>
               </div>
            </div>
			</div>	
		</div>
	</body>	
</html>
