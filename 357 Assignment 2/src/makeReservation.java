import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class makeReservation
{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//Getting params
		String name = request.getParameter("name");
		String arrival = request.getParameter("arrivalDate");
		String departure = request.getParameter("departureDate");
		String numBeds = request.getParameter("bedsRequired");
		String[] amenities = request.getParameterValues("amenities");
		String outsideArrival = request.getParameter("arrOutside");
		if (name.length() < 1)
		{
			//Print Error Message
		}
	}

}