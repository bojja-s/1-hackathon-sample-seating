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
    <title>Register or Login </title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/common.css" rel="stylesheet">
<style>
#wrapper {
  margin-left: 300px;
}
#signin {
  float: right;
  width: 70%;
   margin-left: -200px;
  border: 2px solid black
  
}
#signup {
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

 <h2>Welcome</h2>
<div class="container1" id="signin">

    <form method="POST" action="${contextPath}/login" class="form-signin">
        <h2 class="form-heading">&nbsp;&nbsp;&nbsp;Log in</h2>

        <div class="form-group ${error != null ? 'has-error' : ''}">
            <span>${message}</span>
            <div>
	            <label for="nameInput">&nbsp;&nbsp;&nbsp;Username: </label>  
	            <input name="username" type="text" class="form-control" placeholder=""
	                   autofocus="true"/>
            </div>
            <br/>
            <div>
            <label for="nameInput">&nbsp;&nbsp;&nbsp;Password: </label>  
            <input name="password" type="password" class="form-control" placeholder=""/>
            <span>${error}</span>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</div>
			<br/>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
            
        </div>

    </form>

</div>
<!-- /container -->

<div class="container2" id="signup" >

    <form:form method="POST" action="${contextPath}/registration" modelAttribute="userForm" class="form-signin">
       <h2 class="form-heading">&nbsp;&nbsp;&nbsp;Register</h2>
        
		<spring:bind path="firstName">
			<div class="form-group ${status.error ? 'has-error' : ''}">
			<form:hidden path=""/>
				<label for="firstName">&nbsp;&nbsp;&nbsp;First Name:&nbsp;&nbsp;&nbsp; </label>  
				<form:input type="text" path="firstName" class="form-control" placeholder=""
							autofocus="true"></form:input>
				<form:errors path="firstName"></form:errors>
			</div>
			<br/>
		</spring:bind>
		
		<spring:bind path="lastName">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label for="lastName">&nbsp;&nbsp;&nbsp;Last Name:&nbsp;&nbsp;&nbsp; </label> 
				<form:input type="text" path="lastName" class="form-control" placeholder=""
							autofocus="true"></form:input>
				<form:errors path="lastName"></form:errors>
			</div>
			<br/>
		</spring:bind>
		
		<spring:bind path="email">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label for="email">&nbsp;&nbsp;&nbsp;Email: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
				<form:input type="text" path="email" class="form-control" placeholder=""
							autofocus="true"></form:input>
				<form:errors path="email"></form:errors>
			</div>
			<br/>
		</spring:bind>
		
		<spring:bind path="location">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label for="location">&nbsp;&nbsp;&nbsp;Location: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </label>  
				<form:input type="text" path="location" class="form-control" placeholder=""
							autofocus="true"></form:input>
				<form:errors path="location"></form:errors>
			</div>
			<br/>
		</spring:bind>
		<spring:bind path="state">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label for="state">&nbsp;&nbsp;&nbsp;State:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>  
				<form:select path="state">
				    <form:options items="${states}" />
				</form:select>	
				</div>
				<br/>
		</spring:bind>
		
		<spring:bind path="password">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label for="password">&nbsp;&nbsp;&nbsp;Password: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>  
				<form:input type="text" path="password" class="form-control" placeholder=""
							autofocus="true"></form:input>
				<form:errors path="password"></form:errors>
			</div>
			<br/>
		</spring:bind>

        <spring:bind path="passwordConfirm">
            <div class="form-group ${status.error ? 'has-error' : ''}">
            	<label for="passwordConfirm">&nbsp;&nbsp;&nbsp;PW Confirm: </label>  
                <form:input type="password" path="passwordConfirm" class="form-control"
                            placeholder="Confirm your password"></form:input>
                <form:errors path="passwordConfirm"></form:errors>
            </div>
            <br/>
        </spring:bind>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>

</div>
<div id="cleared"></div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
