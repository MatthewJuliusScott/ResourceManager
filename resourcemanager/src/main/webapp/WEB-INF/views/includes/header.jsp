<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="/resources/js/jquery-3.4.1.min.js"></script>
<script src="/resources/js/jquery-ui.min.js"></script>
<link rel="stylesheet" href="/resources/css/jquery-ui.min.css">
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/style.css">
<script src="/resources/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="/resources/css/all.min.css">
<link rel="icon" href="/resources/images/favicon.png" type="image/x-icon"/>
<link rel="shortcut icon" href="/resources/images/favicon.png" type="image/x-icon"/>
<style> <%@ include file="/WEB-INF/css/styles.css" %> </style>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page import="java.util.LinkedList"%>
<%@page import="com.resourcemanager.model.User"%>


<% User user = (User)session.getAttribute("loggedInUser") != null ? (User)session.getAttribute("loggedInUser") : new User();%>
<% LinkedList<String> messages = (LinkedList<String>)session.getAttribute("messages") != null ? (LinkedList<String>)session.getAttribute("messages") : new LinkedList<String>(); %>

<script>
	function displayMessages() {
	  <% while (messages.size() > 0) { %>
	  	customModal("alert", "Error", "<%=messages.pop()%>").then(function(val){
        	// this is where you put code if you want to do something when the user clicks 'ok'
        }).catch(function(err){
        	// this is where you put code if you want to do something when the user clicks 'cancel'
        });
	  <% } %>
	}
	
	$( document ).ready(function() {
		displayMessages(); 
	});
			
	function customModal(type, title, message){
			
		if (type === "alert") {
			$("#modal-close").show();
			$("#modal-ok").hide();
			$("#modal-cancel").hide();
		} else if (type === "confirm") {
			$("#modal-close").hide();
			$("#modal-ok").show();
			$("#modal-cancel").show();
		} 
		
		$("#modal-title").html(title);
		$("#modal-message").html(message);
		
        return new Promise(function(resolve, reject){
        	 $('#customModal').modal('show');
             $('#customModal .btn-default').click(function(){
          		resolve("user clicked close");
          	 });
             $('#customModal .btn-success').click(function(){
             		resolve("user clicked ok");
             });
             $('#customModal .btn-danger').click(function(){
             		reject("user clicked cancel");
             });
        });
	}
</script>

<!-- Modal -->
<div class="modal fade" id="customModal" role="dialog">
  <div class="modal-dialog">
  
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <h4 id ="modal-title" class="modal-title">Modal Title</h4>
      </div>
      <div class="modal-body">
        <p id="modal-message"></p>
      </div>
      <div class="modal-footer">
        <button type="button" id="modal-close" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" id="modal-ok" class="btn btn-success" data-dismiss="modal">Ok</button>
        <button type="button" id="modal-cancel" class="btn btn-danger" data-dismiss="modal">Cancel</button>
      </div>
    </div>
    
  </div>
</div>

<c:set var = "admin" scope = "page" value = "#{loggedInUser != null and loggedInUser.authorityStrings.contains('ROLE_ADMIN')}"/>