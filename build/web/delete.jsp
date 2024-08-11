<%@page import="Utils.LoggerUtil"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.logging.Logger" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Delete User</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f8ff;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            color: #333;
        }
        h1 {
            color: #4682b4;
            margin-bottom: 30px;
        }
        form {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            display: flex;
            flex-direction: column;
            width: 300px;
        }
        label {
            margin: 10px 0 5px;
        }
        input[type="text"] {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-bottom: 20px;
            width: 100%;
            box-sizing: border-box;
        }
        input[type="submit"] {
            padding: 10px;
            border: none;
            border-radius: 5px;
            background-color: #4682b4;
            color: white;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        input[type="submit"]:hover {
            background-color: #5a9bd4;
        }
    </style>
</head>
<body>
    <%
        // Create a logger instance
        Logger logger = LoggerUtil.getLogger();

        // Log the access to the delete page
        String userId = request.getParameter("id");
        logger.info("Accessed delete page for user ID: " + userId);
    %>

    <h1>Delete User Information</h1>
    <form action="delete" method="post">
        <label for="id">ID:</label>
        <input type="text" id="id" name="id" value="<%= userId %>" readonly>
        <input type="submit" value="Delete">
    </form>

    <%
        // Log when the form is displayed
        logger.info("Delete form displayed for user ID: " + userId);
    %>
</body>
</html>
