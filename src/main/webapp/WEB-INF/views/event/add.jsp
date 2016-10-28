<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="author" content="Event Handlers">
<meta name="robots" content="index, follow">

<!-- icons -->
<link rel="apple-touch-icon" href="assets/img/apple-touch-icon.png">
<link rel="shortcut icon" href="/resources/favicon.ico">

<!-- Bootstrap Core CSS file -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">


<!-- Override CSS file - add your own CSS rules -->
<link rel="stylesheet" href="/resources/assets/css/styles.css">

<!-- Conditional comment containing JS files for IE6 - 8 -->
<!--[if lt IE 9]>
			<script src="assets/js/html5.js"></script>
			<script src="assets/js/respond.min.js"></script>
		<![endif]-->

<title>Home</title>
</head>
<body>
	<tiles:insertDefinition name="defaultTemplate">
		<tiles:putAttribute name="body">

			<!-- /.row -->
			<div class="row">
				<!--Content-->
				<div class="col-sm-8 col-sm-push-4">
					<h3>Add New Event</h3>
					<p></p>
					<div class="well">
						<c:set var="url" value='?${_csrf.parameterName}=${_csrf.token}' />
						<spring:hasBindErrors name="userForm">
							<c:forEach var="error" items="${errors.allErrors}">
								<p class='alert alert-danger'>
									<spring:message message="${error}" />
								</p>
							</c:forEach>
						</spring:hasBindErrors>
						<p
							class="form-group ${success != null ? 'alert alert-success' :'' }">
							<span>${EventAddinSuccess}</span>
						</p>
						<form action="../event/add${url}"
							method="POST">
							<div class="form-group">
								<label for="image">Image: </label> <input type='file'
									name="newEvent.image" class="form-control" id="image" />
							</div>

							<div class="form-group">
								<label for="name">Name</label> <input name="newEvent.name"
									class="form-control" id="name" placeholder="Enter Event name" />
							</div>

							<div class="form-group">
								<label for="date">Date</label> <span> <input
									name="newEvent.startTime" class="form-control" id="startTime"
									placeholder="Enter Start Time Time HH:mm dd/MM/yyyy" />
								</span> <span> <input name="newEvent.endTime"
									class="form-control" id="startTime"
									placeholder="Enter End Time HH:mm dd/MM/yyyy" />
								</span>

							</div>

							<div class="form-group">
								<label for="name">Acceptance Limit</label> <input
									name="newEvent.membersSizeLimit" class="form-control" id="name"
									placeholder="Enter Acceptnce Limit" />
							</div>

							<div class="form-group">
								<label for="category">Category</label> 
								<select	name="newEvent.category" class="form-control" id="category">
									<c:forEach items="${categories}" var="category">
										<option value="${category.name}">${category.name}</option>
									</c:forEach>
								</select>
							</div>

							<div class="form-group">
								<label for="description">Description</label>
								<textarea name="newEvent.description" class="form-control"
									id="description"></textarea>
							</div>

							<div class="form-group">
								<label for="description">Resources Requirment
									Description</label>
								<textarea name="resourcesDescription" class="form-control"
									id="description"></textarea>
							</div>


							<button type="submit" class="btn btn-default">Submit</button>
						</form>
					</div>
					<hr>


				</div>
				<jsp:include page="${request.contextPath}/sidebar"></jsp:include>

			</div>
			<!-- /.row -->

		</tiles:putAttribute>
	</tiles:insertDefinition>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
	<!-- Bootstrap Core scripts -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
		crossorigin="anonymous"></script>

</body>
</html>
