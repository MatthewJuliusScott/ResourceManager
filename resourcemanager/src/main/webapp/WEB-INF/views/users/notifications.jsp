<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
	<head>
		<title>User Page</title>
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
	</head>
	
	<body>
		<jsp:include page="/WEB-INF/views/includes/nav.jsp" />
				
		<table class="tbl">
			<tr>
				<th class="tblHeader" style="padding-left: 5px;">#</th>
				<th class="tblHeader"><i class="far fa-eye"></i></th>
				<th class="tblHeader">Message</th>
			</tr>
			<c:forEach items="${user.notifications}" var="notification">
				<tr>
					<td>
						${notification.id}
					</td>
					<td>
						<c:if test = "${!notification.seen}">
							<i class="far fa-eye"></i>
						</c:if>
					</td>
					<td class="tblDef">
						${notification.message}
               		</td>
				</tr>
          	</c:forEach>
       	</table>
              	
        <div>
        	<button type="button" onclick="history.back()" class="btn btn-danger"><i class="fas fa-ban"></i> Cancel</button>
        </div>      	
              	
	</body>
	
	<footer>
		<script>
			
		</script>
	</footer>
	
</html>
