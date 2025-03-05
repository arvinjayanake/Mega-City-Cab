<%
  String currentPage = request.getRequestURI();
  System.out.println("Nav bar: " + currentPage);
%>

<nav>
  <div class="logo">Mega City Cab</div>
  <ul class="nav-links">
    <li><a href="book-a-taxi" <%= currentPage.contains("book-a-taxi.jsp") ? "style=\"color: #FFD700;\"": "" %>>Book a Taxi</a></li>
    <li><a href="booking-history" <%= currentPage.contains("booking-history.jsp") ? "style=\"color: #FFD700;\"": "" %>>Booking History</a></li>
    <li><a href="profile" <%= currentPage.contains("profile.jsp") ? "style=\"color: #FFD700;\"": "" %>>Profile</a></li>
    <li><a href="contact-us" <%= currentPage.contains("contact-us.jsp") ? "style=\"color: #FFD700;\"": "" %>>Contact Us</a></li>
    <li><a href="form-logout">Logout</a></li>
  </ul>
</nav>