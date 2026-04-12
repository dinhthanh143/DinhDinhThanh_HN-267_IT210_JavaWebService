<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Danh sách sản phẩm</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .info {
            background-color: #f9f9f9;
            padding: 15px;
            margin: 10px 0;
            border-left: 4px solid #007bff;
        }
        .message {
            color: #28a745;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Kết quả tìm kiếm sản phẩm</h1>
        
        <div class="info">
            <p><strong>Loại sản phẩm:</strong> ${category}</p>
            <p><strong>Giới hạn:</strong> ${limit}</p>
            <p class="message"><strong>Thông báo:</strong> ${message}</p>
        </div>
        
        <h3>Danh sách sản phẩm ${category} (giới hạn ${limit} sản phẩm)</h3>
        <p>Đây là trang hiển thị danh sách sản phẩm theo danh mục được chọn.</p>
    </div>
</body>
</html>
