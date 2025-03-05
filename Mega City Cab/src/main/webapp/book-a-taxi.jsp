<%@ page import="com.arvin.megacitycab.model.Vehicle" %>
<%@ page import="java.util.List" %>
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
<jsp:include page="user-nav-bar.jsp"/>

<!-- Booking Page -->
<div class="booking-container">
    <h1>Book Your Ride with Mega City Cab</h1>
    <form id="form-book-a-taxi" action="form-book-a-taxi" method="post">

        <input type="hidden" name="vehicle_name" id="vehicle_name">
        <div class="form-group">
            <label for="pickup_location">Pickup Location</label>
            <input type="text" id="pickup_location" name="pickup_location" placeholder="Enter pickup address" required>
        </div>
        <div class="form-group">
            <label for="pickup_time">Pickup Time</label>
            <input type="datetime-local" id="pickup_time" name="pickup_time" required>
        </div>
        <div class="form-group">
            <label for="drop_off_location">Drop-off Location</label>
            <input type="text" id="drop_off_location" name="drop_off_location" placeholder="Enter drop-off address"
                   required>
        </div>
        <div class="form-group">
            <label for="vehicle">Vehicle</label>
            <select id="vehicle" name="vehicle" required onchange="showVehicleInfo(this)">
                <%
                    List<Vehicle> vehicles = (List<Vehicle>) request.getAttribute("vehicles");
                    if (vehicles != null) {
                        for (Vehicle vehicle : vehicles) {
                %>
                <option value="<%= vehicle.getId() %>"
                        data-full-name="<%= vehicle.fullNameWithYear() %>"
                        data-model="<%= vehicle.getModel() %>"
                        data-year="<%= vehicle.getYear() %>"
                        data-passenger_capacity="<%= vehicle.getPassenger_capacity() %>"
                        data-luggage_capacity="<%= vehicle.getLuggage_capacity() %>"
                        data-price-per-km="<%= vehicle.getPrice_per_km() %>">
                    <%= vehicle.fullNameWithYear() %>
                </option>
                <%
                        }
                    }
                %>
            </select>
            <!-- Vehicle Information Display -->
            <div id="vehicle-info" class="vehicle-info">
            </div>
        </div>
        <div class="form-group">
            <label for="distance">Total Distance (km)</label>
            <input type="number" id="distance" name="distance" placeholder="Enter distance" required
                   oninput="calculatePrice()">
        </div>
        <div class="form-group">
            <label for="price">Total Price (LKR)</label>
            <input type="number" id="price" name="price" placeholder="Enter price" readonly required>
        </div>
        <div class="form-group">
            <label for="payment_method">Payment Method</label>
            <select id="payment_method" name="payment_method" required>
                <option value="1">Cash</option>
                <option value="2">Online</option>
            </select>
        </div>
        <button type="submit">Book Now</button>
    </form>
</div>

<!-- Footer -->
<jsp:include page="user-footer.jsp"/>

<!-- JavaScript -->
<script>
    function disablePastDates() {
        const now = new Date();
        const year = now.getFullYear();
        const month = String(now.getMonth() + 1).padStart(2, '0'); // Months are 0-indexed
        const day = String(now.getDate()).padStart(2, '0');
        const hours = String(now.getHours()).padStart(2, '0');
        const minutes = String(now.getMinutes()).padStart(2, '0');

        const minDateTime = year + "-" + month + "-" + day + "T" + hours + ":" + minutes;
        document.getElementById('pickup_time').setAttribute('min', minDateTime);
    }

    // Function to display vehicle information
    function showVehicleInfo(select) {
        const selectedOption = select.options[select.selectedIndex];
        const model = selectedOption.getAttribute('data-model');
        const year = selectedOption.getAttribute('data-year');
        const passengerCapacity = selectedOption.getAttribute('data-passenger_capacity');
        const luggageCapacity = selectedOption.getAttribute('data-luggage_capacity');
        const pricePerKm = selectedOption.getAttribute('data-price-per-km');
        const fullName = selectedOption.getAttribute('data-full-name');

        document.getElementById('vehicle_name').value = fullName;

        const vehicleInfo = "<p><strong>Model:</strong> " + model + " </p>" +
            "<p><strong>Year:</strong> " + year + "</p>" +
            "<p><strong>Passenge Capacity:</strong> " + passengerCapacity + " passengers</p>" +
            "<p><strong>Luggage Capacity:</strong> " + luggageCapacity + "</p>" +
            "<p><strong>Price per km:</strong> " + pricePerKm + "</p>";

        document.getElementById('vehicle-info').innerHTML = vehicleInfo;

        calculatePrice();
    }

    // Function to calculate total price
    function calculatePrice() {
        const distance = document.getElementById('distance').value;
        const selectedOption = document.getElementById('vehicle').options[document.getElementById('vehicle').selectedIndex];
        const pricePerKm = selectedOption.getAttribute('data-price-per-km');

        if (distance && pricePerKm) {
            const totalPrice = (distance * pricePerKm).toFixed(2);
            document.getElementById('price').value = totalPrice;
        } else {
            document.getElementById('price').value = '';
        }
    }

    // Initialize vehicle info on page load
    document.addEventListener('DOMContentLoaded', function () {
        showVehicleInfo(document.getElementById('vehicle'));
    });

    window.onload = disablePastDates;
</script>
</body>
</html>