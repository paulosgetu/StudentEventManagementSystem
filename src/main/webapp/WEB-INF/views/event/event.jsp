<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ page session="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="author" content="Event Handlers">
<meta name="robots" content="index, follow">
<!-- For the ajax -->
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<!-- For the ajax -->
<!-- icons -->
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
<script type="text/javascript">
	function accept(btn) {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		if (typeof (btn) == "object") {

			var eventId = $(btn).attr("data-event-id");
			var userId = $(btn).attr("data-user-id");

			var data = {
				'eventId' : eventId,
				'userId' : userId,
			};
			$.ajax({
				type : "POST",
				// contentType : "application/json",
				url : "/eventmanagement/event/event/accept",
				//dataType : 'json',
				data : data,
				beforeSend : function(xhr) {
					xhr.setRequestHeader(header, token);
				},
				timeout : 6000,
				success : function(data) {
					$(btn).closest("tr").remove()
				},
				error : function(xhr, textStatus, errorThrown) {
					alert("Can't complete the task" + errorThrown);
				}
			});

		} else {
			return false;
		}
	}
	function deny(btn) {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");

		if (typeof (btn) == "object") {

			var eventId = $(btn).attr("data-event-id");
			var userId = $(btn).attr("data-user-id");

			var data = {
				'eventId' : eventId,
				'userId' : userId
			};

			$.ajax({
				type : "POST",
				// contentType : "application/json",
				url : "/eventmanagement/event/event/deny",
				data : data,
				// dataType : 'json',
				beforeSend : function(xhr) {
					xhr.setRequestHeader(header, token);
				},
				timeout : 6000,
				success : function(data) {
					$(btn).closest("tr").remove()
				},
				error : function(xhr) {
					alert("Faild to complete the task." + xhr);
				}

			});

		} else {
			return false;
		}
	}
</script>
</head>
<body>
	<tiles:insertDefinition name="defaultTemplate">
		<tiles:putAttribute name="body">

			<!-- /.row -->
			<div class="row">
				<!--Content-->
				<div class="col-sm-8 col-sm-push-4">

					<c:url var="prevUrl" value="/event/events/${currentIndex - 1}" />
					<c:url var="nextUrl" value="/event/events/${currentIndex + 1}" />

					<div>
						<div>
							<div class="row">
								<div class="col-sm-8 col-sm-push-4">
									<div class="page-header">
										<h1>${event.id}:${event.name}</h1>
										<p>By ${event.createdBy.name}</p>
										<p>
											<span class="glyphicon glyphicon-time"> </span>
											${event.startTime}${event.endTime}
										</p>
									</div>
								</div>
							</div>
							
							<figure class="margin-b-2">
								<img class="img-responsive" src="<spring:url value="/resources/images/${id}.jpg"></spring:url>"
									alt="">
									
								<figcaption class="margin-t-h">${event.name}</figcaption>
							</figure>
							<p>${event.startTime}-${event.endTime}</p>
							<p class="lead">${event.description}</p>
							<hr>
						</div>

						<div class="panel panel-info">
							<div class="panel-heading">Members Waiting For Approval</div>
							<div class="panel-body">
								<table class="table table-striped table-hover table-users">
									<thead class="head-inverse">
										<tr>
											<th class="hidden-phone">#</th>
											<th class="hidden-phone">Full Name</th>
											<th>Email</th>
											<th>Gender</th>
											<th></th>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="user" items="${event.waitingMembers}">
											<tr>
												<td class="hidden-phone">${user.id}</td>
												<td>${user.name}</td>
												<td>${user.email}</td>
												<td class="hidden-phone">${user.gender}</td>
												<security:authorize access="hasRole('ROLE_ADMIN')">
													<td><button data-user-id="${user.id}"
															data-event-id="${event.id}" class="btn mini blue-stripe"
															onclick="accept(this);">Accept</button></td>
													<td><button data-user-id="${user.id}"
															data-event-id="${event.id}" onclick="deny(this);"
															class="confirm-delete btn mini red-stripe" role="button">Deny</button></td>
												</security:authorize>
											</tr>

										</c:forEach>

									</tbody>
								</table>

							</div>
						</div>
						<div class="panel panel-success">
							<div class="panel-heading">Approved Members</div>
							<div class="panel-body">
								<table class="table table-striped table-hover table-users">
									<thead>
										<tr>
											<th class="hidden-phone">#</th>
											<th class="hidden-phone">Full Name</th>
											<th>Email</th>
											<th>Gender</th>
											<th></th>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="user" items="${event.approvedMembers}">
											<tr>
												<td class="hidden-phone">${user.id}</td>
												<td>${user.name}</td>
												<td>${user.email}</td>
												<td class="hidden-phone">${user.gender}</td>
											</tr>

										</c:forEach>

									</tbody>
								</table>
							</div>
						</div>



					</div>

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
