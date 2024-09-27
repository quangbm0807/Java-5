<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Header</title>
</head>
<body>
	<nav
		class="navbar navbar-dark navbar-expand-md navigation-clean-search">
		<div class="container">
			<a class="navbar-brand" href="#">Slide 3 Demo</a>
			<button class="navbar-toggler" data-toggle="collapse"
				data-target="#navcol-1">
				<span class="sr-only">Toggle navigation</span><span
					class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navcol-1">
				<ul class="nav navbar-nav">
					<li class="dropdown"><a
						class="dropdown-toggle nav-link dropdown-toggle"
						data-toggle="dropdown" aria-expanded="false" href="#">Validator form
					</a>
						<div class="dropdown-menu" role="menu">
							<a class="dropdown-item" role="presentation" href="/staff/index">Staff Form</a>
							<a class="dropdown-item" role="presentation" href="/staff2/index">Staff 2 Form</a>
						</div></li>
				</ul>
				<form class="form-inline mr-auto" target="_self">
					<div class="form-group">
						<label for="search-field"><i class="fa fa-search"></i></label><input
							class="form-control search-field" type="search" name="search"
							id="search-field">
					</div>
				</form>
				<span class="navbar-text"><a href="#" class="login">Login</a>
				</span><a class="btn btn-light action-button" role="button" href="#">Registration</a>
			</div>
		</div>
	</nav>
</body>
</html>