<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Thẻ Sinh Viên Điện Tử</title>
    <style>
        .card { border: 2px solid #2c3e50; padding: 25px; width: 380px; border-radius: 12px; margin-top: 20px; box-shadow: 5px 5px 15px #eee; }
        .error-msg { background-color: #fee; color: #c0392b; padding: 10px; border-radius: 5px; margin: 10px 0; border: 1px solid #fab1a0; }
        .label { font-weight: bold; color: #34495e; }
        /* Định nghĩa màu sắc theo GPA */
        .rank-good { color: #27ae60; font-weight: bold; }
        .rank-fair { color: #2980b9; font-weight: bold; }
        .rank-average { color: #e67e22; font-weight: bold; }
        .rank-warning { color: #c0392b; font-weight: bold; text-decoration: underline; }
    </style>
</head>
<body>

    <h2>Hệ thống Tra cứu Thẻ Sinh viên</h2>
    
    <form action="${pageContext.request.contextPath}/student-card" method="get">
        Mã sinh viên: <input type="text" name="msv" placeholder="VD: SV001" value="<c:out value='${msv}'/>">
        <button type="submit">Tra cứu</button>
    </form>

    <%-- KHỐI 1: HIỂN THỊ LỖI --%>
    <c:if test="${not empty errorMessage}">
        <div class="error-msg">
            <c:out value="${errorMessage}" />
        </div>
    </c:if>

    <%-- KHỐI 2: HIỂN THỊ THẺ --%>
    <c:if test="${not empty studentName}">
        <div class="card">
            <h3 style="text-align: center; color: #2c3e50;">THẺ SINH VIÊN</h3>
            <p><span class="label">Mã số:</span> <c:out value="${msv}" /></p>
            <p><span class="label">Họ tên:</span> <c:out value="${studentName}" /></p>
            <p><span class="label">Khoa:</span> <c:out value="${faculty}" /></p>
            <p><span class="label">Năm học:</span> ${year}</p>
            <p><span class="label">GPA:</span> ${gpa}</p>
            
            <p><span class="label">Học lực:</span>
                <c:choose>
                    <c:when test="${gpa >= 8.0}"><span class="rank-good">Giỏi</span></c:when>
                    <c:when test="${gpa >= 6.5}"><span class="rank-fair">Khá</span></c:when>
                    <c:when test="${gpa >= 5.0}"><span class="rank-average">Trung bình</span></c:when>
                    <c:otherwise><span class="rank-warning">Cảnh báo học vụ</span></c:otherwise>
                </c:choose>
            </p>
        </div>
    </c:if>

    <div style="margin-top: 20px;">
        <a href="${pageContext.request.contextPath}/student-card">Làm mới tra cứu</a>
    </div>

</body>
</html>