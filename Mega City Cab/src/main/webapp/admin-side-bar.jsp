<%
    String currentPage = request.getRequestURI();
%>
<div class="sidebar">
    <div class="logo-title" style="font-size: 13px;">
        <img src="img/app_icon.png" alt="Site Logo">
        <h2>Mega City Cab Admin</h2>
    </div>
    <div style="margin-top: 16px; font-size: 12px; font-weight: bold; color: #2196f3">Manage Users</div>
    <ul style="margin-top: 8px;">
        <%-- New User--%>
        <% if (currentPage.contains("admin-new-user.jsp")) { %>
        <li>New User</li>
        <% } else { %>
        <li><a href="admin-new-user">New User</a></li>
        <% } %>

        <%-- Customers--%>
        <% if (currentPage.contains("admin-view-customers.jsp")) { %>
        <li>Customers</li>
        <% } else { %>
        <li><a href="admin-view-customers">Customers</a></li>
        <% } %>

        <%-- Drivers--%>
        <% if (currentPage.contains("admin-view-drivers.jsp")) { %>
        <li>Drivers</li>
        <% } else { %>
        <li><a href="admin-view-drivers">Drivers</a></li>
        <% } %>

        <%-- Admins --%>
        <% if (currentPage.contains("admin-view-admins.jsp")) { %>
        <li>Admins</li>
        <% } else { %>
        <li><a href="admin-view-admins">Admins</a></li>
        <% } %>
    </ul>

    <div style="margin-top: 16px; font-size: 12px; font-weight: bold; color: #2196f3">Manage Vehicles</div>
    <ul style="margin-top: 8px;">
        <%-- New Vehicle--%>
        <% if (currentPage.contains("admin-new-vehicle.jsp")) { %>
        <li>New Vehicle</li>
        <% } else { %>
        <li><a href="admin-new-vehicle">New Vehicle</a></li>
        <% } %>

        <%-- Vehicles --%>
        <% if (currentPage.contains("admin-view-vehicles.jsp")) { %>
        <li>Vehicles</li>
        <% } else { %>
        <li><a href="admin-view-vehicles">Vehicles</a></li>
        <% } %>
    </ul>

    <div style="margin-top: 16px; font-size: 12px; font-weight: bold; color: #2196f3">Manage Bookings</div>
    <ul style="margin-top: 8px;">
        <%-- Bookings --%>
        <% if (currentPage.contains("admin-view-bookings.jsp")) { %>
        <li>Bookings</li>
        <% } else { %>
        <li><a href="admin-view-bookings">Bookings</a></li>
        <% } %>
    </ul>

    <div style="margin-top: 16px; font-size: 12px; font-weight: bold; color: #2196f3">Other Controls:</div>
    <ul style="margin-top: 8px;">
        <li><a href="form-logout">Logout</a></li>
    </ul>
</div>