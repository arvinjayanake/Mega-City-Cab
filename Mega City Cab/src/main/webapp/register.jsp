<%--
  Created by IntelliJ IDEA.
  User: arvin
  Date: 2/28/2025
  Time: 11:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mega City Cab Booking - Register</title>
    <link rel="stylesheet" href="css/login.css"> <!-- Reuse same CSS -->
</head>
<body>
<div class="login-container">
    <h2>Create New Account</h2>

    <!-- Error Messages -->
    <% if (request.getParameter("error") != null) { %>
    <div class="error-message">
        <%= request.getParameter("error") %>
    </div>
    <% } %>

    <form action="form-register" method="post">
        <!-- Personal Details -->
        <input type="text" name="name" placeholder="Full Name" required>

        <input type="text" name="nic"
               placeholder="NIC Number"
               pattern="(\d{12}|\d{9}V)"
               title="Enter valid NIC (12 digits or 9 digits with V)"
               required>

        <input type="text" name="address" placeholder="Address" required>
        <input type="email" name="email" placeholder="Email" required>
        <input type="tel" name="mobile"
               placeholder="Mobile Number"
               pattern="[0-9]{10}"
               title="10 digit mobile number"
               required>

        <input type="password" name="password"
               placeholder="Password (min 5 characters)"
               minlength="5"
               required>

        <input type="password" name="confirm-password"
               placeholder="Retype Password"
               minlength="5"
               required>

        <input type="submit" value="Register">
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

    <!-- Link to Login -->
    <div style="margin-top: 15px;">
        <p>Already have an account? <a href="login">Login here</a></p>
    </div>
</div>
</body>
</html>
