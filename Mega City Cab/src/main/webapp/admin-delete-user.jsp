<%@ page import="com.arvin.megacitycab.model.base.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.arvin.megacitycab.model.enums.UserType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) request.getAttribute("user");
    String userTypeString = "Customer";

    if (user.getType() == UserType.DRIVER.getValue()) {
        userTypeString = "Driver";
    } else if (user.getType() == UserType.ADMIN.getValue()) {
        userTypeString = "Admin";
    }
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

    <div class="sidebar">
        <div class="logo-title">
            <img src="img/app_icon.png" alt="Site Logo">
            <h2>Mega City Cab Admin</h2>
        </div>
        <div style="margin-top: 16px; font-size: 12px; font-weight: bold; color: #2196f3">Manage Users</div>
        <ul style="margin-top: 8px;">
            <li><a href="admin-view-customers">Customers</a></li>
            <li><a href="admin-view-drivers">Drivers</a></li>
            <li><a href="admin-view-admins">Admins</a></li>
            <li><a href="#">New User</a></li>
        </ul>

        <div style="margin-top: 16px; font-size: 12px; font-weight: bold; color: #2196f3">Other Controls:</div>
        <ul style="margin-top: 8px;">
            <li><a href="form-logout">Logout</a></li>
        </ul>
    </div>

    <div class="content">
        <!-- Content Card-->
        <div class="card" style="width: 400px;">
            <!-- Card Header -->
            <div class="card-header">
                <h3 class="card-title">Delete <%= userTypeString %></h3>
            </div>
            <!-- Card Body-->
            <div class="card-body">
                <ul>
                    <li><strong>ID:</strong> <%= user.getId()  %></li>
                    <li><strong>Name:</strong> <%= user.getName()  %></li>
                    <li><strong>NIC:</strong> <%= user.getNic()  %></li>
                    <li><strong>Address:</strong> <%= user.getAddress()  %></li>
                    <li><strong>Email:</strong> <%= user.getEmail()  %></li>
                    <li><strong>Mobile:</strong> <%= user.getMobile()  %></li>
                    <li><strong>Type:</strong> <%= userTypeString  %></li>
                    <li><strong>Verified Status:</strong> <%= user.getIs_verified() == 1 ? "Verified" : "Not Verified"  %></li>
                </ul>

                <form action="form-delete-user" method="post">
                    <input type="hidden" name="id" value="<%= user.getId() %>">
                    <input type="hidden" name="user_type" value="<%= user.getType() %>">
                    <button class="action-button" type="submit">Delete <%= userTypeString %></button>
                </form>
            </div>
        </div>
    </div>
</div>

</body>

</html>