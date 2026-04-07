<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head><title>Danh sách đơn hàng</title></head>
<body>
    <h2>Xin chào, <c:out value="${sessionScope.loggedUser}"/>! 
        Vai trò: <c:out value="${sessionScope.role}"/></h2>
    
    <table border="1" cellpadding="10">
        <thead>
            <tr>
                <th>Mã đơn</th>
                <th>Sản phẩm</th>
                <th>Tổng tiền</th>
                <th>Ngày đặt</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="o" items="${orders}">
                <tr>
                    <td>${o.id}</td>
                    <td><c:out value="${o.productName}"/></td>
                    <td><fmt:formatNumber value="${o.amount}" type="currency" currencySymbol="VNĐ" /></td>
                    <td><fmt:formatDate value="${o.orderDate}" pattern="dd/MM/yyyy" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <hr>
    <p><strong>Tổng lượt xem toàn hệ thống: ${applicationScope.totalViewCount}</strong></p>
    <a href="<c:url value='/logout'/>">Đăng xuất</a>
</body>
</html>