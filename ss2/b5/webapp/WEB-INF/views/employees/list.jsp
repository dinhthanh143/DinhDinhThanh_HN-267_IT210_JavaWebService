<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head><title>Danh sách nhân viên</title></head>
<body>
    <%@ include file="../header.jsp" %>
    <table border="1" width="100%">
        <tr>
            <th>STT</th><th>Mã NV</th><th>Họ tên</th><th>Phòng ban</th><th>Lương</th><th>Ngày vào</th><th>Trạng thái</th><th>Thao tác</th>
        </tr>
        <c:forEach var="e" items="${empList}" varStatus="status">
            <tr>
                <td>${status.count}</td>
                <td><c:out value="${e.code}"/></td>
                <td><c:out value="${e.fullName}"/></td>
                <td><c:out value="${e.department}"/></td>
                <td><fmt:formatNumber value="${e.salary}" type="currency" currencySymbol="VNĐ"/></td>
                <td><fmt:formatDate value="${e.joinDate}" pattern="dd/MM/yyyy"/></td>
                <td>
                    <c:choose>
                        <c:when test="${e.status == 'Đang làm'}"><span style="color:green">${e.status}</span></c:when>
                        <c:when test="${e.status == 'Nghỉ phép'}"><span style="color:orange">${e.status}</span></c:when>
                        <c:otherwise><span style="color:blue">${e.status}</span></c:otherwise>
                    </c:choose>
                </td>
                <td><a href="<c:url value='/employees/${e.code}'/>">Xem chi tiết</a></td>
            </tr>
        </c:forEach>
    </table>
    <p><b>Tổng lương phòng Kỹ thuật: <fmt:formatNumber value="${techTotalSalary}" type="currency" currencySymbol="VNĐ"/></b></p>
    <%@ include file="../footer.jsp" %>
</body>
</html>