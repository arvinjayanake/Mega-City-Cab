<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<jsp:include page="user-nav-bar.jsp" />

<!-- Profile Section -->
<div class="profile-container">
  <h1>Profile</h1>
  <div class="profile-card">
    <form id="profile-form">
      <!-- Read-Only Fields -->
      <div class="form-group">
        <label for="name">Full Name</label>
        <input type="text" id="name" value="John Doe" readonly>
      </div>
      <div class="form-group">
        <label for="email">Email</label>
        <input type="email" id="email" value="johndoe@example.com" readonly>
      </div>
      <div class="form-group">
        <label for="nic">NIC Number</label>
        <input type="text" id="nic" value="123456789V" readonly>
      </div>

      <!-- Editable Fields -->
      <div class="form-group">
        <label for="address">Address</label>
        <input type="text" id="address" value="123 Main St, City A" required>
      </div>
      <div class="form-group">
        <label for="mobile">Mobile Number</label>
        <input type="tel" id="mobile" value="0771234567" pattern="[0-9]{10}" required>
      </div>
      <div class="form-group">
        <label for="password">New Password</label>
        <input type="password" id="password" placeholder="Enter new password (min 5 characters)" minlength="5">
      </div>
      <div class="form-group">
        <label for="confirm-password">Confirm Password</label>
        <input type="password" id="confirm-password" placeholder="Confirm new password" minlength="5">
      </div>

      <!-- Save Button -->
      <button type="submit">Save Changes</button>
    </form>
  </div>
</div>

<!-- Footer -->
<jsp:include page="user-footer.jsp" />
</body>
</html>
