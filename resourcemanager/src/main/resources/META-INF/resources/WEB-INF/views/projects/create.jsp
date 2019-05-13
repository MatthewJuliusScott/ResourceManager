<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
	<head>
		<title>Manage Project</title>
		<style type="text/css">
			.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
			.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
			.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
			.tg .tg-4eph{background-color:#f9f9f9}
			td {vertical-align:top;}
			.left-arrow {float:left;}
			.right-arrow {float:right;}
		</style>
		<script src="<c:url value="/resources/js/jquery-3.3.1.min.js" />"></script>
	</head>
	
	<body>
			
	<h1>Manage Project</h1>
	
	<c:if test="${!empty project.name}">
		<h2>Add a Requirement</h2>
		<p>Start Date: <input name="startDate" id="startDate" type="text" class="datepicker"></p>
		<p>End Date: <input name="endDate" id="endDate" type="text" class="datepicker"></p>
		<p>Skill</p>
		<select onchange="addResource(this)" id="allSkills">
			<c:forEach items="${listSkills}" var="skill">
				<option value="${skill.id}">${skill.name}</option>
   			</c:forEach>
		</select>
	</c:if>
	
	</body>
	
	<footer>
		<script>			
			$(function() {   
			    $( "#startDate" ).datepicker({   
			      defaultDate: "+1w",  
			      changeMonth: true,   
			      numberOfMonths: 1,  
			      onClose: function( selectedDate ) {  
			        $( "#endDate" ).datepicker( "option", "minDate", selectedDate );  
			      }
			    });  
			    $( "#startDate" ).datepicker({
			      defaultDate: "+1w",
			      changeMonth: true,
			      numberOfMonths: 1,
			      onClose: function( selectedDate ) {
			        $( "#endDate" ).datepicker( "option", "maxDate", selectedDate );
			      }
			    });  
			  });  
		</script>
	</footer>
	
</html>
