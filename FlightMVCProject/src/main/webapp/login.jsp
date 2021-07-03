<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">
<style type="text/css">
.enter {
	width: 400px;
	height: 300px;
	top: 200px;
	margin: auto;
	align-content: center;
}

#input {
	width: 200px;
}
</style>
</head>
<body>
	<h2>Login In</h2>
	<form action="login" class="enter">
		<div class="row mb-3">
			<label for="colFormLabelSm4"
				class="col-sm-4 col-form-label col-form-label-sm">User Name</label>
			<div class="col-sm-8">
				<input type="text" class="form-control form-control-sm"
					id="colFormLabelSm4" placeholder="" name="user" required>
			</div>
		</div>
		<div class="row mb-3">
			<label for="colFormLabelSm5"
				class="col-sm-4 col-form-label col-form-label-sm">Password</label>
			<div class="col-sm-8">
				<input type="password" class="form-control form-control-sm"
					id="colFormLabelSm5" placeholder="" name="pass" required>
			</div>
		</div>
		<div>
			<button type="submit" class="btn btn-primary form-control-sm">Log In</button>
		</div>
	</form>
</body>
</html>