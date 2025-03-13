<%@ page import="com.arvin.megacitycab.model.Booking" %>
<%@ page import="com.arvin.megacitycab.model.base.User" %>
<%@ page import="com.arvin.megacitycab.model.Vehicle" %>
<%@ page import="com.arvin.megacitycab.model.Payment" %>
<%@ page import="com.arvin.megacitycab.model.enums.BookingStatus" %>
<%@ page import="com.arvin.megacitycab.model.enums.PaymentMethod" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Booking booking = (Booking) request.getAttribute("booking");
    User user = (User) request.getAttribute("user");
    Vehicle vehicle = (Vehicle) request.getAttribute("vehicle");
    Payment payment = null;
    Payment refund = null;
    User driver = null;

    if (request.getAttribute("payment") instanceof Payment) {
        payment = (Payment) request.getAttribute("payment");
    }

    if (request.getAttribute("refund") instanceof Payment){
        refund = (Payment) request.getAttribute("refund");
    }

    if (request.getAttribute("driver") instanceof User){
        driver = (User) request.getAttribute("driver");
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

            <!-- Print Button -->
            <button class="print-button" onclick="printBookingDetails()">
                <i class="fas fa-print"></i> Print Details
            </button>

            <!-- Booking Details -->
            <div class="details-section" id="booking-details">
                <h2>Booking Details</h2>
                <div class="details-item">
                    <span class="details-label">Booking ID:</span>
                    <span class="details-value">#<%= booking.getId() %></span>
                </div>
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
                    <span class="details-label">Government TAX:</span>
                    <span class="details-value">LKR <%= booking.getTax() %></span>
                </div>
                <div class="details-item">
                    <span class="details-label">Discount:</span>
                    <span class="details-value">LKR <%= booking.getDiscount() %></span>
                </div>
                <div class="details-item">
                    <span class="details-label">Total Price:</span>
                    <span class="details-value">LKR <%= booking.getTotal_price() %></span>
                </div>
                <div class="details-item">
                    <span class="details-label">Status:</span>
                    <span class="details-value"><%= BookingStatus.fromInt(booking.getStatus()).toString() %></span>
                </div>
                <div class="details-item">
                    <span class="details-label">Payment Method:</span>
                    <span class="details-value"><%= PaymentMethod.fromInt(booking.getPayment_method()).toString() %></span>
                </div>
            </div>

            <!-- Customer Information -->
            <div class="details-section" id="customer-details">
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

            <!-- Driver Information -->
            <% if (driver != null) { %>
            <div class="details-section" id="driver-details">
                <h2>Driver Information</h2>
                <div class="details-item">
                    <span class="details-label">Name:</span>
                    <span class="details-value"><%= driver.getName() %></span>
                </div>
                <div class="details-item">
                    <span class="details-label">Mobile:</span>
                    <span class="details-value"><%= driver.getMobile() %></span>
                </div>
            </div>
            <% } %>

            <!-- Vehicle Information -->
            <div class="details-section" id="vehicle-details">
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

            <!-- Payment Information -->
            <% if (payment != null) { %>
            <div class="details-section" id="payment-details">
                <h2>Payment Information</h2>
                <div class="details-item">
                    <span class="details-label">Payment Date:</span>
                    <span class="details-value"><%= payment.getPayment_date() %></span>
                </div>
                <div class="details-item">
                    <span class="details-label">Total Amount:</span>
                    <span class="details-value">LKR <%= payment.getAmount() %></span>
                </div>
                <div class="details-item">
                    <span class="details-label">Payment Method:</span>
                    <span class="details-value"><%= payment.getCard_no() %></span>
                </div>
            </div>
            <% } %>

            <!-- Refund Information -->
            <% if (refund != null) { %>
            <div class="details-section" id="refund-details">
                <h2>Refund Information</h2>
                <div class="details-item">
                    <span class="details-label">Refund Date:</span>
                    <span class="details-value"><%= refund.getPayment_date() %></span>
                </div>
                <div class="details-item">
                    <span class="details-label">Total Amount:</span>
                    <span class="details-value">LKR <%= refund.getAmount() %></span>
                </div>
                <div class="details-item">
                    <span class="details-label">Payment Method:</span>
                    <span class="details-value"><%= refund.getCard_no() %></span>
                </div>
            </div>
            <% } %>
        </div>
    </div>
</div>

<!-- JavaScript for Print Functionality -->
<script>
    function printBookingDetails() {
        // Create a new window for printing
        const printWindow = window.open('', '', 'height=600,width=800');
        printWindow.document.write('<html><head><title>Booking Details</title>');
        //printWindow.document.write('<link rel="stylesheet" href="css/booking-details.css">');
        printWindow.document.write('</head><body>');


        // Add the booking details content to the new window
        const bookingDetails = document.getElementById('booking-details').innerHTML;
        const customerDetails = document.getElementById('customer-details').innerHTML;
        const driverDetails = document.getElementById('driver-details') ? document.getElementById('driver-details').innerHTML : '';
        const vehicleDetails = document.getElementById('vehicle-details').innerHTML;
        const paymentDetails = document.getElementById('payment-details') ? document.getElementById('payment-details').innerHTML : '';
        const refundDetails = document.getElementById('refund-details') ? document.getElementById('refund-details').innerHTML : '';

        printWindow.document.write('<h1>Booking Details - Mega City Cab</h1>');
        printWindow.document.write(bookingDetails);
        printWindow.document.write(customerDetails);
        printWindow.document.write(driverDetails);
        printWindow.document.write(vehicleDetails);
        printWindow.document.write(paymentDetails);
        printWindow.document.write(refundDetails);

        printWindow.document.write('</body></html>');
        printWindow.document.close();

        // Trigger the print dialog
        printWindow.print();
    }
</script>
</body>

</html>