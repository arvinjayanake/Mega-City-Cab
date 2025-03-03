<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String user_id = String.valueOf(session.getAttribute("user_id")); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>OTP Verification - Mega City Cab</title>
    <link rel="stylesheet" href="css/login-style.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
    <script>
        function validateOTP() {
            const otp = document.getElementById('otp').value;
            const errorDiv = document.getElementById('otpError');

            if (otp.length !== 4 || isNaN(otp)) {
                errorDiv.textContent = "OTP must be a 4-digit number!";
                errorDiv.style.display = 'block';
                return false;
            }

            errorDiv.style.display = 'none';
            return true;
        }
    </script>
</head>
<body>
<div class="login-container">
    <h2>OTP Verification</h2>

    <!-- OTP Entry Form -->
    <form action="form-register-otp" method="post" onsubmit="return validateOTP()">
        <input type="hidden" name="user_id" value="<%= user_id %>">
        <div class="input-group">
            <input type="text" id="otp" name="otp" placeholder="Enter 4-digit OTP" maxlength="4" required>
            <div id="otpError" class="error-message" style="display: none;"></div>
        </div>
        <button type="submit">Verify OTP</button>
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

    <!-- Resend OTP Option -->
    <div class="register-link">
        <p>Didn't receive the OTP? <a href="resend-otp">Resend OTP</a></p>
    </div>
</div>
</body>
</html>