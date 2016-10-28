<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<security:csrfMetaTags />
<title>Log in</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href='<spring:url value="/resources/css/loginStyle.css"/>' />
</head>
<body>

	<div class="wrapper">
		<form:form modelAttribute="user" cssClass="form-signin">

			<h2 class="form-signin-heading">Create Your Account</h2>

			<form:errors path="*" cssClass="alert alert-danger" element="div" />
			<form:input cssClass="form-control" path="name" name="Fullname"
				placeholder="Full Name" autofocus="autofocus" />
			<form:input class="form-control" path="login.username"
				name="username" placeholder="Email Address" autofocus="autofocus" />

			<form:password tyep="password" class="form-control"
				path="login.password" name="password" placeholder="Password" />
			<form:select cssClass="form-control" path="gender">
				<form:option value="Male"></form:option>
				<form:option value="Female"></form:option>
			</form:select>
			<br />
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
				up</button>

			<h4 class="text-center">
				<button class="btn btn-lg btn-primary btn-block" type="reset">Clear</button>
				<h4 class="text-center">

					<a href="login">Go to Login Page</a>
				</h4>
		</form:form>
	</div>


</body>
</html>