<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
	<head>
		<title>Manage Project</title>
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
	</head>
	
	<body>
	
		<jsp:include page="/WEB-INF/views/includes/nav.jsp" />
		
		<div class="container-fluid">
			<h1>Manage Project</h1>
						
			<div class="container-fluid">
				<c:url var="addAction" value="/projects/save" ></c:url>
				<form:form action="${addAction}" modelAttribute="project" method="POST">
				
					<form:hidden path="id" />
								
					<div class="form-group">
						<form:label path="name">
							<spring:message text="Name"/>
						</form:label>
						<form:input path="name" class="form-control"/>
					</div>
					<h2>Requirements</h2>
					<table class="table">
						<tr>
							<th>Skill</th>
							<th>Start Date</th>
							<th>End Date</th>
							<th>Hours</th>
							<th>Allocated To</th>
							<th>Delete</th>
						</tr>
						<c:forEach items="${project.allocations}" var="allocation">
							<tr>
								<td>
									<select name="allocation_${allocation.id}_skillId" class="form-control">
										<option value="${allocation.skill.id}" selected>${allocation.skill.name}</option>
										<c:forEach items="${listSkills}" var="skill">
											<c:if test="${skill.id != allocation.skill.id}">
												<option value="${skill.id}">${skill.name}</option>
											</c:if>
				                  		</c:forEach>
			               			</select>
			               		</td>
								<td><input name="allocation_${allocation.id}_startDate" autocomplete="off" type="text" value="${allocation.startDate}" class="form-control datepicker startDate"/></td>
								<td><input name="allocation_${allocation.id}_endDate" autocomplete="off" type="text" value="${allocation.endDate}" class="form-control datepicker endDate"/></td>
								<td><input name="allocation_${allocation.id}_hours" type="text" value="${allocation.hours}" class="form-control"/></td>
								<td><input name="allocation_${allocation.id}_resourceId" type="hidden" value="${allocation.resource.id}"/><button type="button" class="btn btn-primary btn-sm" onclick="editAllocation(this)"><i class="fas fa-edit"></i></button>${allocation.resource.name}</td>
								<td><button type="button" class="btn btn-primary btn-sm" onclick="deleteAllocation(this)"><i class="fas fa-trash"></i></button></td>
							</tr>
	                	</c:forEach>
                		<tr id="allocationTemplate" style="display: none">
                			<td>
								<select name="allocation_${allocation.id}_skillId" class="form-control">
									<option selected disabled>Select...</option>
									<c:forEach items="${listSkills}" var="skill">
										<option value="${skill.id}">${skill.name}</option>
			                  		</c:forEach>
		               			</select>
		               		</td>
							<td><input name="allocation_0_startDate" autocomplete="off" type="text" class="form-control datepicker startDate"/></td>
							<td><input name="allocation_0_endDate" autocomplete="off" type="text" class="form-control datepicker endDate"/></td>
							<td><input name="allocation_0_hours" type="text" class="form-control"/></td>
							<td><input name="allocation_0_resourceId" type="hidden"/><button type="button" class="btn btn-primary btn-sm" onclick="editAllocation(this)"><i class="fas fa-edit"></i></button></td>
							<td></td>
                		</tr>
	                	<tr id="addAllocationTR">
	                		<td colspan="100%">
	                	 		<button type="button" class="btn btn-primary btn-sm" onclick="addAllocation()"><i class="fas fa-plus"></i> Add Requirement</button>
	                	 	</td>
	                	</tr>
                	</table>
                	          			
           			<button type="submit" class="btn btn-primary"><i class="far fa-save"></i> Save</button>
           			
           		</form:form>
			</div>
		</div>
	</body>
	
	<footer>
		<script>
			// dom template of new allocation
			var allocationTemplate;
			$(function() {
				// copy the allocation template from the dom then remove it, only keeping in memory
				allocationTemplate = $("#allocationTemplate")[0];
				$(allocationTemplate).show();
				$(allocationTemplate).attr('id', '');
				$("#allocationTemplate").remove();
				
				// date picker code, make any start date datepicker set the minimum date of the next end date to the start date, and vice versa
			    $( ".startDate" ).datepicker({   
			      defaultDate: "+1w",  
			      changeMonth: true,
			      dateFormat: 'dd/mm/yy',
			      numberOfMonths: 1,
			      onClose: function( selectedDate ) {
			    	  $(this).parent('td').next('td').children('input').first().datepicker( "option", "minDate", selectedDate );  
			      }
			    });  
			    $( ".endDate" ).datepicker({
			      defaultDate: "+1w",
			      changeMonth: true,
			      dateFormat: 'dd/mm/yy',
			      numberOfMonths: 1,
			      onClose: function( selectedDate ) {
			    	  $(this).parent('td').prev('td').children('input').first().datepicker( "option", "maxDate", selectedDate );
			      }
			    });  
			});
			
			// add in the new requirement html to the dom every time the button is clicked
			function addAllocation() {
				var newTr = $("#addAllocationTR").before(allocationTemplate.outerHTML);
			}
			
			function deleteAllocation(buttonElement) {
				$(buttonElement).closest('tr').remove();
			}
		</script>
	</footer>
	
</html>
