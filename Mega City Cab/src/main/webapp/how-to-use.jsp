<%@ page import="com.arvin.megacitycab.model.base.User" %>
<%@ page import="com.arvin.megacitycab.model.enums.UserType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User loggedUser = null;
    if (session.getAttribute("user") instanceof User) {
        loggedUser = (User) session.getAttribute("user");
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>How to Use - Mega City Cab</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/how-to-use.css">
</head>
<body>
<!-- Navigation Bar -->
<jsp:include page="pre-login-nav-bar.jsp"/>

<!-- How to Use Section -->
<div class="how-to-use-container">
    <h1>How to Use Mega City Cab</h1>

    <!-- Step 1: Login -->
    <div class="step">
        <div class="step-number">1</div>
        <div class="step-content">
            <h2>Login</h2>
            <p>To access your account, follow these steps:</p>
            <ol>
                <li>Go to the <a href="login" target="_blank">Login Page</a>.</li>
                <li>Enter your registered <strong>Email</strong> and <strong>Password</strong>.</li>
                <li>Click <strong>Login</strong> to access your account.</li>
            </ol>
        </div>
    </div>

    <!-- Step 2: Register -->
    <div class="step">
        <div class="step-number">2</div>
        <div class="step-content">
            <h2>Register</h2>
            <p>If you're new to Mega City Cab, create an account:</p>
            <ol>
                <li>Go to the <a href="register" target="_blank">Register Page</a>.</li>
                <li>Enter your <strong>Full Name</strong>, <strong>NIC</strong>, <strong>Address</strong>, <strong>Email</strong>, <strong>Mobile</strong>, and <strong>Password</strong>.</li>
                <li>Submit the form to complete registration.</li>
                <li>An <strong>OTP</strong> will be sent to your email for verification.</li>
                <li>Check your email, enter the OTP, and verify your account.</li>
            </ol>
        </div>
    </div>

    <!-- Step 3: Book a Taxi -->
    <div class="step">
        <div class="step-number">3</div>
        <div class="step-content">
            <h2>Book a Taxi</h2>
            <p>To book a taxi, follow these steps:</p>
            <ol>
                <li>Go to the <a href="book-taxi.html">Book a Taxi Page</a>.</li>
                <li>Enter your <strong>Pickup Location</strong>, <strong>Pickup Time</strong>, and <strong>Drop-off Location</strong>.</li>
                <li>Select your preferred <strong>Vehicle Type</strong>.</li>
                <li>Enter the <strong>Total Distance</strong>.</li>
                <li>Choose your <strong>Payment Method</strong>:
                    <ul>
                        <li><strong>Cash</strong>: Pay the driver at the end of the trip.</li>
                        <li><strong>Online</strong>: Enter your card details to pay securely.</li>
                    </ul>
                </li>
                <li>Confirm your booking and wait for your driver to arrive.</li>
            </ol>
        </div>
    </div>

    <!-- Step 4: View Booking History -->
    <div class="step">
        <div class="step-number">4</div>
        <div class="step-content">
            <h2>View Booking History</h2>
            <p>To view your past and upcoming bookings:</p>
            <ol>
                <li>Go to the Booking History Page.</li>
                <li>View all your bookings in one place.</li>
                <li>Click on a booking to see detailed information, including:
                    <ul>
                        <li><strong>Booking Status</strong></li>
                        <li><strong>Customer Information</strong></li>
                        <li><strong>Driver Information</strong></li>
                        <li><strong>Vehicle Information</strong></li>
                        <li><strong>Payment Information</strong></li>
                    </ul>
                </li>
                <li>Click <strong>Print Receipt</strong> to download or print the booking details.</li>
            </ol>
        </div>
    </div>

    <!-- Step 5: Change Profile Information -->
    <div class="step">
        <div class="step-number">5</div>
        <div class="step-content">
            <h2>Change Profile Information</h2>
            <p>To update your profile details:</p>
            <ol>
                <li>Go to the Profile Page.</li>
                <li>View your current information.</li>
                <li>Edit your <strong>Address</strong>, <strong>Mobile Number</strong>, or <strong>Password</strong>.</li>
                <li>Click <strong>Save</strong> to update your profile.</li>
            </ol>
        </div>
    </div>

    <!-- Step 6: Contact and Support -->
    <div class="step">
        <div class="step-number">6</div>
        <div class="step-content">
            <h2>Contact and Support</h2>
            <p>For assistance, follow these steps:</p>
            <ol>
                <li>Go to the <a href="contact-us.html">Contact Us Page</a>.</li>
                <li>Find our <strong>Email</strong>, <strong>Mobile Number</strong>, and <strong>Address</strong>.</li>
                <li>Reach out to us for any queries or support.</li>
            </ol>
        </div>
    </div>

    <!-- Step 7: Logout -->
    <div class="step">
        <div class="step-number">7</div>
        <div class="step-content">
            <h2>Logout</h2>
            <p>To securely log out of your account:</p>
            <ol>
                <li>Click on the <strong>Logout</strong> option in the navigation menu.</li>
                <li>You will be logged out and redirected to the login page.</li>
            </ol>
        </div>
    </div>
</div>

<!-- Footer -->
<jsp:include page="user-footer.jsp"/>
</body>
</html>