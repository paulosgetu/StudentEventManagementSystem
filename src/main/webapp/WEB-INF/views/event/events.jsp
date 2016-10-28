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
<script type="text/javascript">
	function likeEvent(btnlike) {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");

		if (typeof (btnlike) == "object") {

			var counts = $(btnlike).attr("data-event-likes");
			var eventId = $(btnlike).attr("data-event-id");
			var username = $("#username").text();
			counts = parseInt(counts) + 1;

			var data = {
				'eventId' : eventId,
				'username' : username,
			};

			$
					.ajax({
						type : "POST",
						// contentType : "application/json",
						url : "/eventmanagement/event/event/like",
						data : data,
						// dataType : 'json',
						beforeSend : function(xhr) {
							xhr.setRequestHeader(header, token);
						},
						timeout : 600,
						success : function(data) {
							var r = $('<button data-event-id="'
									+ eventId
									+ '" data-event-likes ="'
									+ counts

									+ '" onclick="unLikeEvent(this);" class="btn btn-sm btn-default like-btn"><span class="glyphicon glyphicon-thumbs-down"></span> UnLike (<span class="counts">'
									+ counts + '</span>)</button>');
							$(btnlike).replaceWith(r);

						},
						error : function(xhr, textStatus, errorThrown) {
							// alert(xhr.responseText);
							$(btnlike).replaceWith(username + eventId);
						}
					});

		} else {
			return false;
		}
	}
	function unLikeEvent(btnlike) {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");

		if (typeof (btnlike) == "object") {

			var eventId = $(btnlike).attr("data-event-id");
			var username = $("#username").text();
			var counts = $(btnlike).attr("data-event-likes");
			counts = parseInt(counts) - 1;
			var data = {
				'eventId' : eventId,
				'username' : username,
			};

			$
					.ajax({
						type : "POST",
						// contentType : "application/json",
						url : "/eventmanagement/event/event/like",
						data : data,
						// dataType : 'json',
						beforeSend : function(xhr) {
							xhr.setRequestHeader(header, token);
						},
						timeout : 600,
						success : function(data) {
							var r = $('<button data-event-id=" '
									+ eventId
									+ '" data-event-likes ="'
									+ counts
									+ '" onclick="likeEvent(this);" class="like-btn btn btn-sm btn-default"><span class="glyphicon glyphicon-thumbs-up"></span> Like (<span class="counts">'
									+ counts + '</span>)</button> ');
							$(btnlike).replaceWith(r);

						},
						error : function(xhr, textStatus, errorThrown) {
						}
					});

		} else {
			return false;
		}

	}

	function addJoin(joinBtn) {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");		
		if (typeof (joinBtn) == "object") {

			var eventId = $(joinBtn).attr("data-event-id");
			var username = $("#username").text();
			var counts = $(joinBtn).attr("data-event-membersCount");
			var waitingCounts = $(joinBtn).attr("data-event-waitingCount");

			waitingCounts = parseInt(waitingCounts) + 1;
			var data = {
				'eventId' : eventId,
				'username' : username,
			};

			$
					.ajax({
						type : "POST",
						// contentType : "application/json",
						url : "/eventmanagement/event/event/join",
						data : data,
						// dataType : 'json',
						beforeSend : function(xhr) {
							xhr.setRequestHeader(header, token);
						},
						timeout : 600,
						success : function(data) {
							var r = $('<button data-event-id=" '
									+ eventId
									+ '" data-event-membersCount ="'
									+ counts
									+ '" onclick="cancelJoin(this);" class="like-btn btn btn-sm btn-default"><span class="glyphicon glyphicon-heart-empty">'
									+ '</span> </span> Cancel Me (<span class="counts">('
									+ counts + ': members in' + waitingCounts
									+ ' waiting) </span>)</button>');
							$(joinBtn).replaceWith(r);

						},
						error : function(xhr, textStatus, errorThrown) {
							alert(xhr);
						}
					});

		} else {
			return false;
		}

	}
	function cancelJoin(joinBtn) {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");

		if (typeof (joinBtn) == "object") {

			var eventId = $(joinBtn).attr("data-event-id");
			var username = $("#username").text();
			var counts = $(joinBtn).attr("data-event-membersCount");
			var waitingCounts = $(joinBtn).attr("data-event-waitingCount");
			waitingCounts = parseInt(waitingCounts) - 1;

			var data = {
				'eventId' : eventId,
				'username' : username,
			};

			$
					.ajax({
						type : "POST",
						// contentType : "application/json",
						url : "/eventmanagement/event/event/cancelJoin",
						data : data,
						// dataType : 'json',
						beforeSend : function(xhr) {
							xhr.setRequestHeader(header, token);
						},
						timeout : 600,
						success : function(data) {
							var r = $('<button data-event-id=" '
									+ eventId
									+ '" data-event-membersCount ="'
									+ counts
									+ '" onclick="addJoin(this);" class="like-btn btn btn-sm btn-default"><span class="glyphicon glyphicon-heart">'
									+ '</span> </span> Add Me (<span class="counts">('
									+ counts + ': members in) ('
									+ waitingCounts
									+ ' waiting) </span>)</button>');
							$(joinBtn).replaceWith(r);

						},
						error : function(xhr, textStatus, errorThrown) {
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
						<!-- Comments -->
						<c:out value=""></c:out>
						<h3>Events</h3>
						<c:forEach var="event" items="${eventsList.content}">
							<div class="well">
								<div class="media">
									<div class="media-left">
										<img style="height: 70;width: 70" src="<spring:url value="/resources/images/${event.id}.jpg"></spring:url>"alt="">
									</div>
									<div class="media-body">
										<h4 class="margin-t-0">
										
											<a href="<spring:url value="/event/event/${event.id}"></spring:url>">${event.id} : ${event.name}</a>
										</h4>
										<p>${event.startTime} - ${event.endTime}</p>
										<p>${event.description}</p>
										<p>
											<c:if test="${event.notFull}">
												<c:choose>
													<c:when
														test="${event.isRegistered(pageContext.request.userPrincipal.name)}">
														<button onclick="cancelJoin(this);"
															data-event-id="${event.id}"
															data-event-membersCount='${event.approvedCounts}'
															data-event-waitingCount='${event.waitingMembersCounts}'
															class="like-btn btn btn-sm btn-default">
															<span class="glyphicon glyphicon-heart-empty"></span>
															Cancel Me (<span class="counts">(${event.approvedCounts}:
																in) (${event.waitingMembersCounts}: waiting)</span>)
														</button>
													</c:when>
													<c:otherwise>
														<button onclick="addJoin(this);"
															data-event-id="${event.id}"
															data-event-membersCount='${event.approvedCounts}'
															data-event-waitingCount='${event.waitingMembersCounts}'
															class="btn btn-sm btn-default like-btn">
															<span class="glyphicon glyphicon-heart"></span> Add Me (<span
																class="counts">(${event.approvedCounts}: members
																in) (${event.waitingMembersCounts}: waiting)</span>)
														</button>
													</c:otherwise>
												</c:choose>


											</c:if>

											<c:choose>
												<c:when
													test="${!event.isLikedBy(pageContext.request.userPrincipal.name)}">
													<button onclick="likeEvent(this);"
														data-event-id="${event.id}"
														data-event-likes='${event.likeCounts}'
														class="like-btn btn btn-sm btn-default">
														<span class="glyphicon glyphicon glyphicon-thumbs-up"></span>
														Like (<span class="counts">${event.likeCounts}</span>)
													</button>
												</c:when>
												<c:otherwise>
													<button onclick="unLikeEvent(this);"
														data-event-id="${event.id}"
														data-event-likes='${event.likeCounts}'
														class="btn btn-sm btn-default like-btn">
														<span class="glyphicon glyphicon-thumbs-down"></span>
														UnLike (<span class="counts">${event.likeCounts}</span>)
													</button>
												</c:otherwise>
											</c:choose>

										</p>
									</div>
								</div>
							</div>
							<hr>

						</c:forEach>

						<!-- Pager -->
						<div>
							<ul class="pager">
								<c:choose>
									<c:when test="${currentIndex == 1}">
										<li class="previous disabled"><a href="#"><span
												class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>
												Older</a></li>
									</c:when>
									<c:otherwise>
										<li class="previous"><a href="${prevUrl}"><span
												class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>
												Older(${currentIndex})</a></li>
									</c:otherwise>
								</c:choose>

								<c:choose>
									<c:when test="${currentIndex == eventsList.totalPages}">
										<li class="next disabled"><a href="#">Newer <span
												class=" glyphicon glyphicon-menu-right" aria-hidden="true"></span></a></li>
									</c:when>
									<c:otherwise>
										<li class="next"><a href="${nextUrl}">Newer <span
												class="glyphicon glyphicon-menu-right" aria-hidden="true"></span></a></li>
									</c:otherwise>
								</c:choose>
							</ul>
						</div>
						<!-- Pager -->

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
