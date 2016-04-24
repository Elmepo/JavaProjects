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
 * Servlet implementation class TrashServlet
 */
@WebServlet("/trash.jsp")
public class TrashServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrashServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void getTrash(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
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
			if (request.getParameter("id") == null) {
				
			} else {
				PreparedStatement prepUpdate = conn.prepareStatement("update messages set trash = 1 where id = ?");
				int temp = Integer.parseInt(request.getParameter("id"));
				prepUpdate.setInt(1, temp);
				prepUpdate.executeUpdate();
			}
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("Select * from messages where recipient = " + userId + " and trash = 1");
			out.println("<html><head><title>Trash</title>" + 
					"<style>.message{" +
					"background: #cdcdcd;" +
					"width: 100%;" +
					"padding-bottom: 5px;" + 
					"}</style></head><body>");
			String mId = null;
			while(rs.next()){
				mId = rs.getString("id");
				out.println("<div class=\"message\">");
				out.println("<table style=\"width: 100%\"><tr>");
				out.println("<td style=\"width: 25%;\">Sent by: " + rs.getString("sender") + "<br>on " + rs.getString("time_sent") + "</td>");
				out.println("<td style=\"width: 60%;\">" + rs.getString("subject") + "<br>" + rs.getString("content") + "</td>");
				out.println("<td style=\"width: 15%;\"><a href=\"removetrash.jsp?id=" + mId + "\">Remove from trash</a></td>");
				out.println("</tr></table>");
				out.println("</div>");
			}
			out.println("</body></html>");
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
		getTrash(request, response);
	}

}
