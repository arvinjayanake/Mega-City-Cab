<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mega City Cab - Register</title>
    <link rel="stylesheet" href="css/login-style.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Poppins', sans-serif;
            background-color: #f8f9fa;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
    </style>
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
        <div class="input-group">
            <input type="text" name="name" placeholder="Full Name" maxlength="100" required>
        </div>
        <div class="input-group">
            <input type="text" name="nic" placeholder="NIC Number" maxlength="12" pattern="(\d{12}|\d{9}V)" title="Enter valid NIC (12 digits or 9 digits with V)" required>
        </div>
        <div class="input-group">
            <input type="text" name="address" placeholder="Address" maxlength="100" required>
        </div>
        <div class="input-group">
            <input type="email" name="email" placeholder="Email" maxlength="100" required>
        </div>
        <div class="input-group">
            <input type="tel" name="mobile" placeholder="Mobile Number" maxlength="10" pattern="[0-9]{10}" title="10 digit mobile number" required>
        </div>
        <div class="input-group">
            <input type="password" name="password" placeholder="Password (min 5 characters)" minlength="5" required>
        </div>
        <div class="input-group">
            <input type="password" name="confirm-password" placeholder="Retype Password" minlength="5" required>
        </div>

        <button type="submit">Register</button>
    </form>

    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
    <div class="error-message">
        <%= error %>
    </div>
    <% } %>

    <!-- Link to Login -->
    <div class="register-link">
        <p>Already have an account? <a href="login">Login here</a></p>
    </div>
</div>
</body>
</html>