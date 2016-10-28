function addJoin(joinBtn) {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
alert('hello');
	if (typeof (joinBtn) == "object") {

		var eventId = $(joinBtn).attr("data-event-id");
		var username = $("#username").text();
		var counts = $(joinBtn).attr("data-event-membersCount");
		counts = parseInt(counts) - 1;
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
								+ counts + ': members in)</span>)</button>');
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
		counts = parseInt(counts) - 1;
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
								+ '" onclick="addJoin(this);" class="like-btn btn btn-sm btn-default"><span class="glyphicon glyphicon-heart">'
								+ '</span> </span> Add Me (<span class="counts">('
								+ counts + ': members in)</span>)</button>');
						$(joinBtn).replaceWith(r);

					},
					error : function(xhr, textStatus, errorThrown) {
					}
				});

	} else {
		return false;
	}

}
