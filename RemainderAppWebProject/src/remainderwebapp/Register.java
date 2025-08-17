package remainderwebapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//
//@WebServlet("/RegisterServlet")
public class Register extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int uid = 0; 
        String uname = request.getParameter("uname");
        String ucontact = request.getParameter("ucontact");
        String umail = request.getParameter("umail");
        String upassword = request.getParameter("upassword");
        
        

        Connection con = DbConnection.Connect();
        

        try {
            

        	PreparedStatement pstmt = con.prepareStatement("INSERT INTO users  VALUES (?, ?, ?, ?, ?)");
           
            pstmt.setInt(1, uid); 
            pstmt.setString(2, uname);
            pstmt.setString(3, ucontact);
            pstmt.setString(4, umail);
            pstmt.setString(5, upassword);

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                response.sendRedirect("Login.html");
              
            } else {
                response.getWriter().println("<h3>Registration Failed.</h3>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " );
        } 
           
        
    }
}
