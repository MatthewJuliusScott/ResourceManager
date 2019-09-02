<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var = "admin" scope = "page" value = "#{loggedInUser != null and loggedInUser.authorityStrings.contains('ROLE_ADMIN')}"/>

<html>
	<head>
		<title>Reports</title>
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
		<script src="/resources/js/Chart.min.js"></script>
	</head>
	
	<body>
		<jsp:include page="/WEB-INF/views/includes/nav.jsp" />
		<div style="width: 929px;">
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
			      labels: report.labels,
			      datasets: [{
			         label: report.name,
			         data: report.data,
			         backgroundColor: report.colours,
			         borderColor: report.borderColours,
			         borderWidth: 1
			      }]
			   }
			};

			var chart = new Chart(ctx, config);
			</script>
	</footer>
</html>
