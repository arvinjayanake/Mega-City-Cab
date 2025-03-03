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
    <title>Mega City Cab - Update <%= userTypeString %></title>
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
                <h3 class="card-title">Update <%= userTypeString %>
                </h3>
            </div>
            <!-- Card Body-->
            <div class="card-body">
                <form action="form-update-user" method="post">
                    <input type="hidden" name="id" value="<%= user.getId() %>">

                    <%-- Name --%>
                    <div class="form-group">
                        <label for="name">Name <span class="form-required">*</span></label>
                        <input type="text" name="name" id="name" maxlength="100" value="<%= user.getName() %>" required>
                    </div>

                    <%-- NIC --%>
                    <div class="form-group">
                        <label for="nic">NIC <span class="form-required">*</span></label>
                        <input type="text" id="nic" name="nic"
                               value="<%= user.getNic() %>"
                               placeholder="NIC Number"
                               maxlength="12"
                               pattern="(\d{12}|\d{9}V)"
                               title="Enter valid NIC (12 digits or 9 digits with V)"
                               required>
                    </div>

                    <%-- Address --%>
                    <div class="form-group">
                        <label for="address">Address <span class="form-required">*</span></label>
                        <input type="text" name="address" id="address" maxlength="100" value="<%= user.getAddress() %>"
                               required>
                    </div>

                    <%-- Email --%>
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="email" name="email" id="email" maxlength="100" value="<%= user.getEmail() %>"
                               required>
                    </div>

                    <%-- Mobile --%>
                    <div class="form-group">
                        <label for="mobile">City <span class="form-required">*</span></label>
                        <input type="tel" name="mobile"
                               id="mobile"
                               maxlength="10"
                               value="<%= user.getMobile() %>"
                               placeholder="Mobile Number"
                               pattern="[0-9]{10}"
                               title="10 digit mobile number"
                               required>
                    </div>

                    <%-- IS Verified --%>
                    <div class="form-group">
                        <label for="is_verified">Verified <span class="form-required">*</span></label>
                        <select name="is_verified" id="is_verified" required>
                            <option value="0" <%= user.getIs_verified() == 0 ? "selected" : "" %>>Not Verified</option>
                            <option value="1" <%= user.getIs_verified() == 1 ? "selected" : "" %>>Verified</option>
                        </select>
                    </div>

                    <%-- Password --%>
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input type="password" name="password" id="password" maxlength="100">
                    </div>

                    <button class="action-button" type="submit">Update</button>
                </form>
            </div>
        </div>
    </div>
</div>

</body>

</html>