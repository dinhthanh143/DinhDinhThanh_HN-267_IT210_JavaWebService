<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Thẻ Sinh Viên Điện Tử</title>
    <style>
        .card { border: 2px solid #333; padding: 20px; width: 400px; border-radius: 10px; margin-top: 20px; font-family: Arial; }
        .error { color: red; font-weight: bold; border: 1px solid red; padding: 10px; display: inline-block; }
        /* Màu sắc xếp loại */
        .rank-gioi { color: green; }
        .rank-kha { color: blue; }
        .rank-tb { color: orange; }
        .rank-warning { color: red; font-weight: bold; }
    </style>
</head>
<body>
<h2>Hệ thống Tra cứu Thẻ Sinh viên</h2>
<form action="<c:url value='/student-card'/>" method="get">
    Mã số sinh viên: <input type="text" name="msv" value="<c:out value='${param.msv}'/>">
    <button type="submit">Xem thẻ</button>
</form>

<c:if test="${not empty errorMessage}">
    <div class="error"><c:out value="${errorMessage}"/></div>
</c:if>

<c:if test="${not empty student}">
    <div class="card">
        <h3>THẺ SINH VIÊN ĐIỆN TỬ</h3>
        <p><strong>Mã SV:</strong> <c:out value="${student.msv}"/></p>
        <p><strong>Họ và tên:</strong> <c:out value="${student.name}"/></p>
        <p><strong>Khoa:</strong> <c:out value="${student.faculty}"/></p>
        <p><strong>Năm học:</strong> ${student.year}</p>
        <p><strong>GPA:</strong> ${student.gpa}</p>

        <p><strong>Xếp loại:</strong>
            <c:choose>
                <c:when test="${student.gpa >= 8.0}">
                    <span class="rank-gioi">Giỏi</span>
                </c:when>
                <c:when test="${student.gpa >= 6.5}">
                    <span class="rank-kha">Khá</span>
                </c:when>
                <c:when test="${student.gpa >= 5.0}">
                    <span class="rank-tb">Trung bình</span>
                </c:when>
                <c:otherwise>
                    <span class="rank-warning">Cảnh báo học vụ</span>
                </c:otherwise>
            </c:choose>
        </p>
    </div>
</c:if>
<br>
<a href="${pageContext.request.contextPath}/student-card">Tra cứu sinh viên khác</a>
</body>
</html>