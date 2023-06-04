<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/4/2023
  Time: 10:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Đăng nhập</h1>
<form action="j_security_check" method="post">
    <label for="j_username">Tên người dùng:</label>
    <input type="text" id="j_username" name="j_username" required><br>

    <label for="j_password">Mật khẩu:</label>
    <input type="password" id="j_password" name="j_password" required><br>

    <input type="submit" value="Đăng nhập">
</form>
</body>
</html>
