<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var = "admin" scope = "page" value = "#{loggedInUser != null and loggedInUser.authorityStrings.contains('ROLE_ADMIN')}"/>

<html>
	<head>
		<title>Manage Project</title>
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
	</head>
	
	<body>
		<jsp:include page="/WEB-INF/views/includes/nav.jsp" />
		
		<div class="formContent">
			<table cellpadding="0" cellspacing="0" class="tbl">
				<tr>
					<td>
						<h1>Manage Project</h1>
					</td>	
				</tr>
				
				<tr>
					<td>
						<div>
						<c:url var="addAction" value="/projects/save" ></c:url>
						<form:form action="${addAction}" modelAttribute="project" method="POST" id="projectForm">
							<form:hidden path="id" />
										
							<table>
								<tr>
									<td>
										<form:label path="name" style="font-weight: bold;">
											<spring:message text="Project Name:"/>
										</form:label>
									</td>
									<td>
										<form:input path="name" style="margin-bottom: 15px; margin-left: 5px;"/>
									</td>
								</tr>
								<tr>
									<td>
										<form:label path="name" style="font-weight: bold;">
											<spring:message text="POB:"/>
										</form:label>
									</td>
									<td>
										<form:input path="pob" style="margin-bottom: 15px; margin-left: 5px;"/>
									</td>
								</tr>
							</table>							
							
							<h2>Requirements</h2>
							<table class="tbl">
								<tr>
									<th class="tblHeader" style="padding-left: 5px;">Skill</th>
									<th class="tblHeader">Start Date</th>
									<th class="tblHeader">End Date</th>
									<th class="tblHeader">Hours</th>
									<th class="tblHeaderCenter">Allocated To</th>
									<th class="tblHeaderCenter">Delete</th>
								</tr>
								<c:forEach items="${project.allocations}" var="allocation">
						
									<tr>
										<td class="tblDef">
											<select name="allocation_${allocation.id}_skillId" class="form-control">
												<option value="${allocation.skill.id}" selected>${allocation.skill.name}</option>
												<c:forEach items="${listSkills}" var="skill">
													<c:if test="${skill.id != allocation.skill.id}">
														<option value="${skill.id}">${skill.name}</option>
													</c:if>
						                  		</c:forEach>
					               			</select>
					               		</td>
										<td class="tblDef"><input name="allocation_${allocation.id}_startDate" autocomplete="off" type="text" value="${allocation.startDateAsString}" class="form-control datepicker startDate"/></td>
										<td class="tblDef"><input name="allocation_${allocation.id}_endDate" autocomplete="off" type="text" value="${allocation.endDateAsString}" class="form-control datepicker endDate"/></td>
										<td class="tblDef"><input name="allocation_${allocation.id}_hours" type="text" value="${allocation.hours}" class="form-control" style="width: 120px;"/></td>
										<td class="tblDefCenter"><input name="allocation_${allocation.id}_resourceId" type="hidden" value="${allocation.resource.id}"/><button type="button" style="background: deepskyblue;" class="btn btn-primary btn-sm" onclick="editAllocation(${allocation.id}, this)"><i class="fas fa-edit"></i></button> <span>${allocation.resource.name}</span></td>
										<td class="tblDefCenter"><button type="button" style="background: deepskyblue;" class="btn btn-primary btn-sm" onclick="deleteAllocation(this)"><i class="fas fa-trash"></i></button></td>
									</tr>
			                	</c:forEach>
		                		<tr id="allocationTemplate" style="display: none">
		                			<td>
										<select name="allocation_0_skillId" class="form-control" required>
											<option value="" disabled selected>Select...</option>
											<c:forEach items="${listSkills}" var="skill">
												<option value="${skill.id}">${skill.name}</option>
					                  		</c:forEach>
				               			</select>
				               		</td>
									<td><input name="allocation_0_startDate" autocomplete="off" type="text" class="form-control datepicker startDate" required/></td>
									<td><input name="allocation_0_endDate" autocomplete="off" type="text" class="form-control datepicker endDate" required/></td>
									<td><input name="allocation_0_hours" type="text" class="form-control" required/></td>
									<td></td>
									<td><button type="button" class="btn btn-primary btn-sm" onclick="deleteAllocation(this)"><i class="fas fa-trash"></i></button></td>
		                		</tr>
		                		
		                		<!-- Add Requirement button -->
			                	<tr id="addAllocationTR">
			                		<td colspan="100%">
			                	 		<button type="button" class="tblButton" style="margin: 10px 0px;" onclick="addAllocation()"><i class="fas fa-plus"></i> Add Requirement</button>
			                	 	</td>
			                	</tr>
		                	</table>
		                	
		                	<!-- Cancel/Save buttons -->
							<button type="button" onclick="history.back()" class="btn btn-danger"><i class="fas fa-ban"></i> Cancel</button>
		           			<button type="submit" class="btn btn-success"><i class="far fa-save"></i> Save</button>
						</form:form>
						
						
						</div>
					</td>	
				</tr>
			</table>
		</div>
           		
    	<!-- Modal Popup -->
		<div id="resourceModal" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">Available Resources</h4>
					</div>
					<input type="hidden" name="allocationId" id="allocationId">
					<div class="modal-body">
						
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-danger" data-dismiss="modal"><i class="far fa-window-close"></i> Cancel</button>
						<button type="button" class="btn btn-success" data-dismiss="modal" onclick="allocate()"><i class="far fa-save"></i> Save</button>
					</div>
				</div>
			</div>
		</div>
        
	</body>
	
	<footer>
		<script>
			// dom templates of new allocation and resource
			var allocationTemplate;
			$(function() {
				// copy the allocation template from the dom then remove it, only keeping in memory
				allocationTemplate = $("#allocationTemplate")[0];
				$(allocationTemplate).show();
				$(allocationTemplate).attr('id', '');
				allocationTemplate.remove();
				
				setupDatePickers();
			});			
			
			function setupDatePickers() {
				// date picker code, make any start date datepicker set the minimum date of the next end date to the start date, and vice versa
			    $( ".startDate" ).datepicker({   
			      defaultDate: "+1w",  
			      changeMonth: true,
			      changeYear: true,
			      dateFormat: 'dd/mm/yy',
			      numberOfMonths: 1,
			      onClose: function( selectedDate ) {
			    	  $(this).parent('td').next('td').children('input').first().datepicker( "option", "minDate", selectedDate );  
			      }
			    });  
			    $( ".endDate" ).datepicker({
			      defaultDate: "+1w",
			      changeMonth: true,
			      changeYear: true,
			      dateFormat: 'dd/mm/yy',
			      numberOfMonths: 1,
			      onClose: function( selectedDate ) {
			    	  $(this).parent('td').prev('td').children('input').first().datepicker( "option", "maxDate", selectedDate );
			      }
			    });
			}
			
			// add in the new requirement html to the dom every time the button is clicked
			function addAllocation() {
				var newTr = $("#addAllocationTR").before(allocationTemplate.outerHTML);
				setupDatePickers();
			}
			
			function deleteAllocation(buttonElement) {
				$(buttonElement).closest('tr').remove();
			}
			
			function editAllocation(allocationId, buttonElement) {
				
				if(! $("#projectForm")[0].checkValidity()) {
				  // If the form is invalid, submit it. The form won't actually submit;
				  // this will just cause the browser to display the native HTML5 error messages.
				  $("#projectForm").find(':submit').click();
				  return;
				}
				
				$('#allocationId').val(allocationId);
				
				var td = $(buttonElement).parent();
				
				var skillId = 0;
				var startDate = "";
				var endDate = "";
				var hours = "";
				
				$(td).siblings().each(function( index ) {
					if (typeof $(this).children('input').first().attr('name') !== "undefined") {
						if ($(this).children('input').first().attr('name').includes("startDate")) {
							startDate = $(this).children('input').first().val();
						} else if ($(this).children('input').first().attr('name').includes("endDate")) {
							endDate = $(this).children('input').first().val();
						} else if ($(this).children('input').first().attr('name').includes("hours")) {
							hours = $(this).children('input').first().val();
						}	
					} else if (typeof $(this).children('select').first().attr('name') !== "undefined") {
						if ($(this).children('select').first().attr('name').includes("skillId")) {
							skillId = $(this).children('select').first().children("option:selected").val()
						}
					}
				})
								
				$.get( "/resources/search", { skillId: skillId, startDate: startDate, endDate: endDate, hours: hours } )
				  .done(function( data ) {
					$("#resourceModal .modal-body").empty();
					$("#resourceModal .modal-body").html(data)
				  });

				$("#resourceModal").modal();
			}
			
			function save() {
				$("#projectForm").find(':submit').click();
			}
			
			function allocate() {
				var key = $(".modal-body").find('input[type="radio"]:checked').val();
				var value = $(".modal-body").find('input[type="radio"]:checked').parent().siblings().first().text();
				var id = $('#allocationId').val();
				var string = "allocation_" + id + "_resourceId";
				var resource = $('input[name=' + string + ']');
				$(resource).val(key);
				$(resource).parent().children('span').first().text(value);
				save();
			}
		</script>
	</footer>
	
</html>
