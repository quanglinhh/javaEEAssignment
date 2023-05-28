<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/28/2023
  Time: 8:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List User</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/dataTables.bootstrap4.min.css">

</head>
<body class="container">
<%@ page import="com.example.assigment12.model.UserEntity" %>
<%@ page import="java.util.List" %>
<% List<UserEntity> users = (List<UserEntity>) request.getAttribute("users"); %>
<% if (users.size() != 0) { %>
<table id="example" class="table table-striped table-bordered" style="width:100%">
    <thead>
    <tr>
        <th>id</th>
        <th>Name</th>
        <th>Email</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    </thead>
    <% for (UserEntity user : users) { %>
    <tr>
        <td><%= user.getId() %>
        </td>
        <td><%= user.getName() %>
        </td>
        <td><%= user.getEmail() %>
        </td>
        <td>
            <form action="userServlet" method="post">
                <input type="hidden" name="userId" value="<%= user.getId() %>">
                <button type="submit" class="btn btn-info" name="update" value="update">Update</button>
            </form>
        </td>
        <td>
            <form action="userServlet" method="post">
                <input type="hidden" name="userId" value="<%= user.getId() %>">
                <button type="submit" class="btn btn-danger" name="delete" value="delete">Delete</button>
            </form>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>
<% } else {%>
<h1>Hello !!</h1>
<% } %>
</body>
</html>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>

<script>
    $(document).ready(function () {
        $('#example').DataTable();
    });
</script>