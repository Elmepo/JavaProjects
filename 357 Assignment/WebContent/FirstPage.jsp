<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Hotel Registration Page</title>
	</head>
	<body>
		<form method=post action="BookingServlet">
			<center><h1>Book a Room</h1></center></br>
			<span>Name: </span><input type="text" name="name"></br>
			<span>Arrival Date: </span><input type="date" name="arrivalDate"></br>
			<span>Departure Date: </span><input type="date" name="departureDate"></br>
			<span>Number of Beds Required: </span><input type="number" name="bedsRequired"></br>
			<span>Contact Number: </span><input type="tel" name="telephone"></br>
			<p>And finally, will you be arriving outside our normal operating hours?</p>
			<input type="radio" name="arrOutside" value="yes"><span>Yes</span></br>
			<input type="radio" name="arrOutside" value="no"><span>No</span></br></br>
			<input type="submit" name="submit">
		</form>
	</body>
</html>