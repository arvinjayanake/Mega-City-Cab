<%@ page import="com.arvin.megacitycab.model.base.User" %>
<%@ page import="com.arvin.megacitycab.model.enums.UserType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% User user = (User) session.getAttribute("user"); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/x-icon" href="img/favicon.ico">
    <title>Profile - Mega City Cab</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/profile.css">
</head>
<body>
<!-- Navigation Bar -->
<jsp:include page="user-nav-bar.jsp"/>

<!-- Profile Section -->
<div class="profile-container">
    <h1>Profile</h1>
    <div class="profile-card">
        <form id="profile-form" action="form-update-profile" method="post">
            <input type="hidden" name="id" value="<%= user.getId() %>">

            <!-- Read-Only Fields -->
            <div class="form-group">
                <label for="user_type">User Type</label>
                <input type="text" id="user_type" value="<%= UserType.fromInt(user.getType()).toString() %>" disabled>
            </div>

            <div class="form-group">
                <label for="name">Full Name</label>
                <input type="text" id="name" value="<%= user.getName() %>" disabled>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" value="<%= user.getEmail() %>" disabled>
            </div>
            <div class="form-group">
                <label for="nic">NIC Number</label>
                <input type="text" id="nic" value="<%= user.getNic() %>" disabled>
            </div>

            <!-- Editable Fields -->
            <div class="form-group">
                <label for="address">Address</label>
                <input type="text" id="address" name="address" value="<%= user.getAddress() %>" required>
            </div>
            <div class="form-group">
                <label for="mobile">Mobile Number</label>
                <input type="tel" id="mobile" name="mobile" value="<%= user.getMobile() %>" max pattern="[0-9]{10}"
                       required>
            </div>
            <div class="form-group">
                <label for="password">New Password</label>
                <input type="password" id="password" name="password" placeholder="Enter new password (min 5 characters)"
                       minlength="5">
            </div>
            <div class="form-group">
                <label for="confirm_password">Confirm Password</label>
                <input type="password" id="confirm_password" name="confirm_password" placeholder="Confirm new password"
                       minlength="5">
            </div>

            <%
                String error = (String) request.getAttribute("error");
                if (error != null) {
            %>
            <div class="error-message">
                <%= error %>
            </div>
            <% } %>

            <!-- Save Button -->
            <button type="submit">Save Changes</button>
        </form>
    </div>
</div>

<!-- Footer -->
<jsp:include page="user-footer.jsp"/>
</body>
</html>
