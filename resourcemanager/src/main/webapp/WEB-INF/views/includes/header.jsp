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
<%@page import="com.resourcemanager.model.User"%>
<% User user = (User)session.getAttribute("loggedInUser") != null ? (User)session.getAttribute("loggedInUser") : new User();%>

