<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tìm kiếm sự kiện</title>
    <style>
        .free { font-weight: bold; color: green; }
        .sold-out { color: red; font-weight: bold; }
        .urgent { color: orange; }
        .available { color: blue; }
        table { width: 100%; border-collapse: collapse; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
    </style>
</head>
<body>

    <h2>Kết quả tìm kiếm cho: <c:out value="${keyword}" /></h2>
    <p>Tìm thấy <strong>${totalFound}</strong> sự kiện.</p>

    <c:choose>
        <c:when test="${empty events}">
            <p>Không tìm thấy sự kiện nào phù hợp.</p>
        </c:when>
        <c:otherwise>
            <table>
                <thead>
                    <tr>
                        <th>STT</th>
                        <th>Tên sự kiện</th>
                        <th>Ngày tổ chức</th>
                        <th>Giá vé</th>
                        <th>Vé còn lại</th>
                        <th>Thao tác</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="event" items="${events}" varStatus="status">
                        <tr>
                            <td>${status.count}</td>
                            <td><c:out value="${event.name}" /></td>
                            <td>${event.eventDate}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${event.price == 0}">
                                        <span class="free">MIỄN PHÍ</span>
                                    </c:when>
                                    <c:otherwise>
                                        <fmt:formatNumber value="${event.price}" type="number" /> VNĐ
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${event.remainingTickets == 0}">
                                        <span class="sold-out">HẾT VÉ</span>
                                    </c:when>
                                    <c:when test="${event.remainingTickets < 10}">
                                        <span class="urgent">Sắp hết (còn ${event.remainingTickets} vé)</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="available">${event.remainingTickets}</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:url var="bookUrl" value="/events/${event.id}/book" />
                                <a href="${bookUrl}" 
                                   <c:if test="${event.remainingTickets == 0}">
                                       onclick="return false;" style="color: gray; text-decoration: none; cursor: not-allowed;"
                                   </c:if>
                                >Đặt vé</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>

    <hr>
    <p>
        Sự kiện đầu tiên: 
        <c:if test="${not empty events}">
            <strong>${fn:toUpperCase(events[0].name)}</strong>
        </c:if>
    </p>
    <p>Độ dài từ khóa: ${fn:length(keyword)} ký tự.</p>

</body>
</html>