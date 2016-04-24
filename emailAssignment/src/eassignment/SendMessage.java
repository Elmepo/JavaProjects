package eassignment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SendMessage
 */
@WebServlet("/sendmessage.jsp")
public class SendMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
			out.println("<form action=\"sendmessage.jsp\" method=\"post\">");
			out.println("<p>Send message to:<p><br>");
			out.println("<input type=\"text\" name=\"recipients\"><br>");
			out.println("<p>Subject</p><br>");
			out.println("<input type=\"text\" name=\"subject\"><br>");
			out.println("<p>Content</p><br>");
			out.println("<textarea name=\"content\" cols=\"100\" rows=\"10\"></textarea><br>");
			out.println("<input type=\"submit\"><br>");
			out.println("</form>");
			out.println("</body></html>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
			String tempUserId = null;
			Cookie[] userSession = request.getCookies();
			if(userSession != null){
				for (Cookie cookie : userSession){
					if (cookie.getName().equals("user")){
						userName = cookie.getValue();
					}
					if (cookie.getName().equals("userId")){
						tempUserId = cookie.getValue();
					}
				}
			}
			List<String> recipients = Arrays.asList(request.getParameter("recipients").split("\\s*,\\s*"));
			for (int i = 0; i < recipients.size(); i++){
				Statement stmt = conn.createStatement();
				ResultSet res = stmt.executeQuery("Select * from users where username = '" + recipients.get(i) + "'");
				String tempString = null;
				while(res.next()){
					tempString = res.getString("id");
				}
				int recId = Integer.parseInt(tempString);
				PreparedStatement prepInsert = conn.prepareStatement("insert into messages (sender, subject, content, recipient, time_sent, mread, trash, bcc) values (?,?,?,?, now(),?,?,?)");
				int userId = Integer.parseInt(tempUserId);
				prepInsert.setInt(1, userId);
				prepInsert.setString(2, request.getParameter("subject"));
				prepInsert.setString(3, request.getParameter("content"));
				prepInsert.setInt(4, recId);
				prepInsert.setInt(5, 0);
				prepInsert.setInt(6, 0);
				prepInsert.setInt(7, 0);
				prepInsert.executeUpdate();
			}
			response.sendRedirect("home.jsp");
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
