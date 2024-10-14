<%--
  Created by IntelliJ IDEA.
  User: AMD
  Date: 11/10/2024
  Time: 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Employee Dashboard</title>
    <link rel="stylesheet" href="styles.css"> <!-- Link to your CSS file -->
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 800px;
            margin: auto;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        .user-details, .leave-request-form {
            margin-bottom: 20px;
        }
        .user-details p {
            margin: 5px 0;
        }
        .leave-request-form label {
            display: block;
            margin-bottom: 8px;
        }
        .leave-request-form input,
        .leave-request-form textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .leave-request-form button {
            background-color: #5cb85c;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 4px;
            cursor: pointer;
        }
        .leave-request-form button:hover {
            background-color: #4cae4c;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Welcome, ${user.name}!</h1>
    <div class="user-details">
        <h2>Your Details</h2>
        <p><strong>Employee ID:</strong> ${user.id}</p>
        <p><strong>Email:</strong> ${user.email}</p>
        <p><strong>Position:</strong> ${user.position}</p>
        <p><strong>Department:</strong> ${user.department}</p>
        <a href="leaveRequest" class="button">View Leave Requests</a>

    </div>

    <div class="leave-request-form">
        <h2>Request Leave</h2>
        <form action="leaveRequest?action=create" method="post">
            <input type="hidden" name="employeeId" value="${user.id}"> <!-- Include employee ID -->
            <label for="leaveType">Leave Type:</label>
            <select id="leaveType" name="leaveType" required>
                <option value="Sick Leave">Sick Leave</option>
                <option value="Vacation">Vacation</option>
                <option value="Personal Leave">Personal Leave</option>
                <option value="Other">Other</option>
            </select>

            <label for="startDate">Start Date:</label>
            <input type="date" id="startDate" name="startDate" required>

            <label for="endDate">End Date:</label>
            <input type="date" id="endDate" name="endDate" required>

            <label for="reason">Reason for Leave:</label>
            <textarea id="reason" name="reason" rows="4" required></textarea>

            <button type="submit">Submit Leave Request</button>
        </form>
    </div>
</div>
</body>
</html>
