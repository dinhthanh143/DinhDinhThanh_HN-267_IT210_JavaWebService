<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html><body>
    <form action="<c:url value='/login'/>" method="post">
        <h3>Đăng nhập HR</h3>
        <c:if test="${not empty errorMessage}"><p style="color:red">${errorMessage}</p></c:if>
        User: <input name="username"><br>
        Pass: <input type="password" name="password"><br>
        <button>Login</button>
    </form>
</body></html>