<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="col-sm-4 col-sm-pull-8">
		<!-- Search -->
		<div class="well">
			<h4 class="margin-t-0">Search By Name</h4>
			<form action="listByName" method="GET">
				<div class="input-group">
					<label class="sr-only" for="search-form">Search the site</label> <input
						name="name" type="text" class="form-control" id="search-form" />
					<span class="input-group-btn">
						<button class="btn btn-default" type="submit">
							<span class="glyphicon glyphicon-search"></span> <span
								class="sr-only"></span>
						</button>
					</span>
				</div>
			</form>
		</div>
		<div class="well">
			<h4 class="margin-t-0">Search By Type</h4>
			<form action="listByType">
				<div class="input-group">
					<label class="sr-only" for="search-form">Search the site</label> <input
						name="type" type="text" class="form-control" id="search-form" />
					<span class="input-group-btn">
						<button class="btn btn-default" type="submit">
							<span class="glyphicon glyphicon-search"></span> <span
								class="sr-only">Search By Type</span>
						</button>
					</span>
				</div>
			</form>
		</div>

		<form action="list" method="get">
			<input id="submit" type="submit" value="List Resource">
		</form>

	</div>
</body>
</html>