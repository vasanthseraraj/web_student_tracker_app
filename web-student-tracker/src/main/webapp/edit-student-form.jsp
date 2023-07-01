<!DOCTYPE html>
<html>
<head>
<title>Update Student</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2 align="center">Anna University</h2>

		</div>
	</div>
	<div id="container">
		<h3>Edit Student</h3>
		<form action="StudentControllerServlet" method="GET">
			<input type="hidden" name="command" value="EDIT" /> <input
				type="hidden" name="studentId" value="${THE_STUDENT.id}" />
			<table>
				<tbody>
					<tr>
						<td><label>First Name:</label></td>
						<td><input type=text name="firstName"
							value="${THE_STUDENT.firstName}" /></td>
					</tr>
					<tr>
						<td><label>Last Name:</label></td>
						<td><input type=text name="lastName"
							value="${THE_STUDENT.lastName}" /></td>
					</tr>
					<tr>
						<td><label>E-Mail:</label></td>
						<td><input type=text name="email"
							value="${THE_STUDENT.email}" /></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="Submit" value="Save" class="save" /></td>
					</tr>

				</tbody>
			</table>
		</form>
		<div style="clear: both;"></div>
		<p>
			<a href="StudentControllerServlet">Previous Page</a>
	</div>
	</p>