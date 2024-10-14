<%--
  Created by IntelliJ IDEA.
  User: AMD
  Date: 11/10/2024
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="assets/css/Style.css"> <!-- Link your CSS file -->
    <style>
        /* Centering the buttons */
        .button-container {
            display: flex;
            flex-direction: column; /* Stack buttons vertically */
            align-items: center; /* Center items horizontally */
            justify-content: center; /* Center items vertically */
            height: 100vh; /* Full viewport height */
        }

        .nav-button, .button {
            margin: 10px 0;
            padding: 10px 20px;
            background-color: #3273ba;
            color: white;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .nav-button:hover, .button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<nav>   <h1> GEV2 </h1>
    <form action="${pageContext.request.contextPath}/auth" method="POST">
    <input type="hidden" name="action" value="logout">
    <button type="submit">Logout</button>
</form>
</nav>
<div class="button-container">
    <a href="${pageContext.request.contextPath}/admin" class="nav-button">View Leave Requests</a>
    <a href="${pageContext.request.contextPath}/employees?action=add" class="button">Add Employee</a>
</div>

</body>
</html>

