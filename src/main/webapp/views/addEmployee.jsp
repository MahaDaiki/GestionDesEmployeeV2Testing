<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="assets/css/Style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    <title>Add Employee</title>
</head>
<body>
<nav>

</nav>

<div class="container">
    <div class="employee-list">
        <h2>Employee List</h2>
        <c:forEach var="employee" items="${employees}">
            <div class="employee-card">
                <h3>${employee.name}</h3>
                <p>Email: ${employee.email}</p>
                <p>Phone: ${employee.phone_number}</p>
                <p>Position: ${employee.position}</p>
                <p>Department: ${employee.department}</p>
                <p>Salary: ${employee.salary} DH</p>
                <div class="button-group">
                    <a href="employees?action=edit&id=${employee.id}" class="edit-button">Edit</a>
                    <form action="employees" method="post" style="display:inline;">
                        <input type="hidden" name="id" value="${employee.id}">
                        <input type="hidden" name="action" value="delete">
                        <input type="submit" value="Delete" class="delete-button" onclick="return confirm('Are you sure you want to delete this employee?');">
                    </form>
                </div>
            </div>
        </c:forEach>
    </div>

    <div class="form-container">

        <form action="employees" method="post">
          <div class="input-group">
              <h2>Add New Employee</h2>
          </div>
            <input type="hidden" name="action" value="add">
            <div class="input-group">
                <label for="name">Name:</label>
                <input type="text" name="name" id="name" required>
            </div>
            <div class="input-group">
                <label for="email">Email:</label>
                <input type="email" name="email" id="email" required>
            </div>
            <div class="input-group">
                <label for="phone_number">Phone Number:</label>
                <input type="text" name="phone_number" id="phone_number" required pattern="^\d{10}$" title="Please enter a 10-digit phone number.">
            </div>
            <div class="input-group">
                <label for="password">Password:</label>
                <input type="password" name="password" id="password" required>
            </div>
            <div class="input-group">
                <label for="socialSecurityNum">Social Security Number:</label>
                <input type="text" name="socialSecurityNum" id="socialSecurityNum" required>
            </div>
            <div class="input-group">
                <label for="hiringDate">Hiring Date:</label>
                <input type="date" name="hiringDate" id="hiringDate" required>
            </div>
            <div class="input-group">
                <label for="birthDate">Birth Date:</label>
                <input type="date" name="birthDate" id="birthDate" required>
            </div>
            <div class="input-group">
                <label for="department">Department:</label>
                <input type="text" name="department" id="department" required>
            </div>
            <div class="input-group">
                <label for="position">Position:</label>
                <input type="text" name="position" id="position" required>
            </div>
            <div class="input-group">
                <label for="salary">Salary:</label>
                <input type="number" name="salary" id="salary" required min="0" step="0.01">
            </div>
            <div class="input-group">
                <label for="leaveDays">Leave Days:</label>
                <input type="number" name="leaveDays" id="leaveDays" required min="0">
            </div>
            <div class="input-group">
                <label for="childCount">Number of Children:</label>
                <input type="number" name="childCount" id="childCount" required min="0">
            </div>

            <input type="submit" value="Add Employee">
        </form>
    </div>
</div>

</body>
</html>
