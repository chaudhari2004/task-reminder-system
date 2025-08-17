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
 * Servlet implementation class AddReminder
 */
public class AddRemainder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddRemainder() {
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
		int uid = GetSet.getUid();
		String rtitle=request.getParameter("rtitle");
		String rdesc=request.getParameter("rdesc");
		String date=request.getParameter("date");
		
		
		Connection con=DbConnection.Connect();
		
		try {
			PreparedStatement pstmt=con.prepareStatement("insert into reminder values(?,?,?,?,?)");
			int rid=0;
			pstmt.setInt(1,rid);
			pstmt.setString(2,rtitle);
			pstmt.setString(3,rdesc);
			pstmt.setString(4,date);
			pstmt.setInt(5, uid);
			int rs=pstmt.executeUpdate();
			
			if(rs>0){
				response.sendRedirect("dashboard.html");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}