<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mega City Cab - Book a Taxi</title>
    <link rel="icon" type="image/x-icon" href="img/favicon.ico">
    <link rel="stylesheet" href="css/customer-pages.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
</head>
<body>
<!-- Navigation Bar -->
<jsp:include page="user-nav-bar.jsp" />

<!-- Booking Page -->
<div class="booking-container">
    <h1>Book Your Ride with Mega City Cab</h1>
    <form id="booking-form">
        <div class="form-group">
            <label for="pickup-location">Pickup Location</label>
            <input type="text" id="pickup-location" placeholder="Enter pickup address" required>
        </div>
        <div class="form-group">
            <label for="pickup-time">Pickup Time</label>
            <input type="datetime-local" id="pickup-time" required>
        </div>
        <div class="form-group">
            <label for="dropoff-location">Drop-off Location</label>
            <input type="text" id="dropoff-location" placeholder="Enter drop-off address" required>
        </div>
        <div class="form-group">
            <label for="vehicle-type">Vehicle Type</label>
            <select id="vehicle-type" required>
                <option value="sedan">Sedan</option>
                <option value="suv">SUV</option>
                <option value="luxury">Luxury</option>
                <option value="van">Van</option>
            </select>
        </div>
        <div class="form-group">
            <label for="distance">Total Distance (km)</label>
            <input type="number" id="distance" placeholder="Enter distance" required>
        </div>
        <div class="form-group">
            <label for="price">Total Price ($)</label>
            <input type="number" id="price" placeholder="Enter price" required>
        </div>
        <button type="submit">Book Now</button>
    </form>
</div>

<!-- Footer -->
<jsp:include page="user-footer.jsp" />

</body>
</html>