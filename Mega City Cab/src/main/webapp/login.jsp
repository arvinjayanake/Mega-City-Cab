<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/login-style.css">
    <link rel="icon" type="image/x-icon" href="img/favicon.ico">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
    <title>Mega City Cab - Login</title>
</head>
<body>
<div class="login-container">
    <div class="login-form">
        <h2>Login to Mega City Cab</h2>
        <form action="form-login" method="post">
            <div class="input-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="input-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required>
            </div>

            <%
                String error = (String) request.getAttribute("error");
                if (error != null) {
            %>
            <div class="error-message">
                <%= error %>
            </div>
            <% } %>

            <button type="submit">Login</button>
            <div class="register-link">
                Don't have an account? <a href="register">Register here</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>