<%--
  Created by IntelliJ IDEA.
  User: Arvin_J
  Date: 2/27/2025
  Time: 10:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mega City Cab Booking - Login</title>
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
<div class="login-container">
    <h2>Login to Mega City Cab</h2>

    <!-- Display error message if login fails -->
    <% if (request.getParameter("error") != null) { %>
    <div class="error-message">
        Invalid username or password. Please try again.
    </div>
    <% } %>

    <!-- Login Form -->
    <form action="form-login" method="post">
        <input type="email" name="email" placeholder="Email" required>
        <input type="password" name="password" placeholder="Password" required>
        <input type="submit" value="Login">
    </form>

    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
    <br>
    <div class="error-message">
        <%= error %>
    </div>
    <% } %>

    <!-- Link to Registration Page -->
    <div style="margin-top: 15px;">
        <p>Don't have an account? <a href="register">Register here</a></p>
    </div>
</div>
</body>
</html>
