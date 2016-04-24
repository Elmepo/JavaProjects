package eassignment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/users.jsp")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
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
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("Select * from users where id = " + request.getParameter("id"));
			while(rs.next()){
				out.println("<h1>" + rs.getString("username") + "</h1><br>");
				out.println("<p>" + rs.getString("about") + "</p>");
				if (userId.equals(request.getParameter("id"))) {
					out.println("<a href=\"edituserpage.jsp?id=" + userId + "\">Edit page</a>");
				}
			}
			out.println("</body></html>");
		} catch(Exception e) {
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
