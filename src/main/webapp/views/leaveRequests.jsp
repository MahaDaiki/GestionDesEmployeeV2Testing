<%--
  Created by IntelliJ IDEA.
  User: AMD
  Date: 12/10/2024
  Time: 02:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Leave Requests</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 900px;
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
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        .status-select {
            padding: 5px;
            border-radius: 4px;
            border: 1px solid #ccc;
        }
        .update-button {
            background-color: #5bc0de;
            color: white;
            border: none;
            padding: 5px 10px;
            border-radius: 4px;
            cursor: pointer;
        }
        .update-button:hover {
            background-color: #31b0d5;
        }
        .back-button {
            display: block;
            margin: 20px auto;
            background-color: #f44336;
            color: white;
            padding: 10px 15px;
            text-align: center;
            border-radius: 4px;
            text-decoration: none;
        }
        .back-button:hover {
            background-color: #d32f2f;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>All Leave Requests</h1>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Employee</th>
            <th>Reason</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="leaveRequest" items="${leaveRequests}">
            <tr>
                <td>${leaveRequest.id}</td>
                <td>${leaveRequest.employee.name}</td> <!-- Adjust based on how you access the employee name -->
                <td>${leaveRequest.reason}</td>
                <td>${leaveRequest.startDate}</td>
                <td>${leaveRequest.endDate}</td>
                <td>
                    <form action="admin" method="post">
                        <input type="hidden" name="id" value="${leaveRequest.id}">
                        <select name="status" class="status-select">
                            <option value="PENDING" <c:if test="${leaveRequest.status == 'PENDING'}">selected</c:if>>Pending</option>
                            <option value="APPROVED" <c:if test="${leaveRequest.status == 'APPROVED'}">selected</c:if>>Approved</option>
                            <option value="REJECTED" <c:if test="${leaveRequest.status == 'REJECTED'}">selected</c:if>>Rejected</option>
                        </select>
                        <button type="submit" class="update-button">Update</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty leaveRequests}">
            <tr>
                <td colspan="7">No leave requests found.</td>
            </tr>
        </c:if>
        </tbody>
    </table>
    <a href="auth?action=dashboardemployee" class="back-button">Back to Dashboard</a>
</div>
</body>
</html>

