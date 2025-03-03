<%@ page import="com.arvin.megacitycab.model.Vehicle" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Vehicle vehicle = (Vehicle) request.getAttribute("vehicle");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FCMS - Fitness Centers</title>
    <link rel="icon" type="image/x-icon" href="img/favicon.ico">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;700&family=Open+Sans:wght@400;600&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>

<body>
<div class="container">

    <jsp:include page="admin-side-bar.jsp"/>

    <div class="content">
        <!-- Content Card-->
        <div class="card" style="width: 400px;">
            <!-- Card Header -->
            <div class="card-header">
                <h3 class="card-title">New Vehicle</h3>
            </div>
            <!-- Card Body-->
            <div class="card-body">
                <form action="form-update-vehicle" method="post">

                    <input type="hidden" name="id" value="<%= vehicle.getId() %>">

                    <%-- Make --%>
                    <div class="form-group">
                        <label for="make">Make <span class="form-required">*</span></label>
                        <input type="text" name="make" id="make" maxlength="50" value="<%= vehicle.getMake() %>" required>
                    </div>

                    <%-- Model --%>
                    <div class="form-group">
                        <label for="model">Model <span class="form-required">*</span></label>
                        <input type="text" name="model" id="model" value="<%= vehicle.getModel() %>" maxlength="50" required>
                    </div>

                    <%-- Category --%>
                    <div class="form-group">
                        <label for="category">Vehicle Category <span class="form-required">*</span></label>
                        <select name="category" id="category" required>
                            <option value="0" <%= vehicle.getCategory() == 0 ? "selected" : "" %>>Uncategorized</option>
                            <option value="1" <%= vehicle.getCategory() == 1 ? "selected" : "" %>>Sedan</option>
                            <option value="2" <%= vehicle.getCategory() == 2 ? "selected" : "" %>>SUV</option>
                            <option value="3" <%= vehicle.getCategory() == 3 ? "selected" : "" %>>Van</option>
                            <option value="4" <%= vehicle.getCategory() == 4 ? "selected" : "" %>>Hatchback</option>
                            <option value="5" <%= vehicle.getCategory() == 5 ? "selected" : "" %>>Electric</option>
                        </select>
                    </div>

                    <%-- Year --%>
                    <div class="form-group">
                        <label for="year">Manufacture Year <span class="form-required">*</span></label>
                        <input type="number" name="year" value="<%= vehicle.getYear() %>" id="year" maxlength="4" required>
                    </div>

                    <%-- Registration Number --%>
                    <div class="form-group">
                        <label for="registration_number">Registration Number <span class="form-required">*</span></label>
                        <input type="text" name="registration_number" value="<%= vehicle.getRegistration_number() %>" id="registration_number" maxlength="20" required>
                    </div>

                    <%-- Passenger Capacity --%>
                    <div class="form-group">
                        <label for="passenger_capacity">Passenger Capacity <span class="form-required">*</span></label>
                        <input type="number" name="passenger_capacity" value="<%= vehicle.getPassenger_capacity() %>" id="passenger_capacity" maxlength="2" required>
                    </div>

                    <%-- Luggage Capacity --%>
                    <div class="form-group">
                        <label for="luggage_capacity">Luggage Capacity <span class="form-required">*</span></label>
                        <input type="text" name="luggage_capacity" value="<%= vehicle.getLuggage_capacity() %>" id="luggage_capacity" maxlength="20" required>
                    </div>

                    <%-- Price Per Km --%>
                    <div class="form-group">
                        <label for="price_per_km">Price Per KM <span class="form-required">*</span></label>
                        <input type="number" id="price_per_km" value="<%= vehicle.getPrice_per_km() %>" name="price_per_km" step="0.01" min="0"
                               placeholder="0.00">
                    </div>

                    <%-- Status --%>
                    <div class="form-group">
                        <label for="status">Status <span class="form-required">*</span></label>
                        <select name="status" id="status" required>
                            <option value="0" <%= vehicle.getStatus() == 0 ? "selected" : "" %>>Default</option>
                            <option value="1" <%= vehicle.getStatus() == 1 ? "selected" : "" %>>Available</option>
                            <option value="2" <%= vehicle.getStatus() == 2 ? "selected" : "" %>>Not Available</option>
                            <option value="3" <%= vehicle.getStatus() == 3 ? "selected" : "" %>>Maintenance</option>
                        </select>
                    </div>

                    <button class="action-button" type="submit">Update Vehicle</button>
                </form>
            </div>
        </div>
    </div>
</div>

</body>

</html>