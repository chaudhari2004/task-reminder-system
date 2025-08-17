package remainderwebapp;



import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String umail= request.getParameter("umail");
		System.out.println("Email : "+umail);
		String upassword = request.getParameter("upassword");
		System.out.println("password : "+upassword);
		
		
		Connection con = DbConnection.Connect();
		try {
			
			
			PreparedStatement pstmt = con.prepareStatement("Select * from users where umail=? and upassword = ?");
			pstmt.setString(1, umail);
			pstmt.setString(2, upassword);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				int uid=rs.getInt(1);
				GetSet.setUid(uid);
				String email = rs.getString(4);
				String password = rs.getString(5);
				
			if(uid>0){
				response.sendRedirect("dashboard.html");
			}
				
				
			
				
			}
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			System.out.println("Not login..");
			e.printStackTrace();
		}
		
	}

}