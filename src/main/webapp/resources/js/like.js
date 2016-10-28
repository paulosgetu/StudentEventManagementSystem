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
