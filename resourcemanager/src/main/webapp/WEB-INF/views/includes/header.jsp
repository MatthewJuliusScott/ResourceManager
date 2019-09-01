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
<%@page import="java.util.NoSuchElementException"%>
<% User user = (User)session.getAttribute("loggedInUser") != null ? (User)session.getAttribute("loggedInUser") : new User();%>
<% LinkedList<String> messages = (LinkedList<String>)session.getAttribute("messages") != null ? (LinkedList<String>)session.getAttribute("messages") : new LinkedList<String>(); %>

<script>
   $(document).ready(function(){
	   <% while (messages.size() > 0) { %>
            try {
               document.getElementById("txtAlertMessage").innerHTML = "<%=messages.pop()%>";
            } catch (NoSuchElementException) {
               document.getElementById("txtAlertMessage").innerHTML = "Something went wrong...";
            }
            $("#alertModal").modal("show");
      <% } %>
   });

   /* Alert messages using Bootstrap modals.
      - strMsg = Message to user
   */
   function BAMJAlert(strMsg){
      document.getElementById("txtAlertMessage").innerHTML = strMsg;
      $("#alertModal").modal("show");
   }

   /* Confirm messages using Bootstrap modals.
      - strMsg = Message to user
      - strPath = location.ref path on confirm.
   */
   function BAMJConfirm(strMsg, strPath){
      document.getElementById("txtConfirmMessage").innerHTML = strMsg;
      document.getElementById("btnModalDelete").onclick = function () { window.location.href = strPath; }
      $("#confirmModal").modal("show");
   }
</script>

<c:set var = "admin" scope = "page" value = "#{loggedInUser != null and loggedInUser.authorityStrings.contains('ROLE_ADMIN')}"/>

<html>
   <body>
      <!-- Alert modal -->
      <div class="modal fade" id="alertModal" role="dialog">
         <div class="modal-dialog">
            <div class="modal-content">
               <div class="modal-header">
               <h4 class="modal-title" style="font-weight:bold;">Error</h4>
                  <button type="button" class="close" data-dismiss="modal">&times;</button>
               </div>
               <div class="modal-body">
                  <p id="txtAlertMessage"></p>
               </div>
               <div class="modal-footer">
                  <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
               </div>
            </div>
         </div>
      </div>
      
      <!-- Confirm modal -->
      <div class="modal fade" id="confirmModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
         <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
               <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLongTitle" style="font-weight:bold;">Warning</h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                     <span aria-hidden="true">&times;</span>
                  </button>
               </div>
               <div class="modal-body">
                  <p id="txtConfirmMessage"></p>
               </div>
               <div class="modal-footer">
                  <button type="button" class="btn btn-primary" data-dismiss="modal" style="background:deepskyblue;">Cancel</button>
                  <button type="button" class="btn btn-danger" id="btnModalDelete">Delete</button>
               </div>
            </div>
         </div>
      </div>
   </body>
</html>