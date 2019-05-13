<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
	<head>
		<title>Resource Page</title>
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

<a class="btn btn-primary" href="/resources/create" role="button">Link</a>

	
	<h3>Resources List</h3>
	<c:if test="${!empty listResources}">
		<table class="tg">
		<tr>
			<th width="80">Resource ID</th>
			<th width="120">Name</th>
			<th width="60">Edit</th>
			<th width="60">Delete</th>
		</tr>
		<c:forEach items="${listResources}" var="resource">
			<tr>
				<td>${resource.id}</td>
				<td>${resource.name}</td>
				<td><a class="btn btn-primary btn-sm" href="/resources/edit/${resource.id}" role="button">Link</a></td>
				<td><a class="btn btn-primary btn-sm" href="/resources/delete/${resource.id}" role="button">Link</a></td>
			</tr>
		</c:forEach>
		</table>
	</c:if>
		
	</body>
	
	<footer>
	</footer>
	
</html>
