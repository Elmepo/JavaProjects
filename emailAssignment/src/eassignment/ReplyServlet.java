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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReplyServlet
 */
@WebServlet("/reply.jsp")
public class ReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyServlet() {
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
		PrintWriter out = response.getWriter();
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
			ResultSet rs = st.executeQuery("select messages.subject, users.username, messages.sender from messages join users on messages.sender = users.id where messages.id = " + request.getParameter("id"));
			String subject = null;
			String sender = null;
			String sId = null;
			while (rs.next()){
				subject = rs.getString("subject");
				sender = rs.getString("username");
				sId = rs.getString("sender");
			}
			out.println("<html>");
			out.println("<head><title>Reply to message</title></head>");
			out.println("<body>");
			out.println("<form action=\"reply.jsp?id=" + request.getParameter("id") + "&s=" + sId + "\" method=\"post\">");
			out.println("<p>You are replying to: " + subject + "</p><br><br>");
			out.println("<p>Subject:</p>");
			out.println("<input type=\"text\" name=\"subject\"><br>");
			out.println("<p>Content</p>");
			out.println("<textarea name=\"content\" cols=\"100\" rows=\"10\"></textarea>");
			out.println("<p>This reply will be sent to " + sender + "<br>");
			out.println("<input type=\"submit\" value=\"Submit\">");
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");
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
		String dbURL = "jdbc:mysql://localhost:3306/";
		String dbName = "test";
		String dbDriver = "com.mysql.jdbc.Driver";
		String dbUserName = "root";
		String dbPassword = "rootpass";
		PrintWriter out = response.getWriter();
		try {
			Class.forName(dbDriver).newInstance();
			Connection conn = DriverManager.getConnection(dbURL+dbName, dbUserName, dbPassword);
			//Q
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from messages where id = " + request.getParameter("id"));
			String sender = null;
			while (rs.next()) {
				sender = rs.getString("sender");
			}
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
			PreparedStatement prepInsert = conn.prepareStatement("insert into messages (sender, subject, content, recipient, time_sent, mread, trash, bcc) values (?,?,?,?, now(),?,?,?)");
			int userId = Integer.parseInt(tempUserId);
			int recId = Integer.parseInt(request.getParameter("s"));
			prepInsert.setInt(1, userId);
			prepInsert.setString(2, request.getParameter("subject"));
			prepInsert.setString(3, request.getParameter("content"));
			prepInsert.setInt(4, recId);
			prepInsert.setInt(5, 0);
			prepInsert.setInt(6, 0);
			prepInsert.setInt(7, 0);
			prepInsert.executeUpdate();
			response.sendRedirect("home.jsp");
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
