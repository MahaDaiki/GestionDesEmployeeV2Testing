<%--
  Created by IntelliJ IDEA.
  User: AMD
  Date: 12/10/2024
  Time: 01:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Leave Requests</title>
    <link rel="stylesheet" href="styles.css"> <!-- Link to your CSS file -->
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            padding: 20px;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ccc;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
            color: #333;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        .no-requests {
            text-align: center;
            color: #888;
            font-style: italic;
        }
    </style>
</head>
<body>
<h1>Your Leave Requests</h1>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Reason</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th>Status</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="leaveRequest" items="${leaveRequests}">
        <tr>
            <td>${leaveRequest.id}</td>
            <td>${leaveRequest.reason}</td>
            <td>${leaveRequest.startDate}</td>
            <td>${leaveRequest.endDate}</td>
            <td>${leaveRequest.status}</td>
        </tr>
    </c:forEach>
    <c:if test="${empty leaveRequests}">
        <tr>
            <td colspan="5" class="no-requests">No leave requests found.</td>
        </tr>
    </c:if>
    </tbody>
</table>
</body>
</html>
