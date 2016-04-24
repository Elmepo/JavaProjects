package eassignment;


import java.io.IOException;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Add ability to "Remember me"
		//Connect to localhost MySql database.
		String dbURL = "jdbc:mysql://localhost:3306/";
		String dbName = "test";
		String dbDriver = "com.mysql.jdbc.Driver";
		String dbUserName = "root";
		String dbPassword = "rootpass";
		try {
			Class.forName(dbDriver).newInstance();
			Connection conn = DriverManager.getConnection(dbURL+dbName, dbUserName, dbPassword);
			//Do Queries and Logic here.
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM users");
			while (rs.next()) {
				String user = rs.getString("username");
				String password = rs.getString("password");
				//String error = request.getParameter("uname") + " " + user;
				if (user.equals(request.getParameter("uname"))) {
					//Username is correct
					if (password.equals(request.getParameter("pass"))){
						//Password is correct
						Cookie userSession = new Cookie("user", user);
						Cookie userID = new Cookie("userId", rs.getString("id"));
						userID.setMaxAge(60*60);
						userSession.setMaxAge(60*60);
						response.addCookie(userID);
						response.addCookie(userSession);
						//Set attributes
						response.sendRedirect("home.jsp");
						//request.setAttribute("list", users);
						//request.getRequestDispatcher("home.jsp").forward(request, response);
					} else {
						//But the password is wrong
						String error = "There was an issue with your login details: <br>Your password was incorrect. Please try again.";
						Cookie cErrors = new Cookie("errors", error);
						cErrors.setMaxAge(15);
						response.addCookie(cErrors);
						//response.sendRedirect("login.jsp");
					}
				} else {
					String error = "There was an issue with your login details: <br>Your username was incorrect. Please try again.";
					Cookie cErrors = new Cookie("errors", error);
					cErrors.setMaxAge(15);
					response.addCookie(cErrors);
					//response.sendRedirect("login.jsp");
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
