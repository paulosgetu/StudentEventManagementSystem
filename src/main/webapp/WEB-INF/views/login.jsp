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
		<form method="POST" class="form-signin">
			<h2 class="form-signin-heading">Please login</h2>
			<div class="form-group ${error != null ? 'has-error alert alert-danger' : ''}">
				<span>${message}</span> <span>${error}</span>
			</div>
			<input type="text" class="form-control" name="username"
				placeholder="Email Address" 
				autofocus="autofocus" /> <input type="password"
				class="form-control" name="password" placeholder="Password"
				/> <label class="checkbox"> <input
				type="checkbox" id="rememberMe"
				name="rememberMe"> Remember me
			</label> <input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

			<button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
			<h4 class="text-center">
				<a href="user/signup">Create an account</a>
			</h4>

		</form>
	</div>


</body>
</html>