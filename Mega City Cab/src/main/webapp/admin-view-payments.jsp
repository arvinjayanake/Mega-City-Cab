<%@ page import="com.arvin.megacitycab.model.base.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.arvin.megacitycab.model.Payment" %>
<%@ page import="com.arvin.megacitycab.model.enums.PaymentType" %>
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
                        <th>Booking ID</th>
                        <th>Transaction<br>Date</th>
                        <th>Card No</th>
                        <th>Payment<br>Type</th>
                        <th>Amount</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<Payment> payments = (List<Payment>) request.getAttribute("payments");
                        if (payments != null) {
                            for (Payment payment : payments) {
                    %>
                    <tr>
                        <td class="cell-content">#<%= payment.getBooking_id() %></td>
                        <td class="cell-content"><%= payment.getPayment_date() %></td>
                        <td class="cell-content"><%= payment.getCard_no() %></td>
                        <td class="cell-content"><%= PaymentType.fromInt(payment.getType()).toString() %></td>
                        <td class="cell-content">LKR <%= payment.getAmount() %></td>
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