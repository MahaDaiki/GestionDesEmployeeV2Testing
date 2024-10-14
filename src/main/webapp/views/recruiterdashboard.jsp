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
    <link rel="stylesheet" href="assets/css/Style.css">
    <title>Recruiter Dashboard</title>
</head>
<body>
<div class="form-offer">
<form action="createJobOffer" method="post">
    <div class="input-group">
        <label for="jobTitle">Job Title:</label>
        <input type="text" id="jobTitle" name="jobTitle" required>
    </div>
    <div class="input-group">
        <label for="description">Description:</label>
        <textarea id="description" name="description" required></textarea>
    </div>
    <div class="input-group">
        <label for="requirements">Requirements:</label>
        <textarea id="requirements" name="requirements" required></textarea>
    </div >
    <div class="input-group">
        <input type="submit" value="Create Job Offer">
    </div>
</form>
</div>

</body>
</html>
