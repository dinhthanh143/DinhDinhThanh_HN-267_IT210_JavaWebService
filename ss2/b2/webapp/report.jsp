<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- Khai báo thư viện thẻ JSTL Core để sử dụng các thẻ điều hướng và vòng lặp --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <%-- JSP Comment: Tiêu đề trang báo cáo --%>
  <title>Báo cáo điểm</title>
</head>
<body>

<%-- Sử dụng c:out để hiển thị tiêu đề an toàn, chống XSS --%>
<h1><c:out value="${reportTitle}" /></h1>

<table border="1">
  <thead>
  <tr>
    <th>STT</th>
    <th>Họ tên</th>
    <th>Điểm</th>
    <th>Xếp loại</th>
  </tr>
  </thead>
  <tbody>
  <%-- Sử dụng c:forEach để thay thế vòng lặp for truyền thống --%>
  <c:forEach var="sv" items="${studentList}" varStatus="status">
    <tr>
      <td>${status.index + 1}</td>

        <%-- Dùng c:out cho dữ liệu chuỗi để ngăn chặn tấn công XSS --%>
      <td><c:out value="${sv.fullName}" /></td>

        <%-- Dùng EL trực tiếp cho dữ liệu số, đã loại bỏ dấu chấm phẩy dư thừa --%>
      <td>${sv.score}</td>

      <td>
          <%-- Sử dụng c:choose để xử lý logic xếp loại thay cho if-else --%>
        <c:choose>
          <c:when test="${sv.score >= 90}">Xuất sắc</c:when>
          <c:when test="${sv.score >= 80}">Giỏi</c:when>
          <c:when test="${sv.score >= 70}">Khá</c:when>
          <c:when test="${sv.score >= 60}">Trung bình khá</c:when>
          <c:when test="${sv.score >= 50}">Trung bình</c:when>
          <c:otherwise>Yếu</c:otherwise>
        </c:choose>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>

</body>
</html>