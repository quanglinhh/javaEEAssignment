<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/6/2023
  Time: 7:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ListEmployee</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/dataTables.bootstrap4.min.css">
</head>
<body class="container">
<%@ page import="com.example.employeeproject.model.Employee" %>
<%@ page import="java.util.List" %>
    <% List<Employee> employees = (List<Employee>) request.getAttribute("employees"); %>
    <% if (employees.size() != 0) { %>
<table id="example" class="table table-striped table-bordered" style="width:100%">
    <thead>
    <tr>
        <th>id</th>
        <th>FullName</th>
        <th>Address</th>
        <th>Department</th>
        <th>Position</th>
    </tr>
    </thead>
    <% for (Employee employee : employees) { %>
    <tr>
        <td><%= employee.getId() %>
        </td>

        <td><%= employee.getFullName() %>
        </td>

        <td><%= employee.getAddress() %>
        </td>

        <td><%= employee.getDepartment() %>
        </td>

        <td><%= employee.getPosition() %>
        </td>
            </form>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>
<a type="submit" class="btn btn-info" href="userServlet">Create User</a>
    <% } else {%>
<h1>Hello !!</h1>
<a type="submit" class="btn btn-info" href="userServlet">Create User</a>
    <% } %>

</html>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
<script>
    $(document).ready(function () {
        $('#example').DataTable();
    });
</script>