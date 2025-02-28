<%@ page import="com.arvin.megacitycab.model.base.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = null;
    Object userObj = session.getAttribute("user");
    if (userObj instanceof User){
        user = (User) userObj;
    } else {
        response.sendRedirect("login");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Home</title>
</head>
<body>
<h1><%= "Welcome to Home" %></h1>
<br/>
<form action="form-logout" method="post">
    <input type="submit" value="Logout">
</form>
</body>
</html>
