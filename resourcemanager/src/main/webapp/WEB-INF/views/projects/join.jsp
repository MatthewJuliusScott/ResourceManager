<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
	<head>
		<title>Join Project</title>
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
	</head>
	
	<body>
		<jsp:include page="/WEB-INF/views/includes/nav.jsp" />
		
		<div class="formContent">
			<table cellpadding="0" cellspacing="0" class="tbl">
				<tr>
					<td>
						<h1>Join Project</h1>
					</td>	
				</tr>
				
				<tr>
					<td>
						<div>
						<c:url var="addAction" value="/allocations/join" ></c:url>
						<form:form action="${addAction}" modelAttribute="resource" method="POST" id="allocateForm">
							<input type="hidden" name="resourceId" id="resourceId">	
							<input type="hidden" name="allocationId" id="allocationId">
							<table>
								<h2>${project.name}</h2>
							</table>							
							
							<h2>Requirements</h2>
							<table class="tbl">
								<tr>
									<th class="tblHeader" style="padding-left: 5px;">Skill</th>
									<th class="tblHeader">Start Date</th>
									<th class="tblHeader">End Date</th>
									<th class="tblHeader">Hours</th>
									<th class="tblHeaderCenter">Join</th>
								</tr>
								
								<c:forEach items="${project.allocations}" var="allocation">
									<c:forEach items="${userResource.skills}" var="skill">							
									<c:if test="${skill.id == allocation.skill.id && empty allocation.resource}">																					
											<tr>											
							               		<td style="width: 300px;" class="tblDef">${allocation.skill.name}</td>
							               		<td style="width: 300px;" class="tblDef">${allocation.startDateAsString}</td>
												<td style="width: 300px;" class="tblDef">${allocation.endDateAsString}</td>
												<td style="width: 300px;" class="tblDef">${allocation.hours}</td>
												
												<td class="tblDefCenter"><button type="button" style="background: deepskyblue;" class="btn btn-primary btn-sm" onclick="joinAllocation(${allocation.id}, ${userResource.id })"><i class="fas fa-edit"></i></button></td>
											</tr>								
									</c:if> 
									</c:forEach>
			                	</c:forEach>
		            
		                		
		                	</table>
		                	
		                	<!-- Cancel/Save buttons -->
							<a href="/projects" class="btn btn-danger"><i class="far fa-window-close"></i> Cancel</a>
		           			<button type="submit" class="btn btn-success"><i class="far fa-save"></i>Save</button>
						</form:form>
						
						
						</div>
					</td>	
				</tr>
			</table>
		</div>
		
		<div>
        	<button type="button" onclick="history.back()" class="btn btn-danger"><i class="fas fa-ban"></i> Cancel</button>
        </div>
           		
    	
	</body>
	
	<footer>
		<script>
			
			
			function joinAllocation(allocation, resource){
				$("#resourceId").val(resource)
				$("#allocationId").val(allocation)
				
				save();
			}
			
			function save() {
				$("#allocateForm").find(':submit').click();
			}
			
		</script>
	</footer>
	
</html>
