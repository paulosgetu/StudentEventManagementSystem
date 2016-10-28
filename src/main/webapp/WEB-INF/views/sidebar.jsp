<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- Side Bar-->
	<div class="col-sm-4 col-sm-pull-8">

		<!-- list group -->
		<div class="list-group margin-b-3">
			<a href="<spring:url value="/event/events"></spring:url>" class="list-group-item">Recently Happening Events</a> 
			<a href="<spring:url value="/event/joinRequests"></spring:url>" class="list-group-item">Join Event Requests</a> 
			<a href="<spring:url value="/event/joined/${pageContext.request.userPrincipal.name}"></spring:url>" class="list-group-item">Joined Events</a> 
			<security:authorize access="hasRole('ROLE_ADMIN')"><a href="<spring:url value="/event/add"></spring:url>" class="list-group-item">New Event Requests</a> </security:authorize>			
		</div>

	</div>
</body>
</html>