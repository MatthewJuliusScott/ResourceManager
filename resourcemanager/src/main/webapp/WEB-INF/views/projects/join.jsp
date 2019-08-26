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
									<c:forEach items="${user.skills}" var="skills">
										<c:if test="${user.skill.id == allocation.skill.id && empty allocation.resource}">																						
											<tr>											
							               		<td style="width: 300px;" class="tblDef">${allocation.skill.name}</td>
							               		<td style="width: 300px;" class="tblDef">${allocation.startDateAsString}</td>
												<td style="width: 300px;" class="tblDef">${allocation.endDateAsString}</td>
												<td style="width: 300px;" class="tblDef">${allocation.hours}</td>
												<td class="tblDefCenter"><button type="button" style="background: deepskyblue;" class="btn btn-primary btn-sm" onclick="joinAllocation(${allocation}, ${resource})"><i class="fas fa-edit"></i></button></td>
											</tr>								
										</c:if>
									</c:forEach>
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
		                		</tr>
		                		
		                	</table>
		                	
		                	<!-- Cancel/Save buttons -->
							<a href="/projects" class="btn btn-danger"><i class="far fa-window-close"></i> Cancel</a>
		           			<button type="submit" class="btn btn-success"><i class="far fa-save"></i> Save</button>
						</form:form>
						
						
						</div>
					</td>	
				</tr>
			</table>
		</div>
		
		<div>
        	<button type="button" onclick="history.back()" class="btn btn-danger"><i class="fas fa-ban"></i> Cancel</button>
        </div>
           		
    	<!-- Modal Popup -->
		<div id="resourceModal" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">Join Project</h4>
					</div>
					
					<div class="modal-body">
						<h5>Do you wish to join this Allocation?</h5>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-danger" data-dismiss="modal"><i class="far fa-window-close"></i> No</button>
						<button type="button" class="btn btn-success" data-dismiss="modal" onclick="allocate()"><i class="far fa-save"></i> Yes</button>
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
			}
			
			// add in the new requirement html to the dom every time the button is clicked
			function addAllocation() {
				var newTr = $("#addAllocationTR").before(allocationTemplate.outerHTML);
				setupDatePickers();
			}
			
			function deleteAllocation(buttonElement) {
				$(buttonElement).closest('tr').remove();
			}
			
			function joinAllocation(allocation, resouce){
				
				
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
				//var value = $(".modal-body").find('input[type="radio"]:checked').parent().siblings().first().text();
				var id = $session.getAttribute("ResourceId");
				var string = "allocation_" + id + "_resourceId";
				var resource = $('input[name=' + string + ']');
				$(resource).val(key);
				$(resource).parent().children('span').first().text(value);
				save();
			}
		</script>
	</footer>
	
</html>
