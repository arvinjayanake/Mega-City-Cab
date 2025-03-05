<%@ page import="com.arvin.megacitycab.model.base.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String searchText = request.getParameter("search") == null ? "" : request.getParameter("search"); %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mega City Cab - Drivers</title>
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
                <h3 class="card-title">Drivers</h3>
            </div>
            <!-- Card Body-->
            <div class="card-body">
                <!-- Search Form -->
                <form class="search-form" action="admin-view-drivers" method="get">
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