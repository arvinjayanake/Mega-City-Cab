<%@ page import="com.arvin.megacitycab.model.Booking" %>
<%@ page import="com.arvin.megacitycab.model.base.User" %>
<%@ page import="com.arvin.megacitycab.model.Vehicle" %>
<%@ page import="com.arvin.megacitycab.model.Payment" %>
<%@ page import="com.arvin.megacitycab.model.enums.BookingStatus" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Booking booking = (Booking) request.getAttribute("booking");
    User user = (User) request.getAttribute("customer");
    Vehicle vehicle = (Vehicle) request.getAttribute("vehicle");
    Payment payment = null;

    if (request.getAttribute("payment") instanceof Payment) {
        payment = (Payment) request.getAttribute("payment");
    }
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Booking Details - Mega City Cab</title>
    <link rel="icon" type="image/x-icon" href="img/favicon.ico">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;700&family=Open+Sans:wght@400;600&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
    <link rel="stylesheet" href="css/admin-style.css">
    <link rel="stylesheet" href="css/booking-details.css">
</head>

<body>
<div class="container">

    <jsp:include page="admin-side-bar.jsp"/>

    <div class="content">
        <div class="booking-details-container">
            <h1>Booking Details</h1>

            <!-- Customer Information -->
            <div class="details-section">
                <h2>Customer Information</h2>
                <div class="details-item">
                    <span class="details-label">Name:</span>
                    <span class="details-value"><%= user.getName() %></span>
                </div>
                <div class="details-item">
                    <span class="details-label">Email:</span>
                    <span class="details-value"><%= user.getEmail() %></span>
                </div>
                <div class="details-item">
                    <span class="details-label">Mobile:</span>
                    <span class="details-value"><%= user.getMobile() %></span>
                </div>
            </div>

            <!-- Vehicle Information -->
            <div class="details-section">
                <h2>Vehicle Information</h2>
                <div class="details-item">
                    <span class="details-label">Vehicle:</span>
                    <span class="details-value"><%= vehicle.fullNameWithYear() %></span>
                </div>
                <div class="details-item">
                    <span class="details-label">License Plate:</span>
                    <span class="details-value"><%= vehicle.getRegistration_number() %></span>
                </div>
                <div class="details-item">
                    <span class="details-label">Passenger Capacity:</span>
                    <span class="details-value"><%= vehicle.getPassenger_capacity() %></span>
                </div>
                <div class="details-item">
                    <span class="details-label">Luggage Capacity:</span>
                    <span class="details-value"><%= vehicle.getLuggage_capacity() %></span>
                </div>
                <div class="details-item">
                    <span class="details-label">Price Per KM:</span>
                    <span class="details-value">LKR <%= vehicle.getPrice_per_km() %></span>
                </div>
            </div>

            <!-- Booking Details -->
            <div class="details-section">
                <h2>Booking Details</h2>
                <div class="details-item">
                    <span class="details-label">Pickup Location:</span>
                    <span class="details-value"><%= booking.getPickup_location() %></span>
                </div>
                <div class="details-item">
                    <span class="details-label">Pickup Time:</span>
                    <span class="details-value"><%= booking.getPickup_datetime() %></span>
                </div>
                <div class="details-item">
                    <span class="details-label">Drop-off Location:</span>
                    <span class="details-value"><%= booking.getDropoff_location() %></span>
                </div>
                <div class="details-item">
                    <span class="details-label">Distance:</span>
                    <span class="details-value"><%= booking.getTotal_distance() %> km</span>
                </div>
                <div class="details-item">
                    <span class="details-label">Total Price:</span>
                    <span class="details-value">LKR <%= booking.getTotal_price() %></span>
                </div>
                <div class="details-item">
                    <span class="details-label">Status:</span>
                    <span class="details-value"><%= BookingStatus. %> km</span>
                </div>
            </div>

            <!-- Payment Information -->
            <div class="details-section">
                <h2>Payment Information</h2>
                <div class="details-item">
                    <span class="details-label">Total Amount:</span>
                    <span class="details-value">LKR 3,750.00</span>
                </div>
                <div class="details-item">
                    <span class="details-label">Payment Method:</span>
                    <span class="details-value">Credit Card</span>
                </div>
                <div class="details-item">
                    <span class="details-label">Payment Status:</span>
                    <span class="details-value">Paid</span>
                </div>
            </div>
        </div>
    </div>
</div>

</body>

</html>
