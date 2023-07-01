<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Details</title>
</head>
<body>
<h2 align="center">Enter the Details</h2>
<hr>
<form action="UserServlet" method="GET">
<input type="hidden" name="insert" value="INSERT" />
		User ID::<input type="number" name="userId" /> <br /> <br />
		First Name:<input type="text" name="firstName" /> <br /> <br />
		Last Name:<input type="text" name="lastName" /> <br /> <br />
		Email:<input type="text" name="email" /> <br /> <br />
		Mobile No:<input type="number" name="mobile" /> <br /> <br />
		Age:<input type="number" name="age" /> <br /> <br />
		
		    <input type="radio" name="lang" value="Tamil">Tamil 
			<input type="radio" name="lang" value="English">English 
			<input type="radio" name="lang" value="Hindi">Hindi 
			<input type="radio" name="lang" value="Telugu">Telugu 
			<input type="radio" name="lang" value="Malayalam">Malayalam 
			<input type="radio" name="lang" value="Marathi">Marathi
			<br /> <br />
			Country : <select name="country">
			<option>America</option>
			<option>London</option>
			<option>India</option>
			<option>Russia</option>
			<option>Argentina</option>
			<option>Spain</option>
			<option>Brazil</option>
			<option>France</option>
		</select><br /> <br> 
			
			
			
		<input type="submit" value="INSERT" /> 




	</form>
	
</body>
</html>