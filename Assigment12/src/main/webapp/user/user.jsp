<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/28/2023
  Time: 9:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/dataTables.bootstrap4.min.css">
</head>
<body>
<%@ page import="com.example.assigment12.model.UserEntity" %>
<% UserEntity user = (UserEntity) request.getAttribute("user"); %>
<form action="userServlet" method="post">
    <div class="form-group">
        <label for="name">Name</label>
        <input type="text" class="form-control" id="name" name="name" placeholder="Name" value="<% if (user != null) { %> <%= user.getName() %> <% } %>">
    </div>
    <div class="form-group">
        <label for="email">Email address</label>
        <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp" placeholder="Enter email" value="<% if (user != null) { %> <%= user.getEmail() %> <% } %>">
    </div>
    <div class="form-group">
        <input type="hidden" class="form-control" id="id" name="id" aria-describedby="id" placeholder="userId" value="<% if (user != null) { %> <%= user.getId() %> <% } %>">
    </div>
    <div class="form-group">
        <input type="hidden" class="form-control" id="createOrUpdate" name="createOrUpdate" aria-describedby="createOrUpdate" value="true">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>
</body>
</html>
