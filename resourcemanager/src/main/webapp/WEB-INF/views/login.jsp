<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<title>ResourceManager Login</title>
		<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" />
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
	</head>
	<body>
		<form action="/login" method="post" autocomplete="off">
			<div>
	            <table class="loginTable" cellpadding="0" cellspacing="0">
	                <!-- logo -->
	                <tr>
	                    <td>
	                        <img src="resources/images/logo.png" alt="Resource Manager Logo" class="centerImg">
	                    </td>
	                </tr>
	
	                <!-- boilerplate text -->
	                <tr>
	                    <td>
	                        <p class="centerText">Please enter your username and password to use the system</p>
	                    </td>
	                </tr>
	                
	                <!-- validation message -->
	                <tr>
	                    <td>
			                <c:if test="${param.error ne null}">
								<div style="color: red; text-align:center;">Invalid credentials.</div>
							</c:if>
						</td>
					</tr>
	
	                <!-- user -->			
	                <tr>
	                    <td>
	                        <div class="textBox">
	                            <input type="text" placeholder="Username" id="username" name="username">
	                            <i class="fa fa-user fa-lg fa-fw" aria-hidden="true"></i>
	                        </div>
	                    </td>
	                </tr>
	
	                <!-- pass -->
	                <tr>
	                    <td>
	                        <div class="textBox">
	                            <input type="password"  placeholder="Password" id="pwd" name="password">
	                            <i class="fa fa-unlock-alt fa-lg fa-fw" aria-hidden="true"></i>
	                        </div>
	                    </td>
	                </tr>
	
	                <!-- login button -->
	                <tr>
	                    <td>
	                        <button type="submit" class="tblLoginButton">Login</button>
	                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	                    </td>
	                </tr>
	            </table>
	
	            <!-- disclaimer text -->
	            <p class="disclaimer">Proudly developed by BAMJ Systems &copy;2019. </p>
	        </div>
		</form>
	</body>
</html>