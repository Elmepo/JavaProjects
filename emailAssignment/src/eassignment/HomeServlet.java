package eassignment;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/home.jsp")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Set request attribute for messages here.
		String dbURL = "jdbc:mysql://localhost:3306/";
		String dbName = "test";
		String dbDriver = "com.mysql.jdbc.Driver";
		String dbUserName = "root";
		String dbPassword = "rootpass";
		try {
			Class.forName(dbDriver).newInstance();
			Connection conn = DriverManager.getConnection(dbURL+dbName, dbUserName, dbPassword);
			//Q
			String userName = null;
			String userId = null;
			Cookie[] userSession = request.getCookies();
			if(userSession != null){
				for (Cookie cookie : userSession){
					if (cookie.getName().equals("user")){
						userName = cookie.getValue();
					}
					if (cookie.getName().equals("userId")){
						userId = cookie.getValue();
					}
				}
			}
			if(userName == null){
				response.sendRedirect("login.jsp");
			}
			Statement st = conn.createStatement();
			ResultSet res = st.executeQuery("Select id, username from users");
			StringBuilder sb = new StringBuilder();
			while(res.next()){
				String id = res.getString("id");
				String username = res.getString("username");
				sb.append("<a href=\"users.jsp?id=" + id + "\">" + username + "</a>  ");
			}
			String users = sb.toString();
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">" +
					"<html>" +
					"<head>" +
					"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">" +
					"<title>Email Assignment Home Page</title>" +
					"<style>" +
					".otherUsers{" +
					"background: #dcdcdc;" +
					"width: 100%;" +
					"}" +
					".message{" +
					"background: #cdcdcd;" +
					"width: 100%;" +
					"padding-bottom: 5px;" + 
					"}" +
					"</style>" +
					"</head>" +
					"<body>");
			out.println("<div class=\"otherUsers\">" + users + "</div>");
			res = st.executeQuery("Select * from messages where recipient = " + userId);
			out.println("<a href=\"sendmessage.jsp\">Send a new message</a><br>");
			out.println("<a href=\"users.jsp?id=" + userId + "\">View Userpage</a><br>");
			out.println("<p>received messages</p>");
			while(res.next()){
				out.println("<div class=\"message\">");
				out.println("<table style=\"width: 100%\"><tr>");
				out.println("<td style=\"width: 25%;\">Sent by: " + res.getString("sender") + "<br>on " + res.getString("time_sent") + "</td>");
				out.println("<td style=\"width: 60%;\">" + res.getString("subject") + "<br>" + res.getString("content") + "</td>");
				//btn reply, btn trash
				out.println("<td style=\"width: 15%;\"><a href=\"reply.jsp?id=" + res.getString("id") + "\">Reply to Message</a><br>");
				out.println("<a href=\"trash.jsp?id=" + res.getString("id") + "\">Move to Trash</a></td>");
				out.println("</tr></table>");
				out.println("</div>");
			}
			out.println("<p>Sent Messages</p>");
			res = st.executeQuery("Select * from messages where sender = " + userId);
			while(res.next()){
				out.println("<div class=\"message\">");
				out.println("<table style=\"width: 100%\"><tr>");
				out.println("<td style=\"width: 25%;\">Sent by: " + res.getString("sender") + "<br>on " + res.getString("time_sent") + "</td>");
				out.println("<td style=\"width: 60%;\">" + res.getString("subject") + "<br>" + res.getString("content") + "</td>");
				out.println("<td style=\"width: 15%;\"><a href=\"reply.jsp?id=" + res.getString("id") + "\">Reply to Message</a></td>");
				out.println("</tr></table>");
				out.println("</div>");
			}
			out.println("</body>" +
						"</html>");
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
