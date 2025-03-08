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
    <title>Mega City Cab - Home</title>
    <link rel="icon" type="image/x-icon" href="img/favicon.ico">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/home.css">
</head>
<body>
<!-- Navigation Bar -->
<nav>
    <div class="logo">Mega City Cab</div>
    <ul class="nav-links">
        <% if (loggedUser == null) {%>
        <li><a href="login" class="login-button">Login</a></li>
        <% } else { %>
            <% if (loggedUser.getType() == UserType.ADMIN.getValue()) {%>
                <li><a href="admin-view-customers" class="login-button">Admin Dashboard</a></li>
            <% } else  if (loggedUser.getType() == UserType.CUSTOMER.getValue()) {%>
                <li><a href="book-a-taxi" class="login-button">Book a Taxi</a></li>
            <% } %>
        <% } %>
    </ul>
</nav>

<!-- Hero Section -->
<section class="hero">
    <div class="hero-content">
        <h1>Your Reliable Ride, Anytime, Anywhere</h1>
        <p>Book a taxi with Mega City Cab and enjoy a comfortable, safe, and affordable ride.</p>
        <a href="login" class="cta-button">Get Started</a>
    </div>
</section>

<!-- Features Section -->
<section id="features" class="features">
    <h2>Why Choose Mega City Cab?</h2>
    <div class="feature-cards">
        <div class="feature-card">
            <img src="img/icon-safe.png" alt="Safe Ride">
            <h3>Safe & Reliable</h3>
            <p>Our drivers are trained professionals, and our vehicles are regularly inspected for safety.</p>
        </div>
        <div class="feature-card">
            <img src="img/icon-affordable.png" alt="Affordable">
            <h3>Affordable Pricing</h3>
            <p>Enjoy competitive pricing with no hidden charges. Pay only for what you use.</p>
        </div>
        <div class="feature-card">
            <img src="img/icon-convenient.png" alt="Convenient">
            <h3>Convenient Booking</h3>
            <p>Book your ride in just a few clicks. Available 24/7 for your convenience.</p>
        </div>
    </div>
</section>

<!-- About Section -->
<section id="about" class="about">
    <h2>About Mega City Cab</h2>
    <p>Mega City Cab is your trusted partner for all your transportation needs. Whether you're commuting to work,
        heading to the airport, or exploring the city, we provide a seamless and enjoyable ride experience. Our mission
        is to make your journey safe, comfortable, and hassle-free.</p>
</section>

<!-- Footer -->
<jsp:include page="user-footer.jsp"/>
</body>
</html>
