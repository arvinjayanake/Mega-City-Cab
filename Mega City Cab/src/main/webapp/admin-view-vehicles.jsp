<%@ page import="com.arvin.megacitycab.model.base.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.arvin.megacitycab.model.Vehicle" %>
<%@ page import="com.arvin.megacitycab.model.enums.VehicleCategory" %>
<%@ page import="com.arvin.megacitycab.model.enums.VehicleStatus" %>
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
                <h3 class="card-title">Vehicles</h3>
            </div>
            <!-- Card Body-->
            <div class="card-body">
                <!-- Search Form -->
                <form class="search-form" action="admin-view-vehicles" method="get">
                    <input type="text" class="search-input" name="search" placeholder="Search..." value="<%= searchText %>">
                    <button type="submit" class="search-button">Search</button>
                </form>
                <!-- Table Data-->
                <table class="table">
                    <thead>
                    <tr>
                        <th>Vehicle</th>
                        <th>Category</th>
                        <th>Reg. No</th>
                        <th>Passenger<br>Capacity</th>
                        <th>Luggage<br>Capacity</th>
                        <th>Price<br>Per KM</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<Vehicle> vehicles = (List<Vehicle>) request.getAttribute("vehicles");
                        if (vehicles != null) {
                            for (Vehicle vehicle : vehicles) {
                    %>
                    <tr>
                        <td class="cell-content"><%= vehicle.fullNameWithYear() %></td>
                        <td class="cell-content"><%= VehicleCategory.fromInt(vehicle.getCategory()) %></td>
                        <td class="cell-content"><%= vehicle.getRegistration_number() != null ? vehicle.getRegistration_number() : "-" %></td>
                        <td class="cell-content"><%= vehicle.getPassenger_capacity() %></td>
                        <td class="cell-content"><%= vehicle.getLuggage_capacity() != null ? vehicle.getLuggage_capacity() : "-" %></td>
                        <td class="cell-content"><%= vehicle.getPrice_per_km() %></td>
                        <td class="cell-content"><%= VehicleStatus.fromInt(vehicle.getStatus()) %></td>
                        <td class="cell-content">
                            <a class="table-button" href="admin-update-vehicle?id=<%= vehicle.getId() %>" type="button"><i class="fa fa-edit"></i></a>
                            <a class="table-button" href="admin-delete-vehicle?id=<%= vehicle.getId() %>" type="button"><i class="fa fa-trash"></i></a>
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