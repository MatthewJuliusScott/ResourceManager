<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var = "admin" scope = "page" value = "#{loggedInUser != null and loggedInUser.authorityStrings.contains('ROLE_ADMIN')}"/>

<script>
   function confirmDelete(allocationId){
      BAMJConfirm("Are you sure you want to delete allocation " + allocationId + "?", "/allocations/delete/" + allocationId);
   }
</script>

<html>
	<head>
		<title>Allocations</title>
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
	</head>
	
	<body>
		<jsp:include page="/WEB-INF/views/includes/nav.jsp" />
	
		<h1>
			View Organisational requirements
		</h1>
		<div>Enter dates to search between then select the View Requirements button</div>
		
		<c:url var="addAction" value="/allocations/listrequired" ></c:url>
		<form:form action="${addAction}" method="GET" id="allocationsForm">
			<table class="tbl">
				<tr>
					<th class="tblHeader">Start Date</th>
					<th class="tblHeader">End Date</th>
					<th class="tblHeader"></th>
				</tr>
				<tr>
					<td><input name="startDate" value="<%=(String)request.getAttribute("startDate")%>" autocomplete="off" type="text" class="form-control datepicker startDate" required/></td>
					<td><input name="endDate" value="<%=(String)request.getAttribute("endDate")%>" autocomplete="off" type="text" class="form-control datepicker endDate" required/></td>
					<td class="tblDefCenter"><button type="submit" style="background: deepskyblue;" class="btn btn-primary btn-sm"><i class="fas fa-eye"></i>View Requirements</button></td>
				</tr>
	       	</table>
       	</form:form>
	
		<div style="padding-top: 30px;">
			<table cellpadding="0" cellspacing="0" class="tblHeaderScroll">
				<tr>
					<th style="width: 300px;" class="tblHeaderCenter" scope="col">#</th>
					<th style="width: 20%;" class="tblHeader" scope="col">Project Name</th>
					<th style="width: 15%;" class="tblHeader" scope="col">Start Date</th>
					<th style="width: 15%;" class="tblHeader" scope="col">End Date</th>
					<th style="width: 20%;" class="tblHeader" scope="col">Skill requirement</th>
					<th style="width: 10%;" class="tblHeaderCenter" scope="col">Edit</th>
					<th style="width: 10%;" class="tblHeaderCenter" scope="col">Delete</th>
				</tr>
				<table cellpadding="0" cellspacing="0" class="tblList">
					<c:forEach items="${listAllocations}" var="allocation">
							<tr>
								<td style="width: 300px;" class="tblDefCenter">${allocation.id}</td>
								<td style="width: 20%;" class="tblDef">${allocation.project.name}</td>
								<td style="width: 15%;" class="tblDef">${allocation.startDateAsString}</td>
								<td style="width: 15%;" class="tblDef">${allocation.endDateAsString}</td>
								<td style="width: 20%;" class="tblDef">${allocation.skill.name}</td>
								<td style="width: 10%;" class="tblDefCenter"><a style="background: deepskyblue;" class="btn btn-primary btn-sm" href="allocations/edit/${allocation.id}" role="button"><i class="far fa-edit"></i></a></td>
								<td style="width: 10%;" class="tblDefCenter"><a onclick="confirmDelete(${allocation.id})" class="btn btn-danger btn-sm" role="button"><i class="fas fa-trash"></i></a></td>
							</tr>
					</c:forEach>
				</table>
			</table>
			
         <div style="padding:10px;">
            <button type="button" onclick="history.back()" class="btn btn-danger"><i class="fas fa-ban"></i> Cancel</button>
         </div>
		</div>
		
		
	
	</body>
	
	<footer>
		<script>
		
			$(function() {
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
		</script>
	</footer>
	
</html>
