<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="css/login-style.css">
    <link rel="icon" type="image/x-icon" href="img/favicon.ico">
    <title>Mega City Cab - Login</title>
</head>
<body>

<div class="login-container">
    <div class="img1">
        <img src="img/login-banner.jpg" alt="" width="300px" class="imgs">
    </div>
    <div class="lgl1">
        <form class="login-form" action="form-login" method="post">
            <h2>Login to Mega City Cab</h2>
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
            <br>
            <div class="error-message">
                <%= error %>
            </div>
            <% } %>

            <button type="submit">Login</button>
            <div style="color: gray; margin-top: 20px;">
                Don't have an account?<br>
                <a href="register">Register here</a>
            </div>
        </form>

    </div>
</div>

</body>
</html>
