<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <jsp:include page="admin-side-bar.jsp" />

    <div class="content">
        <!-- Content Card-->
        <div class="card" style="width: 400px;">
            <!-- Card Header -->
            <div class="card-header">
                <h3 class="card-title">New User</h3>
            </div>
            <!-- Card Body-->
            <div class="card-body">
                <form action="form-new-user" method="post">

                    <%-- User Type --%>
                    <div class="form-group">
                        <label for="user_type">User Type <span class="form-required">*</span></label>
                        <select name="user_type" id="user_type" required>
                            <option value="1">Customer</option>
                            <option value="2">Driver</option>
                            <option value="3">Admin</option>
                        </select>
                    </div>

                    <%-- Name --%>
                    <div class="form-group">
                        <label for="name">Name <span class="form-required">*</span></label>
                        <input type="text" name="name" id="name" maxlength="100" required>
                    </div>

                    <%-- NIC --%>
                    <div class="form-group">
                        <label for="nic">NIC <span class="form-required">*</span></label>
                        <input type="text" id="nic" name="nic"
                               placeholder="NIC Number"
                               pattern="(\d{12}|\d{9}V)"
                               title="Enter valid NIC (12 digits or 9 digits with V)"
                               required>
                    </div>

                    <%-- Address --%>
                    <div class="form-group">
                        <label for="address">Address <span class="form-required">*</span></label>
                        <input type="text" name="address" id="address" maxlength="100" required>
                    </div>

                    <%-- Email --%>
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="email" name="email" id="email" maxlength="100" required>
                    </div>

                    <%-- Mobile --%>
                    <div class="form-group">
                        <label for="mobile">Mobile <span class="form-required">*</span></label>
                        <input type="tel" name="mobile"
                               id="mobile"
                               placeholder="Mobile Number"
                               pattern="[0-9]{10}"
                               title="10 digit mobile number"
                               required>
                    </div>

                    <%-- Password --%>
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input type="password" name="password" id="password" maxlength="100" required>
                    </div>


                    <button class="action-button" type="submit">Submit</button>
                </form>
            </div>
        </div>
    </div>
</div>

</body>

</html>