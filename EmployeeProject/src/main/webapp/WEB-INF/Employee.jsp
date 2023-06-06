<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/6/2023
  Time: 7:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Employee</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/dataTables.bootstrap4.min.css">
</head>
<body>
<%@ page import="com.example.employeeproject.model.Employee" %>
<% Employee employee = (Employee) request.getAttribute("employee"); %>
<form action="employeeServlet" method="post">
    <div class="form-group">
        <label for="fullName">Name</label>
        <input type="text" class="form-control" id="fullName" name="fullName" placeholder="Name" >
    </div>
    <div class="form-group">
        <label for="birthDay">Birthday address</label>
        <input type="date" class="form-control" id="birthDay" name="birthDay" aria-describedby="birthDay" >
    </div>
    <div class="form-group">
        <label for="Address">Address</label>
        <input type="text" class="form-control" id="Address" name="address" aria-describedby="address" placeholder="Enter address" >
    </div>
    <div class="form-group">
        <label for="position">Address</label>
        <input type="text" class="form-control" id="position" name="position" aria-describedby="position" placeholder="Enter position" >
    </div>
    <div class="form-group">
        <label for="department">Address</label>
        <input type="text" class="form-control" id="department" name="department" aria-describedby="department" placeholder="Enter department" >
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>
</body>
</html>
