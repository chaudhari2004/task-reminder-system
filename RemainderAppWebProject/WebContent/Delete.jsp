<%@ page import="java.sql.*" %>
<%@ page import="remainderwebapp.*" %>

<%
    try {
        int id = Integer.parseInt(request.getParameter("id"));

        Connection con = DbConnection.Connect();
        PreparedStatement pstmt = con.prepareStatement("DELETE FROM reminder WHERE rid = ?");
        pstmt.setInt(1, id);

        int i = pstmt.executeUpdate();

        pstmt.close();
        con.close();

        if (i > 0) {
            response.sendRedirect("viewAllReminders.jsp"); // redirect after delete
        } else {
            out.println("<p style='color:red;'>Failed to delete reminder.</p>");
        }
    } catch (Exception e) {
        e.printStackTrace();
        out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
    }
%>
