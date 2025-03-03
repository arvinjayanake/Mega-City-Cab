<%@ page import="com.arvin.megacitycab.model.Vehicle" %>
<%@ page import="com.arvin.megacitycab.model.enums.VehicleStatus" %>
<%@ page import="com.arvin.megacitycab.model.enums.VehicleCategory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Vehicle vehicle = (Vehicle) request.getAttribute("vehicle");
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mega City Cab - Delete Vehicle</title>
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
        <div class="card" style="width: 400px;">
            <!-- Card Header -->
            <div class="card-header">
                <h3 class="card-title">Delete Vehicle</h3>
            </div>
            <!-- Card Body-->
            <div class="card-body">
                <ul>
                    <li><strong>ID:</strong> <%= vehicle.getId()  %></li>
                    <li><strong>Make:</strong> <%= vehicle.getMake()  %></li>
                    <li><strong>Model:</strong> <%= vehicle.getModel()  %></li>
                    <li><strong>Category:</strong> <%= VehicleCategory.fromInt(vehicle.getCategory())  %></li>
                    <li><strong>Year:</strong> <%= vehicle.getYear()  %></li>
                    <li><strong>Registration No:</strong> <%= vehicle.getRegistration_number()  %></li>
                    <li><strong>Passenger Capacity:</strong> <%= vehicle.getPassenger_capacity()  %></li>
                    <li><strong>Price Per KM:</strong> <%= vehicle.getPrice_per_km()  %></li>
                    <li><strong>Status:</strong> <%= VehicleStatus.fromInt(vehicle.getStatus())  %></li>
                    <li><strong>Created At:</strong> <%= vehicle.getCreated_at()  %></li>
                    <li><strong>Updated At:</strong> <%= vehicle.getUpdated_at()  %></li>
                </ul>

                <form action="form-delete-vehicle" method="post">
                    <input type="hidden" name="id" value="<%= vehicle.getId() %>">
                    <button class="action-button" type="submit">Delete Vehicle</button>
                </form>
            </div>
        </div>
    </div>
</div>

</body>

</html>