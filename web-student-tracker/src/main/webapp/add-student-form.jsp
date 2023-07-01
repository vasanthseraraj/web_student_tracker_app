<!DOCTYPE html>
<html>
<head>
<title>Add Student</title>
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
		<h3>Student Details</h3>
		<form action="StudentControllerServlet" method="GET">
			<input type="hidden" name="command" value="ADD" />
			<table>
				<tbody>
					<tr>
						<td><label>First Name:</label></td>
						<td><input type=text name="firstName" /></td>
					</tr>
					<tr>
						<td><label>Last Name:</label></td>
						<td><input type=text name="lastName" /></td>
					</tr>
					<tr>
						<td><label>E-Mail:</label></td>
						<td><input type=text name="email" /></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="Submit" value="Confirm" class="save"/></td>
					</tr>

				</tbody>
            </table>
	   </form>
	   <div style="clear: both;"> </div>
	   <p>
	   <a href="StudentControllerServlet">Previous Page</a>
	   </div>
	   </p>