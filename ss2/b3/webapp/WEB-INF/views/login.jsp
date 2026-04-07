<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Đăng nhập hệ thống</title>
    <style>
        .error { color: red; margin-bottom: 10px; }
        .login-box { border: 1px solid #ccc; padding: 20px; width: 300px; margin: 50px auto; }
    </style>
</head>
<body>

    <div class="login-box">
        <h2>Đăng nhập</h2>

        <%-- Kiểm tra nếu có lỗi trong Request Scope (Model) thì hiển thị --%>
        <c:if test="${not empty error}">
            <div class="error">
                <c:out value="${error}" />
            </div>
        </c:if>

        <%-- Form gửi dữ liệu POST đến controller --%>
        <form action="<c:url value='/login'/>" method="post">
            <div>
                <label>Tài khoản:</label><br>
                <input type="text" name="username" required>
            </div>
            <br>
            <div>
                <label>Mật khẩu:</label><br>
                <input type="password" name="password" required>
            </div>
            <br>
            <button type="submit">Đăng nhập</button>
        </form>
    </div>

</body>
</html>