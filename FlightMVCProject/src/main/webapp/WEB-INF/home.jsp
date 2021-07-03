<%@page import="java.util.Base64"%>
<%@page import="java.util.List"%>
<%@page import="com.nagarro.model.*"%>
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
<style>
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
	<h2>Welcome</h2>
	<div>
		<form class="enter" action="home">
			<div class="row mb-3">
				<label for="colFormLabelSm"
					class="col-sm-4 col-form-label col-form-label-sm">Departure</label>
				<div class="col-sm-8">
					<input type="text" class="form-control form-control-sm"
						id="colFormLabelSm" placeholder="" name="dep" required>
				</div>
			</div>
			<div class="row mb-3">
				<label for="colFormLabelSm1"
					class="col-sm-4 col-form-label col-form-label-sm">Arrival</label>
				<div class="col-sm-8">
					<input type="text" class="form-control form-control-sm"
						id="colFormLabelSm1" placeholder="" name="arr" required>
				</div>
			</div>
			<div class="row mb-3">
				<label for="colFormLabelSm2"
					class="col-sm-4 col-form-label col-form-label-sm">Flight
					Date</label>
				<div class="col-sm-8">
					<input type="text" class="form-control form-control-sm"
						id="colFormLabelSm2" placeholder="" name="date" required>
				</div>
			</div>
			<div class="row mb-3">
				<label for="colFormLabelSm3"
					class="col-sm-4 col-form-label col-form-label-sm">Flight
					Class</label>
				<div class="col-sm-8">
					<input type="text" class="form-control form-control-sm"
						id="colFormLabelSm3" placeholder="" name="class" required>
				</div>
			</div>
			<div class="row mb-3">
				<label for="colFormLabelSm4"
					class="col-sm-4 col-form-label col-form-label-sm">Preference</label>
				<div class="col-sm-8">
					<input type="text" class="form-control form-control-sm"
						id="colFormLabelSm4" placeholder="" name="pref" required>
				</div>
			</div>
			<div>
				<button type="submit" class="btn btn-primary form-control-sm">Search</button>
			</div>
		</form>
	</div>
	<table>
		<tr>
			<th width="100px">S.No</th>
			<th width="125px">Flight No.</th>
			<th width="130px">Departure</th>
			<th width="130px">Arrival</th>
			<th width="140px">Valid Till</th>
			<th width="120px">Time</th>
			<th width="140px">Duration</th>
			<th width="120px">Fare</th>
			<th width="120px">Available</th>
			<th width="120px">Flight Class</th>
		</tr>
		<%
			if (request.getAttribute("data") != null) {
				List<Flight> al = (List<Flight>) request.getAttribute("data");
				int i = 1;
				String type = (String) request.getAttribute("type");
				for (Flight f : al) {
		%>
		<tr>
			<td><%=i%></td>
			<td><%=f.getFlightnumber()%></td>
			<td><%=f.getDeparture()%></td>
			<td><%=f.getArrival()%></td>
			<td><%=f.getValid()%></td>
			<td><%=f.getTime()%></td>
			<td><%=f.getDuration()%></td>
			<%
				if (type.equalsIgnoreCase("E")) {
			%>
			<td><%=f.getFare()%></td>

			<%
				} else {
			%>
			<td><%=f.getBusinessfare()%></td>
			<%
				}
			%>
			<td><%=f.getAvailable()%></td>
			<td><%=f.getFlightclass()%></td>
		</tr>


		<%
			i++;
				}
			}
		%>
	</table>
</body>
</html>