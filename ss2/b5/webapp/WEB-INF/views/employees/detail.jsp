<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head><title>Chi tiết nhân viên</title></head>
<body>
    <%@ include file="../header.jsp" %>
    <h3>Thông tin chi tiết: <c:out value="${employee.code}"/></h3>
    <p>Họ tên: <c:out value="${employee.fullName}"/></p>
    <p>Phòng ban: <c:out value="${employee.department}"/></p>
    <p>Lương: 
        <c:choose>
            <c:when test="${sessionScope.role == 'hr_manager'}">
                <fmt:formatNumber value="${employee.salary}" type="currency" currencySymbol="VNĐ"/>
            </c:when>
            <c:otherwise>*** (Bị ẩn)</c:otherwise>
        </c:choose>
    </p>
    <a href="<c:url value='/employees'/>">Quay lại</a>
</body>
</html>