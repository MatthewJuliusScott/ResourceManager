<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var = "admin" scope = "page" value = "#{loggedInUser != null and loggedInUser.authorityStrings.contains('ROLE_ADMIN')}"/>
<%@ page import="com.resourcemanager.model.Report" %>
<%@ page import="java.util.List" %>
<% Report report = (Report)request.getAttribute("report"); %>

<html>
	<head>
		<title>Reports</title>
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
		<script src="/resources/js/Chart.min.js"></script>
	</head>
	
	<body>
		<jsp:include page="/WEB-INF/views/includes/nav.jsp" />
		
		<c:url var="addAction" value="/reports/${type}" ></c:url>
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
					<td class="tblDefCenter"><button type="submit" style="background: deepskyblue;" class="btn btn-primary btn-sm"><i class="fas fa-eye"></i>View</button></td>
				</tr>
	       	</table>
       	</form:form>
		
		<div style="max-width: 1500px;">
			<div style="width:100%;">
				<canvas id="myChart"></canvas>
			</div>
		</div>
	</body>	
	
	<footer>
		<script>
			var report = JSON.parse('${report.asJSON}');

			var ctx = document.getElementById('myChart').getContext('2d');
			var config = {
			   type: 'bar',
			   data: {
			      labels: report.dataLabels,
			      datasets: [
			      <% int count = 0; %>
			      <% for (List<String> dataset : report.getData()) { %>
			    	{
			         label: report.labels[<%=count%>],
			         data: report.data[<%=count++%>],
			         backgroundColor: report.colours[<%=count%>],
			         borderColor: report.borderColours[<%=count%>],
			         borderWidth: 1
			         
			      }
			    	 <% if (report.getData().size() > count) { %>
			         ,
			         <% } %>
			     <% } %>
			    ]
			   },
			    options: {
			        scales: {
			            yAxes: [{
			                ticks: {
			                    beginAtZero: true
			                }
			            }]
			        }
			    }
			};

			var chart = new Chart(ctx, config);
			</script>
	</footer>
</html>
