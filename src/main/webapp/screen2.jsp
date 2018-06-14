<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Create an account</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<style>
#wrapper {
  margin-left: 300px;
}
#seats {
  float: right;
  width: 70%;
   margin-left: -200px;
  border: 2px solid black
  
}
#sections {
  float: left;
  width: 40%;
  margin-left: -200px;
  border: 2px solid black
  
}
#cleared {
	
  clear: both;
}
</style>     
</head>
<body>
<div id="wrapper">

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>

    </c:if>
<div class="container1" id="sections">		
		Select Section: 
		<form:select path="sections" name='sections' onchange="myFunc(value)">
			<c:forEach varStatus="loop" items="${sections}" var="section">
				<c:choose> 
					<c:when test="${loop.index eq 0}"> 
						<option value="${section.getId()}" selected="true">${section.getName()}</option>
						<option value="2" selected="true">104</option>
					</c:when> 
					<c:otherwise> 
						<option value="${section.getId()}">${section.getName()}</option> 
					</c:otherwise> 						
				</c:choose> 	
			</c:forEach>
		</form:select> 
</div>		
<div class="container2" id="seats">			
		Select Seats: 
		<form:select path="sections" name='seats'>
			<c:forEach varStatus="loop" items="${sections}" var="section">
				<c:forEach items="${section.getSeats()}" var="seat">
					<option value="${seat.getId()}">${seat.getName()}</option>
				</c:forEach>
			</c:forEach>	
		</form:select>
	</div>
	<div id="cleared"></div>
</div>	
<!-- /container -->
<script>
function myFunc($val) {
        alert($val);
       }
</script>       
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
