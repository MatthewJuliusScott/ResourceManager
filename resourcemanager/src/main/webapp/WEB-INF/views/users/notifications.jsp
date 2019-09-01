<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var = "admin" scope = "page" value = "#{loggedInUser != null and loggedInUser.authorityStrings.contains('ROLE_ADMIN')}"/>

<html>
	<head>
		<title>User Page</title>
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
	</head>
	
	<body>
		<jsp:include page="/WEB-INF/views/includes/nav.jsp" />
				
		<table class="tbl">
			<tr>
				<th class="tblHeaderCenter">#</th>
				<th class="tblHeaderCenter">Received Date</i></th>
				<th class="tblHeader">Read</i></th>
				<th class="tblHeader">Message</th>
				<th class="tblHeaderCenter">Delete</th>
			</tr>
			<c:forEach items="${user.notifications}" var="notification">
				<tr>
					<td class="tblDefCenter">
						${notification.id}
					</td>
					<td class="tblDefCenter">
						${notification.createdDateAsString}
					</td>
					<td class="tblDef">
						<c:if test = "${!notification.seen}">
							<button type="button" onclick="markNotificationAsSeen(${notification.id})" class="btn btn-success"><i class="far fa-eye"></i></button>
						</c:if>
					</td>
					<td class="tblDef">
						${notification.message}
         		</td>
         		<td class="tblDefCenter">
						<button type="button" onclick="deleteNotification(${notification.id})" class="btn btn-danger"><i class="fas fa-trash"></i></button>
					</td>
				</tr>
          	</c:forEach>
       	</table>

         <div style="padding:10px;">
            <button type="button" onclick="history.back()" class="btn btn-danger"><i class="fas fa-ban"></i> Cancel</button>
         </div>
	</body>
	
	<footer>
		<script>
			function markNotificationAsSeen(id) {
				var form = document.createElement("form");
			    form.method = "POST";
			    form.action = "/users/notifications/seen/" + id;   
			    document.body.appendChild(form);
			    form.submit();
			}
			
			function deleteNotification(id) {
				var form = document.createElement("form");
			    form.method = "POST";
			    form.action = "/users/notifications/delete/" + id;   
			    document.body.appendChild(form);
			    form.submit();
			}
		</script>
	</footer>
	
</html>
