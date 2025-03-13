<%@ page import="com.arvin.megacitycab.model.Booking" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Booking booking = (Booking) request.getAttribute("booking");
  String vehicle_name = (String) request.getAttribute("vehicle_name");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Booking Completed - Mega City Cab</title>
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="css/booking-completed.css">
</head>
<body>

<!-- Navigation Bar -->
<jsp:include page="user-nav-bar.jsp"/>

<!-- Booking Completed Section -->
<div class="booking-completed-container">
  <h1>Booking Completed</h1>
  <div class="booking-details">
    <div class="detail-item">
      <span class="detail-label">Pickup Location:</span>
      <span class="detail-value"><%= booking.getPickup_location() %></span>
    </div>
    <div class="detail-item">
      <span class="detail-label">Pickup Time:</span>
      <span class="detail-value"><%= booking.getPickup_datetime() %></span>
    </div>
    <div class="detail-item">
      <span class="detail-label">Drop-off Location:</span>
      <span class="detail-value"><%= booking.getDropoff_location() %></span>
    </div>
    <div class="detail-item">
      <span class="detail-label">Vehicle:</span>
      <span class="detail-value"><%= vehicle_name %></span>
    </div>
    <div class="detail-item">
      <span class="detail-label">Distance:</span>
      <span class="detail-value"><%= booking.getTotal_distance() %> km</span>
    </div>
    <div class="detail-item">
      <span class="detail-label">Total Price:</span>
      <span class="detail-value">LKR <%= booking.getTotal_price() %></span>
    </div>
  </div>
  <button onclick="window.location.href='booking-history'">Goto My Bookings</button>
</div>

<!-- Footer -->
<jsp:include page="user-footer.jsp"/>
</body>
</html>