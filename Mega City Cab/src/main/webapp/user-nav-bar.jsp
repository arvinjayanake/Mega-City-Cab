<%
  String currentPage = request.getRequestURI();
%>

<nav>
  <div class="logo">Mega City Cab</div>
  <ul class="nav-links">
    <li><a href="book-a-taxi" <%= currentPage.contains("book-a-taxi.jsp") ? "class=active": "" %>>Book a Taxi</a></li>
    <li><a href="booking-history"  <%= currentPage.contains("booking-history.jsp") ? "class=active": "" %>>Booking History</a></li>
    <li><a href="profile" <%= currentPage.contains("profile.jsp") ? "class=active": "" %> >Profile</a></li>
    <li><a href="contact-us" <%= currentPage.contains("contact-us.jsp") ? "class=active": "" %>>Contact Us</a></li>
    <li><a href="form-logout">Logout</a></li>
  </ul>
</nav>