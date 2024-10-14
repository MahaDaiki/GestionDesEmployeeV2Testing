<%--
  Created by IntelliJ IDEA.
  User: AMD
  Date: 10/10/2024
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="assets/css/Style.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
</head>
<body>
<nav>   <h1> GEV2 </h1> </nav>

<div class="loginpage" >
    <div class="image-container">
        <img src="assets/images/pic1.jpg" alt="Login Image">
    </div>

<div class="form-container">
    <div class="login-form">
        <h2>Login</h2>

        <%
            String message = (String) request.getAttribute("message");
            if (message != null) {
        %>
        <div class="alert"><%= message %></div>
        <%
            }
        %>

        <form action="auth" method="POST">
            <input type="hidden" name="action" value="login">
            <input type="email" name="email" placeholder="Email" required>
            <input type="password" name="password" placeholder="Password" required>
            <button type="submit">Login</button>
        </form>


    </div>
</div>
</div>
</body>
</html>