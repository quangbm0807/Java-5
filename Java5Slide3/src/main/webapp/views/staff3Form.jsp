<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>STAFF 3 FORM</title>
    <link rel="icon" type="image/x-icon" href="/images/iconFavion.png">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Bitter:400,700">
<link rel="stylesheet" href="/css/forHeader.css">
<style>
.form {
	border: 1px solid rgb(209, 209, 209);
	background-color: white;
}
</style>
</head>
<body>

	<div class="header-dark">
		<%--Header --%>
		<jsp:include page="/views/header.jsp"></jsp:include>

		<div class="container hero">
			<div class="row">
				<div class="col-md-8 offset-md-2">
					<form:form action="/staff3/save" modelAttribute="staff3"
						class="m-auto rounded p-5 form">
						<h4 class="h4 mt-3 font-weight-bold">
							Staff Information <i
								class="fa fa-assistive-listening-systems" aria-hidden="true"></i>
						</h4>
						<hr>
						<div class="form-group">
							<form:label class="form-group" path="id">Staff ID:</form:label>
							<form:input path="id" class="form-control" />
							<form:errors path="id" class="badge badge-danger" />
						</div>
						<div class="form-group">
							<form:label class="form-group" path="fullName">Full name:</form:label>
							<form:input path="fullName" class="form-control" />
							<form:errors path="fullName" class="badge badge-danger" />
						</div>
						<div class="form-group">
							<form:label class="form-group" path="email">Email:</form:label>
							<form:input path="email" class="form-control" />
							<form:errors path="email" class="badge badge-danger" />
						</div>
						<div class="form-group">
							<form:label class="form-group" path="salary">Salary:</form:label>
							<form:input path="salary" class="form-control" />
							<form:errors path="salary" class="badge badge-danger" />
						</div>
						<div class="form-group">
							<form:label class="form-group" path="fullName">Gender:</form:label>
							<form:radiobutton path="gender" value="true" label="Male"/>
							<form:errors path="gender" class="badge badge-danger" />
						</div>
						<div class="form-group">
							<form:label class="form-group" path="position">Position:</form:label>
							<form:select path="position">
							<form:option value="CEO">Chief Executive Officer </form:option>
							<form:option value="DIR">Director </form:option>
							<form:option value="MAN">Manager </form:option>
							<form:option value="EMP">Employee </form:option>
							</form:select>
							<form:errors path="position" class="badge badge-danger" />
						</div>
						
						<button class="btn btn-danger" type="submit">Save</button>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>
</body>
</html>