<%@ page import="com.arvin.megacitycab.model.Booking" %>
<%@ page import="java.util.List" %>
<%@ page import="com.arvin.megacitycab.apiclient.VehicleAPIController" %>
<%@ page import="com.arvin.megacitycab.model.Vehicle" %>
<%@ page import="com.arvin.megacitycab.model.enums.BookingStatus" %>
<%@ page import="com.arvin.megacitycab.model.enums.PaymentMethod" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Booking History - Mega City Cab</title>
    <link rel="icon" type="image/x-icon" href="img/favicon.ico">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/booking-history.css">
</head>
<body>
<!-- Navigation Bar -->
<jsp:include page="user-nav-bar.jsp"/>

<!-- Booking History Section -->
<div class="booking-history-container">
    <h1>Booking History</h1>
    <div class="booking-cards">
        <%
            List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");
            if (bookings != null) {
                for (Booking booking : bookings) {
                    Vehicle vehicle = VehicleAPIController.getVehicleById(booking.getVehicle_id());
        %>

        <div class="booking-card">
            <div class="card-header">
                <h3>Booking #<%= booking.getId() %></h3>
                <% if (vehicle.getStatus() == BookingStatus.PENDING.getValue()) { %>
                <span class="status upcoming">Pending</span>
                <% } else if (vehicle.getStatus() == BookingStatus.STARTED.getValue()) { %>
                <span class="status completed">Started</span>
                <% } else if (vehicle.getStatus() == BookingStatus.COMPLETED.getValue()) { %>
                <span class="status completed">Completed</span>
                <% } else if (vehicle.getStatus() == BookingStatus.CANCELLED.getValue()) { %>
                <span class="status cancelled">Cancelled</span>
                <% } %>
            </div>
            <div class="card-body">
                <p><strong>Pickup:</strong> <%= booking.getPickup_location() %></p>
                <p><strong>Drop-off:</strong> <%= booking.getDropoff_location() %></p>
                <p><strong>Pickup time:</strong> <%= booking.getPickup_datetime() %></p>
                <p><strong>Vehicle:</strong> <%= vehicle.fullNameWithYear() %></p>
                <p><strong>Distance (km):</strong> <%= booking.getTotal_distance() %></p>
                <p><strong>Price:</strong> LKR <%= booking.getTotal_price() %></p>
                <p><strong>Payment Method:</strong> <%= PaymentMethod.fromInt(booking.getPayment_method()).toString() %></p>
            </div>
        </div>

        <%
                }
            }
        %>
    </div>
</div>

<!-- Footer -->
<jsp:include page="user-footer.jsp"/>
</body>
</html>