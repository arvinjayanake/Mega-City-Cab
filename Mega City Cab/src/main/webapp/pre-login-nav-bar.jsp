<%@ page import="com.arvin.megacitycab.model.base.User" %>
<%@ page import="com.arvin.megacitycab.model.enums.UserType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User loggedUser = null;
    if (session.getAttribute("user") instanceof User) {
        loggedUser = (User) session.getAttribute("user");
    }
%>
<nav>
    <div class="logo">Mega City Cab</div>
    <ul class="nav-links">
        <li><a href="${pageContext.request.contextPath}/" class="login-button">Home</a></li>
        <li><a href="how-to-use" class="login-button">How to Use</a></li>
        <li><a href="contact-us" class="login-button">Contact Us</a></li>
        <% if (loggedUser == null) {%>
        <li><a href="login" class="login-button">Login</a></li>
        <% } else { %>
        <% if (loggedUser.getType() == UserType.ADMIN.getValue()) {%>
        <li><a href="admin-view-customers" class="login-button">Admin Dashboard</a></li>
        <% } else if (loggedUser.getType() == UserType.CUSTOMER.getValue()) {%>
        <li><a href="book-a-taxi" class="login-button">Book a Taxi</a></li>
        <% } %>
        <% } %>
    </ul>
</nav>