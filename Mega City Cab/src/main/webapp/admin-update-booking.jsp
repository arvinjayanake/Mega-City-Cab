<%@ page import="com.arvin.megacitycab.model.Booking" %>
<%@ page import="com.arvin.megacitycab.model.Driver" %>
<%@ page import="java.util.List" %>
<%@ page import="com.arvin.megacitycab.model.enums.BookingStatus" %>
<%@ page import="com.arvin.megacitycab.model.base.User" %>
<%@ page import="com.arvin.megacitycab.model.enums.PaymentType" %>
<%@ page import="com.arvin.megacitycab.model.enums.PaymentMethod" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Booking booking = (Booking) request.getAttribute("booking");
    List<User> drivers = (List<User>) request.getAttribute("drivers");
    User driver = null;

    if (request.getAttribute("driver") instanceof User){
        driver = (User) request.getAttribute("driver");
    }
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Booking - Mega City Cab</title>
    <link rel="icon" type="image/x-icon" href="img/favicon.ico">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;700&family=Open+Sans:wght@400;600&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
    <link rel="stylesheet" href="css/admin-style.css">
    <link rel="stylesheet" href="css/edit-booking.css">
</head>

<body>
<div class="container">

    <jsp:include page="admin-side-bar.jsp"/>

    <div class="content">
        <div class="edit-booking-container">
            <h1>Edit Booking</h1>

            <!-- Booking Information -->
            <div class="booking-info">
                <h2>Booking Information</h2>
                <div class="info-item">
                    <span class="info-label">Booking ID:</span>
                    <span class="info-value">#<%= booking.getId() %></span>
                </div>
                <div class="info-item">
                    <span class="info-label">Pickup Location:</span>
                    <span class="info-value"><%= booking.getPickup_location() %></span>
                </div>
                <div class="info-item">
                    <span class="info-label">Pickup Time:</span>
                    <span class="info-value"><%= booking.getPickup_datetime() %></span>
                </div>
                <div class="info-item">
                    <span class="info-label">Drop-off Location:</span>
                    <span class="info-value"><%= booking.getDropoff_location() %></span>
                </div>
                <div class="info-item">
                    <span class="info-label">Distance:</span>
                    <span class="info-value"><%= booking.getTotal_distance() %> km</span>
                </div>
                <div class="info-item">
                    <span class="info-label">Total Price:</span>
                    <span class="info-value">LKR <%= booking.getTotal_price() %></span>
                </div>
                <div class="info-item">
                    <span class="info-label">Payment Type:</span>
                    <span class="info-value"><%= PaymentMethod.fromInt(booking.getPayment_method()).toString() %></span>
                </div>
            </div>

            <!-- Edit Booking Form -->
            <form class="edit-booking-form" action="form-update-booking" method="post">
                <input type="hidden" name="booking_id" value="<%= booking.getId() %>">

                <!-- Assign Driver -->
                <div class="form-group">
                    <label for="driver">Assign Driver:</label>
                    <select id="driver" name="driver_id">
                        <option value="-1">Select Driver</option>
                        <%
                            for (User d : drivers) {
                        %>
                        <option value="<%= d.getId() %>" <%= booking.getDriver_id() == d.getId() ? "selected" : "" %>>
                            <%= d.getName() %> (<%= d.getName() %>)
                        </option>
                        <%
                            }
                        %>
                    </select>
                </div>

                <!-- Change Status -->
                <div class="form-group">
                    <label for="status">Booking Status:</label>
                    <select id="status" name="status" required>
                        <%
                            for (BookingStatus status : BookingStatus.values()) {
                        %>
                        <option value="<%= status.getValue() %>" <%= booking.getStatus() == status.getValue() ? "selected" : "" %>>
                            <%= status.toString() %>
                        </option>
                        <%
                            }
                        %>
                    </select>
                </div>

                <!-- Submit Button -->
                <button type="submit">Save Changes</button>
            </form>
        </div>
    </div>
</div>
</body>

</html>