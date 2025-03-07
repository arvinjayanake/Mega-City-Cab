<%@ page import="com.arvin.megacitycab.model.base.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.arvin.megacitycab.model.Vehicle" %>
<%@ page import="com.arvin.megacitycab.model.enums.VehicleCategory" %>
<%@ page import="com.arvin.megacitycab.model.enums.VehicleStatus" %>
<%@ page import="com.arvin.megacitycab.model.Booking" %>
<%@ page import="java.awt.print.Book" %>
<%@ page import="com.arvin.megacitycab.model.enums.BookingStatus" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String searchText = request.getParameter("search") == null ? "" : request.getParameter("search"); %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mega City Cab - Vehicles</title>
    <link rel="icon" type="image/x-icon" href="img/favicon.ico">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;700&family=Open+Sans:wght@400;600&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
    <link rel="stylesheet" href="css/admin-style.css">
</head>

<body>
<div class="container">

    <jsp:include page="admin-side-bar.jsp" />

    <div class="content">
        <!-- Content Card-->
        <div class="card">
            <!-- Card Header -->
            <div class="card-header">
                <h3 class="card-title">Bookings</h3>
            </div>
            <!-- Card Body-->
            <div class="card-body">
                <!-- Search Form -->
                <form class="search-form" action="admin-view-bookings" method="get">
                    <input type="text" class="search-input" name="search" placeholder="Search..." value="<%= searchText %>">
                    <button type="submit" class="search-button">Search</button>
                </form>
                <!-- Table Data-->
                <table class="table">
                    <thead>
                    <tr>
                        <th>Booking ID</th>
                        <th>Pickup Info</th>
                        <th>Drop-off Info</th>
                        <th>Distance</th>
                        <th>Price</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");
                        if (bookings != null) {
                            for (Booking booking : bookings) {
                    %>
                    <tr>
                        <td class="cell-content">#<%= booking.getId() %></td>
                        <td class="cell-content"><%= booking.getPickup_location() + "<br>" + booking.getPickup_datetime() %></td>
                        <td class="cell-content"><%= booking.getDropoff_location() %></td>
                        <td class="cell-content"><%= booking.getTotal_distance() %>km</td>
                        <td class="cell-content"><%= booking.getTotal_price() %></td>
                        <td class="cell-content"><%= BookingStatus.fromInt(booking.getStatus()).toString() %></td>
                        <td class="cell-content">
                            <a class="table-button" href="admin-booking-details?id=<%= booking.getId() %>" type="button"><i class="fa fa-eye"></i></a>
                            <% if (booking.getStatus() == BookingStatus.PENDING.getValue()) {%>
                            <a class="table-button" href="admin-update-booking?id=<%= booking.getId() %>" type="button"><i class="fa fa-edit"></i></a>
                            <% }%>
                        </td>
                    </tr>
                    <%
                            }
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

</body>

</html>