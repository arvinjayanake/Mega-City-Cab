<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mega City Cab - New Vehicle</title>
    <link rel="icon" type="image/x-icon" href="img/favicon.ico">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;700&family=Open+Sans:wght@400;600&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
    <link rel="stylesheet" href="css/admin-style.css">
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
                <form action="form-new-vehicle" method="post">

                    <%-- Make --%>
                    <div class="form-group">
                        <label for="make">Make <span class="form-required">*</span></label>
                        <input type="text" name="make" id="make" maxlength="50" required>
                    </div>

                    <%-- Model --%>
                    <div class="form-group">
                        <label for="model">Model <span class="form-required">*</span></label>
                        <input type="text" name="model" id="model" maxlength="50" required>
                    </div>

                    <%-- Category --%>
                    <div class="form-group">
                        <label for="category">Vehicle Category <span class="form-required">*</span></label>
                        <select name="category" id="category" required>
                            <option value="0">Uncategorized</option>
                            <option value="1">Sedan</option>
                            <option value="2">SUV</option>
                            <option value="3">Van</option>
                            <option value="4">Hatchback</option>
                            <option value="5">Electric</option>
                        </select>
                    </div>

                    <%-- Year --%>
                    <div class="form-group">
                        <label for="year">Manufacture Year <span class="form-required">*</span></label>
                        <input type="number" name="year" id="year" maxlength="4" required>
                    </div>

                    <%-- Registration Number --%>
                    <div class="form-group">
                        <label for="registration_number">Registration Number <span class="form-required">*</span></label>
                        <input type="text" name="registration_number" id="registration_number" maxlength="20" required>
                    </div>

                    <%-- Passenger Capacity --%>
                    <div class="form-group">
                        <label for="passenger_capacity">Passenger Capacity <span class="form-required">*</span></label>
                        <input type="number" name="passenger_capacity" id="passenger_capacity" maxlength="2" required>
                    </div>

                    <%-- Luggage Capacity --%>
                    <div class="form-group">
                        <label for="luggage_capacity">Luggage Capacity <span class="form-required">*</span></label>
                        <input type="text" name="luggage_capacity" id="luggage_capacity" maxlength="20" required>
                    </div>

                    <%-- Price Per Km --%>
                    <div class="form-group">
                        <label for="price_per_km">Price Per KM <span class="form-required">*</span></label>
                        <input type="number" id="price_per_km" name="price_per_km" step="0.01" min="0"
                               placeholder="0.00">
                    </div>

                    <%-- Status --%>
                    <div class="form-group">
                        <label for="status">Status <span class="form-required">*</span></label>
                        <select name="status" id="status" required>
                            <option value="0">Default</option>
                            <option value="1">Available</option>
                            <option value="2">Not Available</option>
                            <option value="3">Maintenance</option>
                        </select>
                    </div>

                    <button class="action-button" type="submit">Submit</button>
                </form>
            </div>
        </div>
    </div>
</div>

</body>

</html>