package remainderwebapp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public dashboard() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operation");

        if (operation == null) {
            response.getWriter().println("No operation selected.");
            return;
        }

        switch (operation) {
            case "Add Reminder":
                response.sendRedirect("addReminder.html");
                break;
            case "View Reminder":
                response.sendRedirect("viewAllReminders.jsp");
                break;
            case "Delete Reminder":
                response.sendRedirect("deleteReminder.html");
                break;
            case "Todays Reminder":
                response.sendRedirect("todaysReminders.jsp"); 
                break;
            case "Exit":
                response.getWriter().println("Exiting...");
                break;
            default:
                response.getWriter().println("Invalid operation selected.");
        }
	}
}
