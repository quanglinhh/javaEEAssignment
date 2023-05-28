<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/28/2023
  Time: 7:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>User List</h1>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
    </tr>
    </thead>
    <tbody>
    <%@ page import="com.example.assigment12.model.UserEntity" %>
    <%@ page import="java.util.List" %>
    <% List<UserEntity> users = (List<UserEntity>) request.getAttribute("users"); %>
    <% if (users.size()!=0) { %>
    <% for (UserEntity user : users) { %>
    <tr>
        <td><%= user.getId() %></td>
        <td><%= user.getName() %></td>
    </tr>
    <% } %>
    <% }else {%>
    <h1>Eo co gi dau</h1>
    <%  } %>
    </tbody>
</table>
</body>
</html>
