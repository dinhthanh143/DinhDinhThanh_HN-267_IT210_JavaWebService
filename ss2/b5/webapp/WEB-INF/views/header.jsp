<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<nav style="background: #f4f4f4; padding: 10px; margin-bottom: 20px;">
    <strong>Mini Portal Nhân sự</strong> | 
    <a href="<c:url value='/employees'/>">Danh sách NV</a> |
    <c:if test="${sessionScope.role == 'hr_manager'}">
        <a href="#">Báo cáo lương (Manager Only)</a> |
    </c:if>
    <span>Xin chào, <b><c:out value="${sessionScope.loggedUser}"/></b> (${sessionScope.role})</span> |
    <a href="<c:url value='/logout'/>">Đăng xuất</a>
</nav>