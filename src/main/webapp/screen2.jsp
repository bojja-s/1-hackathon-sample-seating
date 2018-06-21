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
    <link href="css/common.css" rel="stylesheet">
<style>
#wrapper {
  margin-left: 300px;
}
#signin {
  float: left;
  width: 40%;
   margin-left: -200px;
  border: 2px solid black
  
}
#signup {
  float: left;
  width: 50%;
  margin-left: -200px
  
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
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
			<h2>
				Welcome ${pageContext.request.userPrincipal.name} | 
				<a onclick="document.forms['logoutForm'].submit()">Logout</a>
			</h2>
		</c:if>
		<br/>
	<div class="container1" id="signup">
			
				<label for="sections">List of Sections: </label>
				<form:form method="POST" id="firstForm" modelAttribute="sectionForm" action="/getsections" class="form-signin">
				<form:select id="sections" name='role' path="sectionId" onchange="myFunc(value)">
					<c:forEach varStatus="loop" items="${sections}" var="section">
						
							<c:if test="${section.id != selectedSection}">
								<option value="${section.id}">${section.name}</option>
							</c:if>
							<c:if test="${section.id == selectedSection}">
								<option value="${section.id}" selected="selected">${section.name}</option>
							</c:if>
						
					</c:forEach>
				</form:select>
				</form:form>
</div><br/>

		<div class="container2" id="signin" >			
			<form:form id="secondForm" method="POST" modelAttribute="sectionForm" action="/booktickets" class="form-signin">	
			<label for="seats">Available Seats: ${seats.size()}</label>
			<div>
				<form:select id="seats" path="seatName" multiple="true" onchange="myFunc2(value)" size="5">
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
				<form:hidden path="sectionId" value=""/>
				<form:hidden path="secretValue" value=""/>
			</div>
			<br/>
			<div>
				<button id="bookTickets"  type="submit">Book Ticket</button>
			</div>
			<br/>
		</form:form>

	</div>
</div>	
	<!-- /container -->
	<script>
function myFunc($val) {
	var sel = document.getElementById('sections');
	var opt = sel.options[sel.selectedIndex];
	document.forms['secondForm']['sectionId'].value = opt.value;
	document.forms['secondForm']['secretValue'].value = opt.text;
	
	document.forms['firstForm'].submit();
       }
function myFunc2($val) {
    alert($val);
   }       
</script>
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
