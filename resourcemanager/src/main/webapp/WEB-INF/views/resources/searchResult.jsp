<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<table class="table">
	<tr>
		<th>Name</th>
		<th>Hours</th>
		<th></th>
	</tr>
<c:forEach items="${listResources}" var="resource">
	<tr>
		<td>${resource.name}</td>
		<td>${resource.hours}</td>
		<td><input type="radio" name="resource" value="${resource.id}"></td>
	</tr>
</c:forEach>
	
</table>