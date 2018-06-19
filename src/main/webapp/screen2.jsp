<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">
	<title>Book Tickets View</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
	<div id="wrapper">
		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<form id="logoutForm" method="POST" action="${contextPath}/logout">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
			<h2>
				Welcome ${pageContext.request.userPrincipal.name} | 
				<a onclick="document.forms['logoutForm'].submit()">Logout</a>
			</h2>
		</c:if>
		<div class="form-group">
			<form:form method="POST" modelAttribute="sectionForm" action="/booktickets" class="form-signin">
				Select Section: 
				<form:select id="sections" name='role' path="sectionId" onchange="myFunc(value)">
					<c:forEach varStatus="loop" items="${sections}" var="section">
						<c:choose>
							<c:when test="${loop.index eq 0}">
								<option value="${section.getId()}" selected="true">${section.getName()}</option>
							</c:when>
							<c:otherwise>
								<option value="${section.getId()}">${section.getName()}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</form:select>
			Select Seat:
			<form:select id="seats" path="seatName" multiple="true" onchange="myFunc2(value)">
				<c:forEach varStatus="loop" items="${seats}" var="seat">
					<c:choose>
						<c:when test="${loop.index eq 0}">
							<option value="${seat.getName()}" selected="true">${seat.getName()}</option>
						</c:when>
						<c:otherwise>
							<option value="${seat.getName()}">${seat.getName()}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</form:select>

			<button id="bookTickets"  type="submit">Book Ticket</button>
		</form:form>
		<div id="cleared"></div>
	</div>
	<!-- /container -->
	<script>
function myFunc($val) {
        alert($val);
       }
function myFunc2($val) {
    alert($val);
   }       
</script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
