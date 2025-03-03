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
<jsp:include page="user-nav-bar.jsp" />

<!-- Booking History Section -->
<div class="booking-history-container">
  <h1>Booking History</h1>
  <div class="booking-cards">
    <!-- Card 1 -->
    <div class="booking-card">
      <div class="card-header">
        <h3>Booking #1</h3>
        <span class="status completed">Completed</span>
      </div>
      <div class="card-body">
        <p><strong>Pickup:</strong> 123 Main St, City A</p>
        <p><strong>Drop-off:</strong> 456 Elm St, City B</p>
        <p><strong>Date & Time:</strong> 2023-10-15 10:00 AM</p>
        <p><strong>Vehicle:</strong> Sedan</p>
        <p><strong>Distance:</strong> 12 km</p>
        <p><strong>Price:</strong> $25.00</p>
      </div>
    </div>

    <!-- Card 2 -->
    <div class="booking-card">
      <div class="card-header">
        <h3>Booking #2</h3>
        <span class="status cancelled">Cancelled</span>
      </div>
      <div class="card-body">
        <p><strong>Pickup:</strong> 789 Oak St, City C</p>
        <p><strong>Drop-off:</strong> 101 Pine St, City D</p>
        <p><strong>Date & Time:</strong> 2023-10-10 03:00 PM</p>
        <p><strong>Vehicle:</strong> SUV</p>
        <p><strong>Distance:</strong> 18 km</p>
        <p><strong>Price:</strong> $35.00</p>
      </div>
    </div>

    <!-- Card 3 -->
    <div class="booking-card">
      <div class="card-header">
        <h3>Booking #3</h3>
        <span class="status upcoming">Upcoming</span>
      </div>
      <div class="card-body">
        <p><strong>Pickup:</strong> 202 Maple St, City E</p>
        <p><strong>Drop-off:</strong> 303 Birch St, City F</p>
        <p><strong>Date & Time:</strong> 2023-10-20 09:00 AM</p>
        <p><strong>Vehicle:</strong> Luxury</p>
        <p><strong>Distance:</strong> 25 km</p>
        <p><strong>Price:</strong> $50.00</p>
      </div>
    </div>

    <div class="booking-card">
      <div class="card-header">
        <h3>Booking #3</h3>
        <span class="status upcoming">Upcoming</span>
      </div>
      <div class="card-body">
        <p><strong>Pickup:</strong> 202 Maple St, City E</p>
        <p><strong>Drop-off:</strong> 303 Birch St, City F</p>
        <p><strong>Date & Time:</strong> 2023-10-20 09:00 AM</p>
        <p><strong>Vehicle:</strong> Luxury</p>
        <p><strong>Distance:</strong> 25 km</p>
        <p><strong>Price:</strong> $50.00</p>
      </div>
    </div>
  </div>
</div>

<!-- Footer -->
<jsp:include page="user-footer.jsp" />
</body>
</html>