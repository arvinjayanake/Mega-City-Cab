<%@ page import="com.arvin.megacitycab.model.Booking" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Booking booking = (Booking) request.getAttribute("booking");
    String vehicle_name = (String) request.getAttribute("vehicle_name");
%><!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payment - Mega City Cab</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/payment-booking.css">
</head>
<body>
<!-- Navigation Bar -->
<jsp:include page="user-nav-bar.jsp"/>

<!-- Payment Booking Section -->
<div class="payment-booking-container">
    <h1>Payment</h1>

    <!-- Booking Summary -->
    <div class="booking-summary">
        <h2>Booking Summary</h2>
        <div class="summary-item">
            <span class="summary-label">Pickup Location:</span>
            <span class="summary-value"><%= booking.getPickup_location() %></span>
        </div>
        <div class="summary-item">
            <span class="summary-label">Pickup Time:</span>
            <span class="summary-value"><%= booking.getPickup_datetime() %></span>
        </div>
        <div class="summary-item">
            <span class="summary-label">Drop-off Location:</span>
            <span class="summary-value"><%= booking.getDropoff_location() %></span>
        </div>
        <div class="summary-item">
            <span class="summary-label">Vehicle:</span>
            <span class="summary-value"><%= vehicle_name %></span>
        </div>
        <div class="summary-item">
            <span class="summary-label">Distance:</span>
            <span class="summary-value"><%= booking.getTotal_distance() %> km</span>
        </div>
        <div class="summary-item total">
            <span class="summary-label">Total Amount:</span>
            <span class="summary-value">LKR <%= booking.getTotal_price() %></span>
        </div>
    </div>

    <!-- Card Information Form -->
    <div class="card-information">
        <h2>Card Information</h2>
        <form id="form-online-payment" action="form-online-payment" method="post">
            <input type="hidden" name="booking_id" value="<%= booking.getId() %>">
            <input type="hidden" name="amount" value="<%= booking.getTotal_price() %>">
            <div class="form-group">
                <label for="card_number">Card Number</label>
                <input type="text" id="card_number" name="card_number" placeholder="1234 5678 9012 3456" required>
            </div>
            <div class="form-group">
                <label for="card_holder">Card Holder Name</label>
                <input type="text" id="card_holder" name="card_holder" placeholder="John Doe" required>
            </div>
            <div class="form-group-row">
                <div class="form-group">
                    <label for="expiry_date">Expiry Date</label>
                    <input type="text" id="expiry_date" name="expiry_date" placeholder="MM/YY" required>
                </div>
                <div class="form-group">
                    <label for="cvv">CVV</label>
                    <input type="text" id="cvv" name="cvv" placeholder="123" required>
                </div>
            </div>
            <button type="submit">Pay Now</button>
        </form>
    </div>
</div>

<!-- Footer -->
<jsp:include page="user-footer.jsp"/>
</body>
</html>