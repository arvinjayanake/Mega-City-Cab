<%@ page import="com.arvin.megacitycab.model.Booking" %>
<%@ page import="com.arvin.megacitycab.model.Vehicle" %>
<%@ page import="com.arvin.megacitycab.model.base.User" %>
<%@ page import="com.arvin.megacitycab.model.enums.BookingStatus" %>
<%@ page import="com.arvin.megacitycab.model.enums.PaymentMethod" %>
<%@ page import="com.arvin.megacitycab.apiclient.VehicleAPIController" %>
<%@ page import="com.arvin.megacitycab.apiclient.UserAPIController" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Booking booking = (Booking) request.getAttribute("booking");
  Vehicle vehicle = VehicleAPIController.getVehicleById(booking.getVehicle_id());
  User customer = UserAPIController.getUserById(booking.getCustomer_id());
  User driver = UserAPIController.getUserById(booking.getDriver_id());
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Booking Details - Mega City Cab</title>
  <link rel="icon" type="image/x-icon" href="img/favicon.ico">
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="css/booking-history.css">
  <link rel="stylesheet" href="css/user-booking-details.css">
  <style>
    @media print {
      body * {
        visibility: hidden;
      }
      .booking-details-container, .booking-details-container * {
        visibility: visible;
      }
      .booking-details-container {
        position: absolute;
        left: 0;
        top: 0;
        width: 80%;
        box-shadow: none;
        border: none;
      }
      .print-button, .user-nav-bar, .user-footer {
        display: none;
      }
    }
  </style>
</head>
<body>
<!-- Navigation Bar -->
<jsp:include page="user-nav-bar.jsp"/>

<!-- Booking Details Section -->
<div class="booking-details-container">
  <h1>Booking Details</h1>
  <div class="booking-details-card">
    <div class="card-header">
      <h3>Booking #<%= booking.getId() %></h3>
      <span class="status <%= booking.getStatus() == BookingStatus.COMPLETED.getValue() ? "completed" :
                               booking.getStatus() == BookingStatus.CANCELLED.getValue() ? "cancelled" : "upcoming" %>">
                <%= BookingStatus.fromInt(booking.getStatus()).toString() %>
            </span>
    </div>
    <div class="card-body">
      <p><strong>Customer Name:</strong> <%= customer.getName() %></p>
      <p><strong>Customer Mobile:</strong> <%= customer.getMobile() %></p>
      <hr>
      <p><strong>Driver Name:</strong> <%= driver != null ? driver.getName() : "Not Assigned" %></p>
      <p><strong>Driver Mobile:</strong> <%= driver != null ? driver.getMobile() : "Not Assigned" %></p>
      <hr>
      <p><strong>Pickup Location:</strong> <%= booking.getPickup_location() %></p>
      <p><strong>Drop-off Location:</strong> <%= booking.getDropoff_location() %></p>
      <p><strong>Pickup Time:</strong> <%= booking.getPickup_datetime() %></p>
      <p><strong>Vehicle:</strong> <%= vehicle.fullNameWithYear() %></p>
      <p><strong>Distance (km):</strong> <%= booking.getTotal_distance() %></p>
      <p><strong>Tax (km):</strong> <%= booking.getTax() %></p>
      <p><strong>Price:</strong> LKR <%= booking.getTotal_price() %></p>
      <p><strong>Payment Method:</strong> <%= PaymentMethod.fromInt(booking.getPayment_method()).toString() %></p>
      <p><strong>Booking Status:</strong> <%= BookingStatus.fromInt(booking.getStatus()).toString() %></p>
    </div>
  </div>

  <!-- Print Button -->
  <button class="print-button" onclick="window.print()">Print Receipt</button>
</div>

<!-- Footer -->
<jsp:include page="user-footer.jsp"/>
</body>
</html>