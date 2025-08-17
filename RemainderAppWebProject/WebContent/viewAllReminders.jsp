<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<%@ page import="remainderwebapp.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Your Reminders</title>
    <style>
        body {
            font-family: Arial;
            background: #f9f9f9;
        }
        h2 {
            color: #333;
            text-align: center;
        }
        table {
            width: 90%;
            margin: auto;
            border-collapse: collapse;
            margin-bottom: 30px;
        }
        th, td {
            padding: 10px;
            border: 1px solid #aaa;
            text-align: center;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        .no-data {
            text-align: center;
            color: red;
        }
    </style>
</head>
<body>

<%
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    int uid = GetSet.getUid();
%>

<h2>All Reminders</h2>
<%
    try {
        con = DbConnection.Connect();
        pstmt = con.prepareStatement("SELECT * FROM reminder WHERE uid = ?");
        pstmt.setInt(1, uid);
        rs = pstmt.executeQuery();
%>
<table>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Description</th>
        <th>Date</th>
        <th>Action</th>
    </tr>
<%
    boolean hasReminders = false;
    while (rs.next()) {
        hasReminders = true;
%>
    <tr>
        <td><%= rs.getInt("rid") %></td>
        <td><%= rs.getString("rtitle") %></td>
        <td><%= rs.getString("rdesc") %></td>
        <td><%= rs.getString("date") %></td>
        <td><a href="Delete.jsp?id=<%= rs.getInt("rid") %>">Delete</a></td>
    </tr>
<%
    }
    if (!hasReminders) {
%>
    <tr><td colspan="5" class="no-data">No reminders found.</td></tr>
<%
    }
    rs.close();
    pstmt.close();
%>
</table>
<%
    } catch (Exception e) {
        out.println("<p style='color:red;'>Error loading reminders: " + e.getMessage() + "</p>");
    }
%>

<h2>Today's Reminders</h2>
<%
    try {
        // Match date format with the format stored in DB
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String today = sdf.format(new Date());

        pstmt = con.prepareStatement("SELECT * FROM reminder WHERE uid = ? AND date = ?");
        pstmt.setInt(1, uid);
        pstmt.setString(2, today);
        rs = pstmt.executeQuery();
%>
<table>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Description</th>
        <th>Date</th>
        <th>Action</th>
    </tr>
<%
    boolean hasToday = false;
    while (rs.next()) {
        hasToday = true;
%>
    <tr>
        <td><%= rs.getInt("rid") %></td>
        <td><%= rs.getString("rtitle") %></td>
        <td><%= rs.getString("rdesc") %></td>
        <td><%= rs.getString("date") %></td>
        <td><a href="Delete.jsp?id=<%= rs.getInt("rid") %>">Delete</a></td>
    </tr>
<%
    }
    if (!hasToday) {
%>
    <tr><td colspan="5" class="no-data">No reminders for today.</td></tr>
<%
    }
        rs.close();
        pstmt.close();
        con.close();
    } catch (Exception e) {
        out.println("<p style='color:red;'>Error loading today’s reminders: " + e.getMessage() + "</p>");
    }
%>
</table>

</body>
</html>
