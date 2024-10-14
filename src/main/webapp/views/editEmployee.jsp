<%--
  Created by IntelliJ IDEA.
  User: AMD
  Date: 11/10/2024
  Time: 21:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="assets/css/Style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    <title>Edit Employee</title>
</head>
<body>
<nav>

</nav>

<div class="container">
    <h2>Edit Employee</h2>
    <div class="form-container">

        <form action="employees" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="${employee.id}">

            <div class="input-group">
                <label for="name">Name:</label>
                <input type="text" name="name" id="name" value="${employee.name}" required>
            </div>
            <div class="input-group">
                <label for="email">Email:</label>
                <input type="email" name="email" id="email" value="${employee.email}" required>
            </div>
            <div class="input-group">
                <label for="phone_number">Phone Number:</label>
                <input type="text" name="phone_number" id="phone_number" value="${employee.phone_number}" required pattern="^\d{10}$" title="Please enter a 10-digit phone number.">
            </div>
            <div class="input-group">
                <label for="password">Password:</label>
                <input type="text" name="password" id="password" value="${employee.password}" required>
            </div>
            <div class="input-group">
                <label for="socialSecurityNum">Social Security Number:</label>
                <input type="text" name="socialSecurityNum" id="socialSecurityNum" value="${employee.socialSecurityNum}" required>
            </div>
            <div class="input-group">
                <label for="hiringDate">Hiring Date:</label>
                <input type="date" name="hiringDate" id="hiringDate" value="${employee.hiringDate}" required>
            </div>
            <div class="input-group">
                <label for="birthDate">Birth Date:</label>
                <input type="date" name="birthDate" id="birthDate" value="${employee.birthdate}" required>
            </div>
            <div class="input-group">
                <label for="department">Department:</label>
                <input type="text" name="department" id="department" value="${employee.department}" required>
            </div>
            <div class="input-group">
                <label for="position">Position:</label>
                <input type="text" name="position" id="position" value="${employee.position}" required>
            </div>
            <div class="input-group">
                <label for="salary">Salary:</label>
                <input type="number" name="salary" id="salary" value="${employee.salary}" required min="0" step="0.01">
            </div>
            <div class="input-group">
                <label for="leaveDays">Leave Days:</label>
                <input type="number" name="leaveDays" id="leaveDays" value="${employee.leaveDays}" required min="0">
            </div>
            <div class="input-group">
                <label for="childCount">Number of Children:</label>
                <input type="number" name="childCount" id="childCount" value="${employee.childCount}" required min="0">
            </div>

            <input type="submit" value="Update Employee">
        </form>
    </div>
</div>

</body>
</html>

