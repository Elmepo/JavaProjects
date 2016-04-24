package eassignment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditUserPageServlet
 */
@WebServlet("/edituserpage.jsp")
public class EditUserPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUserPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Registration Page</title></head><body>");
		out.println("<form action=\"edituserpage.jsp?id=" + request.getParameter("id") + "\" method=\"post\">");
		out.println("<p>About yourself</p><br>");
		out.println("<textarea name=\"about\" cols=\"100\" rows=\"10\"></textarea>");
		out.println("<input type=\"submit\" value=\"Update\">");
		out.println("</form>");
		out.println("</body></html>");
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
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("Select * from users");
			int x = Integer.parseInt(request.getParameter("id"));
			PreparedStatement prepInsert = conn.prepareStatement("Update users set about = ? where id = ?");
			prepInsert.setString(1, request.getParameter("about"));
			prepInsert.setInt(2, x);
			prepInsert.executeUpdate();
			response.sendRedirect("home.jsp");
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
