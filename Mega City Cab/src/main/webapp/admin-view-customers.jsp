<%@ page import="com.arvin.megacitycab.model.base.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String searchText = request.getParameter("search") == null ? "" : request.getParameter("search"); %>
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
            <li><a href="admin-new-user">New User</a></li>
            <li>Customers</li>
            <li><a href="admin-view-drivers">Drivers</a></li>
            <li><a href="admin-view-admins">Admins</a></li>
        </ul>

        <div style="margin-top: 16px; font-size: 12px; font-weight: bold; color: #2196f3">Other Controls:</div>
        <ul style="margin-top: 8px;">
            <li><a href="form-logout">Logout</a></li>
        </ul>
    </div>

    <div class="content">
        <!-- Content Card-->
        <div class="card">
            <!-- Card Header -->
            <div class="card-header">
                <h3 class="card-title">Customers</h3>
            </div>
            <!-- Card Body-->
            <div class="card-body">
                <!-- Search Form -->
                <form class="search-form" action="admin-view-customers" method="get">
                    <input type="text" class="search-input" name="search" placeholder="Search..." value="<%= searchText %>">
                    <button type="submit" class="search-button">Search</button>
                </form>
                <!-- Table Data-->
                <table class="table">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>NIC</th>
                        <th>Address</th>
                        <th>Email</th>
                        <th>Mobile</th>
                        <th>Verified</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<User> users = (List<User>) request.getAttribute("users");
                        if (users != null) {
                            for (User user : users) {
                    %>
                    <tr>
                        <td class="cell-content"><%= user.getName() %></td>
                        <td class="cell-content"><%= user.getNic() != null ? user.getNic() : "-" %></td>
                        <td class="cell-content"><%= user.getAddress() != null ? user.getAddress() : "-" %></td>
                        <td class="cell-content"><%= user.getEmail() %></td>
                        <td class="cell-content"><%= user.getMobile() %></td>
                        <td class="cell-content"><%= user.getIs_verified() == 1 ? "Yes" : "No" %></td>
                        <td class="cell-content">
                            <a class="table-button" href="admin-update-user?id=<%= user.getId() %>" type="button"><i class="fa fa-edit"></i></a>
                            <a class="table-button" href="admin-delete-user?id=<%= user.getId() %>" type="button"><i class="fa fa-trash"></i></a>
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